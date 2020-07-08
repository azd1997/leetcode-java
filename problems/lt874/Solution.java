package lt874;

import java.util.HashSet;

// 思路：
// 1. 没什么好说的，就是模拟行走，更新最大值
public class Solution {

    /*
    1.List的contains(obj)方法
　　实际上，List调用contains(Object obj)方法时，会遍历List中的每一个元素，
    然后再调用每个元素的equals()方法去跟contains()方法中的参数进行比较，如果有一个元素的equals()方法
    返回true则contains()方法返回true，否则所有equals()方法都不返回true，则ontains()方法则返回false。
    因此，重写了Course类的equals()方法，否则，testListContains()方法的第二条输出为false。

    2.Set的Contains(obj)方法
　　当调用HashSet的contains(Object obj)方法时，其实是先调用每个元素的hashCode()方法来返回哈希码，
    如果哈希码的值相等的情况下再调用equals(obj)方法去判断是否相等，只有在这两个方法所返回的值都相等的情况下，
    才判定这个HashSet包含某个元素。因此，需重写Course类的hashCode()方法和equals()方法。

    3. Map中是否包含指定的Key和Value
    在Map中，用containsKey()方法，判断是否包含某个Key值；用containsValue()方法，判断是否包含某个Value值。
    注：跟List中的Contains()方法一样，Map中的ContainsValue()方法也需要调用某个Value值的equals()方法，
    去和参数对象进行比较，如果匹配成功，返回结果为true，说明在Map中的Value值确实包含参数对象。因此，
    需要重写Student类的equals()方法。
    */

    private class Point extends Object {
        int x;
        int y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        // 重载equals和hashCode比较难
        // 一种比较好的做法是转为字符串来记录到集合
        public String toString() {
            return this.x + "," + this.y;
        }
    }

    // 方向表: 上(北) 左(东) 下(南) 右(西)
    int[] dx = {0,-1,0,1};
    int[] dy = {1,0,-1,0};
    static final int NORTH = 0;
    static final int SOUTH = 2;
    static final int EAST = 1;
    static final int WEST = 3;

    public int robotSim(int[] commands, int[][] obstacles) {
        // 障碍物表
        HashSet<String> obs = new HashSet<>();
        for (int[] p : obstacles) {
            obs.add(new Point(p[0], p[1]).toString());
        }
        // 模拟行走
        int max = 0;
        Point start = new Point(0, 0);
        Point cur = start;
        int dir = NORTH;
        for (int c : commands) {
            System.out.printf("dir=%d, point=(%d, %d)\n", dir, cur.x, cur.y);

            // 如果是转向命令，则调整方向
            if (c < 0) {
                dir = turn(dir, c);
                continue;
            }
            // 如果是行进命令，则计算下一个点，并更新max
            if (c >= 1 && c <= 9) {
                // 检查中间是否有障碍物，并给出下一步到达位置
                Point next = checkObs(cur, dir, c, obs);
                // 更新max
                max = Math.max(max, odist(start, next));
                // 行进
                cur = next;
            }
        }
        return max;
    }

    // 计算两点的欧氏距离平方和
    private int odist(Point p1, Point p2) {
        return (int)(Math.pow(p1.x - p2.x, 2) +  Math.pow(p1.y - p2.y, 2));
    }

    // 根据当前的方向，以及收到的转换方向的指令，判断转到哪个方向
    private int turn(int curDir, int cmd) {
        // 左转90度
        if (cmd == -2) return (curDir + 1) % 4;
        // 右转90度
        if (cmd == -1) return (curDir + 4 - 1) % 4;
        // 错误指令
        return -1;
    }

    // 检查两点之间是否有障碍物，如果有，返回障碍物前一点，如果没有，返回end
    private Point checkObs(Point cur, int dir, int step, HashSet<String> obs) {
        Point next = new Point(cur.x, cur.y);   // 从cur出发
        // 检查是否有障碍物，没有就可以前进
        if (dir == NORTH || dir == SOUTH) {
            for (int i = 1; i <= step; i++) {
                next.y += dy[dir];
                if (obs.contains(next.toString())) {
                    next.y -= dy[dir];
                    break;
                }
            }
        } else {
            for (int i = 1; i <= step; i++) {
                next.x += dx[dir];
                if (obs.contains(next.toString())) {
                    next.x -= dx[dir];
                    break;
                }
            }
        }
        return next;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] commands = {4,-1,4,-2,4};
        int[][] obstacles = {{2,4}};
        int ret = sol.robotSim(commands, obstacles);
        System.out.println(ret);

        int[] commands1 = {-2,8,3,7,-1};
        int[][] obstacles1 = {{-4,-1},{1,-1},{1,4},{5,0},{4,5},{-2,-1},{2,-5},{5,1},{-3,-1},{5,-3}};
        int ret1 = sol.robotSim(commands1, obstacles1);
        System.out.println(ret1);
    }
}
