import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client {

// Declare all the input and output buffers as null

	private Socket socket = null;
	private DataInputStream  in = null;
	private DataOutputStream out = null;

// Parameterized Constructor for Client class

	public Client(String address, int port) {
		
		try {
			// establish socket connection
			socket = new Socket(address, port);
			System.out.println("Connection established");
			
			// Initialise the instance variables for communication
			out = new DataOutputStream(socket.getOutputStream());
			in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

			Scanner sc = new Scanner(System.in);

			// get command from user
			System.out.println("\nEnter UPLOAD to upload a file to the server \nDOWNLOAD to download a file from the server\nRENAME to rename a file in the server\nDELETE to delete a file from the server. [All commands should be in Upper Case]");
			
			String command = " ";
			System.out.println("\n\nEnter Command: ");
			command = sc.nextLine();

			// check command for validations 
			if(command.equals("UPLOAD")) {
				//send command to server 
				out.writeUTF(command);				

				//read acknowledgement from server
				String incommand = in.readUTF();
				System.out.println(incommand);
				
				// get filename from user
				System.out.println("\nEnter File name to Upload: ");
				String file_name = "";
				file_name = sc.nextLine();
				
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

				String incommand = in.readUTF();
				System.out.println(incommand);

				System.out.println("Enter file name to download:\n");

				String create_file_down1 = "";
				create_file_down1 = sc.nextLine();

				
				String create_file_down2 ="/home/gurvir/A/Work/CSE5306 Project1/Assignment1/Downloads_by_client/"+create_file_down1;

				out.writeUTF(create_file_down1);

				String exist = in.readUTF();
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
				file_name = sc.nextLine();
				out.writeUTF(file_name);

				
				  String eval = "";
				  eval = in.readUTF();
				// below line commented for debugging purposes
				  //System.out.println(eval);
				  

				  if(eval.equals("1"))
				  {
					System.out.println("File Exists");
					System.out.println("Enter New File Name");
					String nfile_name = "";
					nfile_name = sc.nextLine();
					out.writeUTF(nfile_name);

					eval = in.readUTF();
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

				String incommand = in.readUTF();
				System.out.println(incommand);
				
				System.out.println("\nEnter File Name to Delete ");
				String file_name = "";
				file_name = sc.nextLine();
				out.writeUTF(file_name);
	  				
				  String eval = "0";
				  eval = in.readUTF();

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
