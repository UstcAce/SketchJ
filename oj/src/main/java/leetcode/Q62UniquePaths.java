package leetcode;

/**
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 * 问总共有多少条不同的路径？
 *
 * 示例 1:
 * 输入: m = 3, n = 2
 * 输出: 3
 *
 * 解释:
 * 从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -> 向右 -> 向下
 * 2. 向右 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向右
 *
 * 示例 2:
 * 输入: m = 7, n = 3
 * 输出: 28
 *
 */
public class Q62UniquePaths {
    /**
     * 1. 定义动态规划求解问题，dp[i][j]从 (0,0) -> (i,j)总共有多少条不同的路径
     * 2. 状态转移方程，dp[i][j] = dp[i-1][j] + dp[i][j-1]
     * 3. 边界条件 dp[i][0] = 1, dp[0][j] = 1
     */
    public int uniquePaths(int m, int n) {
        if (m == 1 || n == 1) return 1;
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[m - 1][n - 1];
    }
}
