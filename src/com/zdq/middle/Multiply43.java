package com.zdq.middle;

/**
 * 43. 字符串相乘
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 * <p>
 * 示例 1:
 * <p>
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 示例 2:
 * <p>
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 * 说明：
 * <p>
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 *
 * @author ZDQ
 */
public class Multiply43 {
    private static final String ZERO = "0";
    private static final String ONE = "1";

    public static void main(String[] args) {
        String num1 = "24";
        String num2 = "39";
        // 56088
        System.out.println(multiply1(num1, num2));
    }

    /**
     * 普通竖式
     * 时间复杂度 O(m*n)
     * 空间复杂度 O(m+n)
     *
     * @param num1 数字1
     * @param num2 数字2
     * @return 两数乘积
     */
    private static String multiply(String num1, String num2) {
        if (ZERO.equals(num1) || ZERO.equals(num2)) {
            return ZERO;
        }
        if (ONE.equals(num1)) {
            return num2;
        }
        if (ONE.equals(num2)) {
            return num1;
        }
        String res = "0";
        for (int i = num2.length() - 1; i >= 0; i--) {
            int carry = 0;
            StringBuilder builder = new StringBuilder();
            // 补 0
            for (int j = 0; j < num2.length() - 1 - i; j++) {
                builder.append(0);
            }
            int n2 = num2.charAt(i) - '0';
            for (int j = num1.length() - 1; j >= 0 || carry > 0; j--) {
                int n1 = j < 0 ? 0 : num1.charAt(j) - '0';
                int multi = n1 * n2 + carry;
                int digits = multi % 10;
                carry = multi / 10;
                builder.append(digits);
            }
            // 将当前结果与新计算的结果求和作为新的结果
            res = addString(res, builder.reverse().toString());
        }
        return res;
    }

    /**
     * 计算两个字符串数字之和
     *
     * @param num1 数字1
     * @param num2 数字2
     * @return 两数之和
     */
    private static String addString(String num1, String num2) {
        StringBuilder builder = new StringBuilder();
        int carry = 0;
        for (int i = num1.length() - 1, j = num2.length() - 1;
             i >= 0 || j >= 0 || carry != 0;
             i--, j--) {
            int x = i < 0 ? 0 : num1.charAt(i) - '0';
            int y = j < 0 ? 0 : num2.charAt(j) - '0';
            int sum = (x + y + carry) % 10;
            builder.append(sum);
            carry = (x + y + carry) / 10;
        }
        return builder.reverse().toString();
    }


    /**
     * 优化竖式
     * 时间复杂度 O(m*n)
     * 空间复杂度 O(m+n)
     *
     * @param num1 数字1
     * @param num2 数字2
     * @return 两数乘积
     */
    private static String multiply1(String num1, String num2) {
        if (ZERO.equals(num1) || ZERO.equals(num2)) {
            return "0";
        }
        int[] res = new int[num1.length() + num2.length()];
        for (int i = num1.length() - 1; i >= 0; i--) {
            int n1 = num1.charAt(i) - '0';
            for (int j = num2.length() - 1; j >= 0; j--) {
                int n2 = num2.charAt(j) - '0';
                //上一步的进位加上当前两个数字的和
                int sum = (res[i + j + 1] + n1 * n2);
                //个位
                res[i + j + 1] = sum % 10;
                //进位
                res[i + j] += sum / 10;
            }
        }

        int beginIndex = 0;
        //计算new string()的起始索引并将十进制数字转为ASCII码
        for (int i = beginIndex; i < res.length; i++) {
            if (beginIndex < res.length - 1 && res[beginIndex] == 0){
                beginIndex++;
            }
            res[i] += '0';
        }

      /*  StringBuilder result = new StringBuilder();
        for (int i = 0; i < res.length; i++) {
            if (i == 0 && res[i] == 0) {
                continue;
            }
            result.append(res[i]);
        }
        return result.toString();*/
        return new String(res, beginIndex, res.length - beginIndex);
    }

    private static String multiply2(String num1, String num2) {
        char[] value = new char[num1.length() + num2.length()];
        for (int i = num1.length() - 1; i >= 0; i--) {
            for (int j = num2.length() - 1; j >= 0; j--) {
                value[i + j + 1] += (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
            }
        }

        int carry = 0;
        for (int i = value.length - 1; i >= 0; i--) {
            value[i] += carry;
            carry = value[i] / 10;
            value[i] %= 10;
        }

        int beginIndex = 0;
        //计算起始索引
        while (beginIndex < value.length - 1 && value[beginIndex] == 0) {
            beginIndex++;
        }
        //将十进制数字转为ASCII码
        for (int i = beginIndex; i < value.length; i++) {
            value[i] += '0';
        }
        return new String(value, beginIndex, value.length - beginIndex);
    }
}
