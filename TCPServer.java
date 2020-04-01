import java.net.ServerSocket;
import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.IOException;

public class TCPServer {
  
  private ServerSocket serverSocket; //declare object for server's socket information
  private Socket socket;             //declare an actual socket
  private ObjectInputStream input;   //declare and output stream to send a message
  
  public TCPServer() {
    
    System.out.println("Server is running...");
    try {
      // initialize socket information to receive message from the CLient on port 1098
      // & maximim number of 500 clients connected simultaneously
      
      serverSocket = new ServerSocket(1098, 500);
      while(true) { //use a loop to keep server running
        socket = serverSocket.accept(); //accept connection from client
        input = new ObjectInputStream(socket.getInputStream()); //receive output stream object
        String message = (String) input.readObject(); //convert stream byte to String
        System.out.println("Client says " + message); //display the message received from client
      }
    }
    catch (IOException ioe) { ioe.printStackTrace(); }
    catch (ClassNotFoundException cnfe) { cnfe.printStackTrace(); }
    
  }
  
  public static void main(String [] args) {
    new TCPServer();
  }
    
  
  
} //end TCPServer