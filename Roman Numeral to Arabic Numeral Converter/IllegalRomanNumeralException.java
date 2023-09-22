package project4;

/**
 * This class creates the IllegalRomanNumeralException that is thrown whenever a provided Roman numeral input cannot be converted into an Arabic value.
 * The class extends IllegalArgumentException in order to create a custom exception relating to the project, which checks for illegal Roman numerals
 */

public class IllegalRomanNumeralException extends IllegalArgumentException {
	
	/**
	 * Constructs the IllegalRomanNumeralException with an error message that is made in the RomanNumeral class
	 * 
	 * @param errorMessage The error message that is displayed whenever a user provides an illegal Roman numeral
	 */
	
	public IllegalRomanNumeralException(String errorMessage) {
		super(errorMessage); // Sets the error message for the exception by calling the constructor of the superclass
	}
}