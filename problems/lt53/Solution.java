package lt53;

import java.util.ArrayList;
import java.util.Collections;

// 思路:
// 1. 正向推导. 累加数值，如果当前sum值大于0，则sum+=cur，如果小于0，则sum=cur，并且ret=max(ret, sum)
// 2. 典型的倒推DP
    // DP思考：DP一般需要倒着看，对于本题[-2,1,-3,4,-1,2,1,-5,4]，
    // a. 分治，找到重复子问题。 max_sum(i) = max(max_sum(i-1), 0) + a[i]  // max_sum(i)表示以字母i为结尾的最大子序列
    // b. DP数组（状态定义）dp[i]
    // c. 转移方程： dp[i] = max(dp[i-1], 0) + a[i]
// 当然内存可以优化
public class Solution {
    public int maxSubArray(int[] nums) {
        // base case
        int[] dp = nums;
        // 状态转移
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i-1], 0) + nums[i];
        }
        // 返回dp各项的最大值
        int max = Integer.MIN_VALUE;
        for (int d : dp) {
            if (d > max) max = d;
        }
        return max;
    }
}
