package leetcode;

import org.junit.Test;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Q419BattleShip {
    class Tup{
        public int indexI;
        public int indexJ;

        public Tup(int indexI, int indexJ) {
            this.indexI = indexI;
            this.indexJ = indexJ;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Tup tup = (Tup) o;
            return indexI == tup.indexI &&
                    indexJ == tup.indexJ;
        }

        @Override
        public int hashCode() {
            return Objects.hash(indexI, indexJ);
        }

        @Override
        public String toString() {
            return "Tup{" +
                    "indexI=" + indexI +
                    ", indexJ=" + indexJ +
                    '}';
        }
    }

    public int countBattleships(char[][] board) {
        Set<Tup> set = new HashSet<>();
        int row = board.length;
        int col = board[0].length;
        int count = 0;

        for (int idx=0; idx<row; idx++) {
            for (int jdx=0;jdx<col; jdx++) {
                if (set.contains(new Tup(idx, jdx))) {
                    continue;
                }

                char ele = board[idx][jdx];
                if (ele == 'X') {
                    count += 1;
                    set.add(new Tup(idx, jdx));
                    int i=0;
                    while (i+1+idx<row && board[i+1+idx][jdx] == 'X') {
                        set.add(new Tup(i+1+idx, jdx));
                        i++;
                    }

                    int j=0;
                    while (j+1+jdx<col && board[idx][j+1+jdx] == 'X') {
                        set.add(new Tup(idx, j+1+jdx));
                        j++;
                    }
                }
            }
        }

        return count;
    }

    public int countBattleships2(char[][] board) {
        int row = board.length;
        int col = board[0].length;
        int count = 0;

        for (int i=0; i<row; i++) {
            for (int j=0;j<col; j++) {
                if (board[i][j] == 'X') {
                    count += 1;
                    dfs(i, j, board);
                }
            }
        }
        return count;
    }

    private void dfs(int i, int j, char[][] board) {
        int row = board.length;
        int col = board[0].length;
        if (i<0 || i>=row || j<0 || j>=col || board[i][j]!='X') {
            return;
        }
        board[i][j] = '*';
        dfs(i-1, j, board);
        dfs(i+1, j, board);
        dfs(i, j-1, board);
        dfs(i, j+1, board);
    }

    @Test
    public void testCase01() {
        char[][] input = {{'X','.','.','X'},{'.','.','.','X'},{'.','.','.','X'}};
        System.out.println(countBattleships2(input));
    }

    @Test
    public void testCase02() {
        char[][] input = {
                {'.','X','.','.','X'},
                {'.','X','.','.','X'},
                {'.','.','.','.','X'},
                {'X','.','X','X','.'},
                {'X','.','.','.','X'}};
        System.out.println(countBattleships2(input));
    }
}
