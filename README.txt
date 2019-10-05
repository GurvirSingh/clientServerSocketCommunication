Assignment 1
Problem Statement:
Implement a basic single-threaded file server that supports the four operations listed above. You
will use the message-oriented communication protocol. You can assume that the client and the
server reside on the same machine but communicate with each other using different ports. Use
different folders to hold files downloaded to the client or uploaded to the server. 

Instructions To Run the Program:
1) Extract the compressed .zip folder.
2) Select Folder named "Assignment 1". The Folder contains 2 directories, 1 message file, two .java files and 2 .class files.
3) Copy the files from the folder and paste them in your work directory.
4) The next step is to open the Client.java and Server.java Programs and Change the path from "/home/gurvir/A/Work/CSE5306 Project1/Assignment1/" to your desired path. 
5) Open Terminal on your linux system.
6) Compile the Server.java file using the java command "javac Server.java".
7) Compile the Client.java file using the java command "javac Client.java".
8) After Compiling all program files, first run the server file.
9) Run the Server.java file using command "java Server".
10)Run the Client.java file using command "java Client".
11)The Client-Server connection should be established.
12)You can now perform any of the operations given in the program list by interacting with the user menu on the Client side Terminal.

The program will stop execution after completing a single operation or if there is any wrong input.


Assignment 2
Problem Statement:
Based on the single-threaded server, implement a multi-threaded file server. The client side
software does not need any changes but the server should be able to support multiple concurrent
operations. Note that you do not need to consider locking in your multi-threaded server at this
stage. Use multiple clients to test your server.

Instructions To Run the Program:
1) Extract the compressed .zip folder.
2) Select Folder named "Assignment 2". The Folder contains 2 directories, 2 message file, two .java files and 2 .class files.
3) Copy the files from the folder and paste them in your work directory.
4) The next step is to open the Client.java and Server.java Programs and Change the path from "/home/gurvir/A/Work/CSE5306 Project1/Assignment2/" to your desired path. 
5) Open Terminal on your linux system.
6) Compile the Server.java file using the java command "javac Server.java".
7) Compile the Client.java file using the java command "javac Client.java".
8) After Compiling all program files, first run the server file.
9) Run the Server.java file using command "java Server".
10)Run the Client.java file using command "java Client".
11)The Client-Server connection should be established.
12)You can now perform any of the operations given in the program list by interacting with the user menu on the Client side Terminal.

The program will stop execution after completing a single operation or if there is any wrong input.


Assignment 3
Problem Statement:
Use the following design of the synchronous RPCs to implement a computation server. The server
supports four RPCs: calculate_pi(), add(i, j), sort(arrayA),
matrix_multiply(matrixA, matrixB, matrixC). The RPCs represent different
ways to pass the parameters to the server. You need to implement a client stub to pack
parameters into a message sent to the server and a server stub to unpack the parameters. You are
NOT allowed to use Java remote method invocation (RMI) or RPC in other programming
languages to implement the RPC-like communication

Instructions To Run the Program:
1) Extract the compressed .zip folder.
2) Select Folder named "Assignment 3". The Folder contains three .java files and 3 .class files.
3) Copy the files from the folder and paste them in your work directory.
4) Open Terminal on your linux system.
5) Compile the Server.java file using the java command "javac Server.java".
6) Compile the Client.java file using the java command "javac Client.java".
7) Compile the Stub.java   file using the java command "javac Stub.java".
8) After Compiling all program files, first run the server file.
9) Run the Server.java file using command "java Server".
10)Run the Client.java file using command "java Client".
11)The Client-Server connection should be established.
12)You can now perform any of the operations given in the program list by interacting with the user menu on the Client side Terminal.

The program will stop execution after completing a single operation or if there is any wrong input.


Assignment 4
Problem Statement:
Re-implement the computation server using asynchronous and deferred synchronous
RPCs. For asynchronous RPC, the server immediately acknowledges a RPC call before it
actually performs a computation. The result of the computation is saved in a table on
the server, which can be looked up by the client for the RPC result. The design of the
client will be slightly different from that in the synchronous RPC. Instead of waiting for
a synchronous RPC to return, the client using an asynchronous RPC switches to other
computations and queries the server for the RPC result at a later time. For deferred
synchronous RPC, you need to devise a mechanism to interrupt the client to return the
RPC result to the client when the server has completed its local computation. 

Instructions To Run the Program:
1) Extract the compressed .zip folder.
2) Select Folder named "Assignment 4". The Folder contains three .java files and 3 .class files.
3) Copy the files from the folder and paste them in your work directory.
4) Open Terminal on your linux system.
5) Compile the Server.java file using the java command "javac Server.java".
6) Compile the Client.java file using the java command "javac Client.java".
7) Compile the Stub.java   file using the java command "javac Stub.java".
8) After Compiling all program files, first run the server file.
9) Run the Server.java file using command "java Server".
10)Run the Client.java file using command "java Client".
11)The Client-Server connection should be established.
12)You can now perform any of the operations given in the program list by interacting with the user menu on the Client side Terminal.

The program will stop execution after completing a single operation or if there is any wrong input.