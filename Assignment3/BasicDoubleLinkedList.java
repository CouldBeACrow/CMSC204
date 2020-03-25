import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * @author Jacob Whiteis
 * @param <T>
 * A basic doubly linked list class. Implements iterable
 */

public class BasicDoubleLinkedList<T> implements Iterable<T> {
	
	protected Node head, tail;
	protected int listSize;
	
	/**
	 * @author Jacob Whiteis
	 * Inner class to implement listIterator
	 * @para <T>
	 */
	public class iterateInner implements ListIterator<T>{

		private Node first = head;
		private Node last = tail;
		boolean firstNode = true;
		boolean lastNode = true;
		
		@Override
		public void add(T arg0) {
			throw new UnsupportedOperationException();
		}

		/**
		 * @param
		 * @return true or false
		 */
		@Override
		public boolean hasNext() {
			return (first.next != null);
		}

		/**
		 * @param
		 * @return true or false
		 */
		@Override
		public boolean hasPrevious() {
			return (last.prev != null);
		}

		/**
		 * @return the data field of the next element
		 */
		@Override
		public T next() {
			if (hasNext() == false) {
				throw new NoSuchElementException();
			}
			else {
				if (firstNode) {
					firstNode = false;
					return first.data;
				}
				else {
					first = first.next;
					return first.data;
				}

			}
		}

		@Override
		public int nextIndex() {
			throw new UnsupportedOperationException();
		}

		/**
		 * @return the data element of the previous element
		 */
		@Override
		public T previous() {
			if (hasPrevious() == false) {
				throw new NoSuchElementException();
			}
			else {
				if (lastNode) {
					lastNode = false;
					return last.data;
				}
				else {
					last = last.prev;
					return last.data;
				}
			}
		}

		@Override
		public int previousIndex() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void set(T arg0) {
			throw new UnsupportedOperationException();
		}
		
	}
	
	/**
	 * Default constructor
	 * @param
	 * @return
	 */
	// Default constructor
	public BasicDoubleLinkedList() {
		listSize = 0;
		head = null;
		tail = null;
	}
	
	/**
	 * 
	 * @author Jacob Whiteis
	 * Node inner class
	 */
	public class Node {
		T data;
		Node next, prev;
		
		// Node default constructor
		public Node() {
			data = null;
			next = null;
			prev = null;
		}
		
		// Node constructor
		public Node(T data, Node next, Node prev) {
			this.data = data;
			this.next = next;
			this.prev = prev;
		}
		
	}
	
	/**
	 * Add node to end of list
	 * @param data
	 * @return
	 */
	public BasicDoubleLinkedList<T> addToEnd(T data) {
		Node newNode = new Node (data, null, tail);
		if (tail != null) {
			tail.next = newNode;
		}
		tail = newNode;
		if (head == null) {
			head = newNode;
		}
		listSize++;
		return this;
	}
	
	/**
	 * Add node to front of list
	 * @param data
	 * @return
	 */
	public BasicDoubleLinkedList<T> addToFront(T data) {
		Node newNode = new Node (data, head, null);
		if (head != null) {
			head.prev = newNode;
		}
		head = newNode;
		if (tail == null) {
			tail = newNode;
		}
		listSize++;
		return this;
	}
	
	/**
	 * 
	 * @return data of first element
	 */
	public T getFirst() {
		if (listSize == 0) {
			return null; // If the list is empty, then first is null
		}
		else {
			return head.data;
		}
	}
	
	/**
	 * 
	 * @return data of last element
	 */
	public T getLast() {
		if (listSize == 0) {
			return null;
		}
		else {
			return tail.data;
		}
	}
	
	/**
	 * 
	 * @return size of list
	 */
	public int getSize() {
		return listSize;
	}

	/**
	 * Returns a new instance of the inner class that implements listIterator
	 */
	@Override
	public ListIterator<T> iterator() {
		return new iterateInner();
	}
	
	/**
	 * Remove an element from the list
	 * @param targetData
	 * @param comparator
	 * @return
	 */
	public BasicDoubleLinkedList<T> remove(T targetData, Comparator<T> comparator) {
		Node currentNode = head;
		if (listSize > 0) {
			if (comparator.compare(targetData, head.data) == 0) {
				head.data = null;
				head = head.next;
				listSize--;
				return this;
			}
			while (comparator.compare(targetData, currentNode.data) != 0) {
				currentNode = currentNode.next;
			}
			if (comparator.compare(targetData,  currentNode.data)== 0) {
				if (comparator.compare(targetData, tail.data) == 0) {
					tail = tail.prev;
					currentNode.data = null;
					listSize--;
					return this;
				}
				else {
					currentNode.prev.next = currentNode.next;
					currentNode.next.prev = currentNode.prev;
					currentNode.data = null;
					listSize--;
					return this;
				}
			}
		}
		return this;
	}
	
	/**
	 * 
	 * @return data of first element
	 */
	public T retrieveFirstElement() {
		Node element = head;
		head = head.next;
		listSize--;
		return element.data;
	}
	
	/**
	 * 
	 * @return data of last element
	 */
	public T retrieveLastElement() {
		if (listSize == 0) {
			return null;
		}
		
		Node element = tail;
		tail = tail.prev;
		listSize--;
		return element.data;
	}
	
	/**
	 * Copies elements of linked list into an arraylist
	 * @return an arraylist
	 */
	public ArrayList<T> toArrayList() {
		ArrayList<T> list = new ArrayList<T>();
		Node currentNode = head;
		while (currentNode.next != null) {
			list.add(currentNode.data);
			currentNode = currentNode.next;
		}
		list.add(currentNode.data);
		return list;
	}
	
}
