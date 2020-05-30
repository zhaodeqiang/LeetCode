package com.zdq.middle;

import java.util.ArrayList;
import java.util.List;

/**
 * 电话号码的字母组合
 * @author ZDQ
 */
public class LetterCombination17 {
    public static void main(String[] args) {

        List<String> stringList = letterCombinations("23");
        System.out.println(stringList);
    }

    private static List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        char[][] digitChar = {{'a', 'b', 'c'}, {'d', 'e', 'f'}, {'g', 'h', 'i'}, {'j', 'k', 'l'},
                {'m', 'n', 'o'}, {'p', 'q', 'r', 's'}, {'t', 'u', 'v'}, {'w', 'x', 'y', 'z'}};
        // '0' ~ '9' : 48 ~ 57
        if (digits != null && digits.length() != 0) {
            recursive(digitChar, result, "", digits);
        }
        return result;
    }

    private static void recursive(char[][] digitChar, List<String> result, String str, String digits) {
        if (digits.length() == 0) {
            result.add(str);
        } else {

            int num = digits.charAt(0) - '0';
            char[] temp = digitChar[num - 2];
            for (char c : temp) {
                recursive(digitChar, result, str + c, digits.substring(1));
            }
        }
    }

    public List<String> letterCombinations1(String digits) {
        String[] keys = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        List<String> list = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return list;
        }
        doCombinations(keys, new StringBuilder(), list, digits);
        return list;
    }

    private void doCombinations(String[] keys, StringBuilder prefix, List<String> list, String digits) {
        if (prefix.length() == digits.length()) {
            list.add(prefix.toString());
            return;
        }
        int curDigit = digits.charAt(prefix.length()) - '0';
        String key = keys[curDigit];
        for (char c : key.toCharArray()) {
            prefix.append(c);
            doCombinations(keys, prefix, list, digits);
            //删除最后一个字符
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }
}