package microsoft;

import org.junit.Test;

/**
 * 一个环，有n个点, 每次只能走一步， 问从原点0出发，经过k步回到原点有多少种方法？
 * ex1：
 * n = 10, k = 1, 则从0出发只能到1或者9，不可能回到0，共0种走法
 *
 * ex2:
 * n = 10, k = 2, 则从0出发有4条路径:0->1->2, 0->1->0, 0->9->8, 0->9->0,其中有两条回到了0点，故一共有2种走法
 *
 * ex3:
 * n = 10, k = 3
 * 0->1->2->3
 * 0->1->2->1
 * 0->1->0->1
 * 0->1->0->9
 *
 * 0->9->8->7
 * 0->9->8->9
 * 0->9->0->9
 * 0->9->0->1
 */
public class KStep2Original {
    /**
     * n个点编号 0 ~ (n - 1)
     * 1. 定义动态规划问题，dp[i][j] 表示在i点走j步返回到原点的方法数,  0 <= i <= n - 1; 0 <= j <= k
     * 2. dp[i][j] = dp[(i-1+n)%n][j-1] + dp[(i+1)/n][j-1]
     * 3. dp[0][0] = 1
     *    dp[i][0] = 0, i > 0
     * 要求dp[0][k]
     */
    public int calcKStepOriginal(int n, int k) {
        int[][] dp = new int[n][k + 1];
        dp[0][0] = 1;
        for (int j = 1; j <= k; j++) {
            for (int i = 0; i < n; i++) {
                dp[i][j] = dp[(i - 1 + n) % n][j - 1] + dp[(i + 1) % n][j - 1];
            }
        }
        return dp[0][k];
    }

    @Test
    public void testCase01() {
        int n = 10;
        int k = 3;
        System.out.println(calcKStepOriginal(n, k));
    }
}
