package leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。
 *
 * 一个岛被水包围，
 * 并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。你可以假设网格的四个边均被水包围。
 *
 * 示例 1:
 *
 * 输入:
 * 11110
 * 11010
 * 11000
 * 00000
 *
 * 输出: 1
 * 示例 2:
 *
 * 输入:
 * 11000
 * 11000
 * 00100
 * 00011
 *
 * 输出: 3
 */
public class Q200NumberOfIslands {
    private int rowNum;

    private int colNum;

    private int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int numIslands(char[][] grid) {
        rowNum = grid.length;
        if (rowNum == 0) {
            return 0;
        }
        colNum = grid[0].length;
        if (colNum == 0) {
            return 0;
        }

        int count = 0;
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                if (grid[i][j] == '1') {
                    dfsCalc(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private void dfsCalc(char[][] grid, int row, int col) {
        if (row < 0 || row >= rowNum || col < 0 || col >= colNum || grid[row][col] == '0') {
            return;
        }
        grid[row][col] = '0';
        for (int i = 0; i < directions.length; i++) {
            int[] offset = directions[i];
            dfsCalc(grid, row + offset[0], col + offset[1]);
        }
    }

    @Test
    public void testCase00() {
        char[][] grid = {{}};
        int excepted = 0;
        int res = numIslands(grid);
        Assert.assertEquals(excepted, res);
    }


    @Test
    public void testCase01() {
        char[][] grid = {{'0'}};
        int excepted = 0;
        int res = numIslands(grid);
        Assert.assertEquals(excepted, res);
    }

    @Test
    public void testCase02() {
        char[][] grid = {{'1'}};
        int excepted = 1;
        int res = numIslands(grid);
        Assert.assertEquals(excepted, res);
    }

    @Test
    public void testCase03() {
        char[][] grid = {
                {'1', '1', '0', '1', '1'},
                {'1', '1', '0', '1', '1'},
                {'1', '1', '0', '1', '1'},
                {'1', '1', '0', '1', '1'}};
        int excepted = 2;
        int res = numIslands(grid);
        Assert.assertEquals(excepted, res);
    }

    @Test
    public void testCase04() {
        char[][] grid = {
                {'1', '1', '0', '1', '1'},
                {'1', '1', '0', '1', '1'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '1'}};
        int excepted = 3;
        int res = numIslands(grid);
        Assert.assertEquals(excepted, res);
    }
}
