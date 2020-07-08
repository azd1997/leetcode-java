// 括号生成
package lt22;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// 思路：
// 1. 遍历所有可能（将所有可能看作图中节点），再检验合法性
// 2. 在生成过程中就保证合法性(先放左括号，右括号依据左括号剩余情况做选择)
public class Solution {
    List<String> res = new LinkedList<>();
    public List<String> generateParenthesis(int n) {
        backtrack(new StringBuilder(), 0, 0, n);
        return res;
    }

    // left/right为本轮已经放的是第几个左/右括号，待放left+1,right+1
    private void backtrack(StringBuilder cur, int left, int right, int n) {
        // 终止
        if (cur.length() == 2 * n) {
            res.add(cur.toString());
            return;
        }

        // 做选择：放左括号还是放右括号
        if (left < n) {     // 当前左括号还能用时，可以选择放左括号
            // 做选择
            cur.append('(');
            backtrack(cur, left+1, right, n);
            // 撤销选择
            cur.deleteCharAt(cur.length()-1);
        }
        if (right < left) { // 右括号数比左括号数少时(自然满足right<n)，可以选择放右括号
            // 做选择
            cur.append(')');
            backtrack(cur, left, right+1, n);
            // 撤销选择
            cur.deleteCharAt(cur.length()-1);
        }
    }


    public static void main(String[] args) {
        Solution sol = new Solution();

        int n = 3;
        List<String> res = sol.generateParenthesis(n);
        System.out.println(res);
    }
}
