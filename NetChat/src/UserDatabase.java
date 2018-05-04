import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

public class UserDatabase {
	// ## DEFINE VARIABLES SECTION ##
	// define the driver to use
	String driver = "org.apache.derby.jdbc.EmbeddedDriver";
	// the database name
	String dbName = "USERDB";
	// define the Derby connection URL to use
	String connectionURL = "jdbc:derby:" + dbName + ";create=true";
	Connection conn;

	public UserDatabase() {
		// ## LOAD DRIVER SECTION ##
		try {
			/*
			 * * Load the Derby driver.* When the embedded Driver is used this
			 * action start the Derby engine.* Catch an error and suggest a
			 * CLASSPATH problem
			 */
			Class.forName(driver);//加载JDBC数据库驱动器
			System.out.println(driver + " loaded. ");
		} catch (java.lang.ClassNotFoundException e) {
			System.err.print("ClassNotFoundException: ");
			System.err.println(e.getMessage());
			System.out.println("\n    >>> Please check your CLASSPATH variable   <<<\n");
		}
		String createString = "create table MYUSERSTABLE " // 表名
				+ "(USERNAME varchar(20) primary key not null, " // 用户名
				+ "HASHEDPWD char(20) for bit data, " // 口令的HASH值
				+ "SALT char(10) for bit data,"
				+ "PHONE varchar(20),"
				+ "REGISTERTIME timestamp default CURRENT_TIMESTAMP)"; // 注册时间

		try {
			DriverManager.setLogWriter(new PrintWriter(new File("bbb.txt")));
			// Create (if needed) and connect to the database
			conn = DriverManager.getConnection(connectionURL);
			// Create a statement to issue simple commands.
			Statement s = conn.createStatement();
			// Call utility method to check if table exists.
			// Create the table if needed
			if (!checkTable(conn)) {
				System.out.println(" . . . . creating table MYUSERSTABLE");
				s.execute(createString);
			}
			s.close();
			System.out.println("Database openned normally");
		} catch (SQLException e) {
			errorPrint(e);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	// Insert a new user into the MYUSERSTABLE table
	public boolean insertUser(String userName, String userPwd,String phone) {
		try {
			if (!userName.isEmpty() && !userPwd.isEmpty()) {
				//PreparedStatement用来执行预编译sql语句
				PreparedStatement psTest = conn.prepareStatement(
						"select * from MYUSERSTABLE where USERNAME=?",
						ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				psTest.setString(1, userName);
				ResultSet rs = psTest.executeQuery();
				rs.last();
				int n = rs.getRow();
				psTest.close();
				if (n == 0) {
					PreparedStatement psInsert = conn.prepareStatement("insert into MYUSERSTABLE values (?,?,?,?,?)");
					MessageDigest digest = MessageDigest.getInstance("SHA-1");
					byte [] salt = new byte[10];
					new SecureRandom().nextBytes(salt);
					digest.update(userPwd.getBytes());
					digest.update(salt);
					byte[] hashedPwd = digest.digest();
					psInsert.setString(1, userName);
					psInsert.setBytes(2, hashedPwd);
					psInsert.setBytes(3, salt);
					psInsert.setString(4, phone);
					psInsert.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
					psInsert.executeUpdate();
					psInsert.close();
					System.out.println("成功注册新用户" + userName);
					return true;
				}
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			errorPrint(e);
		}
		System.out.println("用户" + userName + "已经存在");
		return false;
	}

	public boolean deleteUser(String userName, String userPwd) {
		if (checkUserPassword(userName, userPwd) == true) {
			try {
				PreparedStatement psDelete = conn
						.prepareStatement("delete from MYUSERSTABLE where USERNAME=?");
				psDelete.setString(1, userName);
				int n = psDelete.executeUpdate();
				psDelete.close();
				if (n > 0) {
					System.out.println("成功删除用户" + userName);
					return true;
				} else {
					System.out.println("删除用户" + userName + "失败");
					return false;
				}
			} catch (SQLException e) {
				errorPrint(e);
			}
		}
		return false;
	}
	
	// check if userName with password userPwd can logon
	public boolean checkUserPassword(String userName, String userPwd) {
		try {
			if (!userName.isEmpty() && !userPwd.isEmpty()) {
				Statement ss = conn.createStatement();
				// Select all records in the MYUSERSTABLE table
				String sql = "select * from MYUSERSTABLE where USERNAME = '"+userName+"'";
				ResultSet users = ss.executeQuery(sql);
				byte []  salt = null ;
				while(users.next()){
					salt = users.getBytes(3);
				}
				PreparedStatement psTest = conn.prepareStatement(
						"select * from MYUSERSTABLE where USERNAME=? and HASHEDPWD=?",
						ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				MessageDigest digest = MessageDigest.getInstance("SHA-1");
				digest.update(userPwd.getBytes());
				digest.update(salt);
				byte[] hashedPwd = digest.digest();
				psTest.setString(1, userName);
				psTest.setBytes(2, hashedPwd);
				ResultSet rs = psTest.executeQuery();
				rs.last();
				int n = rs.getRow();
				psTest.close();
				return n > 0 ? true : false;
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			errorPrint(e);
		}
		return false;
	}
	public boolean checkUserIsExist( String userName){
		try {
			if (!userName.isEmpty()) {
				PreparedStatement psTest = conn.prepareStatement(
						"select * from MYUSERSTABLE where USERNAME=? ",
						ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				psTest.setString(1, userName);
				ResultSet rs = psTest.executeQuery();
				rs.last();
				int n = rs.getRow();
				psTest.close();
				return n > 0 ? true : false;
			}else {
				return false;
			}
		} catch (SQLException e) {
			errorPrint(e);
			return false;
		}	
	}
	// show the information of all users in table MYUSERSTABLE, should be called
	// before the program exited
	public void showAllUsers() {
		String printLine = "  ______________当前所有注册用户______________";
		try {
			Statement s = conn.createStatement();
			// Select all records in the MYUSERSTABLE table
			ResultSet users = s.executeQuery("select USERNAME, HASHEDPWD,SALT,PHONE,REGISTERTIME from MYUSERSTABLE order by REGISTERTIME");

			// Loop through the ResultSet and print the data
			System.out.println(printLine);
			while (users.next()) {
				System.out.println("User-Name: " + users.getString("USERNAME")
						+ " Hashed-Pasword: "
						+ new HexBinaryAdapter().marshal(users.getBytes("HASHEDPWD"))
						+" salt:"
						+ new HexBinaryAdapter().marshal(users.getBytes("SALT"))
						+" phone: "+users.getString("PHONE")
						+ " Regiester-Time " + users.getTimestamp("REGISTERTIME"));
			}
			System.out.println(printLine);
			// Close the resultSet
			s.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 关闭数据库
	public void shutdownDatabase() {
		/***
		 * In embedded mode, an application should shut down Derby. Shutdown
		 * throws the XJ015 exception to confirm success.
		 ***/
		if (driver.equals("org.apache.derby.jdbc.EmbeddedDriver")) {
			boolean gotSQLExc = false;
			try {
				conn.close();
				DriverManager.getConnection("jdbc:derby:;shutdown=true");
			} catch (SQLException se) {
				if (se.getSQLState().equals("XJ015")) {
					gotSQLExc = true;
				}
			}
			if (!gotSQLExc) {
				System.out.println("Database did not shut down normally");
			} else {
				System.out.println("Database shut down normally");
			}
		}
	}

	/*** Check for USER table ****/
	public boolean checkTable(Connection conTst) throws SQLException {
		try {
			Statement s = conTst.createStatement();
			s.execute("update MYUSERSTABLE set USERNAME= 'TEST', REGISTERTIME = CURRENT_TIMESTAMP where 1=3");
		} catch (SQLException sqle) {
			String theError = (sqle).getSQLState();
			// System.out.println("  Utils GOT:  " + theError);
			/** If table exists will get - WARNING 02000: No row was found **/
			if (theError.equals("42X05")) // Table does not exist
			{
				return false;
			} else if (theError.equals("42X14") || theError.equals("42821")) {
				System.out
						.println("checkTable: Incorrect table definition. Drop table MYUSERSTABLE and rerun this program");
				throw sqle;
			} else {
				System.out.println("checkTable: Unhandled SQLException");
				throw sqle;
			}
		}
		return true;
	}

	// Exception reporting methods with special handling of SQLExceptions
	static void errorPrint(Throwable e) {
		if (e instanceof SQLException) {
			SQLExceptionPrint((SQLException) e);
		} else {
			System.out.println("A non SQL error occured.");
			e.printStackTrace();
		}
	}

	// Iterates through a stack of SQLExceptions
	static void SQLExceptionPrint(SQLException sqle) {
		while (sqle != null) {
			System.out.println("\n---SQLException Caught---\n");
			System.out.println("SQLState:   " + (sqle).getSQLState());
			System.out.println("Severity: " + (sqle).getErrorCode());
			System.out.println("Message:  " + (sqle).getMessage());
			sqle.printStackTrace();
			sqle = sqle.getNextException();
		}
	}
}
