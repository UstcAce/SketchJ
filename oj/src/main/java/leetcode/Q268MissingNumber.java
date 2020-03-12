package leetcode;

import org.junit.Test;

/**
 * 给定一个包含 0, 1, 2, ..., n 中 n 个数的序列，找出 0 .. n 中没有出现在序列中的那个数。
 *
 * 示例 1:
 *
 * 输入: [3,0,1]
 * 输出: 2
 * 示例 2:
 *
 * 输入: [9,6,4,2,3,5,7,0,1]
 *       [1,6,4,2,3,5,7,0,9]
 *       [1,5,4,2,3,6,7,0,9]
 *       [1,3,4,2,5,6,7,0,9]
 *       [1,4,3,2,5,6,7,0,9]
 *       [1,2,3,4,5,6,7,0,9]
 * 输出: 8
 * 说明:
 * 你的算法应具有线性时间复杂度。你能否仅使用额外常数空间来实现?
 *
 */
public class Q268MissingNumber {
    public int missingNumber(int[] nums) {
        int i = 0;
        int temp = Integer.MAX_VALUE;
        while (i<nums.length) {
            while (nums[i] != i+1) {
                if (nums[i] == 0) {
                    temp = i;
                    break;
                }
                swap(i, nums[i]-1, nums);
            }
            i += 1;
        }
        return temp == Integer.MAX_VALUE ? 0 : temp+1;
    }

    private void swap(int idx, int jdx, int[] nums) {
        int temp = nums[idx];
        nums[idx] = nums[jdx];
        nums[jdx] = temp;
    }

    @Test
    public void testCase01() {
        int [] input = {3,0,1};
        System.out.println(missingNumber(input));
    }

    @Test
    public void testCase02() {
        int [] input = {9,6,4,2,3,5,7,0,1};
        System.out.println(missingNumber(input));
    }

    @Test
    public void testCase03() {
        int [] input = {1,2};
        System.out.println(missingNumber(input));
    }

    @Test
    public void testCase04() {
        int [] input = {0,1};
        System.out.println(missingNumber(input));
    }

    @Test
    public void testCase05() {
        int [] input = {0,2};
        System.out.println(missingNumber(input));
    }

    @Test
    public void testCase06() {
        int [] input = {1};
        System.out.println(missingNumber(input));
    }
}
