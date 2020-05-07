import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author Jacob Whiteis
 * This class represents the graph structure. It holds a list of towns and roads.
 * It handles the Dijkstra's algorithm implementation, as well as general interactions
 * between towns and roads.
 *
 */
public class Graph implements GraphInterface<Town, Road> {

	private HashSet<Town> towns;		// List of all Town objects
	private HashSet<Road> roads;		// List of all Road objects
	
	private ArrayList<Town> visited;	// List of all vertices for which a shortest path has been found
	private ArrayList<Town> unvisited;	// List of all vertices for which a shortest path has not yet been found
	
	/**
	 * Calls dijkstraShortestPath to find a the shortest path from the source vertex to every other vertex.
	 * Then calls backTrackRecurse to start at the destinationVertex, and trace the path back to the source vertex
	 * @param sourceVertex
	 * @param destinationVertex
	 * @return shortestPath a List representing the shortest path from sourceVertex to destinationVertex
	 */
	@Override
	public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
		// Once the method below is called, each Town object will be given a pointer to a previous Town, and a distance value
		// This allows us to backtrack to the source from any vertex we choose
		dijkstraShortestPath(sourceVertex);
		
		// Call backtrackRecurse() to build a list of town names in the order of the shortest path from destination to source
		ArrayList<String> shortestPath = backtrackRecurse(destinationVertex, sourceVertex, new ArrayList<String>());
		
		// Reverse the list
		Collections.reverse(shortestPath);
		return shortestPath;
	}

	/**
	 * Recursive method to trace back the shortest path to the source vertex, starting at the destination vertex.
	 * Utilizes the 'predecessor' reference in each Town, which is set by dijkstraShortestPath().
	 * @param currentVertex
	 * @param sourceVertex
	 * @param backTrackPath
	 * @return backTrackPack an ArrayList representing the path back to the source vertex
	 */
	public ArrayList<String> backtrackRecurse(Town currentVertex, Town sourceVertex, ArrayList<String> backTrackPath) {
		if (currentVertex.getPredecessor() == null && !(currentVertex.equals(sourceVertex))) {
			// If we reach here, it means the graph is disjoint and we should terminate
			// There is no path back the source vertex
			return new ArrayList<>();
		}
		if (currentVertex.equals(sourceVertex)) {
			// We are at the source vertex
			return backTrackPath;
		}
		backTrackPath.add(currentVertex.getPredecessor().getName() + " via "
		+ getEdge(currentVertex.getPredecessor(), currentVertex).getName()
		+ " to " + currentVertex.getName() + " " + getEdge(currentVertex.getPredecessor(), currentVertex).getWeight() + " mi");
		// Recurse with the predecessor of the current vertex
		return backtrackRecurse(currentVertex.getPredecessor(), sourceVertex, backTrackPath);
	}
	
	/**
	 * This method allows us to determine all shortest paths to every vertex, starting from a specified source vertex.
	 * It does this by assigning every Town object a pointer to a previous Town (stepping towards the source along the shortest path),
	 * as well as a distance value to represent the total shortest distance between the source and destination vertices.
	 */
	@Override
	public void dijkstraShortestPath(Town sourceVertex) {
		
		for (Town town : towns) {
			town.setDistanceFromSource(Integer.MAX_VALUE);
		}
		// At this point, all vertices should already be initialized with a distanceFromSource of MAX_VALUE (a very high number)
		// Set our source's distance from source (itself) to 0
		sourceVertex.setDistanceFromSource(0);
		
		// Fill the list of unvisited with every vertex
		for (Town town : towns) {
			unvisited.add(town);
		}
		
		// While there are still unvisited vertices left to be processed
		while (!unvisited.isEmpty()) {
			// Set the current vertex to the vertex with the smallest distance from the source
			// First time around, currentVertex should be sourceVertex, since source distance is 0 and everything else is "infinity"
			Town currentVertex = unvisitedVertexSmallestDistance();
			// Visit all unvisited vertices adjacent to currentVertex
			for (Town town : unvisitedAdjacentVertices(currentVertex)) {
				// Get the road we're on so we can add distance
				for (Road road : town.getAdjacentRoads()) {
					if (road.contains(currentVertex)) {
						int potentialDistanceFromSource = currentVertex.getDistanceFromSource() + road.getWeight();
						if (potentialDistanceFromSource < town.getDistanceFromSource()) {
							town.setDistanceFromSource(potentialDistanceFromSource);
							town.setPredecessor(currentVertex);
						}
						
					}
				}

			}
			// Finished looking through all the unvisited adjacent vertices of currentVertex
			// Remove the current vertex from unvisited and add it to visited, then loop back through
			unvisited.remove(currentVertex);
			visited.add(currentVertex);
		}
	}
	
	/**
	 * Determines which adjacent vertices of the given Town are unvisited, and returns a list of them.
	 * @param currentVertex
	 * @return unvisitedAdjacentVertices
	 */
	public ArrayList<Town> unvisitedAdjacentVertices(Town currentVertex) {
		ArrayList<Town> unvisitedAdjacentVertices = new ArrayList<Town>();
		// Loop through all currentVertex's adjacent towns
		for (Town town : currentVertex.getAdjacentTowns()) {
			if (unvisited.contains(town)) {
				unvisitedAdjacentVertices.add(town);
			}
		}
		return unvisitedAdjacentVertices;
	}
	
	/**
	 * Returns the unvisited vertex with the smallest distance from the source vertex (out of the entire graph).
	 * @return smallestDistanceVertex
	 */
	public Town unvisitedVertexSmallestDistance() {
		// Set town with smallest distance to first vertex in towns
		Town smallestDistanceVertex = unvisited.get(0);
		for (Town town : unvisited) {
			if (town.getDistanceFromSource() < smallestDistanceVertex.getDistanceFromSource()) {
				// Update smallest distance vertex
				smallestDistanceVertex = town;
			}
		}
		return smallestDistanceVertex;
	}
	
	/**
	 * Constructor.
	 */
	public Graph() {
		towns = new HashSet<Town>();
		roads = new HashSet<Road>();
		visited = new ArrayList<Town>();
		unvisited = new ArrayList<Town>();
	}
	
	/**
	 * Return a reference to the road that is connected to the given sourceVertex and destinationVertex.
	 * Will return null if no such road exists.
	 * @param sourceVertex, destinationVertex
	 * @return road
	 */
	@Override
	public Road getEdge(Town sourceVertex, Town destinationVertex) {
		for (Road road : roads) {
			if (road.contains(sourceVertex) && road.contains(destinationVertex)) {
				return road;
			}
		}
		return null;
	}
	
	/**
	 * Returns a reference to the road that has the given name.
	 * @param s
	 * @return road a Road object
	 */
	public Road getEdge(String s) {
		for (Road road : roads) {
			if (road.getName().equals(s)) {
				return road;
			}
		}
		return null;
	}

	/**
	 * Add an edge to the graph
	 * @param sourceVertex
	 * @param destinationVertex
	 * @param weight
	 * @param description
	 * @return road a Road object
	 */
	@Override
	public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		try {
			Road newRoad = new Road(sourceVertex, destinationVertex, weight, description);
			// Add to the list of roads
			roads.add(newRoad);
			// Modify source vertex
			sourceVertex.addAdjacentTown(destinationVertex, weight);
			sourceVertex.addAdjacentRoad(newRoad);
			// Modify destination vertex
			destinationVertex.addAdjacentTown(sourceVertex, weight);
			destinationVertex.addAdjacentRoad(newRoad);
			return newRoad;
		} catch (IllegalArgumentException e) {
			System.out.println("Threw IllegalArgumentException - Vertices do not already exist in graph");
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Adds a new vertex to the graph if there is no existing Town with the given name.
	 * @param v a Town object
	 * @return true or false
	 */
	@Override
	public boolean addVertex(Town v) {
		try {
			// Check whether this town already exists in the set of towns
			for (Town town : towns) {
				if (v.equals(town)) {
					System.out.println("Town already exists, not adding");
					return false;
				}
			}
			// Vertex doesn't exist yet, add it
			towns.add(v);
			return true;
		} catch (NullPointerException e) {
			System.out.println("Threw NPE - the specified vertex is null");
		}
		return false;
	}

	/**
	 * Returns true if the graph contains a road attached to these towns, false if not.
	 * @param sourceVertex
	 * @param destinationVertex
	 * @param true or false
	 */
	@Override
	public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
		for (Road road : roads) {
			if (road.contains(sourceVertex) && road.contains(destinationVertex)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns true if graph contains the given vertex
	 * @param v a Town object
	 * @return true or false
	 */
	@Override
	public boolean containsVertex(Town v) {
		for (Town town : towns) {
			if (v.equals(town)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns a vertex object with the same name as the given string
	 * @param s town name
	 * @return Town object
	 */
	public Town getVertex(String s) {
		for (Town town : towns) {
			if (s.equals(town.getName())) {
				return town;
			}
		}
		return null;
	}

	/**
	 * Returns set of roads
	 * @return roads
	 */
	@Override
	public Set<Road> edgeSet() {
		return roads;
	}

	/**
	 * Returns set of edges attached to a given vertex
	 * @param vertex
	 * @return set
	 */
	@Override
	public Set<Road> edgesOf(Town vertex) {
		try {
			Set<Road> roadsOf = new HashSet<Road>(vertex.getAdjacentRoads());
			return roadsOf;
		} catch (IllegalArgumentException e) {
			System.out.println("Illegal argument exception - vertex does not exist in graph");
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Removes an edge from the graph and modifies the vertices accordingly
	 * @param sourceVertex
	 * @param destinationVertex
	 * @param weight
	 * @param description
	 * @return road
	 */
	@Override
	public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		for (Road road : roads) {
			if (road.getName().compareTo(description) == 0) {
				if (road.getWeight() >= 1 && !(road.getName().equals(null))) {
					// Remove from roads
					roads.remove(road);
					// Modify sourceVertex
					sourceVertex.removeAdjacentTown(destinationVertex);
					sourceVertex.removeAdjacentRoad(road);
					// Modify destinationVertex
					destinationVertex.removeAdjacentTown(sourceVertex);
					destinationVertex.removeAdjacentRoad(road);
					return road;
				}
			}
		}
		return null;
	}

	/**
	 * Removes a vertex and all of its edges, if the vertex exists
	 * @param v Town object
	 * @return true or false
	 */
	@Override
	public boolean removeVertex(Town v) {
		for (Town town : towns) {
			if (v.equals(town)) {
				// Modify towns that this town is connected to
				for (Town adjacentTown : town.getAdjacentTowns()) {
					// Need to access the Road that connects these towns
					Road roadToRemove = null;
					for (Road road : roads) {
						if (road.contains(town) && road.contains(adjacentTown)) {
							roadToRemove = road;
						}
					}
					adjacentTown.removeAdjacentTown(town);
					adjacentTown.removeAdjacentRoad(roadToRemove);
					// Delete the road itself
					roads.remove(roadToRemove);
				}
				// Remove this town
				return towns.remove(town);
			}
		}
		return false;
	}

	/**
	 * Returns a set all towns
	 * @return set
	 */
	@Override
	public Set<Town> vertexSet() {
		return towns;
	}
	
}
