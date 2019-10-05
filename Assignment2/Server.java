import java.net.*; 
import java.io.*; 
import java.text.*; 
import java.util.*; 

public class Server { 
	public static void main(String[] args) throws IOException { 
 
		ServerSocket socket = new ServerSocket(5002); 
		
		System.out.println("Waiting for Connection");
		// run loop to get muiltiple clients 
		while (true) 
		{ 
			Socket soc = null; 			
			try
			{ 
				soc = socket.accept(); 
				
				System.out.println("Connection Established with Client: " + soc); 
				
				DataInputStream dis = new DataInputStream(soc.getInputStream()); 
				DataOutputStream dos = new DataOutputStream(soc.getOutputStream()); 
				
				// creating a new thread for clients and starting
				Thread t = new ClientHandler(soc, dis, dos); 
				t.start(); 
				
			} 
			catch (Exception e){ 
				socket.close(); 
				System.out.println("Error Occured"); 
			} 
        }
	} 
} 

//Client handler to manage threads
class ClientHandler extends Thread  
{ 
    final DataInputStream dis;
    final DataOutputStream dos; 
    final Socket soc; 
  
      
    // Constructor 
    public ClientHandler(Socket soc, DataInputStream dis, DataOutputStream dos)  
    { 
        this.soc = soc; 
        this.dis = dis; 
	this.dos = dos; 
    } 
  
    @Override
    public void run()  
    { 
        String tosend; 
        String toreturn; 

			try {
				String command = "";

				command = dis.readUTF();
				//System.out.println(command);
					
				if(command.equals("UPLOAD")){
				// Send ok msg to client
				System.out.println("Upload acknowledgement sent: OK");
				dos.writeUTF("OK");
				// end of msg to client
	
				System.out.println("\nClient is uploading a file...");
				String file_name = "";
				  file_name = dis.readUTF(); 
				String create_file = "/home/gurvir/A/Work/CSE5306 Project1/Assignment2/Uploads_to_server/"+file_name;
				try {
					//String contents = in.readUTF();
							File file = new File(create_file);
							file.createNewFile();
					
					BufferedWriter writer = new BufferedWriter(new FileWriter(create_file));
					
					
					String wrt = "";
					while(!((wrt = dis.readUTF()).equals("EOF"))) {
						writer.write(wrt);
					writer.write("\n");
					System.out.println(wrt);
					}
					
						writer.close();
					System.out.println("\nSuccessfully Saved!");
					this.soc.close();
				
						}
						catch(Exception e) {
							System.out.println("Error Occured!");
						}
				} // end of if condition 
				if(command.equals("DOWNLOAD")) {
	
					System.out.println("Download acknowledgement sent: OK");
					dos.writeUTF("OK");
	
					String file_name = "";
					
					file_name = dis.readUTF();
	
					BufferedReader br_down = new BufferedReader(new FileReader(file_name)); 
	
					  String st = ""; 
	
					dos.writeUTF("TRUE");
					  while ((st = br_down.readLine()) != null) {
					//System.out.println(st); 
					dos.writeUTF(st); 
						}
					dos.writeUTF("EOF"); 
					this.soc.close();
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
					file_name = dis.readUTF();
					System.out.println("File Name = "+ file_name);
					String create_file = "/home/gurvir/A/Work/CSE5306 Project1/Assignment2/Uploads_to_server/"+file_name;
					File file = new File(create_file);
	
					if(file.exists())
					{
						eval = "1"; 
						System.out.println("\nFile Exists");
						
						dos.writeUTF(eval);
	
						System.out.println("Client Entering New File Name");
						
						
						String nfile_name = "";
						nfile_name = dis.readUTF();
						System.out.println("New File Name = " + nfile_name);
						String ncreate_file = "/home/gurvir/A/Work/CSE5306 Project1/Assignment2/Uploads_to_server/"+nfile_name;
						 File nfile = new File(ncreate_file);
	
						if(file.renameTo(nfile))
						{
							eval = "2";
							dos.writeUTF(eval);
							System.out.println("File Renamed");
						}
					}
					else
					{
						eval = "0";
						dos.writeUTF(eval);
						System.out.println("File Not Found");
					}
					this.soc.close();
				}		
				//Delete
				if(command.equals("DELETE")) {
					//Send OK MSG TO CLIENT
					System.out.println("Delete Acknowledgement sent :OK");
					dos.writeUTF("OK");
					//End of MSG TO CLIENT
	
					String eval = "0";
					System.out.println("\nClient is entering Filename to delete");
					String file_name = "";
					file_name = dis.readUTF();
					System.out.println("File Name = "+ file_name);
					String create_file = "/home/gurvir/A/Work/CSE5306 Project1/Assignment2/Uploads_to_server/"+file_name;
					File file = new File(create_file);
	
					if(file.exists())
					{
						System.out.println("File Found.\nDeleting File...");
						if(file.delete()){
							System.out.println("File Deleted");
							eval = "1";
							dos.writeUTF(eval);
						}
					}
					else
					{
						eval = "0";
						System.out.println("File Not Found");
						dos.writeUTF(eval);
	
					}
					this.soc.close();
				}
			}
			catch(FileNotFoundException s) {
			try{			
				dos.writeUTF("FALSE");
				System.out.println("Error: File not Found");		
			}
			catch (IOException i) {
				System.out.println("Error");
			}
			}
			catch(IOException e) {
				System.out.println("Error in establishing connection" + e);
			}
          
	}    
    }  
