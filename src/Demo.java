public class Demo {
    public static void main(String[] args) {
        // Undirected, Weighted Graph
        Graph weightedGraph = new Graph(true, true);

        // Add locations
        weightedGraph.addLocation("Colombo");
        weightedGraph.addLocation("Kandy");
        weightedGraph.addLocation("Galle");
        weightedGraph.addLocation("Matara");
        weightedGraph.addLocation("Negombo");
        weightedGraph.addLocation("Jaffna");

        // Add roads
        weightedGraph.addRoad("Colombo", "Kandy", 115.0);
        weightedGraph.addRoad("Colombo", "Galle", 120.5);
        weightedGraph.addRoad("Kandy", "Jaffna", 395.0);
        weightedGraph.addRoad("Galle", "Matara", 45.7);
        weightedGraph.addRoad("Colombo", "Negombo", 35.2);
        weightedGraph.addRoad("Negombo", "Kandy", 100.8);
        weightedGraph.addRoad("Kandy", "Matara", 200.0);

        // Try adding a road to a missing vertex
        weightedGraph.addRoad("Matara", "Trincomalee", 350.0);

        // Display the graph
        weightedGraph.printAllConnections();

        // Remove a few roads
        weightedGraph.removeRoad("Colombo", "Galle");
        weightedGraph.removeRoad("Negombo", "Matara"); // doesn't exist

        // Remove a vertex and display again
        weightedGraph.removeLocation("Jaffna");
        weightedGraph.printAllConnections();

        // Directed, Unweighted graph
        Graph unweightedGraph = new Graph(false, false);

        // Add locations
        unweightedGraph.addLocation("A");
        unweightedGraph.addLocation("B");
        unweightedGraph.addLocation("C");
        unweightedGraph.addLocation("D");
        unweightedGraph.addLocation("E");

        // Add directed connections
        unweightedGraph.addRoad("A", "B");
        unweightedGraph.addRoad("A", "C");
        unweightedGraph.addRoad("B", "D");
        unweightedGraph.addRoad("C", "E");
        unweightedGraph.addRoad("E", "A");

        // Attempt invalid road
        unweightedGraph.addRoad("Z", "A"); // Missing vertex

        // Display final graph state
        unweightedGraph.printAllConnections();
    }
}
