package _solution;

/**
 * 
 * @author jake
 * exception for password not containing any uppercase characters
 */
public class NoUpperAlphaException extends Exception{
	
	/**
	 * default constructor
	 */
	public NoUpperAlphaException() {
		super("Password doesn't contain an uppercase alpha character");
	}
	
	/**
	 * 
	 * @param message
	 */
	public NoUpperAlphaException(String message) {
		super(message);
	}
	
}
