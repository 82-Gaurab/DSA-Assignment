import java.util.Arrays;

public class Q2b {

    /*
     * You have two points in a 2D plane, represented by the arrays x_coords and
     * y_coords. The goal is to find
     * the lexicographically pair i.e. (i, j) of points (one from each array) that
     * are closest to each other.
     * Goal:
     * Determine the lexicographically pair of points with the smallest distance and
     * smallest distance calculated
     * using
     * | x_coords [i] - x_coords [j]| + | y_coords [i] - y_coords [j]|
     */

    /*
     * Approach
     * 1. Manhattan Distance Calculation:
     * • The distance between two points (i, j) is calculated as:
     * Distance=∣xcoords[i]−xcoords[j]∣+∣ycoords[i]−ycoords[j]∣
     * This is a simple formula to compute the distance between two points.
     * 2. Brute Force Approach:
     * • Step 1: For each possible pair of points (i, j), calculate the Manhattan
     * distance.
     * • Step 2: Track the smallest distance encountered.
     * • Step 3: If multiple pairs have the same minimum distance, return the
     * smallest pair.
     */
    public static int[] findClosestPair(int[] x_coords, int[] y_coords) {
        int n = x_coords.length;
        int minDistance = Integer.MAX_VALUE; // Initialize minimum distance to a large value
        int[] result = new int[2]; // To store the result indices

        // Iterate through all possible pairs of points
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // Calculate Manhattan distance
                int distance = Math.abs(x_coords[i] - x_coords[j]) + Math.abs(y_coords[i] - y_coords[j]);

                // If the current distance is smaller than the minimum distance found so far,
                // update the minimum distance and the result indices
                if (distance < minDistance) {
                    minDistance = distance;
                    result[0] = i;
                    result[1] = j;
                }
                // If the distance is equal to the minimum distance, choose the
                // lexicographically smaller pair
                else if (distance == minDistance) {
                    if (i < result[0] || (i == result[0] && j < result[1])) {
                        result[0] = i;
                        result[1] = j;
                    }
                }
            }
        }

        // Return the indices of the closest pair
        Arrays.sort(result);
        return result;
    }

    public static void main(String[] args) {
        int[] x_coords = { 1, 2, 3, 2, 4 };
        int[] y_coords = { 2, 3, 1, 2, 3 };

        int[] result = findClosestPair(x_coords, y_coords);
        System.out.println(Arrays.toString(result));
    }
}

// Output: [0, 3]