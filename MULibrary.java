import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

//client imports
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.util.Scanner;

//server imports
import java.net.ServerSocket;
import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.IOException;

public class MULibrary extends JFrame implements ActionListener {
  
  //GUI variables
  private JLabel isbnLabel, titleLabel, northLabel;
  private JTextField isbnBox, titleBox;
  private JButton addBookButton, searchMULibraryButton, searchArlingtonLibraryButton; 
  private JPanel northPanel, southPanel;
  
  //server variables
  private Socket socket;
  private ObjectOutputStream output;
  private ServerSocket serverSocket;
  private ObjectInputStream input;
  
  
  public MULibrary(){
    super("MU Library");
    
    //******** North ********//
    isbnLabel = new JLabel("ISBN:");
    titleLabel = new JLabel("Title:");
    isbnBox = new JTextField(15);
    titleBox = new JTextField(15);
    
    northPanel = new JPanel(new GridLayout(2,2));
    northPanel.add(isbnLabel);
    northPanel.add(isbnBox);
    northPanel.add(titleLabel);
    northPanel.add(titleBox);
    northPanel.setBorder(BorderFactory.createEmptyBorder(10,40,10,40));
    add(northPanel, BorderLayout.NORTH);
    
    //******** South ********//
    addBookButton = new JButton("Add New Book");
    searchMULibraryButton = new JButton("Search ISBN - MULibrary");
    searchArlingtonLibraryButton = new JButton("Search ISBN - Arlington Library");
    
    //ActionListener
    addBookButton.addActionListener(this);
    searchMULibraryButton.addActionListener(this);
    searchArlingtonLibraryButton.addActionListener(this);
    
    southPanel = new JPanel();
    southPanel.add(addBookButton);
    southPanel.add(searchMULibraryButton);
    southPanel.add(searchArlingtonLibraryButton);
    southPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
    add(southPanel, BorderLayout.SOUTH);

    //******** Main Frame ********//

    setSize(650,200);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
    
  }
  
  public void actionPerformed (ActionEvent e){
    
    if (e.getSource() == addBookButton){
      
      String isbn = isbnBox.getText();
      String title = titleBox.getText();
      
      if(!isbn.equals("") && isbn.length() <= 9){
        updateDatabase(isbn,title);
      }
      else{
        JOptionPane.showMessageDialog(null, "Error ISBN invalid");
      }
      
    }
    else if (e.getSource() == searchMULibraryButton){
      
      String isbn = isbnBox.getText();
      if (!isbn.equals("") && isbn.length() <= 9){
        getDatabaseFromISBN(isbn);
      }
      else{
        JOptionPane.showMessageDialog(null, "Error ISBN invalid");
      }
      
    }
    else if (e.getSource() == searchArlingtonLibraryButton){
      
      String isbn = isbnBox.getText();
      String message = "";
      
      if (!isbn.equals("") && isbn.length() <= 9){
        message = getFromARLibrary(isbn);
        if(!message.equals("")){
          JOptionPane.showMessageDialog(null, message);
        }
      }
      else{
        JOptionPane.showMessageDialog(null, "Error ISBN invalid");
      }
      

      
    }
    
  }
  
  public void updateDatabase(String isbn, String title){
    
    String url = "jdbc:mysql://localhost/mulibrary?user=root&password=";
    
    try{
      
      Class.forName("com.mysql.jdbc.Driver");
      
      Connection connection = DriverManager.getConnection(url);
      
      String insertStatement = "insert into books values('" + isbn + "', '" + title + "');";

      Statement stmt = connection.createStatement();
      stmt.execute(insertStatement);
      stmt.execute("commit;");

      JOptionPane.showMessageDialog(null,"ISBN: " + isbn + "\nTitle:" + title +"\nAdded Successfully");
      
     }
    
    catch (ClassNotFoundException cnfe){
      cnfe.printStackTrace();
    }
    catch (SQLException sqle){
     sqle.printStackTrace(); 
    }
    
  }
  
  
  public void getDatabaseFromISBN(String isbn){
    
    String url = "jdbc:mysql://localhost/mulibrary?user=root&password=";
    
    try{
      
      Class.forName("com.mysql.jdbc.Driver");
      
      Connection connection = DriverManager.getConnection(url);
      
      Statement stmt = connection.createStatement();
      String query = "select title from books where '" + isbn + "' = isbn;";
      
      ResultSet result = stmt.executeQuery(query);
      
      String title = "";
      
      while(result.next()){
        title = result.getString(1);
      }
      
      if (!title.equals("")){
        JOptionPane.showMessageDialog(null, "ISBN: " + isbn + "\nTitle: " + title + "\nAvailable at MU Library");
      }
      else{
        JOptionPane.showMessageDialog(null, "ISBN: " + isbn + " is not available at MU Library"); 
      }
      
    }
    
    catch (ClassNotFoundException cnfe){
      cnfe.printStackTrace();
    }
    catch (SQLException sqle){
      sqle.printStackTrace();
    }
  }
  
  private String getFromARLibrary(String isbn){
    
    System.out.println("Client running...");
    String message = "";
    try {
      
      serverSocket = new ServerSocket(1097,500);
      
      while(message.equals("")){
       socket = new Socket(InetAddress.getByName("localhost"), 1098);
       
       output = new ObjectOutputStream(socket.getOutputStream());
       output.writeObject(isbn);
       output.flush();
       
       socket = serverSocket.accept();
       input = new ObjectInputStream(socket.getInputStream());
       message = (String) input.readObject();
       
      }
      serverSocket.close();
      System.out.println("Client not running!");
      
    }
    catch (ClassNotFoundException cnfe) { cnfe.printStackTrace(); }
    catch (IOException ioe) { ioe.printStackTrace(); }
    return message;
    
  }
  
  
  public static void main (String [] args){
    MULibrary frame = new MULibrary();
  }
}