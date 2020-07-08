// 最小栈
// pop、top 和 getMin 操作总是在 非空栈 上调用。
package lt155;

import java.util.Stack;

// 思路：使用额外的栈来记录最小值
class MinStack {
    Stack<Integer> data;
    Stack<Integer> min;

    /** initialize your data structure here. */
    public MinStack() {
        data = new Stack<>();
        min = new Stack<>();
    }

    public void push(int x) {
        data.push(x);
        if (min.size() == 0 || x <= min.peek()) {   // 如果加入了一个比以往的数据都小或等于的数，则更新min stack。这种策略会使得min stack存储更多的数据，但是pop时思路比较简单直
            min.push(x);
        }
    }

    public void pop() {
        if (data.size() > 0) {
            int x = data.pop();
            if (x == min.peek()) {  // 如果被移除的数恰是最小的(min stack栈顶)，则将之移除
                min.pop();
            }
        }
        // 栈已经空则忽略
    }

    public int top() {
        if (data.size() > 0) {
            return data.peek();
        }
        return Integer.MIN_VALUE;
    }

    public int getMin() {
        if (min.size() > 0) {
            return min.peek();
        }
        return Integer.MIN_VALUE;
    }



    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        minStack.getMin();  // --> 返回 -3.
        minStack.pop();
        minStack.top();     // --> 返回 0.
        minStack.getMin();  // --> 返回 -2.

        System.out.println(minStack.data.toString());
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
