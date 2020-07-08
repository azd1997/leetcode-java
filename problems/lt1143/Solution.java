// 最长公共子序列
package lt1143;

import java.util.Arrays;

// 思考:
// 1. 穷举
// 2. 动态规划
    // A="", B="...", 则LCS=""
    // A="...x", B="..x", 从最后这个字符开始看，如果相同，那么LCS[i] = LCS[i-1] + 1
public class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        // base case
        int[][] dp = new int[m+1][n+1];     // dp[i][j]表示LCS(text1[:i+1], text2[:j+1])，i/j表示字符串长度
        for (int i = 0; i < m; i++) dp[i][0] = 0;   // LCS("", "xxx") = 0
        for (int j = 0; j < n; j++) dp[0][j] = 0;

        // 状态转移
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i-1) == text2.charAt(j-1)) {   // i-1,j-1指的是子串最后一个字符的下标
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);    // 无需再和dp[i-1][j-1]比较，是冗余的
                }
            }
        }
        return dp[m][n];
    }
}
