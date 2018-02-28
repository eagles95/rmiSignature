import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;

public class Send implements java.io.Serializable {
	byte[] pubkey;
	byte[] encrypted;
	String msg;

	public Send(byte[] pubkey, byte[] encrypted, String msg){
		this.pubkey = pubkey;
		this.encrypted = encrypted;
		this.msg = msg;
	}

}
