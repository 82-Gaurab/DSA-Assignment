import java.util.*;

public class Q4b {
    static class Graph {
        int nodes;
        List<List<Integer>> adjList;

        // Constructor to initialize the graph with given number of nodes
        Graph(int nodes) {
            this.nodes = nodes;
            adjList = new ArrayList<>();
            for (int i = 0; i < nodes; i++) {
                adjList.add(new ArrayList<>());
            }
        }

        // Method to add an edge between two nodes
        void addEdge(int u, int v) {
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }
    }

    // Function to determine the minimum roads needed to collect all packages
    public static int minRoadsToCollectPackages(int[] packages, int[][] roads) {
        int n = packages.length;
        Graph graph = new Graph(n);

        // Build the graph using the provided roads
        for (int[] road : roads) {
            graph.addEdge(road[0], road[1]);
        }

        // Identify locations where packages are present
        Set<Integer> packageLocations = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (packages[i] == 1) {
                packageLocations.add(i);
            }
        }

        // Find the shortest path to collect all packages starting from node 2
        return shortestPath(graph, packageLocations, 2);
    }

    // BFS function to calculate the shortest path to collect all packages
    private static int shortestPath(Graph graph, Set<Integer> packageLocations, int start) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[] visited = new boolean[graph.nodes];
        queue.offer(new int[] { start, 0 }); // Start node with distance 0
        visited[start] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int node = curr[0], dist = curr[1];

            // If current node has a package, remove it from the set
            if (packageLocations.contains(node)) {
                packageLocations.remove(node);
            }

            // If all packages are collected, return the distance traveled
            if (packageLocations.isEmpty()) {
                return dist;
            }

            // Explore the neighbors of the current node
            for (int neighbor : graph.adjList.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(new int[] { neighbor, dist + 1 });
                }
            }
        }
        return -1; // Return -1 if it's impossible to collect all packages
    }

    // Main method to test the function
    public static void main(String[] args) {
        int[] packages = { 1, 0, 0, 0, 0, 1 };
        int[][] roads = { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 4 }, { 4, 5 } };
        System.out.println(minRoadsToCollectPackages(packages, roads));
    }
}