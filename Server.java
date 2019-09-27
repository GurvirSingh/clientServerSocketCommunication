import java.net.*;
import java.io.*;

public class Server {

	private Socket socket = null;
	private ServerSocket server = null;
	private DataInputStream in = null;
	private DataOutputStream ou = null;

	public Server(int port) {

		try {
			server = new ServerSocket(port);
			System.out.println("Waiting for Connection");

			socket = server.accept();
			System.out.println("\nConnection Established");
			
			in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
			ou = new DataOutputStream(socket.getOutputStream());

			String command = "";


			command = in.readUTF();
			//System.out.println(command);
			
			if(command.equals("UPLOAD")){
			// Send ok msg to client
			System.out.println("Upload acknowledgement sent: OK");
			ou.writeUTF("OK");
			// end of msg to client

			System.out.println("\nClient is uploading a file...");
			String file_name = "";
  			file_name = in.readUTF(); 
			String create_file = "/home/gurvir/A/Work/CSE5306 Project1/Uploads_by_client/"+file_name;
			try {
				//String contents = in.readUTF();
                		File file = new File(create_file);
                		file.createNewFile();
				
				BufferedWriter writer = new BufferedWriter(new FileWriter(create_file));
				
				
				String wrt = "";
				while(!((wrt = in.readUTF()).equals("EOF"))) {
    				writer.write(wrt);
				//System.out.println(wrt);
				}
				
    				writer.close();
				System.out.println("\nSuccessfully Saved!");
				socket.close();
			
                	}
                	catch(Exception e) {
                		System.out.println("Error Occured!");
                	}
			} // end of if condition 
			
			if(command.equals("DOWNLOAD")) {

				System.out.println("Download acknowledgement sent: OK");
				ou.writeUTF("OK");

				String file_name = "";
				
				file_name = in.readUTF();

				// Check if file exists or not
				File t = new File("/home/gurvir/A/Work");
				boolean exists = t.exists();
				System.out.println(exists);

				if (!(exists)) {
					ou.writeUTF("File not found!");
				}
				else {
					ou.writeUTF("File found!");
				}
				

				BufferedReader br_down = new BufferedReader(new FileReader(file_name)); 

  				String st = ""; 


  				while ((st = br_down.readLine()) != null) {
				System.out.println(st); 
				ou.writeUTF(st); 
    				}
				ou.writeUTF("EOF"); 
				ou.writeUTF("Over");
			}
				
			// Rename 
			if(command.equals("RENAME")) {

			}		

			//Delete
			if(command.equals("DELETE")) {

			}
			
		}
		catch(FileNotFoundException s) {
			System.out.println("Error: File not Found");		
		}
		catch(Exception e) {
			System.out.println("Error in establishing connection");
		}
	}

	public static void main(String args[]) {
		Server server = new Server(5001);
	}
}
