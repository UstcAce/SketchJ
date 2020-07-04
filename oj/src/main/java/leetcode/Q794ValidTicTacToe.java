package leetcode;

import org.junit.Test;

/**
 * 用字符串数组作为井字游戏的游戏板 board。当且仅当在井字游戏过程中，玩家有可能将字符放置成游戏板所显示的状态时，才返回 true。
 * 该游戏板是一个 3 x 3 数组，由字符 " "，"X" 和 "O" 组成。字符 " " 代表一个空位。
 *
 * 以下是井字游戏的规则：
 *
 * <玩家轮流将字符放入空位（" "）中。
 * 第一个玩家总是放字符 “X”，且第二个玩家总是放字符 “O”。 “X” 和 “O” 只允许放置在空位中，不允许对已放有字符的位置进行填充。
 * 当有 3个相同（且非空）的字符填充任何行、列或对角线时，游戏结束。
 * 当所有位置非空时，也算为游戏结束。 如果游戏结束，玩家不允许再放置字符。
 *
 * 1. X_num == O_num || X_num == (O_num + 1)
 * 2. 两个玩家不可能同时赢
 * 3. X落子的情况下，O不能赢；O落子的情况下，X不能赢
 */
public class Q794ValidTicTacToe {
    public boolean validTicTacToe(String[] board) {
        char[][] table = initTable(board);
        int count1 = calcCharNum(table, 'X');
        int count2 = calcCharNum(table, 'O');
        if (count1 != count2 && count1 != count2 + 1) {
            return false;
        }
        boolean win1 = isPlayWin(table, 'X', count1);
        boolean win2 = isPlayWin(table, 'O', count2);
        if (win1 && win2) {
            return false;
        }

        if (count1 == count2 && win2) {
            return false;
        }
        if (count1 == count2 + 1 && win1) {
            return false;
        }
        return true;
    }

    private char[][] initTable(String[] board) {
        char[][] table = new char[3][3];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < 3; j++) {
                table[i][j] = board[i].charAt(j);
            }
        }
        return table;
    }

    private int calcCharNum(char[][] table, char ch) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (table[i][j] == ch) {
                    count++;
                }
            }
        }
        return count;
    }

    private boolean isPlayWin(char[][] table, char ch, int count) {
        if (count < 3) {
            return false;
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (table[i][j] == ch && isValid(table, ch, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isValid(char[][] table, char ch, int row, int col) {
        boolean rowValid = true;
        boolean colValid = true;
        boolean diagonal1 = true;
        boolean diagonal2 = true;

        for (int i = 0; i < 3; i++) {
            rowValid = rowValid && table[i][col] == ch;
            colValid = colValid && table[row][i] == ch;
            if (row == col) {
                diagonal1 = diagonal1 && table[i][i] == ch;
            } else {
                diagonal1 = false;
            }
            if (row + col == 2) {
                diagonal2 = diagonal2 && table[i][2 - i] == ch;
            } else {
                diagonal2 = false;
            }
        }
        return rowValid || colValid || diagonal1 || diagonal2;
    }

    @Test
    public void testCase01() {
        String[] input = {"XOX", "O O", "XOX"};
        System.out.println(validTicTacToe(input));
    }
}
