package leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * A: [1,2,3,2,1]
 * B: [3,2,1,4,7]
 * 输出: 3
 * 解释:
 * 长度最长的公共子数组是 [3, 2, 1]。
 */
public class Q718FindLength {
    /**
     * 1. dp[i][j]表示在A中以A[i]结尾，在B中以B[j]结尾的公共最长子数组的长度
     * 2. 状态转移方程：
     * (1) A[i] != B[j]，dp[i][j] = 0
     * (2) A[i] == B[j], dp[i][j] = dp[i-1][j-1] + 1
     * 3. 边界条件
     * dp[i][0] = A[i] == B[0] ? 1 : 0
     * dp[0][j] = A[0] == B[j] ? 1 ：0
     * 时间复杂度O(m*n) 空间复杂度O(m*n)
     */
    public int findLength(int[] A, int[] B) {
        int lenA = A.length;
        int lenB = B.length;
        if (lenA == 0 || lenB == 0) {
            return 0;
        }

        int max = 0;
        int[][] dp = new int[lenA][lenB];
        for (int i = 0; i < lenA; i++) {
            dp[i][0] = A[i] == B[0] ? 1 : 0;
            max = Math.max(max, dp[i][0]);
        }
        for (int j = 0; j < lenB; j++) {
            dp[0][j] = A[0] == B[j] ? 1 : 0;
            max = Math.max(max, dp[0][j]);
        }

        for (int i = 1; i < lenA; i++) {
            for (int j = 1; j < lenB; j++) {
                dp[i][j] = A[i] == B[j] ? (dp[i - 1][j - 1] + 1) : 0;
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }

    /**
     * 滚动数组优化，dp[i][j]  j = lenB-1 ~ 0
     */
    public int findLength2(int[] A, int[] B) {
        int lenA = A.length;
        int lenB = B.length;
        if (lenA == 0 || lenB == 0) {
            return 0;
        }

        int max = 0;
        int[] dp = new int[lenB];
        for (int i = 0; i < lenA; i++) {
            for (int j = lenB - 1; j >= 0; j--) {
                if (i == 0) {
                    dp[j] = A[0] == B[j] ? 1 : 0;
                } else if (j == 0) {
                    dp[0] = A[i] == B[0] ? 1 : 0;
                } else {
                    dp[j] = A[i] == B[j] ? (dp[j - 1] + 1) : 0;
                }
                max = Math.max(max, dp[j]);
            }
        }
        return max;
    }

    @Test
    public void testCase01() {
        int[] A = {1, 2, 3, 2, 1};
        int[] B = {3, 2, 1, 4, 7};
        int excepted = 3;
        int res = findLength(A, B);
        Assert.assertEquals(excepted, res);

        int res2 = findLength(A, B);
        Assert.assertEquals(excepted, res2);
    }

    @Test
    public void testCase02() {
        int[] A = {};
        int[] B = {3, 2, 1, 4, 7};
        int excepted = 0;
        int res = findLength(A, B);
        Assert.assertEquals(excepted, res);

        int res2 = findLength(A, B);
        Assert.assertEquals(excepted, res2);
    }

    @Test
    public void testCase03() {
        int[] A = {3};
        int[] B = {3, 2, 1, 4, 7};
        int excepted = 1;
        int res = findLength(A, B);
        Assert.assertEquals(excepted, res);

        int res2 = findLength(A, B);
        Assert.assertEquals(excepted, res2);
    }

    @Test
    public void testCase04() {
        int[] A = {1, 2, 1, 4, 6};
        int[] B = {3, 2, 1, 4, 7};
        int excepted = 3;
        int res = findLength(A, B);
        Assert.assertEquals(excepted, res);

        int res2 = findLength(A, B);
        Assert.assertEquals(excepted, res2);
    }
}
