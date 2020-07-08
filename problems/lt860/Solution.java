// 柠檬水找零
package lt860;

import java.util.Arrays;
import java.util.HashMap;

// 思路：
// 1. 很明显的贪心，
//  找零时则优先使用大面值的
public class Solution {
    public boolean lemonadeChange(int[] bills) {
        // 用一个哈希表记录手上的零钱数（这里用数组来代替这个哈希表）
        int[] mycoins = {0,0,0};    // 5,10,20的数量都是0

        // bills只会有5,10,20三种，找零数分别是0,5,15(10+5或者3*5)
        // 遍历bills
        for (int b : bills) {
            switch (b) {
                case 5:
                    mycoins[0]++;
                    continue;
                case 10:
                    // 如果能找零
                    if (mycoins[0] == 0) return false;
                    mycoins[0]--;
                    mycoins[1]++;
                    continue;
                case 20:
                    if (mycoins[0] > 0 && mycoins[1] > 0) {
                        mycoins[0]--;
                        mycoins[1]--;
                        mycoins[2]++;
                    } else if (mycoins[0] >= 3) {
                        mycoins[0] -= 3;
                        mycoins[2]++;
                    } else {
                        return false;
                    }
                    continue;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        Solution sol = new Solution();

//        int[] bills = {5,5,10,10,20};
//        boolean ret = sol.lemonadeChange(bills);
//        System.out.println(ret);

        int[] bills1 = {5,5,5,10,20};
        boolean ret1 = sol.lemonadeChange(bills1);
        System.out.println(ret1);
    }
}
