package com.zdq.middle;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * @author ZDQ
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * 示例 1：
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。(子串是连续的，子序列不连续)
 * https://www.jianshu.com/p/494d7603cac4
 */
public class LongestPalindrome5 {
    public static void main(String[] args) {
        // #j#a#v#a#
        String string = "java";
        String palindrome = longestPalindrome(string);
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
        System.out.println(Arrays.toString(len));
        System.out.println(maxLength);
        System.out.println(maxLengthCenter);
        //截取字符串
        //为什么是maxLengthCenter - maxLength + 1,因为len[i] - 1才是原来的回文字符串长度，而maxLength记录的是len中最大值
        return stringBuilder.substring(maxLengthCenter - maxLength + 1, maxLengthCenter + maxLength - 1)
                .replace("#", "");
    }
}
