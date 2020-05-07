import java.util.ArrayList;

/**
 * 
 * @author Jacob Whiteis
 * This class is a vertex in a graph, and represents a town that is attached to roads.
 *
 */
public class Town implements Comparable<Town> {

	private String name;					// Name of the town
	private ArrayList<Town> adjacentTowns;	// List towns directly connected to this town
	private ArrayList<Road> adjacentRoads;	// List of roads connected to this town
	private int distanceFromSource;			// Total distance from source vertex (used later with Dijkstra's algorithm)
	private Town predecessor;				// Previous vertex in path (used later with Dijkstra's algorithm)
	
	/**
	 * Constructor
	 * @param name
	 */
	public Town(String name) {
		this.name = name;
		this.adjacentTowns = new ArrayList<Town>();
		this.adjacentRoads = new ArrayList<Road>();
		this.distanceFromSource = Integer.MAX_VALUE; // "Infinity", since we don't yet know the distance from source
		this.predecessor = null;
	}
	
	/**
	 * Copy constructor
	 * @param templateTown
	 */
	public Town(Town templateTown) {
		this.name = templateTown.getName();
		this.adjacentTowns = new ArrayList<Town>(templateTown.getAdjacentTowns());
		this.adjacentRoads = new ArrayList<Road>(templateTown.getAdjacentRoads());
		this.distanceFromSource = templateTown.getDistanceFromSource();
		this.predecessor = templateTown.getPredecessor();
	}

	/**
	 * Compare 2 Town objects by comparing their names
	 * @param o a Town object
	 * @return integer
	 */
	@Override
	public int compareTo(Town o) {
		return this.getName().compareTo(o.getName());
	}
	
	/**
	 * Returns name of town
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Add an adjacent town
	 * @param town
	 * @param distance
	 */
	public void addAdjacentTown(Town town, int distance) {
		adjacentTowns.add(town);
	}
	
	/**
	 * Remove an adjacent town
	 * @param town
	 */
	public void removeAdjacentTown(Town town) {
		if (adjacentTowns.contains(town)) {
			adjacentTowns.remove(town);
		}
	}
	
	/**
	 * Returns ArrayList of adjacent towns
	 * @return adjacentTowns
	 */
	public ArrayList<Town> getAdjacentTowns() {
		return adjacentTowns;
	}
	
	/**
	 * Adds an adjacent road
	 * @param road
	 */
	public void addAdjacentRoad(Road road) {
		adjacentRoads.add(road);
	}
	
	/**
	 * Removes an adjacent road
	 * @param road
	 */
	public void removeAdjacentRoad(Road road) {
		adjacentRoads.remove(road);
	}
	
	/**
	 * Returns ArrayList of adjacent roads
	 * @return adjacentRoads
	 */
	public ArrayList<Road> getAdjacentRoads() {
		return adjacentRoads;
	}
	
	/**
	 * Set the predecessor town
	 * @param predecessor
	 */
	public void setPredecessor(Town predecessor) {
		this.predecessor = predecessor;
	}
	
	/**
	 * Returns a reference to the predecessor town
	 * @return predecessor
	 */
	public Town getPredecessor() {
		return predecessor;
	}
	
	/**
	 * Sets total distance from source
	 * @param distanceFromSource
	 */
	public void setDistanceFromSource(int distanceFromSource) {
		this.distanceFromSource = distanceFromSource;
	}
	
	/**
	 * Returns total distance from source
	 * @return distanceFromSource
	 */
	public int getDistanceFromSource() {
		return distanceFromSource;
	}
	
	/**
	 * Returns true if town names are equal by comparison of names, false if not
	 * @param t a Town object
	 * @return true or false
	 */
	@Override
	public boolean equals(Object t) {
		// If the object is compared with itself, return true
		if (t == this) {
			return true;
		}
		// Check if t is an instance of Town or not
		if (!(t instanceof Town)) {
			System.out.println("t is not of type Town in Town.java");
			return false;
		}
		Town t1 = (Town) t;
		if (this.compareTo(t1) == 0) {
			// Names are equal
			return true;
		} 
		else {
			// Names are not equal
			return false;
		}
	}
	
	/**
	 * Return hashcode
	 * @return hashCode
	 */
	@Override
	public int hashCode() {
		return this.getName().hashCode();
	}
	
	/**
	 * Returns toString
	 * return toString
	 */
	@Override
	public String toString() {
		return this.getName();
	}
}
