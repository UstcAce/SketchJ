package leetcode;

import org.junit.Test;

/**
 * 给定一个正整数，返回它在 Excel 表中相对应的列名称。
 *
 * 例如，
 *
 *     1 -> A
 *     2 -> B
 *     3 -> C
 *     ...
 *     26 -> Z
 *     27 -> AA
 *     28 -> AB
 *     ...
 */
public class Q168ExcelColumnTitle {
    /**
     * 注意对应关系中是从1开始的，因此我们预先对n做处理，然后进行%26和/26
     */
    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        while (n>0) {
            int remain = (n-1)%26;
            char c = (char)('A' + remain);
            sb.append(c);
            n = (n-1)/26;
        }
        return sb.reverse().toString();
    }

    @Test
    public void testCase01() {
        int input = 701;
        System.out.println(convertToTitle(input));
    }
    @Test
    public void testCase02() {
        int input = 52;
        System.out.println(convertToTitle(input));
    }

}
