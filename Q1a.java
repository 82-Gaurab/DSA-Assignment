public class Q1a {

    /**
     * You have a material with n temperature levels. You know that there exists a
     * critical temperature f where
     * 0 <= f <= n such that the material will react or change its properties at
     * temperatures higher than f but
     * remain unchanged at or below f.
     * Rules:
     *  You can measure the material's properties at any temperature level once.
     *  If the material reacts or changes its properties, you can no longer use it
     * for further measurements.
     *  If the material remains unchanged, you can reuse it for further
     * measurements.
     * Goal:
     * Determine the minimum number of measurements required to find the critical
     * temperature
     */

    /*
     * Approach
     * We use an optimized search strategy rather than a brute-force approach.
     * Instead of testing linearly, we balance tests across multiple samples to
     * minimize the worst-case number of tests.
     * 1) Define m = 0 (This represents the number of tests conducted.)
     * 2) Use a formula to decide the next test level:
     * Max Levels Covered=Tests with one less sample+Previous test range+1
     * 3) Stop when the covered levels ≥ n.
     */
    public int minMeasurements(int k, int n) {

        // A DP table where dp[k][n] represents the minimum number of measurements
        // required with k samples and n temperature levels.
        int[][] dp = new int[n + 1][k + 1];

        int m = 0; // The minimum number of moves required

        // Loop until the maximum temperature that can be checked with 'm' samples
        // and 'k' temperature is at least 'n'
        while (dp[m][k] < n) {

            m++; // Increment the move count

            // Iterate over the number of samples available
            for (int i = 1; i <= k; i++) {

                // The recurrence relation:
                // dp[m][i] = dp[m-1][i-1] + dp[m-1][i] + 1
                // - If the sample react (we lose a sample), we check dp[m-1][i-1]
                // - If the sample does not react, we check dp[m-1][i]
                // - The '+1' accounts for the current attempt
                dp[m][i] = dp[m - 1][i - 1] + dp[m - 1][i] + 1;
            }
        }
        return m; // Return the minimum moves required
    }

    public static void main(String[] args) {
        Q1a solution = new Q1a(); // Create an instance of the Q1a class

        // Test case 1: 1 sample, 2 temperature levels
        int k1 = 1, n1 = 2;
        System.out.println("Minimum measurements for k=" + k1 + ", n=" + n1 + ": " + solution.minMeasurements(k1, n1));

        // Test case 2: 2 samples, 6 temperature levels
        int k2 = 2, n2 = 6;
        System.out.println("Minimum measurements for k=" + k2 + ", n=" + n2 + ": " + solution.minMeasurements(k2, n2));

        // Test case 3: 3 samples, 14 temperature levels
        int k3 = 3, n3 = 14;
        System.out.println("Minimum measurements for k=" + k3 + ", n=" + n3 + ": " + solution.minMeasurements(k3, n3));

    }
}

// Output
// Minimum measurements for k=1, n=2: 2
// Minimum measurements for k=2, n=6: 3
// Minimum measurements for k=3, n=14: 4