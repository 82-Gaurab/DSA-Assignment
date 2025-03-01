public class Q2a {
    public static int minRewards(int[] ratings) {
        if (ratings == null || ratings.length == 0) {
            return 0;
        }

        int n = ratings.length;
        int[] rewards = new int[n];

        // Initialize all rewards to 1 since each employee must receive at least one
        // reward
        for (int i = 0; i < n; i++) {
            rewards[i] = 1;
        }

        // Traverse from left to right and assign rewards based on the left neighbor
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                rewards[i] = rewards[i - 1] + 1;
            }
        }

        // Traverse from right to left and adjust rewards based on the right neighbor
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                rewards[i] = Math.max(rewards[i], rewards[i + 1] + 1);
            }
        }

        // Sum up the rewards to get the minimum number of rewards needed
        int totalRewards = 0;
        for (int reward : rewards) {
            totalRewards += reward;
        }

        return totalRewards;
    }

    public static void main(String[] args) {
        int[] ratings1 = { 1, 0, 2 };
        System.out.println(minRewards(ratings1)); // Output: 5

        int[] ratings2 = { 1, 2, 2 };
        System.out.println(minRewards(ratings2)); // Output: 4
    }
}
