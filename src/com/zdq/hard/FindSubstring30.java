package com.zdq.hard;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个字符串 s 和一些长度相同的单词 words。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
 * 注意子串要与 words 中的单词完全匹配，中间不能有其他字符，但不需要考虑 words 中单词串联的顺序。
 * 示例 1：
 * <p>
 * 输入：
 * s = "barfoothefoobarman",
 * words = ["foo","bar"]
 * 输出：[0,9]
 * 解释：
 * 从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
 * 输出的顺序不重要, [9,0] 也是有效答案。
 * 示例 2：
 * <p>
 * 输入：
 * s = "wordgoodgoodgoodbestword",
 * words = ["word","good","best","word"]
 * 输出：[]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/substring-with-concatenation-of-all-words
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindSubstring30 {
    public static void main(String[] args) {
        String s = "barfoothefoobarman";
        String[] words = {"foo", "bar"};
        System.out.println(findSubstring(s, words));
    }

    public static List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new LinkedList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) {
            return res;
        }
        HashMap<String, Integer> map = new HashMap<>(16);
        int wordLength = words[0].length();
        int wordCount = words.length;
        for (String str : words) {
            map.put(str, map.getOrDefault(str, 0) + 1);
        }
        for (int i = 0; i < wordLength; i++) {
            int left = i;
            int rigth = i;
            int count = 0;
            HashMap<String, Integer> tempMap = new HashMap<>(16);
            while (rigth + wordLength <= s.length()) {
                String word = s.substring(rigth, rigth + wordLength);
                rigth += wordLength;
                if (!map.containsKey(word)) {
                    count = 0;
                    left = rigth;
                    tempMap.clear();
                } else {
                    // 将word放入临时哈希表
                    tempMap.put(word, tempMap.getOrDefault(word, 0) + 1);
                    count++;
                    while (tempMap.getOrDefault(word, 0) > map.getOrDefault(word, 0)) {
                        String tempWord = s.substring(left, left + wordLength);
                        count--;
                        left = left + wordLength;
                        tempMap.put(tempWord, tempMap.getOrDefault(tempWord, 0) - 1);
                    }
                    if (count == wordCount) {
                        res.add(left);
                    }
                }
            }
        }
        return res;
    }
}
