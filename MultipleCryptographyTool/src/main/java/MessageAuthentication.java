import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.Arrays;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

public class MessageAuthentication {
	public static String generatorStringMAC(String algorithmName,String password,String data) throws NoSuchAlgorithmException, InvalidKeyException {
		Security.addProvider(new BouncyCastleProvider());
		byte key[]=null;
		MessageDigest mDigest=MessageDigest.getInstance("SHA-224");
		key =mDigest.digest(password.getBytes());		
		byte macCode[]=null;
		SecretKey secretKey=new SecretKeySpec(key, algorithmName);
		Mac mac = null;
        mac = Mac.getInstance(algorithmName);
        mac.init(secretKey);
		mac.update(data.getBytes());
		macCode=mac.doFinal();
		return Hex.toHexString(macCode);
	}
	public static String generatorFileMACToString(String algorithmName,String password,String filePath) throws NoSuchAlgorithmException, InvalidKeyException, FileNotFoundException, IOException {
		Security.addProvider(new BouncyCastleProvider());
		byte key[]=null;
		MessageDigest mDigest=MessageDigest.getInstance("SHA-224");
		key =mDigest.digest(password.getBytes());		
		byte macCode[]=null;
		SecretKey secretKey=new SecretKeySpec(key, algorithmName);
		Mac mac = null;
        mac = Mac.getInstance(algorithmName);
        mac.init(secretKey);
        byte []buffer=new byte[4096];
        int n=0;
        try(
        		FileInputStream fileInputStream=new FileInputStream(filePath);       		
        		){
        	while ((n=fileInputStream.read(buffer))!=-1) {
				mac.update(buffer, 0, n);
			}
        	macCode=mac.doFinal();      	
        }
		return Hex.toHexString(macCode);
	}
	public static void generatorFileMACToFile(String algorithmName,String password,String filePath,String macFilePath) throws NoSuchAlgorithmException, InvalidKeyException, FileNotFoundException, IOException {
		Security.addProvider(new BouncyCastleProvider());
		byte key[]=null;
		MessageDigest mDigest=MessageDigest.getInstance("SHA-224");
		key =mDigest.digest(password.getBytes());		
		SecretKey secretKey=new SecretKeySpec(key, algorithmName);
		Mac mac = null;
        mac = Mac.getInstance(algorithmName);
        mac.init(secretKey);
        byte []buffer=new byte[4096];
        int n=0;
        try(
        		FileInputStream fileInputStream=new FileInputStream(filePath); 
        		FileOutputStream fileOutputStream=new FileOutputStream(macFilePath);
        		){
        	 if (algorithmName.equals("HmacSHA1")) {
 				algorithmName="HmacSHA100";
        	 }
        	while ((n=fileInputStream.read(buffer))!=-1) {
				mac.update(buffer, 0, n);
			}
        	fileOutputStream.write(algorithmName.getBytes());
        	fileOutputStream.write(key);
        	fileOutputStream.write(mac.doFinal());     	
        }
	}
	public static void generatorStringMACToFile(String algorithmName,String password,String data,String macFilePath) throws NoSuchAlgorithmException, InvalidKeyException, FileNotFoundException, IOException {
		Security.addProvider(new BouncyCastleProvider());
		byte key[]=null;
		MessageDigest mDigest=MessageDigest.getInstance("SHA-224");
		key =mDigest.digest(password.getBytes());		
		SecretKey secretKey=new SecretKeySpec(key, algorithmName);
		Mac mac = null;
        mac = Mac.getInstance(algorithmName);
        mac.init(secretKey);
         try(
        		FileOutputStream fileOutputStream=new FileOutputStream(macFilePath);
        		){
        	 if (algorithmName.equals("HmacSHA1")) {
				algorithmName="HmacSHA100";
			}
        	fileOutputStream.write(algorithmName.getBytes());
         	fileOutputStream.write(key);
        	fileOutputStream.write(mac.doFinal(data.getBytes()));
        }
	}
	public static boolean vertifyMac(String fileName,String macFileName,char []password) throws FileNotFoundException, IOException, NoSuchAlgorithmException, InvalidKeyException{
		try(
				FileInputStream fileInputStreamFileName=new FileInputStream(fileName);
				FileInputStream fileInputStreamMacFileName=new FileInputStream(macFileName);			
				){
			MessageDigest mDigest=MessageDigest.getInstance("SHA-224");
			byte []key=mDigest.digest(new String(password).getBytes());
			byte []key1=new byte[mDigest.getDigestLength()];
			byte[] algorithmName=new byte["HmacSHA224".length()];
			fileInputStreamMacFileName.read(algorithmName);
			if (Arrays.equals(algorithmName, new String("HmacSHA100").getBytes())) {
				algorithmName="HmacSHA1".getBytes();
			}
			fileInputStreamMacFileName.read(key1);
			if (Arrays.equals(key, key1)) {
				SecretKey macSecretKey=new SecretKeySpec(key, new String(algorithmName));
				Mac mac=Mac.getInstance(new String(algorithmName));
				mac.init(macSecretKey);
				byte []buffer=new byte[4096];
				int n=0;
				while ((n=fileInputStreamFileName.read(buffer))!=-1) {
					mac.update(buffer, 0, n);
				}
				byte []macValueFile=mac.doFinal();
				byte []macValueMacFile=new byte[mac.getMacLength()];
				fileInputStreamMacFileName.read(macValueMacFile);
				if (Arrays.equals(macValueFile, macValueMacFile)) {
					return true;
				}
				else {
					return false;
				}		
			}
			else{
				JOptionPane.showMessageDialog(null, "Password error!");
				return false;
			}	
		}
	}
}
