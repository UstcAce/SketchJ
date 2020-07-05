package leetcode;

import org.junit.Assert;
import org.junit.Test;

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
 */
public class Q44WildCardMatch {
    /**
     * 1. 定义动态规划求解问题 dp[i][j]表示s的前i个字符是否和p的前j个字符匹配
     * 2. 状态转移方程：
     * (1) s[i] == p[j] || p[j] == '?'
     * dp[i][j] = dp[i-1][j-1]
     * (2) p[j] == '*'
     * (2.1) *匹配0个字符，dp[i][j] = dp[i][j-1]
     * (2.2) *至少匹配1个字符，dp[i][j] = dp[i-1][j]
     * 3. 边界条件
     * dp[0][0] = true
     * dp[i][0] = false, i > 0, p = "",无法匹配非空s子串
     * dp[0][j] = p.subString(0, j) == "*"
     */
    public boolean isMatch(String s, String p) {
        p = cleanDuplicateStar(p);
        int sLen = s.length();
        int pLen = p.length();
        boolean[][] dp = new boolean[sLen + 1][pLen + 1];
        dp[0][0] = true;
        for (int j = 1; j <= pLen; j++) {
            dp[0][j] = p.substring(0, j).equals("*");
        }
        for (int i = 1; i <= sLen; i++) {
            for (int j = 1; j <= pLen; j++) {
                char sChar = s.charAt(i - 1);
                char pChar = p.charAt(j - 1);
                if (sChar == pChar || pChar == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (pChar == '*') {
                    // *匹配0个字符 || *匹配至少一个字符
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                }
            }
        }
        return dp[sLen][pLen];
    }

    private String cleanDuplicateStar(String p) {
        String pattern = "\\*+";
        return p.replaceAll(pattern, "\\*");
    }

    /**
     * 解法2：DP
     * 1. 问题定义 dp[i][j]表示s的前i个字符和p的前j个字符匹配的关系
     * 2. 状态转移方程
     *   dp[i][j] =
     *   (1) 若s[i] == p[j]或者p[j]=='?'; dp[i-1][j-1]
     *   (2) 若p[j] == '*'; dp[i][j-1] || dp[i-1][j]
     *   dp[i][j-1] 表示此星号不匹配字符
     *   dp[i-1][j] 表示此星号至少匹配一个字符
     *   (3)其他情况false
     * 3. 边界条件
     * (1)dp[0][0] = true
     * (2)dp[0][j],0<j<=lenP, 只有在p的前j个字符都是'*'才为true，其他情况都是false
     * (3)dp[i][0] = false,0<i<=lenS
     */
    public boolean isMatch2(String s, String p) {
        p = cleanDuplicateStar(p);
        int lenS = s.length();
        int lenP = p.length();

        boolean [][] dp = new boolean[lenS+1][lenP+1];

        dp[0][0] = true;

        for (int i=1; i<=lenP; i++) {
            dp[0][i] = cleanDuplicateStar(p.substring(0, i)).equals("*");
        }

        for (int i=1; i<=lenS; i++) {
            for (int j=1; j<=lenP; j++) {
                if (s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1)=='?') {
                    dp[i][j] = dp[i-1][j-1];
                } else if (p.charAt(j-1)=='*') {
                    dp[i][j] = dp[i][j-1] || dp[i-1][j];
                } else {
                    dp[i][j] = false;
                }
            }
        }
        return dp[lenS][lenP];
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
     *  输入:
     * s = "adceb"
     * p = "*a*b" ,"","a","b"
     * 输出: true
     * 解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
     *
     *  示例 5:
     * 输入:
     * s = "acdcb"
     * p = "a*c?b"
     * 输入: false
     */
    @Test
    public void testCase01() {
        String s = "acdcb";
        String p = "*a*b";
        boolean res = isMatch(s, p);
        boolean excepted = true;
        Assert.assertEquals(excepted, res);
    }

    @Test
    public void testCase02() {
        String s = "acdcb";
        String p = "a*c?b";
        boolean res = isMatch(s, p);
        boolean excepted = false;
        Assert.assertEquals(excepted, res);
    }

    @Test
    public void testCase03() {
        String s = "ho";
        String p = "ho**";
        boolean res = isMatch(s, p);
        boolean excepted = true;
        Assert.assertEquals(excepted, res);
    }

    @Test
    public void testCase03_2() {
        String s = "ho";
        String p = "**ho";
        boolean res = isMatch(s, p);
        boolean excepted = true;
        Assert.assertEquals(excepted, res);
    }

    @Test
    public void testCase04() {
        String s = "aa";
        String p = "a";
        boolean res = isMatch(s, p);
        boolean excepted = false;
        Assert.assertEquals(excepted, res);
    }

    @Test
    public void testCase05() {
        String s = "abbabbbaabaaabbbbbabbabbabbbabbaaabbbababbabaaabbab";
        String p = "*aabb***aa**a******aa*";
        boolean res = isMatch(s, p);
        boolean excepted = true;
        Assert.assertEquals(excepted, res);
    }
}
