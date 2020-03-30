package leetcode;

/**
 * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 *
 *  你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * 示例:
 *
 * 输入: [1,2,3,0,2]
 * 输出: 3
 * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 *
 */
public class Q309MaxProfit {
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
     * k = inf且冷冻期为1天时
     * dp[i][0] = Max(dp[i-1][0], dp[i-1][1]+prices[i])
     * dp[i][1] = Max(dp[i-1][1], dp[i-2][0]-prices[i])
     */
    public int maxProfit(int[] prices) {
        int len = prices.length;
        if (len < 2) return 0;
        if (len == 2) return Math.max(prices[1] - prices[0], 0);

        int[][] dp = new int[len][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[1][0] = Math.max(dp[0][0], dp[0][1] + prices[1]);
        dp[1][1] = Math.max(-prices[1], -prices[0]);

        for (int i = 2; i < len; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 2][0] - prices[i]);
        }
        return dp[len - 1][0];
    }
}
