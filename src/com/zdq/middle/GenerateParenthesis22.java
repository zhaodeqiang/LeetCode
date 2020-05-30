package com.zdq.middle;


import java.util.ArrayList;
import java.util.List;

/**
 * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 * <p>
 * 例如，给出 n = 3，生成结果为：
 * <p>
 * [
 * "((()))",
 * "(()())",
 * "(())()",
 * "()(())",
 * "()()()"
 * ]
 *
 * @author ZDQ
 */
public class GenerateParenthesis22 {
    public static void main(String[] args) {
        System.out.println(generateParenthesis1(3));
    }

    private static List<String> generateParenthesis1(int n) {
        List<String> result = new ArrayList<>();
        // 递归 深度优先
        backTrace(result, "", 0, 0, n);
        return result;
    }

    private static void backTrace(List<String> list, String str, int left, int right, int max) {
        if (str.length() == max << 1) {
            list.add(str);
            return;
        }
        if (left < max) {
            backTrace(list, str + "(", left + 1, right, max);
        }
        if (right < left) {
            backTrace(list, str + ")", left, right + 1, max);
        }
    }


    /**
     * 闭合数
     * 思路
     * 为了枚举某些内容，我们通常希望将其表示为更容易计算的不相交子集的总和。
     * 考虑有效括号序列 S 的 闭包数：至少存在 index >= 0，使得 S[0], S[1], ..., S[2*index+1]是有效的。 显然，每个括号序列都有一个唯一的闭包号。 我们可以尝试单独列举它们。
     * 算法
     * 对于每个闭合数 c，我们知道起始和结束括号必定位于索引 0 和 2*c + 1。然后两者间的 2*c 个元素一定是有效序列，其余元素一定是有效序列。
     *
     * @param n 括号对数
     * @return 结果集
     */
    private List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        if (n == 0) {
            ans.add("");
        } else {
            for (int c = 0; c < n; ++c) {
                for (String left : generateParenthesis(c)) {
                    for (String right : generateParenthesis(n - 1 - c)) {
                        ans.add("(" + left + ")" + right);
                    }
                }
            }
        }
        return ans;
    }
}

