package com.zdq.hard;

import com.zdq.entity.TreeNode;

/**
 * 二叉搜索树中的两个节点被错误地交换。
 * <p>
 * 请在不改变其结构的情况下，恢复这棵树。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,3,null,null,2]
 * <p>
 *    1
 *   /
 *  3
 *   \
 *    2
 * <p>
 * 输出: [3,1,null,null,2]
 * <p>
 *    3
 *   /
 *  1
 *   \
 *    2
 * 示例 2:
 * <p>
 * 输入: [3,1,4,null,null,2]
 * <p>
 * 3
 * / \
 * 1   4
 *    /
 *   2
 * <p>
 * 输出: [2,1,4,null,null,3]
 * <p>
 * 2
 * / \
 * 1   4
 *    /
 *  3
 * 进阶:
 * <p>
 * 使用 O(n) 空间复杂度的解法很容易实现。
 * 你能想出一个只使用常数空间的解决方案吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/recover-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author ZDQ
 */
public class RecoverTree99 {
    private TreeNode node1;
    private TreeNode node2;
    private TreeNode pre;

    public static void main(String[] args) {

    }

    public void recoverTree(TreeNode root) {
        inOrder(root);
        node1.val = node1.val ^ node2.val;
        node2.val = node1.val ^ node2.val;
        node1.val = node1.val ^ node2.val;
    }

    /**
     * 中序遍历解法
     *
     * @param root 根节点
     */
    private void inOrder(TreeNode root) {
        if (root != null) {
            inOrder(root.left);
            if (pre != null && root.val <= pre.val) {
                if (node1 == null) {
                    node1 = pre;
                }
                node2 = root;
            }
            pre = root;
            inOrder(root.right);
        }
    }

    public void swap(TreeNode a, TreeNode b) {
        int tmp = a.val;
        a.val = b.val;
        b.val = tmp;
    }

    /**
     * Morris中序遍历解法
     * 时间复杂度：O(N)，我们访问每个节点两次。
     * 空间复杂度：O(1)。
     *
     * @param root 根节点
     */
    public void recoverTree1(TreeNode root) {
        // predecessor is a Morris predecessor.
        // In the 'loop' cases it could be equal to the node itself predecessor == root.
        // pred is a 'true' predecessor,
        // the previous node in the inorder traversal.
        TreeNode x = null, y = null, pred = null, predecessor;
        while (root != null) {
            // If there is a left child
            // then compute the predecessor.
            // If there is no link predecessor.right = root --> set it.
            // If there is a link predecessor.right = root --> break it.
            if (root.left != null) {
                // Predecessor node is one step left
                // and then right till you can.
                predecessor = root.left;
                while (predecessor.right != null && predecessor.right != root) {
                    predecessor = predecessor.right;
                }
                // set link predecessor.right = root
                // and go to explore left subtree
                if (predecessor.right == null) {
                    predecessor.right = root;
                    root = root.left;
                }
                // break link predecessor.right = root
                // link is broken : time to change subtree and go right
                else {
                    // check for the swapped nodes
                    if (pred != null && root.val < pred.val) {
                        y = root;
                        if (x == null) {
                            x = pred;
                        }
                    }
                    pred = root;

                    predecessor.right = null;
                    root = root.right;
                }
            }
            // If there is no left child
            // then just go right.
            else {
                // check for the swapped nodes
                if (pred != null && root.val < pred.val) {
                    y = root;
                    if (x == null) {
                        x = pred;
                    }
                }
                pred = root;
                root = root.right;
            }
        }
        swap(x, y);
    }
}

