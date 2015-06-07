
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class GuessWho extends JFrame 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	private Person secretPerson; //to hold secret person
	private final int SIZE = 18; //size of arrays
	private JButton[] pictures = new JButton[SIZE]; //array to create buttons/hold pictures
	private Person[] people = new Person[SIZE]; //array to hold person objects
	private ImageIcon[] images = new ImageIcon[SIZE]; //array to hold images
	private JPanel mainPanel = new JPanel(); //main panel
	private JPanel sidePanel = new JPanel(); //side menu panel
	private JList attribBox; //list box containing a list of attributes to choose from
	private JList nameBox; //list box containing a list of names to choose from
	private JButton guessAttrib; //button for guessing an attribute
	private JButton guessPerson; //button for guessing a person
	private JPanel numGuesses; //panel for displaying number of guesses made
	private JTextField guessesMade; //text field holding the number of guesses made
	private int guesses; //holds number of guesses made
	private ImageIcon cardBack = new ImageIcon("src//Images//cardback.jpg"); //image for card back used when turning cards over
	private JPanel peopleList; //panel for displaying person list and its guess button
	private JPanel attribList; //panel for displaying attrib list and its guess button
	private JPanel sPersonPane; //panel to display the secret person
	private ImageIcon sPersonImage = new ImageIcon("src//Images//qmark.jpg"); //to hold secret person image(? or actual image)
	private JButton sPersonButton; //to hold sPersonImage
	
	//attributes to choose from
	private String[] attributes = {"male", "female", "glasses", "no glasses", 
									"beard", "no beard", "mustache", "no mustache", 
									"brown hair", "black hair", "blonde hair", "bald", 
									"brown eyes", "green eyes", "blue eyes"};
	//names to choose from
	private String[] names = {"Adam", "Brenda", "Charlie", "David", "Denise", "Frank", 
							  "Giselle", "Heidi", "Kathy", "Kevin", "Lucas", "Matthew", 
							  "Petra", "Raymond", "Sophia", "Steven", "Tamsin", "Yvette"};
	
	
	//the constructor sets up the game
	public GuessWho() throws IOException
	{
	
		//build person array from the information within the attribute text file
		buildPersonArray();
		//build the main panel that contains the cards/images
		buildMainPanel();
		//build the side panel that contains attribute list and guess buttons
		buildSidePanel();
		
		pack();
		setVisible(true); //set form visible
		
		getSecretPerson(); //obtaining the secret person
		
		//System.out.print(secretPerson.toString()); //DELETE - for testing purposes only
		
	}
	
	
	
	/**
	 * buildPersonArray reads info from a text file to build the person array
	 */
	public void buildPersonArray()
	{
		String fileName = "src//Images//characterFeatures.txt"; //path to file containing person attributes
		File file; //for file input
		Scanner inputFile = null; //for file input
		
		//attempt to open file
		try
		{
			file = new File(fileName);
			inputFile = new Scanner(file);
		}
		catch(IOException e)
		{
			JOptionPane.showMessageDialog(null, "File not found.");
		}
		
		
		//build ArrayList with people's attributes
		for (int i = 0; i < SIZE; i++)
		{
			people[i] = new Person(inputFile.nextLine().toString(), //storing name
								  inputFile.nextLine().toString(), //storing file path
								  inputFile.nextLine().toString(), //storing gender
								  inputFile.nextLine().toString(), //storing glasses status
								  inputFile.nextLine().toString(), //storing hair color
								  inputFile.nextLine().toString(), //storing eye color
								  inputFile.nextLine().toString(), //storing beard status
								  inputFile.nextLine().toString()); //storing mustache status
			
			inputFile.nextLine(); //skip past # separation character for next person to be stored
		}
		
		//close file
		inputFile.close();
	}
	
	
	/**
	 * buildMainPanel builds the main panel that stores the cards/pictures
	 */
	public void buildMainPanel()
	{
	setTitle("Guess Who"); //set title
	setLayout(new BorderLayout()); //set layout
	
	mainPanel.setLayout(new GridLayout(3, 6)); //set panel layout
	
	
	//assigning buttons
	for (int i = 0; i < SIZE; i++)
	{
			images[i] = new ImageIcon(people[i].getPicURL());
			pictures[i] = new JButton(images[i]);
			mainPanel.add(pictures[i]);
	}
	

	add(mainPanel, BorderLayout.CENTER); //add main panel to center of frame
	
	}
	
	
	/**
	 * buildSidePanel builds the side panel containing attribute lists and guess buttons
	 */
	public void buildSidePanel()
	{	
		buildAttrib(); //build panel to hold attribute list and attribute guess button
		buildPerson(); //build panel to hold person list and person guess button
		buildNumGuesses(); //build panel to hold num of guesses made
		buildSecretPerson(); //build panel showing a ? card holding the secret person
		
		sidePanel.setLayout(new GridLayout(2, 2)); //set panel layout
		
		
		sidePanel.add(peopleList); //add people panel to panel
		sidePanel.add(sPersonPane); //add panel to display secret person
		sidePanel.add(attribList); //add attrib panel to panel
		sidePanel.add(numGuesses); //add numGuesses panel to panel
		
		add(sidePanel, BorderLayout.EAST); //add side panel to right side of frame
		
	}
	
	
	/**
	 * buildSecretPerson holds an image showing a card back
	 * it will show the secret person when the game ends
	 */
	public void buildSecretPerson()
	{
		sPersonPane = new JPanel();
		
		JLabel spLabel = new JLabel("Secret Person: "); //create label
		sPersonButton = new JButton(sPersonImage);
		
		//setting layout and centering components
		sPersonPane.setLayout(new BoxLayout(sPersonPane, BoxLayout.PAGE_AXIS));
		sPersonButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		spLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		sPersonPane.add(spLabel);
		sPersonPane.add(sPersonButton);
		
	}
	
	/**
	 * buildAttrib builds a panel holding the attribute list and attribute guess button
	 */
	public void buildAttrib()
	{
		attribList = new JPanel();
		
		attribBox = new JList(attributes); //creating list box holding attributes to choose from
		guessAttrib = new JButton("Guess Attribute");
		guessAttrib.addActionListener(new guessAttribListener()); 
		
		//setting layout and centering components
		attribList.setLayout(new BoxLayout(attribList, BoxLayout.PAGE_AXIS));
		attribBox.setAlignmentX(Component.CENTER_ALIGNMENT);
		guessAttrib.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		attribList.add(attribBox); //add list box to panel
		attribList.add(guessAttrib); //add button to panel
	}
	
	
	/**
	 * buildPerson builds a panel holding the person list and person guess button
	 */
	public void buildPerson()
	{
		peopleList = new JPanel();
		
		nameBox = new JList(names); //creating list box holding names to choose from
		guessPerson = new JButton("Guess Person");
		guessPerson.addActionListener(new guessPersonListener());
		
		//setting layout and centering components
		peopleList.setLayout(new BoxLayout(peopleList, BoxLayout.PAGE_AXIS));
		nameBox.setAlignmentX(Component.CENTER_ALIGNMENT);
		guessPerson.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		peopleList.add(nameBox); //add list box to panel
		peopleList.add(guessPerson); //add button to panel
	}
	
	
	/**
	 * buildNumGuesses builds a panel holding the number of guesses made
	 */
	public void buildNumGuesses()
	{
		numGuesses = new JPanel(); //create panel
		
		JLabel label = new JLabel("Guesses Made: "); //create label
		
		guessesMade = new JTextField(4); //create text field
		guessesMade.setEditable(false); //set to uneditable
		
		guessesMade.setText(Integer.toString(guesses));
		
		numGuesses.add(label); //add label to panel
		numGuesses.add(guessesMade); //add text field to panel
		
	}
	
	
	/**
	 * getSecretPerson gets a random number between 0 and 17 and assigns the person
	 *  to a value stored at the corresponding subscript in the people array.
	 */
	public void getSecretPerson()
	{
		//creating random number
		int random = (int)(Math.random() * (18));
		//assigning secret person based on random number value
		secretPerson = people[random];
	}
	
	
	/**
	 * guessAttribListener compares the selected attribute to the secret person's
	 *   attribute and turns over cards that are to be eliminated from consideration. 
	 *
	 */
	public class guessAttribListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			//get selected attribute
			String selection = (String) attribBox.getSelectedValue();
			
			if (selection == null)
			{
				JOptionPane.showMessageDialog(null, "No attribute is currently selected.");
			}
			else
			{
				//selection matches an attribute of the secret person
				if (selection.matches(secretPerson.getGender()) || 
					selection.matches(secretPerson.getEyeColor()) ||	
					selection.matches(secretPerson.getHairColor()) ||
					selection.matches(secretPerson.getHasGlasses()) ||
					selection.matches(secretPerson.getHasBeard()) ||
					selection.matches(secretPerson.getHasMustache()) )
				{
					guesses++; //increment guesses
					guessesMade.setText(Integer.toString(guesses)); //display guesses in txtfield
					
					//finding card to turn over
					for (int i = 0; i < SIZE; i++)
					{
						if (!selection.matches(people[i].getGender()) &&
							!selection.matches(people[i].getEyeColor()) &&	
							!selection.matches(people[i].getHairColor()) &&
							!selection.matches(people[i].getHasGlasses()) &&
							!selection.matches(people[i].getHasBeard()) &&
							!selection.matches(people[i].getHasMustache()) )
						{
							pictures[i].setIcon(cardBack);
						}
					}
				}
				else //selection doesn't match any attributes
				{
					guesses++; //increment guesses
					guessesMade.setText(Integer.toString(guesses)); //display guesses in txtfield
					
					//finding card to turn over
					for (int i = 0; i < SIZE; i++)
					{
						if (selection.matches(people[i].getGender()) ||
							selection.matches(people[i].getEyeColor()) ||	
							selection.matches(people[i].getHairColor()) ||
							selection.matches(people[i].getHasGlasses()) ||
							selection.matches(people[i].getHasBeard()) ||
							selection.matches(people[i].getHasMustache()) )
						{
							pictures[i].setIcon(cardBack);
						}
					}
				}
			}
		}
	}	
		
		/**
		 * guessPersonListener compares the selected person to the secret person's
		 *   name attribute and turns over cards that don't match or ends the game
		 *	 if the correct guess was made.
		 */
		public class guessPersonListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				//get selected attribute
				String selection = (String) nameBox.getSelectedValue();
				
				if (selection == null)
				{
					JOptionPane.showMessageDialog(null, "No name is currently selected.");
				}
				else
				{
					//selection matches the name of the secret person
					if (selection.matches(secretPerson.getName()))
					{
						guesses++; //increment guesses
						guessesMade.setText(Integer.toString(guesses)); //display guesses in txtfield
						
						//changing question mark card icon to show secret person
						ImageIcon SP = new ImageIcon(secretPerson.getPicURL());
						sPersonButton.setIcon(SP);
						
						//show dialog box telling the player they won and asking if they want to play again
						int choice = JOptionPane.showConfirmDialog(null, selection + " is the correct guess!  " +
													  "You took " + guesses + " guesses.  Would you like to play again?",
													  "Play again?", JOptionPane.YES_NO_OPTION);
						
						if (choice == 0)
						{
							//start new game if yes is selected
							try 
							{
								dispose(); //close previous window
								new GuessWho();
							} 
							catch (IOException e1) 
							{
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						else
						{
							System.exit(0); //exit if no is selected
						}
						
					}
					else //selection doesn't match the name
					{
						guesses++; //increment guesses
						guessesMade.setText(Integer.toString(guesses)); //display guesses in txtfield
						
						//finding card to turn over
						for (int i = 0; i < SIZE; i++)
						{
							if (selection.matches(people[i].getName()))
							{
								pictures[i].setIcon(cardBack);
							}
						}
					}
				}
			}
		}
		
	
}


