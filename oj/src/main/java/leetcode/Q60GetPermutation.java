package leetcode;

import org.junit.Test;

/**
 * 给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。
 *
 * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * 给定 n 和 k，返回第 k 个排列。
 * 说明：
 * 给定 n 的范围是 [1, 9]。
 * 给定 k 的范围是[1,  n!]。
 * 示例 1:
 * 输入: n = 3, k = 3
 * 输出: "213"
 *
 *  示例 2:
 * 输入: n = 4, k = 9
 * 输出: "2314"
 *
 */
public class Q60GetPermutation {
    /**
     * 康拓(contor)展开与逆康拓(contor)展开
     * https://blog.csdn.net/ajaxlt/article/details/86544074
     */

    /**
     * 康拓展开：给定一个正整数n与一个由1~n的数组成的排列a1...an，计算这个排列的字段序rank
     */
    public int calcContorIndex(int[] input) {
        int len = input.length;
        boolean[] visit = new boolean[len];
        int rank = 0;
        for (int val : input) {
            rank += calcLessUnVisited(val, visit) * calcFactorial(len - 1);
            visit[val - 1] = true;
            len--;
        }
        return rank + 1;
    }

    /**
     * 计算小于n且没被访问的数有多少个
     */
    private int calcLessUnVisited(int n, boolean[] visit) {
        int count = 0;
        for (int i = 0; i < n - 1; i++) {
            if (!visit[i]) {
                count++;
            }
        }
        return count;
    }

    /**
     * 计算一个自然数的阶乘
     */
    private int calcFactorial(int n) {
        if (n <= 1) return 1;
        int res = 1;
        for (int i = 1; i <= n; i++) {
            res *= i;
        }
        return res;
    }

    /**
     * 逆康拓展开：给定一个正整数n和由这n个正整数组成的某个排列的字典序rank，求这个排列
     */
    public String calcRevContorSeq(int n, int rank) {
        StringBuilder res = new StringBuilder();
        boolean[] visit = new boolean[n];

        int num = rank - 1;
        for (int i = n - 1; i >= 0; i--) {
            int factorial = calcFactorial(i);
            int val = calcLessKUnVisited(num / factorial + 1, visit);
            res.append(val);
            visit[val - 1] = true;
            num = num % factorial;
        }
        return res.toString();
    }

    /**
     * 计算升序第k个没使用的元素
     */
    private int calcLessKUnVisited(int k, boolean[] visit) {
        int count = 0;
        for (int i = 0; i < visit.length; i++) {
            if (!visit[i]) {
                count++;
                if (count == k) {
                    return i + 1;
                }
            }
        }

        throw new UnsupportedOperationException("");
    }

    @Test
    public void testCase01() {
        int[] input = {2, 3, 4, 1};
        System.out.println(calcContorIndex(input));
    }

    @Test
    public void testCase02() {
        System.out.println(calcRevContorSeq(1, 1));
    }
}
