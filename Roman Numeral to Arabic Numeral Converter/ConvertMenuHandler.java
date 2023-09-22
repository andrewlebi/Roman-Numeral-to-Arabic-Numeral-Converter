package project4;
import javax.swing.*;
import java.awt.event.*;

/**
 * This class creates the "Convert" menu item which uses JOptionPanes to convert singular Roman numerals.
 * If a Roman numeral input is illegal, an IllegalRomanNumeralException is caught and then displays a message dialog that displays the provided illegal Roman numeral and states that it is not valid.
 */

public class ConvertMenuHandler implements ActionListener {
	RomanNumeralGUI GUI; // Declares an instance of the RomanNumeralGUI class
	
	/**
	 * Constructs the ConvertMenuHandler object for the Roman numeral GUI
	 * 
	 * @param ConversionGUI The Roman numeral GUI that the convert menu handler is added to
	 */
	
	public ConvertMenuHandler(RomanNumeralGUI ConversionGUI) {
		GUI = ConversionGUI; // The GUI being worked with is the instance that the user is using
	}
	
	public void actionPerformed(ActionEvent event) {
		String menuName;
		menuName = event.getActionCommand();
		if(menuName.equals("Roman to Arabic")) { // The menu item under the "Convert" button has to be "Roman to Arabic" in order for a user to input single Roman numerals
			String romanInput;
			romanInput = JOptionPane.showInputDialog(null, "Enter a Roman numeral:");
			try { // If there is no error with the Roman numeral that the user provided, it is then displayed with its corresponding Arabic value equivalency
				JOptionPane.showMessageDialog(null, "The Arabic equivalent of " + romanInput + " is " + RomanNumeral.valueOf(romanInput) + ".");
			}
			catch(IllegalRomanNumeralException illegalRoman) { // If there is an error and the input is illegal, an IllegalRomanNumeralException is caught and an error message is displayed
				JOptionPane.showMessageDialog(null, romanInput + " is not a valid Roman numeral!");
			}
		}
	}
}