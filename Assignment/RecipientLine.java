/**
 * Recipient Line class
 * @author jake
 *
 */
public class RecipientLine implements RecipientLineInterface {

	public static final int DEFAULT_SIZE = 0;
	
	MyQueue<Recipient> queue;
	
	/**
	 * Constructor
	 * @param size
	 */
	public RecipientLine(int size) {
		queue = new MyQueue<Recipient>(size);
	}
	
	/**
	 * Default constructor
	 */
	public RecipientLine() {
		queue = new MyQueue<Recipient>(DEFAULT_SIZE);
	}
	
	/**
	 * Check if recipient line is empty
	 * @return true or false
	 */
	@Override
	public boolean recipientLineEmpty() {
		if (queue.isEmpty()) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Add new recipient to recipientLine
	 * @param recipient
	 * @return true
	 */
	@Override
	public boolean addNewRecipient(Recipient rc) throws RecipientException {
		if (queue.enqueue(rc) == false) {
			throw new RecipientException("The Recipient Queue is Full");
		}
		return true;
	}

	/**
	 * Remove recipient from recipient line
	 * @return removed recipient
	 */
	@Override
	public Recipient recipientTurn() throws RecipientException {
		Recipient a = queue.dequeue();
		if (a == null) {
			throw new RecipientException("The Recipient Queue is Empty");
		}
		return a;
	}

	/**
	 * Converts recipientLine queue to array
	 * @return arr
	 */
	@Override
	public Recipient[] toArrayRecipient() {
		Recipient[] arr = new Recipient[queue.size()];
		Object[] temp = queue.toArray();
		for (int i=0; i<queue.size(); i++) {
			arr[i] = (Recipient) temp[i];
		}
		return arr;
	}

}
