import java.io.*;
import java.lang.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;


public class Client
{
    private Socket socket = null;
    private DataInputStream in = null;
    private DataOutputStream out=null;

    private Stub obj;
    public Client(String address, int port)
    {
        try 
        {
            socket = new Socket(address,port);
            System.out.println("Connection Established");

            out = new DataOutputStream (socket.getOutputStream());
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            Scanner sc = new Scanner(System.in);
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("\nEnter :- \n1 for Value of Pi \n2 to Add Two Numbers \n3 to Sort an Array \n4 to Multiply Matrices");
            String command = "";
            command = sc.nextLine();
            out.writeUTF(command);

            if(command.equals("1"))
            {
                System.out.println("Continuing Processing...");
                
                System.out.println(in.readUTF());
                try {
                    Thread.sleep(2 * 1000);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
                int i = 1;

                while(in.available() == 0) {
                    System.out.print(" "+ i++);
                }
                System.out.println(" INTERRUPT ");
            
                String result = in.readUTF();

                System.out.println("Value of Pi = "+result);

            }

            else if(command.equals("2"))
            {

            System.out.println("Enter Data Values to add:-");
            System.out.println("Enter Parameter 1:-");
            String p1,p2 = "";
            p1= reader.readLine();
            System.out.println("Enter Parameter 2:-");
            p2= reader.readLine();            
            obj = new Stub(p1,p2);
            System.out.println(in.readUTF());
              
           //Sending Packed Message to Server
           String result = obj.message(); 
           //System.out.println("THIS IS FOR TESTING "+result);
          
           //Converting the message parameters into Byte Array
           byte BT[] =  convertToBytes(result);
           
           out.write(BT, 0 ,BT.length);
           out.flush();
           
            System.out.println("Continuing Processing...");
            try {
                Thread.sleep(1 * 1000);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
            //Interrupt 
            int i = 1;

            while(in.available() == 0) {
                System.out.print(" "+ i++);
            }
            System.out.println(" INTERRUPT ");

            String value = "";
            value = in.readUTF();

            System.out.println("Result :- "+value);
            }
            else if(command.equals("3"))
            {
                System.out.println("Enter an array to sort.(Decimal Values are allowed).");
                System.out.println("Enter the size of array");
                int N = Integer.parseInt(reader.readLine());

                //Send Array Size to Server
                out.writeUTF(N+"");
                double arr[] = new double[N];

                for(int i =0; i<arr.length;i++)
                {
                    System.out.println("Enter the number at postion "+(i+1)+":- ");
                    arr[i]=Double.parseDouble(reader.readLine());
                }
                System.out.println("The Entered Array :-");
                for(int i =0;i<arr.length;i++)
                {
                    System.out.println(arr[i]);
                }

                obj = new Stub(arr);
                String message = obj.arrmessage(arr);                
                byte BT[] =  convertToBytes(message);
           
                out.write(BT, 0 ,BT.length);
                out.flush();
                System.out.println(in.readUTF());
                System.out.println("Continuing Processing...");
                try {
                    Thread.sleep(2 * 1000);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
                //Interrupt 
                int ij = 1;

                while(in.available() == 0) {
                    System.out.print(" "+ ij++);
                }
                System.out.println(" INTERRUPT ");
                
                System.out.println("Printing Sorted Array");
                for(int i=0;i<arr.length;i++)
                {
                    System.out.println(in.readUTF());
                }


            }
            else if(command.equals("4"))
            {
                System.out.println("Multiply Two Matrices");
                System.out.println("Enter the size of Matrix 1 (Dimensions MxN)");
                System.out.println("Enter the no. of rows:");
                int M = Integer.parseInt(reader.readLine());
                System.out.println("Enter the no. of columns:");
                out.writeInt(M);
                int N = Integer.parseInt(reader.readLine());
                out.writeInt(N);

                System.out.println("Enter the size of Matrix 2 (Dimensions PxQ)");
                System.out.println("Enter the no. of rows:");
                int P = Integer.parseInt(reader.readLine());
                out.writeInt(P);
                System.out.println("Enter the no. of columns:");
                int Q = Integer.parseInt(reader.readLine());
                out.writeInt(Q);

                if(N==P)
                {
                    System.out.println("Input Valid for Matrix Multiplication");
                    int[][] a = new int[M][N];
                    int[][] b = new int[P][Q];
                    int[][] c = new int[M][Q];

                    //Accepting Matrix Values from User
                    System.out.println("Enter the elements of 1st martix row wise \n");
                    for (int i = 0; i < M; i++)
                    {
                        for (int j = 0; j < N; j++)
                        {
                            System.out.print("Enter Element at ("+i+","+j+") th position. (Row,Column)");
                            a[i][j] = Integer.parseInt(reader.readLine());
                        }
                    }
                    System.out.println("Enter the elements of 2nd martix row wise \n");
                    for (int i = 0; i < P; i++)
                    {
                        for (int j = 0; j < Q; j++)
                        {
                            System.out.print("Enter Element at ("+i+","+j+") th position. (Row,Column)");
                            b[i][j] = Integer.parseInt(reader.readLine());
                        }
                    }
                    obj = new Stub(a);
                    String message = obj.message(a,b,M,N,P,Q);

                    byte BT[] =  convertToBytes(message);
           
                    out.write(BT, 0 ,BT.length);
                    out.flush();
                    
                    //Printing Matrix 1 and 2
                    System.out.println("Matrix 1 :- \n");
                    for(int i =0;i< M; i++)
                    {
                        System.out.print("[");
                        for(int j=0;j<N;j++)
                        {
                            System.out.print(" "+a[i][j]+" ");
                        }
                        System.out.print("]");
                        System.out.println();
                    }
                    System.out.println("Matrix 2 :- \n");
                    for(int i =0;i< P; i++)
                    {
                        System.out.print("[");
                        for(int j=0;j<Q;j++)
                        {
                            System.out.print(" "+b[i][j]+" ");
                        }
                        System.out.print("]");
                        System.out.println();
                    }


                    System.out.println(in.readUTF());
                    System.out.println("Continuing Processing...");
                    try {
                        Thread.sleep(2 * 1000);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                    //Interrupt 
                    int ij = 1;
        
                    while(in.available() == 0) {
                        System.out.print(" "+ ij++);
                    }
                    System.out.println(" INTERRUPT ");
                    System.out.println("Result of Matrix Multiplication - \n");
                    String TempRes="";
                    for(int i=0;i<M;i++)
                    {
                        System.out.print("[");
                        for(int j = 0;j<Q;j++)
                        {                       
                            System.out.print(in.readUTF()+"  ");

                        }
                        System.out.print("]");
                        System.out.println();
                    }
                }
                else
                {
                    System.out.println("Since Rows and Columns DO not Match, Matrices can not be multiplied.");
                    System.out.println("Closing Connection");
                    

                }



            }
            else
            {
                System.out.println("Invalid Input");
                System.out.println("Closing Connection");
                
            }





        }
        catch (Exception e) {
            System.out.println("Error in Connection" + e);
        }

    }


    public byte[] convertToBytes(String msg)
    {
        byte[] btarr = msg.getBytes(StandardCharsets.US_ASCII);
        return btarr;
    }

    public static void main(String args[])throws IOException
    {
        Client client = new Client("127.0.0.1",5004);
    }
}
