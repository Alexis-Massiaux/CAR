package fil.car;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;

public class Client {
	
	private static final FTPClient INSTANCE_CLIENT = new FTPClient();
	private static final String PATH_CONFIG = "configuration.txt";
	
	public static void connexion() {
		
		if(!INSTANCE_CLIENT.isConnected()) {
			String addr = "127.0.0.1";
			int port	= 1234;
				try {
					FileReader fileReader = new FileReader(PATH_CONFIG);
					BufferedReader bufferedReader = new BufferedReader(fileReader);
					addr = bufferedReader.readLine();
					port = Integer.parseInt(bufferedReader.readLine());
				} catch (IOException e1) {
					System.err.println("[CLIENT] [CONFIGURATION] "+e1);
				}
				
			try {
				//INSTANCE_CLIENT.connect("0.0.0.0", 2121);
				INSTANCE_CLIENT.connect(addr, port);
			} catch (IOException e) {
				System.err.println("[CLIENT] [CONNECTION] "+e);
			}
		}
	}
	
	public static FTPClient getInstance() {
		return INSTANCE_CLIENT;
	}

}
