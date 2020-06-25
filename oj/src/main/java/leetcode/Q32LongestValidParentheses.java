package leetcode;

import org.junit.Test;

/**
 * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 *
 * 示例 1:
 *
 * 输入: "(()"
 * 输出: 2
 * 解释: 最长有效括号子串为 "()"
 * 示例 2:
 *
 * 输入: ")()())"
 * 输出: 4
 * 解释: 最长有效括号子串为 "()()"
 *
 */
public class Q32LongestValidParentheses {
    /**
     * 1. 定义动态规划求解问题:以n个字符结束 最长有效括号子串的长度为dp[n]
     * 若s[i]='('，那么dp[i]=0
     * 2. 状态转移方程：
     * (1)若s[i]=')', s[i-1]='('即
     *    ......() 的形状
     *    dp[i]=dp[i-2]+2
     * (2)若s[i]=')', s[i-1]=')'即
     *     ......))
     *    若s[i-dp[i-1]-1] == '('
     *    dp[i]=dp[i-1]+dp[i-dp[i-1]-2]+2
     * 3. 边界条件dp[0]=0
     */
    public int longestValidParentheses(String s) {
        int len = s.length();
        if (len <= 1) {
            return 0;
        }
        int [] dp = new int[len];
        int max = 0;
        for (int i=1; i<len; i++) {
            if (s.charAt(i)==')' && s.charAt(i-1)=='(') {
                int local = i-2>=0?dp[i-2]:0;
                dp[i] = local + 2;
            } else if (s.charAt(i)==')' && s.charAt(i-1)==')') {
                if (i-dp[i-1]-1>=0 && s.charAt(i-dp[i-1]-1) == '(') {
                    int local = i-dp[i-1]-2>=0?dp[i-dp[i-1]-2]:0;
                    dp[i] = dp[i-1]+local+2;
                }
            }
            max = Math.max(dp[i], max);
        }
        return max;
    }

    @Test
    public void testCase01() {
        String input = "())";
        System.out.println(longestValidParentheses(input));
    }

    @Test
    public void testCase02() {
        String input = ")()())";
        System.out.println(longestValidParentheses(input));
    }

    @Test
    public void testCase03() {
        String input = "()(())";
        System.out.println(longestValidParentheses(input));
    }

    @Test
    public void testCase04() {
        String input = "())";
        System.out.println(longestValidParentheses(input));
    }
}
