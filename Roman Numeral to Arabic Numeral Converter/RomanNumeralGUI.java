package project4;
import java.awt.*;
import java.util.Iterator;
import javax.swing.*;

/**
 * This class contains the code needed to create the GUI for the user to use
 * The class also contains the code needed to add the "File" and "Convert" handlers to the GUI for a user to interact with
 */


public class RomanNumeralGUI extends JFrame {
    RomanNumeralSorter numeralSorter; // Declares an instance of the RomanNumeralSorter class

    /**
     * Constructs the conversion GUI and instantiates the Roman numeral sorter
     * The constructor also creates a JMenuBar which holds the File and Convert menus, where a user can choose a file of Roman numerals to be converted, or find the value of a singular Roman numeral
     */
    
    public RomanNumeralGUI() {
        numeralSorter = new RomanNumeralSorter(); // Instantiates the Roman numeral sorter
        setSize(600, 400); // Creates a rectangular shape for the GUI so there is enough space for the data to be displayed properly
        setTitle("Roman Numeral to Arabic Numeral Conversion");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 2)); // The GUI is divided into 2 columns, with the left being the Roman numerals and the right being the equivalencies of each Roman numeral
        JMenuBar menuBar = new JMenuBar();
        createFileMenu(menuBar);
        createConvertMenu(menuBar);
        setJMenuBar(menuBar);
    }

    /**
     * Creates the File menu that is added to the JMenuBar
     * The File menu has the options to open and read a text file consisting of Roman numerals, and to quit the program
     * 
     * @param menuBar The JMenuBar that the File menu is added to
     */
    
    public void createFileMenu(JMenuBar menuBar) {
        JMenuItem item;
        JMenu fileMenu = new JMenu("File");
        FileMenuHandler fmh = new FileMenuHandler(this, numeralSorter); // The FileMenuHandler makes use of the RomanNumeralSorter class in order for the "Open" item to work properly
        item = new JMenuItem("Open");
        item.addActionListener(fmh);
        fileMenu.add(item);
        fileMenu.addSeparator(); // A separator is needed to prevent the menus from being in the same area when the GUI is displayed
        item = new JMenuItem("Quit");
        item.addActionListener(fmh);
        fileMenu.add(item);
        menuBar.add(fileMenu);
    }

    /**
     * Creates the Convert menu that is added to the JMenuBar
     * The Convert menu has the option for a user to input a singular Roman numeral in a JOptionPane, which then displays its equivalency in another JOptionPane message dialog
     * 
     * @param menuBar The JMenuBar that the Convert menu is added to
     */
    
    public void createConvertMenu(JMenuBar menuBar) {
        JMenuItem item;
        JMenu convertMenu = new JMenu("Convert");
        ConvertMenuHandler cmh = new ConvertMenuHandler(this);
        item = new JMenuItem("Roman to Arabic");
        item.addActionListener(cmh);
        convertMenu.add(item);
        menuBar.add(convertMenu);
    }
    
    /**
     * Displays the Roman numerals from the provided text file in a sorted order from least to greatest, and their Arabic value equivalencies
     */

    public void displaySortedNumerals() {
        Container inputStorage = getContentPane();
        inputStorage.removeAll(); // This prevents the GUI from creating two more separate columns when opening another file after a file has already been opened
        TextArea romanInput = new TextArea();
        romanInput.setText("Roman Numerals" + "\n\n");
        TextArea arabicInput = new TextArea();
        arabicInput.setText("Arabic Values" + "\n\n");
        
        // To iterate through each set of Roman numerals provided from the text file, an iterator must be used
        Iterator<RomanNumeral> RomanSetIterator = numeralSorter.getSortedNumerals().keySet().iterator(); // Iterates through each Roman numeral provided as keys for the TreeMap, which sorts them based on their Arabic values
        while(RomanSetIterator.hasNext()) { // Loops through each set of Roman numerals until there are no more to convert from the provided text file
            RomanNumeral unconvertedRoman = RomanSetIterator.next();
            romanInput.append(unconvertedRoman.getRomanNumeral() + "\n"); // Adds a singular Roman numeral to the left column of the GUI
            arabicInput.append(String.valueOf(unconvertedRoman.getArabicValue()) + "\n"); // Adds the corresponding Arabic value equivalency to each Roman numeral provided in the right column
        }
        
        // Prevents the user from changing anything in the GUI
        romanInput.setEditable(false);
        arabicInput.setEditable(false);
        
        // Adds the TextAreas to the Container after the data has been appended
        inputStorage.add(romanInput);
        inputStorage.add(arabicInput);
        
        setVisible(true); // Sets the data to be visible after being appended to the GUI
    }
}