import javax.swing.JFrame;                             //main frame
import javax.swing.JLabel;                             //import component
import java.awt.BorderLayout;                          //import BorderLayout (in awt because it is heavyweight)
import javax.swing.JTextField;                         //import JTextField (text box)
import javax.swing.JPanel;                             //import JPanel (used to create a smaller frame to trick the sections of the main frame)
import javax.swing.JComboBox;                          //import JComboBox (dropbox)
import java.awt.GridLayout;                            //grid layout
import javax.swing.JCheckBox;                          //check box
import javax.swing.JRadioButton;                       // button (only one selection)
import javax.swing.ButtonGroup;                        //allows buttons to be grouped together
import javax.swing.JTextArea;                          //big box allows multiple lines of writing
import javax.swing.JButton;                            //buttons
import javax.swing.JScrollPane;                        //scroll pane

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIExampleMe extends JFrame{
  
  /**
   * Declare Local Variables
   */
  
  private JLabel fullnameLabel, stateLabel, hobbyLabel,
                 blankLabel;                                    //declares the label
  private JTextField fnTextField;                               //declares the text field
  private JPanel northPanel, westPanel, westPanel2,
                 centerPanel, eastPanel, southPanel;             //declares the panels to hold the components
  private JComboBox stateDropBox;                               //declare the comboBox
  private JCheckBox musicBox, movieBox;                         //declare checkboxes for movie and music
  private JRadioButton maleRadioButton, femaleRadioButton;      //declare buttons
  private ButtonGroup genderGroup;                              //declare genderGroup to contain male/female buttons
  private JTextArea commentArea;                                //declare commentArea text field
  private JButton submitButton, cancelButton;                   //declare buttons;
  private JScrollPane eastPane;
  
  /**Build a GUI and add components before everything appears
    * 
    */
  public GUIExampleMe (){
    
    //Better Way to set Title (can only do if class extends JFrame)
    // Must be first line in Constructor
    super("My First GUI");
    
    //Set Title of main frame (JFrame) way #1 (worse way)
    //Can be anywhere in Frame constructor
    //setTitle("My First GUI");
    
    
    
    //********NORTH********//
    
    //initialze label and text field and JPanel
    
    fullnameLabel = new JLabel("Enter Full Name:");     //initializes a label that says "Enter Full Name:"
    fnTextField = new JTextField (5);                   //create text field for full name
    String firstName = fnTextField.getText();
    northPanel = new JPanel();
    
    //add components to northPanel
    northPanel.add(fullnameLabel);
    northPanel.add(fnTextField);
    
    //add to main layout in North section
    add(northPanel, BorderLayout.NORTH);             //adds fullnameLabel to the NORTH region of the frame. can only add one component   
    
    //********WEST********//
    
    //Initialize dropbox
    //create data for dropbox (before dropbox) 
    
    String states[] = {"", "DC", "VA", "MD"};
    stateDropBox = new JComboBox(states);
    stateLabel = new JLabel("State:");
    hobbyLabel = new JLabel("Hobbies:");
    musicBox = new JCheckBox("Music");
    movieBox = new JCheckBox("Movies");
    blankLabel = new JLabel("");
    
    
    //Add to panels
    westPanel = new JPanel();
    westPanel.setLayout(new GridLayout(4, 2));          //To change layout of a panel you must do it immediately after you initialize it. (GridLayout constructor takes (row, col)
    westPanel.add(stateLabel);
    westPanel.add(stateDropBox);
    westPanel.add(hobbyLabel);
    westPanel.add(blankLabel);
    westPanel.add(musicBox);
    westPanel.add(movieBox);
    
    westPanel2 = new JPanel();                           //shrink panel down to size
    westPanel2.add(westPanel);                          //add westPanel to westPanel2 for layout
    //add to main layout in West section
    add(westPanel2, BorderLayout.WEST);
    
    //********CENTER********//
    
    maleRadioButton = new JRadioButton("Male");
    femaleRadioButton = new JRadioButton("Female");
    genderGroup = new ButtonGroup();
    genderGroup.add(maleRadioButton);
    genderGroup.add(femaleRadioButton);
    centerPanel = new JPanel();
    centerPanel.add(maleRadioButton);
    centerPanel.add(femaleRadioButton);
    add(centerPanel, BorderLayout.CENTER);
    
    //********EAST********//
    
    commentArea = new JTextArea(10, 20);                     //must define width and height in pixels, (h, w)
    eastPane = new JScrollPane(commentArea);                //initializes a scroll pane that holds the comment area
    eastPanel = new JPanel();
    eastPanel.add(eastPane);
    add(eastPanel, BorderLayout.EAST);
    
    //********SOUTH********//
    
    submitButton = new JButton("Submit");
    cancelButton = new JButton("Cancel");
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
  
  
  
  public static void main (String [] args){
    
    GUIExampleMe app = new GUIExampleMe ();
    
  }//End main
  
}//End GUIExampleMe