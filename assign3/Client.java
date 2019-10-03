import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client {

// Declare all the input and output buffers as null

	private Socket socket = null;
	private DataInputStream dis = null;
	private DataOutputStream dos = null;

// Parameterized Constructor for Client class

	public Client(String address, int port) {
		
		try {
			
			// establish socket connection
			socket = new Socket(address, port);
			System.out.println("Connection established");
			
			dos = new DataOutputStream(socket.getOutputStream());
            		dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
			Scanner sc = new Scanner(System.in);

			System.out.println("Enter command");
			String command = sc.nextLine();
			dos.writeUTF(command);

			while(!(command.equals("EXIT"))) {
			// Initialise the instance variables for communication
				if(command.equals("UPLOAD")) {
					System.out.println("UPLOAD COMMAND RECEIVED");				
break;
				}
			}
			dos.close();
			dis.close();
			socket.close();
        }
        catch (Exception e) {
            System.out.println("Error occured in communication");
        }
    }
    public static void main(String args[]) {
		Client client = new Client("127.0.0.1", 5001);
	}
}
