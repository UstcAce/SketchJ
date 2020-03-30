package leetcode;

import org.junit.Test;

/**
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 *
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
 *
 * 注意你不能在买入股票前卖出股票。
 *
 * 示例 1:
 *
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
 * 示例 2:
 *
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 *
 */
public class Q121MaxProfit {
    /**
     * 1.定义动态规划问题 dp[i][j][s]表示第i天，最多允许交易j次的情况下，s状态下的利润
     *   0 <= i < len, 1 <= j <= K, s = {0, 1}, 0表示没持有，1表示持有
     * 2. 状态转移方程
     *  dp[i][k][0], 第i天空仓的收益
     *  (1) 第i-1天空仓,                dp[i-1][k][0]
     *  (2) 第i-1天持仓，第i天卖了       dp[i-1][k][1]+prices[i]
     *  dp[i][k][1]，第i天持仓的收益
     *  (1) 第i-1天持仓,                dp[i-1][k][1]
     *  (2) 第i-1天空仓，第i天买了       dp[i-1][k-1][0]-prices[i]
     *  3.边界条件
     *  dp[-1][k][0] = 0
     *  dp[-1][k][1] = -inf
     *  dp[i][0][0] = 0
     *  dp[i][0][1] = -inf
     *
     *  k = 1时
     *  dp[i][0] = Max(dp[i-1][0], dp[i-1][1]+prices[i])
     *  dp[i][1] = Max(dp[i-1][1], -prices[i])
     */
    public int maxProfit0(int[] prices) {
        int len = prices.length;
        if (len <= 1) return 0;
        int[][] dp = new int[len][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], 0 - prices[i]);
        }

        return dp[len - 1][0];
    }

    /**
     * dp空间数组，O(n) -> O(1)
     */
    public int maxProfit01(int[] prices) {
        int len = prices.length;
        if (len <= 1) return 0;
        int dpi0 = 0;
        int dpi1 = -prices[0];

        for (int i = 1; i < len; i++) {

            dpi0 = Math.max(dpi0, dpi1 + prices[i]);
            dpi1 = Math.max(dpi1, 0 - prices[i]);
        }

        return dpi0;
    }

    public int maxProfit(int[] prices) {
        int len = prices.length;
        if (len <= 1) {
            return 0;
        }
        int max = 0;
        for (int i=0; i<len; i++) {
            for (int j=i+1; j<len; j++) {
                max = Math.max(prices[j] - prices[i], max);
            }
        }

        return max;
    }
    public int maxProfit2(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int p : prices) {
            minPrice = Math.min(minPrice, p);
            if (p - minPrice > maxProfit) {
                maxProfit = p - maxProfit;
            }
        }
        return maxProfit;
    }

    @Test
    public void testCase01() {
        int [] input = {7,1,5,3,6,4};
        System.out.println(maxProfit01(input));
    }
}
