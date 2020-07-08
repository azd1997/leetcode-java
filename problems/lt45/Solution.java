// 跳跃游戏II
package lt45;

// 思路:
// 1. 贪心。
//    站在位置x时，应检查x+1~x+nums[x]的这些位置中哪个能使得从x出发能最远，选那一个，这样可以使步数最少
//
public class Solution {
    // 好理解的版本
    public int jump(int[] nums) {
        int n = nums.length;
        int cnt = 0;
        for (int i = 0; i < n; i = i+nums[i]+1) {
            System.out.println(i + ";" + cnt);
            int rightmost = 0;
            int bestnext = 0;
            cnt++;
            for (int j = i+1; j <= i+nums[i]; j++) {
                if (j + nums[j] > rightmost) {
                    rightmost = j + nums[j];
                    bestnext = j;   // 下一步应该跳到的位置
                }
            }
            // 检查能否到达终点
            if (rightmost >= n-1) {
                return cnt;
            }
        }
        return cnt;
    }

    public int jump2(int[] nums) {
        int n = nums.length;
        int end = 0;
        int rightmost = 0;
        int cnt = 0;
        for (int i = 0; i < n-1; i++) {     // 注意是i<n-1;因为最后一位已经是终点，算进去会步数多加1
            rightmost = Math.max(rightmost, i + nums[i]);   // 找向右能跳到的最远处，这个最远处成为下一次的边界end
            if (i == end) {     // 遇到边界，就更新边界，并将步数加1
                end = rightmost;
                cnt++;
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] nums = {2,3,1,1,4};
        int ret = sol.jump2(nums);
        System.out.println(ret);
    }
}
