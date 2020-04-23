import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class for converting Morse code to English
 * @author Jacob Whiteis
 *
 */
public class MorseCodeConverter {

	/**
	 * Default constructor
	 */
	public MorseCodeConverter() {
		
	}
	
	/**
	 * Returns a string with all the data in the tree, inorder. Calls toArrayList in MorseCodeTree.
	 * @return tree
	 */
	public static String printTree() {
		MorseCodeTree tree = new MorseCodeTree();
		String treeString = "";
		ArrayList<String> treeList = tree.toArrayList();
		for (int i=0; i<treeList.size(); i++) {
			treeString += treeList.get(i);
			if (i != treeList.size()-1) {
				treeString += " ";
			}
		}
		return treeString;
	}
	
	/**
	 * Converts Morse code into English. Each letter is delimited by a space (' '). Each word is delimited by a slash ('/').
	 * @param code
	 * @return english 
	 */
	public static String convertToEnglish(String code) {
		MorseCodeTree tree = new MorseCodeTree();
		String english = "";
		String[] words = code.split("/");
		String[] letters;
		for (int i=0; i<words.length; i++) {
			letters = words[i].split(" ");
			for (int k=0; k<letters.length; k++) {
				english += tree.fetch(letters[k]);
			}
			if (i != words.length - 1) {
				english += " ";
			}
		}
		return english;
	}
	
	/**
	 * Converts Morse code into English, from a given file.
	 * @param codeFile
	 * @return english
	 */
	public static String convertToEnglish(File codeFile) throws FileNotFoundException {
		String code = "";
		Scanner scanner = new Scanner(codeFile);
		while (scanner.hasNext()) {
			code += (scanner.nextLine());
		}
		scanner.close();
		return convertToEnglish(code);
	}
}
