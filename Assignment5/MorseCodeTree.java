import java.util.ArrayList;

/**
 * A generic linked binary tree which inherits from LinkedConverterTreeInterface
 * @author Jacob Whiteis
 *
 */
public class MorseCodeTree implements LinkedConverterTreeInterface<String> {

	public static final String DOT = ".";
	public static final String DASH = "-";
	
	private TreeNode<String> root;
	
	/**
	 * Constructor, calls buildTree
	 */
	public MorseCodeTree() {
		buildTree();
	}
	
	/**
	 * Returns a reference to the root of MorseCodeTree
	 * @return root
	 */
	@Override
	public TreeNode<String> getRoot() {
		return root;
	}

	/**
	 * Sets the root of MorseCodeTree
	 * @param newNode
	 */
	@Override
	public void setRoot(TreeNode<String> newNode) {
		root = newNode;
	}

	/**
	 * Adds element to the correct position in the tree based on the code.
	 * This method will call the recursive method addNode.
	 */
	@Override
	public LinkedConverterTreeInterface<String> insert(String code, String result) {
		addNode(root, code, result);
		return this;
	}

	/**
	 * Recursive method that adds an element to the correct position in the tree based on the code.
	 * A '.' means traverse to the left. A '-' means traverse to the right. 
	 * The code '.-' would be stored as the right child of the left child of the root.
	 * @param root	
	 * @param code	
	 * @param letter
	 */
	@Override
	public void addNode(TreeNode<String> root, String code, String letter) {
		
		if (code.length() == 1) {
			if (code.equals(DOT)) {
				if (root.left == null) {
					root.left = new TreeNode<String>(letter);
				}
				root.left.setData(letter);
			} else if (code.equals(DASH)) {
				if (root.right == null) {
					root.right = new TreeNode<String>(letter);					
				}
				root.right.setData(letter);
			}
			return;
		}
		
		else if (code.length() > 1) {
			if (code.charAt(0) == DOT.charAt(0)) {
				if (root.left == null) {
					root.left = new TreeNode<String>("");
				}
				root = root.left;
				code = code.substring(1);	// Removes first character from the code string
				addNode(root, code, letter);
			} else if (code.charAt(0) == DASH.charAt(0)) {
				if (root.right == null) {
					root.right = new TreeNode<String>("");
				}
				root = root.right;
				code = code.substring(1);	// Removes first character from the code string
				addNode(root, code, letter);
			}
		}
	}

	/**
	 * Fetch the data in the tree based on the code. This method will call the recursive method fetchNode.
	 * @param code
	 * @return letter
	 */
	@Override
	public String fetch(String code) {
		return fetchNode(root, code);
	}

	/**
	 * This is the recursive method that fetches the data of the TreeNode that corresponds with the code.
	 * @param root
	 * @param code
	 * @return the string (letter) corresponding to the code
	 */
	@Override
	public String fetchNode(TreeNode<String> root, String code) {

		String letter = "";
		
		if (code.length() == 1) {
			if (code.equals(DOT)) {
				return letter = root.left.getData();
			} else if (code.equals(DASH)) {
				letter = root.right.getData();
				return letter;
			}
		}
		
		else if (code.length() > 1) {
			if (code.charAt(0) == DOT.charAt(0)) {
				root = root.left;
				code = code.substring(1);
				return fetchNode(root, code);
			} else if (code.charAt(0) == DASH.charAt(0)) {
				root = root.right;
				code = code.substring(1);
				return fetchNode(root, code);
			}
		}
		
		return "";
		
	}

	/**
	 * This operation is not supported in the MorseCodeTree
	 */
	@Override
	public LinkedConverterTreeInterface<String> delete(String data) throws UnsupportedOperationException {
		System.out.println("Shouldn't be in the delete method");
		return null;
	}

	/**
	 * This operation is not supported in the MorseCodeTree
	 */
	@Override
	public LinkedConverterTreeInterface<String> update() throws UnsupportedOperationException {
		System.out.println("Shouldn't be inthe update method");
		return null;
	}

	/**
	 * Builds the MorseCodeTree by traversing parallel arrays of codes and letters, and calling insert()
	 */
	@Override
	public void buildTree() {
		String[] codes = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", 
								"-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};
		String[] letters = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
							"t", "u", "v", "w", "x", "y", "z"};
		root = new TreeNode<String>("");
		for (int i=0; i<codes.length; i++) {
			insert(codes[i], letters[i]);
		}
	}

	/**
	 * Returns an ArrayList of the items in the linked Tree in LNR (Inorder) Traversal order
	 * Used for testing to make sure tree is built correctly
	 */
	@Override
	public ArrayList<String> toArrayList() {
		ArrayList<String> lettersList = new ArrayList<String>();
		LNRoutputTraversal(root, lettersList);
		return lettersList;
	}

	/**
	 * The recursive method to put the contents of the tree in an ArrayList in LNR (Inorder)
	 */
	@Override
	public void LNRoutputTraversal(TreeNode<String> root, ArrayList<String> list) {
		
		if (root == null) {
			return;
		}
		
		LNRoutputTraversal(root.left, list);
		list.add(root.getData());
		LNRoutputTraversal(root.right, list);
		
	}

}
