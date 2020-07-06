package leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
 * 换句话说，第一个字符串的排列之一是第二个字符串的子串。
 *
 * 示例1:
 * 输入: s1 = "ab" s2 = "eidbaooo"
 * 输出: True
 * 解释: s2 包含 s1 的排列之一 ("ba").
 *
 * 示例2:
 * 输入: s1= "ab" s2 = "eidboaoo"
 * 输出: False
 *
 * 注意：
 * 输入的字符串只包含小写字母
 * 两个字符串的长度都在 [1, 10,000] 之间
 */
public class Q567PermutationInString {
    public boolean checkInclusion(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        if (len1 > len2) {
            return false;
        }

        int[] table1 = calcCharTable(s1);
        int[] table2 = calcCharTable(s2.substring(0, len1 - 1));
        for (int i = 0; i <= len2 - len1; i++) {
            char tail = s2.charAt(i + len1 - 1);
            table2[tail - 'a']++;
            if (Arrays.equals(table1, table2)) {
                return true;
            }
            char head = s2.charAt(i);
            table2[head - 'a']--;
        }

        return false;
    }

    private int[] calcCharTable(String s) {
        int[] table = new int[26];
        for (char c : s.toCharArray()) {
            table[c - 'a']++;
        }
        return table;
    }

    @Test
    public void testCase01() {
       String s1 = "ab";
       String s2 = "eidbaooo";
       boolean res = checkInclusion(s1, s2);

       boolean excepted = true;
       Assert.assertEquals(excepted, res);
    }

    @Test
    public void testCase02() {
        String s1 = "ab";
        String s2 = "eidboaoo";
        boolean res = checkInclusion(s1, s2);

        boolean excepted = false;
        Assert.assertEquals(excepted, res);
    }

    @Test
    public void testCase03() {
        String s1 = "a";
        String s2 = "a";
        boolean res = checkInclusion(s1, s2);

        boolean excepted = true;
        Assert.assertEquals(excepted, res);
    }

    @Test
    public void testCase04() {
        String s1 = "a";
        String s2 = "b";
        boolean res = checkInclusion(s1, s2);

        boolean excepted = false;
        Assert.assertEquals(excepted, res);
    }
}
