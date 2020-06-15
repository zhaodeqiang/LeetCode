package com.zdq.middle;

/**
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
 *
 * 示例 1:
 *
 * 输入: 2.00000, 10
 * 输出: 1024.00000
 * 示例 2:
 *
 * 输入: 2.10000, 3
 * 输出: 9.26100
 * 示例 3:
 *
 * 输入: 2.00000, -2
 * 输出: 0.25000
 * 解释: 2-2 = 1/22 = 1/4 = 0.25
 * 说明:
 *
 * -100.0 < x < 100.0
 * n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/powx-n
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author ZDQ
 */
public class MyPow50 {

    public static void main(String[] args) {
           double x = 2.0;
           int n = -10;
        System.out.println(myPow1(x, n));
    }


    /**
     * 递归+快速幂
     * 时空复杂度O(logn)
     * @param x 双精度浮点数
     * @param n 幂
     * @return 浮点数结果
     */
    private static double myPow(double x, int n) {
        return (long) n >= 0 ? quickMul(x, n) : 1.0 / quickMul(x, -(long) n);
    }

    private static double quickMul(double x, long n) {
        if (n == 0) {
            return 1.0;
        }
        double y = quickMul(x, n / 2);
        return n % 2 == 0 ? y * y : y * y * x;
    }



   private static double quickMul1(double x, long power) {
        double ans = 1.0;
        // 贡献的初始值为 x
        double xContribute = x;
        // 在对 N 进行二进制拆分的同时计算答案
        while (power > 0) {
            if (power % 2 == 1) {
                // 如果 N 二进制表示的最低位为 1，那么需要计入贡献
                ans *= xContribute;
            }
            // 将贡献不断地平方
            xContribute *= xContribute;
            // 舍弃 N 二进制表示的最低位，这样我们每次只要判断最低位即可
            power /= 2;
        }
        return ans;
    }

    /**
     * 快速幂+迭代
     * 时间复杂度O(logn)
     * 空间复杂度O(1)
     * @param x 双精度浮点数
     * @param n 幂
     * @return 浮点数结果
     */
    private static double myPow1(double x, int n) {
        return (long) n >= 0 ? quickMul1(x, n) : 1.0 / quickMul1(x, -(long) n);
    }
}
