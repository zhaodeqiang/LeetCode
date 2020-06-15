package com.zdq.middle;

import java.util.*;

/**
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,1,2]
 * 输出:
 * [
 * [1,1,2],
 * [1,2,1],
 * [2,1,1]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author ZDQ
 */
public class PermuteUnique47 {
    public static void main(String[] args) {
        int[] nums = {2, 3, 3};
        System.out.println(permuteUnique(nums));
    }

    private static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        // 排序（升序或者降序都可以），排序是剪枝的前提
        Arrays.sort(nums);
        int len = nums.length;
        //记录下标为i的元素是否已经使用过
        boolean[] used = new boolean[len];
        // 使用Deque代替Stack是Java官方推荐,用来记录排列数组
        Deque<Integer> path = new ArrayDeque<>(len);
        dfs(nums, len, 0, used, path, res);
        return res;
    }

    private static void dfs(int[] nums, int len, int depth,
                            boolean[] used, Deque<Integer> path, List<List<Integer>> res) {
        if (depth == len) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < len; ++i) {
            // 剪枝，跳过used[i] = true的元素
            if (used[i]) {
                continue;
            }
            // 剪枝条件：i > 0 是为了保证 nums[i - 1] 有意义
            // 写 !used[i - 1] 是因为 nums[i - 1] 在深度优先遍历的过程中刚刚被撤销选择
            // 被撤销选择说明已经被选择过，而且nums[i] == nums[i - 1]，需要跳过该元素
            // used[i - 1] 前面加不加感叹号的区别仅在于保留的是相同元素的顺序索引，还是倒序索引。
            // 很明显，顺序索引（即使用 !used[i - 1] 作为剪枝判定条件得到）的递归树剪枝更彻底，思路也相对较自然。
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            path.addLast(nums[i]);
            used[i] = true;
            dfs(nums, len, depth + 1, used, path, res);
            // 回溯部分的代码，和dfs之前的 used[i] = true 代码是对称的
            used[i] = false;
            path.removeLast();
        }
    }
}
