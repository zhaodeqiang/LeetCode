package com.zdq.middle;

import com.zdq.entity.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 94. 二叉树的中序遍历
 * 给定一个二叉树，返回它的中序 遍历。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,null,2,3]
 * 1
 * \
 * 2
 * /
 * 3
 * <p>
 * 输出: [1,3,2]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-inorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author ZDQ
 */
public class InorderTraversal94 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        System.out.println("中序遍历(左->根->右)：");
        List<Integer> list = inorderTraversal(root);
        System.out.println(list);
        System.out.println("前序遍历(根->左->右)：");
        morrisPre(root);
        System.out.println("后序遍历(左->右->根)：");
        morrisPost(root);
        System.out.println("层序遍历：");
        System.out.println(printFromTopToBottom(root));
    }

    private static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root != null) {
//            inOrder(root, res);
            morrisInOrder(root, res);
        }
        return res;
    }

    /**
     * 递归中序遍历二叉树
     *
     * @param root 根节点
     * @param list 节点值的列表
     */
    private static void inOrder(TreeNode root, List<Integer> list) {
        if (root != null) {
            inOrder(root.left, list);
            list.add(root.val);
            inOrder(root.right, list);
        }
    }

    /**
     * Morris中序遍历二叉树，利用线索二叉树思想，空间复杂度O(1)
     *
     * @param root 根节点
     */
    private static void morrisInOrder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        TreeNode cur = root;
        TreeNode per;
        while (cur != null) {
            per = cur.left;
            if (per != null) {
                //寻找左子树中最右孩子节点
                while (per.right != null && per.right != cur) {
                    per = per.right;
                }
                if (per.right == null) {
                    per.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    per.right = null;
                }
            }
            list.add(cur.val);
            System.out.print(cur.val + "\t");
            cur = cur.right;
        }
        System.out.println();
    }


    /**
     * Morris先序遍历
     *
     * @param head 根结点
     */
    public static void morrisPre(TreeNode head) {
        if (head == null) {
            return;
        }
        TreeNode cur = head;
        TreeNode mostRight;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    System.out.print(cur.val + "\t");
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            } else {
                //一个节点如果没有左子树，此时两次遍历非叶节点的时候重合，此时还是在第一次的时候就打印。
                System.out.print(cur.val + "\t");
            }
            cur = cur.right;
        }
        System.out.println();
    }


    /**
     * Morris后续遍历二叉树
     *
     * @param head 根结点
     */
    public static void morrisPost(TreeNode head) {
        if (head == null) {
            return;
        }
        TreeNode cur1 = head;
        TreeNode cur2;
        while (cur1 != null) {
            cur2 = cur1.left;
            if (cur2 != null) {
                while (cur2.right != null && cur2.right != cur1) {
                    cur2 = cur2.right;
                }
                if (cur2.right == null) {
                    cur2.right = cur1;
                    cur1 = cur1.left;
                    continue;
                } else {
                    cur2.right = null;
                    //整个节点整棵树右节点逆序打印
                    printEdge(cur1.left);
                }
            }
            cur1 = cur1.right;
        }
        //单独逆序打印有边界，用逆转链表的形式更改，然后最后调整回来
        printEdge(head);
        System.out.println();
    }

    public static void printEdge(TreeNode head) {
        TreeNode tail = reverseEdge(head);
        TreeNode cur = tail;
        while (cur != null) {
            System.out.print(cur.val + "\t");
            cur = cur.right;
        }
        reverseEdge(tail);
    }

    public static TreeNode reverseEdge(TreeNode from) {
        TreeNode pre = null;
        TreeNode next;
        while (from != null) {
            next = from.right;
            from.right = pre;
            pre = from;
            from = next;
        }
        return pre;
    }

    /**
     * 二叉树层序遍历
     * 二叉树的先、中、后续遍历均可以使用栈进行遍历
     * @param root 根节点
     * @return 节点值列表
     */
    private static ArrayList<Integer> printFromTopToBottom(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            root = queue.poll();
            if (root.left != null) {
                queue.offer(root.left);
            }
            if (root.right != null) {
                queue.offer(root.right);
            }
            list.add(root.val);
        }
        return list;
    }
}
