package _solution;

/**
 * 
 * @author jake
 * exception for the password being too short
 */
public class LengthException extends Exception {
	
	/**
	 * default constructor
	 */
	public LengthException() {
		super("The password must be at least 6 characters long");
	}
	
	/**
	 * 
	 * @param message
	 */
	public LengthException(String message) {
		super(message);
	}
	
}
