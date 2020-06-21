package leetcode;

/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 示例:
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 *
 * 进阶:
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 */
public class Q53MaxSubArray {
    /**
     * 1. 定义动态规划求解问题dp[i]表示确定以num[i]结尾的最大和连续子数组的值
     * 2. 状态转移方程：dp[i] = Max(dp[i-1] + num[i], num[i])
     */
    public int maxSubArray(int[] nums) {
        int len = nums.length;
        if (len == 0) return 0;
        if (len == 1) return nums[0];

        int pre = nums[0];
        int maxVal = pre;
        for (int i = 1; i < len; i++) {
            pre = pre >= 0 ? pre + nums[i] : nums[i];
            maxVal = Math.max(pre, maxVal);
        }
        return maxVal;
    }
}
