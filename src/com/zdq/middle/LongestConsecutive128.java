package com.zdq.middle;

import java.util.HashSet;

/**
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 *
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 * 示例 2：
 *
 * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
 * 输出：9
 *  
 *
 * 提示：
 *
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-consecutive-sequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LongestConsecutive128 {

    public static void main(String[] args) {

        int[] nums = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        System.out.println(longestConsecutive(nums));
    }



    private static int longestConsecutive(int[] nums) {

        if (nums == null || nums.length == 0) {
            return 0;
        }

        HashSet<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        int longestLen = 0;
        for (Integer num : numSet) {
            // 每次在哈希表中检查是否存在 x−1即可。如果x-1存在，说明当前数x不是连续序列的起始数字，需要跳过这个数
            if (!numSet.contains(num - 1)) {
                //num不存在，则num为序列起点，此时需要更新序列长度
                int currentNum = num;
                int currentLen = 1;

                while (numSet.contains(currentNum + 1)) {
                    currentLen += 1;
                    currentNum += 1;
                }

                longestLen = Math.max(longestLen, currentLen);
            }
        }
        return longestLen;
    }
}
