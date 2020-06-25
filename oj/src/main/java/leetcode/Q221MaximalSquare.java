package leetcode;

/**
 * 在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。
 *
 *  示例:
 * 输入:
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 *
 * 输出: 4
 *
 */
public class Q221MaximalSquare {
    /**
     * 1. 定义动态规划求解问题 dp[i][j]表示以matrix[i][j]为右下角的只包含1的最大正方形的长度
     * 2. 状态转移方程：dp[i][j]
     * (1) matrix[i][j] == '0':
     *     dp[i][j] = 0
     * (2) matrix[i][j] == '1':
     *     dp[i][j] = min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1]) + 1
     * 3. 边界条件：
     *     dp[i][0],dp[0][j] = 相应位置为1 ? 1 : 0
     */
    public int maximalSquare(char[][] matrix) {
        int row = matrix.length;
        if (row == 0) return 0;
        int col = matrix[0].length;
        if (col == 0) return 0;

        int[][] dp = new int[row][col];
        int maxEdge = 0;

        for (int i = 0; i < row; i++) {
            dp[i][0] = matrix[i][0] == '1' ? 1 : 0;
            maxEdge = Math.max(maxEdge, dp[i][0]);
        }

        for (int j = 0; j < col; j++) {
            dp[0][j] = matrix[0][j] == '1' ? 1 : 0;
            maxEdge = Math.max(maxEdge, dp[0][j]);
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (matrix[i][j] == '1') {
                    int localMin = Math.min(dp[i - 1][j], dp[i][j - 1]);
                    localMin = Math.min(localMin, dp[i - 1][j - 1]);
                    dp[i][j] = localMin + 1;
                    maxEdge = Math.max(dp[i][j], maxEdge);
                }
            }
        }

        return maxEdge * maxEdge;
    }
}
