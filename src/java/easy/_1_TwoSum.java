package java.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * 题目：
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 * 
 * 例子：
 * 给定 nums = [2, 7, 11, 15], target = 9
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * @author ZDQ
 *
 */
public class _1_TwoSum {
	   public int[] twoSum(int[] nums, int target) {
           Map<Integer, Integer> map = new HashMap<Integer, Integer>();
           for (int i = 0; i < nums.length; i++) {
               if (map.containsKey(target - nums[i])) {
                   return new int[]{map.get(target - nums[i]), i};
               } else {
                   map.put(nums[i], i);
               }
           }
           return new int[]{-1, -1};
       }
}
