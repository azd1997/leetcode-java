package lt84;

// 思路：
// 1. 比较好想的解法是：使用树状数组来实现一段区间内快速查询最小值，作为长方形的高。
//      而长方形的长则由由长变短。长变短的同时，高必须增加，那么两边就可以选矮边向里侧收缩
//      （如果树状数组还记录有柱子下标的话，可以直接一步跨很多柱子），这样就实现了状态空间的缩减
//      树状数组查数组内元素的时间复杂度O（logn），因此总体时间复杂度O(nlogn)
// 2. 使用单调栈
// 3. 单调栈空间优化。解法2使用了left/right两个数组，还可以优化

import java.util.Stack;

public class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];    // 以某根柱子i为高的举行的左边界（意味着左边界比i要矮），组成的数组
        int[] right = new int[n];

        // 单调栈：元素单调递增或递减的栈. 本题是单调递增栈
        Stack<Integer> monoStack = new Stack<>();

        // 向右一次遍历，得到left数组
        for (int i = 0; i < n; ++i) {
            // 如果新遍历到的柱子高度，比当前单调栈顶的柱子高度要矮的话，
            // 把单调栈内所有比当前柱子高(或等于)的柱子都弹出。维护栈的单调递增
            while (!monoStack.isEmpty() &&
                    heights[monoStack.peek()] >= heights[i]) {
                monoStack.pop();
            }
            // 以当前柱子(i)为高的矩形，其左边界是左边第一根比它矮的柱子。如果没有，则是-1（right - (-1)也能求出正确的矩形长度）
            right[i] = monoStack.isEmpty() ? -1 : monoStack.peek();
            // 再把当前柱子(i)压入单调栈
            monoStack.push(i);
        }

        monoStack.clear();

        // 向左一次遍历，得到right数组
        for (int i = n-1; i >=0; --i) {
            // 如果新遍历到的柱子高度，比当前单调栈顶的柱子高度要矮的话，
            // 把单调栈内所有比当前柱子高(或等于)的柱子都弹出。维护栈的单调递增
            while (!monoStack.isEmpty() &&
                    heights[monoStack.peek()] >= heights[i]) {
                monoStack.pop();
            }
            // 以当前柱子(i)为高的矩形，其右边界是右边第一根比它矮的柱子。如果没有，则是n（n-left也能求出正确的矩形长度）
            left[i] = monoStack.isEmpty() ? n : monoStack.peek();
            // 再把当前柱子(i)压入单调栈
            monoStack.push(i);
        }

        // 再一次遍历，统计并更新最大矩形面积
        // 注意，由于左右边界不包含在矩形内，因此矩形长度 = right - left - 1
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans = Math.max(ans,
                    (right[i] - left[i] - 1) * heights[i]);
        }

        return ans;
    }

}
