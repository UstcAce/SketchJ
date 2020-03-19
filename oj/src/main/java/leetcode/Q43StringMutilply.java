package leetcode;

import org.junit.Test;

/**
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 *
 * 示例 1:
 *
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 示例 2:
 *
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 * 说明：
 *
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 */
public class Q43StringMutilply {
    public String multiply(String num1, String num2) {
        String res = "0";
        String min = num1.length() > num2.length() ? num2 : num1;
        String max = num1.length() > num2.length() ? num1 : num2;

        for (int i = min.length() - 1; i >= 0; i--) {
            String oneDigit = min.substring(i, i + 1);
            String oneMultiplyRes = multiplyOneDigit(max, oneDigit, min.length() - i);
            res = plus(res, oneMultiplyRes);
        }

        return res;
    }

    private String multiplyOneDigit(String num, String oneDigit, int zeroNum) {
        int digit = Integer.parseInt(oneDigit);
        if (digit == 0) return "0";

        StringBuilder res = new StringBuilder();
        StringBuilder sb = new StringBuilder(num).reverse();
        int i = 0;
        int overflow = 0;
        while (i < sb.length() || overflow > 0) {
            int d = 0;
            if (i < sb.length()) {
                d = Integer.parseInt(sb.substring(i, i + 1));
            }
            int val = (d * digit + overflow) % 10;
            overflow = (d * digit + overflow) / 10;
            res.append(val);
            i++;
        }

        StringBuilder zeros = new StringBuilder();

        for (int k = 1; k < zeroNum; k++) {
            zeros.append("0");
        }

        return res.reverse().toString() + zeros;
    }

    private String plus(String num1, String num2) {
        StringBuilder res = new StringBuilder();
        int len1 = num1.length();
        int len2 = num2.length();
        StringBuilder sb1 = new StringBuilder(num1).reverse();
        StringBuilder sb2 = new StringBuilder(num2).reverse();
        int i = 0;
        int j = 0;
        int flag = 0;
        while (i < len1 || j < len2 || flag != 0) {
            int d1 = 0;
            int d2 = 0;

            if (i < len1) {
                d1 = Integer.parseInt(sb1.substring(i, i + 1));
                i++;
            }
            if (j < len2) {
                d2 = Integer.parseInt(sb2.substring(j ,j + 1));
                j++;
            }

            int d = (d1 + d2 + flag) % 10;
            flag = (d1 + d2 + flag) >= 10 ? 1 : 0;
            res.append(d);
        }

        return res.reverse().toString();
    }

    @Test
    public void testCase01() {
        String num1 = "1234";
        String num2 = "0";
        System.out.println(plus(num1, num2));
    }

    @Test
    public void testCase02() {
        String num1 = "1234";
        String num2 = "9";
        System.out.println(multiplyOneDigit(num1, num2, 2));
    }

    @Test
    public void testCase03() {
        String num1 = "2";
        String num2 = "3";
        System.out.println(multiply(num1, num2));
    }
}
