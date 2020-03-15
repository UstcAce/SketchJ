package leetcode;

import org.junit.Test;

/**
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列。
 * <p>
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。两个字符串的「公共子序列」是这两个字符串所共同拥有的子序列。
 * <p>
 * 若这两个字符串没有公共子序列，则返回 0。
 * <p>
 * 示例 1:
 * 输入：text1 = "abcde", text2 = "ace"
 * 输出：3
 * 解释：最长公共子序列是 "ace"，它的长度为 3。
 * <p>
 * 示例 2:
 * 输入：text1 = "abc", text2 = "abc"
 * 输出：3
 * 解释：最长公共子序列是 "abc"，它的长度为 3。
 * <p>
 * 示例 3:
 * 输入：text1 = "abc", text2 = "def"
 * 输出：0
 * 解释：两个字符串没有公共子序列，返回 0。
 */
public class Q1143LongestCommonSubsequence {
    /**
     * 1. 定义动态规划求解问题，dp[i][j]表示text1前i个字符和text2前j个字符的最长公共子序列长度
     * 2. 状态转移方程 dp[i][j]
     * (1) s[i] == s[j]; dp[i][j] = dp[i-1][j-1] + 1
     * (2) s[i] != s[j]; dp[i][j] = Max(dp[i-1][j], dp[i][j-1])
     * 3. 边界条件
     */
    public int longestCommonSubsequence(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        if (len1 == 0 || len2 == 0) {
            return 0;
        }

        int[][] dp = new int[len1][len2];

        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                boolean b = s1.charAt(i) == s2.charAt(j);
                if (i - 1 >= 0 && j - 1 >= 0) {
                    if (b) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    }
                } else if (i - 1 >= 0 && j - 1 < 0) {
                    dp[i][j] = b ? Math.max(1, dp[i - 1][j]) : dp[i - 1][j];
                } else if (i - 1 < 0 && j - 1 >= 0) {
                    dp[i][j] = b ? Math.max(1, dp[i][j - 1]) : dp[i][j - 1];
                } else {
                    dp[i][j] = b ? 1 : 0;
                }
            }
        }

        return dp[len1 - 1][len2 - 1];
    }

    @Test
    public void testCase01() {
        String s1 = "abc";
        String s2 = "def";
        System.out.println(longestCommonSubsequence(s1, s2));
    }


    @Test
    public void testCase02() {
        String s1 = "bl";
        String s2 = "yby";
        System.out.println(longestCommonSubsequence(s1, s2));
    }
}
