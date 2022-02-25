package com.zdq.easy;

public class MaxSubArray {

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray(nums));
    }

    private static int maxSubArray(int[] nums) {
        // 滚动数组思想：用一个变量pre来维护对于当前f(i)的 f(i−1)的值是多少
        // 动态规划： f(i) = max{f(i-1)+ nums[i], nums[i]}
        int pre = 0;
        int maxAns = nums[0];
        for (int num : nums) {
            pre = Math.max(pre + num, num);
            maxAns = Math.max(maxAns, pre);
        }
        return maxAns;
    }
}
