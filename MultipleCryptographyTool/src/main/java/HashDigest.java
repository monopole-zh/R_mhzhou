import java.io.FileInputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.Security;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

public class HashDigest {
	public static void MD5MessageDigest(byte[] data, JTextField jTextField) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			jTextField.setText(Hex.toHexString(messageDigest.digest(data)));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}

	public static void SHA_1MessageDigest(byte[] data, JTextField jTextField) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
			jTextField.setText(Hex.toHexString(messageDigest.digest(data)));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}

	public static void SHA_224MessageDigest(byte[] data, JTextField jTextField) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-224");
			jTextField.setText(Hex.toHexString(messageDigest.digest(data)));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}

	public static void SHA_256MessageDigest(byte[] data, JTextField jTextField) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			jTextField.setText(Hex.toHexString(messageDigest.digest(data)));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}

	public static void SHA_384MessageDigest(byte[] data, JTextField jTextField) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-384");
			jTextField.setText(Hex.toHexString(messageDigest.digest(data)));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}

	public static void SHA_512MessageDigest(byte[] data, JTextField jTextField) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
			jTextField.setText(Hex.toHexString(messageDigest.digest(data)));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}

	public static void SHA3_224MessageDigest(byte[] data, JTextField jTextField) {
		try {
			Security.addProvider(new BouncyCastleProvider());
			MessageDigest messageDigest = MessageDigest.getInstance("SHA3-224");
			jTextField.setText(Hex.toHexString(messageDigest.digest(data)));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}

	public static void SHA3_256MessageDigest(byte[] data, JTextField jTextField) {
		try {
			Security.addProvider(new BouncyCastleProvider());
			MessageDigest messageDigest = MessageDigest.getInstance("SHA3-256");
			jTextField.setText(Hex.toHexString(messageDigest.digest(data)));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}

	public static void SHA3_384MessageDigest(byte[] data, JTextField jTextField) {
		try {
			Security.addProvider(new BouncyCastleProvider());
			MessageDigest messageDigest = MessageDigest.getInstance("SHA3-384");
			jTextField.setText(Hex.toHexString(messageDigest.digest(data)));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void SHA3_512MessageDigest(byte[] data, JTextField jTextField) {
		try {
			Security.addProvider(new BouncyCastleProvider());
			MessageDigest messageDigest = MessageDigest.getInstance("SHA3-512");
			jTextField.setText(Hex.toHexString(messageDigest.digest(data)));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}

	public static void MD5FileDigest(String fileName, JTextField jTextField) {
		try {
			FileInputStream fileInputStream = new FileInputStream(fileName);
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			DigestInputStream dInputStream = new DigestInputStream(fileInputStream, messageDigest);
			byte[] buffer = new byte[4096];
			while (dInputStream.read(buffer) != -1)
				;
			jTextField.setText(Hex.toHexString(dInputStream.getMessageDigest().digest()));
			dInputStream.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}

	public static void SHA_1FileDigest(String fileNme, JTextField jTextField) {
		try {
			FileInputStream fileInputStream = new FileInputStream(fileNme);
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
			DigestInputStream dInputStream = new DigestInputStream(fileInputStream, messageDigest);
			byte[] buffer = new byte[4096];
			while (dInputStream.read(buffer) != -1)
				;
			jTextField.setText(Hex.toHexString(dInputStream.getMessageDigest().digest()));
			dInputStream.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}

	public static void SHA_224FileDigest(String fileNme, JTextField jTextField) {
		try {
			FileInputStream fileInputStream = new FileInputStream(fileNme);
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-224");
			DigestInputStream dInputStream = new DigestInputStream(fileInputStream, messageDigest);
			byte[] buffer = new byte[4096];
			while (dInputStream.read(buffer) != -1)
				;
			jTextField.setText(Hex.toHexString(dInputStream.getMessageDigest().digest()));
			dInputStream.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}

	public static void SHA_256FileDigest(String fileNme, JTextField jTextField) {
		try {
			FileInputStream fileInputStream = new FileInputStream(fileNme);
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			DigestInputStream dInputStream = new DigestInputStream(fileInputStream, messageDigest);
			byte[] buffer = new byte[4096];
			while (dInputStream.read(buffer) != -1)
				;
			jTextField.setText(Hex.toHexString(dInputStream.getMessageDigest().digest()));
			dInputStream.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}

	public static void SHA_384FileDigest(String fileNme, JTextField jTextField) {
		try {
			FileInputStream fileInputStream = new FileInputStream(fileNme);
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-384");
			DigestInputStream dInputStream = new DigestInputStream(fileInputStream, messageDigest);
			byte[] buffer = new byte[4096];
			while (dInputStream.read(buffer) != -1)
				;
			jTextField.setText(Hex.toHexString(dInputStream.getMessageDigest().digest()));
			dInputStream.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}

	public static void SHA_512FileDigest(String fileNme, JTextField jTextField) {
		try {
			FileInputStream fileInputStream = new FileInputStream(fileNme);
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
			DigestInputStream dInputStream = new DigestInputStream(fileInputStream, messageDigest);
			byte[] buffer = new byte[4096];
			while (dInputStream.read(buffer) != -1)
				;
			jTextField.setText(Hex.toHexString(dInputStream.getMessageDigest().digest()));
			dInputStream.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}

	public static void SHA3_224FileDigest(String fileNme, JTextField jTextField) {
		try {
			Security.addProvider(new BouncyCastleProvider());
			FileInputStream fileInputStream = new FileInputStream(fileNme);
			MessageDigest messageDigest = MessageDigest.getInstance("SHA3-224");
			DigestInputStream dInputStream = new DigestInputStream(fileInputStream, messageDigest);
			byte[] buffer = new byte[4096];
			while (dInputStream.read(buffer) != -1)
				;
			jTextField.setText(Hex.toHexString(dInputStream.getMessageDigest().digest()));
			dInputStream.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}

	public static void SHA3_256FileDigest(String fileNme, JTextField jTextField) {
		try {
			Security.addProvider(new BouncyCastleProvider());
			FileInputStream fileInputStream = new FileInputStream(fileNme);
			MessageDigest messageDigest = MessageDigest.getInstance("SHA3-256");
			DigestInputStream dInputStream = new DigestInputStream(fileInputStream, messageDigest);
			byte[] buffer = new byte[4096];
			while (dInputStream.read(buffer) != -1);
			jTextField.setText(Hex.toHexString(dInputStream.getMessageDigest().digest()));
			dInputStream.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}

	public static void SHA3_384FileDigest(String fileNme, JTextField jTextField) {
		try {
			Security.addProvider(new BouncyCastleProvider());
			FileInputStream fileInputStream = new FileInputStream(fileNme);
			MessageDigest messageDigest = MessageDigest.getInstance("SHA3-384");
			DigestInputStream dInputStream = new DigestInputStream(fileInputStream, messageDigest);
			byte[] buffer = new byte[4096];
			while (dInputStream.read(buffer) != -1);
			jTextField.setText(Hex.toHexString(dInputStream.getMessageDigest().digest()));
			dInputStream.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}

	public static void SHA3_512FileDigest(String fileNme, JTextField jTextField) {
		try {
			Security.addProvider(new BouncyCastleProvider());
			FileInputStream fileInputStream = new FileInputStream(fileNme);
			MessageDigest messageDigest = MessageDigest.getInstance("SHA3-512");
			DigestInputStream dInputStream = new DigestInputStream(fileInputStream, messageDigest);
			byte[] buffer = new byte[4096];
			while (dInputStream.read(buffer) != -1)
				;
			jTextField.setText(Hex.toHexString(dInputStream.getMessageDigest().digest()));
			dInputStream.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}
}
