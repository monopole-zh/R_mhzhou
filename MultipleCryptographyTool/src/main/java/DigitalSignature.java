import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStore.PrivateKeyEntry;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;
import javax.swing.JOptionPane;

public class DigitalSignature {
	public static void signFileDSA(String fileToSign, DSAPrivateKey key, String signValueFile, String signAlgorithm)
			throws FileNotFoundException, IOException, NoSuchAlgorithmException, InvalidKeyException,
			SignatureException {

		try (FileInputStream fis = new FileInputStream(fileToSign);
				FileOutputStream fos = new FileOutputStream(signValueFile)) {
			Signature signature = Signature.getInstance(signAlgorithm);
			signature.initSign(key);
			byte[] buffer = new byte[1024];
			int n = 0;
			while ((n = fis.read(buffer)) != -1) {
				signature.update(buffer, 0, n);
			}
			byte[] signaturValue = signature.sign();
			fos.write(signaturValue);
		}

	}

	public static boolean verifyFileDSA(String fileToVerify, DSAPublicKey key, String signValueFile,
			String signAlgorithm) throws FileNotFoundException, IOException, NoSuchAlgorithmException,
			InvalidKeyException, SignatureException {

		try (FileInputStream fisFileToVerify = new FileInputStream(fileToVerify);
				FileInputStream fisSignValueFile = new FileInputStream(signValueFile)) {
			Signature signature = Signature.getInstance(signAlgorithm);
			signature.initVerify(key);
			byte[] buffer = new byte[1024];
			int n = 0;
			while ((n = fisFileToVerify.read(buffer)) != -1) {
				signature.update(buffer, 0, n);
			}
			byte[] signatureValue = new byte[fisSignValueFile.available()];
			fisSignValueFile.read(signatureValue);
			return signature.verify(signatureValue);
		}
	}

	public static DSAPrivateKey getDSAPrivateKey(char[] keyStorePassword, char[] keyDSAPassword) {

		try {
			KeyStore keyStore = KeyStore.getInstance("JCEKS");
			char[] password = keyStorePassword;
			char[] passwordKeyAlias = keyDSAPassword;
			FileInputStream fis = new FileInputStream("mykeystore.keystore");
			keyStore.load(fis, password);
			KeyStore.ProtectionParameter protParam = new KeyStore.PasswordProtection(passwordKeyAlias);
			KeyStore.PrivateKeyEntry privateKeyEntry = (PrivateKeyEntry) keyStore.getEntry("mydsa", protParam);
			DSAPrivateKey dsaPrivateKey = (DSAPrivateKey) privateKeyEntry.getPrivateKey();
			fis.close();
			return dsaPrivateKey;
		} catch (KeyStoreException e) {
			JOptionPane.showMessageDialog(null, "keystore exception!");
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "FileNotFoundException!");
		} catch (NoSuchAlgorithmException e) {
			JOptionPane.showMessageDialog(null, "NoSuchAlgorithmException!");
		} catch (CertificateException e) {
			JOptionPane.showMessageDialog(null, "CertificateException!");
		} catch (UnrecoverableEntryException e) {
			JOptionPane.showMessageDialog(null, "UnrecoverableEntryException!");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
		return null;
	}

	public static DSAPublicKey getDSAPublicKey(char[] keyStorePassword, char[] keyDSAPassword) {

		try {
			KeyStore keyStore = KeyStore.getInstance("JCEKS");
			char[] password = keyStorePassword;
			char[] passwordKeyAlias = keyDSAPassword;
			FileInputStream fis = new FileInputStream("mykeystore.keystore");
			keyStore.load(fis, password);
			KeyStore.ProtectionParameter protParam = new KeyStore.PasswordProtection(passwordKeyAlias);
			KeyStore.PrivateKeyEntry privateKeyEntry = (PrivateKeyEntry) keyStore.getEntry("mydsa", protParam);
			X509Certificate x509Certificate = (X509Certificate) privateKeyEntry.getCertificate();
			DSAPublicKey dsaPublicKey = (DSAPublicKey) x509Certificate.getPublicKey();
			fis.close();
			return dsaPublicKey;
		} catch (KeyStoreException e) {
			JOptionPane.showMessageDialog(null, "keystore exception!");
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "FileNotFoundException!");
		} catch (NoSuchAlgorithmException e) {
			JOptionPane.showMessageDialog(null, "NoSuchAlgorithmException!");
		} catch (CertificateException e) {
			JOptionPane.showMessageDialog(null, "CertificateException!");
		} catch (UnrecoverableEntryException e) {
			JOptionPane.showMessageDialog(null, "UnrecoverableEntryException!");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
		return null;
	}
}
