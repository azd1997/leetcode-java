package lt78;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    private List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        subsets(nums, new ArrayList<Integer>(), 0);
        return result;
    }

    private void subsets(int[] nums, ArrayList<Integer> path, int start) {
        result.add(new ArrayList<>(path));

        for (int i = start; i < nums.length; i++) {
            // 做选择
            path.add(nums[i]);
            // 回溯
            subsets(nums, path, start+1);
            // 撤销选择
            path.remove(path.size()-1);     // 删除末尾元素
        }
    }

     public static void main(String[] args) {
        int[] nums = {1,2,3};
        Solution sol = new Solution();
        List<List<Integer>> res = sol.subsets(nums);
        System.out.println(res);
     }
}
