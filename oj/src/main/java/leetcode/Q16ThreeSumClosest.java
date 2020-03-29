package leetcode;

import java.util.Arrays;

/**
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 * 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
 * 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
 *
 */
public class Q16ThreeSumClosest {
    int first = 0;
    int second = 0;
    int third = 0;
    int minGap = Integer.MAX_VALUE;

    public int threeSumClosest(int[] nums, int target) {
        int len = nums.length;
        Arrays.sort(nums);

        for (int i = 0; i < len; i++) {
            int left = i + 1;
            int right = len - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == target) {
                    return target;
                } else if (sum < target) {
                    updateMinGap(i, left, right, sum, target);
                    left++;
                } else {
                    updateMinGap(i, left, right, sum, target);
                    right--;
                }
            }
        }
        return nums[first] + nums[second] + nums[third];
    }

    private void updateMinGap(int idx, int left, int right, int sum, int target) {
        if (Math.abs(sum - target) < minGap) {
            minGap = Math.abs(sum - target);
            first = idx;
            second = left;
            third = right;
        }
    }
}
