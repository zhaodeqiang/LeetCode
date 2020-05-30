package com.zdq.easy;

/**
 * 35. 搜索插入位置
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * <p>
 * 你可以假设数组中无重复元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,3,5,6], 5
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: [1,3,5,6], 2
 * 输出: 1
 * 示例 3:
 * <p>
 * 输入: [1,3,5,6], 7
 * 输出: 4
 * 示例 4:
 * <p>
 * 输入: [1,3,5,6], 0
 * 输出: 0
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-insert-position
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author ZDQ
 */
public class SearchInsert35 {
    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 6, 7, 8, 9, 10};
        int target = 5;
        System.out.println(searchInsert(nums, target));
    }

    private static int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int lo = 0;
        int hi = nums.length - 1;
        while (lo <= hi) {
            // [lo, mid - 1] [mid + 1, hi]
            int mid = (lo + hi) >> 1;
            if (nums[mid] >= target) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }
}
