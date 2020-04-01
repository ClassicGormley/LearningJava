import java.io.File;                               // allow you to create a file object to create the file
import java.io.FileWriter;                         // use this to write text to file
import java.io.IOException;                        //the exception that must be caught to write to a file

public class WriteToFile {
  
  
  public static void main (String [] args){
    
    File file = new File("data.txt");
    String sampleText = "Marymount University";
    
    try{
      
      FileWriter writer = new FileWriter(file);  // new FileWriter(file, true) appends data to the end of the file
      
      writer.write("Ryan Gormley\n");          //write method overides the file and replaces any text if not true above
      writer.write(sampleText);                //write() takes a String argument
      
      writer.close();
      
    }
    catch (IOException ioe){
      
      ioe.printStackTrace();
      
    }
    
    
  }//end main method
  
}//end WriteToFile