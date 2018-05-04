import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TestServer {
	public static void main(String[] args) throws Exception{
		int port = 9999;
		ServerSocket serverSocket = new ServerSocket(port);
		
		
			try(
					Socket socket = serverSocket.accept();
					//�õ��������������
					ObjectInputStream oiStream = new ObjectInputStream(socket.getInputStream());
					
					ObjectOutputStream ooStream = new ObjectOutputStream(socket.getOutputStream());	
					){
				while (true) {
				Message message = (Message) oiStream.readObject();
				if (message instanceof ChatMessage) {
					ChatMessage chatMessage = (ChatMessage) message;
					if (chatMessage.isPubChatMessage()) {
						System.out.println("�յ��˹�����Ϣ��Ӧ����ת�������������û�");
					}else{
						System.out.println("�յ�˽����Ϣ�������ݷ���"+chatMessage.getDstUser());
					}
					
				} else if (message instanceof UserStateMessage) {
					UserStateMessage uStateMessage = (UserStateMessage) message;
					if (uStateMessage.isUserOnline()) {
						System.out.println(uStateMessage.getSrcUser()+"�����ˣ�");
						System.out.println("��������Ϣ�����������������û�");	
					}else if (uStateMessage.isUserOffline()) {
						System.out.println(uStateMessage.getSrcUser()+"������");
						System.out.println();
					}
					
				} 
			}
		}
	}
}
