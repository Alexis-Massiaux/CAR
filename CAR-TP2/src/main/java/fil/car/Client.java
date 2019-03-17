package fil.car;

import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;

public class Client {
	
	private static final FTPClient INSTANCE_CLIENT = new FTPClient();
	
	public static void connexion() {
		if(!INSTANCE_CLIENT.isConnected()) {
			try {
				//INSTANCE_CLIENT.connect("0.0.0.0", 2121);
				INSTANCE_CLIENT.connect("127.0.0.1", 1234);
			} catch (IOException e) {
				System.err.println("[CLIENT] [CONNECTION] "+e);
			}
		}
	}
	
	public static FTPClient getInstance() {
		return INSTANCE_CLIENT;
	}

}
