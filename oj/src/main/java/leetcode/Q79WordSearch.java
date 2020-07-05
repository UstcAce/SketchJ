package leetcode;

import org.junit.Test;

/**
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
 * 同一个单元格内的字母不允许被重复使用。
 *
 * <p>示例: board = [ ['A','B','C','E'], ['S','F','C','S'], ['A','D','E','E'] ]
 *
 * <p>给定 word = "ABCCED", 返回 true 给定 word = "SEE", 返回 true 给定 word = "ABCB", 返回 false
 *
 * 提示：
 * <board 和 word 中只包含大写和小写英文字母。
 * 1 <= board.length <= 200 1 <= board[i].length <= 200 1 <=word.length <= 10^3
 */
public class Q79WordSearch {
    // 上下左右
    private int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private boolean[][] visited;
    private int rowNum;
    private int colNum;


    public boolean exist(char[][] board, String word) {
        rowNum = board.length;
        colNum = board[0].length;
        visited = new boolean[rowNum][colNum];

        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                if (board[i][j] == word.charAt(0)) {
                    boolean b = dfsCheck(board, i, j, word, 0);
                    if (b) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean dfsCheck(char[][] board, int row, int col, String word, int pos) {
        if (word.charAt(pos) == board[row][col]) {
            visited[row][col] = true;
            if (pos == word.length() - 1) {
                return true;
            }
            for (int i = 0; i < directions.length; i++) {
                int[] d = directions[i];
                int newRow = row + d[0];
                int newCol = col + d[1];
                if (isValid(newRow, newCol) && !visited[newRow][newCol]) {
                    boolean b = dfsCheck(board, newRow, newCol, word, pos + 1);
                    if (b) {
                        return true;
                    }
                }
            }
            visited[row][col] = false;
            return false;
        } else {
            return false;
        }
    }

    private boolean isValid(int row, int col) {
        return row >= 0 && row < rowNum && col >= 0 && col < colNum;
    }

    @Test
    public void testCase01() {
        char[][] borad = {
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
        };

        String input = "ABCEC";

        boolean res = exist(borad, input);
        System.out.println(res);
    }

    @Test
    public void testCase02() {
        char[][] borad = {
                {'A'},
        };

        String input = "A";

        boolean res = exist(borad, input);
        System.out.println(res);
    }
}
