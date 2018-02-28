import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

public class Client {

	    private Client() {}

		public static void main(String[] args) {

			String host = (args.length < 1) ? null : args[0];
			try {
				Registry registry = LocateRegistry.getRegistry(host);
				Hello stub = (Hello) registry.lookup("Hello");
				
				Cripto c = new Cripto();
				KeyPair keys = c.buildKeyPair();

				byte[] encrypted = c.encrypt(keys.getPrivate(),c.sha512("teste").getBytes());
				Send send = new Send(keys.getPublic().getEncoded(),encrypted,"teste");
				
				String response = stub.sayHello( send );
				System.out.println("response: " + response);
			} catch (Exception e) {
				System.err.println("Client exception: " + e.toString());
				e.printStackTrace();
		 	}
		}
}
