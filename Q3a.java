import java.util.*;

public class Q3a {
    // Union-Find (Disjoint Set Union) data structure
    static class UnionFind {
        int[] parent;
        int[] rank;

        public UnionFind(int size) {
            parent = new int[size];
            rank = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i; // Each node is its own parent initially
                rank[i] = 1; // Rank is 1 initially
            }
        }

        // Find the root of a node with path compression
        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]); // Path compression
            }
            return parent[x];
        }

        // Union two sets by rank
        public boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return false; // Already in the same set
            }
            // Merge smaller tree into larger tree
            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
            return true;
        }
    }

    public static int minCostToConnectDevices(int n, int[] modules, int[][] connections) {
        // Treat installing a module as connecting to a virtual root (device n)
        int virtualRoot = n;
        List<int[]> edges = new ArrayList<>();

        // Add edges for module installations
        for (int i = 0; i < n; i++) {
            edges.add(new int[] { i, virtualRoot, modules[i] });
        }

        // Add edges for connections
        for (int[] connection : connections) {
            int device1 = connection[0] - 1; // Convert to 0-based index
            int device2 = connection[1] - 1; // Convert to 0-based index
            int cost = connection[2];
            edges.add(new int[] { device1, device2, cost });
        }

        // Sort edges by cost
        edges.sort((a, b) -> Integer.compare(a[2], b[2]));

        // Initialize Union-Find
        UnionFind uf = new UnionFind(n + 1); // n devices + virtual root

        int totalCost = 0;
        int edgesUsed = 0;

        // Kruskal's algorithm
        for (int[] edge : edges) {
            int device1 = edge[0];
            int device2 = edge[1];
            int cost = edge[2];
            if (uf.union(device1, device2)) {
                totalCost += cost;
                edgesUsed++;
                if (edgesUsed == n) { // All devices are connected
                    break;
                }
            }
        }

        return totalCost;
    }

    public static void main(String[] args) {
        int n = 3;
        int[] modules = { 1, 2, 2 };
        int[][] connections = { { 1, 2, 1 }, { 2, 3, 1 } };
        System.out.println(minCostToConnectDevices(n, modules, connections)); // Output: 3
    }
}