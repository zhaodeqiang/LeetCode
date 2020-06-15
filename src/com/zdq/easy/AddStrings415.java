package com.zdq.easy;

/**
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
 *
 * 注意：
 *
 * num1 和num2 的长度都小于 5100.
 * num1 和num2 都只包含数字 0-9.
 * num1 和num2 都不包含任何前导零。
 * 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author ZDQ
 */
public class AddStrings415 {
    public static void main(String[] args) {
        String num1 = "124";
        String num2 = "456";
        System.out.println(addStrings(num1, num2));
    }

    private static String addStrings(String num1, String num2){
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        StringBuilder res = new StringBuilder();
        int carry = 0;
        while (i >= 0 || j >= 0 || carry > 0){
            int n1 = i < 0 ? 0 : num1.charAt(i) - '0';
            int n2 = j < 0 ? 0 : num2.charAt(j) - '0';
            int add = n1 + n2 + carry;
            int digit = add % 10;
            carry = add / 10;
            res.append(digit);
            i--;
            j--;
        }
        return res.reverse().toString();
    }
}
