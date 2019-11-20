import org.junit.Test;

import java.util.Arrays;

/**
 * 求出1~13的整数中1出现的次数,并算出100~1300的整数中1出现的次数？为此他特别数了一下1~13中包含1的数字有1、10、11、12、13
 * 因此共出现6次,但是对于后面问题他就没辙了。ACMer希望你们帮帮他,并把问题更加普遍化,可以很快的求出任意非负整数区间中1出现
 * 的次数（从1 到 n 中1出现的次数）。
 */
public class Q3NumberOf1Between1AndN {
    /**
     * 方法1：暴力遍历求解
     */
    public int NumberOf1Between1AndN_Solution(int n) {
        int count = 0;
        for (int i=0; i<n; i++) {
            String str = String.valueOf(i);
            for (char item : str.toCharArray()) {
                if (item == '1') {
                    count += 1;
                }
            }
        }
        return count;
    }

    public int NumberOf1Between1AndN_Solution2(int n) {
       return 1;
    }

    @Test
    public void test01() {
        int input = 123;
        System.out.println("result:" + NumberOf1Between1AndN_Solution(input));
    }
}
