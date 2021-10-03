package com.zdq.middle;

/**
 * 516. 最长回文子序列
 * 给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。
 * 子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。
 *
 * 示例 1：
 * 输入：s = "bbbab"
 * 输出：4
 * 解释：一个可能的最长回文子序列为 "bbbb" 。
 *
 * 示例 2：
 * 输入：s = "cbbd"
 * 输出：2
 * 解释：一个可能的最长回文子序列为 "bb" 。
 *
 * 提示：
 * 1 <= s.length <= 1000
 * s 仅由小写英文字母组成
 */
public class LongestPalindromeSubSeq516 {

    public static void main(String[] args) {

        String s = "AAAABA";
        int len = longestPalindromeSubSeq(s);
        System.out.println(len);
    }

    private static int longestPalindromeSubSeq(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int len = s.length();
        // 用 dp[i][j]表示字符串s的下标范围[i,j]内的最长回文子序列的长度。
        // 假设字符串s的长度为 n，则只有当 0≤i≤j<n时，
        // 才会有 dp[i][j]>0，否则 dp[i][j]=0。
        int[][] dp = new int[len][len];

        for (int i = len - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < len; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    // 如果 s[i]≠s[j]，则 s[i]s[i] 和 s[j]s[j]不可能同时作为同一个回文子序列的首尾，
                    // 因此dp[i][j]=max(dp[i+1][j], dp[i][j−1])
                    // i = 0; j = 4，有AAAAB, 则：
                    // i = 1, j=4 = AAAB; i=0; j=3 = AAAA
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        // i=0，j=len - 1；整个字符串
        return dp[0][len - 1];
    }
}
