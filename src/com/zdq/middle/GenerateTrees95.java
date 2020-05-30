package com.zdq.middle;

import com.zdq.entity.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * 95. 不同的二叉搜索树 II
 * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的二叉搜索树。
 * <p>
 * 示例:
 * <p>
 * 输入: 3
 * 输出:
 * [
 *   [1,null,3,2],
 *   [3,2,null,1],
 *   [3,1,null,null,2],
 *   [2,1,3],
 *   [1,null,2,null,3]
 * ]
 * 解释:
 * 以上的输出对应以下 5 种不同结构的二叉搜索树：
 * <p>
 * 1         3     3      2      1
 * \       /     /      / \      \
 * 3     2     1      1   3      2
 * /     /       \                 \
 * 2     1         2                 3
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-binary-search-trees-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author ZDQ
 */
public class GenerateTrees95 {
    public static void main(String[] args) {
        int n = 3;
        List<TreeNode> treeNodes = generateTrees(n);
        for (TreeNode treeNode : treeNodes) {
            System.out.println(treeNode);
        }
    }

    /**
     * 官方解法
     * 空间复杂度和时间复杂度均为 O(nGn) = O(4^n/(n^1/2)),Gn为卡特兰数(Catalan).
     * 卡特兰数类似问题：1、一个栈(无穷大)的进栈序列为1，2，3，…，n，有多少个不同的出栈序列？
     *                2、给定n对括号，求括号正确配对的字符串数量。
     * @param start 起始值
     * @param end   终止值
     * @return 二叉搜索树列表
     */
    private static LinkedList<TreeNode> generateTrees(int start, int end) {
        LinkedList<TreeNode> allTrees = new LinkedList<>();
        if (start > end) {
            allTrees.add(null);
            return allTrees;
        }
        // pick up a root
        for (int i = start; i <= end; i++) {
            // all possible left subtrees if i is choosen to be a root
            LinkedList<TreeNode> leftTrees = generateTrees(start, i - 1);

            // all possible right subtrees if i is choosen to be a root
            LinkedList<TreeNode> rightTrees = generateTrees(i + 1, end);

            // connect left and right trees to the root i
            for (TreeNode l : leftTrees) {
                for (TreeNode r : rightTrees) {
                    TreeNode currentTree = new TreeNode(i);
                    currentTree.left = l;
                    currentTree.right = r;
                    allTrees.add(currentTree);
                }
            }
        }
        return allTrees;
    }

    private static List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new LinkedList<>();
        }
        return generateTrees(1, n);
    }
}
