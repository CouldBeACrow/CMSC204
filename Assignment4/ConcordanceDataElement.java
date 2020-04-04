import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Data element, holds the word string and a linked list of page numbers
 * @author Jacob Whiteis
 *
 */
public class ConcordanceDataElement implements Comparable<ConcordanceDataElement> {

	private String word;
	private LinkedList<Integer> lineNumbers = new LinkedList<Integer>();
	
	/**
	 * Main constructor
	 * @param word
	 */
	public ConcordanceDataElement(String word) {
		this.word = word;
	}
	
	/**
	 * Add a page to the linked list of line numbers
	 * @param lineNum
	 */
	// Add page to linked list of line numbers if the list does not already contain the line number
	public void addPage(int lineNum) {
		if (!lineNumbers.contains(lineNum)) {
			lineNumbers.add(lineNum);
		}
	}
	
	/**
	 * 
	 * @return lineNumbers
	 */
	// Returns the linked list of page numbers
	public LinkedList<Integer> getList() {
		return lineNumbers;
	}
	
	/**
	 * 
	 * @return word
	 */
	// Returns the word as a string
	public String getWord() {
		return word;
	}
	
	/**
	 * @return hashcode of word
	 */
	// Returns the hash code of the word, calls .hashCode()
	public int hashCode() {
		return word.hashCode();
	}
	
	/**
	 * @return toString
	 */
	// Returns a string with the word and the page numbers
	public String toString() {
		String line = word + ": ";
		ListIterator<Integer> iterator = lineNumbers.listIterator();
		for (int i=0; i<lineNumbers.size(); i++) {
			line += iterator.next();
			if (iterator.hasNext()) {
				line += ", ";
			}
		}
		return line;
	}

	@Override
	public int compareTo(ConcordanceDataElement o) {
		return this.word.compareTo(o.word);
	}
	
}
