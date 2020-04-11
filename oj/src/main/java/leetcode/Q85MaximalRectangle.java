package leetcode;

import org.junit.Test;

/**
 * 给定一个仅包含 0 和 1 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 * 示例:
 *
 * 输入:
 * [
 *   ["1","0","1","0","0"],
 *   ["1","0","1","1","1"],
 *   ["1","1","1","1","1"],
 *   ["1","0","0","1","0"]
 * ]
 * 输出: 6
 */
public class Q85MaximalRectangle {
    /**
     * 1. 定义动态规划条件dp[i][j]表示以(0,0)为左上角，以matrix[i][j]为右下角的矩阵中的最大只包含1的矩形面积
     * 2. 状态转移方程：dp[i][j]
     * （1）matrix[i][j] = 0, dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1])
     *  (2) matrix[i][j] = 1，dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1], 确定以matrix[i][j]为右下角的矩形面积)
     *
     */
    public int maximalRectangle(char[][] matrix) {
        int rowNum = matrix.length;
        if (rowNum == 0) return 0;
        int colNum = matrix[0].length;
        if (colNum == 0) return 0;

        int[][] dp = new int[rowNum][colNum];

        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                if (i == 0 && j == 0) {
                    dp[0][0] = matrix[0][0] == '1' ? 1 : 0;
                } else if (i == 0) {
                    dp[i][j] = matrix[i][j] == '1' ? Math.max(dp[i][j - 1], getGivenMatrixArea(matrix, i, j)) : dp[i][j - 1];
                } else if (j == 0) {
                    dp[i][j] = matrix[i][j] == '1' ?  Math.max(dp[i - 1][j], getGivenMatrixArea(matrix, i, j)) : dp[i - 1][j];
                } else {
                    int maxLocal = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    dp[i][j] = matrix[i][j] == '1' ? Math.max(maxLocal, getGivenMatrixArea(matrix, i, j)) : maxLocal;
                }
            }
        }

        return dp[rowNum - 1][colNum - 1];
    }

    /**
     * 确定以matrix[row][col]为右下角的最大矩形面积
     */
    private int getGivenMatrixArea(char[][] matrix, int row, int col) {
        int rowAboveLen = 0;
        int colLeftLen = 0;

        int idx = row;
        while (idx >= 0 && matrix[idx][col] == '1') {
            rowAboveLen++;
            idx--;
        }

        idx = col;
        while (idx >= 0 && matrix[row][idx] == '1') {
            colLeftLen++;
            idx--;
        }

        boolean[][] validMatrix = new boolean[row + 1][col + 1];
        int max = 1;
        for (int i = row; i >= row - rowAboveLen + 1; i--) {
            for (int j = col; j >= col - colLeftLen + 1; j--) {
                if (i == row && j == col) {
                    validMatrix[i][j] = true;
                } else if (i == row) {
                    validMatrix[i][j] = matrix[i][j] == '1' && validMatrix[i][j + 1];
                } else if (j == col) {
                    validMatrix[i][j] = matrix[i][j] == '1' && validMatrix[i + 1][j];
                } else {
                    validMatrix[i][j] = matrix[i][j] == '1' && validMatrix[i + 1][j] && validMatrix[i][j + 1];
                }

                if (validMatrix[i][j]) {
                    max = Math.max(max, (row - i + 1) * (col - j + 1));
                } else {
                    break;
                }
            }
        }
        return max;
    }

    @Test
    public void testCase00() {
        char[][] input = {
                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'}
        };
        System.out.println(getGivenMatrixArea(input, 2, 2));
    }

    @Test
    public void testCase01() {
        char[][] input = {
                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'}
        };
        System.out.println(maximalRectangle(input));
    }

    @Test
    public void testCase02() {
        char[][] input = {
                {'1','0','1','1','1'},
                {'0','1','0','1','0'},
                {'1','1','0','1','1'},
                {'1','1','0','1','1'},
                {'0','1','1','1','1'}
        };
        System.out.println(getGivenMatrixArea(input, 4, 3));
    }

    @Test
    public void testCase03() {
        char[][] input = {
                {'1','0','1','1','1'},
                {'0','1','0','1','0'},
                {'1','1','0','1','1'},
                {'1','1','0','1','1'},
                {'0','1','1','1','1'}
        };
        System.out.println(maximalRectangle(input));
    }

}
