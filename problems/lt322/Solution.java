package lt322;

import java.util.Arrays;

// 思路：
// DP一般倒序推导. 这道题一般是DP+贪心（优先面值大的零钱）
// 下面采用标准DP来写一下
public class Solution {
    public int coinChange(int[] coins, int amount) {
        // base case
        int[] dp = new int[amount+1];
        Arrays.fill(dp, amount+1);  // 填充一个大值
        dp[0] = 0;  // base case

        for (int i = 0; i <= amount; i++) {
            for (int c : coins) {
                if (c <= i) {   // 还可以找零
                    dp[i] = Math.min(dp[i], dp[i-c] + 1);
                }
            }
        }

        // 最后检查下dp[amount]是否被修改，没被修改则说明完全无法找零
        if (dp[amount] > amount) {
            return -1;
        } else {
            return dp[amount];
        }
    }
}
