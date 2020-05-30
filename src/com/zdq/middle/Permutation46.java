package com.zdq.middle;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,2,3]
 * 输出:
 * [
 * [1,2,3],
 * [1,3,2],
 * [2,1,3],
 * [2,3,1],
 * [3,1,2],
 * [3,2,1]
 * ]
 *
 * @author ZDQ
 */
public class Permutation46 {
    public static void main(String[] args) {

    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();

        permute(nums, 0, res);

        return res;
    }

    private void permute(int[] nums, int start, List<List<Integer>> res) {
        if (start == nums.length) {
            List<Integer> list = new ArrayList<>();
            for (int a : nums) {
                list.add(a);
//                System.arraycopy(nums,0,list,0,nums.length);
            }
            res.add(list);
            return;
        }
        for (int i = start; i < nums.length; i++) {
            swap(nums, i, start);
            permute(nums, start + 1, res);
            swap(nums, i, start);
        }
    }

    private void swap(int[] nums, int i, int j) {
        // 优化性能，相等时不交换。
        if (i != j && nums[i] != nums[j]) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }
}
