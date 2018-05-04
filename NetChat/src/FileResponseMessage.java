import java.net.InetAddress;

public class FileResponseMessage extends Message {
	private boolean ifAgree = false;
	private InetAddress inetAddress;
	private int port ;
	public FileResponseMessage(String srcUser, String dstUser, boolean ifAgree, InetAddress inetAddress, int port) {
		super(srcUser, dstUser);
		this.ifAgree = ifAgree;
		this.inetAddress = inetAddress;
		this.port = port;
	}
	public boolean isIfAgree() {
		return ifAgree;
	}
	public void setIfAgree(boolean ifAgree) {
		this.ifAgree = ifAgree;
	}
	public InetAddress getInetAddress() {
		return inetAddress;
	}
	public void setInetAddress(InetAddress inetAddress) {
		this.inetAddress = inetAddress;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	

}
