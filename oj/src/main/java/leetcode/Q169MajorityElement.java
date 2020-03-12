package leetcode;

import org.junit.Test;

import java.util.Arrays;

public class Q169MajorityElement {
    public int majorityElement(int[] nums) {
        int len = nums.length;
        Arrays.sort(nums);
        return nums[len/2];
    }

    @Test
    public void testCase01() {
        int[] input = {3,2,3};
        System.out.println(majorityElement(input));
    }

    @Test
    public void testCase02() {
        int[] input = {2,2,1,1,1,2,2};
        System.out.println(majorityElement(input));
    }
}
