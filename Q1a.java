public class Q1a {

    /**
     * You are given k identical eggs and you have access to a building with n
     * floors labeled from 1 to n.
     *
     * You know that there exists a floor f where 0 <= f <= n such that any egg
     * dropped at a floor higher than f will break, and any egg dropped at or below
     * floor f will not break.
     *
     * Each move, you may choose a floor x with 1 <= x <= n and drop an egg.
     *
     * If the egg breaks, you know that f < x.
     * If the egg does not break, you know that f >= x.
     *
     * Return the minimum number of moves that you need to know with certainty what
     * the value of f is.
     *
     *
     *
     * Example 1:
     *
     * Input: k = 1, n = 2
     * Output: 2
     * Explanation:
     * Drop the egg from floor 1.
     * If the egg breaks, we know that f = 0.
     * If the egg does not break, drop the egg from floor 2.
     * If the egg breaks, we know that f = 1.
     * If the egg does not break, we know that f = 2.
     * Hence, we need 2 moves in the worst case to know what the value of f is.
     * Example 2:
     *
     * Input: k = 2, n = 6
     * Output: 3
     * Example 3:
     *
     * Input: k = 3, n = 14
     * Output: 4
     *
     *
     * Constraints:
     *
     * 1 <= k <= 100
     * 1 <= n <= 104
     */
    public int minMeasurements(int k, int n) {
        int[][] dp = new int[n + 1][k + 1];
        int m = 0;
        while (dp[m][k] < n) {
            m++;
            for (int i = 1; i <= k; i++) {
                dp[m][i] = dp[m - 1][i - 1] + dp[m - 1][i] + 1;
            }
        }
        return m;
    }

    public static void main(String[] args) {
        Q1a solution = new Q1a();
        int k1 = 1, n1 = 2;
        System.out.println("Minimum measurements for k=" + k1 + ", n=" + n1 + ": " + solution.minMeasurements(k1, n1)); // Output:
                                                                                                                        // 2

        int k2 = 2, n2 = 6;
        System.out.println("Minimum measurements for k=" + k2 + ", n=" + n2 + ": " + solution.minMeasurements(k2, n2)); // Output:
                                                                                                                        // 3

        int k3 = 3, n3 = 14;
        System.out.println("Minimum measurements for k=" + k3 + ", n=" + n3 + ": " + solution.minMeasurements(k3, n3)); // Output:
                                                                                                                        // 4
    }
}