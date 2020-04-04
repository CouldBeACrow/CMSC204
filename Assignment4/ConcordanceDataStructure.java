import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Implementation of the actual hash table
 * @author Jacob Whiteis
 *
 */
public class ConcordanceDataStructure implements ConcordanceDataStructureInterface {

	// Temporary, replace this later with a call to the 4k+3 function
	// private static final int TABLE_SIZE = 379;
	
	private LinkedList<ConcordanceDataElement>[] hashTable; 
	int size;
	private static final double LOADING_FACTOR = 1.5;
	
	
	///////////////////////////////////////////////////////////
	// 4k+3 function to size the array
	/**
	 * Taken from the slides
	 * @param n
	 * @return
	 */
	public static int fourKPlus3(int n)
	{  boolean fkp3 = false;
	   boolean aPrime = false;
	   int prime, highDivisor, d;

	   prime = (int)(n/LOADING_FACTOR);
	   if(prime % 2 == 0) // if even make odd
	      prime = prime +1;
	   while(fkp3 == false) // not a 4k+3 prime
	   {  while(aPrime == false) // not a prime
	      {  highDivisor = (int)(Math.sqrt(prime) + 0.5);
	         for(d = highDivisor; d > 1; d--)
	         {  if(prime % d == 0)
	               break; // not a prime
	         }
	         if(d != 1) // prime not found
	            prime = prime + 2;
	         else
	            aPrime = true;
	      } // end of the prime search loop
	      if((prime - 3) % 4 == 0)
	         fkp3 = true;
	      else
	      {  prime = prime + 2;
	         aPrime = false;
	      }
	   } // end of 4k+3 prime search loop
	   return prime;
	}

/////////////////////////////////////////////////////////////
	
	/**
	 * Constructor
	 * @param num
	 */
	// Main constructor
	public ConcordanceDataStructure(int num) {
		hashTable = new LinkedList[fourKPlus3(num)];
		for (int i=0; i<hashTable.length; i++) {
			hashTable[i] = new LinkedList<ConcordanceDataElement>();
		}
	}
	
	/**
	 * Test constructor
	 * @param test
	 * @param num
	 */
	// Test constructor
	public ConcordanceDataStructure(String test, int num) {
		hashTable = new LinkedList[num];
		for (int i=0; i<hashTable.length; i++) {
			hashTable[i] = new LinkedList<ConcordanceDataElement>();
		}
		System.out.println(test);
	}
	
	/**
	 * @param term, lineNum
	 * Adds a word and/or line number to the hash table
	 */
	// Adds a word and page number to a linked list in the hash table
	public void add(String term, int lineNum) {
		int hashTableIndex = Math.abs(term.hashCode() % hashTable.length);
		// LinkedList<ConcordanceDataElement> currentLinkedList = hashTable[hashTableIndex];
		
		// Iterate through the linked list at our index in the hash table
		// The linked list we are iterating through represents the buckets
		// We do this to check whether there is already an element in the list 
		//for the word we are given
		System.out.println(hashTable[hashTableIndex].size());
		if (hashTable[hashTableIndex].size() == 0) {
			ConcordanceDataElement dataElement = new ConcordanceDataElement(term);
			dataElement.addPage(lineNum);
			hashTable[hashTableIndex].add(dataElement);
		}
		else {
			for (int i=0; i<hashTable[hashTableIndex].size(); i++) {
				
				if (hashTable[hashTableIndex].get(i).getWord().toLowerCase().equals(term.toLowerCase())) {
					if (!hashTable[hashTableIndex].get(i).getList().contains(lineNum)) {
						hashTable[hashTableIndex].get(i).addPage(lineNum);
						break;
					}
					else {
						System.out.println("break!");
						break;
					}
				}
				
				else if (i == hashTable[hashTableIndex].size()-1) {
					ConcordanceDataElement dataElement = new ConcordanceDataElement(term);
					dataElement.addPage(lineNum);
					hashTable[hashTableIndex].add(dataElement);
				}
				
				// If neither of these cases are triggered, the word and page number must already exist.
			}
		}
	}
	
	/**
	 * @return pageNumbers
	 * returns an arraylist of all the linked lists of page numbers
	 */
	// Return an arraylist of all the linked lists of page numbers
	// at a specific index in the hash table
	public ArrayList<LinkedList<Integer>> getPageNumbers(int index) {
		LinkedList<ConcordanceDataElement> currentLinkedList = hashTable[index];
		ArrayList<LinkedList<Integer>> pageNumbers = new ArrayList<LinkedList<Integer>>();
		
		for (int i=0; i<currentLinkedList.size(); i++) {
			pageNumbers.add(currentLinkedList.get(i).getList());
		}
		
		return pageNumbers;
	}
	
	/**
	 * @param index
	 * @return words
	 * returns the words at an index in the hash table
	 */
	// Return an arraylist of all the words at a 
	// specific index in the hash table
	public ArrayList<String> getWords(int index) {
		LinkedList<ConcordanceDataElement> currentLinkedList = hashTable[index];
		ArrayList<String> words = new ArrayList<String>();
		for (int i=0; i<currentLinkedList.size(); i++) {
			words.add(currentLinkedList.get(i).getWord());
		}
		return words;
	}
	
	/**
	 * @return all
	 * Returns a text output of all the data in the hash table
	 */
	public ArrayList<String> showAll() {
		ArrayList<String> all = new ArrayList<String>();
		ArrayList<ConcordanceDataElement> elements = new ArrayList<ConcordanceDataElement>();
		for (LinkedList<ConcordanceDataElement> bucket : hashTable) {
			for (ConcordanceDataElement e : bucket) {
				elements.add(e);
			}
		}
		System.out.println("elements: " + elements);
		for (ConcordanceDataElement e : elements) {
			all.add(e.toString() + "\n");
		}
		Collections.sort(all);
		return all;
	}
	
	/**
	 * @return hashTable.length
	 * returns the size of the hash table
	 */
	public int getTableSize() {
		System.out.println("returning hash table length of " + hashTable.length);
		return hashTable.length;
	}
	
}
