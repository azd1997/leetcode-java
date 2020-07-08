// 任务调度器
package lt621;

import java.util.Arrays;

// 思路：
// 1. 搜索遍历 当然可以穷举所有顺序组合，然后比较出最短时间
// 2. 排序
    // 将所有任务存到map['A'~'Z']int中，记录各任务类型的数量
    // 排序，优先处理数量最多的任务(这样可以保证最少的空闲时间)
// 3. 优先队列。 （原理与排序类似）
public class Solution {
    // 排序。 预计算，贪心
    public int leastInterval(char[] tasks, int n) {
        // 特殊情况(任务数<=1或者没有空闲时间)
        if (tasks.length <= 1 || n < 1) return tasks.length;

        // 统计任务数量
        int[] map = new int[26];
        for (int i = 0; i < tasks.length; i++) {
            map[tasks[i] - 'A']++;
        }
        // map排序
        Arrays.sort(map);
        int maxCount = map[25]; // 最后这种任务的数量最多
        int retCount = (maxCount - 1) * (n + 1) + 1;    // 总共所需时间至少为这些（仅考虑A->X->X->A->X->X->A这样的形式，X为其他类型任务）
        // 从倒数第二的任务开始向前检查，如果任务的数量和maxCount一样
        // 那么retCount++
        // 这是因为此时任务调度的顺序就变成了A->B->X->A->B->X->A->B这样的形式，可以看出，只需要关心最大数量的任务有多少个
        int i = 24;
        while (i >= 0 && map[i] == maxCount) {
            retCount++;
            i--;
        }
        // 如果还剩有数量较少的任务，将他们随便穿插在最大任务数的任务中间，不影响retCount
        return Math.max(retCount, tasks.length);
    }

    // 排序。 模拟分配，贪心
    public int leastInterval2(char[] tasks, int n) {
        // 特殊情况(任务数<=1或者没有空闲时间)
        if (tasks.length <= 1 || n < 1) return tasks.length;

        // 统计任务数量
        int[] map = new int[26];
        for (int i = 0; i < tasks.length; i++) {
            map[tasks[i] - 'A']++;
        }
        // map排序
        Arrays.sort(map);
        int retCount = 0;   // 总时间，也就是返回的数字
        while (map[25] > 0) {
            int i = 0;
            while (i <= n) {
                if (map[25] == 0) {
                    break;
                }
                if (i < 26 && map[25 - i] > 0) {
                    map[25 - i]--;
                }
                retCount++;
                i++;
            }
            Arrays.sort(map);   // 重新排序
        }
        return retCount;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        Object[] taskses = new Object[]{
                new char[]{'A','A','A','B','B','B'},
        };
        int[] ns = {2};
        int[] res = {8};
        if (ns.length != res.length || ns.length != taskses.length) System.out.println("test error!");
        int succCnt = 0;
        for (int i = 0; i < ns.length; i++) {
            char[] tasks = (char[])taskses[i];
            int r = sol.leastInterval2(tasks, ns[i]);
            if (r != res[i]) {
                System.out.printf("test failed. tasks=%s, n=%d, r should be %d, but got %d", Arrays.toString(tasks), ns[i], res[i], r);
            }
            succCnt++;
        }
        if (succCnt == ns.length) System.out.println("test passed!");
    }
}
