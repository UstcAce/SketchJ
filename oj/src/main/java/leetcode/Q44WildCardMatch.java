package leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
 *
 * '?' 可以匹配任何单个字符。
 * '*' 可以匹配任意字符串（包括空字符串）。
 * 两个字符串完全匹配才算匹配成功。
 *
 * 说明:
 *
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
 *
 */
public class Q44WildCardMatch {
    private Map<KeyStr, Boolean> map = new HashMap<KeyStr, Boolean>();
    /**
     * 解法1：递归 + 记忆 + 清除冗余star
     */
    public boolean isMatch(String s, String p) {
        p = cleanDuplicateStar(p);

        KeyStr keyStr = new KeyStr(s, p);
        if (map.containsKey(keyStr)) {
            return map.get(keyStr);
        }

        if (s.equals(p)) {
            map.put(keyStr, true);
            return true;
        }
        if (p.equals("*")) {
            map.put(keyStr, true);
            return true;
        }
        if (s.equals("")) {
            boolean res = p.replace("*", "").equals("");
            map.put(keyStr, res);
            return res;
        }

        if (p.equals("")) {
            map.put(keyStr, false);
            return false;
        }

        String s0 = s.substring(0, 1);
        String p0 = p.substring(0, 1);

        if (s0.equals(p0) || p0.equals("?")) {
            boolean res = isMatch(s.substring(1), p.substring(1));
            map.put(keyStr, res);
            return res;
        }

        if (p0.equals("*")) {
            // 星号没有匹配字符
            boolean cond1 = isMatch(s, p.substring(1));
            // 星号至少匹配了一个字符
            boolean cond2 = isMatch(s.substring(1), p);
            boolean res = cond1 || cond2;
            map.put(keyStr, res);
            return res;
        }
        map.put(keyStr, false);
        return false;
    }

    private String cleanDuplicateStar(String p) {
        String pattern = "\\*+";
        return p.replaceAll(pattern, "\\*");
    }

    private static class KeyStr {
        public String a;
        public String b;

        public KeyStr(String a, String b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            KeyStr keyStr = (KeyStr) o;
            return a.equals(keyStr.a) &&
                    b.equals(keyStr.b);
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b);
        }
    }

    /**
     * 示例 1:
     *
     * 输入:
     * s = "aa"
     * p = "a"
     * 输出: false
     * 解释: "a" 无法匹配 "aa" 整个字符串。
     * 示例 2:
     *
     * 输入:
     * s = "aa"
     * p = "*"
     * 输出: true
     * 解释: '*' 可以匹配任意字符串。
     * 示例 3:
     *
     * 输入:
     * s = "cb"
     * p = "?a"
     * 输出: false
     * 解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
     * 示例 4:
     *
     * 输入:
     * s = "adceb"
     * p = "*a*b" ,"","a","b"
     * 输出: true
     * 解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
     * 示例 5:
     *
     * 输入:
     * s = "acdcb"
     * p = "a*c?b"
     * 输入: false
     */
    @Test
    public void testCase01() {
        String s = "acdcb";
        String p = "*a*b";
        System.out.println(isMatch(s, p));
    }

    @Test
    public void testCase02() {
        String s = "acdcb";
        String p = "a*c?b";
        System.out.println(isMatch(s, p));
    }

    @Test
    public void testCase03() {
        String s = "ho";
        String p = "ho**";
        System.out.println(isMatch(s, p));
    }

    @Test
    public void testCase04() {
        String s = "aa";
        String p = "a";
        System.out.println(isMatch(s, p));
    }

    @Test
    public void testCase05() {
        String s = "abbabbbaabaaabbbbbabbabbabbbabbaaabbbababbabaaabbab";
        String p = "*aabb***aa**a******aa*";
        System.out.println(isMatch(s, p));
    }
}
