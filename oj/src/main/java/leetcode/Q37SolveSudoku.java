package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Q37SolveSudoku {
    int rowNum;
    int colNum;
    char[][] board;

    public void solveSudoku(char[][] board) {
        rowNum = board.length;
        colNum = board[0].length;
        this.board = board;

        List<Integer> rowList = new ArrayList<>();
        List<Integer> colList = new ArrayList<>();

        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                if (board[i][j] == '.') {
                    rowList.add(i);
                    colList.add(j);
                }
            }
        }
        traceBackSolve(rowList, colList, 0);
    }

    private boolean traceBackSolve(List<Integer> rowList, List<Integer> colList, int pos) {
        if (pos == rowList.size()) return true;
        int row = rowList.get(pos);
        int col = colList.get(pos);
        List<Character> candidate = getValidNum(row, col, board);
        if (candidate.isEmpty()) return false;

        for (char c : candidate) {
            board[row][col] = c;
            if (traceBackSolve(rowList, colList, pos + 1)) {
                return true;
            }
        }
        board[row][col] = '.';

        return false;
    }

    private List<Character> getValidNum(int row, int col, char[][] board) {
        int rowNum = board.length;
        int colNum = board[0].length;
        Set<Character> existing = new HashSet<>();
        // 纵向和横向已有数值
        for (int i = 0; i < rowNum; i++) {
            if (board[i][col] != '.') {
                existing.add(board[i][col]);
            }
            if (board[row][i] != '.') {
                existing.add(board[row][i]);
            }
        }

        // 3 * 3小宫格已有数值
        int startRow = row / 3 * 3;
        int startCol = col / 3 * 3;
        for (int i = startRow; i < rowNum / 3 + startRow; i++) {
            for (int j = startCol; j < colNum / 3 + startCol; j++) {
                if (board[i][j] != '.') {
                    existing.add(board[i][j]);
                }
            }
        }

        List<Character> res = new ArrayList<>();
        for (char c = '1'; c <= '9'; c++) {
            if (!existing.contains(c)) {
                res.add(c);
            }
        }
        return res;
    }

    @Test
    public void testCase01() {
        char[][] board = {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}};

    }
}
