import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import java.security.PublicKey;
import java.security.KeyFactory;
import java.security.spec.X509EncodedKeySpec;

public class Server implements Hello {

	public Server() {}

	public String sayHello(Send send) throws Exception{
		Cripto c = new Cripto();
		PublicKey publicKey = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(send.pubkey));
		String decrypted = new String (c.decrypt(publicKey,send.encrypted));
		String compare = c.sha512(send.msg);
		if(decrypted.equals(compare))
			return "Autentication Success";
		else
			return "Authentication Failed";
	}
			        
	public static void main(String args[]) {
		try {
			Server obj = new Server();
			Hello stub = (Hello) UnicastRemoteObject.exportObject(obj, 0);
			// Bind the remote object's stub in the registry
			Registry registry = LocateRegistry.getRegistry();
			registry.bind("Hello", stub);
			System.err.println("Server ready");
		} catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
	}
}
