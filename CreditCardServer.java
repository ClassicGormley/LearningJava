//objects for creating server
import java.net.ServerSocket;
import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.IOException;

//objects for creating client
import java.io.ObjectOutputStream;
import java.net.InetAddress;

//database imports
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class CreditCardServer {
  
  private ServerSocket serverSocket;
  private Socket socket;
  private ObjectInputStream input;
  private ObjectOutputStream output;
  
  public CreditCardServer(){
   
    System.out.println("Server is running...");
    
    try{
      
      serverSocket = new ServerSocket(1098, 500);
      while(true){
        
        socket = serverSocket.accept();
        input = new ObjectInputStream(socket.getInputStream());
        String creditInfo = (String) input.readObject();
        String verified = verifyCreditCard(creditInfo);
        
        //send message back to client
        socket = new Socket(InetAddress.getByName("localhost"), 1097);
        output = new ObjectOutputStream(socket.getOutputStream());
        
        //write and flush 
        output.writeObject(verified);
        output.flush();
        
      }
      
    }
    catch (IOException ioe) { ioe.printStackTrace(); }
    catch (ClassNotFoundException cnfe) { cnfe.printStackTrace(); }
    
  }

  public String verifyCreditCard(String creditInfo){
    
    String url = "jdbc:mysql://localhost/hotel?user=root&password=";
    String message = "";
    try{
      
      Class.forName("com.mysql.jdbc.Driver");
      
      Connection connection = DriverManager.getConnection(url);
      
      Statement stmt = connection.createStatement();
      String query = "select * from creditcard where creditcardinfo = '" + creditInfo + "';";
      
      ResultSet result = stmt.executeQuery(query);
      result.last();
      int rowNum = result.getRow();
      if (rowNum == 0){
       message = "No"; 
      }
      else{
       message = "Yes"; 
      }
      
    }
    catch (ClassNotFoundException cnfe){ cnfe.printStackTrace(); }
    catch (SQLException sqle){ sqle.printStackTrace(); }
    
    return message;
    }
  
  public static void main (String [] args){
    new CreditCardServer();
  }

  
} //end CreditCardServer