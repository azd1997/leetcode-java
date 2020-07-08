// 分发饼干
package lt455;

import java.util.Arrays;

// 思路
// 1. 很明显的贪心，优先将小的饼干发给胃口小的孩子。就是对两个数组排序
public class Solution {
    public int findContentChildren(int[] g, int[] s) {
        // 排序
        Arrays.sort(g);
        Arrays.sort(s);
        // 遍历g
        int cnt = 0;    // 可满足的小孩数
        int si = 0; // s数组下标
        for (int i = 0; i < g.length; i++) {
            boolean find = false;   // 为这个小孩找到合适的饼干吗
            // 向右寻找第一块恰好能满足胃口的饼干
            for (int j = si; j < s.length; j++) {
                if (s[j] >= g[i]) {
                    find = true;
                    cnt++;
                    si = j+1;
                    break;
                }
            }
            // 如果没找到合适的饼干，那么提前停止遍历
            if (!find) {
                break;
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] g = {1,2,3}, s = {1,1};
        int ret = sol.findContentChildren(g,s);
        System.out.println(ret);
    }
}
