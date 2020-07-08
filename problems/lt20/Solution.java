// 有效的括号
package lt20;

import java.util.Stack;

// 思路：
// 1. 栈
// 2. 可不可以只用三个变量记录左括号个数，保证遍历过程中计数始终>=0呢？
    // 不可以。如果只有一种括号类型，可以这么做。考虑这样的例子 (({(})))，显然这通过计数检查是会误判的
public class Solution {

    public boolean isValid(String s) {
        if (s == null) return false;
        if (s.equals("")) return true;

        Stack<Character> stack = new Stack<>();
        char top;
        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case '(':
                    stack.push('(');
                    continue;
                case ')':
                    if (stack.size() == 0 || stack.pop() != '(') return false;
                    continue;
                case '[':
                    stack.push('[');
                    continue;
                case ']':
                    if (stack.size() == 0 || stack.pop() != '[') return false;
                    continue;
                case '{':
                    stack.push('{');
                    continue;
                case '}':
                    if (stack.size() == 0 || stack.pop() != '{') return false;
                    continue;
                default:
                    return false;
            }
        }
        return stack.size() == 0;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        String[] ss = {"", "()", "()[]{}", "(]", "([)]", "{[]}", "]"};
        boolean[] res = {true, true, true, false, false, true, false};

        if (ss.length != res.length) System.out.println("test error!");
        int succCnt = 0;
        for (int i = 0; i < ss.length; i++) {
            boolean r = sol.isValid(ss[i]);
            if (r != res[i]) {
                System.out.printf("test failed. s=%s, r should be %b, but got %b\n", ss[i], res[i], r);
            }
            succCnt++;
        }
        if (succCnt == ss.length) System.out.println("test passed!");
    }
}
