package com.zdq.middle;

import com.zdq.entity.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 105. 从中序与前序遍历序列构造二叉树
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zhaodeqiang
 */
public class BuildTree105 {
    public static void main(String[] args) {

    }

    private static TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inOrderMap = new HashMap<>(16);
        for(int i = 0; i < inorder.length; i ++){
            inOrderMap.put(inorder[i], i);
        }
        return recursive(preorder, 0, inorder.length - 1, new int[1], inOrderMap);

    }

    private static TreeNode recursive(int[] preorder, int left, int right, int[] preOrderIndex,                                                           Map<Integer, Integer> inOrderMap) {
        if(left > right){
            return null;
        }
        TreeNode node = new TreeNode(preorder[preOrderIndex[0]]);
        preOrderIndex[0]++;
        int mid = inOrderMap.get(node.val);
        node.left = recursive(preorder, left, mid - 1, preOrderIndex, inOrderMap);
        node.right = recursive(preorder, mid + 1, right, preOrderIndex, inOrderMap);
        return node;
    }
}
