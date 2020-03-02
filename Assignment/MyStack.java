/**
 * 
 * @author jake
 * Stack class implements stackInterface
 * @param <T>
 */
public class MyStack<T> implements StackInterface<T> {

	private T arr[];
	private int top;
	private int capacity;
	
	/**
	 * Constructor
	 * @param stackSize
	 */
	public MyStack(int stackSize) {
		arr = (T[]) new Object[stackSize];
		capacity = stackSize;
		top = -1;
	}
	
	/**
	 * Checks if stack is empty
	 * @return true or false
	 */
	@Override
	public boolean isEmpty() {
		return (size() == 0);
	}

	/**
	 * Checks if stack is full
	 * @return true or false
	 */
	@Override
	public boolean isFull() {
		return (capacity == size());
	}

	/**
	 * Removes an element from top of stack
	 * @return removed element
	 */
	@Override
	public T pop() {
		if (isEmpty()) {
			System.out.println("Underflow\nTerminating");
			return null;
		}
		
		System.out.println("Removing " + peek());
		
		// decrease stack size by 1 and return popped element
		return arr[top--];
	}
	
	/**
	 * Returns the top element in stack
	 * @return element
	 */
	public T peek() { // Returns top element in stack
		if (!isEmpty()) {
			return arr[top];
		}
		else {
			System.out.println("Empty. terminating.");
			return null;
		}
	}

	/**
	 * Returns size of stack
	 * @return size
	 */
	@Override
	public int size() {
		return top + 1;
	}

	/**
	 * Adds an element to top of stack
	 * @param element
	 * @return true or false
	 */
	@Override
	public boolean push(T e) { // add to stack
		if (isFull()) {
			System.out.println("Overflow\nReturning false");
			return false;
		}
		System.out.println("Inserting " + e);
		arr[++top] = e;
		return true;
	}

	/** 
	 * Converts stack to array
	 * @return toArr
	 */
	@Override
	public T[] toArray() {
		T[] toArr = (T[]) new Object[size()];
		for (int i=0; i<size(); i++) {
			toArr[i] = arr[i];
		}
		return toArr;
	}

}
