package leetcode;

/**
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * <p>
 * 示例 1:
 * 输入: [7,1,5,3,6,4]
 * 输出: 7
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
 * <p>
 * 示例 2:
 * 输入: [1,2,3,4,5]
 * 输出: 4
 * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
 *      因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * <p>
 * 示例 3:
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 */
public class Q122MaxProfit {
    /**
     * 1.定义动态规划问题 dp[i][j][s]表示第i天，最多允许交易j次的情况下，s状态下的利润
     * 0 <= i < len, 1 <= j <= K, s = {0, 1}, 0表示没持有，1表示持有
     * 2. 状态转移方程
     * dp[i][k][0], 第i天空仓的收益
     * (1) 第i-1天空仓,                dp[i-1][k][0]
     * (2) 第i-1天持仓，第i天卖了       dp[i-1][k][1]+prices[i]
     * dp[i][k][1]，第i天持仓的收益
     * (1) 第i-1天持仓,                dp[i-1][k][1]
     * (2) 第i-1天空仓，第i天买了       dp[i-1][k-1][0]-prices[i]
     * 3.边界条件
     * dp[-1][k][0] = 0
     * dp[-1][k][1] = -inf
     * dp[i][0][0] = 0
     * dp[i][0][1] = -inf
     * <p>
     * k = inf时
     * dp[i][0] = Max(dp[i-1][0], dp[i-1][1]+prices[i])
     * dp[i][1] = Max(dp[i-1][1], dp[i-1][0]-prices[i])
     */
    public int maxProfit(int[] prices) {
        int len = prices.length;
        if (len <= 1) return 0;

        int[][] dp = new int[len][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[len - 1][0];
    }

    public int maxProfit2(int[] prices) {
        int len = prices.length;
        if (len <= 1) return 0;

        int dpi0 = 0;
        int dpi1 = -prices[0];
        for (int i = 1; i < len; i++) {
            int temp = dpi0;
            dpi0 = Math.max(dpi0, dpi1 + prices[i]);
            dpi1 = Math.max(dpi1, temp - prices[i]);
        }
        return dpi0;
    }
}