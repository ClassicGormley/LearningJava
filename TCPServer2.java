//objects for creating server
import java.net.ServerSocket;
import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.IOException;

//objects for creating client
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.util.Scanner;

/**
 * TCP Two-Way communication
 */
public class TCPServer2 {
  
  private ServerSocket serverSocket; //declare object for server's socket information
  private Socket socket;             //declare an actual socket
  private ObjectInputStream input;   //declare an output stream to send a message
  private ObjectOutputStream output; //declare an output stream to send a message
  
  public TCPServer2() {
    
    System.out.println("Server is running...");
    Scanner scanner = new Scanner(System.in);
    
    try {
      // initialize socket information to receive message from the CLient on port 1098
      // & maximim number of 500 clients connected simultaneously
      
      serverSocket = new ServerSocket(1098, 500);
      while(true) { //use a loop to keep server running
        socket = serverSocket.accept(); //accept connection from client
        input = new ObjectInputStream(socket.getInputStream()); //receive output stream object
        String message = (String) input.readObject(); //convert stream byte to String
        System.out.println("Client says " + message); //display the message received from client
        
        //initialize socket to send a message to client runnin gon port 1097
        socket = new Socket(InetAddress.getByName("localhost"), 1097);
        //initialize output stream object
        output = new ObjectOutputStream(socket.getOutputStream());
        System.out.print("Server says:");
        String message2 = scanner.nextLine();
        
        //write buffered output bytes and flush through to the underlying stream
        output.writeObject(message2);
        output.flush();
      }
    }
    catch (IOException ioe) { ioe.printStackTrace(); }
    catch (ClassNotFoundException cnfe) { cnfe.printStackTrace(); }
    
  }
  
  public static void main(String [] args) {
    new TCPServer2();
  }
    
  
  
} //end TCPServer2