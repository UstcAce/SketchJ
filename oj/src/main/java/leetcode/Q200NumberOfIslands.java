package leetcode;

/**
 * 给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。一个岛被水包围，
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
    /**
     * DFS,走到一个是陆地的点，DPS把它附近的陆地点置为非陆地
     */
    public int numIslands(char[][] grid) {
        int row = grid.length;
        if (row == 0) {
            return 0;
        }
        int col = grid[0].length;
        if (col == 0) {
            return 0;
        }

        int count = 0;
        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                char c = grid[i][j];
                if (c == '1') {
                    count += 1;
                    dfs(i, j, grid);
                }
            }
        }
        return count;
    }

    private void dfs(int i, int j, char[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        if (i<0 || i>=row || j<0 || j>=col || grid[i][j]!='1') {
            return;
        }
        grid[i][j] = '2';
        dfs(i-1, j, grid);
        dfs(i+1, j, grid);
        dfs(i, j-1, grid);
        dfs(i, j+1, grid);
    }

}
