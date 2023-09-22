package project4;
import java.util.TreeMap;
import java.util.Comparator;

/**
 * This class sorts Roman numerals using their Arabic value equivalencies by using the Comparator interface to do the sorting
 * A TreeMap is also used to store and sort the Roman numerals that were found in the provided text file
 */

public class RomanNumeralSorter implements Comparator<RomanNumeral> {
    TreeMap<RomanNumeral, Integer> sortedNumerals;

    /**
     * Constructor for the RomanNumeralSorter class which initializes the TreeMap with the Comparator
     */
    
    public RomanNumeralSorter() {
        sortedNumerals = new TreeMap<>(this);
    }

    /**
     * Adds a singular Roman numeral to the sortedNumerals TreeMap with its Arabic value equivalency
     *
     * @param romanInput The Roman numeral that is added to the TreeMap
     */
    
    public void addRomanNumeral(RomanNumeral romanInput) {
        sortedNumerals.put(romanInput, romanInput.getArabicValue());
    }

    /**
     * Compares two Roman numerals based on their Arabic value equivalencies
     * The method makes use of the compareTo method made in the RomanNumeral class
     *
     * @param romanOne The original Roman numeral that is being compared to by the new Roman numeral
     * @param romanTwo The new Roman numeral that is being compared to the old Roman numeral
     * 
     * @return The result of the comparison between the two Roman numerals
     */
    
    public int compare(RomanNumeral romanOne, RomanNumeral romanTwo) {
        return romanOne.compareTo(romanTwo);
    }

    /**
     * Returns the TreeMap containing the sorted Roman numerals and their Arabic equivalencies
     *
     * @return TreeMap of sorted Roman numerals and their Arabic values
     */
    
    public TreeMap<RomanNumeral, Integer> getSortedNumerals() {
        return sortedNumerals;
    }
}
