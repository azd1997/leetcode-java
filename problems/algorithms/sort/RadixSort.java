package algorithms.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

// 基数排序
// 低位优先(LSD)排序
public class RadixSort {
//    private static int mod = 10;  // 10进制数取每位时的模数（%MOD）
//    private static int dev = 1;
//
//
//    public static void sort(int[] nums) {
//        int n = 1;  // 代表位数对应的数值: 1, 10, 100, ...
//        int k = 0;  // 保留每一位排序的结果，用于下一位的排序输入
//
//        // 边界
//        if (nums.length == 0) return;
//        // 统计得到输入数据的最大值
//        int _max = Integer.MIN_VALUE;
//        for (int num : nums) {
//            if (num > _max) _max = num;
//        }
//        // 有了最大值，就知道所有数据表示时的最大位数
//        int maxDigit;
//        while (_max > 0) {
//            _max /= mod;
//            maxDigit++;
//        }
//
//        //
//        int[][] counter;
//        for (int i = 0; i < maxDigit; ++i, dev *= 10, mod *= 10) {
//            for (int j = 0; j < nums.length; ++j) {
//                int bucket = (nums[j] % mod) / dev;
//                if ()
//            }
//        }
//
//        // 桶初始化，这里不作桶大小的自定义了
//        int bucketSize = DEFAULT_BUCKET_SIZE;
//        int bucketCnt = Math.floorDiv(_max - _min, bucketSize) + 1;    // 桶的数量
//        //ArrayList<ArrayList<Integer>> buckets = new ArrayList<>(bucketCnt);     // 使用原始数据类型int[][]不太方便实现追加操作
//        // ArrayList的初始化只会设置容量，不设置长度，真的是SB
//        ArrayList[] buckets = new ArrayList[bucketCnt];
//        Arrays.fill(buckets, new ArrayList<Integer>());
//        System.out.println(buckets.length);
//        // 利用映射关系，将数据分配到各个桶中
//        for (int num : nums) {
//            // 这个映射关系中其实是将数据做了一个粗排序，桶与桶之间是有大小关系的
//            ArrayList b = buckets[Math.floorDiv(num - _min, bucketSize)];
//            b.add(num);
//        }
//        // 对每个桶进行内部排序，再拼接回去
//        // 编号0的桶的数据最小，其中数据排好序之后，就可以直接从nums的头部开始放置数据
//        int sortedIdx = 0;
//        for (int i = 0; i < buckets.length; ++i) {
//            // 桶内部排序，由于元素数量少，建议使用【插入排序】。 这里则直接调用语言本身的sort API (优化快排，其实数据量少时也是插入排序)
//            Collections.sort(buckets[i]);   // ArrayList本身的sort方法暂时不知怎么使用，是JDK11之后支持的
//            // 将桶内排好序的数据放回nums
//            for (int j = 0; j < buckets[i].size(); ++j) {
//                nums[sortedIdx] = (int) buckets[i].get(j);
//                sortedIdx++;
//            }
//        }
//    }
//
//    public static void main(String[] ars) {
//        int[] nums = {2,3,3,4,1,1,5};
//        RadixSort.sort(nums);
//        System.out.println(Arrays.toString(nums));
//    }
}
