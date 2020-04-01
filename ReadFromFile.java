import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class ReadFromFile {
  
  public static void main (String [] args){
   
    File file = new File("data.txt");
    
    try{
      Scanner reader = new Scanner(file);
      
      while (reader.hasNext()){
        System.out.println(reader.nextLine());
      }
      reader.close();
    }
    catch (FileNotFoundException fnfe){
      fnfe.printStackTrace();
    }
    
  }//end main method
  
}//end ReadFromFile