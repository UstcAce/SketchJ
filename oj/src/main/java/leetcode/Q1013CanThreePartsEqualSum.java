package leetcode;

import org.junit.Test;

/**
 * 给你一个整数数组 A，只有可以将其划分为三个和相等的非空部分时才返回 true，否则返回 false。
 *
 * 形式上，如果可以找出索引 i+1 < j 且满足 (A[0] + A[1] + ... + A[i] == A[i+1] + A[i+2] + ... + A[j-1] == A[j] + A[j-1] + ... + A[A.length - 1]) 就可以将数组三等分。
 *
 * 示例 1：
 *
 * 输出：[0,2,1,-6,6,-7,9,1,2,0,1]
 * 输出：true
 * 解释：0 + 2 + 1 = -6 + 6 - 7 + 9 + 1 = 2 + 0 + 1
 */
public class Q1013CanThreePartsEqualSum {
    /**
     * 1. 数组求和 sum
     * 2. sum能被3整除，part = sum / 3 = 3
     * 3. 数组排序 -7, -6, 0, 0, 1, 1, 1, 2, 2, 6, 9
     */
    public boolean canThreePartsEqualSum(int[] A) {
        int len = A.length;
        if (len <= 2) {
            return false;
        }
        int sum = 0;
        for (int item : A) {
            sum += item;
        }

        if (sum % 3 != 0) {
            return false;
        }

        int i=0, j=len-1;
        int left = A[i];
        int right = A[j];
        while (i+1 < j) {
            if (left == sum/3 && right == sum/3) {
                return true;
            }
            if (left!=sum/3) {
                i++;
                left += A[i];
            }
            if (right!=sum/3) {
                j--;
                right += A[j];
            }
        }

        return false;
    }

    @Test
    public void testCase01() {
        int[] nums = {0,2,1,-6,6,-7,9,1,2,0,1};
        System.out.println(canThreePartsEqualSum(nums));
    }

    @Test
    public void testCase02() {
        int[] nums = {0,2,1,-6,6,7,9,-1,2,0,1};
        System.out.println(canThreePartsEqualSum(nums));
    }

    @Test
    public void testCase03() {
        int[] nums = {3,3,6,5,-2,2,5,1,-9,4};
        System.out.println(canThreePartsEqualSum(nums));
    }

    @Test
    public void testCase04() {
        int[] nums = {1,-1,1,-1};
        System.out.println(canThreePartsEqualSum(nums));
    }
}
