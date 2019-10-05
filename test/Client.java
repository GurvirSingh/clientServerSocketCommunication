import java.net.*;
import java.io.*;
import java.util.*;

public class Client {
    public static void main(String[] args) throws UnknownHostException,IOException, ClassNotFoundException {
        System.out.println("welcome client");
        Socket socket = new Socket("localhost", 4444);
        System.out.println("Client connected");

        ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
        
        System.out.println("Ok");
        Message message = new Message(new Integer(15), new Integer(32));
        os.writeObject(message);
        System.out.println("Envoi des informations au serveur ...");

        
        Message returnMessage = (Message) is.readObject();
        System.out.println("return Message is=" + returnMessage);
        socket.close();
    }
}
