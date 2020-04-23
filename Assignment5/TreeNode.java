/**
 * Tree Node Class
 * @author Jacob Whiteis
 * @param <T>
 */
public class TreeNode<T> {

	TreeNode<T> left = null;
	TreeNode<T> right = null;
	private T data = null;

	/**
	 * Main constructor
	 * @param dataNode
	 */
	public TreeNode(T dataNode) {
		left = null;
		right = null;
		data = dataNode;
	}
	
	/**
	 * Constructor, useed for making deep copies
	 * @param node
	 */
	public TreeNode(TreeNode<T> node) {
		
	}
	
	/**
	 * Return data
	 * @return data
	 */
	public T getData() {
		return data;
	}
	
	/**
	 * Set data
	 * @param data
	 */
	public void setData(T data) {
		this.data = data;
	}
}
