import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.GridLayout;
import javax.swing.JCheckBox;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class DeliveryService3 extends JFrame implements ActionListener {
   
  private JLabel weightLabel, deliveryLabel;
  private JTextField weightTextField;
  private JPanel northPanel, centerPanel, radioPanel, southPanel, centerPanel2;
  private JRadioButton localRadioButton, longRadioButton;
  private ButtonGroup distanceButtonGroup;
  private JCheckBox insuranceBox;
  private JButton goButton, resetButton;
  
  
  public DeliveryService3 (){
    
    super("Delivery Service");
    
    //******** NORTH ********//
    
    weightLabel = new JLabel("Enter weight in pounds:");
    weightTextField = new JTextField(5);
    northPanel = new JPanel();
    northPanel.add(weightLabel);
    northPanel.add(weightTextField);
    
    add(northPanel,BorderLayout.NORTH);
    
    //******** CENTER ********//
    
    deliveryLabel = new JLabel("Type of Delivery:");
    localRadioButton = new JRadioButton("Local");
    longRadioButton = new JRadioButton("Long Distance");
    distanceButtonGroup = new ButtonGroup();
    distanceButtonGroup.add(localRadioButton);
    distanceButtonGroup.add(longRadioButton);
    
    insuranceBox = new JCheckBox("Insurance");
    
    radioPanel = new JPanel();
    radioPanel.add(deliveryLabel);
    radioPanel.add(localRadioButton);
    radioPanel.add(longRadioButton);
    
    centerPanel = new JPanel();
    centerPanel.setLayout(new BorderLayout());
    centerPanel.add(radioPanel, BorderLayout.NORTH);
    centerPanel.add(insuranceBox, BorderLayout.CENTER);
    
    centerPanel2 = new JPanel();
    centerPanel2.add(centerPanel);
    
    add(centerPanel2, BorderLayout.CENTER);
    
    //******** SOUTH ********//
    
    goButton = new JButton("Go");
    goButton.addActionListener(this);
    resetButton = new JButton("Reset");
    resetButton.addActionListener(this);
    
    
    southPanel = new JPanel();
    southPanel.add(goButton);
    southPanel.add(resetButton);
    
    add(southPanel, BorderLayout.SOUTH);

    //******** Main Frame ********//
    
    setSize(500,300);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }
  
  
  public void actionPerformed(ActionEvent e){
    
    if (e.getSource() == goButton){
      
      String weightString = weightTextField.getText();
      double weight = Double.parseDouble(weightString);
      String distance = "";
      String insurance = "No";
      
      if (localRadioButton.isSelected()){
        distance = "Local";
      }
      if (longRadioButton.isSelected()){
        distance = "Long Distance";
      }
      
      if (insuranceBox.isSelected()){
        insurance = "Yes";
      }
      
      double price = getPrice(weight, distance, insurance);
      
      String output = "Delivery Information" +
                      "\nWeight: " + weight + 
                      "\nType of Delivery: " + distance + 
                      "\nInsurance: " + insurance + 
                      "\nPrice: $" + Math.round(price*100.0)/100.0;
                      
                      
      
      JOptionPane.showMessageDialog(null, output);
      
    }
    else if (e.getSource() == resetButton){
      
      weightTextField.setText("");
      distanceButtonGroup.clearSelection();
      insuranceBox.setSelected(false);
      
    }
    
  }
  
  public double getPrice(double weight, String distance, String insurance){
    
    double price = 0.0;
    
    if (distance.equals("Local")){
      
      if (weight > 20){
        price = 20.75;
      }
      
      else if (weight >= 5 && weight <= 20){
        price = 10.75;
      }
      
      else if (weight < 5 && weight > 0){
        price = 5.75;
      }
      
    }
        
    else if(distance.equals("Long Distance")){
      
      if (weight < 5 && weight > 0){
        price = 35.75;
      }
      else if (weight >= 5){
        price = 47.75;
      }
    }
    
    if (insurance.equals("Yes")){
      price += 4;
    }
    return price;
  }
  
  public static void main (String [] args){
    
    DeliveryService3 box = new DeliveryService3 ();
    
  }//end main method
  
}//End DeliveryService3