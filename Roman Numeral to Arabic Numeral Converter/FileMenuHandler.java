package project4;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
 * This class handles the actions that a user may perform when interacting with the "File" menu of the Roman numeral GUI.
 * The actions that a user may perform that the class handles are selecting and opening a file, as well as reading the contents of a file
 */

public class FileMenuHandler implements ActionListener {
    static String[] romanNumerals = new String[100]; // Declares a global variable for a set of Roman numerals provided in a text file, which is then used in the readSource method
    
	// Declares instances of the RomanNumeralGUI and RomanNumeralSorter classes
    RomanNumeralGUI GUI;
    RomanNumeralSorter numeralSorter;

    /**
     * Creates a constructor for the FileMenuHandler class, which is then used in the RomanNumeralGUI class when creating a file menu
     * 
     * @param ConversionGUI The instance of the RomanNumeralGUI class, which is used to represent that the FileMenuHandler is part of the GUI
     * @param RNSorter The instance of the RomanNumeralSorter class, which is used to represent that the FileMenuHandler class makes use of the Comparator when reading text files
     */
    
    public FileMenuHandler(RomanNumeralGUI ConversionGUI, RomanNumeralSorter RNSorter) {
        GUI = ConversionGUI;
        numeralSorter = RNSorter;
    }

    /**
     * Adds effect to the "Open" and "Quit" menuNames, which each have their own result when selected
     * If the user selects the "Quit" button, the program ends and the GUI closes
     * 
     * @param event The specific event that is done when a user performs a certain action
     */
    
    public void actionPerformed(ActionEvent event) {
        String menuName;
        menuName = event.getActionCommand();
        if(menuName.equals("Open")) openFile();
        else if(menuName.equals("Quit")) System.exit(0);
    }

    /**
     * This method holds the code performed when the user selects the "Open" button of the File menu
     * When the "Open" button is selected, a file dialog is opened and the user is prompted to select a text file which is used in the GUI for numeral conversion
     */
    
    public void openFile() {
        JFileChooser fileSelector;
        int status;
        fileSelector = new JFileChooser();
        status = fileSelector.showOpenDialog(null);
        if(status == JFileChooser.APPROVE_OPTION) readSource(fileSelector.getSelectedFile()); // If the user selects a file, the file is then read
        else JOptionPane.showMessageDialog(null, "The open file dialog has been cancelled."); // If the user closes the file dialog, this message displays immediately
    }

    /**
     * Reads the contents of the file that the user selected
     * 
     * @param chosenFile The file that the user chose
     */
    
    private void readSource(File chosenFile) {
        String filePath = chosenFile.getAbsolutePath();
        TextFileInput numeralConversion = new TextFileInput(filePath);
        String inputLine = numeralConversion.readLine();
        while(inputLine != null) { // If the input line is null, that means we have reached the end of the provided text file
            romanNumerals = inputLine.split(","); // For every comma found in the text file, it is stored as a String in an array of Strings
            for(int i = 0; i < romanNumerals.length; i++) { // Loops through every separate String found in the text file
                String splitRoman = romanNumerals[i];
                RomanNumeral RomanData = new RomanNumeral(splitRoman);
                numeralSorter.addRomanNumeral(RomanData); // Adds the Roman numeral found to the TreeMap which then sorts it
            }
            inputLine = numeralConversion.readLine(); // Reads the next line of the text file
        }
        GUI.displaySortedNumerals(); // Updates the GUI to display the Roman numerals after they have been sorted
    }
}