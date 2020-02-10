package _solution;

/**
 * 
 * @author jake
 *	exception for password not containing any numbers
 */
public class NoDigitException extends Exception {

	/**
	 * default constructor
	 */
	public NoDigitException() {
		super("The password must contain at least one digit");
	}
	
	/**
	 * 
	 * @param message
	 */
	public NoDigitException(String message) {
		super(message);
	}
	
}
