package leetcode;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * 判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。
 *
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 */
public class Q36ValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        int len = board.length;
        boolean[][] table = new boolean[len][len];

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (!table[i][j]) {
                    boolean isValid = isValidIndex(i, j, board);
                    if (!isValid)  {
                        return false;
                    } else {
                        setValidTable(i, j, table);
                    }
                }

                if (i % 3 == 0 && j % 3 == 0 && !checkSubArea(i, j, board)) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * 验证3*3x小格子是否合法
     * @param i     左上角行号
     * @param j     左上角列号
     * @param board 数独矩阵
     * @return      3*3小格子是否合法
     */
    private boolean checkSubArea(int i, int j, char[][] board) {
        Set<Character> set = new HashSet<>();
        for (int k = i; k < i + 3; k++) {
            for (int l = j; l < j + 3; l++) {
                char c = board[k][l];
                if (c != '.') {
                    if (set.contains(c)) {
                        return false;
                    } else {
                        set.add(c);
                    }
                }
            }
        }
        return true;
    }

    private void setValidTable(int i, int j, boolean[][] table) {
        int len = table.length;
        for (int k = 0; k < len; k++) {
            table[k][j] = true;
            table[i][k] = true;
        }
    }

    private boolean isValidIndex(int i, int j, char[][] board) {
        int len = board.length;
        Set<Character> col = new HashSet<>();
        Set<Character> row = new HashSet<>();

        for (int k = 0; k < len; k++) {
            char r = board[i][k];
            if (r != '.') {
                if (col.contains(r)) {
                    return false;
                } else {
                    col.add(r);
                }
            }

            char c = board[k][j];
            if (c != '.') {
                if (row.contains(c)) {
                    return false;
                } else {
                    row.add(c);
                }
            }
        }

        return true;
    }

    @Test
    public void testCase01() {
        char[][] input = {
                {'.','.','4','.','.','.','6','3','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'5','.','.','.','.','.','.','9','.'},
                {'.','.','.','1','6','.','.','.','.'},
                {'4','.','3','.','.','.','.','.','1'},
                {'.','.','.','7','.','.','.','.','.'},
                {'.','.','.','5','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.'}
        };
        System.out.println(isValidSudoku(input));
    }
}
