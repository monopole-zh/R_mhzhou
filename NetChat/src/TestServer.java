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
					//得到对象输入输出流
					ObjectInputStream oiStream = new ObjectInputStream(socket.getInputStream());
					
					ObjectOutputStream ooStream = new ObjectOutputStream(socket.getOutputStream());	
					){
				while (true) {
				Message message = (Message) oiStream.readObject();
				if (message instanceof ChatMessage) {
					ChatMessage chatMessage = (ChatMessage) message;
					if (chatMessage.isPubChatMessage()) {
						System.out.println("收到了公聊消息，应将其转发给其他在线用户");
					}else{
						System.out.println("收到私聊消息，将数据发给"+chatMessage.getDstUser());
					}
					
				} else if (message instanceof UserStateMessage) {
					UserStateMessage uStateMessage = (UserStateMessage) message;
					if (uStateMessage.isUserOnline()) {
						System.out.println(uStateMessage.getSrcUser()+"上线了！");
						System.out.println("将在线信息发给所有其他在线用户");	
					}else if (uStateMessage.isUserOffline()) {
						System.out.println(uStateMessage.getSrcUser()+"下线了");
						System.out.println();
					}
					
				} 
			}
		}
	}
}
