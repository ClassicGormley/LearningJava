import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.GridLayout;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.Label;

public class GasStation5 extends JFrame implements ActionListener{
  
  private JButton payButton, printReceiptButton, clearButton;
  private JPanel buttonPanel, gasPanel, centerPanel, carWashPanel;
  private JTextField gasCostField;
  private JLabel dollarLabel, gasLabel, carWashLabel;
  private ImageIcon gasImage, carWashImage;
  private JCheckBox carWashBox;
  
  
  public GasStation5() {

    super ("Gas Station Cashier");
    
    //******** CENTER ********//
    
    dollarLabel = new JLabel("$");
    gasCostField = new JTextField(5);
    gasImage = new ImageIcon("gas.gif");
    gasLabel = new JLabel(gasImage);
    
    gasPanel = new JPanel();
    gasPanel.add(gasLabel);
    gasPanel.add(dollarLabel);
    gasPanel.add(gasCostField);
    
    carWashBox = new JCheckBox("$10.00");
    carWashImage = new ImageIcon ("carwash.gif");
    carWashLabel = new JLabel(carWashImage);
    
    carWashPanel = new JPanel();
    carWashPanel.add(carWashBox);
    carWashPanel.add(carWashLabel);
    
    centerPanel = new JPanel();
    centerPanel.setLayout(new GridLayout (2,1));
    centerPanel.add(gasPanel);
    centerPanel.add(carWashPanel);
    
    add(centerPanel, BorderLayout.CENTER);
    
    //******** SOUTH ********//
    
    payButton = new JButton ("Pay");
    printReceiptButton = new JButton ("Print Receipt");
    clearButton = new JButton ("Clear");
    buttonPanel = new JPanel();
    
    //actions
    payButton.addActionListener(this);
    printReceiptButton.addActionListener(this);
    clearButton.addActionListener(this);
   
    buttonPanel.add(payButton);
    buttonPanel.add(printReceiptButton);
    buttonPanel.add(clearButton);
    
    add(buttonPanel, BorderLayout.SOUTH);
    
    /**
     * Create Main Frame
     */
    
    setSize(500,300);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }
  
  /**
   * ActionPerformed method
   */
  public void actionPerformed (ActionEvent e){
    
    if (e.getSource() == payButton){
      
      String carWash = "No";
      double gasAmount = 0;
      double totalPurchase = 0;
      try{
      
        if (gasCostField.getText().equals("")) {
          throw new AmountException ();
          
        }
       
        
         gasAmount = Double.parseDouble(gasCostField.getText());
         
         if (gasAmount <= 0){
           throw new AmountException ("Error: Your dollar amount is either a zero or negative number!!");
         }
         
         totalPurchase = gasAmount;
               
      if (carWashBox.isSelected()){
        carWash = "Yes";
        totalPurchase += 10;
      }
      
      String output = "Receipt \nGas Amount: $" + String.format("%.2f", gasAmount) + "\nCar Wash: " + carWash
                      + "\nTotal Fee: " + String.format("%.2f", totalPurchase);
      JOptionPane.showMessageDialog(null, output);
      
      
      File receipt = new File("receipt.txt");
      
      try{
        
        FileWriter writer = new FileWriter (receipt);
        writer.write(output);
        writer.close();
        
      }//write to file
      catch (IOException ioe){
        
      }//File IOException error
      
      }//Amount is not 0
      
      catch (AmountException exc){
        
        JOptionPane.showMessageDialog(null, exc.getMessage());
        
      }//AmountException Error

      
    }
    
    else if (e.getSource() == printReceiptButton){
      
      File receipt = new File("receipt.txt");
      
      try {
        
        Scanner reader = new Scanner(receipt);
        
        while(reader.hasNext()){
          System.out.println(reader.nextLine());
        }
        reader.close();
      }
      
      catch(FileNotFoundException fnfe){
        
      }
      
    }
    
    else if (e.getSource() == clearButton){
      
      gasCostField.setText("");
      carWashBox.setSelected(false);
      
    }
    
  }
  
  
  
  /**
   * Main Method
   */
  public static void main (String [] args){
    
    GasStation5 frame = new GasStation5();
    
  }//end main method
  
  
  
}//end GasStation5