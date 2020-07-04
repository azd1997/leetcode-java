package lt78;

import java.util.ArrayList;
import java.util.List;

public class Solution2 {
    private List<List<Integer>> result = new ArrayList<>();
    private int n,  k;

    public List<List<Integer>> subsets(int[] nums) {
        n = nums.length;
        // k为子集的长度
        for (k = 0; k <= n; ++k) {
            backtrack(nums, new ArrayList<>(), 0);
        }
        return result;
    }

    private void backtrack(int[] nums, ArrayList<Integer> path, int start) {
        if (path.size() == k) {
            result.add(new ArrayList<>(path));
        }

        for (int i = start; i < n; ++i) {
            // 做选择
            path.add(nums[i]);
            // 回溯
            backtrack(nums, path, start+1);
            // 撤销选择
            path.remove(path.size()-1);     // 删除末尾元素
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        Solution2 sol = new Solution2();
        List<List<Integer>> res = sol.subsets(nums);
        System.out.println(res);
    }
}