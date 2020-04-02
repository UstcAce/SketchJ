package leetcode;

import org.junit.Test;

/**
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
 *
 * 示例 1:
 *
 * 输入: 2.00000, 10
 * 输出: 1024.00000
 * 示例 2:
 *
 * 输入: 2.10000, 3
 * 输出: 9.26100
 * 示例 3:
 *
 * 输入: 2.00000, -2
 * 输出: 0.25000
 * 解释: 2-2 = 1/22 = 1/4 = 0.25
 *
 */
public class Q50MyPow {
    public double myPow(double x, int n) {
        if (n == 0)  {
            return 1;
        } else if (n < 0) {
            return 1d / fastPow(x, -n);
        } else {
            return fastPow(x, n);
        }
    }

    private double fastPow(double x, long n) {
        if (n == 0) return 1;
        double half = fastPow(x, n / 2);
        if (n % 2 == 0) {
            return half * half;
        } else {
            return half * half * x;
        }
    }

    public double myPow2(double x, int n) {
        if (n == 0)  {
            return 1;
        } else if (n < 0) {
            return 1d / myPow(x, -n);
        } else {
            double a = Math.floor(x);
            double b = x - a;
            if (Double.compare(b, 0d) == 0) {
                return doublePow(a, n);
            }

            double res = 0;
            for (int i = 0; i <= n; i++) {
                double p1 =  combination(n, i);
                double p2 = doublePow(a, i);
                double p3 =  doublePow(b, n - i);
                res += p1 * p2 * p3;
            }
            return res;
        }
    }

    private double doublePow(double x, int n) {
        double res = 1;
        for (int i = 0; i < n; i++) {
            res = res * x;
        }
        return res;
    }

    private int combination(int n, int i) {
        int m = Math.min(i, n - i);
        int res = 1;
        for (int j = 0; j < m; j++) {
            res = res * (n - j);
        }
        return res;
    }

    @Test
    public void testCase01() {

        System.out.println(myPow(1.00000, -2147483648));

        System.out.println(Math.pow(1.6, 4));
    }
}
