// 乘积最大子数组
package lt152;

// 思考:
// 需要保存正数最大值和负数最大值
// 动态规划:
    //
public class Solution {
    public int maxProduct(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;

        // base case dp[i][0]表示以元素i结尾的乘积和最小值；dp[i][1]则是最大值
        int[][] dp = new int[n][2];
        dp[0][0] = nums[0];
        dp[0][1] = nums[0];

        for (int i = 1; i < n; i++) {
            if (nums[i] >= 0) {
                dp[i][0] = Integer.min(dp[i-1][0] * nums[i], nums[i]);
                dp[i][1] = Integer.max(dp[i-1][1] * nums[i], nums[i]);
            } else {
                dp[i][0] = Integer.min(dp[i-1][1] * nums[i], nums[i]);
                dp[i][1] = Integer.max(dp[i-1][0] * nums[i], nums[i]);
            }
        }

        // 返回dp各项的最大值
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (dp[i][1] > max) max = dp[i][1];
        }
        return max;
    }
}
