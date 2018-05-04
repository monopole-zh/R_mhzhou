import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyStore;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class Server extends JFrame {
	private ServerSocket serverSocket;
	private final int port = 9999;
	// ���������û����û�����Socket��Ϣ
	private final UserManager userManager = new UserManager();
	// �������û��б�ListModel��,����ά���������û��б�����ʾ������
	final DefaultTableModel onlineUsersDtm = new DefaultTableModel();
	// ���ڿ���ʱ����Ϣ��ʾ��ʽ
	// private final SimpleDateFormat dateFormat = new
	// SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	UserDatabase userDatabase = new UserDatabase();
	private final JPanel contentPane;
	private final JTable tableOnlineUsers;
	private final JTextPane textPaneMsgRecord;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Server frame = new Server();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Server() {
		setTitle("\u670D\u52A1\u5668");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 561, 403);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JSplitPane splitPaneNorth = new JSplitPane();
		splitPaneNorth.setResizeWeight(0.5);
		splitPaneNorth.setDividerSize(10);
		contentPane.add(splitPaneNorth, BorderLayout.CENTER);

		JScrollPane scrollPaneMsgRecord = new JScrollPane();
		scrollPaneMsgRecord.setPreferredSize(new Dimension(100, 300));
		scrollPaneMsgRecord.setViewportBorder(new TitledBorder(null,
				"\u6D88\u606F\u8BB0\u5F55", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		splitPaneNorth.setLeftComponent(scrollPaneMsgRecord);

		textPaneMsgRecord = new JTextPane();
		textPaneMsgRecord.setPreferredSize(new Dimension(100, 100));
		scrollPaneMsgRecord.setViewportView(textPaneMsgRecord);

		JScrollPane scrollPaneOnlineUsers = new JScrollPane();
		scrollPaneOnlineUsers.setPreferredSize(new Dimension(100, 300));
		splitPaneNorth.setRightComponent(scrollPaneOnlineUsers);

		onlineUsersDtm.addColumn("�û���");
		onlineUsersDtm.addColumn("IP");
		onlineUsersDtm.addColumn("�˿�");
		onlineUsersDtm.addColumn("��¼ʱ��");
		tableOnlineUsers = new JTable(onlineUsersDtm);
		tableOnlineUsers.setPreferredSize(new Dimension(100, 270));
		tableOnlineUsers.setFillsViewportHeight(true); // ��JTable������������
		scrollPaneOnlineUsers.setViewportView(tableOnlineUsers);

		JPanel panelSouth = new JPanel();
		contentPane.add(panelSouth, BorderLayout.SOUTH);
		panelSouth.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		final JButton btnStart = new JButton("\u542F\u52A8");
		// "����"��ť
		btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					SSLContext sslContext = createSSLContext();
					SSLServerSocketFactory sslServerSocketFactory = sslContext.getServerSocketFactory();
					serverSocket = sslServerSocketFactory.createServerSocket(port);
					String[] supported=((SSLServerSocket) serverSocket).getSupportedCipherSuites();
				    ((SSLServerSocket) serverSocket).setEnabledCipherSuites(supported);
					// �ڡ���Ϣ��¼���ı������ú�ɫ��ʾ�������������ɹ�X��������ʱ����Ϣ
					String msgRecord = dateFormat.format(new Date())
							+ " �����������ɹ�" + "\r\n";
					addMsgRecord(msgRecord, Color.red, 12, false, false);
					// �����������������û������̡߳������ܲ�����ͻ�����������
					new Thread() {
						@Override
						public void run() {
							while (true) {
								try {
									// ����serverSocket.accept()���������û���������
									Socket socket = serverSocket.accept();
									// Ϊ�������û��������������û������̡߳�
									// ����serverSocket.accept()�������ص�socket���󽻸����û������̡߳�������
									UserHandler userHandler = new UserHandler(
											socket);
									new Thread(userHandler).start();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						};
					}.start();
					btnStart.setEnabled(false);
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panelSouth.add(btnStart);
	}

	public SSLContext createSSLContext() throws Exception {
	    String keyStoreFile = "test.keys";
	    String passphrase = "123456";
	    KeyStore ks = KeyStore.getInstance("JKS");
	    char[] password = passphrase.toCharArray();
	    ks.load(new FileInputStream(keyStoreFile), password);
	    KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
	    kmf.init(ks, password);
	    SSLContext sslContext = SSLContext.getInstance("SSL");
	    sslContext.init(kmf.getKeyManagers(), null, null);

	    //��Ҫ��ͻ����ṩ��ȫ֤��ʱ���������˿ɴ���TrustManagerFactory��
	    //����������TrustManager��TrustManger������֮������KeyStore�е���Ϣ��
	    //�������Ƿ����ſͻ��ṩ�İ�ȫ֤�顣
	    //String trustStoreFile = "client.keys";    
	    //KeyStore ts = KeyStore.getInstance("JKS");
	    //ts.load(new FileInputStream(trustStoreFile), password);
	    //TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
	    //tmf.init(ts);
	    //sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
	    
	    return sslContext;
	  }
	// ����Ϣ��¼�ı��������һ����Ϣ��¼
	private void addMsgRecord(final String msgRecord, Color msgColor,
			int fontSize, boolean isItalic, boolean isUnderline) {
		final SimpleAttributeSet attrset = new SimpleAttributeSet();
		StyleConstants.setForeground(attrset, msgColor);
		StyleConstants.setFontSize(attrset, fontSize);
		StyleConstants.setUnderline(attrset, isUnderline);
		StyleConstants.setItalic(attrset, isItalic);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Document docs = textPaneMsgRecord.getDocument();
				try {
					docs.insertString(docs.getLength(), msgRecord, attrset);
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
			}
		});
	}

	class UserHandler implements Runnable {
		private final Socket currentUserSocket;
		private ObjectInputStream ois;
		private ObjectOutputStream oos;

		public UserHandler(Socket currentUserSocket) {
			this.currentUserSocket = currentUserSocket;
			try {
				ois = new ObjectInputStream(currentUserSocket.getInputStream());
				oos = new ObjectOutputStream(currentUserSocket.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			try {
				while (true) {
					//����������Ϣ�ж�һ����Ϣ
					Message msg = (Message) ois.readObject();
					if (msg instanceof LoginMessage) {
						if (userDatabase.checkUserIsExist(msg.getSrcUser())) {
							if (userDatabase.checkUserPassword(msg.getSrcUser(), ((LoginMessage) msg).getPassword())) {
								((LoginMessage) msg).setUserExist(true);
								((LoginMessage) msg).setCorrectPwd(true);
							} else {
								((LoginMessage) msg).setUserExist(true);
							}
						}
						synchronized (oos) {
							oos.writeObject(msg);
							oos.flush();
						}
					}else if (msg instanceof UserStateMessage) {
						// �����û�״̬��Ϣ
						processUserStateMessage((UserStateMessage) msg);
					} else if (msg instanceof ChatMessage) {
						// ����������Ϣ
						processChatMessage((ChatMessage) msg);
					} else if (msg instanceof RegistryMessage) {
						//����ע����Ϣ
						processRegistryMessage((RegistryMessage)msg);
					}else if (msg instanceof FileSendMessage) {
						//�����ļ�������Ϣ
						processFileSendMessage((FileSendMessage)msg);
					}else if (msg instanceof FileResponseMessage) {
						//�����ļ���Ӧ��Ϣ
						processFileResponseMessage((FileResponseMessage)msg);
					}else if (msg instanceof ExpressionMessage) {
						//�����ļ���Ӧ��Ϣ
						processExpressionMessage((ExpressionMessage)msg);
					}else{
						// ���������Ӧ���û���������Ϣ��ʽ ����Ӧ�÷���Ϣ��ʾ�û����������
						System.err.println("�û���������Ϣ��ʽ����!");
					}
				}
			} catch (IOException e) {
				
				if (e.toString().endsWith("Connection reset")) {
					// ����û�δ����������Ϣ��ֱ�ӹر��˿ͻ��ˣ�Ӧ�������ﲹ����룬ɾ���û�������Ϣ
					String [] users = userManager.getAllUsers();
					for (int i = 0; i < users.length; i++) {
						if (userManager.getUserSocket(users[i]).isInputShutdown()) {
							for (int j = 0; j < onlineUsersDtm.getRowCount(); j++) {
								if (onlineUsersDtm.getValueAt(j, 0).equals(users[i])) {
									onlineUsersDtm.removeRow(j);
									break;
								}
							}
							transferMsgToOtherUsers(new UserStateMessage(users[i], null, false));
							final String msgRecord = dateFormat.format(new Date()) + " "
									+ users[i] + "(" + userManager.getUserSocket(users[i]).getInetAddress() + ")" + "������������!\r\n";
							addMsgRecord(msgRecord, Color.green, 12, false, false);
							userManager.removeUser(users[i]);
							break;
						}
					}
					// ���û�������Ϣת�����������������û�					
				} else {
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				if (currentUserSocket != null) {
					try {
						currentUserSocket.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

		// �������û�ת����Ϣ
		private void transferMsgToOtherUsers(Message msg) {
			String[] users = userManager.getAllUsers();
			for (String user : users) {
				if (userManager.getUserSocket(user) != currentUserSocket) {
					try {
						ObjectOutputStream o = userManager.getUserOos(user);
						synchronized (o) {
							o.writeObject(msg);
							o.flush();
						}
					} catch (IOException e) {
						System.err.println("transferMsgToOtherUsersʱio�쳣");
					}
				}
			}
		}

		// �����û�״̬��Ϣ
		private void processUserStateMessage(UserStateMessage msg) {
			String srcUser = msg.getSrcUser();
			if (msg.isUserOnline()) { // �û�������Ϣ
				if (userManager.hasUser(srcUser)) {
					// ���������Ӧ���û��ظ���¼��Ӧ�÷���Ϣ��ʾ�ͻ��ˣ��������
					System.err.println("�û��ظ���¼");
					return;
				}
				// �������ߵ��û�ת����ǰ�����û��б�
				String[] users = userManager.getAllUsers();
				try {
					for (String user : users) {
						UserStateMessage userStateMessage = new UserStateMessage(
								user, srcUser, true);
						synchronized (oos) {
							oos.writeObject(userStateMessage);
							oos.flush();
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				// ���������������û�ת���û�������Ϣ
				transferMsgToOtherUsers(msg);
				// ���û���Ϣ���뵽�������û����б���
				userManager.addUser(srcUser, currentUserSocket, oos, ois);
				onlineUsersDtm.addRow(new Object[] { srcUser,
						currentUserSocket.getInetAddress().getHostAddress(),
						currentUserSocket.getPort(),
						dateFormat.format(new Date()) });
				// ����ɫ���ֽ��û������û�����ʱ����ӵ�����Ϣ��¼���ı�����
				String ip = currentUserSocket.getInetAddress().getHostAddress();
				final String msgRecord = dateFormat.format(new Date()) + " "
						+ srcUser + "(" + ip + ")" + "������!\r\n";
				addMsgRecord(msgRecord, Color.green, 12, false, false);
			} else { // �û�������Ϣ
				if (!userManager.hasUser(srcUser)) {
					// ���������Ӧ���û�δ����������Ϣ��ֱ�ӷ�����������Ϣ��Ӧ�÷���Ϣ��ʾ�ͻ��ˣ��������
					System.err.println("�û�δ���͵�¼��Ϣ�ͷ�����������Ϣ");
					return;
				}
				// ����ɫ���ֽ��û������û�����ʱ����ӵ�����Ϣ��¼���ı�����
				String ip = userManager.getUserSocket(srcUser).getInetAddress()
						.getHostAddress();
				final String msgRecord = dateFormat.format(new Date()) + " "
						+ srcUser + "(" + ip + ")" + "������!\r\n";
				addMsgRecord(msgRecord, Color.green, 12, false, false);
				// �ڡ������û��б���ɾ�������û�
				userManager.removeUser(srcUser);
				for (int i = 0; i < onlineUsersDtm.getRowCount(); i++) {
					if (onlineUsersDtm.getValueAt(i, 0).equals(srcUser)) {
						onlineUsersDtm.removeRow(i);
					}
				}
				// ���û�������Ϣת�����������������û�
				transferMsgToOtherUsers(msg);
			}
		}
		
		private void processExpressionMessage(ExpressionMessage msg) {
			String srcUser = msg.getSrcUser();
			String dstUser = msg.getDstUser();
			if (!msg.getDstUser().equals("")) {
				ObjectOutputStream oFileResponseStream = userManager.getUserOos(dstUser);
				synchronized (oFileResponseStream) {
					try {
						oFileResponseStream.writeObject(msg);
						oFileResponseStream.flush();
					} catch (IOException e) {
						System.err.println("�����������ļ���Ӧ��Ϣ�쳣");
					}
				}
				addMsgRecord(dateFormat.format(new Date())+srcUser+"��"+dstUser+"���ͱ���"+msg.getExpression()+"\r\n", Color.orange, 12, false, false);
			}else {
				addMsgRecord(dateFormat.format(new Date())+srcUser+"���ҷ��ͱ��� ��"+msg.getExpression()+"\r\n", Color.orange, 12, false, false);
				transferMsgToOtherUsers(msg);
			}
			
		}
		
		private void processFileSendMessage(FileSendMessage msg) {
			String srcUser = msg.getSrcUser();
			String dstUser = msg.getDstUser();
			ObjectOutputStream oFileSendStram = userManager.getUserOos(dstUser);
			synchronized (oFileSendStram) {
				try {
					oFileSendStram.writeObject(msg);
					oFileSendStram.flush();
				} catch (IOException e) {
					System.err.println("�����������ļ�������Ϣ�쳣");
				}
			}
			addMsgRecord(dateFormat.format(new Date())+
					srcUser+"��"+dstUser+"������"+msg.getFileSize()+"�ֽڵ��ļ�"+msg.getFileName()
					+"\r\n", Color.orange, 12, false, false);
		}
		
		private void processFileResponseMessage(FileResponseMessage msg) {
			String srcUser = msg.getSrcUser();
			String dstUser = msg.getDstUser();
			ObjectOutputStream oFileResponseStream = userManager.getUserOos(dstUser);
			synchronized (oFileResponseStream) {
				try {
					oFileResponseStream.writeObject(msg);
					oFileResponseStream.flush();
				} catch (IOException e) {
					System.err.println("�����������ļ���Ӧ��Ϣ�쳣");
				}
			}
			addMsgRecord(dateFormat.format(new Date())+srcUser+"��"+dstUser+"��Ӧ�ļ���������"+"\r\n", Color.orange, 12, false, false);
		}
		private boolean processRegistryMessage(RegistryMessage msg) {
			String userName = msg.getSrcUser();
			String password = msg.getPassword();
			String phone = msg.getTelePhone();
			if (userDatabase.insertUser(userName, password, phone)) {
				msg.setRegistrySucccess(true);
				try {
					synchronized (oos) {
						oos.writeObject(msg);
						oos.flush();
					}
				} catch (IOException e) {
					System.err.println("����������ע����Ϣʱ���ִ���");
				}
				addMsgRecord(dateFormat.format(new Date())+"��ע���û�"+userName+"\r\n", Color.green, 12, false, false);
				return true;
			}
			return false;
		}
		// �����û�������������Ϣ
		private void processChatMessage(ChatMessage msg) {
			String srcUser = msg.getSrcUser();
			String dstUser = msg.getDstUser();
			String msgContent = msg.getMsgContent();
			if (userManager.hasUser(srcUser)) {
				if (msg.isPubChatMessage()) {
					// �ú�ɫ���ֽ��յ���Ϣ��ʱ�䡢������Ϣ���û�������Ϣ������ӵ�����Ϣ��¼���ı�����
					final String msgRecord = dateFormat.format(new Date()) + " "
							+ srcUser + "˵: " + msgContent + "\r\n";
					addMsgRecord(msgRecord, Color.black, 12, false, false);
					// ��������Ϣת�����������������û�
					transferMsgToOtherUsers(msg);
				} else {
					// ��˽����Ϣת����Ŀ���û�������δʵ��
					final String msgPrivateRecord = dateFormat.format(new Date()) + " "
							+ srcUser + "��"+dstUser+"˵: " + msgContent + "\r\n";
					addMsgRecord(msgPrivateRecord, Color.black, 12, false, false);
					
					ObjectOutputStream oStream=userManager.getUserOos(dstUser);
					try {
						synchronized (oStream) {
							oStream.writeObject(msg);
							oStream.flush();
						}
					} catch (IOException e) {
						System.err.println("����˽����ϢIO�쳣");
					}
				}
			} else {
				// ���������Ӧ���û�δ����������Ϣ��ֱ�ӷ�����������Ϣ��Ӧ�÷���Ϣ��ʾ�ͻ��ˣ��������
				System.err.println("����δ����������Ϣ��ֱ�ӷ�����������Ϣ");
				return;
			}
		}
	}
}

// ���������û���Ϣ
class UserManager {
	private final Map<String, User> onLineUsers;

	public UserManager() {
		onLineUsers = new HashMap<String, User>();
	}

	// �ж�ĳ�û��Ƿ�����
	public boolean hasUser(String userName) {
		return onLineUsers.containsKey(userName);
	}

	// �ж������û��б��Ƿ��
	public boolean isEmpty() {
		return onLineUsers.isEmpty();
	}

	// ��ȡ�����û���Socket�ĵ��������װ�ɵĶ��������
	public ObjectOutputStream getUserOos(String userName) {
		if (hasUser(userName)) {
			return onLineUsers.get(userName).getOos();
		}
		return null;
	}

	// ��ȡ�����û���Socket�ĵ���������װ�ɵĶ���������
	public ObjectInputStream getUserOis(String userName) {
		if (hasUser(userName)) {
			return onLineUsers.get(userName).getOis();
		}
		return null;
	}

	// ��ȡ�����û���Socket
	public Socket getUserSocket(String userName) {
		if (hasUser(userName)) {
			return onLineUsers.get(userName).getSocket();
		}
		return null;
	}

	// ��������û�
	public boolean addUser(String userName, Socket userSocket) {
		if ((userName != null) && (userSocket != null)) {
			onLineUsers.put(userName, new User(userSocket));
			return true;
		}
		return false;
	}

	// ��������û�
	public boolean addUser(String userName, Socket userSocket,
			ObjectOutputStream oos, ObjectInputStream ios) {
		if ((userName != null) && (userSocket != null) && (oos != null)
				&& (ios != null)) {
			onLineUsers.put(userName, new User(userSocket, oos, ios));
			return true;
		}
		return false;
	}

	// ɾ�������û�
	public boolean removeUser(String userName) {
		if (hasUser(userName)) {
			onLineUsers.remove(userName);
			return true;
		}
		return false;
	}

	// ��ȡ���������û���
	public String[] getAllUsers() {
		String[] users = new String[onLineUsers.size()];
		int i = 0;
		for (Map.Entry<String, User> entry : onLineUsers.entrySet()) {
			users[i++] = entry.getKey();
		}
		return users;
	}

	// ��ȡ�����û�����
	public int getOnlineUserCount() {
		return onLineUsers.size();
	}
}

class User {
	//ԭ������˵���ĸ���Ա�������ǳ������͵�
	private final Socket socket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private final Date logonTime;

	public User(Socket socket) {
		this.socket = socket;
		try {
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		logonTime = new Date();
	}

	public User(Socket socket, ObjectOutputStream oos, ObjectInputStream ois) {
		this.socket = socket;
		this.oos = oos;
		this.ois = ois;
		logonTime = new Date();
	}

	public User(Socket socket, ObjectOutputStream oos, ObjectInputStream ois,
			Date logonTime) {
		this.socket = socket;
		this.oos = oos;
		this.ois = ois;
		this.logonTime = logonTime;
	}

	public Socket getSocket() {
		return socket;
	}

	public ObjectOutputStream getOos() {
		return oos;
	}

	public ObjectInputStream getOis() {
		return ois;
	}

	public Date getLogonTime() {
		return logonTime;
	}

}
