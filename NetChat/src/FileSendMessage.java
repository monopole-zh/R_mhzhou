import java.io.File;

public class FileSendMessage extends Message {
	private String fileName;
	private long fileSize;
	public FileSendMessage(String srcUser, String dstUser, String fileName, long fileSize) {
		super(srcUser, dstUser);
		this.fileName = fileName;
		this.fileSize = fileSize;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
}
