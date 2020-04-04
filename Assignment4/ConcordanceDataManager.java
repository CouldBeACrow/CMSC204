import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.IOException;

/**
 * Data manager, creates a hash table object and handles reading and writing from files
 * @author Jacob Whiteis
 *
 */
public class ConcordanceDataManager implements ConcordanceDataManagerInterface {
	
	private ConcordanceDataStructure concordanceTable = new ConcordanceDataStructure(20);

	/**
	 * @param input
	 * Creates a concordance array
	 * @return all concordance array
	 */
	@Override
	public ArrayList<String> createConcordanceArray(String input) {
		
		String[] line;
		String[] word;
		int currentLine = 0;
		
		// Split entire text into lines by newline characters
		line = input.split("\n"); 
		
		for (int i=0; i<line.length; i++) {
			word = line[i].split(" "); // Split entire line into words by spaces
			currentLine = i+1;
			for (int k=0; k<word.length; k++) {
				if (!word[k].equals("the") && !word[k].equals("and") && word[k].length() >= 3) {
					word[k] = word[k].replaceAll("[^a-zA-Z0-9'\\s]+","");
					word[k] = word[k].toLowerCase();
					
					// Length could've changed at this point so recheck the word length
					if (word[k].length() >= 3) {
						concordanceTable.add(word[k], currentLine);
					}
				}
			}
		}
		ArrayList<String> allWords = concordanceTable.showAll();
		Collections.sort(allWords);
		return allWords;
	}

	/**
	 * @param input, output
	 * Creates a text file with the concordance data
	 * @return true 
	 */
	@Override
	public boolean createConcordanceFile(File input, File output) throws FileNotFoundException {
		ArrayList<String> wordsFile = new ArrayList<String>();
		String[] word;
		Scanner scanner = new Scanner(input);
		int currentLine = 0;
		
		while (scanner.hasNext()) {
			wordsFile.add(scanner.nextLine());
		}
		
		System.out.println(wordsFile);
		
		for (int i=0; i<wordsFile.size(); i++) {
			word = wordsFile.get(i).split(" ");
			currentLine = i+1;
			for (int k=0; k<word.length; k++) {
				if (!word[k].toLowerCase().equals("the") && !word[k].toLowerCase().equals("and") && word[k].length() >= 3) {
					word[k] = word[k].replaceAll("[^a-zA-Z0-9'\\s]+","");
					word[k] = word[k].toLowerCase();
					if (word[k].length() >= 3) {
						// System.out.println(word[k] + "   ZZZZZ");
						concordanceTable.add(word[k], currentLine);
					}
				}
			}
			ArrayList<String> outputData = concordanceTable.showAll();
			Collections.sort(outputData);
			try {
				PrintWriter outFile = new PrintWriter(output);
				System.out.println("outputData is " + outputData);
				for (String s : outputData) {
					outFile.write(s);
				}
				outFile.close();
			} catch (IOException e) {
				System.out.println("IOEXCEPTION");
			} catch (Exception e) {
				System.out.println("Some unkown exception threw");
			}

		scanner.close();
		
		}
		return true;
	}
}

