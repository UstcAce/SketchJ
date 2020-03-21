package leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数，将其转为罗马数字。输入确保在 1 到 3999 的范围内。
 */
public class Q12Int2Roman {
    public String intToRoman(int num) {
        String[][] table = initTable();
        Map<Integer, Integer> map = calcKeyMap();

        StringBuilder strNum = new StringBuilder().append(num);
        int len = strNum.length();

        StringBuilder res = new StringBuilder();

        for (int i = 0; i < len; i++) {
            int row = len - i - 1;
            int col = Integer.parseInt(strNum.substring(i, i + 1));
            if (col == 1 || col == 4 || col == 5 || col == 9) {
                res.append(table[row][map.get(col)]);
            } else if (col >= 2 && col <= 3){
                for (int j = 0; j < col; j++) {
                    res.append(table[row][0]);
                }
            } else if (col == 0) {
                // do nothing
                continue;
            }  else {
                res.append(table[row][2]);
                for (int j = 0; j < col - 5; j++) {
                    res.append(table[row][0]);
                }
            }
        }

        return res.toString();
    }

    private String[][] initTable() {
        String[][] table = new String[4][4];
        table[0][0] = "I";
        table[0][1] = "IV";
        table[0][2] = "V";
        table[0][3] = "IX";
        table[1][0] = "X";
        table[1][1] = "XL";
        table[1][2] = "L";
        table[1][3] = "XC";
        table[2][0] = "C";
        table[2][1] = "CD";
        table[2][2] = "D";
        table[2][3] = "CM";
        table[3][0] = "M";

        return table;
    }

    private Map<Integer, Integer> calcKeyMap() {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 0);
        map.put(4, 1);
        map.put(5, 2);
        map.put(9, 3);
        return map;
    }

    @Test
    public void testCase01() {
        int input = 1000;
        String res = intToRoman(input);
        System.out.println(res);
    }
}
