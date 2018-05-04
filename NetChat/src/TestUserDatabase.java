public class TestUserDatabase {
	public static void main(String[] args) {
		UserDatabase userDatabase = new UserDatabase();
		// 注册三个新用户
		//userDatabase.insertUser("aaa", "aaa","18322799734");
		userDatabase.insertUser("bbb", "bbb","18322798794");
	//	userDatabase.insertUser("ccc", "ccc","18322729738");
		// 显示所有已注册用户信息
		userDatabase.showAllUsers();
		// 用户名"ccc"，口令"ccc"是否能登录
		// 删除用户"bbb"
		userDatabase.checkUserPassword("aaa","aaa");
		// 显示所有已注册用户信息
		userDatabase.showAllUsers();
		userDatabase.shutdownDatabase();
	}
}
