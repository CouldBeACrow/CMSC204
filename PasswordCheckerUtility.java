package _solution;
// Author: Jacob Whiteis

import java.util.ArrayList;

/**
 * 
 * @author Jacob Whiteis
 * This is the password checker utility. It contains 3 methods to 
 * check for the validity of given passwords
 *
 */
public final class PasswordCheckerUtility {

	/**
	 * 
	 * @param passwordString
	 * @return true if the password fits the given constraints, otherwise false
	 */
	public static boolean isValidPassword(String passwordString) throws 
		LengthException,
		NoDigitException,
		NoUpperAlphaException,
		NoLowerAlphaException,
		InvalidSequenceException
	{
		
			if (passwordString.length() < 6) {
				throw new LengthException();
			}
			if (!passwordString.contains("1") && !passwordString.contains("2") &&
				!passwordString.contains("3") && !passwordString.contains("4") &&
				!passwordString.contains("5") && !passwordString.contains("6") && 
				!passwordString.contains("7") && !passwordString.contains("8") &&
				!passwordString.contains("9")) {
				throw new NoDigitException();
			}
			if (passwordString.toLowerCase().equals(passwordString)) {
				throw new NoUpperAlphaException();
			}
			if (passwordString.toUpperCase().equals(passwordString)) {
				throw new NoLowerAlphaException();
			}
			for (int i=2; i<passwordString.length(); i++) {
				if (passwordString.charAt(i) == passwordString.charAt(i-1) && passwordString.charAt(i) == passwordString.charAt(i-2)) {
					throw new InvalidSequenceException();
				}
			}
				

		
	return true;
	}
	
	/**
	 * 
	 * @param passwordString
	 * @return true is password is greater than or equal to 6
	 * and less than or equal to 9.
	 */
	public static boolean isWeakPassword(String passwordString) {
		if (passwordString.length() >= 6 && passwordString.length() <= 9) {
			System.out.println("Password is weak");
			return true;
		}
		else
			return false;
	}
	
	/**
	 * 
	 * @param passwords
	 * @return an arraylist containing the passwords that are invalid.
	 * @throws InvalidSequenceException 
	 * @throws NoLowerAlphaException 
	 * @throws NoUpperAlphaException 
	 * @throws NoDigitException 
	 * @throws LengthException 
	 */
	public static ArrayList<String> validPasswords(ArrayList<String> passwords) throws LengthException, NoDigitException, NoUpperAlphaException, NoLowerAlphaException, InvalidSequenceException {
		ArrayList<String> invalidPasswords = new ArrayList<>();
			for (int i=0; i<passwords.size(); i++) {
				try {
					if (isValidPassword(passwords.get(i))) {
						System.out.println("index " + i + " was valid");
					}

				}
				catch (LengthException ex) {
				System.out.println("Triggered length exception catch");
				System.out.println(ex.getMessage());
				invalidPasswords.add(passwords.get(i) + " " + ex.getMessage());
				}
				catch (NoDigitException ex) {
				System.out.println("Triggered no digit exception catch");
				System.out.println(ex.getMessage());
				invalidPasswords.add(passwords.get(i) + " " + ex.getMessage());
				}
				catch (NoUpperAlphaException ex) {
				System.out.println("Triggered no upperalpha exception catch");
				System.out.println(ex.getMessage());
				invalidPasswords.add(passwords.get(i) + " " + ex.getMessage());
				}
				catch (NoLowerAlphaException ex) {
				System.out.println("Triggered no lowercase excpetion catch");
				System.out.println(ex.getMessage());
				invalidPasswords.add(passwords.get(i) + " " + ex.getMessage());
				}
				catch (InvalidSequenceException ex) {
				System.out.println("Triggered invalid sequence exception catch");
				System.out.println(ex.getMessage());
				invalidPasswords.add(passwords.get(i) + " " + ex.getMessage());
				}
			}
		return invalidPasswords;
	}
}
