package com.zdq.hard;

/**
 * 题目描述：
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * <p>
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 * <p>
 * 说明:
 * <p>
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 * 示例 1:
 * <p>
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 * <p>
 * 输入:
 * s = "aa"
 * p = "a*"
 * 输出: true
 * 解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 * 示例 3:
 * <p>
 * 输入:
 * s = "ab"
 * p = ".*"
 * 输出: true
 * 解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 * 示例 4:
 * <p>
 * 输入:
 * s = "aab"
 * p = "c*a*b"
 * 输出: true
 * 解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 * 示例 5:
 * <p>
 * 输入:
 * s = "mississippi"
 * p = "mis*is*p*."
 * 输出: false
 *
 * @author ZDQ
 */
public class IsMatch10 {
    public static void main(String[] args) {
        String text = "aaa";
        String pattern = "a*";
        boolean match = isMatch2(text, pattern);
        System.out.println(match);
    }

    /**
     * 回溯解法
     *
     * @param text    主串
     * @param pattern 模式串
     * @return true or false
     */
    private static boolean isMatch(String text, String pattern) {
        if (pattern.isEmpty()) {
            return text.isEmpty();
        }
        // 主串不为空 & （首个字符相同 | 模式串首字符是.)
        boolean firstMatch = (!text.isEmpty() &&
                (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));

        if (pattern.length() >= 2 && pattern.charAt(1) == '*') {
            // isMatch(text, pattern.substring(2):表示*号匹配前一个字符0次
            // isMatch(text.substring(1), pattern):匹配前一个字符1次
            return (isMatch(text, pattern.substring(2)) ||
                    (firstMatch && isMatch(text.substring(1), pattern)));
        } else {
            return firstMatch && isMatch(text.substring(1), pattern.substring(1));
        }
    }


    private static Boolean[][] memo;

    /**
     * 动态规划 自顶向下方法 递归（回溯） 依赖子问题的解
     *
     * @param text    主串
     * @param pattern 模式串
     * @return true or false
     */
    private static boolean isMatch1(String text, String pattern) {
        memo = new Boolean[text.length() + 1][pattern.length() + 1];
        return dp(0, 0, text, pattern);

    }

    public static boolean dp(int i, int j, String text, String pattern) {
        if (memo[i][j] != null) {
            return memo[i][j];
        }
        boolean ans;
        if (j == pattern.length()) {
            ans = i == text.length();
        } else {
            boolean firstMatch = (i < text.length() &&
                    (pattern.charAt(j) == text.charAt(i) ||
                            pattern.charAt(j) == '.'));

            if (j + 1 < pattern.length() && pattern.charAt(j + 1) == '*') {
                ans = (dp(i, j + 2, text, pattern) ||
                        firstMatch && dp(i + 1, j, text, pattern));
            } else {
                ans = firstMatch && dp(i + 1, j + 1, text, pattern);
            }
        }
        memo[i][j] = ans;
        return ans;
    }

    /**
     * 动态规划 自底向上方法 每个子问题只需求解一次，求解某个子问题时，该子问题依赖的更小子问题均已求解完毕
     *
     * @param text    主串
     * @param pattern 模式串
     * @return true or false
     */
    private static boolean isMatch2(String text, String pattern) {
        boolean[][] dp = new boolean[text.length() + 1][pattern.length() + 1];
        dp[text.length()][pattern.length()] = true;

        for (int i = text.length(); i >= 0; i--) {
            for (int j = pattern.length() - 1; j >= 0; j--) {
                boolean firstMatch = (i < text.length() &&
                        (pattern.charAt(j) == text.charAt(i) ||
                                pattern.charAt(j) == '.'));
                if (j + 1 < pattern.length() && pattern.charAt(j + 1) == '*') {
                    dp[i][j] = dp[i][j + 2] || firstMatch && dp[i + 1][j];
                } else {
                    //dp[i + 1][j + 1]不会越界是因为firstMatch不成立时后者不会执行
                    dp[i][j] = firstMatch && dp[i + 1][j + 1];
                }
            }
        }
        return dp[0][0];
    }


    private static boolean match(String s, String p) {
        int sLen = s.length();
        int pLen = p.length();
        boolean[][] memory = new boolean[sLen + 1][pLen + 1];
        memory[0][0] = true;
        for (int i = 0; i <= sLen; i++) {
            for (int j = 1; j <= pLen; j++) {
                if (p.charAt(j - 1) == '*') {
                    // memory[i][j - 2]：匹配0次
                    memory[i][j] = memory[i][j - 2] || (i > 0 && (s.charAt(i - 1) == p.charAt(j - 2) ||
                            p.charAt(j - 2) == '.') && memory[i - 1][j]);
                } else {
                    memory[i][j] = i > 0 && (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.')
                            && memory[i - 1][j - 1];
                }
            }
        }
        return memory[sLen][pLen];
    }


    private static boolean match1(String s, String p) {
        int sLen = s.length();
        int pLen = p.length();
        boolean[][] memory = new boolean[2][pLen + 1];
        memory[0][0] = true;
        int cur = 0;
        int pre;
        for (int i = 0; i <= sLen; i++) {
            cur = i % 2;
            pre = (i + 1) % 2;
            if (i > 1) {
                for (int j = 0; j <= pLen; j++) {
                    memory[cur][j] = false;
                }
            }
            for (int j = 1; j <= pLen; j++) {
                if (p.charAt(j - 1) == '*') {
                    memory[cur][j] = memory[cur][j - 2] || (i > 0 && (s.charAt(i - 1) == p.charAt(j - 2)
                            || p.charAt(j - 2) == '.') && memory[pre][j]);
                } else {
                    memory[cur][j] = i > 0 && (s.charAt(i - 1) == p.charAt(j - 1)
                            || p.charAt(j - 1) == '.')
                            && memory[pre][j - 1];
                }
            }
        }
        return memory[cur][pLen];
    }
}
