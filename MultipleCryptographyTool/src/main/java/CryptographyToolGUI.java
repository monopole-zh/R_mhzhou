import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;

import org.jfree.chart.ChartPanel;

public class CryptographyToolGUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldFilePath;
	private JTextField textFieldInputString;
	private JTextField textFieldMD5;
	private JTextField textFieldSHA_1;
	private JTextField textFieldSHA_224;
	private JTextField textFieldSHA_256;
	private JTextField textFieldSHA_384;
	private JTextField textFieldSHA_512;
	private JTextField textFieldSHA3_224;
	private JTextField textFieldSHA3_256;
	private JTextField textFieldSHA3_384;
	private JTextField textFieldMessage;
	private JTextField textFieldHashValue;
	private JTextField textFieldVertify;
	private JTextField textFieldSHA3_512;
	private JPasswordField pwdPassword1;
	private JTextField textFieldDESEncryptedFilePath;
	private JTextField textFieldDESEncryptedFileToPath;
	private JPasswordField pwdPassword2;
	private JTextField textFieldDESFileDecryptedPath;
	private JTextField textFieldDESDecryptedToFilePath;
	private JLabel labelTipInputDESPassword;
	private JPasswordField pwdDESPassword;
	private JPasswordField pwdAESEncryptedPassword1;
	private JTextField textFieldAESEncryptedFilePath;
	private JTextField textFieldAESEncryptedToFilePath;
	private JPasswordField pwdAESEncryptedPassword2;
	private JRadioButton rdbtnAESEncrypted128;
	private JRadioButton rdbtnAESEncrypted192;
	private JRadioButton rdbtnAESEncrypted256;
	private JTextField textFieldAESDecryptedFilePath;
	private JTextField textFieldAESDecryptedToFilePath;
	private JPasswordField pwdAESDecryptedPassword;
	private JButton btnAESDecrypted;
	private JRadioButton rdbtnAESDecrypted128;
	private JRadioButton rdbtnAESDecrypted192;
	private JRadioButton rdbtnAESDecrypted256;
	private JTextField textFieldDSASignatureFilePath;
	private JTextField textFieldDSASignatureValueFilePath;
	private JRadioButton rdbtnSHA224WithDSASignature;
	private JRadioButton rdbtnSHA256WithDSASignature;
	private JRadioButton rdbtnSHA384WithDSASignature;
	private JRadioButton rdbtnSHA512WithDSASignature;
	private JTextField textFieldDSAVertifyFilePath;
	private JTextField textFieldDSAVertifySignValueFilePath;
	private JRadioButton rdbtnDSAVertifySHA224WithDSA;
	private JRadioButton rdbtnDSAVertifySHA256WithDSA;
	private JRadioButton rdbtnDSAVertifySHA384WithDSA;
	private JRadioButton rdbtnDSAVertifySHA512WithDSA;
	private JTextField textFieldHMACSHA1String;
	private JTextField textFieldHMACSHA1FilePath;
	private JPasswordField pwdHMACSHA1Password1;
	private JPasswordField pwdHMACSHA1Password2;
	private JButton btnHMACSHA1FilePath;
	private JRadioButton rdbtnHMACSHA1StringMAC;
	private JRadioButton rdbtnHMACSHA1FileMAC;
	private JTextField textFieldHMACSHA1Result;
	private JTextField textFieldHMACSHA1ToFilePath;
	private JTextField textFieldHMACSHA224String;
	private JTextField textFieldHMACSHA224FilePath;
	private JPasswordField pwdHMACSHA224Password1;
	private JPasswordField pwdHMACSHA224Password2;
	private JTextField textFieldHMACSHA224Result;
	private JTextField textFieldHMACSHA224ToFilePath;
	private JRadioButton rdbtnHMACSHA224StringMAC;
	private JRadioButton rdbtnHMACSHA224FileMAC;
	private JButton btnHMACSHA224FilePath;
	private JTextField textFieldHMACSHA256String;
	private JTextField textFieldHMACSHA256FilePath;
	private JPasswordField pwdHMACSHA256Password1;
	private JPasswordField pwdHMACSHA256Password2;
	private JTextField textFieldHMACSHA256Result;
	private JTextField textFieldHMACSHA256ToFilePath;
	private JRadioButton rdbtnHMACSHA256StringMAC;
	private JButton btnHMACSHA256FilePath;
	private JRadioButton rdbtnHMACSHA256FileMAC;
	private JTextField textFieldHMACSHA384String;
	private JTextField textFieldHMACSHA384FilePath;
	private JPasswordField pwdHMACSHA384Password1;
	private JPasswordField pwdHMACSHA384Password2;
	private JTextField textFieldHMACSHA384Result;
	private JTextField textFieldHMACSHA384ToFilePath;
	private JRadioButton rdbtnHMACSHA384StringMAC;
	private JRadioButton rdbtnHMACSHA384FileMAC;
	private JButton btnHMACSHA384FilePath;
	private JTextField textFieldHMACSHA512String;
	private JTextField textFieldHMACSHA512FilePath;
	private JPasswordField pwdHMACSHA512Password1;
	private JPasswordField pwdHMACSHA512Password2;
	private JTextField textFieldHMACSHA512Result;
	private JTextField textFieldHMACSHA512ToFilePath;
	private JRadioButton rdbtnHMACSHA512StringMAC;
	private JRadioButton rdbtnHMACSHA512FileMAC;
	private JButton btnHMACSHA512FilePath;
	private JTextField textFieldMACVertifyFilePath;
	private JTextField textFieldMACVertifyMACFilePath;
	private JPasswordField pwdMACVertifyPassword;
	private JTextField textFieldMACVertifyMACResult;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CryptographyToolGUI frame = new CryptographyToolGUI();
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
	public CryptographyToolGUI() {
		setTitle("CryptographyTool");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 798, 680);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		mnFile.setHorizontalAlignment(SwingConstants.LEFT);
		menuBar.add(mnFile);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mntmExit.setHorizontalAlignment(SwingConstants.LEFT);
		mnFile.add(mntmExit);

		JLookAndFeelMenu mnWindow = new JLookAndFeelMenu("Window", this);
		menuBar.add(mnWindow);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem mntmWelcome = new JMenuItem("Welcome");
		mntmWelcome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"    Welcome to Zhou Minghua's cryptography tools."
								+ "\n If you have any questions,please send them to" + "\n182322799712@163.com. ",
						"Welcome", JOptionPane.PLAIN_MESSAGE);
			}
		});
		mnHelp.add(mntmWelcome);

		JMenuItem mntmAboutProgrammer = new JMenuItem("AboutProgrammer");
		mntmAboutProgrammer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"            The program has four parts, ten classic hash algorithms,MD5,"
								+ "\nSHA1,SHA224,SHA256,SHA384,SHA512,SHA3-224,SHA3-256,SHA3-384,SHA3-512."
								+ "\nsymmetric encryption algorithms, AES128, AES192, AES256.Digital signature"
								+ "\nand authentication of DSA.Message authentication algorithm MAC,HmacSHA1,"
								+ "\nHmacSHA224,HmacSHA256,HmacSHA384,HmacSHA512.",
						"About Programmer", JOptionPane.PLAIN_MESSAGE);
			}
		});
		mnHelp.add(mntmAboutProgrammer);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(5, 5, 777, 581);
		contentPane.add(tabbedPane);

		JTabbedPane tabbedPaneHashDigest = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("Hash", null, tabbedPaneHashDigest, null);

		JPanel panelHashDigest = new JPanel();
		tabbedPaneHashDigest.addTab("HashDigest", null, panelHashDigest, null);

		JButton btnBrowse = new JButton("Browse");
		btnBrowse.setBounds(624, 74, 80, 23);
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser("d:");
				if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					textFieldFilePath.setText(fileChooser.getSelectedFile().getPath());
				}
			}
		});
		panelHashDigest.setLayout(null);
		panelHashDigest.add(btnBrowse);

		JRadioButton rdbtnStringDigest = new JRadioButton("StringDigest");
		rdbtnStringDigest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnStringDigest.isSelected()) {
					btnBrowse.setEnabled(false);
				}
			}
		});
		rdbtnStringDigest.setBounds(144, 31, 112, 23);
		panelHashDigest.add(rdbtnStringDigest);

		JRadioButton rdbtnFileDigest = new JRadioButton("FileDigest");
		rdbtnFileDigest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnFileDigest.isSelected()) {
					btnBrowse.setEnabled(true);
				}
			}
		});
		rdbtnFileDigest.setBounds(144, 74, 106, 23);
		ButtonGroup btnGroup = new ButtonGroup();
		btnGroup.add(rdbtnFileDigest);
		btnGroup.add(rdbtnStringDigest);

		panelHashDigest.add(rdbtnFileDigest);

		textFieldFilePath = new JTextField();
		textFieldFilePath.setBounds(262, 75, 302, 21);
		panelHashDigest.add(textFieldFilePath);
		textFieldFilePath.setColumns(10);

		textFieldInputString = new JTextField();
		textFieldInputString.setBounds(262, 32, 300, 21);
		panelHashDigest.add(textFieldInputString);
		textFieldInputString.setColumns(10);

		JCheckBox chckbxMD5 = new JCheckBox("MD5");
		chckbxMD5.setSelected(true);
		chckbxMD5.setFont(new Font("宋体", Font.PLAIN, 12));
		chckbxMD5.setBounds(63, 127, 50, 23);
		panelHashDigest.add(chckbxMD5);

		textFieldMD5 = new JTextField();
		textFieldMD5.setEditable(false);
		textFieldMD5.setBounds(164, 129, 522, 21);
		panelHashDigest.add(textFieldMD5);
		textFieldMD5.setColumns(10);

		JCheckBox chckbxSHA_1 = new JCheckBox("SHA-1");
		chckbxSHA_1.setBounds(63, 160, 81, 23);
		panelHashDigest.add(chckbxSHA_1);

		textFieldSHA_1 = new JTextField();
		textFieldSHA_1.setEditable(false);
		textFieldSHA_1.setColumns(10);
		textFieldSHA_1.setBounds(164, 162, 522, 21);
		panelHashDigest.add(textFieldSHA_1);

		JCheckBox chckbxSHA_224 = new JCheckBox("SHA-224");
		chckbxSHA_224.setBounds(63, 191, 81, 23);
		panelHashDigest.add(chckbxSHA_224);

		textFieldSHA_224 = new JTextField();
		textFieldSHA_224.setEditable(false);
		textFieldSHA_224.setColumns(10);
		textFieldSHA_224.setBounds(164, 193, 522, 21);
		panelHashDigest.add(textFieldSHA_224);

		JCheckBox chckbxSHA_256 = new JCheckBox("SHA-256");
		chckbxSHA_256.setBounds(63, 222, 81, 23);
		panelHashDigest.add(chckbxSHA_256);

		textFieldSHA_256 = new JTextField();
		textFieldSHA_256.setEditable(false);
		textFieldSHA_256.setColumns(10);
		textFieldSHA_256.setBounds(164, 224, 522, 21);
		panelHashDigest.add(textFieldSHA_256);

		JCheckBox chckbxSHA_384 = new JCheckBox("SHA-384");
		chckbxSHA_384.setBounds(63, 253, 81, 23);
		panelHashDigest.add(chckbxSHA_384);

		textFieldSHA_384 = new JTextField();
		textFieldSHA_384.setEditable(false);
		textFieldSHA_384.setColumns(10);
		textFieldSHA_384.setBounds(164, 255, 522, 21);
		panelHashDigest.add(textFieldSHA_384);

		textFieldSHA_512 = new JTextField();
		textFieldSHA_512.setEditable(false);
		textFieldSHA_512.setColumns(10);
		textFieldSHA_512.setBounds(164, 286, 522, 21);
		panelHashDigest.add(textFieldSHA_512);

		JCheckBox chckbxSHA_512 = new JCheckBox("SHA-512");
		chckbxSHA_512.setBounds(63, 284, 81, 23);
		panelHashDigest.add(chckbxSHA_512);

		JCheckBox chckbxSHA3_224 = new JCheckBox("SHA3-224");
		chckbxSHA3_224.setBounds(63, 315, 81, 23);
		panelHashDigest.add(chckbxSHA3_224);

		textFieldSHA3_224 = new JTextField();
		textFieldSHA3_224.setEditable(false);
		textFieldSHA3_224.setColumns(10);
		textFieldSHA3_224.setBounds(164, 317, 522, 21);
		panelHashDigest.add(textFieldSHA3_224);

		JCheckBox chckbxSHA3_256 = new JCheckBox("SHA3-256");
		chckbxSHA3_256.setBounds(63, 346, 81, 23);
		panelHashDigest.add(chckbxSHA3_256);

		textFieldSHA3_256 = new JTextField();
		textFieldSHA3_256.setEditable(false);
		textFieldSHA3_256.setColumns(10);
		textFieldSHA3_256.setBounds(164, 348, 522, 21);
		panelHashDigest.add(textFieldSHA3_256);

		JCheckBox chckbxSHA3_384 = new JCheckBox("SHA3-384");
		chckbxSHA3_384.setBounds(63, 376, 81, 23);
		panelHashDigest.add(chckbxSHA3_384);

		textFieldSHA3_384 = new JTextField();
		textFieldSHA3_384.setEditable(false);
		textFieldSHA3_384.setColumns(10);
		textFieldSHA3_384.setBounds(164, 378, 522, 21);
		panelHashDigest.add(textFieldSHA3_384);

		JCheckBox chckbxSHA3_512 = new JCheckBox("SHA3-512");
		chckbxSHA3_512.setBounds(63, 407, 81, 23);
		panelHashDigest.add(chckbxSHA3_512);

		textFieldSHA3_512 = new JTextField();
		textFieldSHA3_512.setEditable(false);
		textFieldSHA3_512.setColumns(10);
		textFieldSHA3_512.setBounds(164, 409, 522, 21);
		panelHashDigest.add(textFieldSHA3_512);

		JButton btnComputer = new JButton("Computer");
		btnComputer.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (rdbtnStringDigest.isSelected()) {
					byte[] message = null;
					message = textFieldInputString.getText().getBytes();
					if (chckbxMD5.isSelected()) {
						HashDigest.MD5MessageDigest(message, textFieldMD5);
					}
					if (chckbxSHA_1.isSelected()) {
						HashDigest.SHA_1MessageDigest(message, textFieldSHA_1);
					}
					if (chckbxSHA_224.isSelected()) {
						HashDigest.SHA_224MessageDigest(message, textFieldSHA_224);
					}
					if (chckbxSHA_256.isSelected()) {
						HashDigest.SHA_256MessageDigest(message, textFieldSHA_256);
					}
					if (chckbxSHA_384.isSelected()) {
						HashDigest.SHA_384MessageDigest(message, textFieldSHA_384);
					}
					if (chckbxSHA_512.isSelected()) {
						HashDigest.SHA_512MessageDigest(message, textFieldSHA_512);
					}
					if (chckbxSHA3_224.isSelected()) {
						HashDigest.SHA3_224MessageDigest(message, textFieldSHA3_224);
					}
					if (chckbxSHA3_256.isSelected()) {
						HashDigest.SHA3_256MessageDigest(message, textFieldSHA3_256);
					}
					if (chckbxSHA3_384.isSelected()) {
						HashDigest.SHA3_384MessageDigest(message, textFieldSHA3_384);
					}
					if (chckbxSHA3_512.isSelected()) {
						HashDigest.SHA3_512MessageDigest(message, textFieldSHA3_512);
					}
				}
				if (rdbtnFileDigest.isSelected()) {
					if (chckbxMD5.isSelected()) {
						HashDigest.MD5FileDigest(textFieldFilePath.getText(), textFieldMD5);
					}
					if (chckbxSHA_1.isSelected()) {
						HashDigest.SHA_1FileDigest(textFieldFilePath.getText(), textFieldSHA_1);
					}
					if (chckbxSHA_224.isSelected()) {
						HashDigest.SHA_224FileDigest(textFieldFilePath.getText(), textFieldSHA_224);
					}
					if (chckbxSHA_256.isSelected()) {
						HashDigest.SHA_256FileDigest(textFieldFilePath.getText(), textFieldSHA_256);
					}
					if (chckbxSHA_384.isSelected()) {
						HashDigest.SHA_384FileDigest(textFieldFilePath.getText(), textFieldSHA_384);
					}
					if (chckbxSHA_512.isSelected()) {
						HashDigest.SHA_512FileDigest(textFieldFilePath.getText(), textFieldSHA_512);
					}
					if (chckbxSHA3_224.isSelected()) {
						HashDigest.SHA3_224FileDigest(textFieldFilePath.getText(), textFieldSHA3_224);
					}
					if (chckbxSHA3_256.isSelected()) {
						HashDigest.SHA3_256FileDigest(textFieldFilePath.getText(), textFieldSHA3_256);
					}
					if (chckbxSHA3_384.isSelected()) {
						HashDigest.SHA3_384FileDigest(textFieldFilePath.getText(), textFieldSHA3_384);
					}
					if (chckbxSHA3_512.isSelected()) {
						HashDigest.SHA3_512FileDigest(textFieldFilePath.getText(), textFieldSHA3_512);
					}
				}
			}
		});
		btnComputer.setBounds(164, 469, 93, 23);
		panelHashDigest.add(btnComputer);

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldInputString.setText(null);
				textFieldFilePath.setText(null);
				textFieldMD5.setText(null);
				textFieldSHA_1.setText(null);
				textFieldSHA_224.setText(null);
				textFieldSHA_256.setText(null);
				textFieldSHA_384.setText(null);
				textFieldSHA_512.setText(null);
				textFieldSHA3_224.setText(null);
				textFieldSHA3_256.setText(null);
				textFieldSHA3_384.setText(null);
				textFieldSHA3_512.setText(null);
			}
		});
		btnClear.setBounds(593, 469, 93, 23);
		panelHashDigest.add(btnClear);

		JPanel panelHashVertify = new JPanel();
		tabbedPaneHashDigest.addTab("HashVertify", null, panelHashVertify, null);
		panelHashVertify.setLayout(null);

		JLabel lblMessage = new JLabel("Message");
		lblMessage.setBounds(126, 236, 63, 20);
		panelHashVertify.add(lblMessage);

		textFieldMessage = new JTextField();
		textFieldMessage.setBounds(199, 236, 342, 21);
		panelHashVertify.add(textFieldMessage);
		textFieldMessage.setColumns(10);

		JLabel labelHashValue = new JLabel("HashValue");
		labelHashValue.setBounds(126, 314, 75, 20);
		panelHashVertify.add(labelHashValue);

		textFieldHashValue = new JTextField();
		textFieldHashValue.setColumns(10);
		textFieldHashValue.setBounds(199, 314, 342, 21);
		panelHashVertify.add(textFieldHashValue);

		JRadioButton rdbtnMD5 = new JRadioButton("MD5");
		rdbtnMD5.setSelected(true);
		rdbtnMD5.setBounds(56, 55, 61, 23);
		panelHashVertify.add(rdbtnMD5);

		JRadioButton rdbtnSHA_1 = new JRadioButton("SHA-1");
		rdbtnSHA_1.setBounds(206, 55, 67, 23);
		panelHashVertify.add(rdbtnSHA_1);

		JRadioButton rdbtnSHA_224 = new JRadioButton("SHA-224");
		rdbtnSHA_224.setBounds(340, 55, 84, 23);
		panelHashVertify.add(rdbtnSHA_224);

		JRadioButton rdbtnSHA_256 = new JRadioButton("SHA-256");
		rdbtnSHA_256.setBounds(481, 55, 93, 23);
		panelHashVertify.add(rdbtnSHA_256);

		JRadioButton rdbtnSHA_384 = new JRadioButton("SHA-384");
		rdbtnSHA_384.setBounds(642, 55, 93, 23);
		panelHashVertify.add(rdbtnSHA_384);

		JRadioButton rdbtnSHA_512 = new JRadioButton("SHA-512");
		rdbtnSHA_512.setBounds(57, 111, 78, 23);
		panelHashVertify.add(rdbtnSHA_512);

		JRadioButton rdbtnSHA3_224 = new JRadioButton("SHA3-224");
		rdbtnSHA3_224.setBounds(206, 111, 88, 23);
		panelHashVertify.add(rdbtnSHA3_224);

		JRadioButton rdbtnSHA3_256 = new JRadioButton("SHA3-256");
		rdbtnSHA3_256.setBounds(340, 111, 84, 23);
		panelHashVertify.add(rdbtnSHA3_256);

		JRadioButton rdbtnSHA3_384 = new JRadioButton("SHA3-384");
		rdbtnSHA3_384.setBounds(481, 111, 93, 23);
		panelHashVertify.add(rdbtnSHA3_384);

		JRadioButton rdbtnSHA3_512 = new JRadioButton("SHA3-512");
		rdbtnSHA3_512.setBounds(642, 111, 93, 23);
		panelHashVertify.add(rdbtnSHA3_512);

		ButtonGroup rdButtonGroup = new ButtonGroup();
		rdButtonGroup.add(rdbtnMD5);
		rdButtonGroup.add(rdbtnSHA_1);
		rdButtonGroup.add(rdbtnSHA_224);
		rdButtonGroup.add(rdbtnSHA_256);
		rdButtonGroup.add(rdbtnSHA_384);
		rdButtonGroup.add(rdbtnSHA_512);
		rdButtonGroup.add(rdbtnSHA3_224);
		rdButtonGroup.add(rdbtnSHA3_256);
		rdButtonGroup.add(rdbtnSHA3_384);
		rdButtonGroup.add(rdbtnSHA3_512);

		JButton btnVertify = new JButton("Vertify");
		btnVertify.setBounds(96, 425, 93, 23);
		panelHashVertify.add(btnVertify);
		btnVertify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField jTextField = new JTextField();

				if (rdbtnMD5.isSelected()) {
					jTextField.setText(null);
					HashDigest.MD5MessageDigest(textFieldMessage.getText().getBytes(), jTextField);
				} else if (rdbtnSHA_1.isSelected()) {
					jTextField.setText(null);
					HashDigest.SHA_1MessageDigest(textFieldMessage.getText().getBytes(), jTextField);
				} else if (rdbtnSHA_224.isSelected()) {
					jTextField.setText(null);
					HashDigest.SHA_224MessageDigest(textFieldMessage.getText().getBytes(), jTextField);
				} else if (rdbtnSHA_256.isSelected()) {
					jTextField.setText(null);
					HashDigest.SHA_256MessageDigest(textFieldMessage.getText().getBytes(), jTextField);
				} else if (rdbtnSHA_384.isSelected()) {
					jTextField.setText(null);
					HashDigest.SHA_384MessageDigest(textFieldMessage.getText().getBytes(), jTextField);
				} else if (rdbtnSHA_512.isSelected()) {
					jTextField.setText(null);
					HashDigest.SHA_512MessageDigest(textFieldMessage.getText().getBytes(), jTextField);
				} else if (rdbtnSHA3_224.isSelected()) {
					jTextField.setText(null);
					HashDigest.SHA3_224MessageDigest(textFieldMessage.getText().getBytes(), jTextField);
				} else if (rdbtnSHA3_256.isSelected()) {
					jTextField.setText(null);
					HashDigest.SHA3_256MessageDigest(textFieldMessage.getText().getBytes(), jTextField);
				} else if (rdbtnSHA3_384.isSelected()) {
					jTextField.setText(null);
					HashDigest.SHA3_384MessageDigest(textFieldMessage.getText().getBytes(), jTextField);
				} else if (rdbtnSHA3_512.isSelected()) {
					HashDigest.SHA3_512MessageDigest(textFieldMessage.getText().getBytes(), jTextField);
				} else {
				}
				if (jTextField.getText().equals(textFieldHashValue.getText())) {
					textFieldVertify.setText("Vertify Sucessfully!");
				} else {
					textFieldVertify.setText("Vertify Failure!");
				}
			}
		});

		JLabel lblMessagevertify = new JLabel("MessageVertify:");
		lblMessagevertify.setBounds(23, 178, 98, 15);
		panelHashVertify.add(lblMessagevertify);

		textFieldVertify = new JTextField();
		textFieldVertify.setEditable(false);
		textFieldVertify.setBounds(199, 426, 151, 21);
		panelHashVertify.add(textFieldVertify);
		textFieldVertify.setColumns(10);

		JButton btnClear_1 = new JButton("Clear");
		btnClear_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdButtonGroup.clearSelection();
				textFieldMessage.setText(null);
				textFieldHashValue.setText(null);
			}
		});
		btnClear_1.setBounds(609, 425, 93, 23);
		panelHashVertify.add(btnClear_1);

		JLabel lblHashVertifyTip1 = new JLabel("Please choose hash type:");
		lblHashVertifyTip1.setBounds(23, 17, 180, 15);
		panelHashVertify.add(lblHashVertifyTip1);

		JPanel panelHashAnalysis = new JPanel();
		tabbedPaneHashDigest.addTab("HashAnalysis", null, panelHashAnalysis, null);
		panelHashAnalysis.setLayout(new BorderLayout(0, 0));

		JPanel panelHashAnalysisInner = new JPanel();
		panelHashAnalysis.add(panelHashAnalysisInner, BorderLayout.SOUTH);
		panelHashAnalysisInner.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnStartAnalysisHash = new JButton("StartAnalysis");
		btnStartAnalysisHash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChartHash chartHash = new ChartHash();
				ChartPanel chartPanelHash = chartHash.makeAChartHash();
				panelHashAnalysis.add(chartPanelHash, BorderLayout.CENTER);
			}
		});

		panelHashAnalysisInner.add(btnStartAnalysisHash);
		JTabbedPane tabbedPaneSymmetricEncryption = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("SymmetricEncryption", null, tabbedPaneSymmetricEncryption, null);

		JPanel panelDESEncrypted = new JPanel();
		tabbedPaneSymmetricEncryption.addTab("DES/Encrypted", null, panelDESEncrypted, null);
		panelDESEncrypted.setLayout(null);

		JLabel lblDESTip1 = new JLabel("Plesase choose file what you encrypted:");
		lblDESTip1.setBounds(10, 34, 237, 15);
		panelDESEncrypted.add(lblDESTip1);

		JLabel lblDESTip2 = new JLabel("File");
		lblDESTip2.setBounds(118, 93, 54, 15);
		panelDESEncrypted.add(lblDESTip2);

		JLabel lblDESTip3 = new JLabel("FileEncrypted");
		lblDESTip3.setBounds(88, 154, 84, 15);
		panelDESEncrypted.add(lblDESTip3);

		JLabel lbDESlTip4 = new JLabel("Please input the password twice:");
		lbDESlTip4.setBounds(10, 226, 202, 15);
		panelDESEncrypted.add(lbDESlTip4);

		pwdPassword1 = new JPasswordField();
		pwdPassword1.setToolTipText("");
		pwdPassword1.setBounds(305, 291, 182, 21);
		panelDESEncrypted.add(pwdPassword1);

		textFieldDESEncryptedFilePath = new JTextField();
		textFieldDESEncryptedFilePath.setColumns(10);
		textFieldDESEncryptedFilePath.setBounds(174, 90, 381, 21);
		panelDESEncrypted.add(textFieldDESEncryptedFilePath);

		textFieldDESEncryptedFileToPath = new JTextField();
		textFieldDESEncryptedFileToPath.setColumns(10);
		textFieldDESEncryptedFileToPath.setBounds(174, 151, 381, 21);
		panelDESEncrypted.add(textFieldDESEncryptedFileToPath);

		JButton btnBrowse1 = new JButton("Browse...");

		btnBrowse1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileEncrypted = new JFileChooser("e:");
				if (fileEncrypted.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					textFieldDESEncryptedFilePath.setText(fileEncrypted.getSelectedFile().getPath());
					textFieldDESEncryptedFileToPath.setText(fileEncrypted.getSelectedFile().getPath() + ".enc");
				}
			}
		});
		btnBrowse1.setBounds(604, 89, 93, 23);
		panelDESEncrypted.add(btnBrowse1);

		JButton btnBrowse2 = new JButton("Browse...");
		btnBrowse2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileEncryptedToFile = new JFileChooser("e:");
				if (fileEncryptedToFile.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					textFieldDESEncryptedFileToPath.setText(fileEncryptedToFile.getSelectedFile().getPath());
				}
			}
		});
		btnBrowse2.setBounds(604, 150, 93, 23);
		panelDESEncrypted.add(btnBrowse2);

		pwdPassword2 = new JPasswordField();
		pwdPassword2.setBounds(305, 350, 182, 21);
		panelDESEncrypted.add(pwdPassword2);

		JButton btnEncrypted = new JButton("Encrypted");
		btnEncrypted.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fileName = textFieldDESEncryptedFilePath.getText();
				String encryptedFileName = textFieldDESEncryptedFileToPath.getText();
				char[] password1 = pwdPassword1.getPassword();
				char[] password2 = pwdPassword2.getPassword();
				if (Arrays.equals(password1, password2) && password1.length != 0) {
					String password = new String(password1);
					SymmetricEncryption.fileDESEncryptor(fileName, encryptedFileName, password);
				} else {
					JOptionPane.showMessageDialog(null, "Password is't identified or is null!");
				}
			}
		});
		btnEncrypted.setBounds(154, 416, 93, 23);
		panelDESEncrypted.add(btnEncrypted);

		JLabel label_4 = new JLabel("Password2");
		label_4.setBounds(196, 353, 84, 15);
		panelDESEncrypted.add(label_4);

		JLabel label_5 = new JLabel("Password");
		label_5.setBounds(196, 294, 84, 15);
		panelDESEncrypted.add(label_5);

		JButton btnClear_2 = new JButton("Clear");
		btnClear_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldDESEncryptedFilePath.setText(null);
				textFieldDESEncryptedFileToPath.setText(null);
				pwdPassword1.setText(null);
				pwdPassword2.setText(null);
			}
		});
		btnClear_2.setBounds(499, 416, 93, 23);
		panelDESEncrypted.add(btnClear_2);

		JPanel panelDESDecrypted = new JPanel();
		tabbedPaneSymmetricEncryption.addTab("DES/Decrypted", null, panelDESDecrypted, null);
		panelDESDecrypted.setLayout(null);

		textFieldDESFileDecryptedPath = new JTextField();
		textFieldDESFileDecryptedPath.setBounds(220, 123, 317, 21);
		panelDESDecrypted.add(textFieldDESFileDecryptedPath);
		textFieldDESFileDecryptedPath.setColumns(10);

		JLabel lblDESDecryptedTip1 = new JLabel("Plesase choose file what you encrypted:");
		lblDESDecryptedTip1.setBounds(16, 48, 237, 15);
		panelDESDecrypted.add(lblDESDecryptedTip1);

		JLabel lblDESDecryptedfile = new JLabel("DecryptedFile");
		lblDESDecryptedfile.setBounds(127, 188, 89, 15);
		panelDESDecrypted.add(lblDESDecryptedfile);

		JButton btnBrowseDESDecrypted = new JButton("Browse...");
		btnBrowseDESDecrypted.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooserDESDecrypted = new JFileChooser();
				fileChooserDESDecrypted.setFileFilter(new FileFilter() {

					@Override
					public String getDescription() {
						return "显示类型(*.enc)";
					}

					@Override
					public boolean accept(File f) {
						if (f.getName().endsWith(".enc") || f.isDirectory()) {
							return true;
						}
						return false;
					}
				});
				if (fileChooserDESDecrypted.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					textFieldDESFileDecryptedPath.setText(fileChooserDESDecrypted.getSelectedFile().getPath());
					String decryptedFileName = fileChooserDESDecrypted.getSelectedFile().getName();
					textFieldDESDecryptedToFilePath.setText(
							fileChooserDESDecrypted.getSelectedFile().getParent() + "\\" + fileChooserDESDecrypted
									.getSelectedFile().getName().substring(0, decryptedFileName.length() - 4));
				}
			}
		});
		btnBrowseDESDecrypted.setBounds(578, 122, 93, 23);
		panelDESDecrypted.add(btnBrowseDESDecrypted);

		JLabel lblFile = new JLabel("File");
		lblFile.setBounds(156, 126, 54, 15);
		panelDESDecrypted.add(lblFile);

		textFieldDESDecryptedToFilePath = new JTextField();
		textFieldDESDecryptedToFilePath.setColumns(10);
		textFieldDESDecryptedToFilePath.setBounds(220, 185, 317, 21);
		panelDESDecrypted.add(textFieldDESDecryptedToFilePath);

		JButton btnDESDescryptedToBrowse = new JButton("Browse...");
		btnDESDescryptedToBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser filehooserDESDescryptedSave = new JFileChooser();
				if (filehooserDESDescryptedSave.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					textFieldDESDecryptedToFilePath.setText(filehooserDESDescryptedSave.getSelectedFile().getPath());
				}
			}
		});
		btnDESDescryptedToBrowse.setBounds(578, 184, 93, 23);
		panelDESDecrypted.add(btnDESDescryptedToBrowse);

		labelTipInputDESPassword = new JLabel("Please input password:");
		labelTipInputDESPassword.setBounds(16, 282, 237, 15);
		panelDESDecrypted.add(labelTipInputDESPassword);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(214, 343, 68, 15);
		panelDESDecrypted.add(lblPassword);

		pwdDESPassword = new JPasswordField();
		pwdDESPassword.setBounds(303, 340, 160, 21);
		panelDESDecrypted.add(pwdDESPassword);

		JButton btnDESDecrypted = new JButton("Decrypted");
		btnDESDecrypted.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				char[] password = pwdDESPassword.getPassword();
				String myPassword = new String(password);
				SymmetricEncryption.fileDESDecryptor(textFieldDESFileDecryptedPath.getText(),
						textFieldDESDecryptedToFilePath.getText(), myPassword);
			}
		});
		btnDESDecrypted.setBounds(127, 433, 93, 23);
		panelDESDecrypted.add(btnDESDecrypted);

		JButton btnClear_3 = new JButton("Clear");
		btnClear_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldDESDecryptedToFilePath.setText(null);
				textFieldDESFileDecryptedPath.setText(null);
				pwdDESPassword.setText(null);
			}
		});
		btnClear_3.setBounds(550, 433, 93, 23);
		panelDESDecrypted.add(btnClear_3);

		JPanel panelAESEncrypted = new JPanel();
		panelAESEncrypted.setLayout(null);
		tabbedPaneSymmetricEncryption.addTab("AES/Encrypted", null, panelAESEncrypted, null);

		JLabel lblAESTip1 = new JLabel("Plesase choose file what you encrypted:");
		lblAESTip1.setBounds(10, 145, 237, 15);
		panelAESEncrypted.add(lblAESTip1);

		JLabel lblAESTip2 = new JLabel("File");
		lblAESTip2.setBounds(120, 201, 54, 15);
		panelAESEncrypted.add(lblAESTip2);

		JLabel lblAESTip3 = new JLabel("FileEncrypted");
		lblAESTip3.setBounds(90, 262, 84, 15);
		panelAESEncrypted.add(lblAESTip3);

		JLabel lblAESTip4 = new JLabel("Please input the password twice:");
		lblAESTip4.setBounds(10, 315, 202, 15);
		panelAESEncrypted.add(lblAESTip4);

		pwdAESEncryptedPassword1 = new JPasswordField();
		pwdAESEncryptedPassword1.setToolTipText("");
		pwdAESEncryptedPassword1.setBounds(303, 355, 182, 21);
		panelAESEncrypted.add(pwdAESEncryptedPassword1);

		textFieldAESEncryptedFilePath = new JTextField();
		textFieldAESEncryptedFilePath.setColumns(10);
		textFieldAESEncryptedFilePath.setBounds(176, 198, 381, 21);
		panelAESEncrypted.add(textFieldAESEncryptedFilePath);

		textFieldAESEncryptedToFilePath = new JTextField();
		textFieldAESEncryptedToFilePath.setColumns(10);
		textFieldAESEncryptedToFilePath.setBounds(176, 259, 381, 21);
		panelAESEncrypted.add(textFieldAESEncryptedToFilePath);

		JButton btnAESEncryptedFilePath = new JButton("Browse...");
		btnAESEncryptedFilePath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileEncrypted = new JFileChooser("e:");
				if (fileEncrypted.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					textFieldAESEncryptedFilePath.setText(fileEncrypted.getSelectedFile().getPath());
					textFieldAESEncryptedToFilePath.setText(fileEncrypted.getSelectedFile().getPath() + ".enc");
				}
			}
		});
		btnAESEncryptedFilePath.setBounds(606, 197, 93, 23);
		panelAESEncrypted.add(btnAESEncryptedFilePath);

		JButton btnAESEncryptedToFilePath = new JButton("Browse...");
		btnAESEncryptedToFilePath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileEncryptedToFile = new JFileChooser("e:");
				if (fileEncryptedToFile.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					textFieldAESEncryptedToFilePath.setText(fileEncryptedToFile.getSelectedFile().getPath());
				}
			}
		});
		btnAESEncryptedToFilePath.setBounds(606, 258, 93, 23);
		panelAESEncrypted.add(btnAESEncryptedToFilePath);

		pwdAESEncryptedPassword2 = new JPasswordField();
		pwdAESEncryptedPassword2.setBounds(303, 414, 182, 21);
		panelAESEncrypted.add(pwdAESEncryptedPassword2);

		JButton btnAESEncrypted = new JButton("Encrypted");
		btnAESEncrypted.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fileName = textFieldAESEncryptedFilePath.getText();
				String encryptedFileName = textFieldAESEncryptedToFilePath.getText();
				int keyByteLength = 16;
				if (rdbtnAESEncrypted128.isSelected()) {

				} else if (rdbtnAESEncrypted192.isSelected()) {
					keyByteLength = 192 / 8;
				} else if (rdbtnAESEncrypted256.isSelected()) {
					keyByteLength = 256 / 8;
				} else {
				}
				char[] password1 = pwdAESEncryptedPassword1.getPassword();
				char[] password2 = pwdAESEncryptedPassword2.getPassword();
				if (Arrays.equals(password1, password2) && password1.length != 0) {
					try {
						SymmetricEncryption.fileAESEncryptor(fileName, encryptedFileName, new String(password2),
								keyByteLength);
					} catch (HeadlessException e1) {
						JOptionPane.showMessageDialog(null, "Encrypted failure!");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Password is't identified or is null!");
				}

			}
		});
		btnAESEncrypted.setBounds(152, 464, 93, 23);
		panelAESEncrypted.add(btnAESEncrypted);

		JLabel lblAESTip6 = new JLabel("Password2");
		lblAESTip6.setBounds(194, 417, 84, 15);
		panelAESEncrypted.add(lblAESTip6);

		JLabel lblAESTip5 = new JLabel("Password");
		lblAESTip5.setBounds(194, 358, 84, 15);
		panelAESEncrypted.add(lblAESTip5);

		JButton btnAESEncryptedClear = new JButton("Clear");
		btnAESEncryptedClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldAESEncryptedFilePath.setText(null);
				textFieldAESEncryptedToFilePath.setText(null);
				pwdAESEncryptedPassword1.setText(null);
				pwdAESEncryptedPassword2.setText(null);
			}
		});
		btnAESEncryptedClear.setBounds(497, 464, 93, 23);
		panelAESEncrypted.add(btnAESEncryptedClear);

		rdbtnAESEncrypted128 = new JRadioButton("AES-128");
		rdbtnAESEncrypted128.setSelected(true);
		rdbtnAESEncrypted128.setBounds(152, 88, 121, 23);
		panelAESEncrypted.add(rdbtnAESEncrypted128);

		rdbtnAESEncrypted192 = new JRadioButton("AES-192");
		rdbtnAESEncrypted192.setBounds(333, 88, 121, 23);
		panelAESEncrypted.add(rdbtnAESEncrypted192);

		rdbtnAESEncrypted256 = new JRadioButton("AES-256");
		rdbtnAESEncrypted256.setBounds(515, 88, 121, 23);
		panelAESEncrypted.add(rdbtnAESEncrypted256);
		ButtonGroup keyChooseGroup = new ButtonGroup();
		keyChooseGroup.add(rdbtnAESEncrypted128);
		keyChooseGroup.add(rdbtnAESEncrypted192);
		keyChooseGroup.add(rdbtnAESEncrypted256);
		JLabel lblPleaseChooseLength = new JLabel("Please choose length of key:");
		lblPleaseChooseLength.setBounds(10, 39, 214, 15);
		panelAESEncrypted.add(lblPleaseChooseLength);

		JPanel panelAESDecrypted = new JPanel();
		panelAESDecrypted.setLayout(null);
		tabbedPaneSymmetricEncryption.addTab("AES/Decrypted", null, panelAESDecrypted, null);

		textFieldAESDecryptedFilePath = new JTextField();
		textFieldAESDecryptedFilePath.setColumns(10);
		textFieldAESDecryptedFilePath.setBounds(220, 221, 317, 21);
		panelAESDecrypted.add(textFieldAESDecryptedFilePath);

		JLabel lblAESDecryptedTip1 = new JLabel("Plesase choose file what you encrypted:");
		lblAESDecryptedTip1.setBounds(26, 151, 237, 15);
		panelAESDecrypted.add(lblAESDecryptedTip1);

		JLabel lblAESDecryptedTip3 = new JLabel("DecryptedFile");
		lblAESDecryptedTip3.setBounds(127, 286, 89, 15);
		panelAESDecrypted.add(lblAESDecryptedTip3);

		JButton btnAESDecryptedFilePath = new JButton("Browse...");
		btnAESDecryptedFilePath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooserAESDecrypted = new JFileChooser();
				fileChooserAESDecrypted.setFileFilter(new FileFilter() {

					@Override
					public String getDescription() {
						return "显示类型(*.enc)";
					}

					@Override
					public boolean accept(File f) {
						if (f.getName().endsWith(".enc") || f.isDirectory()) {
							return true;
						}
						return false;
					}
				});
				if (fileChooserAESDecrypted.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					textFieldAESDecryptedFilePath.setText(fileChooserAESDecrypted.getSelectedFile().getPath());
					String decryptedFileName = fileChooserAESDecrypted.getSelectedFile().getName();
					textFieldAESDecryptedToFilePath.setText(
							fileChooserAESDecrypted.getSelectedFile().getParent() + "\\" + fileChooserAESDecrypted
									.getSelectedFile().getName().substring(0, decryptedFileName.length() - 4));
				}
			}
		});
		btnAESDecryptedFilePath.setBounds(578, 220, 93, 23);
		panelAESDecrypted.add(btnAESDecryptedFilePath);

		JLabel lblAESDecryptedTip2 = new JLabel("File");
		lblAESDecryptedTip2.setBounds(156, 224, 54, 15);
		panelAESDecrypted.add(lblAESDecryptedTip2);

		textFieldAESDecryptedToFilePath = new JTextField();
		textFieldAESDecryptedToFilePath.setColumns(10);
		textFieldAESDecryptedToFilePath.setBounds(220, 283, 317, 21);
		panelAESDecrypted.add(textFieldAESDecryptedToFilePath);

		JButton btnAESDecryptedToFilePath = new JButton("Browse...");
		btnAESDecryptedToFilePath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser filehooserAESDescryptedSave = new JFileChooser();
				if (filehooserAESDescryptedSave.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					textFieldAESDecryptedToFilePath.setText(filehooserAESDescryptedSave.getSelectedFile().getPath());
				}
			}
		});
		btnAESDecryptedToFilePath.setBounds(578, 282, 93, 23);
		panelAESDecrypted.add(btnAESDecryptedToFilePath);

		JLabel lblAESDecryptedTip4 = new JLabel("Please input password:");
		lblAESDecryptedTip4.setBounds(26, 340, 237, 15);
		panelAESDecrypted.add(lblAESDecryptedTip4);

		JLabel lblAESDecryptedTip5 = new JLabel("Password");
		lblAESDecryptedTip5.setBounds(220, 380, 68, 15);
		panelAESDecrypted.add(lblAESDecryptedTip5);

		pwdAESDecryptedPassword = new JPasswordField();
		pwdAESDecryptedPassword.setBounds(298, 377, 160, 21);
		panelAESDecrypted.add(pwdAESDecryptedPassword);

		btnAESDecrypted = new JButton("Decrypted");
		btnAESDecrypted.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String AESDecryptedFileName = textFieldAESDecryptedFilePath.getText();
				String AESDecryptedFileToName = textFieldAESDecryptedToFilePath.getText();
				char[] password = pwdAESDecryptedPassword.getPassword();
				int keyByteLength = 16;
				if (rdbtnAESDecrypted128.isSelected()) {
					keyByteLength = 128 / 8;
				} else if (rdbtnAESDecrypted192.isSelected()) {
					keyByteLength = 192 / 8;
				} else if (rdbtnAESDecrypted256.isSelected()) {
					keyByteLength = 256 / 8;
				} else {
				}
				try {
					SymmetricEncryption.fileAESDecryptor(AESDecryptedFileName, AESDecryptedFileToName,
							new String(password), keyByteLength);
				} catch (HeadlessException e1) {
					JOptionPane.showMessageDialog(null, "Deceypted Failure!");
				}
			}
		});
		btnAESDecrypted.setBounds(127, 433, 93, 23);
		panelAESDecrypted.add(btnAESDecrypted);

		JButton btnAESDecryptedClear = new JButton("Clear");
		btnAESDecryptedClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldAESDecryptedFilePath.setText(null);
				textFieldAESDecryptedToFilePath.setText(null);
				pwdAESDecryptedPassword.setText(null);
			}
		});
		btnAESDecryptedClear.setBounds(550, 433, 93, 23);
		panelAESDecrypted.add(btnAESDecryptedClear);

		rdbtnAESDecrypted128 = new JRadioButton("AES-128");
		rdbtnAESDecrypted128.setSelected(true);
		rdbtnAESDecrypted128.setBounds(141, 85, 121, 23);
		panelAESDecrypted.add(rdbtnAESDecrypted128);

		JLabel lblAESDecryptedTip6 = new JLabel("Please choose length of key:");
		lblAESDecryptedTip6.setBounds(26, 34, 214, 15);
		panelAESDecrypted.add(lblAESDecryptedTip6);

		rdbtnAESDecrypted192 = new JRadioButton("AES-192");
		rdbtnAESDecrypted192.setBounds(330, 85, 121, 23);
		panelAESDecrypted.add(rdbtnAESDecrypted192);

		rdbtnAESDecrypted256 = new JRadioButton("AES-256");
		rdbtnAESDecrypted256.setBounds(522, 85, 121, 23);
		panelAESDecrypted.add(rdbtnAESDecrypted256);
		ButtonGroup AESDecryptedChooseGroup = new ButtonGroup();
		AESDecryptedChooseGroup.add(rdbtnAESDecrypted128);
		AESDecryptedChooseGroup.add(rdbtnAESDecrypted192);
		AESDecryptedChooseGroup.add(rdbtnAESDecrypted256);

		JPanel panelAESAnalysis = new JPanel();
		tabbedPaneSymmetricEncryption.addTab("AESAnalysis", null, panelAESAnalysis, null);
		panelAESAnalysis.setLayout(new BorderLayout(0, 0));

		JPanel panelAESAnalysisInner = new JPanel();
		panelAESAnalysis.add(panelAESAnalysisInner, BorderLayout.SOUTH);
		panelAESAnalysisInner.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnStartAnalysisAES = new JButton("StartAnalysis");
		btnStartAnalysisAES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChartAES chartAES = new ChartAES();
				ChartPanel chartPanelAES = chartAES.makeAChartAES();
				panelAESAnalysis.add(chartPanelAES, BorderLayout.CENTER);

			}
		});
		panelAESAnalysisInner.add(btnStartAnalysisAES);

		JTabbedPane tabbedPaneDigitalSignature = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("DigitalSignature", null, tabbedPaneDigitalSignature, null);

		ButtonGroup btnDSAVertifyGroup = new ButtonGroup();
		JPanel panelDSASignature = new JPanel();
		tabbedPaneDigitalSignature.addTab("DSA/Signature", null, panelDSASignature, null);
		panelDSASignature.setLayout(null);

		JButton btnDSASignatureFilePath = new JButton("Browse...");
		btnDSASignatureFilePath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileDSASignature = new JFileChooser("e:");
				if (fileDSASignature.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					textFieldDSASignatureFilePath.setText(fileDSASignature.getSelectedFile().getPath());
					textFieldDSASignatureValueFilePath.setText(fileDSASignature.getSelectedFile().getPath() + ".sign");
				}
			}
		});
		btnDSASignatureFilePath.setBounds(509, 262, 93, 23);
		panelDSASignature.add(btnDSASignatureFilePath);

		textFieldDSASignatureFilePath = new JTextField();
		textFieldDSASignatureFilePath.setBounds(224, 263, 255, 21);
		panelDSASignature.add(textFieldDSASignatureFilePath);
		textFieldDSASignatureFilePath.setColumns(10);

		textFieldDSASignatureValueFilePath = new JTextField();
		textFieldDSASignatureValueFilePath.setBounds(224, 342, 255, 21);
		panelDSASignature.add(textFieldDSASignatureValueFilePath);
		textFieldDSASignatureValueFilePath.setColumns(10);

		JButton btnDSASigantureValueFilePath = new JButton("Browse...");
		btnDSASigantureValueFilePath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileDSASignatureValue = new JFileChooser("e:");
				if (fileDSASignatureValue.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					textFieldDSASignatureValueFilePath.setText(fileDSASignatureValue.getSelectedFile().getPath());
				}
			}
		});
		btnDSASigantureValueFilePath.setBounds(509, 341, 93, 23);
		panelDSASignature.add(btnDSASigantureValueFilePath);

		JLabel lblDSASigantureTip1 = new JLabel("File");
		lblDSASigantureTip1.setBounds(129, 266, 54, 15);
		panelDSASignature.add(lblDSASigantureTip1);

		JLabel lblDSASignatureTip2 = new JLabel("SignFile");
		lblDSASignatureTip2.setBounds(129, 345, 54, 15);
		panelDSASignature.add(lblDSASignatureTip2);

		JLabel lblPleaseChooseFile = new JLabel("Please choose file path of signature:");
		lblPleaseChooseFile.setBounds(10, 197, 236, 15);
		panelDSASignature.add(lblPleaseChooseFile);

		class KeyStorePasswordVertifyForDSASignature extends JFrame {
			private static final long serialVersionUID = 1L;
			public JPasswordField pwdKeyStorePassword;
			public JPasswordField pwdDSAKeyPassword;

			public KeyStorePasswordVertifyForDSASignature() {
				super("Signature key password");
				getContentPane().setLayout(null);
				this.setSize(434, 330);
				this.setLocation(20, 30);
				this.setResizable(false);
				JPanel panel = new JPanel();
				panel.setBounds(0, 0, 434, 261);
				getContentPane().add(panel);
				panel.setLayout(null);

				pwdKeyStorePassword = new JPasswordField();
				pwdKeyStorePassword.setBounds(203, 90, 133, 21);
				panel.add(pwdKeyStorePassword);

				JLabel lblKeystorePassword = new JLabel("KeyStore Password:");
				lblKeystorePassword.setBounds(84, 93, 133, 15);
				panel.add(lblKeystorePassword);

				JButton btnGetPasswordEnter = new JButton("Enter");
				btnGetPasswordEnter.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						char[] keyStorePassword = pwdKeyStorePassword.getPassword();
						char[] keyAliasDSAKeyPassword = pwdDSAKeyPassword.getPassword();
						if (rdbtnSHA224WithDSASignature.isSelected()) {
							try {
								DigitalSignature.signFileDSA(textFieldDSASignatureFilePath.getText(),
										DigitalSignature.getDSAPrivateKey(keyStorePassword, keyAliasDSAKeyPassword),
										textFieldDSASignatureValueFilePath.getText(), "SHA224withDSA");
								JOptionPane.showMessageDialog(null, "Signature successfully!");
							} catch (Exception e1) {
								JOptionPane.showMessageDialog(null, e1.toString());
							}
						} else if (rdbtnSHA256WithDSASignature.isSelected()) {
							try {
								DigitalSignature.signFileDSA(textFieldDSASignatureFilePath.getText(),
										DigitalSignature.getDSAPrivateKey(keyStorePassword, keyAliasDSAKeyPassword),
										textFieldDSASignatureValueFilePath.getText(), "SHA256withDSA");
								JOptionPane.showMessageDialog(null, "Signature successfully!");
							} catch (Exception e1) {
								JOptionPane.showMessageDialog(null, e1.toString());
							}
						} else if (rdbtnSHA384WithDSASignature.isSelected()) {
							try {
								DigitalSignature.signFileDSA(textFieldDSASignatureFilePath.getText(),
										DigitalSignature.getDSAPrivateKey(keyStorePassword, keyAliasDSAKeyPassword),
										textFieldDSASignatureValueFilePath.getText(), "SHA384withDSA");
								JOptionPane.showMessageDialog(null, "Signature successfully!");
							} catch (Exception e1) {
								JOptionPane.showMessageDialog(null, e1.toString());
							}
						} else if (rdbtnSHA512WithDSASignature.isSelected()) {
							try {
								DigitalSignature.signFileDSA(textFieldDSASignatureFilePath.getText(),
										DigitalSignature.getDSAPrivateKey(keyStorePassword, keyAliasDSAKeyPassword),
										textFieldDSASignatureValueFilePath.getText(), "SHA512withDSA");
								JOptionPane.showMessageDialog(null, "Signature successfully!");
							} catch (Exception e1) {
								JOptionPane.showMessageDialog(null, e1.toString());
							}
						} else {
						}
						setVisible(false);
					}
				});
				btnGetPasswordEnter.setBounds(153, 206, 93, 23);
				panel.add(btnGetPasswordEnter);

				JLabel lblPleaseInputKeystore = new JLabel("Please input keystore password and alias password");
				lblPleaseInputKeystore.setBounds(62, 34, 314, 15);
				panel.add(lblPleaseInputKeystore);

				JLabel lblDsaKeyPassword = new JLabel("DSA key Password:");
				lblDsaKeyPassword.setBounds(84, 151, 108, 15);
				panel.add(lblDsaKeyPassword);

				pwdDSAKeyPassword = new JPasswordField();
				pwdDSAKeyPassword.setBounds(203, 148, 133, 21);
				panel.add(pwdDSAKeyPassword);
			}
		}
		JButton btnDsasignature = new JButton("DSASignature");
		btnDsasignature.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KeyStorePasswordVertifyForDSASignature keyStorePasswordVertify = new KeyStorePasswordVertifyForDSASignature();
				keyStorePasswordVertify.setVisible(true);
			}
		});
		btnDsasignature.setBounds(89, 435, 114, 23);
		panelDSASignature.add(btnDsasignature);

		JButton btnDSASignatureClear = new JButton("Clear");
		btnDSASignatureClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldDSASignatureFilePath.setText(null);
				textFieldDSASignatureValueFilePath.setText(null);
			}
		});
		btnDSASignatureClear.setBounds(509, 435, 93, 23);
		panelDSASignature.add(btnDSASignatureClear);

		JLabel lblDSASignatureTip3 = new JLabel("Please choose DSA signature type:");
		lblDSASignatureTip3.setBounds(10, 43, 210, 15);
		panelDSASignature.add(lblDSASignatureTip3);

		rdbtnSHA224WithDSASignature = new JRadioButton("SHA224withDSA");
		rdbtnSHA224WithDSASignature.setSelected(true);
		rdbtnSHA224WithDSASignature.setBounds(75, 106, 121, 23);
		panelDSASignature.add(rdbtnSHA224WithDSASignature);

		rdbtnSHA256WithDSASignature = new JRadioButton("SHA256withDSA");
		rdbtnSHA256WithDSASignature.setBounds(255, 106, 121, 23);
		panelDSASignature.add(rdbtnSHA256WithDSASignature);

		rdbtnSHA384WithDSASignature = new JRadioButton("SHA384withDSA");
		rdbtnSHA384WithDSASignature.setBounds(434, 106, 121, 23);
		panelDSASignature.add(rdbtnSHA384WithDSASignature);

		rdbtnSHA512WithDSASignature = new JRadioButton("SHA512withDSA");
		rdbtnSHA512WithDSASignature.setBounds(600, 106, 121, 23);
		panelDSASignature.add(rdbtnSHA512WithDSASignature);
		ButtonGroup btnGroupDSASignatureChoose = new ButtonGroup();
		btnGroupDSASignatureChoose.add(rdbtnSHA224WithDSASignature);
		btnGroupDSASignatureChoose.add(rdbtnSHA256WithDSASignature);
		btnGroupDSASignatureChoose.add(rdbtnSHA384WithDSASignature);
		btnGroupDSASignatureChoose.add(rdbtnSHA512WithDSASignature);

		JPanel panelDSAVertify = new JPanel();
		panelDSAVertify.setLayout(null);
		tabbedPaneDigitalSignature.addTab("DSA/Vertify", null, panelDSAVertify, null);

		JButton btnDSAVertifyFilePath = new JButton("Browse...");
		btnDSAVertifyFilePath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileDSAVertifyChooser = new JFileChooser("e:");
				if (fileDSAVertifyChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					textFieldDSAVertifyFilePath.setText(fileDSAVertifyChooser.getSelectedFile().getPath());
				}
			}
		});
		btnDSAVertifyFilePath.setBounds(509, 262, 93, 23);
		panelDSAVertify.add(btnDSAVertifyFilePath);

		textFieldDSAVertifyFilePath = new JTextField();
		textFieldDSAVertifyFilePath.setColumns(10);
		textFieldDSAVertifyFilePath.setBounds(224, 263, 255, 21);
		panelDSAVertify.add(textFieldDSAVertifyFilePath);

		textFieldDSAVertifySignValueFilePath = new JTextField();
		textFieldDSAVertifySignValueFilePath.setColumns(10);
		textFieldDSAVertifySignValueFilePath.setBounds(224, 342, 255, 21);
		panelDSAVertify.add(textFieldDSAVertifySignValueFilePath);

		JButton btnDSAVertifySignValueFilePath = new JButton("Browse...");
		btnDSAVertifySignValueFilePath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFileChooser fileChooserDSAVertifySignValue = new JFileChooser();
				fileChooserDSAVertifySignValue.setFileFilter(new FileFilter() {

					@Override
					public String getDescription() {
						return "显示类型(*.sign)";
					}

					@Override
					public boolean accept(File f) {
						if (f.getName().endsWith(".sign") || f.isDirectory()) {
							return true;
						}
						return false;
					}
				});
				if (fileChooserDSAVertifySignValue.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					textFieldDSAVertifySignValueFilePath
							.setText(fileChooserDSAVertifySignValue.getSelectedFile().getPath());
				}
			}
		});
		btnDSAVertifySignValueFilePath.setBounds(509, 341, 93, 23);
		panelDSAVertify.add(btnDSAVertifySignValueFilePath);

		JLabel lblDSAVertifyTip1 = new JLabel("File");
		lblDSAVertifyTip1.setBounds(149, 266, 54, 15);
		panelDSAVertify.add(lblDSAVertifyTip1);

		JLabel lblDSAVertifyTip4 = new JLabel("SignValueFile");
		lblDSAVertifyTip4.setBounds(129, 345, 85, 15);
		panelDSAVertify.add(lblDSAVertifyTip4);

		JLabel lblDSAVertifyTip2 = new JLabel("Please choose file path of vertify:");
		lblDSAVertifyTip2.setBounds(10, 197, 236, 15);
		panelDSAVertify.add(lblDSAVertifyTip2);

		class KeyStoreVertifyPasswordForDSAVertify extends JFrame {
			private static final long serialVersionUID = 1L;
			public JPasswordField pwdKeyStorePassword;
			public JPasswordField pwdDSAKeyPassword;

			public KeyStoreVertifyPasswordForDSAVertify() {
				super("Signature key password");
				getContentPane().setLayout(null);
				this.setSize(434, 330);
				this.setLocation(20, 30);
				this.setResizable(false);
				JPanel panel = new JPanel();
				panel.setBounds(0, 0, 434, 261);
				getContentPane().add(panel);
				panel.setLayout(null);

				pwdKeyStorePassword = new JPasswordField();
				pwdKeyStorePassword.setBounds(203, 90, 133, 21);
				panel.add(pwdKeyStorePassword);

				JLabel lblKeystorePassword = new JLabel("KeyStore Password:");
				lblKeystorePassword.setBounds(84, 93, 133, 15);
				panel.add(lblKeystorePassword);

				JButton btnGetPasswordEnter = new JButton("Enter");
				btnGetPasswordEnter.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						char[] keyStorePassword = pwdKeyStorePassword.getPassword();
						char[] keyAliasDSAKeyPassword = pwdDSAKeyPassword.getPassword();
						if (rdbtnDSAVertifySHA224WithDSA.isSelected()) {
							try {
								if (DigitalSignature.verifyFileDSA(textFieldDSAVertifyFilePath.getText(),
										DigitalSignature.getDSAPublicKey(keyStorePassword, keyAliasDSAKeyPassword),
										textFieldDSASignatureValueFilePath.getText(), "SHA224withDSA")) {
									JOptionPane.showMessageDialog(null, "Vertify successfully!");
								} else {
									JOptionPane.showMessageDialog(null, "Vertify failure!");
								}
							} catch (Exception e1) {
								JOptionPane.showMessageDialog(null, e1.toString());
							}
						} else if (rdbtnDSAVertifySHA256WithDSA.isSelected()) {
							try {
								if (DigitalSignature.verifyFileDSA(textFieldDSAVertifyFilePath.getText(),
										DigitalSignature.getDSAPublicKey(keyStorePassword, keyAliasDSAKeyPassword),
										textFieldDSASignatureValueFilePath.getText(), "SHA256withDSA")) {
									JOptionPane.showMessageDialog(null, "Vertify successfully!");
								} else {
									JOptionPane.showMessageDialog(null, "Vertify failure!");
								}
							} catch (Exception e1) {
								JOptionPane.showMessageDialog(null, e1.toString());
							}
						} else if (rdbtnDSAVertifySHA384WithDSA.isSelected()) {
							try {
								if (DigitalSignature.verifyFileDSA(textFieldDSAVertifyFilePath.getText(),
										DigitalSignature.getDSAPublicKey(keyStorePassword, keyAliasDSAKeyPassword),
										textFieldDSASignatureValueFilePath.getText(), "SHA384withDSA")) {
									JOptionPane.showMessageDialog(null, "Vertify successfully!");
								} else {
									JOptionPane.showMessageDialog(null, "Vertify failure!");
								}
							} catch (Exception e1) {
								JOptionPane.showMessageDialog(null, e1.toString());
							}
						} else if (rdbtnDSAVertifySHA512WithDSA.isSelected()) {
							try {
								if (DigitalSignature.verifyFileDSA(textFieldDSAVertifyFilePath.getText(),
										DigitalSignature.getDSAPublicKey(keyStorePassword, keyAliasDSAKeyPassword),
										textFieldDSASignatureValueFilePath.getText(), "SHA512withDSA")) {
									JOptionPane.showMessageDialog(null, "Vertify successfully!");
								} else {
									JOptionPane.showMessageDialog(null, "Vertify failure!");
								}
							} catch (Exception e1) {
								JOptionPane.showMessageDialog(null, e1.toString());
							}
						} else {
						}
						setVisible(false);
					}
				});
				btnGetPasswordEnter.setBounds(153, 206, 93, 23);
				panel.add(btnGetPasswordEnter);

				JLabel lblPleaseInputKeystore = new JLabel("Please input keystore password and alias password");
				lblPleaseInputKeystore.setBounds(62, 34, 314, 15);
				panel.add(lblPleaseInputKeystore);

				JLabel lblDsaKeyPassword = new JLabel("DSA key Password:");
				lblDsaKeyPassword.setBounds(84, 151, 108, 15);
				panel.add(lblDsaKeyPassword);

				pwdDSAKeyPassword = new JPasswordField();
				pwdDSAKeyPassword.setBounds(203, 148, 133, 21);
				panel.add(pwdDSAKeyPassword);
			}

		}
		JButton btnDSAVertify = new JButton("DSAVertify");
		btnDSAVertify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KeyStoreVertifyPasswordForDSAVertify keyStoreVertifyPasswordForDSAVertify = new KeyStoreVertifyPasswordForDSAVertify();
				keyStoreVertifyPasswordForDSAVertify.setVisible(true);
			}
		});
		btnDSAVertify.setBounds(89, 435, 114, 23);
		panelDSAVertify.add(btnDSAVertify);

		JButton btnDSAVertifyClear = new JButton("Clear");
		btnDSAVertifyClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldDSAVertifyFilePath.setText(null);
				textFieldDSAVertifySignValueFilePath.setText(null);
			}
		});
		btnDSAVertifyClear.setBounds(509, 435, 93, 23);
		panelDSAVertify.add(btnDSAVertifyClear);

		JLabel lblDSAVertifyTip3 = new JLabel("Please choose DSA vertify type:");
		lblDSAVertifyTip3.setBounds(10, 43, 193, 15);
		panelDSAVertify.add(lblDSAVertifyTip3);

		rdbtnDSAVertifySHA224WithDSA = new JRadioButton("SHA224withDSA");
		rdbtnDSAVertifySHA224WithDSA.setSelected(true);
		rdbtnDSAVertifySHA224WithDSA.setBounds(75, 106, 121, 23);
		panelDSAVertify.add(rdbtnDSAVertifySHA224WithDSA);

		rdbtnDSAVertifySHA256WithDSA = new JRadioButton("SHA256withDSA");
		rdbtnDSAVertifySHA256WithDSA.setBounds(255, 106, 121, 23);
		panelDSAVertify.add(rdbtnDSAVertifySHA256WithDSA);

		rdbtnDSAVertifySHA384WithDSA = new JRadioButton("SHA384withDSA");
		rdbtnDSAVertifySHA384WithDSA.setBounds(434, 106, 121, 23);
		panelDSAVertify.add(rdbtnDSAVertifySHA384WithDSA);

		rdbtnDSAVertifySHA512WithDSA = new JRadioButton("SHA512withDSA");
		rdbtnDSAVertifySHA512WithDSA.setBounds(600, 106, 121, 23);
		panelDSAVertify.add(rdbtnDSAVertifySHA512WithDSA);
		btnDSAVertifyGroup.add(rdbtnDSAVertifySHA224WithDSA);
		btnDSAVertifyGroup.add(rdbtnDSAVertifySHA256WithDSA);
		btnDSAVertifyGroup.add(rdbtnDSAVertifySHA384WithDSA);
		btnDSAVertifyGroup.add(rdbtnDSAVertifySHA512WithDSA);

		JTabbedPane tabbedPaneMAC = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("MAC", null, tabbedPaneMAC, null);

		JPanel panelHMACSHA1 = new JPanel();
		tabbedPaneMAC.addTab("HmacSHA1", null, panelHMACSHA1, null);
		panelHMACSHA1.setLayout(null);

		textFieldHMACSHA1String = new JTextField();
		textFieldHMACSHA1String.setColumns(10);
		textFieldHMACSHA1String.setBounds(254, 80, 302, 21);
		panelHMACSHA1.add(textFieldHMACSHA1String);

		rdbtnHMACSHA1StringMAC = new JRadioButton("StringMac");
		rdbtnHMACSHA1StringMAC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnHMACSHA1StringMAC.isSelected()) {
					btnHMACSHA1FilePath.setEnabled(false);
				}
			}
		});
		rdbtnHMACSHA1StringMAC.setBounds(146, 79, 93, 23);
		panelHMACSHA1.add(rdbtnHMACSHA1StringMAC);

		rdbtnHMACSHA1FileMAC = new JRadioButton("FileMac");
		rdbtnHMACSHA1FileMAC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnHMACSHA1FileMAC.isSelected()) {
					btnHMACSHA1FilePath.setEnabled(true);
				}
			}
		});
		ButtonGroup btnGroupHMACSHA1 = new ButtonGroup();
		btnGroupHMACSHA1.add(rdbtnHMACSHA1FileMAC);
		btnGroupHMACSHA1.add(rdbtnHMACSHA1StringMAC);
		rdbtnHMACSHA1FileMAC.setBounds(146, 140, 93, 23);
		panelHMACSHA1.add(rdbtnHMACSHA1FileMAC);

		textFieldHMACSHA1FilePath = new JTextField();
		textFieldHMACSHA1FilePath.setColumns(10);
		textFieldHMACSHA1FilePath.setBounds(254, 141, 302, 21);
		panelHMACSHA1.add(textFieldHMACSHA1FilePath);

		btnHMACSHA1FilePath = new JButton("Browse...");
		btnHMACSHA1FilePath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooseHMACSHA1 = new JFileChooser("e:");
				if (fileChooseHMACSHA1.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					textFieldHMACSHA1FilePath.setText(fileChooseHMACSHA1.getSelectedFile().getPath());
				}
			}
		});
		btnHMACSHA1FilePath.setBounds(566, 140, 93, 23);
		panelHMACSHA1.add(btnHMACSHA1FilePath);

		pwdHMACSHA1Password1 = new JPasswordField();
		pwdHMACSHA1Password1.setBounds(296, 239, 216, 21);
		panelHMACSHA1.add(pwdHMACSHA1Password1);

		JLabel lblHMACSHA1Password1 = new JLabel("password");
		lblHMACSHA1Password1.setBounds(202, 242, 84, 15);
		panelHMACSHA1.add(lblHMACSHA1Password1);

		JLabel lblHMACSHA1Password2 = new JLabel("password");
		lblHMACSHA1Password2.setBounds(202, 300, 84, 15);
		panelHMACSHA1.add(lblHMACSHA1Password2);

		pwdHMACSHA1Password2 = new JPasswordField();
		pwdHMACSHA1Password2.setBounds(296, 297, 216, 21);
		panelHMACSHA1.add(pwdHMACSHA1Password2);

		JButton btnHMACSHA1Calculator = new JButton("Calculator to str");
		btnHMACSHA1Calculator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Arrays.equals(pwdHMACSHA1Password1.getPassword(), pwdHMACSHA1Password2.getPassword())
						&& pwdHMACSHA1Password1.getPassword().length != 0) {
					if (rdbtnHMACSHA1FileMAC.isSelected()) {
						try {
							textFieldHMACSHA1Result.setText(MessageAuthentication.generatorFileMACToString("HmacSHA1",
									new String(pwdHMACSHA1Password1.getPassword()),
									textFieldHMACSHA1FilePath.getText()));
							JOptionPane.showMessageDialog(null, "Generator MAC successfully!");
						} catch (InvalidKeyException e1) {
							JOptionPane.showMessageDialog(null, "InvalidKey!");
						} catch (NoSuchAlgorithmException e1) {
							JOptionPane.showMessageDialog(null, "NoSuchAlgorithm");
						} catch (FileNotFoundException e1) {
							JOptionPane.showMessageDialog(null, "FileNotFound");
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(null, "IOException");
						}
					} else if (rdbtnHMACSHA1StringMAC.isSelected()) {
						try {
							textFieldHMACSHA1Result.setText(MessageAuthentication.generatorStringMAC("HmacSHA1",
									new String(pwdHMACSHA1Password1.getPassword()), textFieldHMACSHA1String.getText()));
							JOptionPane.showMessageDialog(null, "Generator MAC successfully!");
						} catch (InvalidKeyException e1) {
							JOptionPane.showMessageDialog(null, "InvalidKey!");
						} catch (NoSuchAlgorithmException e1) {
							JOptionPane.showMessageDialog(null, "NoSuchAlgorithm");
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Password is't identified or is null!");
				}
			}
		});
		btnHMACSHA1Calculator.setBounds(53, 391, 142, 23);
		panelHMACSHA1.add(btnHMACSHA1Calculator);

		JButton btnHMACSHA1Clear = new JButton("Clear");
		btnHMACSHA1Clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldHMACSHA1Result.setText(null);
				textFieldHMACSHA1FilePath.setText(null);
				textFieldHMACSHA1String.setText(null);
				textFieldHMACSHA1ToFilePath.setText(null);
				pwdHMACSHA1Password1.setText(null);
				pwdHMACSHA1Password2.setText(null);
			}
		});
		btnHMACSHA1Clear.setBounds(649, 469, 93, 23);
		panelHMACSHA1.add(btnHMACSHA1Clear);

		JLabel lblHMACSHA1Tip1 = new JLabel("Please choose mac style:");
		lblHMACSHA1Tip1.setBounds(28, 32, 230, 15);
		panelHMACSHA1.add(lblHMACSHA1Tip1);

		JLabel lblHMACSHA1Tip2 = new JLabel("Please input identified password twice:");
		lblHMACSHA1Tip2.setBounds(28, 197, 249, 15);
		panelHMACSHA1.add(lblHMACSHA1Tip2);

		JButton btnHMACSHA1CalculatorToFile = new JButton("Calculator to file");
		btnHMACSHA1CalculatorToFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Arrays.equals(pwdHMACSHA1Password1.getPassword(), pwdHMACSHA1Password2.getPassword())
						&& pwdHMACSHA1Password1.getPassword().length != 0) {
					if (rdbtnHMACSHA1FileMAC.isSelected()) {
						try {
							MessageAuthentication.generatorFileMACToFile("HmacSHA1",
									new String(pwdHMACSHA1Password2.getPassword()), textFieldHMACSHA1FilePath.getText(),
									textFieldHMACSHA1ToFilePath.getText());
							JOptionPane.showMessageDialog(null, "Generator MAC successfully!");
						} catch (InvalidKeyException e1) {
							JOptionPane.showMessageDialog(null, "InvalidKey!");
						} catch (HeadlessException e1) {
							JOptionPane.showMessageDialog(null, "Headless!");
						} catch (NoSuchAlgorithmException e1) {
							JOptionPane.showMessageDialog(null, "NoSuchAlgorithm!");
						} catch (FileNotFoundException e1) {
							JOptionPane.showMessageDialog(null, "FileNotFound!");
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(null, "IOException!");
						}

					} else if (rdbtnHMACSHA1StringMAC.isSelected()) {
						try {
							MessageAuthentication.generatorStringMACToFile("HmacSHA1",
									new String(pwdHMACSHA1Password2.getPassword()), textFieldHMACSHA1String.getText(),
									textFieldHMACSHA1ToFilePath.getText());
							JOptionPane.showMessageDialog(null, "Generator MAC successfully!");
						} catch (InvalidKeyException e1) {
							JOptionPane.showMessageDialog(null, "InvalidKey!");
						} catch (HeadlessException e1) {
							JOptionPane.showMessageDialog(null, "Headless!");
						} catch (NoSuchAlgorithmException e1) {
							JOptionPane.showMessageDialog(null, "NoSuchAlgorithm!");
						} catch (FileNotFoundException e1) {
							JOptionPane.showMessageDialog(null, "FileNotFound!");
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(null, "IOException!");
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Password is't identified or is null!");
				}
			}
		});
		btnHMACSHA1CalculatorToFile.setBounds(53, 437, 141, 23);
		panelHMACSHA1.add(btnHMACSHA1CalculatorToFile);

		textFieldHMACSHA1Result = new JTextField();
		textFieldHMACSHA1Result.setBounds(205, 392, 537, 21);
		panelHMACSHA1.add(textFieldHMACSHA1Result);
		textFieldHMACSHA1Result.setColumns(10);

		JLabel lblPleaseChooseCalculator = new JLabel("Please choose calculator style:");
		lblPleaseChooseCalculator.setBounds(28, 341, 230, 15);
		panelHMACSHA1.add(lblPleaseChooseCalculator);

		textFieldHMACSHA1ToFilePath = new JTextField();
		textFieldHMACSHA1ToFilePath.setBounds(209, 438, 290, 21);
		panelHMACSHA1.add(textFieldHMACSHA1ToFilePath);
		textFieldHMACSHA1ToFilePath.setColumns(10);

		JButton btnHMACSHA1ToFilePath = new JButton("Browse...");
		btnHMACSHA1ToFilePath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooserHMACToFile = new JFileChooser();
				if (fileChooserHMACToFile.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					textFieldHMACSHA1ToFilePath.setText(fileChooserHMACToFile.getSelectedFile().getPath() + ".mac");
				}
			}
		});
		btnHMACSHA1ToFilePath.setBounds(509, 437, 93, 23);
		panelHMACSHA1.add(btnHMACSHA1ToFilePath);

		JPanel panelHMACSHA224 = new JPanel();
		panelHMACSHA224.setLayout(null);
		tabbedPaneMAC.addTab("HmacSHA224", null, panelHMACSHA224, null);

		textFieldHMACSHA224String = new JTextField();
		textFieldHMACSHA224String.setColumns(10);
		textFieldHMACSHA224String.setBounds(254, 80, 302, 21);
		panelHMACSHA224.add(textFieldHMACSHA224String);

		rdbtnHMACSHA224StringMAC = new JRadioButton("StringMac");
		rdbtnHMACSHA224StringMAC.setSelected(true);
		rdbtnHMACSHA224StringMAC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (rdbtnHMACSHA224StringMAC.isSelected()) {
					btnHMACSHA224FilePath.setEnabled(false);
				}

			}
		});
		rdbtnHMACSHA224StringMAC.setBounds(146, 79, 93, 23);
		panelHMACSHA224.add(rdbtnHMACSHA224StringMAC);

		rdbtnHMACSHA224FileMAC = new JRadioButton("FileMac");
		rdbtnHMACSHA224FileMAC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnHMACSHA224FileMAC.isSelected()) {
					btnHMACSHA224FilePath.setEnabled(true);
				}
			}
		});

		ButtonGroup btnGroupHMACSHA224 = new ButtonGroup();
		btnGroupHMACSHA224.add(rdbtnHMACSHA224FileMAC);
		btnGroupHMACSHA224.add(rdbtnHMACSHA224StringMAC);
		rdbtnHMACSHA224FileMAC.setBounds(146, 140, 93, 23);
		panelHMACSHA224.add(rdbtnHMACSHA224FileMAC);

		textFieldHMACSHA224FilePath = new JTextField();
		textFieldHMACSHA224FilePath.setColumns(10);
		textFieldHMACSHA224FilePath.setBounds(254, 141, 302, 21);
		panelHMACSHA224.add(textFieldHMACSHA224FilePath);

		btnHMACSHA224FilePath = new JButton("Browse...");
		btnHMACSHA224FilePath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFileChooser fileChooseHMACSHA224 = new JFileChooser("e:");
				if (fileChooseHMACSHA224.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					textFieldHMACSHA224FilePath.setText(fileChooseHMACSHA224.getSelectedFile().getPath());
				}
			}
		});
		btnHMACSHA224FilePath.setBounds(566, 140, 93, 23);
		panelHMACSHA224.add(btnHMACSHA224FilePath);

		pwdHMACSHA224Password1 = new JPasswordField();
		pwdHMACSHA224Password1.setBounds(296, 239, 216, 21);
		panelHMACSHA224.add(pwdHMACSHA224Password1);

		JLabel lblHMACSHA224Tip3 = new JLabel("password");
		lblHMACSHA224Tip3.setBounds(202, 242, 84, 15);
		panelHMACSHA224.add(lblHMACSHA224Tip3);

		JLabel lblHMACSHA224Tip4 = new JLabel("password");
		lblHMACSHA224Tip4.setBounds(202, 300, 84, 15);
		panelHMACSHA224.add(lblHMACSHA224Tip4);

		pwdHMACSHA224Password2 = new JPasswordField();
		pwdHMACSHA224Password2.setBounds(296, 297, 216, 21);
		panelHMACSHA224.add(pwdHMACSHA224Password2);

		JButton btnHMACSHA224CalculatorToStr = new JButton("Calculator to str");
		btnHMACSHA224CalculatorToStr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Arrays.equals(pwdHMACSHA224Password1.getPassword(), pwdHMACSHA224Password2.getPassword())
						&& pwdHMACSHA224Password1.getPassword().length != 0) {
					if (rdbtnHMACSHA224FileMAC.isSelected()) {
						try {
							textFieldHMACSHA224Result.setText(MessageAuthentication.generatorFileMACToString(
									"HmacSHA224", new String(pwdHMACSHA224Password1.getPassword()),
									textFieldHMACSHA224FilePath.getText()));
							JOptionPane.showMessageDialog(null, "Generator MAC successfully!");
						} catch (InvalidKeyException e1) {
							JOptionPane.showMessageDialog(null, "InvalidKey!");
						} catch (NoSuchAlgorithmException e1) {
							JOptionPane.showMessageDialog(null, "NoSuchAlgorithm");
						} catch (FileNotFoundException e1) {
							JOptionPane.showMessageDialog(null, "FileNotFound");
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(null, "IOException");
						}
					} else if (rdbtnHMACSHA224StringMAC.isSelected()) {
						try {
							textFieldHMACSHA224Result.setText(MessageAuthentication.generatorStringMAC("HmacSHA224",
									new String(pwdHMACSHA224Password1.getPassword()),
									textFieldHMACSHA224String.getText()));
							JOptionPane.showMessageDialog(null, "Generator MAC successfully!");
						} catch (InvalidKeyException e1) {
							JOptionPane.showMessageDialog(null, "InvalidKey!");
						} catch (NoSuchAlgorithmException e1) {
							JOptionPane.showMessageDialog(null, "NoSuchAlgorithm");
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Password is't identified or is null!");
				}

			}
		});
		btnHMACSHA224CalculatorToStr.setBounds(60, 391, 142, 23);
		panelHMACSHA224.add(btnHMACSHA224CalculatorToStr);

		JButton btnHMACSHA224Clear = new JButton("Clear");
		btnHMACSHA224Clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldHMACSHA224Result.setText(null);
				textFieldHMACSHA224FilePath.setText(null);
				textFieldHMACSHA224String.setText(null);
				textFieldHMACSHA224ToFilePath.setText(null);
				pwdHMACSHA224Password1.setText(null);
				pwdHMACSHA224Password2.setText(null);
			}
		});
		btnHMACSHA224Clear.setBounds(646, 472, 93, 23);
		panelHMACSHA224.add(btnHMACSHA224Clear);

		JLabel lblHMACSHA224Tip1 = new JLabel("Please choose mac style:");
		lblHMACSHA224Tip1.setBounds(28, 32, 230, 15);
		panelHMACSHA224.add(lblHMACSHA224Tip1);

		JLabel lblHMACSHA224Tip2 = new JLabel("Please input identified password twice:");
		lblHMACSHA224Tip2.setBounds(28, 197, 249, 15);
		panelHMACSHA224.add(lblHMACSHA224Tip2);

		JButton btnHMACSHA224CalculatorToFile = new JButton("Calculator to file");
		btnHMACSHA224CalculatorToFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (Arrays.equals(pwdHMACSHA224Password1.getPassword(), pwdHMACSHA224Password2.getPassword())
						&& pwdHMACSHA224Password1.getPassword().length != 0) {
					if (rdbtnHMACSHA224FileMAC.isSelected()) {
						try {
							MessageAuthentication.generatorFileMACToFile("HmacSHA224",
									new String(pwdHMACSHA224Password2.getPassword()),
									textFieldHMACSHA224FilePath.getText(), textFieldHMACSHA224ToFilePath.getText());
							JOptionPane.showMessageDialog(null, "Generator MAC successfully!");
						} catch (InvalidKeyException e1) {
							JOptionPane.showMessageDialog(null, "InvalidKey!");
						} catch (HeadlessException e1) {
							JOptionPane.showMessageDialog(null, "Headless!");
						} catch (NoSuchAlgorithmException e1) {
							JOptionPane.showMessageDialog(null, "NoSuchAlgorithm!");
						} catch (FileNotFoundException e1) {
							JOptionPane.showMessageDialog(null, "FileNotFound!");
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(null, "IOException!");
						}

					} else if (rdbtnHMACSHA224StringMAC.isSelected()) {
						try {
							MessageAuthentication.generatorStringMACToFile("HmacSHA224",
									new String(pwdHMACSHA224Password2.getPassword()),
									textFieldHMACSHA224String.getText(), textFieldHMACSHA224ToFilePath.getText());
							JOptionPane.showMessageDialog(null, "Generator MAC successfully!");
						} catch (InvalidKeyException e1) {
							JOptionPane.showMessageDialog(null, "InvalidKey!");
						} catch (HeadlessException e1) {
							JOptionPane.showMessageDialog(null, "Headless!");
						} catch (NoSuchAlgorithmException e1) {
							JOptionPane.showMessageDialog(null, "NoSuchAlgorithm!");
						} catch (FileNotFoundException e1) {
							JOptionPane.showMessageDialog(null, "FileNotFound!");
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(null, "IOException!");
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Password is't identified or is null!");
				}

			}
		});
		btnHMACSHA224CalculatorToFile.setBounds(60, 437, 141, 23);
		panelHMACSHA224.add(btnHMACSHA224CalculatorToFile);

		textFieldHMACSHA224Result = new JTextField();
		textFieldHMACSHA224Result.setColumns(10);
		textFieldHMACSHA224Result.setBounds(212, 392, 527, 21);
		panelHMACSHA224.add(textFieldHMACSHA224Result);

		JLabel lblHMACSHA224Tip5 = new JLabel("Please choose calculator style:");
		lblHMACSHA224Tip5.setBounds(28, 341, 230, 15);
		panelHMACSHA224.add(lblHMACSHA224Tip5);

		textFieldHMACSHA224ToFilePath = new JTextField();
		textFieldHMACSHA224ToFilePath.setColumns(10);
		textFieldHMACSHA224ToFilePath.setBounds(211, 438, 290, 21);
		panelHMACSHA224.add(textFieldHMACSHA224ToFilePath);

		JButton btnHMACSHA224ToFilePath = new JButton("Browse...");
		btnHMACSHA224ToFilePath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooserHMAC224ToFile = new JFileChooser();
				if (fileChooserHMAC224ToFile.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					textFieldHMACSHA224ToFilePath
							.setText(fileChooserHMAC224ToFile.getSelectedFile().getPath() + ".mac");
				}
			}
		});
		btnHMACSHA224ToFilePath.setBounds(511, 437, 93, 23);
		panelHMACSHA224.add(btnHMACSHA224ToFilePath);

		JPanel panelHMACSHA256 = new JPanel();
		panelHMACSHA256.setLayout(null);
		tabbedPaneMAC.addTab("HmacSHA256", null, panelHMACSHA256, null);

		textFieldHMACSHA256String = new JTextField();
		textFieldHMACSHA256String.setColumns(10);
		textFieldHMACSHA256String.setBounds(254, 80, 302, 21);
		panelHMACSHA256.add(textFieldHMACSHA256String);

		rdbtnHMACSHA256StringMAC = new JRadioButton("StringMac");
		rdbtnHMACSHA256StringMAC.setSelected(true);
		rdbtnHMACSHA256StringMAC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnHMACSHA256StringMAC.isSelected()) {
					btnHMACSHA256FilePath.setEnabled(false);
				}

			}
		});
		rdbtnHMACSHA256StringMAC.setBounds(146, 79, 93, 23);
		panelHMACSHA256.add(rdbtnHMACSHA256StringMAC);

		rdbtnHMACSHA256FileMAC = new JRadioButton("FileMac");
		rdbtnHMACSHA256FileMAC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnHMACSHA256FileMAC.isSelected()) {
					btnHMACSHA256FilePath.setEnabled(true);
				}
			}
		});
		rdbtnHMACSHA256FileMAC.setBounds(146, 140, 93, 23);
		panelHMACSHA256.add(rdbtnHMACSHA256FileMAC);
		ButtonGroup btnGroupHMACSHA256 = new ButtonGroup();

		btnGroupHMACSHA256.add(rdbtnHMACSHA256FileMAC);
		btnGroupHMACSHA256.add(rdbtnHMACSHA256StringMAC);
		textFieldHMACSHA256FilePath = new JTextField();
		textFieldHMACSHA256FilePath.setColumns(10);
		textFieldHMACSHA256FilePath.setBounds(254, 141, 302, 21);
		panelHMACSHA256.add(textFieldHMACSHA256FilePath);

		btnHMACSHA256FilePath = new JButton("Browse...");
		btnHMACSHA256FilePath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooseHMACSHA256 = new JFileChooser("e:");
				if (fileChooseHMACSHA256.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					textFieldHMACSHA256FilePath.setText(fileChooseHMACSHA256.getSelectedFile().getPath());
				}
			}
		});
		btnHMACSHA256FilePath.setBounds(566, 140, 93, 23);
		panelHMACSHA256.add(btnHMACSHA256FilePath);

		pwdHMACSHA256Password1 = new JPasswordField();
		pwdHMACSHA256Password1.setBounds(296, 239, 216, 21);
		panelHMACSHA256.add(pwdHMACSHA256Password1);

		JLabel lblHMACSHA256Tip3 = new JLabel("password");
		lblHMACSHA256Tip3.setBounds(202, 242, 84, 15);
		panelHMACSHA256.add(lblHMACSHA256Tip3);

		JLabel lblHMACSHA256Tip4 = new JLabel("password");
		lblHMACSHA256Tip4.setBounds(202, 300, 84, 15);
		panelHMACSHA256.add(lblHMACSHA256Tip4);

		pwdHMACSHA256Password2 = new JPasswordField();
		pwdHMACSHA256Password2.setBounds(296, 297, 216, 21);
		panelHMACSHA256.add(pwdHMACSHA256Password2);

		JButton btnHMACSHA256CalculatorToStr = new JButton("Calculator to str");
		btnHMACSHA256CalculatorToStr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Arrays.equals(pwdHMACSHA256Password1.getPassword(), pwdHMACSHA256Password2.getPassword())
						&& pwdHMACSHA256Password1.getPassword().length != 0) {
					if (rdbtnHMACSHA256FileMAC.isSelected()) {
						try {
							textFieldHMACSHA256Result.setText(MessageAuthentication.generatorFileMACToString(
									"HmacSHA256", new String(pwdHMACSHA256Password1.getPassword()),
									textFieldHMACSHA256FilePath.getText()));
							JOptionPane.showMessageDialog(null, "Generator MAC successfully!");
						} catch (InvalidKeyException e1) {
							JOptionPane.showMessageDialog(null, "InvalidKey!");
						} catch (NoSuchAlgorithmException e1) {
							JOptionPane.showMessageDialog(null, "NoSuchAlgorithm");
						} catch (FileNotFoundException e1) {
							JOptionPane.showMessageDialog(null, "FileNotFound");
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(null, "IOException");
						}
					} else if (rdbtnHMACSHA256StringMAC.isSelected()) {
						try {
							textFieldHMACSHA256Result.setText(MessageAuthentication.generatorStringMAC("HmacSHA256",
									new String(pwdHMACSHA256Password1.getPassword()),
									textFieldHMACSHA256String.getText()));
							JOptionPane.showMessageDialog(null, "Generator MAC successfully!");
						} catch (InvalidKeyException e1) {
							JOptionPane.showMessageDialog(null, "InvalidKey!");
						} catch (NoSuchAlgorithmException e1) {
							JOptionPane.showMessageDialog(null, "NoSuchAlgorithm");
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Password is't identified or is null!");
				}
			}
		});
		btnHMACSHA256CalculatorToStr.setBounds(41, 391, 142, 23);
		panelHMACSHA256.add(btnHMACSHA256CalculatorToStr);

		JButton btnHMACSHA256Clear = new JButton("Clear");
		btnHMACSHA256Clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldHMACSHA256Result.setText(null);
				textFieldHMACSHA256FilePath.setText(null);
				textFieldHMACSHA256String.setText(null);
				textFieldHMACSHA256ToFilePath.setText(null);
				pwdHMACSHA256Password1.setText(null);
				pwdHMACSHA256Password2.setText(null);
			}
		});
		btnHMACSHA256Clear.setBounds(630, 469, 93, 23);
		panelHMACSHA256.add(btnHMACSHA256Clear);

		JLabel lblHMACSHA256Tip1 = new JLabel("Please choose mac style:");
		lblHMACSHA256Tip1.setBounds(28, 32, 211, 15);
		panelHMACSHA256.add(lblHMACSHA256Tip1);

		JLabel lblHMACSHA256Tip2 = new JLabel("Please input identified password twice:");
		lblHMACSHA256Tip2.setBounds(28, 197, 249, 15);
		panelHMACSHA256.add(lblHMACSHA256Tip2);

		JButton btnHMACSHA256CalculatorToFile = new JButton("Calculator to file");
		btnHMACSHA256CalculatorToFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Arrays.equals(pwdHMACSHA256Password1.getPassword(), pwdHMACSHA256Password2.getPassword())
						&& pwdHMACSHA256Password1.getPassword().length != 0) {
					if (rdbtnHMACSHA256FileMAC.isSelected()) {
						try {
							MessageAuthentication.generatorFileMACToFile("HmacSHA256",
									new String(pwdHMACSHA256Password2.getPassword()),
									textFieldHMACSHA256FilePath.getText(), textFieldHMACSHA256ToFilePath.getText());
							JOptionPane.showMessageDialog(null, "Generator MAC successfully!");
						} catch (InvalidKeyException e1) {
							JOptionPane.showMessageDialog(null, "InvalidKey!");
						} catch (HeadlessException e1) {
							JOptionPane.showMessageDialog(null, "Headless!");
						} catch (NoSuchAlgorithmException e1) {
							JOptionPane.showMessageDialog(null, "NoSuchAlgorithm!");
						} catch (FileNotFoundException e1) {
							JOptionPane.showMessageDialog(null, "FileNotFound!");
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(null, "IOException!");
						}

					} else if (rdbtnHMACSHA256StringMAC.isSelected()) {
						try {
							MessageAuthentication.generatorStringMACToFile("HmacSHA256",
									new String(pwdHMACSHA256Password2.getPassword()),
									textFieldHMACSHA256String.getText(), textFieldHMACSHA256ToFilePath.getText());
							JOptionPane.showMessageDialog(null, "Generator MAC successfully!");
						} catch (InvalidKeyException e1) {
							JOptionPane.showMessageDialog(null, "InvalidKey!");
						} catch (HeadlessException e1) {
							JOptionPane.showMessageDialog(null, "Headless!");
						} catch (NoSuchAlgorithmException e1) {
							JOptionPane.showMessageDialog(null, "NoSuchAlgorithm!");
						} catch (FileNotFoundException e1) {
							JOptionPane.showMessageDialog(null, "FileNotFound!");
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(null, "IOException!");
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Password is't identified or is null!");
				}
			}
		});
		btnHMACSHA256CalculatorToFile.setBounds(41, 437, 141, 23);
		panelHMACSHA256.add(btnHMACSHA256CalculatorToFile);

		textFieldHMACSHA256Result = new JTextField();
		textFieldHMACSHA256Result.setColumns(10);
		textFieldHMACSHA256Result.setBounds(202, 392, 521, 21);
		panelHMACSHA256.add(textFieldHMACSHA256Result);

		JLabel lblHMACSHA256Tip5 = new JLabel("Please choose calculator style:");
		lblHMACSHA256Tip5.setBounds(28, 341, 230, 15);
		panelHMACSHA256.add(lblHMACSHA256Tip5);

		textFieldHMACSHA256ToFilePath = new JTextField();
		textFieldHMACSHA256ToFilePath.setColumns(10);
		textFieldHMACSHA256ToFilePath.setBounds(202, 438, 290, 21);
		panelHMACSHA256.add(textFieldHMACSHA256ToFilePath);

		JButton btnHMACSHA256ToFilePath = new JButton("Browse...");
		btnHMACSHA256ToFilePath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooserHMAC256ToFile = new JFileChooser();
				if (fileChooserHMAC256ToFile.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					textFieldHMACSHA256ToFilePath
							.setText(fileChooserHMAC256ToFile.getSelectedFile().getPath() + ".mac");
				}
			}
		});
		btnHMACSHA256ToFilePath.setBounds(502, 437, 93, 23);
		panelHMACSHA256.add(btnHMACSHA256ToFilePath);

		JPanel panelHMACSHA384 = new JPanel();
		panelHMACSHA384.setLayout(null);
		tabbedPaneMAC.addTab("HmacSHA384", null, panelHMACSHA384, null);

		textFieldHMACSHA384String = new JTextField();
		textFieldHMACSHA384String.setColumns(10);
		textFieldHMACSHA384String.setBounds(254, 80, 302, 21);
		panelHMACSHA384.add(textFieldHMACSHA384String);

		rdbtnHMACSHA384StringMAC = new JRadioButton("StringMac");
		rdbtnHMACSHA384StringMAC.setSelected(true);
		rdbtnHMACSHA384StringMAC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnHMACSHA384StringMAC.isSelected()) {
					btnHMACSHA384FilePath.setEnabled(false);
				}
			}
		});
		rdbtnHMACSHA384StringMAC.setBounds(146, 79, 93, 23);
		panelHMACSHA384.add(rdbtnHMACSHA384StringMAC);

		rdbtnHMACSHA384FileMAC = new JRadioButton("FileMac");
		rdbtnHMACSHA384FileMAC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnHMACSHA384FileMAC.isSelected()) {
					btnHMACSHA384FilePath.setEnabled(false);
				}
			}
		});
		rdbtnHMACSHA384FileMAC.setBounds(146, 140, 93, 23);
		panelHMACSHA384.add(rdbtnHMACSHA384FileMAC);
		ButtonGroup btnGroupHMACSHA384 = new ButtonGroup();
		btnGroupHMACSHA384.add(rdbtnHMACSHA384FileMAC);
		btnGroupHMACSHA384.add(rdbtnHMACSHA384StringMAC);
		textFieldHMACSHA384FilePath = new JTextField();
		textFieldHMACSHA384FilePath.setColumns(10);
		textFieldHMACSHA384FilePath.setBounds(254, 141, 302, 21);
		panelHMACSHA384.add(textFieldHMACSHA384FilePath);

		btnHMACSHA384FilePath = new JButton("Browse...");
		btnHMACSHA384FilePath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooseHMACSHA384 = new JFileChooser("e:");
				if (fileChooseHMACSHA384.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					textFieldHMACSHA384FilePath.setText(fileChooseHMACSHA384.getSelectedFile().getPath());
				}
			}
		});
		btnHMACSHA384FilePath.setBounds(566, 140, 93, 23);
		panelHMACSHA384.add(btnHMACSHA384FilePath);

		pwdHMACSHA384Password1 = new JPasswordField();
		pwdHMACSHA384Password1.setBounds(296, 239, 216, 21);
		panelHMACSHA384.add(pwdHMACSHA384Password1);

		JLabel lblHMACSHA384Tip3 = new JLabel("password");
		lblHMACSHA384Tip3.setBounds(202, 242, 84, 15);
		panelHMACSHA384.add(lblHMACSHA384Tip3);

		JLabel lblHMACSHA384Tip4 = new JLabel("password");
		lblHMACSHA384Tip4.setBounds(202, 300, 84, 15);
		panelHMACSHA384.add(lblHMACSHA384Tip4);

		pwdHMACSHA384Password2 = new JPasswordField();
		pwdHMACSHA384Password2.setBounds(296, 297, 216, 21);
		panelHMACSHA384.add(pwdHMACSHA384Password2);

		JButton btnHMACSHA384CalculatorToStr = new JButton("Calculator to str");
		btnHMACSHA384CalculatorToStr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Arrays.equals(pwdHMACSHA384Password1.getPassword(), pwdHMACSHA384Password2.getPassword())
						&& pwdHMACSHA384Password1.getPassword().length != 0) {
					if (rdbtnHMACSHA384FileMAC.isSelected()) {
						try {
							textFieldHMACSHA384Result.setText(MessageAuthentication.generatorFileMACToString(
									"HmacSHA384", new String(pwdHMACSHA384Password1.getPassword()),
									textFieldHMACSHA384FilePath.getText()));
							JOptionPane.showMessageDialog(null, "Generator MAC successfully!");
						} catch (InvalidKeyException e1) {
							JOptionPane.showMessageDialog(null, "InvalidKey!");
						} catch (NoSuchAlgorithmException e1) {
							JOptionPane.showMessageDialog(null, "NoSuchAlgorithm");
						} catch (FileNotFoundException e1) {
							JOptionPane.showMessageDialog(null, "FileNotFound");
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(null, "IOException");
						}
					} else if (rdbtnHMACSHA384StringMAC.isSelected()) {
						try {
							textFieldHMACSHA384Result.setText(MessageAuthentication.generatorStringMAC("HmacSHA384",
									new String(pwdHMACSHA384Password1.getPassword()),
									textFieldHMACSHA384String.getText()));
							JOptionPane.showMessageDialog(null, "Generator MAC successfully!");
						} catch (InvalidKeyException e1) {
							JOptionPane.showMessageDialog(null, "InvalidKey!");
						} catch (NoSuchAlgorithmException e1) {
							JOptionPane.showMessageDialog(null, "NoSuchAlgorithm");
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Password is't identified or is null!");
				}

			}
		});
		btnHMACSHA384CalculatorToStr.setBounds(30, 391, 142, 23);
		panelHMACSHA384.add(btnHMACSHA384CalculatorToStr);

		JButton btnHMACSHA384Clear = new JButton("Clear");
		btnHMACSHA384Clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldHMACSHA384Result.setText(null);
				textFieldHMACSHA384FilePath.setText(null);
				textFieldHMACSHA384String.setText(null);
				textFieldHMACSHA384ToFilePath.setText(null);
				pwdHMACSHA384Password1.setText(null);
				pwdHMACSHA384Password2.setText(null);
			}
		});
		btnHMACSHA384Clear.setBounds(631, 469, 93, 23);
		panelHMACSHA384.add(btnHMACSHA384Clear);

		JLabel lblHMACSHA384Tip1 = new JLabel("Please choose mac style:");
		lblHMACSHA384Tip1.setBounds(28, 32, 211, 15);
		panelHMACSHA384.add(lblHMACSHA384Tip1);

		JLabel lblHMACSHA384Tip2 = new JLabel("Please input identified password twice:");
		lblHMACSHA384Tip2.setBounds(28, 197, 249, 15);
		panelHMACSHA384.add(lblHMACSHA384Tip2);

		JButton btnHMACSHA384CalculatorToFile = new JButton("Calculator to file");
		btnHMACSHA384CalculatorToFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Arrays.equals(pwdHMACSHA384Password1.getPassword(), pwdHMACSHA384Password2.getPassword())
						&& pwdHMACSHA384Password1.getPassword().length != 0) {
					if (rdbtnHMACSHA384FileMAC.isSelected()) {
						try {
							MessageAuthentication.generatorFileMACToFile("HmacSHA384",
									new String(pwdHMACSHA384Password2.getPassword()),
									textFieldHMACSHA384FilePath.getText(), textFieldHMACSHA384ToFilePath.getText());
							JOptionPane.showMessageDialog(null, "Generator MAC successfully!");
						} catch (InvalidKeyException e1) {
							JOptionPane.showMessageDialog(null, "InvalidKey!");
						} catch (HeadlessException e1) {
							JOptionPane.showMessageDialog(null, "Headless!");
						} catch (NoSuchAlgorithmException e1) {
							JOptionPane.showMessageDialog(null, "NoSuchAlgorithm!");
						} catch (FileNotFoundException e1) {
							JOptionPane.showMessageDialog(null, "FileNotFound!");
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(null, "IOException!");
						}

					} else if (rdbtnHMACSHA384StringMAC.isSelected()) {
						try {
							MessageAuthentication.generatorStringMACToFile("HmacSHA384",
									new String(pwdHMACSHA384Password2.getPassword()),
									textFieldHMACSHA384String.getText(), textFieldHMACSHA384ToFilePath.getText());
							JOptionPane.showMessageDialog(null, "Generator MAC successfully!");
						} catch (InvalidKeyException e1) {
							JOptionPane.showMessageDialog(null, "InvalidKey!");
						} catch (HeadlessException e1) {
							JOptionPane.showMessageDialog(null, "Headless!");
						} catch (NoSuchAlgorithmException e1) {
							JOptionPane.showMessageDialog(null, "NoSuchAlgorithm!");
						} catch (FileNotFoundException e1) {
							JOptionPane.showMessageDialog(null, "FileNotFound!");
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(null, "IOException!");
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Password is't identified or is null!");
				}

			}
		});
		btnHMACSHA384CalculatorToFile.setBounds(31, 437, 141, 23);
		panelHMACSHA384.add(btnHMACSHA384CalculatorToFile);

		textFieldHMACSHA384Result = new JTextField();
		textFieldHMACSHA384Result.setColumns(10);
		textFieldHMACSHA384Result.setBounds(202, 392, 522, 21);
		panelHMACSHA384.add(textFieldHMACSHA384Result);

		JLabel lblHMACSHA384Tip5 = new JLabel("Please choose calculator style:");
		lblHMACSHA384Tip5.setBounds(28, 341, 230, 15);
		panelHMACSHA384.add(lblHMACSHA384Tip5);

		textFieldHMACSHA384ToFilePath = new JTextField();
		textFieldHMACSHA384ToFilePath.setColumns(10);
		textFieldHMACSHA384ToFilePath.setBounds(202, 438, 290, 21);
		panelHMACSHA384.add(textFieldHMACSHA384ToFilePath);

		JButton btnHMACSHA384ToFilePath = new JButton("Browse...");
		btnHMACSHA384ToFilePath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooserHMAC384ToFile = new JFileChooser();
				if (fileChooserHMAC384ToFile.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					textFieldHMACSHA384ToFilePath
							.setText(fileChooserHMAC384ToFile.getSelectedFile().getPath() + ".mac");
				}
			}
		});
		btnHMACSHA384ToFilePath.setBounds(502, 437, 93, 23);
		panelHMACSHA384.add(btnHMACSHA384ToFilePath);

		JPanel panelHMACSHA512 = new JPanel();
		panelHMACSHA512.setLayout(null);
		tabbedPaneMAC.addTab("HmacSHA512", null, panelHMACSHA512, null);

		textFieldHMACSHA512String = new JTextField();
		textFieldHMACSHA512String.setColumns(10);
		textFieldHMACSHA512String.setBounds(254, 80, 302, 21);
		panelHMACSHA512.add(textFieldHMACSHA512String);

		rdbtnHMACSHA512StringMAC = new JRadioButton("StringMac");
		rdbtnHMACSHA512StringMAC.setSelected(true);
		rdbtnHMACSHA512StringMAC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnHMACSHA512StringMAC.isSelected()) {
					btnHMACSHA512FilePath.setEnabled(false);
				}
			}
		});
		rdbtnHMACSHA512StringMAC.setBounds(146, 79, 93, 23);
		panelHMACSHA512.add(rdbtnHMACSHA512StringMAC);

		rdbtnHMACSHA512FileMAC = new JRadioButton("FileMac");
		rdbtnHMACSHA512FileMAC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnHMACSHA512FileMAC.isSelected()) {
					btnHMACSHA512FilePath.setEnabled(true);
				}
			}
		});
		rdbtnHMACSHA512FileMAC.setBounds(146, 140, 93, 23);
		panelHMACSHA512.add(rdbtnHMACSHA512FileMAC);
		ButtonGroup btnGroupHMACSHA512 = new ButtonGroup();
		btnGroupHMACSHA512.add(rdbtnHMACSHA512StringMAC);
		btnGroupHMACSHA512.add(rdbtnHMACSHA512FileMAC);
		textFieldHMACSHA512FilePath = new JTextField();
		textFieldHMACSHA512FilePath.setColumns(10);
		textFieldHMACSHA512FilePath.setBounds(254, 141, 302, 21);
		panelHMACSHA512.add(textFieldHMACSHA512FilePath);

		btnHMACSHA512FilePath = new JButton("Browse...");
		btnHMACSHA512FilePath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooseHMACSHA512 = new JFileChooser("e:");
				if (fileChooseHMACSHA512.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					textFieldHMACSHA512FilePath.setText(fileChooseHMACSHA512.getSelectedFile().getPath());
				}
			}
		});
		btnHMACSHA512FilePath.setBounds(566, 140, 93, 23);
		panelHMACSHA512.add(btnHMACSHA512FilePath);

		pwdHMACSHA512Password1 = new JPasswordField();
		pwdHMACSHA512Password1.setBounds(296, 239, 216, 21);
		panelHMACSHA512.add(pwdHMACSHA512Password1);

		JLabel lblHMACSHA512Tip3 = new JLabel("password");
		lblHMACSHA512Tip3.setBounds(202, 242, 84, 15);
		panelHMACSHA512.add(lblHMACSHA512Tip3);

		JLabel lblHMACSHA512Tip4 = new JLabel("password");
		lblHMACSHA512Tip4.setBounds(202, 300, 84, 15);
		panelHMACSHA512.add(lblHMACSHA512Tip4);

		pwdHMACSHA512Password2 = new JPasswordField();
		pwdHMACSHA512Password2.setBounds(296, 297, 216, 21);
		panelHMACSHA512.add(pwdHMACSHA512Password2);

		JButton btnHMACSHA512CalculatorToStr = new JButton("Calculator to str");
		btnHMACSHA512CalculatorToStr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Arrays.equals(pwdHMACSHA512Password1.getPassword(), pwdHMACSHA512Password2.getPassword())
						&& pwdHMACSHA512Password1.getPassword().length != 0) {
					if (rdbtnHMACSHA512FileMAC.isSelected()) {
						try {
							textFieldHMACSHA512Result.setText(MessageAuthentication.generatorFileMACToString(
									"HmacSHA512", new String(pwdHMACSHA512Password1.getPassword()),
									textFieldHMACSHA512FilePath.getText()));
							JOptionPane.showMessageDialog(null, "Generator MAC successfully!");
						} catch (InvalidKeyException e1) {
							JOptionPane.showMessageDialog(null, "InvalidKey!");
						} catch (NoSuchAlgorithmException e1) {
							JOptionPane.showMessageDialog(null, "NoSuchAlgorithm");
						} catch (FileNotFoundException e1) {
							JOptionPane.showMessageDialog(null, "FileNotFound");
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(null, "IOException");
						}
					} else if (rdbtnHMACSHA512StringMAC.isSelected()) {
						try {
							textFieldHMACSHA512Result.setText(MessageAuthentication.generatorStringMAC("HmacSHA512",
									new String(pwdHMACSHA512Password1.getPassword()),
									textFieldHMACSHA512String.getText()));
							JOptionPane.showMessageDialog(null, "Generator MAC successfully!");
						} catch (InvalidKeyException e1) {
							JOptionPane.showMessageDialog(null, "InvalidKey!");
						} catch (NoSuchAlgorithmException e1) {
							JOptionPane.showMessageDialog(null, "NoSuchAlgorithm");
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Password is't identified or is null!");
				}

			}
		});
		btnHMACSHA512CalculatorToStr.setBounds(53, 391, 142, 23);
		panelHMACSHA512.add(btnHMACSHA512CalculatorToStr);

		JButton btnHMACSHA512Clear = new JButton("Clear");
		btnHMACSHA512Clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldHMACSHA512Result.setText(null);
				textFieldHMACSHA512FilePath.setText(null);
				textFieldHMACSHA512String.setText(null);
				textFieldHMACSHA512ToFilePath.setText(null);
				pwdHMACSHA512Password1.setText(null);
				pwdHMACSHA512Password2.setText(null);
			}
		});
		btnHMACSHA512Clear.setBounds(646, 471, 93, 23);
		panelHMACSHA512.add(btnHMACSHA512Clear);

		JLabel lblHMACSHA512Tip1 = new JLabel("Please choose mac style:");
		lblHMACSHA512Tip1.setBounds(28, 32, 211, 15);
		panelHMACSHA512.add(lblHMACSHA512Tip1);

		JLabel lblHMACSHA512Tip2 = new JLabel("Please input identified password twice:");
		lblHMACSHA512Tip2.setBounds(28, 197, 249, 15);
		panelHMACSHA512.add(lblHMACSHA512Tip2);

		JButton btnHMACSHA512CalculatorToFile = new JButton("Calculator to file");
		btnHMACSHA512CalculatorToFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Arrays.equals(pwdHMACSHA512Password1.getPassword(), pwdHMACSHA512Password2.getPassword())
						&& pwdHMACSHA512Password1.getPassword().length != 0) {
					if (rdbtnHMACSHA512FileMAC.isSelected()) {
						try {
							MessageAuthentication.generatorFileMACToFile("HmacSHA512",
									new String(pwdHMACSHA512Password2.getPassword()),
									textFieldHMACSHA512FilePath.getText(), textFieldHMACSHA512ToFilePath.getText());
							JOptionPane.showMessageDialog(null, "Generator MAC successfully!");
						} catch (InvalidKeyException e1) {
							JOptionPane.showMessageDialog(null, "InvalidKey!");
						} catch (HeadlessException e1) {
							JOptionPane.showMessageDialog(null, "Headless!");
						} catch (NoSuchAlgorithmException e1) {
							JOptionPane.showMessageDialog(null, "NoSuchAlgorithm!");
						} catch (FileNotFoundException e1) {
							JOptionPane.showMessageDialog(null, "FileNotFound!");
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(null, "IOException!");
						}

					} else if (rdbtnHMACSHA512StringMAC.isSelected()) {
						try {
							MessageAuthentication.generatorStringMACToFile("HmacSHA512",
									new String(pwdHMACSHA512Password2.getPassword()),
									textFieldHMACSHA512String.getText(), textFieldHMACSHA512ToFilePath.getText());
							JOptionPane.showMessageDialog(null, "Generator MAC successfully!");
						} catch (InvalidKeyException e1) {
							JOptionPane.showMessageDialog(null, "InvalidKey!");
						} catch (HeadlessException e1) {
							JOptionPane.showMessageDialog(null, "Headless!");
						} catch (NoSuchAlgorithmException e1) {
							JOptionPane.showMessageDialog(null, "NoSuchAlgorithm!");
						} catch (FileNotFoundException e1) {
							JOptionPane.showMessageDialog(null, "FileNotFound!");
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(null, "IOException!");
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Password is't identified or is null!");
				}

			}
		});
		btnHMACSHA512CalculatorToFile.setBounds(53, 437, 141, 23);
		panelHMACSHA512.add(btnHMACSHA512CalculatorToFile);

		textFieldHMACSHA512Result = new JTextField();
		textFieldHMACSHA512Result.setColumns(10);
		textFieldHMACSHA512Result.setBounds(217, 392, 522, 21);
		panelHMACSHA512.add(textFieldHMACSHA512Result);

		JLabel lblHMACSHA512Tip5 = new JLabel("Please choose calculator style:");
		lblHMACSHA512Tip5.setBounds(28, 341, 230, 15);
		panelHMACSHA512.add(lblHMACSHA512Tip5);

		textFieldHMACSHA512ToFilePath = new JTextField();
		textFieldHMACSHA512ToFilePath.setColumns(10);
		textFieldHMACSHA512ToFilePath.setBounds(217, 438, 290, 21);
		panelHMACSHA512.add(textFieldHMACSHA512ToFilePath);

		JButton btnHMACSHA512ToFilePath = new JButton("Browse...");
		btnHMACSHA512ToFilePath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooserHMAC512ToFile = new JFileChooser();
				if (fileChooserHMAC512ToFile.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					textFieldHMACSHA512ToFilePath
							.setText(fileChooserHMAC512ToFile.getSelectedFile().getPath() + ".mac");
				}
			}
		});
		btnHMACSHA512ToFilePath.setBounds(541, 437, 93, 23);
		panelHMACSHA512.add(btnHMACSHA512ToFilePath);
		
		JPanel panelMACVertify = new JPanel();
		tabbedPaneMAC.addTab("MACVertify", null, panelMACVertify, null);
		panelMACVertify.setLayout(null);
		
		textFieldMACVertifyFilePath = new JTextField();
		textFieldMACVertifyFilePath.setBounds(192, 98, 314, 21);
		panelMACVertify.add(textFieldMACVertifyFilePath);
		textFieldMACVertifyFilePath.setColumns(10);
		
		textFieldMACVertifyMACFilePath = new JTextField();
		textFieldMACVertifyMACFilePath.setBounds(192, 182, 313, 21);
		panelMACVertify.add(textFieldMACVertifyMACFilePath);
		textFieldMACVertifyMACFilePath.setColumns(10);
		
		JButton btnMACVertifyFilePath = new JButton("Browse...");
		btnMACVertifyFilePath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooseMACVertify = new JFileChooser("e:");
				if (fileChooseMACVertify.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					textFieldMACVertifyFilePath.setText(fileChooseMACVertify.getSelectedFile().getPath());
				}
			
			}
		});
		btnMACVertifyFilePath.setBounds(565, 98, 93, 23);
		panelMACVertify.add(btnMACVertifyFilePath);
		
		JButton btnMACVertifyMACFilePath = new JButton("Browse...");
		btnMACVertifyMACFilePath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooserMACVertifyMacFilePath = new JFileChooser();
				fileChooserMACVertifyMacFilePath.setFileFilter(new FileFilter() {
					@Override
					public String getDescription() {
						return "显示类型(*.mac)";
					}

					@Override
					public boolean accept(File f) {
						if (f.getName().endsWith(".mac") || f.isDirectory()) {
							return true;
						}
						return false;
					}
				});
				if (fileChooserMACVertifyMacFilePath.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					textFieldMACVertifyMACFilePath.setText(fileChooserMACVertifyMacFilePath.getSelectedFile().getPath());			
				}
			}
		});
		btnMACVertifyMACFilePath.setBounds(565, 181, 93, 23);
		panelMACVertify.add(btnMACVertifyMACFilePath);
		
		JLabel lblMACVertifyTip1 = new JLabel("Please choose file path:");
		lblMACVertifyTip1.setBounds(36, 49, 179, 15);
		panelMACVertify.add(lblMACVertifyTip1);
		
		JLabel lblMACVertifyTip3 = new JLabel("Password");
		lblMACVertifyTip3.setBounds(192, 327, 81, 15);
		panelMACVertify.add(lblMACVertifyTip3);
		
		JLabel lblMACVertifyTip2 = new JLabel("Please input password:");
		lblMACVertifyTip2.setBounds(36, 260, 167, 15);
		panelMACVertify.add(lblMACVertifyTip2);
		
		pwdMACVertifyPassword = new JPasswordField();
		pwdMACVertifyPassword.setBounds(283, 324, 192, 21);
		panelMACVertify.add(pwdMACVertifyPassword);
		
		JButton btnMACVertifyClear = new JButton("Clear");
		btnMACVertifyClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldMACVertifyFilePath.setText(null);
				textFieldMACVertifyMACFilePath.setText(null);
				textFieldMACVertifyMACResult.setText(null);
				pwdMACVertifyPassword.setText(null);
			}
		});
		btnMACVertifyClear.setBounds(565, 434, 93, 23);
		panelMACVertify.add(btnMACVertifyClear);
		
		textFieldMACVertifyMACResult = new JTextField();
		textFieldMACVertifyMACResult.setEditable(false);
		textFieldMACVertifyMACResult.setBounds(228, 435, 123, 21);
		panelMACVertify.add(textFieldMACVertifyMACResult);
		textFieldMACVertifyMACResult.setColumns(10);
		
		JButton btnMACVertify = new JButton("MACVertify");
		btnMACVertify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (pwdMACVertifyPassword.getPassword().length!=0) {
					try {
						if(
								MessageAuthentication.vertifyMac(
										textFieldMACVertifyFilePath.getText(),
										textFieldMACVertifyMACFilePath.getText(), 
										pwdMACVertifyPassword.getPassword()
										)
								){
							textFieldMACVertifyMACResult.setText("Vertify successfully!");
						}else{
							textFieldMACVertifyMACResult.setText("Vertify failure!");
						}
					} catch (InvalidKeyException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e1.toString());
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e1.toString());
					} catch (NoSuchAlgorithmException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e1.toString());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e1.toString());
					}
				}else {
					JOptionPane.showMessageDialog(null, "Password is null");
				}
			}
		});
		btnMACVertify.setBounds(89, 434, 126, 23);
		panelMACVertify.add(btnMACVertify);

		JLabel lblCorporation = new JLabel("Development by ZhouMinghua");
		lblCorporation.setBounds(5, 584, 249, 26);
		contentPane.add(lblCorporation);
	}
}
