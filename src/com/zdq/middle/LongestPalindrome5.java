package com.zdq.middle;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * 示例 1：
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。(子串是连续的，子序列不连续)
 *
 * @author ZDQ
 */
public class LongestPalindrome5 {
    public static void main(String[] args) {
        // #j#a#v#a#
        String string = "babad";
//        String string = "java";
        String palindrome = longestPalindrome1(string);
        System.out.println(palindrome);
    }


    @NotNull
    private static String longestPalindrome(@NotNull String string) {
        // 字符串预处理
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("#");
        for (int i = 0; i < string.length(); i++) {
            stringBuilder.append(string.charAt(i));
            stringBuilder.append("#");
        }

        // 最长回文右边界
        int rightIndex = 0;
        // 最长回文右边界的对称中心
        int symmetricCenter = 0;
        //len数组中的最大值
        int maxLength = 0;
        //length最大时的中心
        int maxLengthCenter = 0;
        // length数组 存放该字符在原来的字符串中得到的最长回文字符串的一半长度，即回文半径，包括字符本身
        int[] len = new int[stringBuilder.length()];
        for (int i = 1; i < stringBuilder.length(); i++) {
            //当rightIndex > i，那么我们就在rightIndex - i 与len[2 * symmetricCenter - i](len[j])，取得最小值
            //因为当i + len[j] < rightIndex时，我们就把len[i]更新为len[j]
            //但是如果i + len[j] >= rightIndex时，我们暂且将len[i]定更新为rightIndex - i,
            // 超出的部分需要我们一个一个的匹配
            len[i] = rightIndex > i ? Math.min(rightIndex - i, len[2 * symmetricCenter - i]) : 1;
//            if (rightIndex > i) {
//                len[i] = Math.min(rightIndex - i, len[2 * symmetricCenter - i]);
//            } else {
//                len[i] = 1;
//            }
            //一个一个匹配
            // 如果产生回文，i + 1 == len[i]，否则 i + 1 > len[i]
            while (i - len[i] > -1 && i + len[i] < stringBuilder.length()) {
                if (stringBuilder.charAt(i - len[i]) == stringBuilder.charAt(i + len[i])) {
                    len[i]++;
                } else {
                    break;
                }
            }
            //当 len[i] + i > rightIndex,我们需要更新symmetricCenter和rightIndex
            //至于为什么会这样做，理解一下rightIndex和symmetricCenter的含义
            if (len[i] + i > rightIndex) {
                rightIndex = len[i] + i;
                symmetricCenter = i;
            }
            // 记录最大回文长度和对应的最大回文中心
            if (len[i] > maxLength) {
                maxLength = len[i];
                maxLengthCenter = i;
            }
        }
        System.out.println("symmetricCenter = " + symmetricCenter);
        System.out.println(Arrays.toString(len));
        System.out.println(maxLength);
        System.out.println(maxLengthCenter);
        //截取字符串
        //为什么是maxLengthCenter - maxLength + 1,因为len[i] - 1才是原来的回文字符串长度，而maxLength记录的是len中最大值
        return stringBuilder.substring(maxLengthCenter - maxLength + 1, maxLengthCenter + maxLength - 1)
                .replace("#", "");
    }

    /**
     * Manacher算法
     * 执行用时 :7 ms, 在所有 Java 提交中击败了95.43%的用户
     * 内存消耗 :39.2 MB, 在所有 Java 提交中击败了18.68%的用户
     *
     * @param string 含有回文子串的字符串
     * @return 最长回文子串
     */
    private static String longestPalindrome1(String string) {
        if (string == null || string.length() == 0) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        int index = 0;
        char[] ch = string.toCharArray();
        int length = (string.length() << 1) + 1;
        char[] chars = new char[length];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (i & 1) == 0 ? '#' : ch[index++];
        }
        // 最大回文右边界
        int maxRight = 0;
        // 最大回文中心, 会随着i的增加而增加
        int center = 0;
        int maxValue = -1;
        int maxIndex = -1;
        int[] pArr = new int[length];
        for (int i = 0; i < chars.length; i++) {
            pArr[i] = maxRight > i ? Math.min(pArr[(center << 1) - i], maxRight - i) : 1;
            while (i + pArr[i] < length && i - pArr[i] > -1) {
                if (chars[i + pArr[i]] == chars[i - pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }
            if (i + pArr[i] > maxRight) {
                maxRight = i + pArr[i];
                center = i;
            }
            if (pArr[i] > maxValue) {
                maxValue = pArr[i];
                maxIndex = i;
            }
        }
        System.out.println(chars);
        System.out.println("maxIndex = " + maxIndex);
        System.out.println("maxValue = " + (maxValue - 1));
        System.out.println("maxRight = " + maxRight);
        System.out.println("center = " + center);
        System.out.println("pArr = " + Arrays.toString(pArr));
        for (int i = maxIndex - maxValue + 2; i < maxIndex + maxValue - 1; i += 2) {
            result.append(chars[i]);
        }
        /*result = new StringBuilder(new String(chars).substring(maxIndex - maxValue + 1, maxIndex + maxValue - 1)
                .replace("#", ""));*/
        System.out.println("result = " + result);
        return result.toString();
    }

    /**
     * 动态规划解法
     *
     * @param s 字符串
     * @return 最长回文子串
     */
    public String longestPalindrome2(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }

        boolean[][] dp = new boolean[len][len];

        // 初始化
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        int maxLen = 1;
        int start = 0;

        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {

                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                } else {
                    dp[i][j] = false;
                }
                // 只要 dp[i][j] == true 成立，就表示子串 s[i, j] 是回文，此时记录回文长度和起始位置
                if (dp[i][j]) {
                    int curLen = j - i + 1;
                    if (curLen > maxLen) {
                        maxLen = curLen;
                        start = i;
                    }
                }
            }
        }
        return s.substring(start, start + maxLen);
    }
}

