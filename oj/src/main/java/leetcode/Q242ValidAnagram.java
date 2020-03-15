package leetcode;

import org.junit.Test;

import java.util.Arrays;

/**
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 *
 * 示例 1:
 *
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * 示例 2:
 *
 * 输入: s = "rat", t = "car"
 * 输出: false
 * 说明:
 * 你可以假设字符串只包含小写字母。
 *
 * 进阶:
 * 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
 *
 */
public class Q242ValidAnagram {
    public boolean isAnagram(String s, String t) {
        if (s.isEmpty() && t.isEmpty()) {
            return true;
        }

        int len1 = s.length();
        int len2 = t.length();

        if (len1 != len2) {
            return false;
        }

        char[] s1 = s.toCharArray();
        char[] s2 = t.toCharArray();
        Arrays.sort(s1);
        Arrays.sort(s2);

        for (int i = 0; i < len1; i++) {
            if (s1[i] != s2[i]) {
                return false;
            }
        }

        return true;
    }

    public boolean isAnagram2(String s, String t) {
        int len1 = s.length();
        int len2 = t.length();

        if (len1 != len2) {
            return false;
        }

        int[] count = new int[26];

        for (int i = 0; i < len1; i++) {
            count[s.charAt(i) - 'a'] += 1;
            count[t.charAt(i) - 'a'] -= 1;
        }

        for (int i = 0; i < 26; i++) {
            if (count[i] != 0) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void testCase01() {
        String s1 = "anagram";
        String s2 = "nagaram";

        System.out.println(isAnagram(s1, s2));
    }
}
