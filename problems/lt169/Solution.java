package lt169;


// 思路：
// 1. 排序，中间元素一定是多数元素
// 2. 哈希表统计频次
// 3. 分治：区间分成两半，区间的众数 至少是两个子区间之一的众数

import java.util.Arrays;

public class Solution {
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }
}
