package leetcode;

import org.junit.Assert;
import org.junit.Test;

public class Q6ZigzagConversion {
    public String convert(String s, int numRows) {
        int len = s.length();
        if (len <= 1 || numRows <= 1) return s;

        String[][] table = new String[numRows][len];

        boolean isDown = true;
        int row = 0;
        int col = 0;
        int i = 0;
        while (i < len) {
            String letter = s.substring(i, i + 1);
            table[row][col] = letter;

            if (isDown) {
                if (row + 1 == numRows) {
                    isDown = false;
                    row--;
                    col++;
                } else {
                    row++;
                }
            } else {
                if (row - 1 < 0) {
                    isDown = true;
                    row++;
                } else {
                    row--;
                    col++;
                }
            }

            i++;
        }

        return combineResult(table);
    }

    private String combineResult(String[][] table) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                String local = table[i][j] == null ? "" : table[i][j];
                sb.append(local);
            }
        }

        return sb.toString();
    }

    @Test
    public void testCase01() {
        String input = "LEETCODEISHIRING";
        int numRows = 3;
        String result = convert(input, numRows);
        String standardOutput = "LCIRETOESIIGEDHN";
        Assert.assertEquals(standardOutput, result);
        System.out.println(result);
    }

    @Test
    public void testCase02() {
        String input = "LEETCODEISHIRING";
        int numRows = 4;
        String result = convert(input, numRows);
        String standardOutput = "LDREOEIIECIHNTSG";
        Assert.assertEquals(standardOutput, result);
        System.out.println(result);
    }
}
