// x的平方根
package lt69;

// 寻找y*y <= x的右边界
// 此外，为免y*y溢出，使用x/y来比较更合适
public class Solution {
    public int mySqrt(int x) {
        if (x < 0) return -1;
        int l = 1, r = x;
        while (l <= r) {
            int mid = (r-l)/2 + l;
            if (mid * mid <= x) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return r;
    }

    public int mySqrt2(int x) {
        if (x < 0) return -1;
        int l = 1, r = x;
        while (l <= r) {
            int mid = (r-l)/2 + l;
            int s = x / mid;
            if (s >= mid) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return r;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        int x = 9;
        int ret = sol.mySqrt2(x);
        System.out.println(ret);

        int x1 = 12;
        int ret1 = sol.mySqrt2(x1);
        System.out.println(ret1);

        int x2 = 4;
        int ret2 = sol.mySqrt2(x2);
        System.out.println(ret2);

        int x3 = -1;
        int ret3 = sol.mySqrt2(x3);
        System.out.println(ret3);

        int x4 = 0;
        int ret4 = sol.mySqrt2(x4);
        System.out.println(ret4);

        int x5 = 1;
        int ret5 = sol.mySqrt2(x5);
        System.out.println(ret5);
    }
}
