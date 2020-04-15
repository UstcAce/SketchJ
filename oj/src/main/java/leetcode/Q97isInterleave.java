package leetcode;

/**
 * 给定三个字符串 s1, s2, s3, 验证 s3 是否是由 s1 和 s2 交错组成的。
 * 示例 1:
 * 输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * 输出: true
 *
 *  示例 2:
 * 输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * 输出: false
 */
public class Q97isInterleave {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false;
        return traceBack(s1, 0, s2, 0, s3, 0);
    }

    private boolean traceBack(String s1, int p1, String s2, int p2, String s3, int p3) {
        if (p1 == s1.length() && p2 == s2.length() && p3 == s3.length()) {
            return true;
        }

        boolean res = false;
        if (p1 < s1.length() && p3 < s3.length() && s1.charAt(p1) == s3.charAt(p3)) {
            res = res || traceBack(s1, p1 + 1, s2, p2, s3, p3 + 1);
        }
        if (p2 < s2.length() && p3 < s3.length() && s2.charAt(p2) == s3.charAt(p3)) {
            res = res || traceBack(s1, p1, s2, p2 + 1, s3, p3 + 1);
        }
        return res;
    }

    /**
     * 1. 定义动态规划求解问题 dp[i][j] s1前i个字符加上s2前j个字符是否能交错构成s3的前i+j个字符
     * 2. 状态转移方程：
     * (1)s1[i] == s3[i+j], dp[i][j] = dp[i-1][j]
     * (2)s2[j] == s3[i+j], dp[i][j] = dp[i][j-1]
     * 3. 边界条件
     * dp[0][0] = true
     *
     */
    public boolean isInterleave2(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false;
        boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
        dp[0][0] = true;
        for (int i = 1; i <= s1.length(); i++) {
            dp[i][0] = dp[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);
        }
        for (int i = 1; i <= s2.length(); i++) {
            dp[0][i] = dp[0][i - 1] && s2.charAt(i - 1) == s3.charAt(i - 1);
        }
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                boolean b = false;
                b = b || (s1.charAt(i - 1) == s3.charAt(i + j - 1) && dp[i - 1][j]);
                b = b || (s2.charAt(j - 1) == s3.charAt(i + j - 1) && dp[i][j - 1]);
                dp[i][j] = b;
            }
        }
        return dp[s1.length()][s2.length()];
    }
}
