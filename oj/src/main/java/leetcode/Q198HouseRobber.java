package leetcode;

import org.junit.Test;

public class Q198HouseRobber {
    public int rob(int[] nums) {
        int len = nums.length;

        if (len == 0) return 0;

        if (len == 1) return nums[0];

        int[] dp = new int[len];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < len; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }

        return dp[len - 1];
    }

    public int rob2(int[] nums, int left, int right) {
        int len = right - left + 1;

        if (len == 1) return nums[left];

        int[] dp = new int[len];
        dp[0] = nums[left];
        dp[1] = Math.max(nums[left], nums[left + 1]);

        for (int i = 2; i < len; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[left + i]);
        }

        return dp[len - 1];
    }

    @Test
    public void testCase01() {
        int[] nums = {1, 2, 3, 1};
        System.out.println(rob2(nums, 1, 3));
    }

    @Test
    public void testCase02() {
        int[] nums = {2, 7, 9, 3, 1};
        System.out.println(rob2(nums, 1, 4));
    }
}

