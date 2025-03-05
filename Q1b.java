public class Q1b {

    /*
     * You have two sorted arrays of investment returns, returns1 and returns2, and
     * a target number k. You
     * want to find the kth lowest combined return that can be achieved by selecting
     * one investment from each
     * array.
     * Rules:
     *  The arrays are sorted in ascending order.
     *  You can access any element in the arrays.
     * Goal:
     * Determine the kth lowest combined return that can be achieved.
     */

    /*
     * Approach
     * Step 1: Use a Min Heap (Priority Queue)
     * We can use a min heap (priority queue) to efficiently find the k-th smallest
     * product:
     * 1) Start with the smallest possible product: Multiply the first element from
     * returns1 with each element from returns2.
     * 2) Use a heap to store and process the smallest elements first.
     * 3) Keep track of visited pairs to avoid duplication.
     * 4) Extract k times from the heap until we find the k-th smallest product.
     */

    public static int kthSmallestProduct(int[] returns1, int[] returns2, int k) {
        // Define search range for possible products
        long left = -1000000000000L; // Smallest possible product -1e12
        long right = 1000000000000L; // Largest possible product 1e12

        // Perform binary search to find the kth smallest return
        while (left < right) {
            long mid = left + (right - left) / 2; // Midpoint to check

            // Count how many returns are <= mid
            if (count(returns1, returns2, mid) < k) {
                left = mid + 1; // If count is less than k, move left bound up
            } else {
                right = mid; // Otherwise, move right bound down
            }
        }
        return (int) left; // The kth smallest return
    }

    private static long count(int[] returns1, int[] returns2, long target) {
        long cnt = 0; // Counter for valid returns
        for (int x : returns1) {
            if (x > 0) {
                int j = returns2.length - 1; // Start from last index of returns2
                while (j >= 0 && (long) x * returns2[j] > target) {
                    j--; //// Move left if product is greater than target
                }
                cnt += j + 1; // Count valid pairs
            } else if (x < 0) {
                int i = 0; // Start from first index of returns2
                while (i < returns2.length && (long) x * returns2[i] > target) {
                    i++; // Move right if product is greater than target
                }
                cnt += returns2.length - i;
            } else { // x == 0
                if (target >= 0) {
                    cnt += returns2.length; // All products are valid when target is non-negative
                }
            }
        }
        return cnt; // Return count of valid products
    }

    public static void main(String[] args) {
        // Test case 1
        int[] returns1 = { 2, 5 };
        int[] returns2 = { 3, 4 };
        int k = 2;
        System.out.println(kthSmallestProduct(returns1, returns2, k)); // Expected output: 8

        // Test case 2
        int[] returns3 = { -4, -2, 0, 3 };
        int[] returns4 = { 2, 4 };
        k = 6;
        System.out.println(kthSmallestProduct(returns3, returns4, k)); // Expected output: 0
    }
}

// Output:
// 8
// 0