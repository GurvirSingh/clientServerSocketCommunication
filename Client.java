import java.net.*;
import java.io.*;

public class Client {

// Declare all the input and output buffers as null

	private Socket socket = null;
	private DataInputStream input, input1, in = null;
	private DataOutputStream out = null;

// Parameterized Constructor for Client class

	public Client(String address, int port) {
		
		try {
			// establish socket connection
			socket = new Socket(address, port);
			System.out.println("Connection established");
			
			// Initialise the instance variables for communication
			input = new DataInputStream(System.in);
			out = new DataOutputStream(socket.getOutputStream());
			in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

			// get command from user
			System.out.println("\nEnter UPLOAD to upload a file to the server \nDOWNLOAD to download a file from the server\nRENAME to rename a file in the server\nDELETE to delete a file from the server. [All commands should be in Upper Case]");
			input1 = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
			String command = " ";
			System.out.println("\n\nEnter Command: ");
			command = input.readLine();

			// check command for validations 
			if(command.equals("UPLOAD")) {
				//send command to server 
				out.writeUTF(command);				

				//read acknowledgement from server
				String incommand = input1.readUTF();
				System.out.println(incommand);
				
				// get filename from user
				System.out.println("\nEnter File name to Upload: ");
				String file_name = "";
				file_name = input.readLine();
				
				// initialise buffer for communication
				BufferedReader br = new BufferedReader(new FileReader(file_name)); 

				// send filename to the server
  				out.writeUTF(file_name);
	
				// send the file block by block
  				String st = ""; 
  				while ((st = br.readLine()) != null) {
				// below line commented for debugging purposes
				// System.out.println(st); 
				out.writeUTF(st); 
    				}
				out.writeUTF("EOF"); 
			}
			else if(command.equals("DOWNLOAD")) {
				//send command to server 
				out.writeUTF(command);

				String incommand = input1.readUTF();
				System.out.println(incommand);

				System.out.println("Enter file name to download:\n");

				String create_file_down1 = "";
				create_file_down1 = input.readLine();

				
				String create_file_down2 ="/home/gurvir/A/Work/CSE5306 Project1/Downloads_by_client/"+create_file_down1;

				out.writeUTF(create_file_down1);

				String exist = input1.readUTF();
				// below line commented for debugging purposes
				//System.out.println(exist);
				if(exist.equals("TRUE")){
				
				File file = new File(create_file_down2);
                		file.createNewFile();
				
				BufferedWriter writer = new BufferedWriter(new FileWriter(create_file_down2));
				
				
				String wrt = "";
				while(!((wrt = in.readUTF()).equals("EOF"))) {
    				writer.write(wrt);
				writer.write("\n");
				// below line commented for debugging purposes
				//System.out.println(wrt);
				}
				
    				writer.close();
				System.out.println("\nSuccessfully Saved!");
				// below line commented for debugging purposes
				//socket.close();
				
				}//end of if
				else {
					System.out.println("File not found!");
				}
			
			}
			// Rename 
			else if(command.equals("RENAME")) {
				//send command to server
				out.writeUTF(command);

				System.out.println("Enter File to Rename");
				String file_name = "";
				file_name = input.readLine();
				out.writeUTF(file_name);

				
				  String eval = "";
				  eval = input1.readUTF();
				// below line commented for debugging purposes
				  //System.out.println(eval);
				  

				  if(eval.equals("1"))
				  {
					System.out.println("File Exists");
					System.out.println("Enter New File Name");
					String nfile_name = "";
					nfile_name = input.readLine();
					out.writeUTF(nfile_name);

					eval = input1.readUTF();
					if(eval.equals("2"))
					{
						System.out.println("File Successfully Renamed");
					}

				  }
				  else if(eval.equals("0"))
				  {
					System.out.println("File Not Found");
				  }
			}
			//Delete
			else if(command.equals("DELETE")) {
				//send command to server
				out.writeUTF(command);

				String incommand = input1.readUTF();
				System.out.println(incommand);
				
				System.out.println("\nEnter File Name to Delete ");
				String file_name = "";
				file_name = input.readLine();
				out.writeUTF(file_name);
	  				
				  String eval = "0";
				  eval = input1.readUTF();

				  if(eval.equals("1")){
					  System.out.println("File Found");
					  System.out.println("File Deleted");
				  }
				  else 
				  {
					  System.out.println("File not found");
					
				  }				  
			}
			else {
				System.out.println("Command not found!");
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
