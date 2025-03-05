public class Q2a {

    /*
     * You have a team of n employees, and each employee is assigned a performance
     * rating given in the
     * integer array ratings. You want to assign rewards to these employees based on
     * the following rules:
     * Every employee must receive at least one reward.
     * Employees with a higher rating must receive more rewards than their adjacent
     * colleagues.
     * Goal:
     * Determine the minimum number of rewards you need to distribute to the
     * employees.
     * Input:
     * ratings: The array of employee performance ratings.
     * Output:
     * The minimum number of rewards needed to distribute.
     */

    /*
     * Approach
     * To solve this problem efficiently, we use a greedy two-pass approach:
     * 1) Left-to-Right Pass
     * • We start by giving each employee 1 reward initially.
     * • Traverse the ratings array from left to right. If an employee has a higher
     * rating than the one on the left, we increment their reward by 1 more than the
     * previous employee's reward.
     * 
     * 2) Right-to-Left Pass
     * • After the left-to-right pass, we do a second pass from right to left.
     * • If an employee has a higher rating than the one on the right, we make sure
     * that their reward is greater than the reward of the employee on the right (if
     * it isn't already).
     * 3) Final Reward Calculation
     * • The total number of rewards is the sum of the rewards array, where each
     * element represents the number of rewards for a given employee.
     */
    public static int minRewards(int[] ratings) {
        if (ratings == null || ratings.length == 0) {
            return 0;
        }

        int n = ratings.length; // Get the length of the ratings array
        int[] rewards = new int[n]; // Array to store the rewards for each employee

        // Initialize all rewards to 1 since each employee must receive at least one
        // reward
        for (int i = 0; i < n; i++) {
            rewards[i] = 1; // Initialize the reward for the current employee
        }

        // Traverse from left to right and assign rewards based on the left neighbor
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                rewards[i] = rewards[i - 1] + 1; // Assign reward to the current employee based on the left neighbor
            }
        }

        // Traverse from right to left and adjust rewards based on the right neighbor
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                rewards[i] = Math.max(rewards[i], rewards[i + 1] + 1); // Assign reward to the current employee based on
                                                                       // the right neighbor if it is higher
            }
        }

        // Sum up the rewards to get the minimum number of rewards needed
        int totalRewards = 0;
        for (int reward : rewards) {
            totalRewards += reward; // Add the reward for the current employee
        }

        return totalRewards; // Return the minimum number of rewards needed
    }

    public static void main(String[] args) {
        // Test cases: 1
        int[] ratings1 = { 1, 0, 2 };
        System.out.println(minRewards(ratings1));

        // Test cases: 2
        int[] ratings2 = { 1, 2, 2 };
        System.out.println(minRewards(ratings2));
    }
}

// Output:
// 5
// 4