import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.MessageDigest;

import javax.crypto.Cipher;
public class Cripto {
	public Cripto(){}

	public static KeyPair buildKeyPair() throws NoSuchAlgorithmException {
		final int keySize = 2048;
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(keySize);      
		return keyPairGenerator.genKeyPair();
	}

	public static byte[] encrypt(PrivateKey privateKey, byte[] message) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA");  
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);  
		return cipher.doFinal(message);  
	}

	public static byte[] decrypt(PublicKey publicKey, byte [] encrypted) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA");  
		cipher.init(Cipher.DECRYPT_MODE, publicKey);
		return cipher.doFinal(encrypted);
	}

	public static String sha512( String msg ) throws Exception{
		MessageDigest sh = MessageDigest.getInstance("SHA-512");
		sh.update(msg.getBytes());
		StringBuffer sb = new StringBuffer();
		for (byte b : sh.digest()) sb.append(Integer.toHexString(0xff & b));
		return sb.toString();
	}
}
