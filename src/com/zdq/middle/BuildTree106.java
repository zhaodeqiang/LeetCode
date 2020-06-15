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

    }

    private static TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer, Integer> inOrderMap = new HashMap<>(16);
        for(int i = 0; i < inorder.length; i ++){
            inOrderMap.put(inorder[i], i);
        }
        int[] postOrderIndex = {postorder.length - 1};
        return recursive(postorder, 0, inorder.length - 1, postOrderIndex, inOrderMap);
    }

    private static TreeNode recursive(int[] postorder, int left, int right, int[] postOrderIndex,                                                           Map<Integer, Integer> inOrderMap) {
        if(left > right){
            return null;
        }
        TreeNode node = new TreeNode(postorder[postOrderIndex[0]]);
        postOrderIndex[0]--;
        int mid = inOrderMap.get(node.val);
        node.right = recursive(postorder, mid + 1, right, postOrderIndex, inOrderMap);
        node.left = recursive(postorder, left, mid - 1, postOrderIndex, inOrderMap);
        return node;
    }
}
