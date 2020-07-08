package lt551;

// 思路:
// 不愧是简单题，就是一边遍历的问题
// 其他思路：
// 1. 利用s.indexOf(substr) (s.indexOf("LLL")) 来判断
// 2. 正则表达式 java.util.regexp   s.matches(".*(A.*A|LLL).*")
public class Solution {
    public boolean checkRecord(String s) {
        int ANums = 0;          // A次数
        int continuousL = 0;    // 连续L
        char last = ' ';        // 上一个字符
        for (char c : s.toCharArray()) {
            if (c == 'A') {
                continuousL = 0;    // 非'L'则将continuousL清零
                ANums++;
                if (ANums > 1) return false;
            } else if (c == 'L') {
                continuousL++;
                if (continuousL > 2) return false;
            } else {
                continuousL = 0;    // 非'L'则将continuousL清零
            }
        }
        return true;
    }


// . : 匹配任何除了换行以外的单个字符。
//
// * : 匹配 0 个或者 大于 0 个 * 符号前面的表达式。
//
// .* : 匹配任何字符串
//
// a|b : 要么匹配 a 要么匹配 b

    public boolean checkRecord2(String s) {
        return !s.matches(".*(A.*A|LLL).*");
    }
}
