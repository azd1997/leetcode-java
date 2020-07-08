package lt198;

// DP:
    // 子问题
    // 状态数组 dp[i]表示对于0~i房子能偷到的最大值，最优值就是dp[n-1]
        // dp[i][0] / dp[i][1] 0表示不偷，1表示偷
    // 转移方程 dp[i][0] = dp[i-1][1] + nums[i]
public class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int n = nums.length;
        int[][] dp = new int[n][2];

        // base case
        dp[0][0] = 0;   // 第0号房子不偷
        dp[0][1] = nums[0];

        // 状态转移
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]);
            dp[i][1] = dp[i-1][0] + nums[i];
        }

        return Math.max(dp[n-1][0], dp[n-1][1]);
    }
}
