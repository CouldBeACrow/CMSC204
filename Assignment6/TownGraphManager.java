import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * 
 * @author Jacob Whiteis
 * Manages the Graph class.
 * Allows elements and functionalities of the graph to be accessed by referring to Towns and Roads as their names (Strings).
 *
 */
public class TownGraphManager implements TownGraphManagerInterface {

	Graph myGraph = new Graph();
	
	/**
	 * Adds a road to the graph
	 * @param town1
	 * @param town2
	 * @param weight
	 * @param roadName
	 * @return true or false
	 */
	@Override
	public boolean addRoad(String town1, String town2, int weight, String roadName) {
		// Check whether these towns already exist
		// If they do not exist already, create new Town objects
		Town town1Obj;
		Town town2Obj;
		if (myGraph.getVertex(town1) != null) {
			town1Obj = myGraph.getVertex(town1);
		} else {
			town1Obj = new Town(town1);
			myGraph.addVertex(town1Obj);
		}
		if (myGraph.getVertex(town2) != null) {
			town2Obj = myGraph.getVertex(town2);
		} else {
			town2Obj = new Town(town2);
			myGraph.addVertex(town2Obj);
		}
		myGraph.addEdge(town1Obj, town2Obj, weight, roadName);
		return true;
	}

	/**
	 * Returns a road name based on given towns
	 * @param town1
	 * @param town2
	 * @return roadName
	 */
	@Override
	public String getRoad(String town1, String town2) {
		Town town1Obj = myGraph.getVertex(town1);
		Town town2Obj = myGraph.getVertex(town2);
		String roadName = myGraph.getEdge(town1Obj, town2Obj).getName();
		return roadName;
	}

	/**
	 * Adds a town to the graph
	 * @param v name
	 * @return true or false
	 */
	@Override
	public boolean addTown(String v) {
		Town newTown = new Town(v);
		return myGraph.addVertex(newTown);
	}
	
	/**
	 * Returns a Town object based on a given town name
	 * @param name
	 * @return town
	 */
	@Override
	public Town getTown(String name) {
		Town town = myGraph.getVertex(name);
		return town;
	}

	/**
	 * Returns true if the graph contains a town based on a given name, false if not
	 * @param v name
	 * @return true or false
	 */
	@Override
	public boolean containsTown(String v) {
		Town town = myGraph.getVertex(v);
		if (town == null) {
			return false;
		}
		return myGraph.containsVertex(town);
	}

	/**
	 * Returns true if there is an existing road connection between two given town names, false if not
	 * @param town1
	 * @param town2
	 * @return true or false
	 */
	@Override
	public boolean containsRoadConnection(String town1, String town2) {
		Town t1 = myGraph.getVertex(town1);
		Town t2 = myGraph.getVertex(town2);
		return myGraph.containsEdge(t1, t2);
	}

	/**
	 * Returns a list of all roads in graph
	 * @return allRoads
	 */
	@Override
	public ArrayList<String> allRoads() {
		ArrayList<String> allRoads = new ArrayList<>();
		for (Road road : myGraph.edgeSet()) {
			allRoads.add(road.getName());
		}
		Collections.sort(allRoads);
		return allRoads;
	}

	/**
	 * Deletes a road
	 * @param town1
	 * @param town2
	 * @param road
	 * @return true or false
	 */
	@Override
	public boolean deleteRoadConnection(String town1, String town2, String road) {
		Town t1 = myGraph.getVertex(town1);
		Town t2 = myGraph.getVertex(town2);
		Road r1 = myGraph.getEdge(road);
		int weight = r1.getWeight();
		System.out.println("Removed " + myGraph.removeEdge(t1, t2, weight, road));
		return true;
	}

	/**
	 * Deletes a town
	 * @param v name
	 * @return true or false
	 */
	@Override
	public boolean deleteTown(String v) {
		Town t1 = myGraph.getVertex(v);
		if (t1 == null) {
			return false;
		}
		return myGraph.removeVertex(t1);
	}

	/**
	 * Returns a list of all towns in the graph
	 * @return allTowns
	 */
	@Override
	public ArrayList<String> allTowns() {
		ArrayList<String> allTowns = new ArrayList<>();
		for (Town town : myGraph.vertexSet()) {
			allTowns.add(town.getName());
		}
		Collections.sort(allTowns);
		return allTowns;
	}

	/**
	 * Returns a list of towns representing the shortest path between town1 and town2
	 * @param town1
	 * @param town2
	 * @return shortestPath
	 */
	@Override
	public ArrayList<String> getPath(String town1, String town2) {
		Town t1 = myGraph.getVertex(town1);
		Town t2 = myGraph.getVertex(town2);
		return myGraph.shortestPath(t1, t2);
	}
	
	/**
	 * Populates the graph based off information from a text file
	 * @param selectedFile
	 * @throws IOException
	 */
	public void populateTownGraph(File selectedFile) throws IOException {
		try {
			String line;
			Scanner scanner = new Scanner(selectedFile);
			while (scanner.hasNextLine()) {
				line = scanner.nextLine();
				String[] info = line.split(";|,");
				// Add road (addRoad will automatically handle creating new towns. Ain't that nice!)
				addRoad(info[2], info[3], Integer.parseInt(info[1]), info[0]);
			}
			scanner.close();
		} catch(IOException e) {
			throw e;
		}
	}
}
