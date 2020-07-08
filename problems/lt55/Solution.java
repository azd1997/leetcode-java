package lt55;

// 思路:
// 1. 贪心. 每次尝试在可达的最远距离范围内的连续位置，检查下一步是否可达终点
public class Solution {

    // Q: 如何判断 可达到某个位置y ?
    // A: 存在x可达, 并且 x+nums[x] >= y
    // 那么就是从0出发，找0下一步可达的所有位置，看这些位置的下一步能否到达终点，不能则更新最远可达距离，能则返回true
    public boolean canJump(int[] nums) {
        int n = nums.length;
        int rightmost = 0;      // 当前位置向右最远可跳到的位置
        for (int i = 0; i< nums.length; i++) {
            if (i <= rightmost) {   // i不能超过前面诸步所能跳到的最远距离rightmost
                // 更新rightmost
                rightmost = Math.max(rightmost, i + nums[i]);
                if (rightmost >= n-1) return true;  // 向右可到达重点(最右)
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] nums = {2,3,1,1,4};
        boolean ret = sol.canJump(nums);
        System.out.println(ret);
    }
}
