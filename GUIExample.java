import javax.swing.JFrame;                             //main frame
import javax.swing.JLabel;                             //import component
import java.awt.BorderLayout;                          //import BorderLayout (in awt because it is heavyweight)
import javax.swing.JTextField;                         //import JTextField (text box)
import javax.swing.JPanel;                             /**import JPanel (used to create a smaller frame to trick the
                                                        *sections of the main frame)
                                                        */
import javax.swing.JComboBox;                          //import JComboBox (dropbox)
import java.awt.GridLayout;                            //grid layout
import javax.swing.JCheckBox;                          //check box
import javax.swing.JRadioButton;                       // button (only one selection)
import javax.swing.ButtonGroup;                        //allows buttons to be grouped together
import javax.swing.JTextArea;                          //big box allows multiple lines of writing
import javax.swing.JButton;                            //buttons
import javax.swing.JScrollPane;                        //scroll pane (creates a text pane that automatically scrolls)
import java.awt.event.ActionListener;                  //The listener needed to perform actions
import java.awt.event.ActionEvent;                     //Allows for the event to take place
import javax.swing.JOptionPane;                        //Popup box
import java.awt.Color;                                 //import Color class
import javax.swing.ImageIcon;                          //used to store the image to place within the JLabel


public class GUIExample extends JFrame implements ActionListener {
  
  /**
   * Declare Local Variables
   */
  
  private JLabel fullnameLabel, stateLabel, hobbyLabel,
                 blankLabel, ageLabel, maleImage, femaleImage;  //declares the label
  private JTextField fnTextField, ageTextField;                 //declares the text field
  private JPanel northPanel, westPanel, westPanel2,
                 centerPanel, eastPanel, southPanel;             //declares the panels to hold the components
  private JComboBox stateDropBox;                               //declare the comboBox
  private JCheckBox musicBox, movieBox;                         //declare checkboxes for movie and music
  private JRadioButton maleRadioButton, femaleRadioButton;      //declare buttons
  private ButtonGroup genderGroup;                              //declare genderGroup to contain male/female buttons
  private JTextArea commentArea;                                //declare commentArea text field
  private JButton submitButton, cancelButton;                   //declare buttons
  private JScrollPane eastPane;
  private ImageIcon maleIcon, femaleIcon;                       //declare maleIcon and femaleIcon to hold images
  
  /**Build a GUI and add components before everything appears
    * 
    */
  public GUIExample (){
    
    //Better Way to set Title (can only do if class extends JFrame)
    // Must be first line in Constructor
    super("My First GUI");
    
    //Set Title of main frame (JFrame) way #1 (worse way)
    //Can be anywhere in Frame constructor
    //setTitle("My First GUI");
    
    Color myColor = new Color (255, 204, 204);          //creates new color based on RGB codes
    
    //********NORTH********//
    
    //initialze label and text field and JPanel
    
    fullnameLabel = new JLabel("Enter Full Name:");     //initializes a label that says "Enter Full Name:"
    fnTextField = new JTextField (10);                  //create text field for full name
    ageLabel = new JLabel("Enter Age:");
    ageTextField = new JTextField (3);
    northPanel = new JPanel();
    
    //Decorate North Panel
    northPanel.setBackground(Color.RED);              //changes background color to BLUE
    fullnameLabel.setForeground(Color.WHITE);          //changes foreground (text) color
    ageLabel.setForeground(myColor);                   //changes foreground (text) color to created color
 
    //add components to northPanel
    northPanel.add(fullnameLabel);
    northPanel.add(fnTextField);
    northPanel.add(ageLabel);
    northPanel.add(ageTextField);
    
    //add to main layout in North section
    add(northPanel, BorderLayout.NORTH);                //adds fullnameLabel to the NORTH region of the frame. can only 
                                                        // add one component 
    
    //********WEST********//
    
    //Initialize dropbox
    //create data for dropbox (before dropbox) 
    
    String states[] = {"", "DC", "VA", "MD"};
    stateDropBox = new JComboBox(states);
    stateLabel = new JLabel("State:");
    hobbyLabel = new JLabel("Hobbies:");
    hobbyLabel.setToolTipText("Choose your hobbies"); //show tip text when the mouse hovers over 
    musicBox = new JCheckBox("Music");
    movieBox = new JCheckBox("Movies");
    blankLabel = new JLabel("");
    
    
    //Add to panels
    westPanel = new JPanel();
    westPanel.setLayout(new GridLayout(4, 2));          //To change layout of a panel you must do it immediately after  
                                                        //you initialize it. (GridLayout constructor takes (row, col)
    westPanel.add(stateLabel);
    westPanel.add(stateDropBox);
    westPanel.add(hobbyLabel);
    westPanel.add(blankLabel);
    westPanel.add(musicBox);
    westPanel.add(movieBox);
    
    westPanel2 = new JPanel();                           //shrink panel down to size
    westPanel2.add(westPanel);                           //add westPanel to westPanel2 for layout
    //add to main layout in West section
    add(westPanel2, BorderLayout.WEST);
    
    //********CENTER********//
    
 //  maleRadioButton = new JRadioButton("Male");         //buttons using the word
 //  femaleRadioButton = new JRadioButton("Female");     //buttons using the word
    
    maleRadioButton = new JRadioButton("");              //remove text to allow for a picture next to it
    femaleRadioButton = new JRadioButton("");            //remove text to allow for a picture next to it
    
    maleIcon = new ImageIcon("male.gif");
    femaleIcon = new ImageIcon("female.gif");
    maleImage = new JLabel(maleIcon);                            //initialize a maleImage label using the image imported above
    femaleImage = new JLabel(femaleIcon);                          //initialize a femaleImage label using the image imported above
    
    genderGroup = new ButtonGroup();
    genderGroup.add(maleRadioButton);
    genderGroup.add(femaleRadioButton);
    centerPanel = new JPanel();
    centerPanel.add(maleRadioButton);
    centerPanel.add(maleImage);
    centerPanel.add(femaleRadioButton);
    centerPanel.add(femaleImage);
    add(centerPanel, BorderLayout.CENTER);
    
    //********EAST********//
    
    commentArea = new JTextArea(10, 20);                     //must define width and height in pixels, (h, w)
    eastPane = new JScrollPane(commentArea);                 //initializes a scroll pane that holds the comment area
    eastPanel = new JPanel();
    eastPanel.add(eastPane);
    add(eastPanel, BorderLayout.EAST);
    
    //********SOUTH********//
    
    submitButton = new JButton("Submit");                   //creates submit button at bottom of GUI
    submitButton.addActionListener(this);                   //adds the button to the listener so that it will activate
    cancelButton = new JButton("Cancel");                   //creates submit button at bottom of GUI
    cancelButton.addActionListener(this);                   //adds the button to the listener. must declare it as (this)
    southPanel = new JPanel();
    southPanel.add(submitButton);
    southPanel.add(cancelButton);
    add(southPanel, BorderLayout.SOUTH);
     
    
    //********Create Main Frame********//
    /**
     * Add the following lines of code to constructor
     */   
    setSize(600,400);                                   //Set dimension of frame to 400 pixels by 200 pixels
    setLocationRelativeTo(null);                        //center frame in middle of screen
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     //end program on close of fram
    setVisible(true);                                   //sets the frame to be visible
       
  } //default constructor
  
  
  /**
   * Must have this method in order to compile once the ActionListener has been implemented
   * Actions can only be performed once added to the actionListener
   */
  public void actionPerformed(ActionEvent e){
    
    if(e.getSource() == submitButton ){
      
      String fullName = fnTextField.getText();
      String comments = commentArea.getText();
      String state = stateDropBox.getSelectedItem().toString();       //have to convert to string
      String movie = "No", music = "No";
      String gender = "";
      String ageText = ageTextField.getText();
      int age = Integer.parseInt(ageText);
      
      
      if(movieBox.isSelected()){
        movie = "Yes";
      }
      else{
        movie = "No";         //may be required or a click then unclick will stay as a click
      }
      if(musicBox.isSelected()){
        music  = "Yes";
      }
      
      if(maleRadioButton.isSelected()){
        gender = "Male";
      }
      else if (femaleRadioButton.isSelected()){
        gender = "Female";
      }
      
     

      String output = "Full Name: " + fullName + 
                      "\nAge: " + age + 
                      "\nComments: " + comments + 
                      "\nState: " + state  + 
                      "\nMovies: " + movie + 
                      "\nMusic: " + music  + 
                      "\nGender: " + gender;
      
      
      JOptionPane.showMessageDialog(null, output);      //null means use the default popup box                     
    }                                                                
   
    else if (e.getSource() == cancelButton){
      
      fnTextField.setText("");                          //resets text to blank
      ageTextField.setText("");
      commentArea.setText("");
      stateDropBox.setSelectedIndex(0);                 //set index to the blank in the array
      movieBox.setSelected(false);                      //takes boolean parameter. set to false to uncheck
      musicBox.setSelected(false);
      genderGroup.clearSelection();
      
    }
    
  } //special method that allows for performance of actions
  
  
  
  public static void main (String [] args){
    
    GUIExample app = new GUIExample ();
    
  }//End main
  
}//End GUI example