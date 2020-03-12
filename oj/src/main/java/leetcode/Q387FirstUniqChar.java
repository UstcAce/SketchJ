package leetcode;

import org.junit.Test;

import java.util.HashMap;

/**
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 * <p>
 * 案例:
 * <p>
 * s = "leetcode"
 * 返回 0.
 * <p>
 * s = "loveleetcode",
 * 返回 2.
 *  
 * 注意事项：您可以假定该字符串只包含小写字母。
 */
public class Q387FirstUniqChar {
    public int firstUniqChar(String s) {
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            String subStr = new StringBuilder(s).deleteCharAt(i).toString();
            if (subStr.indexOf(c) == -1) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Map + 两次遍历
     */
    public int firstUniqChar2(String s) {
        HashMap<Character, Integer> count = new HashMap<Character, Integer>();
        int n = s.length();
        // build hash map : character and how often it appears
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            count.put(c, count.getOrDefault(c, 0) + 1);
        }

        // find the index
        for (int i = 0; i < n; i++) {
            if (count.get(s.charAt(i)) == 1)
                return i;
        }
        return -1;
    }


    @Test
    public void testCase01() {
        String input = "lovelove";
        System.out.println(firstUniqChar(input));
    }
}
