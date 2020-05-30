package com.zdq.easy;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 * 示例 1:
 * <p>
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 * <p>
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 * 说明:
 * <p>
 * 所有输入只包含小写字母 a-z 。
 *
 * @author ZDQ
 */
public class LongestCommonPrefix14 {
    public static void main(String[] args) {
        String[] strArrays = {"aa", "a"};
        String s = longestCommonPrefix(strArrays);
        System.out.println(s);
    }

    /**
     * 自己想出来的解法
     * 执行用时 :2 ms, 在所有 Java 提交中击败了37.88%的用户
     * 内存消耗 :38.1 MB, 在所有 Java 提交中击败了5.67%的用户。
     *
     * @param strs 字符串数组
     * @return 最长公共前缀
     */
    public static String longestCommonPrefix(String[] strs) {
        StringBuilder result = new StringBuilder();
        if (strs == null || strs.length == 0) {
            return result.toString();
        }
        if (strs.length == 1) {
            return strs[0];
        }
        String first = strs[0];
        for (int i = 0; i < first.length(); i++) {
            for (int j = 1; j < strs.length; j++) {
                if (i + 1 > strs[j].length() || (first.charAt(i) != strs[j].charAt(i))) {
                    return result.toString();
                }
            }
            result.append(first.charAt(i));
        }
        return result.toString();
    }


    /**
     * 官方二分查找
     *
     * @param strs 字符串数组
     * @return 最长公共子串
     */
    private String longestCommonPrefix1(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        int minLen = Integer.MAX_VALUE;
        for (String str : strs) {
            minLen = Math.min(minLen, str.length());
        }
        int low = 1;
        int high = minLen;
        while (low <= high) {
            int middle = (low + high) / 2;
            if (isCommonPrefix(strs, middle)) {
                low = middle + 1;
            } else {
                high = middle - 1;
            }
        }
        return strs[0].substring(0, (low + high) / 2);
    }

    private boolean isCommonPrefix(String[] strs, int len) {
        String str1 = strs[0].substring(0, len);
        for (int i = 1; i < strs.length; i++) {
            if (!strs[i].startsWith(str1)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 分治解法
     *
     * @param strs 字符串数组
     * @return 最长公共前缀
     */
    public String longestCommonPrefix2(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        return longestCommonPrefix(strs, 0, strs.length - 1);
    }

    private String longestCommonPrefix(String[] strs, int l, int r) {
        if (l == r) {
            return strs[l];
        } else {
            int mid = (l + r) / 2;
            String lcpLeft = longestCommonPrefix(strs, l, mid);
            String lcpRight = longestCommonPrefix(strs, mid + 1, r);
            return commonPrefix(lcpLeft, lcpRight);
        }
    }

    String commonPrefix(String left, String right) {
        int min = Math.min(left.length(), right.length());
        for (int i = 0; i < min; i++) {
            if (left.charAt(i) != right.charAt(i)) {
                return left.substring(0, i);
            }
        }
        return left.substring(0, min);
    }

}
