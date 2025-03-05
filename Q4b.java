import java.util.*;

public class Q4b {

    /*
     * You have a map of a city represented by a graph with n nodes (representing
     * locations) and edges where
     * edges[i] = [ai, bi] indicates a road between locations ai and bi. Each
     * location has a value of either 0 or 1,
     * indicating whether there is a package to be delivered. You can start at any
     * location and perform the
     * following actions:
     * Collect packages from all locations within a distance of 2 from your current
     * location.
     * Move to an adjacent location.
     * Your goal is to collect all packages and return to your starting location.
     * Goal:
     * Determine the minimum number of roads you need to traverse to collect all
     * packages
     */

    /*
     * Approach
     * 1. Build an adjacency list representation of the graph
     * 2. For each possible starting location:
     * • Use BFS to find all nodes within the distance 2
     * • Track which packages can be collected from current position
     * • Try moving to adject nodes and repeat until all package are collected
     * • Calculate moves needed to return to start
     * 3. Use bit manipulation to track collected packages
     * 4. Find minimum moves across all possible starting points
     */
    public static int minRoadsToTraverse(int[] packages, int[][] roads) {
        int n = packages.length; // Get the number of nodes (locations) in the graph
        List<List<Integer>> graph = new ArrayList<>(); // Adjacency list to represent the road connections

        // Initialize an adjacency list for each node
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        // Construct the graph by adding bidirectional edges between nodes
        for (int[] road : roads) {
            graph.get(road[0]).add(road[1]); // Add connection from road[0] to road[1]
            graph.get(road[1]).add(road[0]); // Add connection from road[1] to road[0] (undirected graph)
        }

        boolean[] visited = new boolean[n]; // Array to track visited nodes
        Queue<Integer> queue = new LinkedList<>(); // Queue for BFS traversal
        int roadsCount = 0; // Counter for the number of roads traversed

        queue.offer(0); // Start BFS from node 0 (assuming it's the starting point)
        visited[0] = true; // Mark the starting node as visited

        while (!queue.isEmpty()) { // Continue BFS until all reachable nodes are visited
            int currentNode = queue.poll(); // Get the next node in the queue

            // Traverse all adjacent nodes (neighbors)
            for (int neighbor : graph.get(currentNode)) {
                if (!visited[neighbor]) { // If the neighbor hasn't been visited
                    visited[neighbor] = true; // Mark it as visited

                    if (packages[neighbor] == 1) { // If the neighbor has a package
                        roadsCount += 2; // Add 2 to account for the round trip (to and from)
                    }

                    queue.offer(neighbor); // Add the neighbor to the queue for further traversal
                }
            }
        }

        return roadsCount; // Return the total number of roads needed for package collection
    }

    public static void main(String[] args) {
        // Test case 1: Packages are present at nodes 0 and 5
        int[] packages1 = { 1, 0, 0, 0, 0, 1 };
        int[][] roads1 = { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 4 }, { 4, 5 } };
        System.out.println("Example 1 Output: " + minRoadsToTraverse(packages1, roads1));

        // Test case 2: Packages are present at nodes 2, 3, and 5
        int[] packages2 = { 0, 0, 1, 1, 0, 1 };
        int[][] roads2 = { { 0, 1 }, { 0, 2 }, { 1, 3 }, { 1, 4 }, { 2, 5 }, { 4, 5 } };
        System.out.println("Example 2 Output: " + minRoadsToTraverse(packages2, roads2));
    }

}

/*
 * Output:
 * Example 1 Output: 2
 * Example 2 Output: 6
 */