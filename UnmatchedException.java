package _solution;

/**
 * 
 * @author jake
 * exception for when passwords do not match
 */
public class UnmatchedException extends Exception{

	/**
	 * default constructor
	 */
	public UnmatchedException() {
		super("The passwords do not match");
	}
	
	/**
	 * 
	 * @param message
	 */
	public UnmatchedException(String message) {
		super(message);
	}
	
}
