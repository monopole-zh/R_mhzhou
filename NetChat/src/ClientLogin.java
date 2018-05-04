import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;

public class ClientLogin extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldUserName;
	private JPasswordField userPasswd;
	protected LoginMessage loginMessage;
	protected int port = 9999;
	protected SSLSocket socket;
	protected ObjectOutputStream oos;
	protected ObjectInputStream ois;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientLogin frame = new ClientLogin();
					frame.setVisible(true);
					frame.setTitle("QQ聊天");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ClientLogin() {
		try {
			String passphrase = "123456";
			char[] password = passphrase.toCharArray();
			String trustStoreFile = "test.keys";
			KeyStore ts = KeyStore.getInstance("JKS");
			ts.load(new FileInputStream(trustStoreFile), password);
			TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
			tmf.init(ts);
			SSLContext sslContext = SSLContext.getInstance("SSL");
			sslContext.init(null, tmf.getTrustManagers(), null);
			SSLSocketFactory factory = sslContext.getSocketFactory();
			socket = (SSLSocket) factory.createSocket("localhost", port);
			String[] supportedSuites = socket.getSupportedCipherSuites();
			socket.setEnabledCipherSuites(supportedSuites);
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
		} catch (KeyManagementException | KeyStoreException | NoSuchAlgorithmException | CertificateException
				| IOException e2) {
			// TODO Auto-generated catch block
			System.err.println("socket建立异常");
		}
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel labelLogin = new JLabel("\u6B22\u8FCE\u767B\u5F55");
		labelLogin.setHorizontalAlignment(SwingConstants.CENTER);
		labelLogin.setForeground(Color.BLACK);
		labelLogin.setFont(new Font("宋体", Font.PLAIN, 43));
		labelLogin.setBackground(Color.WHITE);
		contentPane.add(labelLogin, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblLoginUserName = new JLabel("\u7528\u6237\u540D");
		lblLoginUserName.setBounds(109, 39, 54, 15);
		panel.add(lblLoginUserName);
		
		JLabel labelLoginPasswd = new JLabel("\u5BC6\u7801");
		labelLoginPasswd.setBounds(109, 82, 54, 15);
		panel.add(labelLoginPasswd);
		
		textFieldUserName = new JTextField();
		textFieldUserName.setBounds(173, 36, 130, 21);
		panel.add(textFieldUserName);
		textFieldUserName.setColumns(10);
		
		userPasswd = new JPasswordField();
		userPasswd.setBounds(173, 79, 130, 21);
		panel.add(userPasswd);
		
		JButton buttonLogin = new JButton("\u767B\u5F55");
		
		buttonLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String userName = textFieldUserName.getText().trim();
					// 创建登录消息
					loginMessage = new LoginMessage(userName, new String(userPasswd.getPassword()).trim());
					synchronized (oos) {
						oos.writeObject(loginMessage);
						oos.flush();
					}
					LoginMessage loginMessageRecieve = (LoginMessage) ois.readObject();
					if (!loginMessageRecieve.isUserExist()) {
						JOptionPane.showMessageDialog(null, "用户不存在...");
					} else if (!loginMessageRecieve.isCorrectPwd()) {
						JOptionPane.showMessageDialog(null, "密码错误...");
					} else {
						Client client = new Client(userName,ois,oos);
						client.setVisible(true);
						ClientLogin.this.setVisible(false);					
					}
				}  catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					System.out.println("io异常");
				}
			}
		});
		buttonLogin.setBounds(91, 146, 66, 23);
		panel.add(buttonLogin);
		
		JButton buttonRegister = new JButton("\u6CE8\u518C");
		buttonRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClientRegister cRegister = new ClientRegister(ois, oos);
				cRegister.setVisible(true);
			}
		});
		buttonRegister.setBounds(273, 146, 66, 23);
		panel.add(buttonRegister);
	}
}
