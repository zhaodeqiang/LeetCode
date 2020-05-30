package com.zdq.easy;

import java.util.HashMap;
import java.util.Stack;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "()"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: "()[]{}"
 * 输出: true
 * 示例 3:
 * <p>
 * 输入: "(]"
 * 输出: false
 * 示例 4:
 * <p>
 * 输入: "([)]"
 * 输出: false
 * 示例 5:
 * <p>
 * 输入: "{[]}"
 * 输出: true
 *
 * @author ZDQ
 */
public class IsValid20 {
    public static void main(String[] args) {

    }

    private static HashMap<Character, Character> mappings;

    // Initialize hash map with mappings. This simply makes the code easier to read.
    static {
        mappings = new HashMap<>();
        mappings.put(')', '(');
        mappings.put('}', '{');
        mappings.put(']', '[');
    }

    public static boolean isValid(String s) {
        if ((s.length() & 1) == 1){
            return false;
        }

        // Initialize a stack to be used in the algorithm.
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // If the current character is a closing bracket.
            if (mappings.containsKey(c)) {

                // Get the top element of the stack. If the stack is empty, set a dummy value of '#'
                char topElement = stack.empty() ? '#' : stack.pop();

                // If the mapping for this bracket doesn't match the stack's top element, return false.
                if (topElement != mappings.get(c)) {
                    return false;
                }
            } else {
                // If it was an opening bracket, push to the stack.
                stack.push(c);
            }
        }

        // If the stack still contains elements, then it is an invalid expression.
        return stack.isEmpty();
    }

}
