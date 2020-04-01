

public class MultiThreadExample {
  
  public static void main (String [] args){
    
    Thread task1 = new Thread(new CountExample("Task 1"));
    task1.start();
    
    Thread task2 = new Thread(new CountExample("Task 2"));
    task2.start();
    
  }//end main method
  
} //end MultiThreadExample

class CountExample implements Runnable{
  
  String task;
  
  public CountExample(String newTask){
    
    task = newTask;
    
  }
  
  public void run(){
    
    System.out.println(task);
    
    try{
      
      for(int i = 1; i <= 5; i++){
       
        int num = (int) ((Math.random() * 10) % 10) * 1000;
        
        Thread.sleep(num);
        
        System.out.println(task + " " + i + " sleep = " + num);
        
      }
      System.out.println(task + " Done!");
      
    }
    catch(InterruptedException ie) { ie.printStackTrace(); }
    
  }//end run method
  
}//end CountExample