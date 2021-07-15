package com.zdq.middle;

import com.zdq.entity.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 106. 从中序与后序遍历序列构造二叉树
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *
 * 中序遍历 inorder = [9,3,15,20,7]
 * 后序遍历 postorder = [9,15,7,20,3]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * @author zhaodeqiang
 */
public class BuildTree106 {
    public static void main(String[] args) {
        int[] inOrder = {1, 2, 3};
        int[] postOrder = {2, 1, 3};
        TreeNode treeNode = buildTree(inOrder, postOrder);
        System.out.println(treeNode);
    }

    private static TreeNode buildTree(int[] inOrder, int[] postOrder) {
        Map<Integer, Integer> inOrderMap = new HashMap<>(16);
        for(int i = 0; i < inOrder.length; i ++){
            inOrderMap.put(inOrder[i], i);
        }
        int[] postOrderIndex = {postOrder.length - 1};
        return recursive(postOrder, 0, inOrder.length - 1, postOrderIndex, inOrderMap);
    }

    private static TreeNode recursive(int[] postOrder, int left, int right, int[] postOrderIndex,                                                           Map<Integer, Integer> inOrderMap) {
        if(left > right){
            return null;
        }
        TreeNode node = new TreeNode(postOrder[postOrderIndex[0]]);
        postOrderIndex[0]--;
        int mid = inOrderMap.get(node.val);
        node.right = recursive(postOrder, mid + 1, right, postOrderIndex, inOrderMap);
        node.left = recursive(postOrder, left, mid - 1, postOrderIndex, inOrderMap);
        return node;
    }
}
