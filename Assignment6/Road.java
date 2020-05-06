/**
 * 
 * @author Jacob Whiteis
 * This class is an edge in a graph, and represents a road connecting two towns
 *
 */
public class Road implements Comparable<Road> {

	Town source;		// First town connected
	Town destination;	// Second town connected
	int distance;		// Length of road
	String name;		// Name of road
	
	/** 
	 * Constructor
	 * @param source
	 * @param destination
	 * @param degrees
	 * @param name
	 */
	public Road(Town source, Town destination, int degrees, String name) {
		this.source = source;
		this.destination = destination;
		this.distance = degrees;
		this.name = name;
	}
	
	/** 
	 * Constructor with distance preset to 1
	 * @param source
	 * @param destination
	 * @param name
	 */
	public Road(Town source, Town destination, String name) {
		this.source = source;
		this.destination = destination;
		this.distance = 1;
		this.name = name;
	}
	
	/** 
	 * Returns true if the road contains a given town, false if it doesn't
	 * @param town
	 * @return true or false
	 */
	public boolean contains(Town town) {
		if (source.equals(town) || destination.equals(town)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Return source town
	 * @return source
	 */
	public Town getSource() {
		return source;
	}
	
	/**
	 * Returns destination town
	 * @return destination
	 */
	public Town getDestination() {
		return destination;
	}
	
	/**
	 * Returns name of road
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns distance of road
	 * @return distance
	 */
	public int getWeight() {
		return distance;
	}
	
	/**
	 * toString
	 * @return string representation of the road
	 */
	@Override
	public String toString() {
		return source + " to " + destination + " via " + getName();
	}
	
	/**
	 * Compares 2 Road objects by checking if they are both attached to the same towns
	 * @param r	a road object
	 * @return true or false
	 */
	public boolean equals(Object r) {
		// If the object is compared with itself, return true
		if (r == this) {
			return true;
		}
		// Check if r is an instance of Road or not
		if (!(r instanceof Road)) {
			System.out.println("r is not type Road in Road.java");
			return false;
		}
		Road r1 = (Road) r;
		if ((this.source.equals(r1.source) && this.destination.equals(r1.destination)) || (this.source.equals(r1.destination) && this.destination.equals(r1.source))) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Compare 2 Road objects by comparing their names
	 * @param o a Road object
	 * @return integer
	 */
	@Override
	public int compareTo(Road o) {
		return this.getName().compareTo(o.getName());
	}

}
