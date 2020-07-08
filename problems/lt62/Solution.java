package lt62;

import java.util.Arrays;

// 思路
// 1. 搜索遍历
// 2. 动态规划递推
    // 可以进一步优化内存
public class Solution {
    public int uniquePaths(int m, int n) {
        if (m <= 0 || n <= 0) return 0;

        // base case
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) dp[i][0] = 1;
        for (int j = 0; j < n; j++) dp[0][j] = 1;

        // 状态转移
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }

        return dp[m-1][n-1];
    }
}
