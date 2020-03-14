package com.zdq.easy;

/**
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 *
 * 示例 1:
 *
 * 输入: 121
 * 输出: true
 * 示例 2:
 *
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * 示例 3:
 *
 * 输入: 10
 * 输出: false
 * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
 *
 * @author ZDQ
 */
public class IsPalindrome9 {
    public static void main(String[] args) {
        System.out.println(isPalindrome(23));
    }

    /**
     * 此解法需要处理溢出问题，效率低
     * @param x 待判断整数
     * @return true or false
     */
    public static boolean isPalindrome1(int x){
        if(x < 0){
            return false;
        }
        if(x == 0){
            return true;
        }
        int reverse = 0;
        int temp = x;
        while (x != 0){
            int pop = x % 10;
            // 溢出判断可以省略
          /*  if(reverse  > Integer.MAX_VALUE / 10 || (reverse == Integer.MAX_VALUE / 10 && pop > Integer.MAX_VALUE % 10)){
                return false;
            }*/
            reverse = reverse * 10 + pop;
            x = x / 10;
        }
        // 如果溢出，说明不是回文数字
        return reverse == temp;
    }

    /**
     * 官方解法
     * @param x 待判断整数
     * @return true or false
     */
    private static boolean isPalindrome(int x){
        //  特殊情况：
        //  如上所述，当 x < 0 时，x 不是回文数。
        //  同样地，如果数字的最后一位是 0，为了使该数字为回文，
        // 则其第一位数字也应该是 0
        // 只有 0 满足这一属性
        if (x < 0 || (x % 10 == 0 && x != 0)){
            return false;
        }
         int revert = 0;
        while (x > revert){
            revert = revert * 10 + x % 10;
            x = x / 10;
        }
        // 当数字长度为奇数时，我们可以通过 revertedNumber/10 去除处于中位的数字。
        // 例如，当输入为 12321 时，在 while 循环的末尾我们可以得到 x = 12，revertedNumber = 123，
        // 由于处于中位的数字不影响回文（它总是与自己相等），所以我们可以简单地将其去除。
        return x == revert || x == revert / 10;
    }
}
