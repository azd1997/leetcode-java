package lt72;

// 思路：
// 1. 典型的二维DP题
    // 状态
public class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m+1][n+1];

        // base case: 第一行/第一列编辑距离
        for (int i = 0; i <= m; i++) dp[i][0] = i;  // word2为空，word1长度为i，二者的编辑距离自然就是i
        for (int j = 0; j <= n; j++) dp[0][j] = j;

        // 状态转移，自底向上
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 注意word1[i-1]就对应着DP表中的i
                // 因此这里的意思是如果当前位子的两个单词字母相同，那么就不需要增加新的编辑
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    // 否则的话，需要进行编辑
                    dp[i][j] = Math.min(
                            Math.min(
                                    dp[i-1][j] + 1, // 删除word1的这个字母
                                    dp[i][j-1] + 1 // 在word1中插入word2的这个字母
                            ),
                            dp[i-1][j-1] + 1    // 替换这个字母
                    );
                }

            }
        }
        return dp[m][n];
    }
}
