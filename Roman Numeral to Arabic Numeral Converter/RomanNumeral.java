package project4;
import java.util.HashMap;

/**
 * This class represents Roman numerals and its Arabic value equivalencies.
 * The class also creates the RomanNumeral object and contains the method of converting Roman numerals to Arabic numerals.
 * Error checking is made possible in the class by a method using regular expression that checks the legality of a singular Roman numeral, and IllegalRomanNumeralExceptions are thrown if an input is illegal
 */

public class RomanNumeral {
	
	// Both the Roman and Arabic values are set to private so they require get methods and set for Roman numerals
	private String romanValue;
	private int arabicValue;
	
	/**
	 * Checks the legality of a given Roman numeral input using regular expression
	 * If the given input is all lower-case, has characters that aren't Roman numerals, or does not follow the subtraction rules of Roman numerals, the method returns false.
	 * The biggest number in the Roman numeral system is 3999, so that is the maximum value allowed for the regular expression
	 * 
	 * @param unconvertedRoman The Roman numeral input that is being checked for legality before it is converted in the valueOf method
	 * @return true if the given Roman numeral input is legal, false if it's illegal
	 */
	public static boolean isLegalRoman(String unconvertedRoman) {
		
		// ^ and $ are used to represent the beginning and ending of the given string
		// M is used to represent 1000 in Roman numerals, and there can be up to 3 M's in one Roman numeral
		// CM and CD are used to represent the subtraction notation in the Roman numeral system, and there can be up to 3 C's in one Roman numeral after an M or D
		// XC and XL are used to represent the subtraction notation in the Roman numeral system, and there can be up to 3 X's in one Roman numeral after a C or L
		// IX and IV are used to represent the subtraction notation in the Roman numeral system, and there can be up to 3 I's in one Roman numeral after an X or V
		
        String romanRegex = "^(?:(M{0,3})(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3}))$"; // Regular expression pattern for Roman numeral legality
        return unconvertedRoman.matches(romanRegex); // If the Roman numeral input matches the regular expression pattern, it is legal
    }
	
	/**
	 * Constructs the RomanNumeral object that is used for the append and add methods for the linked lists
	 * If the Roman numeral input is illegal, an IllegalRomanNumeralException is thrown, an error message will be printed to the console, and the arabic value is set to 0 to prevent any errors
	 * 
	 * @param romanInput The Roman numeral (in String form) that has to be converted into an Arabic value equivalency
	 */
	public RomanNumeral(String romanInput) {
		romanValue = romanInput;
		try { // If the provided Roman numeral input has no errors, it is converted into its Arabic value equivalency
			arabicValue = valueOf(romanValue);
		}
		catch(IllegalRomanNumeralException illegalRoman) { // If there is an error and an exception is thrown, an error message is printed and the arabic value resets
			System.out.println(illegalRoman.getMessage());
			arabicValue = 0;
		}
	}
	
	/**
	 * Sets the Roman numeral value to be the same as the provided Roman numeral input (in the form of a String)
	 * 
	 * @param romanInput The Roman numeral (in String form) that has to be converted into an Arabic value equivalency
	 */
	
	public void setRomanNumeral(String romanInput) {
		romanValue = romanInput;
	}
	
	/**
	 * Returns the Roman numeral value for the user
	 * 
	 * @return The Roman numeral value
	 */
	
	public String getRomanNumeral() {
		return romanValue;
	}
	
	/**
	 * Returns the Arabic equivalency of the Roman numeral provided for the user
	 * 
	 * @return The Arabic numeral value
	 */
	
	public int getArabicValue() {
		return arabicValue;
	}
	
	/**
	 * Tests if one Roman numeral's Arabic value is the same as another's Arabic value
	 * 
	 * @param other The other Roman numeral that is being compared to the original Roman numeral
	 * 
	 * @return true if the Arabic values are equivalent, false if they are not
	 */
	
	public boolean equals(RomanNumeral other) {
		if(other == null) return false;
		return this.getArabicValue() == other.getArabicValue();
	}
	
	/**
	 * Compares one RomanNumeral object to another RomanNumeral object.
	 * 
	 * @param other The other Roman Numeral that is being compared to the original Roman numeral
	 * 
	 * @return 0 if the Arabic values are equivalent to each other, -1 if the other Roman numeral's Arabic value is greater than the original's Arabic value, and 1 if the original Arabic value is greater than the other's
	 */
	
	public int compareTo(RomanNumeral other) {
		int romanOne = this.getArabicValue();
		int romanTwo = other.getArabicValue();
		if(romanOne == romanTwo) return 0;
		else if(romanOne < romanTwo) return -1;
		return 1;
	}
	
	/**
	 * Converts a Roman numeral (in String form) into its Arabic value equivalency
	 * If a Roman numeral input cannot be converted into an Arabic value, an exception is thrown
	 * 
	 * @param unconvertedRoman The Roman numeral (in String form) that has to be converted
	 * 
	 * @throws IllegalRomanNumeralException If the provided Roman numeral is illegal after being checked by the isLegalRoman method
	 * 
	 * @return arabicEquivalency The Arabic value equivalency of the Roman numeral string provided
	 */
	
	public static int valueOf(String unconvertedRoman) throws IllegalRomanNumeralException {
		int arabicEquivalency = 0; // Final Arabic equivalency of the provided Roman numeral
		int previousValue = 0; // previousValue has to be set at 0 at the start so the first Roman numeral is always greater than the previous Roman numeral (if any) during the first iteration of the for loop
		 
		if(isLegalRoman(unconvertedRoman) == false) {
			throw new IllegalRomanNumeralException(unconvertedRoman + " is an illegal Roman numeral!"); // Creates the error message thrown after an illegal Roman numeral input is found
		}
		
		HashMap<Character, Integer> unconvertedRomanMap = new HashMap<Character, Integer>(); // The HashMap converts singular characters to integers and provides the normal equivalencies for Roman numerals
		unconvertedRomanMap.put('I', 1);
		unconvertedRomanMap.put('V', 5);
        unconvertedRomanMap.put('X', 10);
        unconvertedRomanMap.put('L', 50);
        unconvertedRomanMap.put('C', 100);
        unconvertedRomanMap.put('D', 500);
        unconvertedRomanMap.put('M', 1000);
        
        for(int i = unconvertedRoman.length() - 1; i >= 0; i--) { // The for loop starts from the last character in the Roman numeral string and goes backwards until there are no more characters to convert
        	char currentRoman = unconvertedRoman.charAt(i);
        	int currentValue = unconvertedRomanMap.get(currentRoman);
        	if(previousValue > currentValue) { // If the previous value (from right to left) is greater than the current value, that means the current value has to be subtracted from the current Arabic equivalency (ex: IV = 4)
        		arabicEquivalency -= currentValue;
        	}
        	else { // If the previous value (from right to left) is less than the current value, that means the current value has to be added to the current Arabic equivalency (ex: VI = 6)
        		arabicEquivalency += currentValue;
        	}
        	previousValue = currentValue; // At the end of each for loop iteration, the previous value has to be set to the current value so the values can be compared properly in the next iteration
        }
        return arabicEquivalency;
	}
}