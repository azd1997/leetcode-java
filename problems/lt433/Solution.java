// 最小基因变化
package lt433;

import java.util.*;

// 思路：
// 1. DFS回溯
    // 1. 着重于start与end的区别，每次讲start修改一个字符，以使其更接近end。
    // 同时检查变化后start是否在bank中，不在，则说明该变化行不通，回溯，换一种变化
    // 注意：这个思路是错的，因为路途中的基因节点不一定只和end差一个字母
    // 2. 着重于start下一步是否能走到bank中其中一种，遍历bank，
    // 检查start与bank[i]是否只差一个字母，是则选择其，继续下一步
    // 此外，遍历基因库看是否
// 2. BFS
    // 2.1 单向BFS。 从start出发，看能否走到end
    // 2.2 双向BFS. 从start和end同时出发，看能否相遇
public class Solution {

    // 1.1 DFS回溯（错误）
    int count = Integer.MAX_VALUE;  // 最小基因变化数，全局变量
    public int minMutation(String start, String end, String[] bank) {
        // 将bank转为哈希集合存储，便于快速查询
        HashSet<String> bankset = new HashSet<>();
        Collections.addAll(bankset, bank);

        // 遍历基因序列的8个字母，如果有不同的，则修改(做选择)
        char[] startC = start.toCharArray(), endC = end.toCharArray();
        backtrack(startC, endC, bankset, 0);
        // count有更新说明start能走到end
        return count == Integer.MAX_VALUE ? -1 : count;
    }

    // 返回回溯深度，也就是递归深度
    private void backtrack(char[] startC, char[] endC, HashSet<String> bankset, int step) {
        // 到达end，则将当前步数与count比较，尝试更新count
        if (Arrays.equals(startC, endC)) {
            count = count > step ? step : count;
        }

        System.out.println("startC=" + new String(startC) + " step=" + step);

        // 没到达则继续尝试变化
        for (int i = 0; i < 8; ++i) {
            if (startC[i] != endC[i]) {
                // 做选择
                char backup = startC[i];
                startC[i] = endC[i];
                // 如果此次选择是“可行的”, 进入下一层
                if (bankset.contains(new String(startC))) {
                    backtrack(startC, endC, bankset, ++step);   // 注意这里不能写step++。 因为step++是先将step赋给左值，再加1
                }
                // 回溯，撤销选择
                startC[i] = backup;
            }
        }
    }

    // 1.2 DFS回溯
    public int minMutation2(String start, String end, String[] bank) {
        // DFS
        dfs(start, end, bank, 0, new HashSet<String>());
        // count有更新说明start能走到end
        return count == Integer.MAX_VALUE ? -1 : count;
    }

    // 返回回溯深度，也就是递归深度
    private void dfs(String cur, String end, String[] bank, int step, HashSet<String> walked) {
        // 到达end，则将当前步数与count比较，尝试更新count
        if (cur.equals(end)) {  // 不要用cur==end来比较字符串内容
            count = Math.min(count, step);
            return;
        }

        System.out.println("cur=" + new String(cur) + " step=" + step);

        // 没到达则尝试看下一步能否走到基因库之一
        for (String str : bank) {
            // 统计编辑距离
            int diff = 0;   // 不同字母数（编辑距离）
            for (int i = 0; i < 8; ++i) {
                if (cur.charAt(i) != str.charAt(i)) {
                    if (++diff > 1) break;  // 如果编辑距离超过1，则不用继续了，下一步走不到
                }
            }
            // 编辑距离恰好为1, 并且下一步的那个基因没有走过，则向下继续
            if (diff == 1 && !walked.contains(str)) {
                // 做选择（下一步往str走）
                walked.add(str);
                dfs(str, end, bank, step + 1, walked);  // 这里不能用++step，这是因为step本身的值接下来还要使用，不能修改；step++也会出错
                // 撤销选择(回来，不走str，去走别的)
                walked.remove(str);
            }
        }
    }

    // 2.1 单向BFS
    public int minMutation3(String start, String end, String[] bank) {
        // bank -> bankset
        HashSet<String> bankset = new HashSet<>();
        Collections.addAll(bankset, bank);
        // 碱基库
        char[] banks = {'A', 'C', 'G', 'T'};
        // 队列
        Deque<String> deque = new LinkedList<>();
        deque.offer(start);
        // 最小基因变化数cnt
        int cnt = 0;
        // 已访问过哈希集合
        HashSet<String> visited = new HashSet<>();

        // BFS
        while (!deque.isEmpty()) {
            int size = deque.size();
            while (size-- > 0) {    // 等价于 while (size > 1) {...  size--} 其实就是把队列的所有元素都遍历
                String cur = deque.poll();  // 弹出队首
                if (cur.equals(end)) return cnt;    // 本层第一次发现终点end，说明cnt就是最小变化数，直接返回

                // 处理cur, 看它下一步还能走吗
                char[] curC = cur.toCharArray();
                for (int i = 0; i < curC.length; i++) {
                    char backup = curC[i];
                    // 做选择
                    for (char b : banks) {
                        curC[i] = b;
                        String next = new String(curC);
                        // 如果下一步没走过，并且下一步在基因库中， 才将其加入队列
                        if (!visited.contains(next) && bankset.contains(next)) {
                            visited.add(next);
                            deque.add(next);
                        }
                    }
                    // 撤销选择
                    curC[i] = backup;
                }
            }
            cnt++;  // 本层检查完毕，没发现end，准备进入下一层
        }
        return -1;  // 没找到end
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String start = "AACCGGTT";
        String end = "AAACGGTA";
        String[] bank = {"AACCGATT","AACCGATA","AAACGATA","AAACGGTA"};
        int ret = sol.minMutation3(start, end, bank);
        System.out.println(ret);

        String start1 = "AACCGGTT";
        String end1 = "AAACGGTA";
        String[] bank1 = {"AACCGGTA", "AACCGCTA", "AAACGGTA"};
        int ret1 = sol.minMutation3(start1, end1, bank1);
        System.out.println(ret1);
    }
}
