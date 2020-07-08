package lt552;

// 思路:
// 1. 穷举所有状态。检查每个出勤记录是否可奖励
// 2. 动态规划。
    // 显然穷举时会穷举很多不必要的记录，例如发现前两个字母是AA后，后面没必要继续
    // 动态规划思考：
    // 1. 显然需要关注当前记录的长度(递增，1~n)
    // 2. 还要关注到目前为止A的数量(0/1)
    // 3. 以及当前记录末尾的最末尾有几个L (0/1/2)
    // 总共三种状态变量，其确定的值为可奖励的记录个数
    // 因此这是一个三维DP，我们先直接使用三维DP表去做
// 3. 动态规划空间优化
    // 显然可以看到下面的解法中当前状态只与之前6个状态有关，因此空间可以优化为6个DP变量
    // 其实还可以继续优化空间，参看官方题解。
public class Solution {
    public int checkRecord(int n) {
        int MOD = 1000000007;   // 10e9+7
        long[][][] dp = new long[n+1][2][3];    // 使用long可避免中间每次都去模10e9的计算量
        // 初始状态(n=1)
        dp[1][0][0] = 1;
        dp[1][1][0] = 1;
        dp[1][0][1] = 1;
        dp[1][1][1] = 0;    // 不可能
        dp[1][0][2] = 0;
        dp[1][1][2] = 0;
        // DP推导
        for (int i = 2; i <= n; i++) {
            // +P (会影响L，消除之前末尾L带来的威胁)
            dp[i][0][0] = (dp[i-1][0][0] + dp[i-1][0][1] + dp[i-1][0][2]) % MOD;    // 过程中可能就溢出了，因此，每次加法都模一下
            dp[i][1][0] = (dp[i-1][1][0] + dp[i-1][1][1] + dp[i-1][1][2]) % MOD;
            // +A (会影响A)
            dp[i][1][0] += (dp[i-1][0][0] + dp[i-1][0][1] + dp[i-1][0][2]) % MOD;
            // +L (会影响L)
            dp[i][0][1] = dp[i-1][0][0];
            dp[i][1][1] = dp[i-1][1][0];
            dp[i][0][2] = dp[i-1][0][1];
            dp[i][1][2] = dp[i-1][1][1];
        }
        // 最后将dp[n]的六种情况汇总，求和后模上10e9返回
        return (int)((dp[n][0][0] + dp[n][0][1] + dp[n][0][2] +
                dp[n][1][0] + dp[n][1][1] + dp[n][1][2]) % MOD);
    }


    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] ns = {1,2,100};
        int[] res = {3,8,985598218};
        if (ns.length != res.length) System.out.println("test error!");
        int succCnt = 0;
        for (int i = 0; i < ns.length; i++) {
            int r = sol.checkRecord(ns[i]);
            if (r != res[i]) {
                System.out.printf("test failed. n=%d, r should be %d, but got %d", ns[i], res[i], r);
            }
            succCnt++;
        }
        if (succCnt == ns.length) System.out.println("test passed!");
    }
}
