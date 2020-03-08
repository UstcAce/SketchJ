package leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 *
 * 示例 1:
 *
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3
 * 解释: 11 = 5 + 5 + 1
 * 示例 2:
 *
 * 输入: coins = [2], amount = 3
 * 输出: -1
 * 说明:
 * 你可以认为每种硬币的数量是无限的。
 *
 */
public class Q322CoinChange {

    /**
     * 解法1：动态规划
     * 1. 定义动态规划求解问题 dp[i]表示兑换i金额钱所需的最少硬币个数
     * 2. 状态转移方程:
     * dp[i] = dp[i-coins[k]] + 1, min(dp[i-coins[k]]), 0<=k<coins.length
     * 3. 边界条件
     *  dp[0]=0
     */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        for (int i=1; i<=amount; i++) {
            int localMin = Integer.MAX_VALUE;
            for (int item : coins) {
                if (i-item >= 0 && dp[i-item] != -1) {
                    localMin = Math.min(dp[i-item]+1, localMin);
                }
            }
            dp[i] = localMin == Integer.MAX_VALUE ? -1 : localMin;
        }
        return dp[amount];
    }

    /**
     * 解法2：递归
     */
    private Map<Integer, Integer> map = new HashMap<>();
    public int coinChange2(int[] coins, int amount) {
        if (map.containsKey(amount)) {
            return map.get(amount);
        }

        if (amount == 0) {
            return 0;
        }

        if (amount < 0) {
            return -1;
        }

        int res = Integer.MAX_VALUE;
        for (int item:coins) {
            int subPro = coinChange2(coins, amount-item);
            if (subPro != -1) {
                res = Math.min(res, subPro+1);
            }
        }
        res = res == Integer.MAX_VALUE ? -1 : res;
        map.put(amount, res);
        return res;
    }


    @Test
    public void testCase01() {
        int[] input = {1, 2, 5};
        int amount = 11;
        System.out.println(coinChange(input, amount));
    }

    @Test
    public void testCase02() {
        int[] input = {2};
        int amount = 3;
        System.out.println(coinChange(input, amount));
    }

    @Test
    public void testCase03() {
        int[] input = {83,186,408,419};
        int amount = 6249;
        System.out.println(coinChange2(input, amount));
    }

    @Test
    public void testCase04() {
        int[] input = {1, 2, 4};
        int amount = 3;
        System.out.println(coinChange(input, amount));
    }
}
