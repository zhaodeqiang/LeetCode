package com.zdq.middle;

import java.util.Arrays;

/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 * 给定一个按照34. 在排序数组中查找元素的第一个和最后一个位置升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * <p>
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * <p>
 * 如果数组中不存在目标值，返回 [-1, -1]。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: [3,4]
 * 示例 2:
 * <p>
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: [-1,-1]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author ZDQ
 */
public class SearchRange34 {
    public static void main(String[] args) {
//        int[] nums = {5, 7, 7, 9, 10, 10};
        int[] nums = {1};
        int target = 1;
        // range = {0, 0}
        int[] range = searchRange(nums, target);
        System.out.println(Arrays.toString(range));
    }

    private static int[] searchRange(int[] nums, int target) {
        int[] result = {-1, -1};
        if (nums == null || nums.length < 1) {
            return result;
        }
        int left = search(nums, target);
        int right = search(nums, target + 1) - 1;
        if (left == nums.length || nums[left] != target) {
            return result;
        }
        result[0] = left;
        result[1] = right;
        return result;
    }

    private static int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            // 5, 7, 7, 8, 8, 10
            // 0  1 mid 3  4  5
            int mid = (left + right) >> 1;
            if (nums[mid] >= target) {
                //向左搜索
                right = mid - 1;
            } else {
                //向右搜索
                left = mid + 1;
            }
        }
        return left;
    }


    /**
     * 官方二分解法
     *
     * @param nums   升序数组
     * @param target 目标值
     * @return 目标值第一次和最后一次出现的下标，不存在返回[-1, -1]
     */
    public static int[] searchRangeOfficial(int[] nums, int target) {
        int[] targetRange = {-1, -1};
        int leftIdx = extremeInsertionIndex(nums, target, true);
        // assert that `leftIdx` is within the array bounds and that `target`
        // is actually in `nums`.
        if (leftIdx == nums.length || nums[leftIdx] != target) {
            return targetRange;
        }
        targetRange[0] = leftIdx;
        targetRange[1] = extremeInsertionIndex(nums, target, false) - 1;
        return targetRange;
    }

    private static int extremeInsertionIndex(int[] nums, int target, boolean left) {
        // 1、left 参数是一个 boolean 类型的变量，指示我们在遇到 target == nums[mid] 时应该做什么。
        // 如果 left 为 true ，那么我们递归查询左区间，否则递归右区间。
        // 2、求最左下标时，如果在下标为 i 处遇到了 target ，最左边的 target 一定不会出现在下标大于 i 的位置，
        // 所以我们永远不需要考虑右子区间。当求最右下标时，道理同样适用。
        int lo = 0;
        int hi = nums.length;
        while (lo < hi) {
            // 区间为[lo,mid)和[mid + 1, hi)
            int mid = (lo + hi) / 2;
            // 如果是求最左下标，需要判断target是否等于nums[mid]
            boolean midIsLarger = nums[mid] > target || (left && target == nums[mid]);
            if (midIsLarger) {
                //搜索左侧边界时，条件为：nums[mid] >= target
                hi = mid;
            } else {
                //搜索右侧边界时，条件为：nums[mid] <= target
                //向右搜索
                lo = mid + 1;
            }
        }
        return lo;
    }

    /**
     * 搜索左侧边界二分查找
     * 左闭右开
     * 因为我们初始化 right = nums.length
     * 所以决定了我们的「搜索区间」是 [left, right)
     * 所以决定了 while (left < right)
     * 同时也决定了 left = mid + 1 和 right = mid
     * <p>
     * 因为我们需找到 target 的最左侧索引
     * 所以当 nums[mid] == target 时不要立即返回
     * 而要收紧右侧边界以锁定左侧边界
     *
     * @param nums   有序数组
     * @param target 目标值
     * @return 最左边界
     */
    private int leftBound(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }
        int left = 0;
        // 注意
        int right = nums.length;
        // 注意
        while (left < right) {
            int mid = (left + right) / 2;
            // 因为「搜索区间」是 [left, right) 左闭右开，所以当 nums[mid] 被检测之后，
            // 下一步的搜索区间应该去掉 mid 分割成两个区间，即 [left, mid) 或 [mid + 1, right)。
            if (nums[mid] == target) {
                // 继续搜索左侧,缩小右边界
                right = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                // 注意
                right = mid;
            }
        }
        // 边界检查
        if (left >= nums.length || nums[left] != target) {
            return -1;
        }
        return left;
    }


    /**
     * 查找右侧边界
     * 左闭右开
     * 因为我们初始化 right = nums.length
     * 所以决定了我们的「搜索区间」是 [left, right)
     * 所以决定了 while (left < right)
     * 同时也决定了 left = mid + 1 和 right = mid
     * <p>
     * 因为我们需找到 target 的最右侧索引
     * 所以当 nums[mid] == target 时不要立即返回
     * 而要收紧左侧边界以锁定右侧边界
     * <p>
     * 又因为收紧左侧边界时必须 left = mid + 1
     * 所以最后无论返回 left 还是 right，必须减一
     *
     * @param nums   有序数组
     * @param target 目标值
     * @return 最右侧边界下标
     */
    private int rightBound(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }
        int left = 0, right = nums.length;
        // 中止条件left == right
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                // 注意，继续向右侧搜索，缩小左边界
                left = mid + 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid;
            }
        }
        // 注意返回 left - 1
        return left == 0 ? -1 : (nums[left - 1] == target ? (left - 1) : -1);
    }

//---------------------------------闭区间二分查找算法写法-------------------------------------//

    int binarySearch(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] == target) {
                // 直接返回
                return mid;
            }
        }
        // 直接返回
        return -1;
    }

    private int leftBoundClose(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] == target) {
                // 别返回，收缩左侧边界
                right = mid - 1;
            }
        }
        // 最后要检查 left 越界的情况
        if (left >= nums.length || nums[left] != target) {
            return -1;
        }
        return left;
    }


    private int rightBoundClose(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] == target) {
                // 别返回，收缩右侧边界
                left = mid + 1;
            }
        }
        // 最后要检查 right 越界的情况
        if (right < 0 || nums[right] != target) {
            return -1;
        }
        return right;
    }


}
