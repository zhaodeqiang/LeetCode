package com.zdq.middle;

/**
 * 33.搜索旋转排序数组
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * <p>
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * <p>
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * <p>
 * 你可以假设数组中不存在重复的元素。
 * <p>
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 * 示例 2:
 * <p>
 * 输入: nums = [4,5,6,7,0,1,2], target = 3
 * 输出: -1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author ZDQ
 */
public class Search33 {
    public static void main(String[] args) {
        int[] nums = {3, 1};
        int target = 1;
        System.out.println(search(nums, target));
    }

    private static int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        if (nums.length == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = (low + high) >> 1;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[0] <= nums[mid]) {
                // 左半边有序
                if (nums[0] <= target && target < nums[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                // 右半边有序
                if (nums[mid] < target && nums[nums.length - 1] >= target) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }
}
