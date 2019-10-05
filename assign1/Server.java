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
			String create_file = "/home/gurvir/A/Work/CSE5306 Project1/Uploads_to_server/"+file_name;
			try {
				//String contents = in.readUTF();
                		File file = new File(create_file);
                		file.createNewFile();
				
				BufferedWriter writer = new BufferedWriter(new FileWriter(create_file));
				
				
				String wrt = "";
				while(!((wrt = in.readUTF()).equals("EOF"))) {
    				writer.write(wrt);
				writer.write("\n");
				//System.out.println(wrt);
				}
				
    				writer.close();
				System.out.println("\nSuccessfully Saved!");
				//socket.close();
			
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

				BufferedReader br_down = new BufferedReader(new FileReader(file_name)); 

  				String st = ""; 

				ou.writeUTF("TRUE");
  				while ((st = br_down.readLine()) != null) {
				//System.out.println(st); 
				ou.writeUTF(st); 
    				}
				ou.writeUTF("EOF"); 
			}
			// Rename 
			if(command.equals("RENAME")) {
				//Send OK MSG TO CLIENT
				System.out.println("Rename Acknowledgement sent :OK");
				//ou.writeUTF("OK");
				//End of MSG TO CLIENT


				String eval = "0";
				System.out.println("\nClient is entering Filename to rename");
				String file_name = "";
				file_name = in.readUTF();
				System.out.println("File Name = "+ file_name);
				String create_file = "/home/gurvir/A/Work/CSE5306 Project1/Uploads_to_server/"+file_name;
				File file = new File(create_file);

				if(file.exists())
				{
					eval = "1"; 
					System.out.println("\nFile Exists");
					
					ou.writeUTF(eval);
					//System.out.println("AFTER EVAL + 1");

					System.out.println("Client Entering New File Name");
					
					
					String nfile_name = "";
					nfile_name = in.readUTF();
					System.out.println("New File Name = " + nfile_name);
					String ncreate_file = "/home/gurvir/A/Work/CSE5306 Project1/Uploads_to_server/"+nfile_name;
			     	File nfile = new File(ncreate_file);

					if(file.renameTo(nfile))
					{
						eval = "2";
						ou.writeUTF(eval);
						System.out.println("File Renamed");
					}
				}
				else
				{
					eval = "0";
					ou.writeUTF(eval);
					System.out.println("File Not Found");
				}
			}		
			//Delete
			if(command.equals("DELETE")) {
				//Send OK MSG TO CLIENT
				System.out.println("Delete Acknowledgement sent :OK");
				ou.writeUTF("OK");
				//End of MSG TO CLIENT

                String eval = "0";
				System.out.println("\nClient is entering Filename to delete");
				String file_name = "";
				file_name = in.readUTF();
				System.out.println("File Name = "+ file_name);
				String create_file = "/home/gurvir/A/Work/CSE5306 Project1/Uploads_to_server/"+file_name;
				File file = new File(create_file);

				if(file.exists())
				{
					System.out.println("File Found.\nDeleting File...");
					if(file.delete()){
						System.out.println("File Deleted");
						eval = "1";
						ou.writeUTF(eval);
					}
				}
				else
				{
					eval = "0";
					System.out.println("File Not Found");
					ou.writeUTF(eval);

				}
				socket.close();
			}
		}
		catch(FileNotFoundException s) {
		try{			
			ou.writeUTF("FALSE");
			System.out.println("Error: File not Found");		
		}
		catch (IOException i) {
			System.out.println("Error");
		}
		}
		catch(Exception e) {
			System.out.println("Error in establishing connection");
		}
	
	}

	public static void main(String args[]) {
		Server server = new Server(5001);
	}
}
