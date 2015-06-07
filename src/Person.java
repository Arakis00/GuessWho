

public class Person 
{
	private String name; //stores name of person
	private String gender; //stores gender of person
	private String eyeColor; //stores eye color of person
	private String hairColor; //stores hair color of person
	private String hasGlasses; //whether person is wearing glasses or not
	private String hasBeard; //whether person has a beard or not
	private String hasMustache; //whether person has a mustache or not
	private String picURL; //name/location of picture file associated with person
	
	/**
	 * Constructor
	 * @param n person's name
	 * @param g person's gender
	 * @param e person's eye color
	 * @param h person's hair color
	 * @param hg whether person has glasses or not
	 * @param hb whether person has beard or not
	 * @param hm whether person has mustache or not
	 * @param p name/location of picture file associated with the person
	 */
	public Person(String n, String p, String g, String hg, String h, 
					String e, String hb, String hm)
	{
		name = n;
		picURL = p;
		gender = g;
		hasGlasses = hg;
		hairColor = h;
		eyeColor = e;
		hasBeard = hb;
		hasMustache = hm;
	}
	
	
	/**
	 * Accessor
	 * @return the person's name
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Accessor
	 * @return the person's gender
	 */
	public String getGender()
	{
		return gender;
	}
	
	/**
	 * Accessor
	 * @return the person's eye color
	 */
	public String getEyeColor()
	{
		return eyeColor;
	}
	
	/**
	 * Accessor
	 * @return the person's hair color
	 */
	public String getHairColor()
	{
		return hairColor;
	}
	
	/**
	 * Accessor
	 * @return whether the person is wearing glasses or not
	 */
	public String getHasGlasses()
	{
		return hasGlasses;
	}
	
	/**
	 * Accessor
	 * @return whether the person has a beard or not
	 */
	public String getHasBeard()
	{
		return hasBeard;
	}
	
	/**
	 * Accessor
	 * @return whether the person has a mustache or not
	 */
	public String getHasMustache()
	{
		return hasMustache;
	}
	
	/**
	 * Accessor
	 * @return the name/location of the picture associated with the person
	 */
	public String getPicURL()
	{
		return picURL;
	}
	
	
	//following method not used in program....was only used for testing
	/**
	 * toString - override method that displays the stored values in each object's variables
	 * @return str the variable values stored in a string
	 */
	public String toString()
	{
		String str = "Name: " + getName() + "\n" + 
					 "Sex: " + getGender() + "\n" + 
					 "Glasses: " + getHasGlasses() + "\n" + 
					 "Hair Color: " + getHairColor() + "\n" + 
					 "Eye Color: " + getEyeColor() + "\n" + 
					 "Beard: " + getHasBeard() + "\n" + 
					 "Mustache: " + getHasMustache() + "\n";
		
		return str;
	}
}
