package leetcode;

import org.junit.Test;

/**
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 *
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 *
 * 示例:
 *
 * 输入: [2,3,1,1,4]
 *       [0, 2] [1, 4] [2, 3] [3, 4] [4, 8]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 *      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 *
 */
public class Q45JumpGame2 {
    public int jump(int[] nums) {
        int end = 0;
        int maxPosition = 0;
        int steps = 0;
        for (int i=0; i<nums.length-1; i++) {
            // 能跳的最远距离
            maxPosition = Math.max(maxPosition, nums[i]+i);
            // 遇到边界就更新边界，并且步数加1
            if (i == end) {
                end = maxPosition;
                steps ++;
            }
        }
        return steps;
    }

    @Test
    public void testCase01() {
        int[] input = {2,3,1,1,4};
        System.out.println(jump(input));
    }

    @Test
    public void testCase02() {
        int[] input = {1,2};
        System.out.println(jump(input));
    }
}
