import java.awt.BorderLayout;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;
public class FileRecieveHandler extends JFrame {
	private InputStream inputStream;
	private long fileSize;
	private String filePathAndName;
	private FileOutputStream fStream;
	private JProgressBar progressBar;
	private JPanel contentPane;
	public  FileRecieveHandler (InputStream inputStream,String fileName,long filesize) {
		this.inputStream =inputStream;
		this.filePathAndName = fileName;
		this.fileSize = filesize;	
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 60);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        progressBar = new JProgressBar(0, 100);
        contentPane.add(progressBar, BorderLayout.NORTH);
        this.setVisible(true);
	}
	class PrograssRecieveHandler extends SwingWorker<Boolean, Long>{
		protected Boolean doInBackground() throws Exception {
			try {	
				fStream = new FileOutputStream(filePathAndName);
				byte[] bytes = new byte[2048];
				int length = 0;
				long doLength = 0 ;
				while ((length = inputStream.read(bytes)) != -1) {
					fStream.write(bytes,0,length);
					doLength += length;
					publish(doLength);
				}
				fStream.close();
				inputStream.close();
			} catch (IOException e) {
				try {
					fStream.close();
					inputStream.close();
				} catch (IOException e1) {
					System.err.println("关流异常");
				}	
			}	
			return true;
		}

		protected void process(List<Long> chunks) {
			long part = fileSize/100;//表示一百份的数据，每一份的大小
			long nowProgress = chunks.get(chunks.size()-1);
			int nowPart =(int) (nowProgress/part);
			progressBar.setValue(nowPart);
		}
		@Override
		protected void done() {
			JOptionPane.showMessageDialog(contentPane, "文件接收完成！");
		}
	}
}
