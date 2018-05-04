import java.awt.BorderLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;

import javax.net.ssl.ExtendedSSLSession;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;

public class FileSendHandler extends JFrame {
	private JPanel contentPane;
	private JProgressBar progressBar;
	private InetAddress remoteInetAddress ;
	private int remotePort;
	private File file;
	public FileSendHandler(InetAddress remoteInetAddress, int remotePort, File file) {
		super();
		this.remoteInetAddress = remoteInetAddress;
		this.remotePort = remotePort;
		this.file = file;
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
	class PrograssbarProcess extends SwingWorker<Boolean,Long> {
		@Override
		protected Boolean doInBackground() throws Exception {
			try {
				Socket socket = new Socket(remoteInetAddress, remotePort);
				FileInputStream fiStream = new FileInputStream(file);
				OutputStream oStream = socket.getOutputStream();
				byte [] bytes = new byte[2048];
				int length = 0;
				long doLength = 0;
				while((length = fiStream.read(bytes))!=-1){
					synchronized (oStream) {
						oStream.write(bytes, 0, length);
						oStream.flush();
						doLength +=length;
						publish(doLength);
					}
				}
				fiStream.close();
				socket.close();
				return true;
			} catch (IOException e) {
				return false;
			}	
		}
		@Override
		protected void process(List<Long> chunks) {
			// TODO Auto-generated method stub
			//long nowProgress = chunks.get(chunks.size()-1);
			long part = file.length()/100;//表示一百份的数据，每一份的大小
			long nowProgress = chunks.get(chunks.size()-1);
			int nowPart =(int) (nowProgress/part);
			progressBar.setValue(nowPart);
		}
		@Override
		protected void done() {
			// TODO Auto-generated method stub
			JOptionPane.showMessageDialog(contentPane, "文件传输完成！");
		}
	}

	
}
