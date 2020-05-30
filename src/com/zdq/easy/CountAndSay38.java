package com.zdq.easy;

/**
 * 38. 外观数列
 * 「外观数列」是一个整数序列，从数字 1 开始，序列中的每一项都是对前一项的描述。前五项如下：
 * <p>
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 * 1 被读作  "one 1"  ("一个一") , 即 11。
 * 11 被读作 "two 1s" ("两个一"）, 即 21。
 * 21 被读作 "one 2",  "one 1" （"一个二" ,  "一个一") , 即 1211。
 * <p>
 * 给定一个正整数 n（1 ≤ n ≤ 30），输出外观数列的第 n 项。
 * <p>
 * 注意：整数序列中的每一项将表示为一个字符串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1
 * 输出: "1"
 * 解释：这是一个基本样例。
 * 示例 2:
 * <p>
 * 输入: 4
 * 输出: "1211"
 * 解释：当 n = 3 时，序列是 "21"，其中我们有 "2" 和 "1" 两组，"2" 可以读作 "12"，也就是出现频次 = 1 而 值 = 2；
 * 类似 "1" 可以读作 "11"。所以答案是 "12" 和 "11" 组合在一起，也就是 "1211"。
 *
 * @author ZDQ
 */
public class CountAndSay38 {
    public static void main(String[] args) {
        int n = 6;
        // 21
        System.out.println(countAndSay1(n));
//        System.out.println(recursion(4, ""));
    }

    private static String countAndSay(int n) {
        if (n == 0) {
            return "";
        }
        if (n == 1) {
            return "1";
        }
        String string = countAndSay(n - 1);
        StringBuilder builder = new StringBuilder();
        int count = 1;
        for (int i = 0; i < string.length(); i++) {
            if (i != string.length() - 1 && string.charAt(i) == string.charAt(i + 1)) {
                count++;
            } else {
                builder.append(count).append(string.charAt(i));
                count = 1;
            }
        }
        return builder.toString();
    }

    private static String countAndSay1(int n) {
        if (n == 0) {
            return "";
        }
        if (n == 1) {
            return "1";
        }
        String string = countAndSay1(n - 1);
        StringBuilder builder = new StringBuilder();
        int i = 1;
        int pre = 0;
        for (; i < string.length(); i++) {
            if (string.charAt(i) != string.charAt(pre)) {
                builder.append(i - pre).append(string.charAt(pre));
                pre = i;
            }
        }
        builder.append(i - pre).append(string.charAt(pre));
        return builder.toString();
    }
}
