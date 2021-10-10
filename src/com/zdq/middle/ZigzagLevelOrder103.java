package com.zdq.middle;

import com.zdq.entity.TreeNode;

import java.util.*;

/**
 * 103. 二叉树的锯齿形层序遍历
 * 给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回锯齿形层序遍历如下：
 *
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 */
public class ZigzagLevelOrder103 {

    public static void main(String[] args) {
        // [1,2,3,4,null,null,5]
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        List<List<Integer>> lists = zigzagLevelOrder(root);
        for (List<Integer> list : lists) {
            for (Integer integer : list) {
                System.out.print(integer + "\t");
            }
            System.out.println();
        }
    }

    private static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> resList = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        //起始 奇数层
        boolean isEven = false;
        if (root != null) {
            queue.add(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                LinkedList<Integer> tmpList = new LinkedList<>();
                for (int i = 0; i < size; i++) {
                    TreeNode currNode = queue.poll();
                    if (currNode != null) {
                        int val = currNode.val;
                        if (isEven) {
                            //偶数层 头插法
                            tmpList.addFirst(val);
                        } else {
                            //奇数层 尾插法
                            tmpList.addLast(val);
                        }

                        if (currNode.left != null) {
                            queue.add(currNode.left);
                        }
                        if (currNode.right != null) {
                            queue.add(currNode.right);
                        }
                    }
                }
                if (tmpList.size() > 0) {
                    resList.add(new LinkedList<>(tmpList));
                }
                isEven = !isEven;
            }
        }
        return resList;
    }
}
