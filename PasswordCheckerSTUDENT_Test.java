package _solution;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * STUDENT tests for the methods of PasswordChecker
 * @author Jacob Whiteis
 *
 */
public class PasswordCheckerSTUDENT_Test {

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	
	}

	/**
	 * Test if the password is less than 8 characters long.
	 * This test should throw a LengthException for second case.
	 */
	@Test
	public void testIsValidPasswordTooShort()
	{
		try { 
			assertTrue(PasswordCheckerUtility.isValidPassword("blahBLAH123"));
			PasswordCheckerUtility.isValidPassword("bB1");
			assertTrue("Did not throw length exception", false);
		}
		catch (LengthException e) {
			assertTrue("Successfully threw a length exception", true);
		}
		catch (Exception e) {
			assertTrue("Threw some other exception besides length", true);
		}
	}
	
	/**
	 * Test if the password has at least one uppercase alpha character
	 * This test should throw a NoUpperAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoUpperAlpha()
	{
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("blahBLAH123"));
			PasswordCheckerUtility.isValidPassword("blahblah123");
			assertTrue("Did not throw upper alpha exception", false);
		}
		catch (NoUpperAlphaException e) {
			assertTrue("Successfully threw upper alpha exception", true);
		}
		catch (Exception e) {
			assertTrue("Threw some other exception besides upper alpha", true);
		}
		
	}
	
	/**
	 * Test if the password has at least one lowercase alpha character
	 * This test should throw a NoLowerAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoLowerAlpha()
	{
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("blahBLAH123"));
			PasswordCheckerUtility.isValidPassword("BLAHBLAH123");
			assertTrue("Did not throw lower alpha exception", false);
		}
		catch (NoLowerAlphaException e) {
			assertTrue("Successfully threw lower alpha exception", true);
		}
		catch (Exception e) {
			assertTrue("Threw some other exception besides lower alpha", true);
		}
	}
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsWeakPassword()
	{
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("blahBLAH123"));
			PasswordCheckerUtility.isValidPassword("bbbbbb123");
			assertTrue("Did not throw invalid sequence exception", false);
		}
		catch (InvalidSequenceException e) {
			assertTrue("Successfully threw invalid sequence exception", true);
		}
		catch (Exception e) {
			assertTrue("Threw some other exception besides invalid sequence", true);
		}
	}
	
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsValidPasswordInvalidSequence()
	{
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("blahBLAH123"));
			PasswordCheckerUtility.isValidPassword("bbbbbb123");
			assertTrue("Did not throw invalid sequence exception", false);
		}
		catch (InvalidSequenceException e) {
			assertTrue("Successfully threw invalid sequence exception", true);
		}
		catch (Exception e) {
			assertTrue("Threw some other exception besides invalid sequence", true);
		}
	}
	
	/**
	 * Test if the password has at least one digit
	 * One test should throw a NoDigitException
	 */
	@Test
	public void testIsValidPasswordNoDigit()
	{
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("blahBLAH123"));
			PasswordCheckerUtility.isValidPassword("blahBLAHblah");
			assertTrue("Did not throw no digit exception", false);
		}
		catch (NoDigitException e) {
			assertTrue("Successfully threw no digit exception", true);
		}
		catch (Exception e) {
			assertTrue("Threw some other exception besides no digit", true);
		}
	}
	
	/**
	 * Test correct passwords
	 * This test should not throw an exception
	 */
	@Test
	public void testIsValidPasswordSuccessful()
	{
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("blahBLAH123"));
			assertTrue(PasswordCheckerUtility.isValidPassword("bbahBLAH123"));
			assertTrue(PasswordCheckerUtility.isValidPassword("914567aZ9"));
		}
		catch (Exception e) {
			assertTrue("Threw some exceptiion. THIS SHOULD NOT HAPPEN.", true);
		}
	}
	
	/**
	 * Test the validPasswords method
	 * Check the results of the ArrayList of Strings returned by the validPasswords method
	 * @throws InvalidSequenceException 
	 * @throws NoLowerAlphaException 
	 * @throws NoUpperAlphaException 
	 * @throws NoDigitException 
	 * @throws LengthException 
	 */
	@Test
	public void testValidPasswords() throws LengthException, NoDigitException, NoUpperAlphaException, NoLowerAlphaException, InvalidSequenceException {
		ArrayList<String> passwords = new ArrayList<>();
		String[] p = {"334455BB", "AAAAAAAaaa1", "okayLEE123", "tenNine109", "JACOBJACOB01"};
		passwords = new ArrayList<String>();
		passwords.addAll(Arrays.asList(p));
		ArrayList<String> results = PasswordCheckerUtility.validPasswords(passwords);
		assertTrue(results.get(0).contains("334455BB"));
		assertTrue(results.get(1).contains("AAAAAAAaaa1"));
	}
	
}
