package _solution;

/**
 * 
 * @author jake
 * exception for when there are more than 2 of the same character in a row
 */
public class InvalidSequenceException extends Exception{

	/**
	 * default constructor
	 */
	public InvalidSequenceException() {
		super("The password cannot contain more than two of the same character in sequence");
	}
	
	/**
	 * 
	 * @param message
	 */
	public InvalidSequenceException(String message) {
		super(message);
	}
	
}
