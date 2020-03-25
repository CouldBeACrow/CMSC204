import java.util.Comparator;
import java.util.ListIterator;

/**
 * Sorted double linked list. Extends the basic double linked list class
 * @author Jacob Whiteis
 *
 * @param <T>
 */
public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T> {

	Comparator<T> myComparator;
	
	/**
	 * Default constructor
	 * @param comparator2
	 */
	SortedDoubleLinkedList(Comparator<T> comparator2) {
		myComparator = comparator2;
	}
	
	/**
	 * Remove method. Calls superclass remove method.
	 */
	public SortedDoubleLinkedList<T> remove(T data, Comparator<T> comparator) {
		return (SortedDoubleLinkedList<T>) super.remove(data, comparator);
	}
	
	/**
	 * Calls super iterator method.
	 */
	public ListIterator<T> iterator(){
		return super.iterator();
	}
	
	/**
	 * Not supported for sorted list
	 */
	public BasicDoubleLinkedList<T> addToEnd(T data) throws UnsupportedOperationException {
		return this;
	}
	
	/**
	 * Not supported for sorted list
	 */
	public BasicDoubleLinkedList<T> addToFront(T data) throws UnsupportedOperationException {
		return this;
	}
	
	/**
	 * Add an element in the correct position.
	 * @param data
	 * @return
	 */
	public SortedDoubleLinkedList<T> add(T data) {
		Node newNode = new Node(data, null, null);
		Node currentNode = head;
		while (currentNode.next != null) {
			if (myComparator.compare(data, currentNode.data) > 0 && currentNode.next != null) {
				currentNode = currentNode.next;
			}
			else if (myComparator.compare(data,  currentNode.data) > 0 && currentNode.next == null) {
				tail.next = newNode;
				newNode.prev = tail;
				tail = newNode;
				listSize++;
				return this;
			}
			else if (myComparator.compare(data, currentNode.data) < 0 && currentNode.next != null) {
				newNode.prev = currentNode.prev;
				newNode.next = currentNode;
				currentNode.prev.next = newNode;
				currentNode.prev = newNode;
				listSize++;
				return this;
			}
			else if (myComparator.compare(data,  currentNode.data) < 0 && currentNode.next == null) {
				newNode.prev =  currentNode;
				currentNode.next = newNode;
				listSize++;
				return this;
			}
		}
		return this;
	}
	
}
