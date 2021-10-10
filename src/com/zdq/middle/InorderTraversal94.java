package com.zdq.middle;

import com.zdq.entity.TreeNode;

import java.util.Deque;
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
        // List中的元素如果是基本类型或者string类型，会调用AbstractCollection中的toString()直接打印输出
        System.out.println("Morris中序遍历(左->根->右)：" + morrisInOrder(root));
        System.out.println("Morris前序遍历(根->左->右)：" + morrisPre(root));
        System.out.println("Morris后序遍历(左->右->根)：" + morrisPost(root));
        System.out.println("层序遍历：" + levelOrder(root));
    }

    private static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root != null) {
            inOrder(root, res);
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
     * 递归前序遍历二叉树
     *
     * @param root 根节点
     * @param res  节点值的列表
     */
    private static void preOrder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        preOrder(root.left, res);
        preOrder(root.right, res);
    }

    /**
     * 递归后序遍历二叉树
     *
     * @param root 根节点
     * @param res  节点值的列表
     */
    private static void postOrder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        postOrder(root.left, res);
        postOrder(root.right, res);
        res.add(root.val);
    }


    /**
     * Morris中序遍历二叉树，利用线索二叉树思想，空间复杂度O(1)
     *
     * @param root 根节点
     */
    private static List<Integer> morrisInOrder(TreeNode root) {
        List<Integer> resultList = new LinkedList<>();
        if (root == null) {
            return resultList;
        }
        TreeNode cur = root;
        TreeNode currLeft;
        while (cur != null) {
            currLeft = cur.left;
            if (currLeft != null) {
                //寻找左子树中最右孩子节点
                while (currLeft.right != null && currLeft.right != cur) {
                    currLeft = currLeft.right;
                }
                if (currLeft.right == null) {
                    currLeft.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    currLeft.right = null;
                }
            }
            resultList.add(cur.val);
            cur = cur.right;
        }
        return resultList;
    }


    /**
     * Morris先序遍历
     *
     * @param root 根结点
     */
    public static List<Integer> morrisPre(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        TreeNode cur = root;
        TreeNode currLeft;
        while (cur != null) {
            currLeft = cur.left;
            if (currLeft != null) {
                while (currLeft.right != null && currLeft.right != cur) {
                    currLeft = currLeft.right;
                }
                if (currLeft.right == null) {
                    currLeft.right = cur;
                    res.add(cur.val);
                    cur = cur.left;
                    continue;
                } else {
                    currLeft.right = null;
                }
            } else {
                //一个节点如果没有左子树，此时两次遍历非叶节点的时候重合，此时还是在第一次的时候就打印。
                res.add(cur.val);
            }
            cur = cur.right;
        }
        return res;
    }


    /**
     * Morris后续遍历二叉树
     *
     * @param root 根结点
     */
    public static List<Integer> morrisPost(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        TreeNode curr = root;
        TreeNode currLeft;
        while (curr != null) {
            currLeft = curr.left;
            if (currLeft != null) {
                while (currLeft.right != null && currLeft.right != curr) {
                    currLeft = currLeft.right;
                }
                if (currLeft.right == null) {
                    currLeft.right = curr;
                    curr = curr.left;
                    continue;
                } else {
                    currLeft.right = null;
                    //整个节点整棵树右节点逆序打印
                    printEdge(curr.left, res);
                }
            }
            curr = curr.right;
        }
        //单独逆序打印有边界，用逆转链表的形式更改，然后最后调整回来
        printEdge(root, res);
        return res;
    }

    public static void printEdge(TreeNode root, List<Integer> res) {
        TreeNode tail = reverseEdge(root);
        TreeNode cur = tail;
        while (cur != null) {
            res.add(cur.val);
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
     * 二叉树层序遍历 队列
     *
     * @param root 根节点
     * @return 节点值列表
     */
    private static List<Integer> levelOrder(TreeNode root) {
        List<Integer> list = new LinkedList<>();
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


    /**
     * 二叉树的前序遍历 迭代法 栈
     *
     * @param root 根节点
     * @return 根 左 右顺序的节点值
     */
    private static List<Integer> preOrderStack(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                res.add(node.val);
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            node = node.right;
        }
        return res;
    }

    /**
     * 二叉树的中序遍历 迭代法 栈
     *
     * @param root 根节点
     * @return 左 中 右 顺序的节点值
     */
    private static List<Integer> inOrderStack(TreeNode root) {
        List<Integer> resultList = new LinkedList<>();
        if (root == null) {
            return resultList;
        }

        Deque<TreeNode> stack = new LinkedList<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            //  此时root=null，取出最左边的节点
            root = stack.pop();
            resultList.add(root.val);
            //中间节点遍历完成，需要遍历右边节点
            root = root.right;
        }
        return resultList;
    }

    /**
     * 二叉树的后序遍历 迭代法 栈
     *
     * @param root 根节点
     * @return 左 右 根 顺序的节点值
     */
    private List<Integer> postOrderStack(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }

        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode prev = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.right == null || root.right == prev) {
                res.add(root.val);
                prev = root;
                root = null;
            } else {
                stack.push(root);
                root = root.right;
            }
        }
        return res;
    }
}
