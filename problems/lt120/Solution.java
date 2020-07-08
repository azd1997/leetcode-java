// 三角形最小路径和
package lt120;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


//[
//        [2],
//       [3,4],
//      [6,5,7],
//     [4,1,8,3]
//]

// 思考：
// 每次向下都可以向左或者向右
// 1. 典型的可以递归回溯，遍历所有路线 O(2^n)
// 2. DP.
    // 1. 重复性(分治)，找到重复子问题。
        // 如上图，minimum("2") = min(minimum("3") + minimum("4")) + 2
        // 可以写作： problem(i, j) = min(sub(i+1, j), sub(i+1, j+1)) + a[i,j]   // i为层索引，j为列索引
    // 2. 定义状态数组 dp[i,j]
    // 3. 写DP方程（状态转移） dp[i, j] = min(dp[i+1, j], dp[i+1, j+1]) + a[i,j]
public class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        // dp数组，注意这里的dp是对应的三角形的行，三角形行的最大长度就是triangle.size() (行和列等长)
        int[] dp = new int[triangle.size()+1];
        // 状态转移
        for (int i = triangle.size() - 1; i >= 0; i--) {    // 从底层向上
            for (int j = 0; j < triangle.get(i).size(); j++) {
                // 本层的第j个，其值 = 下层的第j个 和 下层的第j+1个 的小者 + 自身
                dp[j] = Math.min(dp[j], dp[j+1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];
    }
}
