package leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * 1. '.' 匹配任意单个字符
 * 2. '*' 匹配零个或多个前面的那一个元素(与前面的字符关联在一起匹配，可以把前面的字符删掉)(*无法单独出现)
 *
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 *
 * 说明:
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 */
public class Q10RegularExpressionMatching {

    /**
     * 1. 定义动态规划求解问题，dp[i][j]表示s的前i个字符是否可以和p的前j个字符匹配
     *
     * 2. 状态转移方程：dp[i][j]
     * (2.1) s[i] == p[i] || p[i] == '.'
     * dp[i][j] = dp[i-1][j-1]
     * (2.2) p[j] == '*'
     * (i) *删除前面字符,如s="",p="a*", dp[i][j] = dp[i][j-2]
     * (ii) *匹配前面的字符至少一次,
     * 如  s="a",p="a*"; s="a",p=".*"
     *  s[i] == p[j-1] || p[j-1] == '.';
     *  dp[i][j] = dp[i-1][j]
     *
     * 3.边界情况
     * dp[0][0] = true
     * dp[i][0] = false,i > 0
     * dp[0][j] = p[j-1] == "*",j < 0
     */
    public boolean isMatch(String s, String p) {
        int sLen = s.length();
        int pLen = p.length();
        boolean[][] dp = new boolean[sLen + 1][pLen + 1];
        dp[0][0] = true;
        for (int j = 2; j <= pLen; j++) {
           dp[0][j] = (p.charAt(j - 1) == '*') && dp[0][j - 2];
        }
        for (int i = 1; i <= sLen; i++) {
            for (int j = 1; j <= pLen; j++) {
                char sChar = s.charAt(i - 1);
                char pChar = p.charAt(j - 1);
                boolean b = false;
                if (sChar == pChar || pChar == '.') {
                    b = b || dp[i - 1][j - 1];
                } else if (pChar == '*') {
                    b = b || dp[i][j - 2];
                    if (sChar == p.charAt(j - 2) || p.charAt(j - 2) == '.') {
                        b = b || dp[i - 1][j];
                    }
                }
                dp[i][j] = b;
            }
        }
        return dp[sLen][pLen];
    }

    @Test
    public void testCase01() {
        String s = "";
        String p = "";
        boolean except = true;
        boolean res = isMatch(s, p);
        Assert.assertEquals(except, res);
    }

    @Test
    public void testCase02() {
        String s = "aa";
        String p = "a";
        boolean except = false;
        boolean res = isMatch(s, p);
        Assert.assertEquals(except, res);
    }

    @Test
    public void testCase03() {
        String s = "aa";
        String p = "a.";
        boolean except = true;
        boolean res = isMatch(s, p);
        Assert.assertEquals(except, res);
    }

    @Test
    public void testCase04() {
        String s = "aa";
        String p = "a*";
        boolean except = true;
        boolean res = isMatch(s, p);
        Assert.assertEquals(except, res);
    }


    @Test
    public void testCase05() {
        String s = "aaaaabc";
        String p = ".*";
        boolean except = true;
        boolean res = isMatch(s, p);
        Assert.assertEquals(except, res);
    }

    @Test
    public void testCase06() {
        String s = "";
        String p = "c*";
        boolean except = true;
        boolean res = isMatch(s, p);
        Assert.assertEquals(except, res);
    }

    @Test
    public void testCase07() {
        String s = "aaaaa";
        String p = "a*";
        boolean except = true;
        boolean res = isMatch(s, p);
        Assert.assertEquals(except, res);
    }

    @Test
    public void testCase08() {
        String s = "mississippi";
        String p = "mis*is*p*.";
        boolean except = false;
        boolean res = isMatch(s, p);
        Assert.assertEquals(except, res);
    }

    @Test
    public void testCase09() {
        String s = "";
        String p = ".*";
        boolean except = true;
        boolean res = isMatch(s, p);
        Assert.assertEquals(except, res);
    }

    @Test
    public void testCase10() {
        String s = "aaa";
        String p = "ab*a*c*a";
        boolean except = true;
        boolean res = isMatch(s, p);
        Assert.assertEquals(except, res);
    }
}
