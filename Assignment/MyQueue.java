/**
 * 
 * @author jake
 * Queue class implements queueInterface
 * @param <T>
 */
public class MyQueue<T> implements QueueInterface<T>{

	private final int DEFAULT_SIZE = 0;
	
	private T arr[]; // array to store queue elements
	private int front;
	private int rear;
	private int capacity;
	private int count;
	
	/**
	 * Constructor
	 * @param queueSize
	 */
	public MyQueue(int queueSize) {
		arr = (T[])new Object[queueSize]; // This cast is okay because the new Object arr is of null
		capacity = queueSize;
		front = 0;
		rear = -1;
		count = 0;
	}
	
	/**
	 * Default constructor
	 */
	public MyQueue() {
		arr = (T[])new Object[DEFAULT_SIZE]; // This cast is okay because the new Object arr is of null
		capacity = DEFAULT_SIZE;
		front = 0;
		rear = -1;
		count = 0;
	}
	
	/**
	 * Checks if queue is empty
	 * @return true or false
	 */
	@Override
	// Returns true if empty, false if not
	public boolean isEmpty() {
		return (size() == 0);
	}
	
	/**
	 * Checks if queue is full
	 * @return true or false
	 */
	@Override
	// Returns true if full, false if not
	public boolean isFull() {
		return (capacity == size());
	}
	
	/**
	 * Removes an element from the end of the queue
	 * @return the removed element
	 */
	@Override
	// Deletes and returns element at the front of the queue
	public T dequeue() {
		// Check for queue already being empty
		if (isEmpty()) {
			System.out.println("Queue underflow\nTerminating...");
			return null;
		}
		
		System.out.println("Removing " + arr[front]); // CHANGED FROM ARR[COUNT] TO ARR[COUNT-1] TO ARR[FRONT]
		T removed = arr[front];
		
		front = (front+1) % capacity;
		count--;
		
		return removed;
	}

	/**
	 * returns the size of the queue
	 * @return count
	 */
	@Override
	public int size() {
		return count;
	}

	/**
	 * Adds an element to the queue
	 * @param element
	 * @return true or false 
	 */
	@Override
	public boolean enqueue(T e) {
		// Check for queue already full
		if (isFull()) {
			System.out.println("Queue overflow\nReturning false...");
			return false;
		}
		
		System.out.println("Inserting " + e);
		rear = (rear+1) % capacity;
		arr[rear] = e;
		count++;
		
		return true;
	}

	/**
	 * Converts queue to an array
	 * @param 
	 * @return arr
	 */
	@Override
	public T[] toArray() {
		T[] toArr = (T[])new Object[size()]; // cast is safe
		System.out.println("size is " + size());
		int locFront = front;
		for (int i=0; i<size(); i++) {
			toArr[i] = arr[locFront];
			if (locFront == capacity) {
				locFront = 0;
			}
			else {
				locFront++;
			}
		}
		return toArr;
	}

}
