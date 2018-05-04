import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.omg.CORBA.StringHolder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.awt.event.ActionEvent;

public class ClientRegister extends JFrame {

	private JPanel contentPane;
	private JLabel label;
	private JTextField textFieldUserName;
	private JTextField textFieldTelePhone;
	private JPasswordField pwd1;
	private JPasswordField pwdConfrim;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;

	public ClientRegister(ObjectInputStream ois,ObjectOutputStream oos) {
		this.ois = ois;
		this.oos = oos;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		label = new JLabel("\u7528\u6237\u6CE8\u518C");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("宋体", Font.PLAIN, 50));
		contentPane.add(label, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblUserName = new JLabel("\u7528\u6237\u540D");
		lblUserName.setBounds(84, 16, 54, 15);
		panel.add(lblUserName);
		
		textFieldUserName = new JTextField();
		textFieldUserName.setBounds(164, 13, 105, 21);
		panel.add(textFieldUserName);
		textFieldUserName.setColumns(10);
		
		JLabel label_1 = new JLabel("\u7535\u8BDD");
		label_1.setBounds(84, 109, 54, 15);
		panel.add(label_1);
		
		textFieldTelePhone = new JTextField();
		textFieldTelePhone.setBounds(164, 106, 105, 21);
		textFieldTelePhone.setColumns(10);
		panel.add(textFieldTelePhone);
		
		JLabel label_2 = new JLabel("\u5BC6\u7801");
		label_2.setBounds(84, 47, 54, 15);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("\u786E\u8BA4\u5BC6\u7801");
		label_3.setBounds(84, 72, 54, 15);
		panel.add(label_3);
		
		pwd1 = new JPasswordField();
		pwd1.setBounds(164, 44, 105, 21);
		panel.add(pwd1);
		
		pwdConfrim = new JPasswordField();
		pwdConfrim.setBounds(164, 69, 105, 21);
		panel.add(pwdConfrim);
		
		JButton btnNewButton = new JButton("\u786E\u8BA4");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userRegister();
			}
		});
		btnNewButton.setBounds(45, 147, 93, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u53D6\u6D88");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClientRegister.this.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(281, 147, 93, 23);
		panel.add(btnNewButton_1);
	}
	public boolean userRegister() {
		String userName = textFieldUserName.getText().trim();
		String password =null;
		if (!new String(pwd1.getPassword()).equals(new String(pwdConfrim.getPassword()))) {
			JOptionPane.showInternalMessageDialog(null, "密码不一致,请重新输入！");
			return false;
		}else{
			RegistryMessage rMessage = new RegistryMessage(userName, new String(pwd1.getPassword()), textFieldTelePhone.getText().trim());
			try {
				synchronized (oos) {
					oos.writeObject(rMessage);
					oos.flush();
				}
				RegistryMessage msg = (RegistryMessage) ois.readObject();
				if (msg.isRegistrySucccess()) {
					JOptionPane.showMessageDialog(null, "注册成功！");
					ClientRegister.this.setVisible(false);
				}else {
					JOptionPane.showMessageDialog(null, "注册失败，请重写用户名！");
				}
			} catch (IOException | ClassNotFoundException e) {
				System.err.println("注册用户时IO异常");
			}
			return true;
		}	
		
	}
}
