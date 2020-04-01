//server imports
import java.net.ServerSocket;
import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.IOException;

//Client imports
import java.io.ObjectOutputStream;
import java.net.InetAddress;

//database imports
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class ARLibrary {
  
  private ServerSocket serverSocket;
  private Socket socket;
  private ObjectInputStream input;
  private ObjectOutputStream output;
  
  public ARLibrary (){
    
    System.out.println("Arlington Library Server is running...");
    
    try{
     
      serverSocket = new ServerSocket(1098, 500);
      while(true){
        
        socket = serverSocket.accept();
        input = new ObjectInputStream(socket.getInputStream());
        String isbnCheck = (String) input.readObject();
        String message = getISBNFromDatabase(isbnCheck);
        
        //send message to client
        socket = new Socket(InetAddress.getByName("localhost"), 1097);
        output = new ObjectOutputStream(socket.getOutputStream());
        
        //write and flush 
        System.out.println(message);
        output.writeObject(message);
        output.flush();
        
        
      }//server while loop
      
    } //end try block
    catch (IOException ioe) { ioe.printStackTrace(); }
    catch (ClassNotFoundException cnfe) {cnfe.printStackTrace(); }
  }
  
  public String getISBNFromDatabase(String isbn){
    
    String url = "jdbc:mysql://localhost/arlibrary?user=root&password=";
    String message = "";
    try{
      
      Class.forName("com.mysql.jdbc.Driver");
      
      Connection connection = DriverManager.getConnection(url);
      
      Statement stmt = connection.createStatement();
      String query = "select title from book where '" + isbn + "' = isbn;";
      
      ResultSet result = stmt.executeQuery(query);
      
      String title = "";

      
      while(result.next()){
        title = result.getString(1);
      }
      
      if (!title.equals("")){
         message = "ISBN: " + isbn + "\nTitle: " + title + "\nAvailable at Arlington Library";
      }
      else{
         message = "ISBN: " + isbn + " is not available at Arilngton Library";
      }
    }
    catch (ClassNotFoundException cnfe){ cnfe.printStackTrace(); }
    catch (SQLException sqle){ sqle.printStackTrace(); }
    
    return message;
    }
  
  public static void main (String [] args){
    new ARLibrary();
  }
  
} //end ARLibrary