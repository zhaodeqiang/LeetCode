package com.zdq.easy;

/**
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。
 * 请根据这个假设，如果反转后整数溢出那么就返回 0。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 123
 * 输出: 321
 * </p>
 * 示例 2:
 * <p>
 * 输入: -123
 * 输出: -321
 * </p>
 * <p>
 * 示例 3:
 * <p>
 * 输入: 120
 * 输出: 21
 * </p>
 *
 * @author ZDQ
 */
public class Reverse7 {
    public static void main(String[] args) {
        //x463847412
        int reverse = reverse(2147483647);
        System.out.println(reverse);
    }

    public static int reverse(int x) {
        int reverse = 0;
        while (x != 0) {
            // 当reverse = 214748364时，原数 = x463847412;x只能是1，所以reverse * 10 + pop不会溢出
            if ((reverse * 10) / 10 != reverse) {
                return 0;
            }
            int pop = x % 10;
            reverse = reverse * 10 + pop;
            x /= 10;
        }
        return reverse;
    }

    /**
     * 官方解法
     * @param x 原整数
     * @return 反转后的整数
     */
    public static int reverse1(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            // 如果 reverse * 10 + pop 溢出，那么 reverse >= Integer.MAX_VALUE / 10
            // 如果reverse > Integer.MAX_VALUE，那么 reverse * 10 + pop一定溢出。
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) {
                return 0;
            }
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) {
                return 0;
            }
            rev = rev * 10 + pop;
        }
        return rev;
    }
}
