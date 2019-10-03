import java.net.*;
import java.io.*;

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
			
			// Initialise the instance variables for communication
			dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        }
        catch (Exception e) {
            System.out.println("Error occured in communication");
        }
    }
    public static void main(String args[]) {
		Client client = new Client("127.0.0.1", 5001);
	}
}