import java.net.*;
import java.io.*;

public class Server {

	private Socket socket = null;
	private ServerSocket server = null;
	private DataInputStream dis = null;
	private DataOutputStream dos = null;

	public Server(int port) {
		
		try {
			server = new ServerSocket(port);
			System.out.println("Waiting for Connection");

			socket = server.accept();
			System.out.println("\nConnection Established");

			dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
			dos = new DataOutputStream(socket.getOutputStream());

			String command = "";
			System.out.println("no command received");
			//while(!(command.equals("EXIT"))) {
			command = dis.readUTF();
			System.out.println(dis.readUTF());
			System.out.println("inside while loop");
				if(command.equals("UPLOAD")) {
					System.out.println("UPLOAD COMMAND RECEIVED SERVER");				
				}
			//}
        }
        catch(Exception e) {
		System.out.println(e);
        }
    }
    public static void main(String args[]) {
		Server server = new Server(5001);
	}
}
