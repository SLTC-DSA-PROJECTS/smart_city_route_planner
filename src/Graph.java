import java.util.*;

public class Graph {
    private final boolean undirected;
    private final boolean weighted;
    private final Map<String, List<Edge>> adj = new HashMap<>();
    private final Set<String> locations = new HashSet<>();

    public Graph(boolean undirected, boolean weighted) {
        this.undirected = undirected;
        this.weighted = weighted;
        System.out.println("[INIT] Graph created: " +
                (undirected ? "UNdirected" : "Directed") + ", " +
                (weighted ? "Weighted" : "Unweighted"));
    }

    public boolean addLocation(String name) {
        Objects.requireNonNull(name, "location name");
        if (locations.add(name)) {
            adj.putIfAbsent(name, new ArrayList<>());
            System.out.println("[ADD-LOCATION] Added: " + name);
            return true;
        } else {
            System.out.println("[ADD-LOCATION] Skipped (already exists): " + name);
            return false;
        }
    }

    public boolean removeLocation(String name) {
        if (!locations.remove(name)) {
            System.out.println("[REMOVE-LOCATION] Not found: " + name);
            return false;
        }
        adj.remove(name);
        for (List<Edge> edges : adj.values()) {
            edges.removeIf(e -> e.getTo().equals(name));
        }
        System.out.println("[REMOVE-LOCATION] Removed: " + name + " (and its connections)");
        return true;
    }

    public boolean addRoad(String from, String to, double weight) {
        if (!locations.contains(from) || !locations.contains(to)) {
            if (!locations.contains(from))
                System.out.println("[ADD-ROAD] FAILED: Missing location '" + from + "'");
            if (!locations.contains(to))
                System.out.println("[ADD-ROAD] FAILED: Missing location '" + to + "'");
            return false;
        }

        double effectiveWeight = weighted ? weight : 1.0;
        boolean added = addDirectedEdge(from, to, effectiveWeight);
        if (undirected) added |= addDirectedEdge(to, from, effectiveWeight);

        if (added) {
            System.out.println("[ADD-ROAD] " + from + " -> " + to +
                (weighted ? " (w=" + weight + ")" : "") +
                (undirected ? " [undirected pair added]" : ""));
        } else {
            System.out.println("[ADD-ROAD] Skipped (already exists): " + from + " -> " + to);
        }
        return added;
    }

    public boolean addRoad(String from, String to) {
        return addRoad(from, to, 1.0);
    }

    public boolean removeRoad(String from, String to) {
        boolean removed = removeDirectedEdge(from, to);
        if (undirected) removed |= removeDirectedEdge(to, from);

        if (removed) {
            System.out.println("[REMOVE-ROAD] Removed: " + from + " -> " + to +
                    (undirected ? " (and reverse)" : ""));
        } else {
            System.out.println("[REMOVE-ROAD] FAILED: No road found between " + from + " and " + to);
        }
        return removed;
    }

    public Set<String> getLocations() {
        return Collections.unmodifiableSet(locations);
    }

    public List<Edge> getConnectionsOf(String location) {
        return List.copyOf(adj.getOrDefault(location, List.of()));
    }

    public void printAllConnections() {
        System.out.println("[DISPLAY] ---- Graph Connections ----");
        if (locations.isEmpty()) {
            System.out.println("[DISPLAY] (no locations)");
            return;
        }

        List<String> keys = new ArrayList<>(adj.keySet());
        Collections.sort(keys);

        for (String loc : keys) {
            List<Edge> edges = adj.getOrDefault(loc, List.of());
            if (edges.isEmpty()) {
                System.out.println(loc + " -> (no connections)");
            } else {
                StringJoiner sj = new StringJoiner(", ");
                for (Edge e : edges) {
                    sj.add(weighted ? e.getTo() + "(w=" + e.getWeight() + ")" : e.getTo());
                }
                System.out.println(loc + " -> " + sj);
            }
        }
        System.out.println("[DISPLAY] --------------------------------");
    }

    private boolean addDirectedEdge(String from, String to, double weight) {
        List<Edge> list = adj.computeIfAbsent(from, k -> new ArrayList<>());
        for (Edge e : list) {
            if (e.getTo().equals(to)) {
                return false;
            }
        }
        list.add(new Edge(to, weight));
        return true;
    }

    private boolean removeDirectedEdge(String from, String to) {
        List<Edge> list = adj.get(from);
        if (list == null) return false;
        int before = list.size();
        list.removeIf(e -> e.getTo().equals(to));
        return list.size() < before;
    }

    public static final class Edge {
        private final String to;
        private final double weight;

        private Edge(String to, double weight) {
            this.to = Objects.requireNonNull(to);
            this.weight = weight;
        }

        public String getTo() { return to; }
        public double getWeight() { return weight; }

        @Override
        public String toString() {
            return "(" + to + (weight != 1.0 ? ", w=" + weight : "") + ")";
        }
    }
}
