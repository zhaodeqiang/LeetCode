package com.zdq.middle;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * 
 * 示例 1:
 * 
 * 输入: "abcabcbb" 输出: 3 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。 示例 2:
 * 
 * 输入: "bbbbb" 输出: 1 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 
 * 示例 3:
 * 
 * 输入: "pwwkew" 输出: 3 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。   请注意，你的答案必须是 子串
 * 的长度，"pwke" 是一个子序列，不是子串。
 * 
 * @author ZDQ
 *
 */
public class LengthOfLongestSubstring_3 {

	public static void main(String[] args) {

		String string = "tmmzuxt"; // "tmmzuxt"
		int lengthOfLongestSubstring = lengthOfLongestSubstringSet(string);
		System.out.println(lengthOfLongestSubstring);
	}

	/**
	 * 标签：滑动窗口 暴力解法时间复杂度较高，会达到 O(n^2)，故而采取滑动窗口的方法降低时间复杂度。定义一个 map 数据结构存储 (k, v)，其中
	 * key 值为字符，value 值为字符位置 +1，加 1 表示从字符位置后一个才开始不重复。我们定义不重复子串的开始位置为 start，结束位置为
	 * end。 随着 end 不断遍历向后，会遇到与 [start, end] 区间内字符相同的情况，此时将字符作为 key 值，获取其 value 值，并更新
	 * start，此时 [start, end] 区间内不存在重复字符。 无论是否更新 start，都会更新其 map 数据结构和结果 ans。
	 * 时间复杂度：O(n)
	 * 
	 * @param s 目标字符串
	 * @return 最长不重复子串
	 */
	public static int lengthOfLongestSubstring(String s) {
		int length = s.length();
		HashMap<Character, Integer> map = new HashMap<Character, Integer>(16);
		int result = 0;
		for (int end = 0, start = 0; end < length; end++) {
			if (map.containsKey(s.charAt(end))) {
				start = Math.max(map.get(s.charAt(end)), start);
//				start = Math.max(map.get(s.charAt(end)) + 1, start);
			}
			// 将字符添加到map当中，并添加value值，若字符重复，只更新value值
			// 加 1 表示从字符位置后一个才开始不重复.
			map.put(s.charAt(end), end + 1);
//			map.put(s.charAt(end), end);
			// 保留最大的子串长度，与新的长度进行比较
			result = Math.max(result, end - start + 1);
		}
		return result;
	}
	
	
	public static int lengthOfLongestSubstringSet(String s) {
		// tmmzuxt
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            }
            else {
            	// 当遇到重复字符时，将移除之前所存的所有字符，并将i的值设为所移除的最后一个字符的下标值+1
                set.remove(s.charAt(i++));
            }
        }
//        System.out.println(set.toString());
        return ans;
    }



}
