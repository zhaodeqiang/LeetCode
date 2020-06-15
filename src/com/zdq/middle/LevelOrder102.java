package com.zdq.middle;

import com.zdq.entity.TreeNode;

import java.util.*;

/**
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 * <p>
 *  
 * <p>
 * 示例：
 * 二叉树：[3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回其层次遍历结果：
 * <p>
 * [
 * [3],
 * [9,20],
 * [15,7]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author ZDQ
 */
public class LevelOrder102 {

    public static void main(String[] args) {
        TreeNode node = new TreeNode(3);
        node.left = new TreeNode(9);
        node.right = new TreeNode(20);
        node.right.left = new TreeNode(15);
        node.right.right = new TreeNode(7);
        List<List<Integer>> lists = levelOrder(node);
        System.out.println(lists);
    }

    /**
     * BFS解法
     *
     * @param root 根节点
     * @return 层序遍历结果
     */
    private static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if (root != null) {
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                int count = queue.size();
                List<Integer> list = new LinkedList<>();
                for (int i = 0; i < count; i++) {
                    TreeNode poll = queue.poll();
                    if (poll != null) {
                        queue.offer(poll.left);
                        queue.offer(poll.right);
                        list.add(poll.val);
                    }
                }
                if (!list.isEmpty()) {
                    res.add(list);
                }
            }
        }
        return res;
    }

    /**
     * DFS 解法
     *
     * @param root 根节点
     * @return 层序遍历结果
     */
    private static List<List<Integer>> levelOrder1(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root != null) {
            dfs(res, root, 1);
        }
        return res;
    }

    private static void dfs(List<List<Integer>> res, TreeNode node, int depth) {
        if (res.size() < depth) {
            res.add(new ArrayList<>());
        }
        res.get(depth).add(node.val);
        if (node.left != null) {
            dfs(res, node.left, depth + 1);
        }
        if (node.right != null) {
            dfs(res, node.right, depth + 1);
        }
    }


}
