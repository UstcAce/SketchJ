package sword2offer;

import org.junit.Test;


/**
 * 求出1~13的整数中1出现的次数,并算出100~1300的整数中1出现的次数？为此他特别数了一下1~13中包含1的数字有1、10、11、12、13
 * 因此共出现6次,但是对于后面问题他就没辙了。ACMer希望你们帮帮他,并把问题更加普遍化,可以很快的求出任意非负整数区间中1出现
 * 的次数（从1 到 n 中1出现的次数）。
 */
public class Q31NumberOf1Between1AndN {
    /**
     * 方法1：暴力遍历求解，遍历k (0~n)，并将每个k转成字符数组，遍历判断有多少个'1'
     */
    public int NumberOf1Between1AndN_Solution(int n) {
        int count = 0;
        for (int i=0; i<=n; i++) {
            String str = String.valueOf(i);
            for (char item : str.toCharArray()) {
                if (item == '1') {
                    count += 1;
                }
            }
        }
        return count;
    }

    /**
     * 方法2：指定位数分析
     */
    public int NumberOf1Between1AndN_Solution2(int n) {
        if (n == 0) {
            return 0;
        }
        int count = 0;
        int size = String.valueOf(n).length();
        for (int i=1; i<=size; i++) {
            int val = getIdxVal(n, i);
            int high = getHighVal(n, i);
            int low = getLowVal(n, i);
            double base = Math.pow(10, i-1);
            if (val == 0) {
                count += high * base;
            } else if (val == 1) {
                count += high * base + low + 1;
            } else {
                count += (high + 1) * base;
            }
        }

       return count;
    }

    /**
     * 获取n右数位第i位的数字
     */
    private int getIdxVal(int n, int idx) {
        return (int)((n / Math.pow(10, idx-1)) % 10);
    }

    /**
     * 获取n右数第i位右边的高位数字
     */
    private int getHighVal(int n, int idx) {
        return (int)(n / Math.pow(10, idx));
    }

    /**
     * 获取n右数第i位左边的低位数字
     */
    private int getLowVal(int n, int idx) {
        return (int)(n % Math.pow(10, idx-1));
    }

    @Test
    public void test01() {
        int input = 10;
        System.out.println("result:" + NumberOf1Between1AndN_Solution(input));
        System.out.println("result:" + NumberOf1Between1AndN_Solution2(input));
    }

    @Test
    public void test02() {
        System.out.println(getIdxVal(123, 1));
        System.out.println(getHighVal(123, 1));
        System.out.println(getLowVal(123, 1));
    }

    /**
     * 求输入n数字中1出现的次数
     */
    private int numOfOne(int n) {
        int count = 0;
        char[] chars = String.valueOf(n).toCharArray();
        for (char item : chars) {
            if (item == '1') {
                count += 1;
            }
        }
        return count;
    }

    /**
     * 求start到end中整数1出现的次数，包含start和end
     */
    public int NumberOf1BetweenAnd_Solution(int start, int end) {
        if (start > end) {
            throw new IllegalArgumentException();
        }
        return numOfOne(start) + NumberOf1Between1AndN_Solution2(end) - NumberOf1Between1AndN_Solution2(start);
    }


    @Test
    public void test03() {
        int input1 = 1;
        int input2 = 11;
        int res1 = NumberOf1Between1AndN_Solution2(input1);
        int res2 = NumberOf1Between1AndN_Solution2(input2);
        int n1 = numOfOne(input1);
        int n2 = numOfOne(input2);

        System.out.println("result:" + res1);
        System.out.println("result:" + res2);
        System.out.println("n1:" + n1);
        System.out.println("n2:" + n2);
        System.out.println(res2 - res1 + n1);
    }

    @Test
    public void test04() {
        int input1 = 2;
        int input2 = 11;
        System.out.println(NumberOf1BetweenAnd_Solution(input1, input2));
    }
}
