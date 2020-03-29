package leetcode;

public class Q29DivideTwoIntegers {
    /**
     * 我们直接举个例子如果被除数 15，除数 3，用我们上面的方法要遍历 5 次。
     * 接下来，我们使用不断 增倍除数
     * 比如：
     * 被除数 除数
     * 15 3
     * 12 6
     * 6 12
     *
     * 发现除数 大于 被除数大，再重现开始
     * 6 3
     * ...
     * 3 3
     * 虽然这个例子遍历次数相等，对于较大的数，可以减少时间复杂度。
     *
     */
    public int divide(int dividend, int divisor) {
        if (dividend == 0) return 0;
        // true: 异号，false: 同号
        boolean isNegative = (dividend ^ divisor) < 0;

        long d1 = Math.abs((long) dividend);
        long d2 = Math.abs((long) divisor);
        long res = 0;
        while (d1 >= d2) {
            long tmp = d2;
            long i = 1;
            while (d1 >= tmp) {
                d1 -= tmp;
                res += i;
                i = i << 1;
                tmp = tmp << 1;
            }
        }

        if (isNegative) res = 0 - res;
        if (res < Integer.MIN_VALUE) return Integer.MIN_VALUE;
        else if (res > Integer.MAX_VALUE) return Integer.MAX_VALUE;
        return (int) res;
    }
}
