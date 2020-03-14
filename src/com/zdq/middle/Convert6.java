package com.zdq.middle;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 题目描述：
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 * <p>
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
 * <p>
 * 请你实现这个将字符串进行指定行数变换的函数：
 * <p>
 * string convert(string s, int numRows);
 * 示例 1:
 * <p>
 * 输入: s = "LEETCODEISHIRING", numRows = 3
 * 输出: "LCIRETOESIIGEDHN"
 * 示例 2:
 * <p>
 * 输入: s = "LEETCODEISHIRING", numRows = 4
 * 输出: "LDREOEIIECIHNTSG"
 * 解释:
 * <p>
 * L     D     R      0   6    12
 * E   O E   I I      1  5 7  11 13
 * E C   I H   N      2 4  8 10  14
 * T     S     G      3     9    15
 *
 * @author ZDQ
 */
public class Convert6 {
    public static void main(String[] args) {
        String s = "LEETCODEISHIRING";
        int rowNums = 4;
        String convert = convert0(s, rowNums);
        System.out.println(convert);
    }

    /**
     * 找数组下标规律
     * @param s 目标字符串
     * @param numRows 行数
     * @return 转换后的字符串
     */
    private static String convert(String s, int numRows) {
        if (s == null || numRows <= 1 || s.length() <= 2 || numRows >= s.length()) {
            return s;
        }
        int t = 2 * (numRows - 1);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            int j = i;
            boolean flag = true;
            while (j < s.length()) {
                result.append(s.charAt(j));
                if (i == 0 || i == numRows - 1) {
                    j = j + t;
                } else if (flag) {
                    j = j + t - 2 * i;
                    flag = false;
                } else {
                    j = j + 2 * i;
                    flag = true;
                }
            }
        }
        return result.toString();
    }

    private static String convert0(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        int t = 2 * numRows - 2;
        List<StringBuilder> builders = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            builders.add(new StringBuilder());
        }

        for (int i = 0; i < s.length(); i++) {
            // 余数法
            int row = i % t > numRows - 1 ? t - i % t : i % t;
            builders.get(row).append(s.charAt(i));
        }
        StringBuilder returnString = new StringBuilder();
        for (StringBuilder b : builders) {
            returnString.append(b);
        }
        return returnString.toString();
    }

    /**
     * 官方解法一
     * @param s 目标字符串
     * @param numRows 行数
     * @return 转换后的字符串
     */
    private static String convert1(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        List<StringBuilder> stringBuilderList = new ArrayList<>();
        // 在字符串长度和指定的行数中选择最小的值进行遍历，实例化StringBuilder对象。
        for (int i = 0; i < Math.min(s.length(), numRows); i++) {
            stringBuilderList.add(new StringBuilder());
        }

        int curRow = 0;
        boolean goingDown = false;
        //核心代码
        for (char c : s.toCharArray()) {
            stringBuilderList.get(curRow).append(c);
            // 判断是第一行或者是最后一行，如果是第一行，需要增加行；若是最后一行，需要减少行。
            if (curRow == 0 || curRow == numRows - 1) {
                goingDown = !goingDown;
            }
            curRow += goingDown ? 1 : -1;
        }

        StringBuilder returnString = new StringBuilder();
        for (StringBuilder builder : stringBuilderList) {
            returnString.append(builder);
        }
        return returnString.toString();
    }

    /**
     * 官方解法二：逐行访问
     * 行 0 中的字符位于索引k(2*numRows−2) 处;
     * 行 numRows−1 中的字符位于索引 k(2*numRows−2)+numRows−1 处;
     * 内部的 行 i 中的字符位于索引 k(2*numRows−2)+i 以及(k+1)(2*numRows−2)−i 处;
     *
     * @param s       目标字符串
     * @param numRows 指定行数
     * @return Z字形字符串
     */
    private static String convert2(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }

        StringBuilder ret = new StringBuilder();
        int n = s.length();
        int cycleLen = 2 * numRows - 2;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j + i < n; j += cycleLen) {
                ret.append(s.charAt(j + i));
                if (i != 0 && i != numRows - 1 && j + cycleLen - i < n) {
                    ret.append(s.charAt(j + cycleLen - i));
                }
            }
        }
        return ret.toString();
    }

    public String convert3(String s, int numRows) {
        if (s == null || s.length() < 3 || s.length() <= numRows || numRows <= 1) {
            return s;
        }
        int step = 2 * numRows - 2;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            int k = 0;
            while (k * step + i < s.length()) {
                sb.append(s.charAt(k * step + i));
                k++;
                if (i > 0 && i < numRows - 1 && k * step - i < s.length()) {
                    sb.append(s.charAt(k * step - i));
                }
            }
        }
        return sb.toString();
    }
}
