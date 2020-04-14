package leetcode;

import org.junit.Test;

/**
 * 一条包含字母 A-Z 的消息通过以下方式进行了编码：
 *
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 给定一个只包含数字的非空字符串，请计算解码方法的总数。
 *
 * 示例 1:
 * 输入: "12"
 * 输出: 2
 * 解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
 *
 * 示例 2:
 * 输入: "226"
 * 输出: 3
 * 解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 */
public class Q91NumDecodings {
    /**
     * 1.定义动态规划求解问题 dp[i]表示从0到i的解码方法总数
     * 2.状态转移方程：
     * (1) s[i]=0, if s[i-1] = 0 or 1，则dp[i] = dp[i-2]，否则dp[i] = 0并return 0
     * (2) s[i]!=0, s[i-1] = 1, dp[i] = dp[i-1] + dp[i-2]
     * (3) s[i]!=0, s[i-1] = 2 且 1 <= s[i] <= 6, dp[i] = dp[i-1] + dp[i-2]
     * (4) s[i]!=0, s[i-1] = [3, 9],dp[i] = dp[i-1]
     */
    public int numDecodings(String s) {
        if (s.charAt(0) == '0') return 0;
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                if (s.charAt(i - 1) == '1' || s.charAt(i - 1) == '2') {
                    dp[i + 1] = dp[i - 1];
                } else {
                    return 0;
                }
            } else {
                if (s.charAt(i - 1) == '1' || (s.charAt(i - 1) == '2' && s.charAt(i) <= '6')) {
                    dp[i + 1] = dp[i] + dp[i - 1];
                } else {
                    dp[i + 1] = dp[i];
                }
            }
        }

        return dp[s.length()];
    }

    @Test
    public void testCase01() {
        String input = "226";
        System.out.println(numDecodings(input));
    }
}
