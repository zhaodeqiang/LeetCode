package com.zdq.hard;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 42. 接雨水
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Marcos 贡献此图。
 * <p>
 * 示例:
 * <p>
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 *
 * @author ZDQ
 */
public class Trap42 {
    public static void main(String[] args) {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println("暴力解法:" + trapBruteForce(height));
        System.out.println("动态规划解法:" + trapDp(height));
        System.out.println("双指针解法:" + trapWithTwoPoints(height));
        System.out.println("栈解法:" + trapWithStack(height));
    }

    private static int trapBruteForce(int[] height) {
        int ans = 0;
        if (height != null) {
            int len = height.length;
            for (int i = 1; i < len - 1; i++) {
                int maxLeft = 0, maxRight = 0;
                for (int j = i; j >= 0; j--) {
                    maxLeft = Math.max(maxLeft, height[j]);
                }
                for (int j = i; j < len; j++) {
                    maxRight = Math.max(maxRight, height[j]);
                }
                ans += Math.min(maxLeft, maxRight) - height[i];
            }
        }
        return ans;
    }


    private static int trapDp(int[] height) {
        int ans = 0;
        if (height != null) {
            int len = height.length;
            int[] leftMax = new int[len];
            int[] rightMax = new int[len];
            leftMax[0] = height[0];
            rightMax[len - 1] = height[len - 1];
            // 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1
            for (int i = 1; i < len; i++) {
                leftMax[i] = Math.max(leftMax[i - 1], height[i]);
            }
            for (int i = len - 2; i >= 0; i--) {
                rightMax[i] = Math.max(rightMax[i + 1], height[i]);
            }
            //从下标 1开始遍历到 len - 2
            for (int i = 1; i < len - 1; i++) {
                ans += Math.min(leftMax[i], rightMax[i]) - height[i];
            }
        }
        return ans;
    }

    private static int trapWithTwoPoints(int[] height) {
        int ans = 0;
        if (height != null) {
            int left = 0;
            int right = height.length - 1;
            int rightMax = 0;
            int leftMax = 0;
            while (left < right) {
                if (height[left] < height[right]) {
                    //height[left] >= left_max ? (left_max = height[left])
                    // : ans += (left_max - height[left]);
                    leftMax = Math.max(height[left], leftMax);
                    ans += leftMax - height[left];
                    left++;
                } else {
                    rightMax = Math.max(height[right], rightMax);
                    ans += rightMax - height[right];
                    right--;
                }
            }
        }
        return ans;
    }

    private static int trapWithStack(int[] height) {
        int ans = 0;
        if (height != null) {
            int current = 0;
            Deque<Integer> stack = new LinkedList<>();
            while (current < height.length) {
                while (!stack.isEmpty() && height[current] > height[stack.peek()]) {
                    int top = stack.pop();
                    if (stack.isEmpty()) {
                        break;
                    }
                    Integer peek = stack.peek();
                    int distance = current - peek - 1;
                    int high = Math.min(height[current], height[peek]) - height[top];
                    ans += distance * high;
                }
                stack.push(current++);
            }
        }
        return ans;
    }
}
