package com.zdq.middle;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 40. 组合总和 II
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * <p>
 * candidates 中的每个数字在每个组合中只能使用一次。
 * <p>
 * 说明：
 * <p>
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。
 * 示例 1:
 * <p>
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 所求解集为:
 * [
 * [1, 7],
 * [1, 2, 5],
 * [2, 6],
 * [1, 1, 6]
 * ]
 * 示例 2:
 * <p>
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 所求解集为:
 * [
 * [1,2,2],
 * [5]
 * ]
 *
 * @author ZDQ
 */
public class CombinationSum40 {
    public static void main(String[] args) {
        int[] candidates = {2, 5, 2, 1, 2};
        int target = 5;
        System.out.println(combinationSum2(candidates, target));
    }

    /**
     * 递归、回溯、剪枝
     * 自己做出来的
     * @param candidates 正整数数组
     * @param target 目标值
     * @return 和为目标值的所有数组列表
     */
    private static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new LinkedList<>();
        if (candidates == null || candidates.length == 0) {
            return res;
        }
        Arrays.sort(candidates);
        System.out.println(Arrays.toString(candidates));
        boolean[] visited = new boolean[candidates.length];
        collect(candidates, target, 0, visited, new LinkedList<>(), res);
        return res;
    }

    private static void collect(int[] candidates, int target, int start, boolean[] visited, Deque<Integer> list,
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
            if (visited[i]) {
                continue;
            }
            if (i > 0 && candidates[i - 1] == candidates[i] && !visited[i] && !visited[i - 1]) {
                continue;
            }
            int sub = target - candidates[i];
            if (sub >= 0) {
                list.addLast(candidates[i]);
                visited[i] = true;
                collect(candidates, sub, i + 1, visited, list, res);
                visited[i] = false;
                list.removeLast();
            } else {
                //剪枝
                break;
            }
        }
    }
}
