package com.zdq.middle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ZDQ
 */
public class ThreeSum15 {
    public static void main(String[] args) {

        int[] nums = {-3, 4, -1, 0, 2};
        List<List<Integer>> lists = threeSum(nums);
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return result;
        }
        Arrays.sort(nums);
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            if (nums[i] > 0) {
                return result;
            }
            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }
            int positive = ~nums[i] + 1;
            int low = i + 1;
            int high = length - 1;
            while (low < high) {
                int twoSums = nums[low] + nums[high];
                if (twoSums == positive) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(nums[i]);
                    temp.add(nums[low]);
                    temp.add(nums[high]);
                    result.add(temp);
                    while (low < high && nums[low + 1] == nums[low]) {
                        low++;
                    }
                    while (low < high && nums[high - 1] == nums[high]) {
                        high--;
                    }
                    low++;
                    high--;
                } else if (twoSums < positive) {
                    low++;
                } else {
                    high--;
                }
            }
        }
        return result;
    }
}
