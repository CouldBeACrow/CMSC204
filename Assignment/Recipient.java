/**
 *  
 * @author jake
 * Class for a Recipient
 */
public class Recipient {
	
	String name;
	
	/**
	 * Default constructor
	 */
	public Recipient() {
		name = null;
	}
	
	/** 
	 * Constructor
	 * @param name
	 */
	public Recipient(String name) {
		this.name = name;
	}

	/**
	 * Return name
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
}
