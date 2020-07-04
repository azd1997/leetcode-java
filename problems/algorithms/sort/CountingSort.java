package algorithms.sort;

import java.util.Arrays;

// 计数排序
// 稳定
// 当输入为n个范围在0~k间的整数时，时空复杂度O(n+k)，是最快的
// 当k(或者说最大最小差值)不是很大时(能维护其频次表)，且分布比较密集(避免浪费过多空间)，计数排序非常高效
// 还有一个缺点是 只能处理整数或经过适当转换能表示为整数的类型
public class CountingSort {
    // 计数排序
    public static void sort(int[] nums) {
        int n = nums.length;
        // 1. 首先需要最大值最小值
        int _max = Integer.MIN_VALUE, _min = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num > _max) _max = num;
            if (num < _min) _min = num;
        }
        // 2. 计数排序要求待排序的区间是分布连续的，不然会占用不必要的空间
        // 将_min作为base，nums中各项表示为num-base
        int base = _min;
        int maxV = _max - base;
        int [] counts = new int[maxV+1];    // 创建一个桶，用于存放 各元素-base 出现的次数， 相当于一个哈希表
        int countsL = maxV + 1;
        int numsL = nums.length;

        // 3. 遍历nums，统计每个元素的出现频次
        for (int i = 0; i < numsL; ++i) {
            counts[nums[i] - base]++;   // 统计每个元素出现的频次，即所谓"计数"
        }

        // 4. 遍历bucket(相当于nums元素频次哈希表)，根据频次去填充回nums
        int sortedIdx = 0;  // 排序后索引
        for (int j = 0; j < countsL; ++j) {
            while (counts[j] > 0) {
                nums[sortedIdx] = j + base;
                sortedIdx++;
                counts[j]--;
            }
        }
    }

    public static void main(String[] ars) {
        int[] nums = {2,3,3,4,1,1,5};
        CountingSort.sort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
