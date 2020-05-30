package com.zdq.hard;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "(()"
 * 输出: 2
 * 解释: 最长有效括号子串为 "()"
 * 示例 2:
 * <p>
 * 输入: ")()())"
 * 输出: 4
 * 解释: 最长有效括号子串为 "()()"
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author ZDQ
 */
public class LongestValidParentheses32 {
    public static void main(String[] args) {

    }


    /**
     * 解法1：栈
     * 时间复杂度和空间复杂度:O(n)
     *
     * @param s 括号字符串
     * @return 最长括号数
     */
    public int longestValidParentheses1(String s) {
        if (s == null || "".equals(s.trim())) {
            return 0;
        }
        Deque<Integer> stack = new LinkedList<>();
        int count = 0;
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else if (s.charAt(i) == ')') {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    count = Math.max(count, i - stack.peek());
                }
            }
        }
        return count;
    }


    /**
     * 动态规划
     * 时间复杂度和空间复杂度:O(n)
     *
     * @param s 括号字符串
     * @return 最长括号数
     */
    public int longestValidParentheses2(String s) {
        int maxans = 0;
        int dp[] = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                maxans = Math.max(maxans, dp[i]);
            }
        }
        return maxans;
    }


    /**
     * 无需额外空间
     * 时间复杂度O(n)
     * 空间复杂度O(1)
     *
     * @param s 括号字符串
     * @return 最长括号数
     */
    public int longestValidParentheses3(String s) {
        int left = 0, right = 0, maxlength = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * right);
            } else if (right >= left) {
                left = right = 0;
            }
        }
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * left);
            } else if (left >= right) {
                left = right = 0;
            }
        }
        return maxlength;
    }
}
