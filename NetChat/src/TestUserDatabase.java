public class TestUserDatabase {
	public static void main(String[] args) {
		UserDatabase userDatabase = new UserDatabase();
		// ע���������û�
		//userDatabase.insertUser("aaa", "aaa","18322799734");
		userDatabase.insertUser("bbb", "bbb","18322798794");
	//	userDatabase.insertUser("ccc", "ccc","18322729738");
		// ��ʾ������ע���û���Ϣ
		userDatabase.showAllUsers();
		// �û���"ccc"������"ccc"�Ƿ��ܵ�¼
		// ɾ���û�"bbb"
		userDatabase.checkUserPassword("aaa","aaa");
		// ��ʾ������ע���û���Ϣ
		userDatabase.showAllUsers();
		userDatabase.shutdownDatabase();
	}
}
