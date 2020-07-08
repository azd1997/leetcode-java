// 有效的完全平方数
package lt367;

// 思路：找target 使得target*target = num
public class Solution {
    public boolean isPerfectSquare(int num) {
        if (num < 0) return false;
        if (num == 0) return false;  // 因为后面有nums/mid，所以不能查找范围不能有0; 题目认为0不算完全平方数

        int l = 1, r = num;
        while (l <= r) {
            int mid = (r-l)/2 + l;
            int s = num / mid, remained = num % mid;    // 注意除法会向下圆整，因此需要检查余数
            if (s == mid && remained == 0) {
                return true;
            } else if (s > mid || (s == mid && remained != 0)) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return false;
    }
}
