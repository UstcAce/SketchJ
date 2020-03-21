package leetcode;

import org.junit.Test;

/**
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 *
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 *
 *  
 *
 * 示例:
 *
 * board =
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A','D','E','E']
 * ]
 *
 * 给定 word = "ABCCED", 返回 true
 * 给定 word = "SEE", 返回 true
 * 给定 word = "ABCB", 返回 false
 *
 */
public class Q79WordSearch {

    private boolean[][] visit;

    private int rowNum;

    private int colNum;

    private int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public boolean exist(char[][] board, String word) {
        rowNum = board.length;
        colNum = board[0].length;

        visit = new boolean[rowNum][colNum];

        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                boolean localRes = traceBackExist(new StringBuilder(), 0, board, i, j, word);
                if (localRes) return true;
            }
        }

        return false;
    }

    private boolean traceBackExist(StringBuilder tmp, int pos, char[][] board, int row, int col, String word) {
        if (tmp.toString().equals(word)) return true;

        if (!isValid(row, col)) return false;

        if (word.charAt(pos) == board[row][col]) {
            tmp.append(board[row][col]);
            visit[row][col] = true;
            for (int i = 0; i < directions.length; i++) {
                int newRow = row + directions[i][0];
                int newCol = col + directions[i][1];
                if (isValid(newRow, newCol) && visit[newRow][newCol]) {
                    continue;
                }
                boolean localRes = traceBackExist(tmp, pos + 1, board, newRow, newCol, word);
                if (localRes) {
                    return true;
                }
            }
            visit[row][col] = false;
            tmp.deleteCharAt(tmp.length() - 1);
        }

        return false;
    }

    private boolean isValid(int i, int j) {
        return i >= 0 && i < rowNum && j >= 0 && j < colNum;
    }

    @Test
    public void testCase01() {
        char[][] borad = {
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
        };

        String input = "ADEA";

        boolean res = exist(borad, input);
        System.out.println(res);
    }
}
