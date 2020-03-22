package leetcode;

import org.junit.Test;

/**
 * 给定两个单词 word1 和 word2，计算出将 word1 转换成 word2 所使用的最少操作数 。
 *
 * 你可以对一个单词进行如下三种操作：
 *
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 *
 */
public class Q72EditDistance {
    /**
     * 1. 定义动态规划求解问题，dp[i][j]表示word1的前i个字符和word2的前j个字符的编辑距离
     * 2. 状态转移方程：
     *  (2.1) s1[i] == s2[j], dp[i][j] = dp[i - 1][j - 1]
     *  (2.2) s1[i] != s2[j], dp[i][j] 以下3种可能的最小值 Min(dp[i][j - 1] + 1, dp[i - 1][j] + 1, dp[i - 1][j - 1] + 1)
     *  a: 插入一个字符          dp[i][j - 1] + 1
     *  b: 删除s1[i]            dp[i - 1][j] + 1
     *  c: 替换s1[i] 为 s2[j]   dp[i - 1][j - 1] + 1
     * 3. 边界条件
     * dp[][] = new int[s1.length+1][s2.length+1]
     * dp[0][0] = 0
     * dp[0][j] = j     1 <= j <= s2.length
     * dp[i][0] = i     1 <= i <= s1.length
     */
    public int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();

        int[][] dp = new int[len1 + 1][len2 + 1];

        dp[0][0] = 0;

        for (int i = 1; i <= len1; i++) {
            dp[i][0] = i;
        }

        for (int j = 1; j <= len2; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                char c1 = word1.charAt(i - 1);
                char c2 = word2.charAt(j - 1);
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i][j - 1] + 1, Math.min(dp[i - 1][j] + 1, dp[i - 1][j - 1] + 1));
                }
            }
        }

        return dp[len1][len2];
    }

    @Test
    public void testCase01() {
        String word1 = "horse";
        String word2 = "ros";

        int res = minDistance(word1, word2);
        System.out.println(res);
    }

    @Test
    public void testCase02() {
        String word1 = "intention";
        String word2 = "execution";

        int res = minDistance(word1, word2);
        System.out.println(res);
    }
}
