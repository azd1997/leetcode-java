// 二叉树的层序遍历
package lt102;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// 思路：
// 1. BFS
//      1.1 双队列，交替存储当前层节点
//      1.2 单队列，使用虚拟节点作为层间分隔符
// 2. DFS 遍历当前节点时额外传递当前节点的深度，处理每个节点时将其值存到其所在深度的数列中
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        if (root == null) return lists;

        LinkedList<TreeNode> queue1 = new LinkedList<>();
        LinkedList<TreeNode> queue2 = new LinkedList<>();
        queue1.offer(root);
        while (!queue1.isEmpty()) {
            List<Integer> item = new ArrayList<>();
            while (!queue1.isEmpty()) {
                // 本层的节点(queue1)
                TreeNode node = queue1.remove();
                item.add(node.val);
                // 将当前层节点的孩子节点加入队列queue2
                if (node.left != null) {
                    queue2.offer(node.left);
                }
                if (node.right != null) {
                    queue2.offer(node.right);
                }
            }
            // 将本层节点的值都加入到结果中
            lists.add(item);
            LinkedList<TreeNode> tmp = queue1;
            queue1 = queue2;
            queue2 = tmp;
        }

        return lists;
    }
}
