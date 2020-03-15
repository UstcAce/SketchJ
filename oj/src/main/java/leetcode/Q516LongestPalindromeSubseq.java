package leetcode;

/**
 * 给定一个字符串s，找到其中最长的回文子序列。可以假设s的最大长度为1000。
 *
 * 示例 1:
 * 输入:
 *
 * "bbbab"
 * 输出:
 *
 * 4
 * 一个可能的最长回文子序列为 "bbbb"。
 *
 * 示例 2:
 * 输入:
 *
 * "cbbd"
 * 输出:
 *
 * 2
 * 一个可能的最长回文子序列为 "bb"。
 */
public class Q516LongestPalindromeSubseq {
    /**
     * 1. 定义动态规划求解问题dp[i][j]表示开始于i,结束于j的字符串的最大回文子序列长度
     * 2. 状态转移方程 dp[i][j], 0 <= i <= j < len
     * (1) i == j, dp[i][j] = 1
     * (2) i + 1 = j, 判断s[i]是否等于s[j], 是: dp[i][j] = 2; 否：dp[i][j] = 1
     * (3)判断s[i]是否等于s[j], 是: dp[i][j] = dp[i+1][j-1] + 2; 否： dp[i][j] = Max(dp[i+1][j], dp[i][j-1])
     * 这里可以发现计算 dp[i][j]的时候需要dp表里面
     * a: dp[i][j]下方的位置dp[i+1][j]的解
     * b: dp[i][j]左边的位置dp[i][j-1]的解
     * 于是考虑i从没有下方的位置开始 i = n -1  to 0
     * j从没有左边的位置开始 j = i to n - 1 (j >= i)
     *
     */
    public int longestPalindromeSubseq(String s) {
        int len = s.length();
        if (len == 0) {
            return 0;
        }

        int[][] dp = new int[len][len];

        for (int i = len - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < len; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i+1][j-1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }

        return dp[0][len - 1];
    }
}
