package leetcode;

/**
 * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 *
 * 每行中的整数从左到右按升序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 * 示例 1:
 * 输入:
 * matrix = [
 *   [1,   3,  5,  7],
 *   [10, 11, 16, 20],
 *   [23, 30, 34, 50]
 * ]
 * target = 3
 * 输出: true
 *
 * 示例 2:
 * 输入:
 * matrix = [
 *   [1,   3,  5,  7],
 *   [10, 11, 16, 20],
 *   [23, 30, 34, 50]
 * ]
 * target = 13
 * 输出: false
 *
 */
public class Q74SearchMatrix {
    /**
     * 注意到：从矩阵左下角起，往上数值减少，往下数值增加，于是便可以从左下角起始点开始寻找
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int rowNum = matrix.length;
        if (rowNum == 0) return false;
        int colNum = matrix[0].length;
        if (colNum == 0) return false;

        int i = rowNum - 1;
        int j = 0;
        while (i >= 0 && j <= colNum - 1) {
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] < target) {
                j++;
            } else {
                i--;
            }
        }
        return false;
    }
}
