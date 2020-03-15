package leetcode;

import org.junit.Test;

/**
 * 给定一个包含了一些 0 和 1的非空二维数组 grid , 一个 岛屿 是由四个方向 (水平或垂直) 的 1 (代表土地) 构成的组合。你可以假设二维矩阵的四个边缘都被水包围着。
 *
 * 找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为0。)
 *
 * 示例 1:
 *
 * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,1,1,0,1,0,0,0,0,0,0,0,0],
 *  [0,1,0,0,1,1,0,0,1,0,1,0,0],
 *  [0,1,0,0,1,1,0,0,1,1,1,0,0],
 *  [0,0,0,0,0,0,0,0,0,0,1,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * 对于上面这个给定矩阵应返回 6。注意答案不应该是11，因为岛屿只能包含水平或垂直的四个方向的‘1’。
 *
 * 示例 2:
 *
 * [[0,0,0,0,0,0,0,0]]
 * 对于上面这个给定的矩阵, 返回 0。
 *
 * 注意: 给定的矩阵grid 的长度和宽度都不超过 50。
 *
 */
public class Q695MaxAreaOfIsland {
    private int area;

    private int row;

    private int col;

    public int maxAreaOfIsland(int[][] grid) {
        area = 0;
        row = grid.length;
        col = grid[0].length;

        for (int i = 0; i < row; i ++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    int oneArea = dfs(grid, i, j);
                    area = Math.max(oneArea, area);
                }
            }
        }

        return area;
    }

    private int dfs(int[][] grid, int i, int j) {
        int localArea = 0;
        if (i < 0 || i >= row || j < 0 || j >= col || grid[i][j] != 1) {
            return 0;
        }

        localArea += 1;
        grid[i][j] = 0;
        localArea = localArea + dfs(grid, i-1, j) + dfs(grid, i+1, j) + dfs(grid, i, j-1) + dfs(grid, i, j+1);

        return localArea;
    }

    /**
     * input:
     * [
     * [1,1,0,0,0],
     * [1,1,0,0,0],
     * [0,0,0,1,1],
     * [0,0,0,1,1]
     * ]
     *
     * output: 4
     */
    @Test
    public void testCase01() {

    }
}
