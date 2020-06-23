package com.zdq.easy;

/**
 * 实现 strStr() 函数。
 * <p>
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 * <p>
 * 示例 1:
 * <p>
 * 输入: haystack = "hello", needle = "ll"
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: haystack = "aaaaa", needle = "bba"
 * 输出: -1
 * 说明:
 * <p>
 * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 * <p>
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-strstr
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author ZDQ
 */
public class Str28 {
    public static void main(String[] args) {

    }

    private static int strStr1(String haystack, String needle) {
        // 逐个比较
        int l = needle.length();
        int n = haystack.length();
        for (int start = 0; start < n - l + 1; ++start) {
            if (haystack.substring(start, start + l).equals(needle)) {
                return start;
            }
        }
        return -1;
    }

    /**
     * 官方双指针解法
     *
     * @param haystack 主串
     * @param needle   模式串
     * @return 首次匹配成功下标
     */
    private static int strStr(String haystack, String needle) {
        int l = needle.length();
        int n = haystack.length();
        if (l == 0) {
            return 0;
        }

        int pn = 0;
        while (pn < n - l + 1) {
            // find the position of the first needle character
            // in the haystack string
            while (pn < n - l + 1 && haystack.charAt(pn) != needle.charAt(0)) {
                ++pn;
            }

            // compute the max match string
            int currLen = 0;
            int pL = 0;
            while (pL < l && pn < n && haystack.charAt(pn) == needle.charAt(pL)) {
                ++pn;
                ++pL;
                ++currLen;
            }

            // if the whole needle string is found,
            // return its start position
            if (currLen == l) {
                return pn - l;
            }

            // otherwise, backtrack
            pn = pn - currLen + 1;
        }
        return -1;
    }


    private static int sunday(String haystack, String needle) {
        if (haystack == null || needle == null) {
            return -1;
        }
        int hLength = haystack.length();
        int nLength = needle.length();
        int i = 0;
        int j = 0;
        while (i <= hLength - nLength) {
            while (j < nLength && haystack.charAt(i + j) == needle.charAt(j)) {
                j++;
            }
            if (j == nLength) {
                return i;
            }
            if (i < hLength - nLength) {
                i += (nLength - lastIndex(needle, haystack.charAt(i + nLength)));
            } else {
                return -1;
            }
            j = 0;
        }
        return -1;
    }

    private static int lastIndex(String str, char ch) {
        for (int i = str.length() - 1; i >= 0; i--) {
            if (str.charAt(i) == ch) {
                return i;
            }
        }
        return -1;
    }
}
