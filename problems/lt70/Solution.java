package lt70;

public class Solution {
    public int climbStairs(int n) {
        if (n <= 0) return 1;
        if (n < 3) return n;

        // base case
        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 2;

        // 状态转移
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[n];
    }
}
