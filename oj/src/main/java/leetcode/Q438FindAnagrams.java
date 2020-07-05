package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
 * 字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。
 *
 * 说明：
 * 字母异位词指字母相同，但排列不同的字符串。 不考虑答案输出的顺序。
 *
 * 示例 1:
 * 输入: s: "cbaebabacd" p: "abc"
 * 输出: [0, 6]
 * 解释: 起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。 起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。  
 *
 * 示例 2:
 * 输入: s: "abab" p: "ab"
 * 输出: [0, 1, 2]
 * 解释: 起始索引等于 0 的子串是 "ab", 它是 "ab" 的字母异位词。 起始索引等于 1 的子串是 "ba", 它是 "ab" 的字母异位词。 起始索引等于 2 的子串是
 * "ab", 它是 "ab" 的字母异位词。
 */
public class Q438FindAnagrams {
    /**
     * 字符串只包含小写英文字母
     * @param p 非空
     */
    public List<Integer> findAnagrams(String s, String p) {
        int sLen = s.length();
        int pLen = p.length();

        if (sLen < pLen) {
            return new ArrayList<>();
        }

        List<Integer> res = new ArrayList<>();
        int[] pTable = calcTable(p);
        int[] sLocalTable = calcTable(s.substring(0, pLen));
        if (Arrays.equals(pTable, sLocalTable)) {
            res.add(0);
        }
        for (int i = 1; i <= sLen - pLen; i++) {
            sLocalTable[s.charAt(i - 1) - 'a'] -= 1;
            sLocalTable[s.charAt(i + pLen - 1) - 'a'] += 1;
            if (Arrays.equals(pTable, sLocalTable)) {
                res.add(i);
            }
        }
        return res;
    }

    private int[] calcTable(String str) {
        int[] table = new int[26];
        for (char ch : str.toCharArray()) {
            table[ch - 'a'] += 1;
        }
        return table;
    }
}
