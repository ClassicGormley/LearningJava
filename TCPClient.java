import java.net.Socket;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.io.IOException;
import java.util.Scanner;

/**
 * One-Way Communication
 */
public class TCPClient {
  
  private Socket socket; //declare an actual socket
  private ObjectOutputStream output; // declare an output sream to send a message
  
  public TCPClient() {
   
    try{
      
      while(true) { //use a loop to keep client running
        //initialize socket to send a message to server running on port 1098
        socket = new Socket(InetAddress.getByName ("localhost"), 1098);
        //initialize output streeam object
        output = new ObjectOutputStream(socket.getOutputStream());
        
        Scanner scan = new Scanner(System.in);
        System.out.print("Client says:");
        String message = scan.nextLine(); //Scanner to type in a message sent to the client
          
        //write buffered output bytes and flush thorugh to the underlying stream
        output.writeObject(message);
        output.flush();
        System.out.println("Message sent!!!");
      }
    }
    catch(UnknownHostException uhe) { uhe.printStackTrace(); }
    catch(IOException ioe) { ioe.printStackTrace(); }
  }
  
  public static void main(String [] args) {
    new TCPClient();
  }
  
} //end TCPCLient