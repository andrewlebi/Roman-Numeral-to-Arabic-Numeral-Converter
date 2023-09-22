package project4;

/** 
 * Main class for Project 4 that instantiates the Roman numeral conversion GUI and then makes it visible to the user
 * This project allows the user to choose a text file consisting of Roman numerals to use as input for the GUI, and then converts it to Arabic numerals and displays both in separate columns
 * The GUI also has a "Convert" menu item that uses JOptionPanes to convert singular Roman numerals to its corresponding Arabic value
 * Error checking is also used throughout the project to check for illegal Roman numeral inputs
 */

public class Project4 {
	static RomanNumeralGUI conversionGUI; // Declares an instance of the RomanNumeralGUI class, which is then used in the main method
	public static void main(String[] args) {
		RomanNumeralGUI conversionGUI = new RomanNumeralGUI(); // Officially instantiates the Roman numeral GUI
		conversionGUI.setVisible(true); // The GUI is set to be visible after it is instantiated
	}
}