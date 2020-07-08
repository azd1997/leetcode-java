package templates;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Stack;

public class search {

    public class Node {
        public Node[] children() {
            Node[] childs = {};
            return childs;
        }
    }


    //HashSet<Node> visited = new HashSet<>();
    public void dfs(Node node, HashSet<Node> visited) {
        // 将节点添加为已访问
        visited.add(node);

        // 处理当前节点
        // do something

        // 遍历当前节点的孩节点
        for (Node next : node.children()) {
            if (!visited.contains(next)) {
                dfs(next, visited);
            }
        }
    }

    public void dfs2(Node node, HashSet<Node> visited) {
        // 如果当前节点已访问过，直接退出
        if (visited.contains(node)) {
            return;
        }
        // 将节点添加为已访问
        visited.add(node);

        // 处理当前节点
        // do something

        // 遍历当前节点的孩节点
        for (Node next : node.children()) {
            if (!visited.contains(next)) {
                dfs2(next, visited);
            }
        }
    }

    public void DFS(Node root) {
        // 如果起始节点(例如树的根节点)为空，则直接退出
        if (root == null) return;

        // 辅助栈/哈希集合
        Stack<Node> stack = new Stack<>();
        HashSet<Node> visited = new HashSet<>();
        // 将起始节点压入栈中
        stack.push(root);

        // 栈不为空则将栈顶节点弹出，并处理其子节点
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            visited.add(cur);

            // do something to cur Node

            // generate the next Nodes. for example, cur.children
            Node[] nexts = cur.children();

            // push them back stack;
            for (Node next : nexts) {
                stack.push(next);
            }

        }

        // 其他的处理工作


        return;
    }

    // BFS模板
    public void BFS(Node root) {
        // 如果起始节点(例如树的根节点)为空，则直接退出
        if (root == null) return;

        // 辅助队列/哈希集合
        Queue<Node> queue = new ArrayDeque<>();
        HashSet<Node> visited = new HashSet<>();
        // 将起始节点压入队中
        queue.add(root);

        // 队不为空则将队首节点弹出，并处理其子节点
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            visited.add(cur);

            // do something to cur Node

            // generate the next Nodes. for example, cur.children
            Node[] nexts = cur.children();

            // add them into queue;
            for (Node next : nexts) {
                queue.add(next);
            }
        }

        // 其他的处理工作

        return;
    }
}
