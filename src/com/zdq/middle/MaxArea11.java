package com.zdq.middle;

/**
 * @author ZDQ
 */
public class MaxArea11 {
    public static void main(String[] args) {
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int i = maxArea1(height);
        System.out.println(i);
    }

    /**
     * 暴力法
     *
     * @param height 容器高度数组
     * @return 最大盛水面积
     */
    private static int maxArea(int[] height) {
        int maxArea = 0;
        for (int i = 0; i < height.length - 1; i++) {
            for (int j = i + 1; j < height.length; j++) {
                maxArea = Math.max(maxArea, Math.min(height[i], height[j]) * (j - i));
            }
        }
        return maxArea;
    }

    private static int maxArea1(int[] height) {
        int maxArea = 0;
        if (height == null) {
            return maxArea;
        }
        int length = height.length;
        int left = 0;
        int right = length - 1;
        while (left < right) {
            maxArea = Math.max(maxArea, Math.min(height[left], height[right]) * (right - left));
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }
}
