package leetcode;

/**
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都围成一圈，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
 */
public class Q213Rob {

    /**
     * 由于第一间房和最后一间房相邻，那么有如下3种情况
     * 1. first和last都不抢
     * 2. first抢 last不抢
     * 3. first不抢 last抢
     */
    public int rob(int[] nums) {
        int len = nums.length;
        if (len == 0) return 0;
        if (len == 1) return nums[0];
        return Math.max(rob2(nums, 0, len - 2), rob2(nums, 1, len - 1));
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
}
