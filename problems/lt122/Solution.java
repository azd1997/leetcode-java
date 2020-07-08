// 买卖股票的最佳时机II
package lt122;

// 思路:
// 1. 常规思路：动态规划
// 2. 贪心。 由于可以交易任意多次，所以只要第二天比前一天股价高就在第一天买，第二天卖
public class Solution {
    public int maxProfit(int[] prices) {
        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i-1]) {
                max += (prices[i] - prices[i-1]);   // 卖出
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] prices = {7,1,5,3,6,4};
        int ret = sol.maxProfit(prices);
        System.out.println(ret);
    }
}
