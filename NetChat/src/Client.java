import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.ListSelectionModel;

public class Client extends JFrame {
	private final int port = 9999;
	ObjectInputStream ois;
	ObjectOutputStream oos;
	private String localUserName;
	// �������û��б�ListModel��,����ά���������û��б�����ʾ������
	private final DefaultListModel<String> onlinUserDlm = new DefaultListModel<String>();
	// ���ڿ���ʱ����Ϣ��ʾ��ʽ
	// private final SimpleDateFormat dateFormat = new
	// SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	private File file;
	private final JPanel contentPane;
	private final JTextField textFieldUserName;
	private final JTextField textFieldMsgToSend;
	private final JTextPane textPaneMsgRecord;
	private final JList<String> listOnlineUsers;
	private final JButton btnLogon;
	private final JButton btnSendMsg;
	private final JButton btnSendFile;
	private JCheckBox chckbxPrivateChat;
	private File [] imageList;
	private Map<Image,String> fileMap;
	private ExpressionPacket exPacket;
	private int sendFilePort ;
	private ServerSocket myServerSocket ;
	public Client(String userName,ObjectInputStream ois, ObjectOutputStream oos) {
		try {
			sendFilePort=new Random().nextInt(25000)+19999;
			myServerSocket = new ServerSocket(sendFilePort,10,InetAddress.getByName("localhost"));
			myServerSocket.setReuseAddress(true);
		} catch (IOException e2) {
			System.out.println("���ļ�serversocket�쳣");
		}
		File dfile = new  File("image");
		imageList = dfile.listFiles();
		exPacket = new ExpressionPacket();
		exPacket.setVisible(false);
		this.ois = ois;
		this.oos = oos;
		localUserName = userName;
		setTitle("\u5BA2\u6237\u7AEF");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 612, 397);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panelNorth = new JPanel();
		panelNorth.setBorder(new EmptyBorder(0, 0, 5, 0));
		contentPane.add(panelNorth, BorderLayout.NORTH);
		panelNorth.setLayout(new BoxLayout(panelNorth, BoxLayout.X_AXIS));

		JLabel lblUserName = new JLabel("\u7528\u6237\u540D\uFF1A");
		panelNorth.add(lblUserName);

		textFieldUserName = new JTextField();
		textFieldUserName.setEditable(false);
		panelNorth.add(textFieldUserName);
		textFieldUserName.setColumns(10);
		textFieldUserName.setText(userName);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		panelNorth.add(horizontalStrut);

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		panelNorth.add(horizontalStrut_1);

		btnLogon = new JButton("\u6CE8\u9500"); // ����¼����ť
		btnLogon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 if (btnLogon.getText().equals("ע��")) {
					if (JOptionPane.showConfirmDialog(null, "�Ƿ��˳�?", "�˳�ȷ��",
							JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
						// ������������û�������Ϣ
						UserStateMessage userStateMessage = new UserStateMessage(
								localUserName, "", false);
						try {
							synchronized (oos) {
								oos.writeObject(userStateMessage);
								oos.flush();
							}
							System.exit(0);
						} catch (IOException e1) {
							System.err.println("�����û�������ϢʱIO�쳣");
						}
					}
				}

			}
		});
		panelNorth.add(btnLogon);

		JSplitPane splitPaneCenter = new JSplitPane();
		splitPaneCenter.setResizeWeight(1);
		splitPaneCenter.setDividerSize(10);
		contentPane.add(splitPaneCenter, BorderLayout.CENTER);

		JScrollPane scrollPaneMsgRecord = new JScrollPane();
		scrollPaneMsgRecord.setViewportBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "\u6D88\u606F\u8BB0\u5F55",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		splitPaneCenter.setLeftComponent(scrollPaneMsgRecord);

		textPaneMsgRecord = new JTextPane();
		textPaneMsgRecord.setEditable(false);
		scrollPaneMsgRecord.setViewportView(textPaneMsgRecord);

		JScrollPane scrollPaneOnlineUsers = new JScrollPane();
		scrollPaneOnlineUsers.setViewportBorder(new TitledBorder(null,
				"\u5728\u7EBF\u7528\u6237", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		splitPaneCenter.setRightComponent(scrollPaneOnlineUsers);

		listOnlineUsers = new JList<String>(onlinUserDlm);
		listOnlineUsers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPaneOnlineUsers.setViewportView(listOnlineUsers);

		JPanel panelSouth = new JPanel();
		panelSouth.setBorder(new EmptyBorder(5, 0, 0, 0));
		contentPane.add(panelSouth, BorderLayout.SOUTH);
		panelSouth.setLayout(new BoxLayout(panelSouth, BoxLayout.X_AXIS));

		textFieldMsgToSend = new JTextField();
		panelSouth.add(textFieldMsgToSend);
		textFieldMsgToSend.setColumns(10);

		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		panelSouth.add(horizontalStrut_2);

		btnSendMsg = new JButton("\u53D1\u9001\u6D88\u606F"); // ��������Ϣ����ť
		btnSendMsg.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String msgContent = textFieldMsgToSend.getText();
				if (msgContent.length() > 0  && !chckbxPrivateChat.isSelected()) {
					// ����Ϣ�ı����е�������Ϊ������Ϣ���͸�������
					ChatMessage chatMessage = new ChatMessage(localUserName,
							"", msgContent);
					try {
						synchronized (oos) {
							oos.writeObject(chatMessage);
							oos.flush();
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					// �ڡ���Ϣ��¼���ı���������ɫ��ʾ���͵���Ϣ������ʱ��
					String msgRecord = dateFormat.format(new Date()) + "����˵:"
							+ msgContent + "\r\n";
					addMsgRecord(msgRecord, Color.blue, 12, false, false);
				}else if (msgContent.length() > 0  && chckbxPrivateChat.isSelected()) {
					String dstUser = listOnlineUsers.getSelectedValue().trim();
					if (!dstUser.equals("")) {
						ChatMessage chatMessage = new ChatMessage(localUserName,
								dstUser, msgContent);
						try {
							synchronized (oos) {
								oos.writeObject(chatMessage);
								oos.flush();
							}
						} catch (IOException e1) {
							System.err.println("����˽����Ϣ����");
						}
						String msgPrivateRecord = dateFormat.format(new Date()) + "����"+
								dstUser+"˵��"+ msgContent + "\r\n";
						addMsgRecord(msgPrivateRecord, Color.blue, 12, false, false);
					}else {
						JOptionPane.showMessageDialog(null, "��ѡ��һ��˽�ĵĺ��ѣ�");
					}
				}
			}
		});
		
		chckbxPrivateChat = new JCheckBox("\u79C1\u804A");
		panelSouth.add(chckbxPrivateChat);
		
		Component horizontalStrut_5 = Box.createHorizontalStrut(20);
		panelSouth.add(horizontalStrut_5);
		
		JButton btnImage = new JButton("\u53D1\u9001\u8868\u60C5");
		btnImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exPacket.setVisible(true);
				exPacket.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			}
		});
		panelSouth.add(btnImage);
		
		Component horizontalStrut_4 = Box.createHorizontalStrut(20);
		panelSouth.add(horizontalStrut_4);
		panelSouth.add(btnSendMsg);

		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		panelSouth.add(horizontalStrut_3);

		btnSendFile = new JButton("\u53D1\u9001\u6587\u4EF6");
		btnSendFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxPrivateChat.isSelected() && !listOnlineUsers.isSelectionEmpty()) {
					String dstUser = listOnlineUsers.getSelectedValue().trim();
					JFileChooser fileChooser =new JFileChooser();
					fileChooser.showOpenDialog(null);
					file = fileChooser.getSelectedFile();
					FileSendMessage fMessage = new FileSendMessage(localUserName, dstUser, file.getName(), file.length());
					try {
						synchronized (oos) {
							oos.writeObject(fMessage);
							oos.flush();
						}
					} catch (IOException e1) {
						System.err.println("�ͻ��η����ļ�IO�쳣");
					}
				}else {
					JOptionPane.showMessageDialog(null, "��ѡ�����ļ��ĺ���");
				}
			}
		});
		panelSouth.add(btnSendFile);
		new Thread(new ListeningHandler()).start();
		//�����û�״̬��Ϣ
		UserStateMessage uStateMessage = new UserStateMessage(userName, "", true);
		try {
			synchronized (oos) {
				oos.writeObject(uStateMessage);
				oos.flush();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.err.println("�û���¼ʱ����״̬��Ϣʱio�쳣��");
		}
	}
	private void addImageRecord(final String msgRecord, Color msgColor,
			int fontSize, boolean isItalic, boolean isUnderline,ImageIcon icon) {
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
					textPaneMsgRecord.insertIcon(icon);
					docs.insertString(docs.getLength(), "\r\n", attrset);
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
			}
		});
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
	
	class ExpressionPacket extends JFrame {
		private JPanel contentPane;
		public ExpressionPacket() {
			this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			setResizable(false);
			File []files = new File("image").listFiles();
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 450, 300);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			GridBagLayout gbl_contentPane = new GridBagLayout();
			
			gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0};
			gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0};
			gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
			contentPane.setLayout(gbl_contentPane);
			fileMap =new HashMap<>();
			for (int i = 0; i < files.length; i++) {	
				JButton iconButton = new JButton();
				iconButton.setSize(25,25);
				ImageIcon icon = new ImageIcon(files[i].getPath());  
			    Image temp = icon.getImage().getScaledInstance(20,20, icon.getImage().SCALE_AREA_AVERAGING);  
			    fileMap.put(temp,files[i].getName());
			    icon = new ImageIcon(temp);  
			    iconButton.setIcon(icon);
				iconButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if (chckbxPrivateChat.isSelected()) {
							String dstUser = listOnlineUsers.getSelectedValue().trim();
							if (!dstUser.equals("")) {
								JButton jButton = (JButton) e.getSource();
								ImageIcon icon = (ImageIcon) (jButton.getIcon());
								String fileFlag = fileMap.get(icon.getImage());
								ExpressionMessage expMseeage = new ExpressionMessage(localUserName, dstUser, fileFlag);
								try {
									synchronized (oos) {
										oos.writeObject(expMseeage);
										oos.flush();
									}
								} catch (IOException e1) {
									System.err.println("����˽����Ϣ����");
								}
								String msgPrivateRecord = dateFormat.format(new Date()) + "����" + dstUser + "˵�� ";
								addImageRecord(msgPrivateRecord, Color.black, 12, false, false, icon);
							} else {
								JOptionPane.showMessageDialog(null, "��ѡ��һ��˽�ĵĺ��ѣ�");
							}
						} else {
							String dstUser = "";
							JButton jButton = (JButton) e.getSource();
							ImageIcon icon = (ImageIcon) (jButton.getIcon());
							String fileFlag = fileMap.get(icon.getImage());
							ExpressionMessage expMseeage = new ExpressionMessage(localUserName, dstUser, fileFlag);
							try {
								synchronized (oos) {
									oos.writeObject(expMseeage);
									oos.flush();
								}
							} catch (IOException e1) {
								System.err.println("���͹�����Ϣ����");
							}
							String msgPrivateRecord = dateFormat.format(new Date()) + "����" + dstUser + "���˵�� ";
							addImageRecord(msgPrivateRecord, Color.black, 12, false, false, icon);

						}
					}
				});

				GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
				gbc_btnNewButton.insets = new Insets(0, 0, 0, 0);
				gbc_btnNewButton.gridx = i%8;
				gbc_btnNewButton.gridy = i/8;
				contentPane.add(iconButton, gbc_btnNewButton);
			}
		}
		private void addImageRecord(final String msgRecord, Color msgColor,
				int fontSize, boolean isItalic, boolean isUnderline,ImageIcon icon) {
			final SimpleAttributeSet attrset = new SimpleAttributeSet();
			StyleConstants.setForeground(attrset, msgColor);
			StyleConstants.setFontSize(attrset, fontSize);
			StyleConstants.setUnderline(attrset, isUnderline);
			StyleConstants.setItalic(attrset, isItalic);
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public synchronized void run() {
					Document docs = textPaneMsgRecord.getDocument();
					try {
						docs.insertString(docs.getLength(), msgRecord, attrset);
						textPaneMsgRecord.insertIcon(icon);
						docs.insertString(docs.getLength(), "\r\n", attrset);
					} catch (BadLocationException e) {
						e.printStackTrace();
					}
				}
			});
		}
	}
	// ��̨�����߳�
	class ListeningHandler implements Runnable {
		@Override
		public void run() {
			try {
				while (true) {
					Message msg =  (Message) ois.readObject();
					if (msg instanceof UserStateMessage) {
						// �����û�״̬��Ϣ
						processUserStateMessage((UserStateMessage) msg);
					} else if (msg instanceof ChatMessage) {
						// ����������Ϣ
						processChatMessage((ChatMessage) msg);
					}  else if(msg instanceof FileSendMessage){
						//�����ļ�������Ϣ
						processFileSendMessage((FileSendMessage)msg);
					} else if(msg instanceof FileResponseMessage){
						//�����ļ���Ӧ
						processFileResponseMessage((FileResponseMessage)msg);
					}else if(msg instanceof ExpressionMessage){
						//�����ļ���Ӧ
						processExpressionMessage((ExpressionMessage)msg);
					}else {
						// ���������Ӧ���û���������Ϣ��ʽ ����Ӧ�÷���Ϣ��ʾ�û����������
						System.err.println("�û���������Ϣ��ʽ����!");
					}
				}
			} catch (IOException e) {
				if (e.toString().endsWith("Connection reset")) {
					System.out.println("���������˳�");
				} else {
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		private void processExpressionMessage(ExpressionMessage msg) {
			String imageName = msg.getExpression();
			for (File file : imageList) {
				if (file.getName().equals(imageName)) {
					ImageIcon icon = new ImageIcon(file.getPath());
					Image temp = icon.getImage().getScaledInstance(20, 20, icon.getImage().SCALE_AREA_AVERAGING);
					icon = new ImageIcon(temp);
					if (!msg.getDstUser().equals("")) {
						String msgPrivateRecord = dateFormat.format(new Date()) + msg.getDstUser() + "����˵��";
						addImageRecord(msgPrivateRecord, Color.black, 12, false, false, icon);
						break;
					} else {
						String msgPrivateRecord = dateFormat.format(new Date()) + msg.getDstUser() + "����˵��";
						addImageRecord(msgPrivateRecord, Color.black, 12, false, false, icon);
						break;
					}
				}
			}
		}
		private void processFileResponseMessage(FileResponseMessage  msg) {
			if (msg.isIfAgree()) {
				FileSendHandler fHandler = new FileSendHandler(msg.getInetAddress(), msg.getPort(), file);
				FileSendHandler.PrograssbarProcess process = fHandler.new PrograssbarProcess();
				process.execute();
			}else {
				JOptionPane.showMessageDialog(null, msg.getSrcUser()+"�ܾ������ļ�");
			}			
		}
		private synchronized void processFileSendMessage(FileSendMessage msg) {
			if (JOptionPane.showConfirmDialog(null,
					"�Ƿ���ܺ���"+msg.getSrcUser()+"�������ļ���СΪ"+msg.getFileSize()+"���ļ�"+msg.getFileName(),
					"�ļ���������",JOptionPane.YES_NO_OPTION ) == JOptionPane.YES_OPTION) {
				// �����Ӧ����ͻ��˷��ͽ����ļ���Ӧ��Ϣ���ҿ����ļ������߳�
				try {
					InetAddress inetAddress = InetAddress.getByName("localhost");
					synchronized (oos) {
						try {
							JFileChooser jFileChooser = new JFileChooser();
							jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
							jFileChooser.showOpenDialog(null);
							String filePathAndName = jFileChooser.getSelectedFile().getPath()+"\\"+msg.getFileName(); 
							oos.writeObject(new FileResponseMessage(msg.getDstUser(), msg.getSrcUser(), true,
									inetAddress,sendFilePort));
							oos.flush();
							Socket mysocket = myServerSocket.accept();
							FileRecieveHandler fRecieveHandler = new FileRecieveHandler(mysocket.getInputStream(),filePathAndName,msg.getFileSize());	
							FileRecieveHandler.PrograssRecieveHandler process1 = fRecieveHandler.new PrograssRecieveHandler();
							process1.execute();
						} catch (IOException e) {
							System.err.println("�ͻ��˷����ļ���Ӧ��Ϣʱ�쳣");
						}
					}
				} catch (UnknownHostException e) {
					System.err.println("�����ļ����յ�ַ��ʱ��δ��������");
				}	
			}else {
				synchronized (oos) {
					try {
						oos.writeObject(new FileResponseMessage(msg.getDstUser(), msg.getSrcUser(), false,null, 0));
						oos.flush();
					} catch (IOException e) {
						System.err.println("�ͻ��˷����ļ���Ӧ��Ϣʱ�쳣");
					}
				}
			}
		}
		// �����û�״̬��Ϣ
		private void processUserStateMessage(UserStateMessage msg) {
			String srcUser = msg.getSrcUser();
			String dstUser = msg.getDstUser();
			if (msg.isUserOnline()) {
				if (msg.isPubUserStateMessage()) { // ���û�������Ϣ
					// ����ɫ���ֽ��û������û�����ʱ����ӵ�����Ϣ��¼���ı�����
					final String msgRecord = dateFormat.format(new Date())
							+ " " + srcUser + "������!\r\n";
					addMsgRecord(msgRecord, Color.green, 12, false, false);
					// �ڡ������û����б������������ߵ��û���
					onlinUserDlm.addElement(srcUser);
				}
				if (dstUser.equals(localUserName)) { // �û�������Ϣ
					onlinUserDlm.addElement(srcUser);
				}
			} else if (msg.isUserOffline()) { // �û�������Ϣ
				if (onlinUserDlm.contains(srcUser)) {
					// ����ɫ���ֽ��û������û�����ʱ����ӵ�����Ϣ��¼���ı�����
					final String msgRecord = dateFormat.format(new Date())
							+ " " + srcUser + "������!\r\n";
					addMsgRecord(msgRecord, Color.green, 12, false, false);
					// �ڡ������û����б���ɾ�����ߵ��û���
					onlinUserDlm.removeElement(srcUser);
				}
			}
		}
		// ���������ת�����Ĺ�����Ϣ
		private void processChatMessage(ChatMessage msg) {
			String srcUser = msg.getSrcUser();
			String dstUser = msg.getDstUser();
			String msgContent = msg.getMsgContent();
			if (onlinUserDlm.contains(srcUser)) {
				if (msg.isPubChatMessage()) {
					// �ú�ɫ���ֽ��յ���Ϣ��ʱ�䡢������Ϣ���û�������Ϣ������ӵ�����Ϣ��¼���ı�����
					final String msgRecord = dateFormat.format(new Date())
							+ " " + srcUser + "˵: " + msgContent + "\r\n";
					addMsgRecord(msgRecord, Color.black, 12, false, false);
				}else {
					final String msgPrivateRecord = dateFormat.format(new Date())
							+ srcUser +"����˵: " + msgContent + "\r\n";
					addMsgRecord(msgPrivateRecord, Color.black, 12, false, false);
				}
			}
		}
	}

	
}
