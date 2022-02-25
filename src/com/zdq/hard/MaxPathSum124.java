package com.zdq.hard;

import com.zdq.entity.TreeNode;

/**
 * 124. 二叉树中的最大路径和
 * 路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
 * <p>
 * 路径和 是路径中各节点值的总和。
 * <p>
 * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
 * <p>
 * 输入：root = [-10,9,20,null,null,15,7]
 * 输出：42
 * 解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-maximum-path-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxPathSum124 {

    public static void main(String[] args) {

    }

    int maxSum = Integer.MIN_VALUE;
    private int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        //递归计算
        countMaxSum(root);
        return maxSum;
    }

    private int countMaxSum(TreeNode root) {
        // 在以该节点为根节点的子树中寻找以该节点为起点的一条路径，使得该路径上的节点值之和最大

        // 对于二叉树中的一个节点，该节点的最大路径和取决于该节点的值与该节点的左右子节点的最大贡献值，
        // 如果子节点的最大贡献值为正，则计入该节点的最大路径和，否则不计入该节点的最大路径和。
        if (root == null) {
            return 0;
        }
        // 递归计算左右子节点的最大贡献值
        // 只有在最大贡献值大于 0 时，才会选取对应子节点
        int leftMax = Math.max(countMaxSum(root.left), 0);
        int rightMax = Math.max(countMaxSum(root.right), 0);

        // 节点的最大路径和取决于该节点的值与该节点的左右子节点的最大贡献值
        int tmpMax = leftMax + rightMax + root.val;

        maxSum = Math.max(maxSum, tmpMax);

        // 返回节点的最大贡献值
        return root.val + Math.max(leftMax, rightMax);
    }
}
