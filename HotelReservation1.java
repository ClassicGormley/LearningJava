import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.BorderFactory;

public class HotelReservation1 extends JFrame implements ActionListener{
  
  private JLabel fnLabel, lnLabel, sRequestLabel, checkInDateLabel, creditCardLabel,
                 checkOutDateLabel, numOfRoomsLabel, guestsPerRoomLabel, 
                 specialRatesLabel, roomTypesLabel, billingAddressLabel,
                 emptyLabel, streetLabel, cityLabel, stateLabel, zipLabel, phoneLabel,
                 visaImage, masterImage, amexImage, discoverImage, creditNumLabel, expirationLabel;
  private JTextField fnTextField, lnTextField, checkInTextField, checkOutTextField,
                     streetTextField, cityTextField, zipTextField, phoneTextField, 
                     creditNumTextField, expirationTextField;
  private JPanel northPanel, southPanel, roomInfoPanel, centerPanel,
                 requestPanel, westPanel, billingAddressPanel, creditPanel,
                 creditPanel2, creditGridPanel;
  private JButton submitButton, cancelButton;
  private JTextArea sRequestTextArea;
  private JScrollPane sRequestPane;
  private JComboBox numRoomsBox, guestsRoomBox, stateComboBox;
  private JRadioButton specRatesCheck1, specRatesCheck2, specRatesCheck3, specRatesCheck4,
                       visaButton, masterButton, amexButton, discoverButton;
  private JCheckBox studioCheck, standardCheck, familyCheck, suiteCheck;
  private ButtonGroup specialRatesGroup, creditButtonGroup;
  private ImageIcon visaPic, masterPic, amexPic, discoverPic;
  
  
  public HotelReservation1 (){
    
    super("Hotel Reservation System");
    
    //******** NORTH ********//
    
    //initialze labels and text fields
    fnLabel = new JLabel("First Name: ");
    lnLabel = new JLabel("Last Name: ");
    fnTextField = new JTextField(10);
    lnTextField = new JTextField(10);
    northPanel = new JPanel();
    
    //decorate northPanel
    Color nsColor = new Color(204,255,255);
    northPanel.setBackground(nsColor);
    northPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
    
    //add items to northPanel
    northPanel.add(fnLabel);
    northPanel.add(fnTextField);
    northPanel.add(lnLabel);
    northPanel.add(lnTextField);
    
    //add northPanel to NORTH section
    add(northPanel, BorderLayout.NORTH);
    
    //******** CENTER ********//
    
    //Billing Address Section
    billingAddressLabel = new JLabel("Billing Address:");
    billingAddressLabel.setForeground(Color.RED);
    emptyLabel = new JLabel("");
    streetLabel = new JLabel("Street:");
    streetTextField = new JTextField(10);
    cityLabel = new JLabel("City:");
    cityTextField = new JTextField(10);
    stateLabel = new JLabel("State:");
    final String STATES [] = {"", "AK", "AL", "AR", "AZ", "CA", "CO", "CT", "DC", "DE", 
                              "FL", "GA", "HI", "IA", "ID", "IL", "IN", "KS", "KY", "LA",
                              "MA", "MD", "ME", "MI", "MN", "MS", "MO", "MT", "NC", "ND",
                              "NE", "NH", "NJ", "NM", "NV", "NY", "OH", "OK", "OR", "PA", 
                              "RI", "SC", "SD", "TN", "TX", "UT", "VA", "VT", "WA", "WI",
                              "WV", "WY"};
    stateComboBox = new JComboBox(STATES);
    zipLabel = new JLabel("Zip Code:");
    zipTextField = new JTextField(10);
    phoneLabel = new JLabel("Phone:");
    phoneTextField = new JTextField(10);
    
    //Billing Address Panel
    billingAddressPanel = new JPanel();
    billingAddressPanel.setLayout(new GridLayout(6,2));
    billingAddressPanel.add(billingAddressLabel);
    billingAddressPanel.add(emptyLabel);
    billingAddressPanel.add(streetLabel);
    billingAddressPanel.add(streetTextField);
    billingAddressPanel.add(cityLabel);
    billingAddressPanel.add(cityTextField);
    billingAddressPanel.add(stateLabel);
    billingAddressPanel.add(stateComboBox);
    billingAddressPanel.add(zipLabel);
    billingAddressPanel.add(zipTextField);
    billingAddressPanel.add(phoneLabel);
    billingAddressPanel.add(phoneTextField);
    
    //Credit Card Panel
    creditCardLabel = new JLabel("Credit Card Information:");
    creditCardLabel.setForeground(Color.RED);
    creditPanel = new JPanel();
    visaButton = new JRadioButton("");
    visaPic = new ImageIcon("visa.gif");
    visaImage = new JLabel(visaPic);
    masterButton = new JRadioButton("");
    masterPic = new ImageIcon("master.gif");
    masterImage = new JLabel(masterPic);
    amexButton = new JRadioButton("");
    amexPic = new ImageIcon("amex.gif");
    amexImage = new JLabel(amexPic);
    discoverButton = new JRadioButton("");
    discoverPic = new ImageIcon("discover.gif");
    discoverImage = new JLabel(discoverPic);
    
    creditButtonGroup = new ButtonGroup();
    creditButtonGroup.add(visaButton);
    creditButtonGroup.add(masterButton);
    creditButtonGroup.add(amexButton);
    creditButtonGroup.add(discoverButton);
  
   
    creditPanel.add(visaButton);
    creditPanel.add(visaImage);  
    creditPanel.add(masterButton);
    creditPanel.add(masterImage);
    creditPanel.add(amexButton);
    creditPanel.add(amexImage);
    creditPanel.add(discoverButton);
    creditPanel.add(discoverImage);

    //credit number section
    creditNumLabel = new JLabel("Credit Card Number:");
    creditNumTextField = new JTextField(10);
    expirationLabel = new JLabel("Expiration (mm/yy):");
    expirationTextField = new JTextField(5);
    
    creditPanel2 = new JPanel();
    creditPanel2.add(creditNumLabel);
    creditPanel2.add(creditNumTextField);
    creditPanel2.add(expirationLabel);
    creditPanel2.add(expirationTextField);
    
    //Credit Grid
    creditGridPanel = new JPanel();
    creditGridPanel.setLayout(new GridLayout(3,1));
    creditGridPanel.add(creditCardLabel);
    creditGridPanel.add(creditPanel);
    creditGridPanel.add(creditPanel2);
    
    //Center panel
    centerPanel = new JPanel();
    centerPanel.setLayout(new GridLayout(2,1));
    centerPanel.add(billingAddressPanel);
    centerPanel.add(creditGridPanel);
    centerPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
    
    
    add(centerPanel, BorderLayout.CENTER);
    
    //******** WEST ********//
    
    //initialize variables
    
    String numRoomsOptions [] = {"", "1", "2", "3", "4-9", "10-25", "26+"};
    String guestRoomsNums [] = {"", "1","2","3","4","5","6"};
    
    //Check in and Room
    checkInDateLabel = new JLabel("Check-in Date (mm/dd/yy)");
    checkOutDateLabel = new JLabel("Check-out Date (mm/dd/yy)");
    checkInTextField = new JTextField();
    checkOutTextField = new JTextField();
    numOfRoomsLabel = new JLabel("No. of Rooms");
    guestsPerRoomLabel = new JLabel("Guests/Room");
    numRoomsBox = new JComboBox(numRoomsOptions);
    guestsRoomBox = new JComboBox(guestRoomsNums);
    
    //Special Rates
    specialRatesLabel = new JLabel("Special Rates");
    specRatesCheck1 = new JRadioButton("None");
    specRatesCheck2 = new JRadioButton("AAA/CAA");
    specRatesCheck3 = new JRadioButton("Senior Discount");
    specRatesCheck4 = new JRadioButton("Government & Military");
    specialRatesGroup = new ButtonGroup();
    specialRatesGroup.add(specRatesCheck1);
    specialRatesGroup.add(specRatesCheck2);
    specialRatesGroup.add(specRatesCheck3);
    specialRatesGroup.add(specRatesCheck4);
    
    //Room Types
    roomTypesLabel = new JLabel("Room Types");
    studioCheck = new JCheckBox("Studio");
    standardCheck = new JCheckBox("Standard");   
    familyCheck = new JCheckBox("Family");
    suiteCheck = new JCheckBox("Suite");
    
    //Add special Request box
    sRequestLabel = new JLabel("Special Requests");
    sRequestTextArea = new JTextArea(30, 20);
    sRequestPane = new JScrollPane(sRequestTextArea);
    requestPanel = new JPanel();
    requestPanel.setLayout(new GridLayout(3,1));
    requestPanel.add(sRequestLabel);
    requestPanel.add(sRequestPane);
    
    //Panel to hold Room info
    roomInfoPanel = new JPanel();
    roomInfoPanel.setLayout(new GridLayout(9,2));
    
    //add Components to gridPanel
    roomInfoPanel.add(checkInDateLabel);
    roomInfoPanel.add(checkOutDateLabel);    
    roomInfoPanel.add(checkInTextField);
    roomInfoPanel.add(checkOutTextField);    
    roomInfoPanel.add(numOfRoomsLabel);
    roomInfoPanel.add(guestsPerRoomLabel);
    roomInfoPanel.add(numRoomsBox);
    roomInfoPanel.add(guestsRoomBox);   
    roomInfoPanel.add(specialRatesLabel);
    roomInfoPanel.add(roomTypesLabel);
    roomInfoPanel.add(specRatesCheck1);
    roomInfoPanel.add(studioCheck);
    roomInfoPanel.add(specRatesCheck2);
    roomInfoPanel.add(standardCheck);
    roomInfoPanel.add(specRatesCheck3);
    roomInfoPanel.add(familyCheck);
    roomInfoPanel.add(specRatesCheck4);
    roomInfoPanel.add(suiteCheck);


    
    //add panels together
    westPanel = new JPanel();
    westPanel.setLayout(new GridLayout(2,1));   
    westPanel.add(roomInfoPanel);
    westPanel.add(requestPanel);
    westPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
    
    //add to main Frame
    add(westPanel, BorderLayout.WEST);
    

    
    
    //******** SOUTH ********//
    
    //initialize buttons
    
    submitButton = new JButton("Submit");
    submitButton.addActionListener(this);
    cancelButton = new JButton("Cancel");
    cancelButton.addActionListener(this);
    
    //add buttons to southPanel
    
    southPanel = new JPanel();
    southPanel.add(submitButton);
    southPanel.add(cancelButton);
    
    //decorate southPanel
    southPanel.setBackground(nsColor);
    
    //add to SOUTH section
    add(southPanel, BorderLayout.SOUTH);
    
    //******** Main Frame ********//
    
    setSize(900,500);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
    
  }
  
  /**
   * actionPerformed method
   */
  
  public void actionPerformed (ActionEvent e){
    
    if (e.getSource() == submitButton) {
      
      String firstName = fnTextField.getText();
      String lastName = lnTextField.getText();
      String checkInDate = checkInTextField.getText();
      String checkOutDate = checkOutTextField.getText();
      String numRooms = numRoomsBox.getSelectedItem().toString();
      String guestsRoom = guestsRoomBox.getSelectedItem().toString();
      String specialRates = "";
      String roomTypes = "";
      boolean studio = false, standard = false, family = false, suite = false;
      String specialRequests = sRequestTextArea.getText();
      String street = streetTextField.getText();
      String city = cityTextField.getText();
      String state = stateComboBox.getSelectedItem().toString();
      String zipCode = zipTextField.getText();
      String phoneNumber = phoneTextField.getText();
      String creditCardType = "";
      String creditCardNum = creditNumTextField.getText();
      String creditCardExp = expirationTextField.getText();
      
      
      //Special Rate Check
      if (specRatesCheck1.isSelected()){
        specialRates = "None";
      }
      else if (specRatesCheck2.isSelected()){
        specialRates = "AAA/CAA";
      }
      else if (specRatesCheck3.isSelected()){
        specialRates = "Senior Discount";
      }
      else if (specRatesCheck4.isSelected()){
        specialRates = "Government & Military";
      }
      
      //Room types check
      
      if (studioCheck.isSelected()){
        studio = true;
        roomTypes += "\n\t-Studio";
      }
      else{
        studio = false;
      }
      if (standardCheck.isSelected()){
        standard = true;
        roomTypes += "\n\t-Standard";
      }
      else{
        standard = false;
      }
      if (familyCheck.isSelected()){
        family = true;
        roomTypes += "\n\t-Family";
      }
      else{
        family = false;
      }
      if (suiteCheck.isSelected()){
        suite = true;
        roomTypes += "\n\t-Suite";
      }
      else{
        suite = false;
      }
      
      //CC Type Check
      
      if (visaButton.isSelected()){
        creditCardType = "Visa";
      }
      else if (masterButton.isSelected()){
        creditCardType = "Master Card";
      }
      else if (amexButton.isSelected()){
        creditCardType = "Amex";
      }
      else if (discoverButton.isSelected()){
        creditCardType = "Discover";
      }
      

      
      //Output Box
      String output = "First Name: "           + firstName + 
                      "\nLast Name: "          + lastName +
                      "\nCheck-in Date: "      + checkInDate +
                      "\nCheck-out Date: "     + checkOutDate + 
                      "\nNo. of rooms: "       + numRooms + 
                      "\nGuest/Room: "         + guestsRoom + 
                      "\nSpecial Rates: "      + specialRates + 
                      "\nRoom Types:"        + roomTypes + 
                      "\nSpecial Requests:\n"  + specialRequests + 
                      "\nBilling Address:\n\t" + street + "\n\t"  
                                               + city + ", " + state + " " + zipCode +
                      "\nPhone: "              + phoneNumber + 
                      "\nCredit Card: "        + creditCardType + 
                      "\nCredit Card Number: " + creditCardNum + 
                      "\nExpiration: "         + creditCardExp;
      
      try{
        
        if (creditCardType.equals("Amex")){
          if (creditCardNum.length() != 15){
            throw new InvalidCreditCardException ("Invalid Amex number. Must be 15 numbers no spaces!");
          }
          if (!Character.toString(creditCardNum.charAt(0)).equals("3")){
            throw new InvalidCreditCardException ("Invalid Amex number. Must start with a 3!");
          }
          
        }
        
        else {
          
          if (creditCardNum.length() != 16){
            throw new InvalidCreditCardException ();
          }
          
          if (creditCardType.equals("Visa")){
            if (!Character.toString(creditCardNum.charAt(0)).equals("4")){
              throw new InvalidCreditCardException ("Invalid Visa number. Must start with a 4!");             
            }
          }
          else if (creditCardType.equals("Master Card")){
            if (!Character.toString(creditCardNum.charAt(0)).equals("5")){
              throw new InvalidCreditCardException ("Invalid Master number. Must start with a 5!");             
            }
          }
          else if (creditCardType.equals("Discover")){
            if (!Character.toString(creditCardNum.charAt(0)).equals("6")){
              throw new InvalidCreditCardException ("Invalid Discover number. Must start with a 6");             
            }
          }
          else{
            throw new InvalidCreditCardException ("Error! Must choose credit card type!");
          }
          
          
        }
      
      JOptionPane.showMessageDialog(null, output);
      writeAcctReservation (creditCardNum, output);
      
      
      
      }
      
      catch (InvalidCreditCardException cce){
        JOptionPane.showMessageDialog(null, cce.getMessage());
      }
      
    }
    
    else if (e.getSource() == cancelButton){
      
      fnTextField.setText("");
      lnTextField.setText("");
      streetTextField.setText("");
      cityTextField.setText("");
      stateComboBox.setSelectedIndex(0);
      zipTextField.setText("");
      phoneTextField.setText("");
      creditButtonGroup.clearSelection();
      creditNumTextField.setText("");
      expirationTextField.setText("");
      checkInTextField.setText("");
      checkOutTextField.setText("");
      numRoomsBox.setSelectedIndex(0);
      guestsRoomBox.setSelectedIndex(0);
      specialRatesGroup.clearSelection();
      studioCheck.setSelected(false);
      standardCheck.setSelected(false);
      familyCheck.setSelected(false);
      suiteCheck.setSelected(false);
      sRequestTextArea.setText("");
      
    }
    
    
  }
  
  private void writeAcctReservation(String creditCardNum, String fileInfo){
    
    String path = "/Users/gormleyryan/Documents/Marymount/IT-230 - Advanced Java/HotelReservationFolder/Reservations/";
    File reservation = new File(path + creditCardNum);
 
    try{
      
      FileWriter writer = new FileWriter(reservation);
      writer.write(fileInfo);
      writer.close();
      
    }
    
    catch (IOException ioe) {
      
      ioe.printStackTrace();
      
    }
    
  }
  
  public static void main (String [] args){
    
    HotelReservation1 app = new HotelReservation1();
    
  }//end main method
  
}//end HotelReservation1