package com.zdq.middle;

import java.util.Arrays;

public class ThreeSumClosest16 {
    public static void main(String[] args) {

        int[] nums = {-3, 1, 2, 0, 4};
        threeSumClosest(nums, -2);
    }

    public static int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 3) {
            return 0;
        }
        Arrays.sort(nums);
        int min = Integer.MAX_VALUE;
        int result = 0;
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }
            int current = nums[i];
            int low = i + 1;
            int high = length - 1;
            while (low < high) {
                int sum = current + nums[low] + nums[high];
                int abs = sum - target;
                if (abs == 0) {
                    return sum;
                }
                if (abs < 0) {
                    low++;
                    if ((~abs + 1) < min) {
                        min = ~abs + 1;
                        result = sum;
                    }
                } else {
                    high--;
                    if (abs < min) {
                        min = abs;
                        result = sum;
                    }

                }
            }
        }
        return result;
    }
}
