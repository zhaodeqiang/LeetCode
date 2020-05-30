package com.zdq.middle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 * <p>
 * 返回被除数 dividend 除以除数 divisor 得到的商。
 * <p>
 * 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: dividend = 10, divisor = 3
 * 输出: 3
 * 解释: 10/3 = truncate(3.33333..) = truncate(3) = 3
 * 示例 2:
 * <p>
 * 输入: dividend = 7, divisor = -3
 * 输出: -2
 * 解释: 7/-3 = truncate(-2.33333..) = -2
 *  
 * <p>
 * 提示：
 * <p>
 * 被除数和除数均为 32 位有符号整数。
 * 除数不为 0。
 * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231,  231 − 1]。本题中，如果除法结果溢出，则返回 231 − 1。
 *
 * @author ZDQ
 */
public class Divide29 {
    public static void main(String[] args) {
        int dividend = Integer.MIN_VALUE;
        int divisor = 1;
        System.out.println(divide(dividend, divisor));
    }

    public static int divide(int dividend, int divisor) {
        if (divisor == 0 || (dividend == Integer.MIN_VALUE && divisor == -1)) {
            return Integer.MAX_VALUE;
        }
        if (divisor == Integer.MIN_VALUE) {
            return (divisor == dividend) ? 1 : 0;
        }
       /* if (divisor == 1) {
            return dividend;
        }
        if (dividend != Integer.MIN_VALUE && positive(dividend) < positive(divisor)) {
            return 0;
        }*/

        boolean isNegative = (dividend < 0) ^ (divisor < 0);
        //当dividend和divisor为Integer.MIN_VALUE时无法转换为正数
        boolean flag = false;
        if (dividend == Integer.MIN_VALUE) {
            dividend = Integer.MAX_VALUE;
            flag = true;
        } else if (dividend < 0) {
            dividend = -dividend;
        }
        if (divisor < 0) {
            divisor = -divisor;
        }
        int count = 0;
        int div = 0;
        for (int i = 0; i < 32; i++) {
            count = count << 1;
            div = (div << 1) | (dividend >>> (31 - i)) & 1;
            while (div >= divisor) {
                count++;
                div -= divisor;
            }
        }
        if (flag && (div + 1 - divisor) >= 0) {
            count++;
        }
//        int res = count == Integer.MIN_VALUE ? Integer.MAX_VALUE : count;
        return isNegative ? -count : count;
    }

    private static int positive(int val) {
        if (val < 0) {
            return ~val + 1;
        }
        return val;

    }


    public static int divide1(int dividend, int divisor) {
        boolean sign = (dividend > 0) ^ (divisor > 0);
        int result = 0;
        if (dividend > 0) {
            dividend = -dividend;
        }
        if (divisor > 0) {
            divisor = -divisor;
        }
        while (dividend <= divisor) {
            int tempResult = -1;
            int tempDivisor = divisor;
            while (dividend <= (tempDivisor << 1)) {
                if (tempDivisor <= (Integer.MIN_VALUE >> 1)) {
                    break;
                }
                tempResult = tempResult << 1;
                tempDivisor = tempDivisor << 1;
            }
            dividend = dividend - tempDivisor;
            result += tempResult;
        }
        if (!sign) {
            if (result <= Integer.MIN_VALUE) {
                return Integer.MAX_VALUE;
            }
            result = -result;
        }
        return result;
    }
}
