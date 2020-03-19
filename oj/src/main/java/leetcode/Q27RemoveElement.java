package leetcode;

import org.junit.Test;

import java.util.Arrays;

public class Q27RemoveElement {
    /**
     * 当 nums[j]nums[j] 与给定的值相等时，递增 j++ 以跳过该元素。只要 nums[j] != val
     * nums[j] = val，我们就复制 nums[j]nums[j] 到 nums[i]nums[i] 并同时递增两个索引。重复这一过程，直到 jj 到达数组的末尾，该数组的新长度为 ii。
     */
    public int removeElement(int[] nums, int val) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }


    public int removeElement2(int[] nums, int val) {
        if (nums.length == 0) return 0;
        int newLen = 0;
        int last = nums.length - 1;

        while (last >= 0 && nums[last] == val) {
            last--;
        }

        int i = 0;
        while (i <= last) {
            if (nums[i] == val) {
                swap(i, last, nums);
                last--;
                while (nums[last] == val) {
                    last--;
                }
            }
            newLen++;
            i++;
        }

        return newLen;
    }

    private void swap(int i, int j, int[] nums) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    @Test
    public void testCase01() {
        int[] nums = {3, 2, 2, 3, 2, 2, 3};
        int val = 2;
        int res = removeElement(nums, val);
        System.out.println("res:" + res);
        System.out.println(Arrays.toString(nums));
    }

    @Test
    public void testCase02() {
        int[] nums = {0, 1, 2, 2, 3, 0, 4, 2};
        int val = 0;
        int res = removeElement(nums, val);
        System.out.println("res:" + res);
        System.out.println(Arrays.toString(nums));
    }

    @Test
    public void testCase03() {
        int[] nums = {0};
        int val = 1;
        int res = removeElement(nums, val);
        System.out.println("res:" + res);
        System.out.println(Arrays.toString(nums));
    }
}
