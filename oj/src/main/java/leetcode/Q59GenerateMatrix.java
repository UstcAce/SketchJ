package leetcode;

import org.junit.Test;

/**
 * 给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
 * 示例:
 * 输入: 3
 * 输出:
 * [
 *  [ 1, 2, 3 ],
 *  [ 8, 9, 4 ],
 *  [ 7, 6, 5 ]
 * ]
 *
 */
public class Q59GenerateMatrix {
    public int[][] generateMatrix(int n) {
        // 0: 右，1：下，2：左，3：上
        int direction = 0;
        int[][] res = new int[n][n];
        int val = 1;
        int row = 0;
        int col = 0;
        while (val <= n * n) {
            direction = direction % 4;
            res[row][col] = val;
            val++;
            if (direction == 0) {
                if (col + 1 < n && res[row][col + 1] == 0) {
                    col++;
                } else {
                    direction++;
                    row++;
                }
            } else if (direction == 1) {
                if (row + 1 < n && res[row + 1][col] == 0) {
                    row++;
                } else {
                    direction++;
                    col--;
                }
            } else if (direction == 2) {
                if (col - 1 >= 0 && res[row][col - 1] == 0) {
                    col--;
                } else {
                    direction++;
                    row--;
                }
            } else {
                if (row - 1 >= 0 && res[row - 1][col] == 0) {
                    row--;
                } else {
                    direction++;
                    col++;
                }
            }
        }

        return res;
    }

    @Test
    public void testCase01() {
        generateMatrix(5);
    }
}
