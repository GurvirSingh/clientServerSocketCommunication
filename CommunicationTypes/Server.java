import java.net.*;
import java.io.*;
import java.lang.*;
import java.util.*;

public class Server 
{

	private Socket socket = null;
	private ServerSocket server = null;
	private DataInputStream in = null;
    private DataOutputStream ou = null;
    
    private Stub obj = null;

    public Server(int port) 
    {

		try {
			server = new ServerSocket(port);
			System.out.println("Waiting for Connection");

			socket = server.accept();
			System.out.println("\nConnection Established");
			
			in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            ou = new DataOutputStream(socket.getOutputStream());
            
            ByteArrayOutputStream baos = new ByteArrayOutputStream();


            String command = "";
            command = in.readUTF();
            System.out.println("Entered Option = "+command);

            if(command.equals("1"))
            {
                System.out.println("Request Received");
		System.out.println("Acknowledgement Sent: OK");
                ou.writeUTF("Pi Value Acknowledgement: OK");

                try {
                    Thread.sleep(1 * 1000);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }

                double result = 0;
                result = CalculatePi();
                //Sending Result to Client
                
                ou.writeUTF(result+"");


                
            }

            else if(command.equals("2"))
            {
                System.out.println("Request Received");
		System.out.println("Acknowledgement Sent: OK");
                ou.writeUTF("ADD Acknowledgement: OK");

                try {
                    Thread.sleep(5 * 1000);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }

                String tempst = "";

               byte buffer[] = new byte[1024];
               baos.write(buffer, 0 , in.read(buffer));

               byte Res[] = baos.toByteArray();

               String SRes = new String(Res);



                System.out.println("RECEIVED PACKAGE");

                
                
                String str[] = SRes.split("/",2);

                int p1 = Integer.parseInt(str[0]);
                int p2 = Integer.parseInt(str[1]);
                
               

                String result = ""+(Add(p1,p2));
                System.out.println("Result =" +result);

                //Sending Result to Client
                
                ou.writeUTF(result);
                
            }
            else if(command.equals("3"))
            {
                System.out.println("Request Received");
		System.out.println("Acknowledgement Sent: OK");
                ou.writeUTF("Array Sort Acknowledgement: OK");
                
                String size = in.readUTF();
                int N = Integer.parseInt(size);
                System.out.println("Array Size = "+size);
               
                byte buffer[] = new byte[1024];
               baos.write(buffer, 0 , in.read(buffer));

               byte Res[] = baos.toByteArray();


               String SRes = new String(Res);


                System.out.println("Received Package");

                String[] str = SRes.split(",",N);
                double[] arr = new double[N];

                  for(int i =0; i <N; i++)
                  {
                   arr[i]=Double.parseDouble(str[i]);
                  }

               
                Sort(arr,N);

                try {
                    Thread.sleep(5 * 1000);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
                //Print Sorted Array TEST
                for(int i =0; i<arr.length;i++)
                {
                    //System.out.println(arr[i]);
                    ou.writeUTF(arr[i]+"");
                }

            }
            else if(command.equals("4"))
            {
                System.out.println("Request Received");
		System.out.println("Acknowledgement Sent: OK");
                ou.writeUTF("Matrix Multiplication Acknowledgement: OK");

                
                int M = in.readInt();
                int N = in.readInt();
                int P = in.readInt();
                int Q = in.readInt();
                System.out.println("Array Size = "+M+","+N+","+P+","+Q);

                byte buffer[] = new byte[1024];
                baos.write(buffer, 0 , in.read(buffer));
 
                byte Res[] = baos.toByteArray();

 
                String SRes = new String(Res);


                String[] str = SRes.split("/",2);
                String[] str1 = str[0].split(",",1000);
                String[] str2 = str[1].split(",",1000);

                int sum1 = M*N;
                int sum2 = P*Q;
                int temp=0;

                //Preparing Int Matrix from Message
                int[][] l= new int[M][N];
                int[][] m= new int[P][Q];
                  
                    int d = 0;
                    for(int j= 0; j<M;j++)
                   {
                    for(int k=0; k<N; k++)
                    {
                        temp=Integer.parseInt(str1[d++]);
                        l[j][k] = temp;                   
                    }
                   }
                  
                  temp = 0;                 
                    d=0;
                    for(int j= 0; j<P;j++)
                   {
                    for(int k=0; k<Q; k++)
                    {
                        temp=Integer.parseInt(str2[d++]);
                        m[j][k] = temp;                   
                    }
                   }
                  
                  

                  //Printing Matrices
                  System.out.println("Matrix 1 :- ");
                  for (int i = 0; i < M; i++)
                  {
                      for (int j = 0; j < N; j++)
                      {
                          System.out.print(l[i][j]);
                      }
                      System.out.println();
                  }
                  System.out.println("Matrix 2 :- ");
                  for (int i = 0; i < P; i++)
                  {
                      for (int j = 0; j < Q; j++)
                      {
                          System.out.print(m[i][j]);
                      }
                      System.out.println();
                  }

                  //Multiplying Matrices
                  int soln[][] = new int[M][Q];
                 soln = MatrixMul(l,m,M,Q,N);

                 try {
                    Thread.sleep(5 * 1000);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }

                 //Answer 
                 for (int i = 0; i < M; i++)
                  {
                      for (int j = 0; j < Q; j++)
                      {
                          //System.out.print(soln[i][j]);
                          ou.writeUTF(soln[i][j]+"");
                          
                      }
                                           
                  }                        



            }
            else
            {
                System.out.println("Invalid Input");
                System.out.println("Closing Connection");
                socket.close();
            }

        }		
        catch(Exception e)
        {
			System.out.println("Error in establishing connection");
        }
    
    }

    public int Add(int a,int b)
    {
        int sum = 0;
         sum = a+b;
         return sum;

    }
    public double CalculatePi()
    {
        double ans = Math.PI;
        return ans;
    }
    public void Sort(double arr[],int N)
    {
        

        //Sorting
        double temp=0.0;
        for(int i =0;i<arr.length;i++)
        {
            for(int j = 0; j<arr.length-1-i;j++)
            {
                if(arr[j]>arr[j+1])
                {
                    temp = arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;

                }
            }
        }


    }
    public int[][] MatrixMul(int m1[][],int m2[][],int s1,int s2, int s3)
    {
        int[][] c = new int[s1][s2];
        for (int i = 0; i < s1; i++)
        {
            for (int j = 0; j < s2; j++)
            {
                for (int k = 0; k < s3; k++)
                {
                    c[i][j] = c[i][j] + m1[i][k] * m2[k][j];
                }
            }
        }
        return c;
    }
    public static void main(String args[]) 
        {
            Server server = new Server(5004);
        }
}
    
