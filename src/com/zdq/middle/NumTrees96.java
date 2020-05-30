package com.zdq.middle;

/**
 * 96. 不同的二叉搜索树
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 * <p>
 * 示例:
 * <p>
 * 输入: 3
 * 输出: 5
 * 解释:
 * 给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
 * <p>
 * 1         3     3      2      1
 * \       /     /      / \      \
 * 3     2     1      1   3      2
 * /     /       \                 \
 * 2     1         2                 3
 *
 * @author ZDQ
 */
public class NumTrees96 {
    public static void main(String[] args) {
        int n = 3;
        System.out.println(numTrees(n));
    }

    /**
     * 动态规划
     * F(i,n) = G(i - 1) * G(n - i)
     * G(n) = F(i,1) + F(i,2) +...+ F(i,n)
     * @param n 1~n个数
     * @return 二叉搜索树的数量
     */
    private static int numTrees(int n) {
        int[] g = new int[n + 1];
        g[0] = 1;
        g[1] = 1;
        for (int i = 2; i <= n; ++i) {
            for (int j = 1; j <= i; ++j) {
                g[i] += g[j - 1] * g[i - j];
            }
        }
        return g[n];
    }


    /**
     * 卡特兰数递推公式：
     * C0 = 1 = C1 = 1,
     * Cn+1 = (2(2n + 1)/(n + 2))*Cn
     * @param n 1~n个数
     * @return 二叉搜索树的数量
     */
    private static int numTreesUseCatalan(int n) {
        // Note: we should use long here instead of int, otherwise overflow
        long C = 1;
        for (int i = 0; i < n; ++i) {
            C = C * 2 * (2 * i + 1) / (i + 2);
        }
        return (int) C;
    }
}