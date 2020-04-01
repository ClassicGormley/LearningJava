

public class InvalidCreditCardException extends Exception {
  
  public InvalidCreditCardException (){
    
    super("Invalid credit card number!!! Enter 16 numbers without spaces!!!");
    
  }
  
  public InvalidCreditCardException (String message){
    
    super(message);
    
  }
  
}