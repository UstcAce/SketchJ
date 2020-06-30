package microsoft;

import org.junit.Test;

/**
 * 将一个二进制数转换为十进制数，保证输入合法，可能溢出(溢出判断，值变小了)
 * ex1: 1011 -> 11
 * ex2: -1011 -> -11
 */
public class Binary2Decimal {
    public Long binary2Decimal(String s) {
        boolean isPositive = true;
        int endIdx = 0;
        if (s.charAt(0) == '-') {
            isPositive = false;
            endIdx = 1;
        }
        long result = 0;
        int powCount = 0;
        int i = s.length() - 1;
        while (i >= endIdx) {
            long temp = (long) Math.pow(2, powCount) * Integer.parseInt(s.substring(i, i + 1));
            if (temp + result < result) {
                throw new UnsupportedOperationException("Input binary number exceed Long.MAX_VALUE or Long.MIN_VALUE!");
            } else {
                result += temp;
            }
            i--;
            powCount++;
        }
        result = isPositive ? result : -result;
        return result;
    }

    @Test
    public void testCase01() {
        String input = "1011";
        System.out.println(binary2Decimal(input));
    }

    @Test
    public void testCase02() {
        String input = "-1011";
        System.out.println(binary2Decimal(input));
    }

    @Test
    public void testCase03() {
        String input = "-00111";
        System.out.println(binary2Decimal(input));
    }

    @Test
    public void testCase04() {
        String input = Long.toString(Long.MAX_VALUE, 2) + "1";
        System.out.println(binary2Decimal(input));
    }
}
