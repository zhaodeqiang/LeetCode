package com.zdq.middle;

import com.zdq.entity.TreeNode;

import java.util.*;

/**
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * <p>
 * 假设一个二叉搜索树具有如下特征：
 * <p>
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 示例 1:
 * <p>
 * 输入:
 * 2
 * / \
 * 1   3
 * 输出: true
 * 示例 2:
 * <p>
 * 输入:
 * 5
 * / \
 * 1   4
 *      / \
 *     3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *      根节点的值为 5 ，但是其右子节点值为 4 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/validate-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author ZDQ
 */
public class IsValidBst98 {
    public static void main(String[] args) {

//        TreeNode treeNode = new TreeNode(Integer.MAX_VALUE);
//        treeNode.left = new TreeNode(1);
//        System.out.println(isValidBst(treeNode));
    }

    public static boolean isValidBst(TreeNode root) {
        return isValidBst(root, null, null);
    }

    private static boolean isValidBst(TreeNode root, Integer minValue, Integer maxValue) {
        if (root == null) {
            return true;
        }
        if (minValue != null && root.val <= minValue || maxValue != null && root.val >= maxValue) {
            return false;
        }
        return isValidBst(root.left, minValue, root.val) &&
                isValidBst(root.right, root.val, maxValue);
    }

    /**
     * 中序遍历方法： 左 -> 根 -> 右
     *
     * @param root 根节点
     * @return true when the tree is a BST and false when the tree is not a BST
     */
    public boolean isValidBst1(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        double inorder = -Double.MAX_VALUE;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            // 如果中序遍历得到的节点的值小于等于前一个 inorder，说明不是二叉搜索树
            if (root.val <= inorder) {
                return false;
            }
            inorder = root.val;
            root = root.right;
        }
        return true;
    }
}
