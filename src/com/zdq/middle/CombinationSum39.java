package com.zdq.middle;

import java.util.*;

/**
 * 39. 组合总和
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * <p>
 * candidates 中的数字可以无限制重复被选取。
 * <p>
 * 说明：
 * <p>
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。
 * 示例 1:
 * <p>
 * 输入: candidates = [2,3,6,7], target = 7,
 * 所求解集为:
 * [
 * [7],
 * [2,2,3]
 * ]
 * 示例 2:
 * <p>
 * 输入: candidates = [2,3,5], target = 8,
 * 所求解集为:
 * [
 * [2,2,2,2],
 * [2,3,3],
 * [3,5]
 * ]
 *
 * @author ZDQ
 */
public class CombinationSum39 {
    public static void main(String[] args) {
        int[] nums = {2, 3, 5};
        int target = 8;
        System.out.println(combinationSum(nums, target));
    }


    /**
     * 回溯+剪枝
     * @param candidates 整数数组
     * @param target 目标值
     * @return 和为目标值的所有数组组成的列表
     */
    private static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new LinkedList<>();
        if (candidates == null || candidates.length == 0) {
            return res;
        }
        Arrays.sort(candidates);
        collect(candidates, target, 0, new LinkedList<>(), res);
        return res;
    }

    private static void collect(int[] candidates, int target, int start, Deque<Integer> list,
                                List<List<Integer>> res) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            res.add(new LinkedList<>(list));
            return;
        }
        // 注意，每次从上一次的索引开始搜索。此处没有考虑到，参考了LeetCode题解
        for (int i = start; i < candidates.length; i++) {
            int sub = target - candidates[i];
            if (sub >= 0) {
                list.addLast(candidates[i]);
                collect(candidates, sub, i, list, res);
                list.removeLast();
            } else {
                //剪枝
                break;
            }
        }
    }
}
