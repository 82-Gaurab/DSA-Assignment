public class Q1b {

    public static int kthSmallestProduct(int[] returns1, int[] returns2, int k) {
        long left = -1000000000000L; // -1e12
        long right = 1000000000000L; // 1e12

        while (left < right) {
            long mid = left + (right - left) / 2;
            if (count(returns1, returns2, mid) < k) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return (int) left;
    }

    private static long count(int[] returns1, int[] returns2, long target) {
        long cnt = 0;
        for (int x : returns1) {
            if (x > 0) {
                int j = returns2.length - 1;
                while (j >= 0 && (long) x * returns2[j] > target) {
                    j--;
                }
                cnt += j + 1;
            } else if (x < 0) {
                int i = 0;
                while (i < returns2.length && (long) x * returns2[i] > target) {
                    i++;
                }
                cnt += returns2.length - i;
            } else { // x == 0
                if (target >= 0) {
                    cnt += returns2.length;
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        int[] returns1 = { 2, 5 };
        int[] returns2 = { 3, 4 };
        int k = 2;
        System.out.println(kthSmallestProduct(returns1, returns2, k)); // Expected output: 8

        int[] returns3 = { -4, -2, 0, 3 };
        int[] returns4 = { 2, 4 };
        k = 6;
        System.out.println(kthSmallestProduct(returns3, returns4, k)); // Expected output: 0
    }
}