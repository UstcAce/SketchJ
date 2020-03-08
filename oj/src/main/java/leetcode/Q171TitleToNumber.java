package leetcode;

import org.junit.Test;

/**
 * 给定一个Excel表格中的列名称，返回其相应的列序号。
 *
 * 例如，
 *
 *     A -> 1
 *     B -> 2
 *     C -> 3
 *     ...
 *     Z -> 26
 *     AA -> 27
 *     AB -> 28
 *     ...
 */
public class Q171TitleToNumber {
    public int titleToNumber(String s) {
        s = new StringBuilder(s).reverse().toString();
        if (s.isEmpty()) {
            return 0;
        }
        int len = s.length();
        int sum = 0;

        for (int i=0; i<len; i++) {
            char c = s.charAt(i);
            sum += (c - 'A' + 1)*Math.pow(26, i);
        }

        return sum;
    }

    @Test
    public void testCase01() {
        System.out.println(titleToNumber("AA"));
    }

    @Test
    public void testCase02() {
        System.out.println(titleToNumber("ZY"));
    }
}
