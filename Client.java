import java.net.*;
import java.io.*;

public class Client {

	private Socket socket = null;
	private DataInputStream input, input1, in = null;
	private DataOutputStream out = null;

	public Client(String address, int port) {

		try {
			socket = new Socket(address, port);
			System.out.println("Connection established");
		
			input = new DataInputStream(System.in);
			out = new DataOutputStream(socket.getOutputStream());
			in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

			// get command from user
			System.out.println("\nEnter UPLOAD to upload a file to the server \nDOWNLOAD to download a file from the server\nRENAME to rename a file in the server\nDELETE to delete a file from the server. [All commands should be in Upper Case]");

			input1 = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
		
			String command = " ";
			
			
			System.out.println("\n\nEnter Command: ");
			command = input.readLine();

			if(command.equals("UPLOAD")) {

				out.writeUTF(command);				

				String incommand = input1.readUTF();
				System.out.println(incommand);

				System.out.println("\nEnter File name to Upload: ");
				String file_name = "";
				file_name = input.readLine();
				out.writeUTF(file_name);
				
				BufferedReader br = new BufferedReader(new FileReader(file_name)); 
  
  				String st = ""; 


  				while ((st = br.readLine()) != null) {
				//System.out.println(st); 
				out.writeUTF(st); 
    				}
				out.writeUTF("EOF"); 
				out.writeUTF("Over");
			}
			//else {
			//	System.out.println("\nError: Command not found");
			//}
			
			if(command.equals("DOWNLOAD")) {

				out.writeUTF(command);

				String incommand = input1.readUTF();
				System.out.println(incommand);

				System.out.println("Enter file name to download:\n");
		
				String create_file_down1 = "";
				create_file_down1 = input.readLine();

				
				String create_file_down2 ="/home/gurvir/A/Work/CSE5306 Project1/Downloads_by_client/"+create_file_down1;

				out.writeUTF(create_file_down1);
				
				File file = new File(create_file_down2);
                		file.createNewFile();
				
				BufferedWriter writer = new BufferedWriter(new FileWriter(create_file_down2));
				
				
				String wrt = "";
				while(!((wrt = in.readUTF()).equals("EOF"))) {
    				writer.write(wrt);
				//System.out.println(wrt);
				}
				
    				writer.close();
				System.out.println("\nSuccessfully Saved!");
				socket.close();
			}
				
			// Rename 
			if(command.equals("RENAME")) {

			}

			//Delete
			if(command.equals("DELETE")) {

			}
			
		}
		catch(FileNotFoundException e) {
			System.out.println("Error: File not found");
		}
		catch(Exception u) {
			System.out.println("Error while establishing connection");
		}
		
	}

	public static void main(String args[]) {
			
		Client client = new Client("127.0.0.1", 5001);

	}
}
