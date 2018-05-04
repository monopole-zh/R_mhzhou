import java.awt.HeadlessException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;

public class SymmetricEncryption {
	public static void fileDESEncryptor(String fileName, String encryptedFileName, String password) {
		try {
			try (FileInputStream fileInputStream = new FileInputStream(fileName);
					FileOutputStream fileOutputStream = new FileOutputStream(encryptedFileName);) {
				MessageDigest mDigest = MessageDigest.getInstance("SHA-1");
				byte[] key = mDigest.digest(password.getBytes());
				SecretKeySpec keySpec = new SecretKeySpec(key, 0, 8, "DES");

				byte[] ivValue = new byte[8];
				Random random = new Random(System.currentTimeMillis());
				random.nextBytes(ivValue);
				IvParameterSpec ivParameterSpec = new IvParameterSpec(ivValue);

				fileOutputStream.write("MyFileEncryptor".getBytes());
				fileOutputStream.write(ivValue);
				fileOutputStream.write(key);
				Cipher cipher = Cipher.getInstance("DES/OFB/PKCS5Padding");
				cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParameterSpec);
				
				CipherInputStream cInputStream = new CipherInputStream(fileInputStream, cipher);

				byte[] buffer = new byte[4096];
				int n = 0;
				while ((n  = cInputStream.read(buffer)) != -1) {
					fileOutputStream.write(buffer, 0, n);
				}
				cInputStream.close();
			}
			JOptionPane.showMessageDialog(null, "Encrypted successfully!");
		} catch (InvalidKeyException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		} catch (NoSuchAlgorithmException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		} catch (NoSuchPaddingException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		} catch (InvalidAlgorithmParameterException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}

	public static void fileDESDecryptor(String encryptedFileName, String decryptedFileName, String password) {
		try {
			try (FileInputStream fileInputStream = new FileInputStream(encryptedFileName);
					FileOutputStream fileOutputStream = new FileOutputStream(decryptedFileName);) {
				byte[] fileIdentifier = new byte["MyFileEncryptor".getBytes().length];
				fileInputStream.read(fileIdentifier);
				if (new String(fileIdentifier).equals("MyFileEncryptor")) {
					byte[] ivValue = new byte[8];
					fileInputStream.read(ivValue);
					IvParameterSpec ivParameterSpec = new IvParameterSpec(ivValue);

					MessageDigest mDigest = MessageDigest.getInstance("SHA-1");
					byte[] key = mDigest.digest(password.getBytes());
					byte[] key2 = new byte[20];
					fileInputStream.read(key2);
					if (Arrays.equals(key, key2)) {
						SecretKeySpec keySpec = new SecretKeySpec(key, 0, 8, "DES");

						Cipher cipher = Cipher.getInstance("DES/OFB/PKCS5Padding");
						cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParameterSpec);

						CipherInputStream cInputStream = new CipherInputStream(fileInputStream, cipher);
						byte[] buffer = new byte[4096];
						int n = 0;

						while ((n = cInputStream.read(buffer)) != -1) {
							fileOutputStream.write(buffer, 0, n);
						}
						JOptionPane.showMessageDialog(null, "Decrypted successfully!");
						cInputStream.close();
					} else {
						JOptionPane.showMessageDialog(null, "Password error!");
					}
				} else {
					JOptionPane.showMessageDialog(null, "The file is't encrypted by me!");
				}
			}
		} catch (InvalidKeyException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		} catch (HeadlessException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		} catch (NoSuchAlgorithmException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		} catch (NoSuchPaddingException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		} catch (InvalidAlgorithmParameterException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}

	public static void fileAESEncryptor(String fileName, String encryptedFileName, String password, int keyByteLength) {
		try {
			try (FileInputStream fileInputStream = new FileInputStream(fileName);
					FileOutputStream fileOutputStream = new FileOutputStream(encryptedFileName);) {
				MessageDigest mDigest = MessageDigest.getInstance("SHA-384");
				byte[] key = mDigest.digest(password.getBytes());
				SecretKeySpec keySpec = new SecretKeySpec(key, 0, keyByteLength, "AES");

				byte[] ivValue = new byte[16];
				Random random = new Random(System.currentTimeMillis());
				random.nextBytes(ivValue);
				IvParameterSpec ivParameterSpec = new IvParameterSpec(ivValue);

				fileOutputStream.write("MyFileEncryptor".getBytes());
				fileOutputStream.write(ivValue);
				fileOutputStream.write(key);
				Cipher cipher = Cipher.getInstance("AES/OFB/PKCS5Padding");
				cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParameterSpec);

				CipherInputStream cInputStream = new CipherInputStream(fileInputStream, cipher);

				byte[] buffer = new byte[4096];
				int n = 0;

				while ((n = cInputStream.read(buffer)) != -1) {
					fileOutputStream.write(buffer, 0, n);
				}
				JOptionPane.showMessageDialog(null, "Encrypted successfully!");
				cInputStream.close();

			}
		} catch (InvalidKeyException e) {
			JOptionPane.showMessageDialog(null, "Invalid encrypted key!");
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Can't find file!");
		} catch (NoSuchAlgorithmException e) {
			JOptionPane.showMessageDialog(null, "Algorithm name error!");
		} catch (NoSuchPaddingException e) {
			JOptionPane.showMessageDialog(null, "Padding pattern error!");
		} catch (InvalidAlgorithmParameterException e) {
			JOptionPane.showMessageDialog(null, "Invalid algorithm parameter!");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}

	public static void fileAESDecryptor(String encryptedFileName, String decryptedFileName, String password,
			int keyByteLength) {
		try {
			try (FileInputStream fileInputStream = new FileInputStream(encryptedFileName);
					FileOutputStream fileOutputStream = new FileOutputStream(decryptedFileName);) {
				byte[] fileIdentifier = new byte["MyFileEncryptor".getBytes().length];
				fileInputStream.read(fileIdentifier);
				if (new String(fileIdentifier).equals("MyFileEncryptor")) {
					byte[] ivValue = new byte[16];
					fileInputStream.read(ivValue);
					IvParameterSpec ivParameterSpec = new IvParameterSpec(ivValue);
					MessageDigest mDigest = MessageDigest.getInstance("SHA-384");
					byte[] key = mDigest.digest(password.getBytes());
					byte[] key2 = new byte[48];
					fileInputStream.read(key2);
					if (Arrays.equals(key, key2)) {
						SecretKeySpec keySpec = new SecretKeySpec(key, 0, keyByteLength, "AES");
						
						Cipher cipher = Cipher.getInstance("AES/OFB/PKCS5Padding");
						cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParameterSpec);

						CipherInputStream cInputStream = new CipherInputStream(fileInputStream, cipher);
						byte[] buffer = new byte[4096];
						int n = 0;
						while ((n = cInputStream.read(buffer)) != -1) {
							fileOutputStream.write(buffer, 0, n);
						}
						cInputStream.close();
						JOptionPane.showMessageDialog(null, "Decrypted successfully!");
					} else {
						JOptionPane.showMessageDialog(null, "Password error!");
					}
				} else {
					JOptionPane.showMessageDialog(null, "The file isn't encrypted by me!");
				}
			}
		} catch (InvalidKeyException e) {
			JOptionPane.showMessageDialog(null, "Invalid decrypted key!");
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Can't find file!");
		} catch (NoSuchAlgorithmException e) {
			JOptionPane.showMessageDialog(null, "Algorithm name error!");
		} catch (NoSuchPaddingException e) {
			JOptionPane.showMessageDialog(null, "Padding pattern error!");
		} catch (InvalidAlgorithmParameterException e) {
			JOptionPane.showMessageDialog(null, "Invalid algorithm parameter!");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}
}
