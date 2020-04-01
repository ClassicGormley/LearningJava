//objects for  creating client
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.util.Scanner;

//objects for creating server
import java.net.ServerSocket;
import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.IOException;


/**
 * Two-Way Communication
 */
public class TCPClient2 {
  
  private Socket socket; //declare an actual socket
  private ObjectOutputStream output; // declare an output sream to send a message
  
  private ServerSocket serverSocket; //declare an output for server's socket information
  private ObjectInputStream input; //declare an input stream to receive a message
  
  public TCPClient2() {
    
    System.out.println("Client is running...");
    Scanner scanner = new Scanner(System.in);
    
    try{
      
      //initialize another socket information to receive message from the server on port 1097
      // & maximum number of 500 clients connected simulatneously
      serverSocket = new ServerSocket(1097, 500);
        
      while(true) { //use a loop to keep client running
        //initialize socket to send a message to server running on port 1098
        socket = new Socket(InetAddress.getByName ("localhost"), 1098);
        //initialize output streeam object
        output = new ObjectOutputStream(socket.getOutputStream());
        System.out.print("Client says:");
        String message = scanner.nextLine(); //Scanner to type in a message sent to the client
          
        //write buffered output bytes and flush thorugh to the underlying stream
        output.writeObject(message);
        output.flush();
        
        socket = serverSocket.accept(); //accept connection from server
        input = new ObjectInputStream(socket.getInputStream()); //receive output stream object
        String message2 = (String) input.readObject(); //conver stream byte to String
        System.out.println("Server says:" + message2); //display the message received from cleint
      }
    }
    catch(ClassNotFoundException cnfe) { cnfe.printStackTrace(); }
    catch(IOException ioe) { ioe.printStackTrace(); }
  }
  
  public static void main(String [] args) {
    new TCPClient2();
  }
  
} //end TCPCLient