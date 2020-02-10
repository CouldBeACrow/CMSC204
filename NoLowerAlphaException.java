package _solution;

/**
 * 
 * @author jake
 * exception for password not containing any lowercase characters
 */
public class NoLowerAlphaException extends Exception{

	/**
	 * default constructor
	 */
	public NoLowerAlphaException() {
		super("Password doesn't contain a lowercase alpha character");
	}
	
	/**
	 * 
	 * @param message
	 */
	public NoLowerAlphaException(String message) {
		super(message);
	}
	
}
