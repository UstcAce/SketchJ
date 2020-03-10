package leetcode;

import org.junit.Test;

import java.util.Arrays;

/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * 示例:
 *
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 说明:
 *
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 */
public class Q283MoveZeroes {
    public void moveZeroes(int[] nums) {
        int len = nums.length;
        if (len <=1) {
            return;
        }
        // 倒数第一个非零下标
        int lastNotZero = len-1;
        while (lastNotZero>=0 && nums[lastNotZero] == 0) {
            lastNotZero = lastNotZero - 1;
        }

        int i = lastNotZero;
        while (i>=0) {
            if (nums[i] == 0)  {
                swap2Pos(nums, i, lastNotZero);
                lastNotZero -= 1;
            }
            i -= 1;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    /**
     * 将nums[i]的值平移到nums[j]
     */
    private void swap2Pos(int[] nums, int i, int j) {
        for (int id=i; id<j; id++) {
            swap(nums, id, id+1);
        }
    }

    @Test
    public void testCase01() {
        int[] input = {0,1,0,3,12};
        moveZeroes(input);
        System.out.println(Arrays.toString(input));
    }

    @Test
    public void testCase02() {
        int[] input = {1};
        moveZeroes(input);
        System.out.println(Arrays.toString(input));
    }

    @Test
    public void testCase03() {
        int[] input = {0, 0};
        moveZeroes(input);
        System.out.println(Arrays.toString(input));
    }
}
