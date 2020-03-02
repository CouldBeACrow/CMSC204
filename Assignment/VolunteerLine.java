/**
 * Volunteer Line class
 * @author jake
 *
 */
public class VolunteerLine implements VolunteerLineInterface {
	
	public static final int DEFAULT_SIZE = 0;
	
	MyQueue<Volunteer> queue;

	/**
	 * Constructor
	 * @param size
	 */
	public VolunteerLine(int size) {
		queue = new MyQueue<Volunteer>(size);
	}
	
	/**
	 * Default constructor
	 */
	public VolunteerLine() {
		queue = new MyQueue<Volunteer>(DEFAULT_SIZE);
	}
	
	/**
	 * Check if volunteer line is empty
	 * @return true or false
	 */
	@Override
	public boolean volunteerLineEmpty() {
		if (queue.isEmpty()) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Add new volunteer to volunteerLine
	 * @param volunteer
	 * @return true or false
	 */
	@Override
	public boolean addNewVolunteer(Volunteer v) throws VolunteerException {
		if (queue.enqueue(v) == false) {
			throw new VolunteerException("Volunteer Queue is full");
		}
		return true;
	}

	/**
	 * Remove volunteer from volunteerLine
	 * @return removed volunteer
	 */
	@Override
	public Volunteer volunteerTurn() throws VolunteerException {
		Volunteer a = queue.dequeue();
		if (a == null) {
			throw new VolunteerException("Volunteer queue is empty");
		}
		return a;
	}

	/**
	 * Convert volunteerLine queue to array
	 * @return arr
	 */
	@Override
	public Volunteer[] toArrayVolunteer() {
		Volunteer[] arr = new Volunteer[queue.size()];
		Object[] temp = queue.toArray();
		for (int i=0; i<queue.size(); i++) {
			arr[i] = (Volunteer) temp[i];
		}
		return arr;
	}

}
