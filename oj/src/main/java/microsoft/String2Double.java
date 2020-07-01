package microsoft;

import org.junit.Test;

/**
 * 将一个二进制数转换为十进制数，保证输入合法，可能溢出(溢出判断，值变小了)
 * ex1: -1.1
 * ex2: 1e1
 * ex3: 1.1e1
 */
public class String2Double {
    public double string2double(String str) {
        double a = 1e11;
        System.out.println();
        return 1;
    }

    @Test
    public void testCase01() {
        double a = 1e1;
        System.out.println(a);
    }
}
