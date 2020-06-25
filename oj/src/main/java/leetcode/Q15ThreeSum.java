package leetcode;

import java.util.*;

/**
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 *
 * 示例：
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * 满足要求的三元组集合为：
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 *
 */
public class Q15ThreeSum {
    /**
     * 1. 基本思路排序后从0 ~ len - 1固定一个数值，从剩下的最左和最右进行夹逼
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int len = nums.length;
        if (len < 3) return res;

        Arrays.sort(nums);

        int i = 0;
        while (i < len) {
            // 2. 当当前基准元素为正数时，后面所有元素都为正数，即可跳出
            if (nums[i] > 0) break;

            // 3. 向前去重(由于两个元素相同这种情况以及被计算过)，不能向后去重，因为有同一元素出现两次-1,-1,2的这种情况
            if (i > 0 && nums[i] == nums[i - 1]) {
                i++;
                continue;
            }

            int left = i + 1;
            int right = len - 1;
            // 4. 需要下标合法才能进行计算，不能重叠
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum < 0) {
                    left++;
                } else if (sum > 0) {
                    right--;
                } else {
                    List<Integer> one = Arrays.asList(nums[i], nums[left], nums[right]);
                    res.add(one);
                    // 5. 合法情况下，向后去重
                    while (left + 1 < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right - 1 && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    left++;
                    right--;
                }
            }
            i++;
        }

        return res;
    }

    List<List<Integer>> result = new ArrayList<>();
    public List<List<Integer>> threeSum2(int[] nums) {
        // 1. 先进行原数组排序
        Arrays.sort(nums);

        // 2. 从0~len-1固定一个数，从剩下的有序数组的头和尾分别移动找和等于某个元素的2个数。
        int len = nums.length;
        for (int i = 0; i < len - 2; i++) {
            if (nums[i] > 0) break;
            // 3. 向前去重(由于两个元素相同这种情况以及被计算过)，不能向后去重，因为有同一元素出现两次-1,-1,2的这种情况
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            findValEquals(nums, i);
        }
        return result;
    }

    private void findValEquals(int[] nums, int firstIdx) {
        int firstVal = nums[firstIdx];
        int left = firstIdx + 1;
        int right = nums.length - 1;
        while (left < right) {
            if (firstVal + nums[left] + nums[right] == 0) {
                result.add(new ArrayList<>(Arrays.asList(firstVal, nums[left], nums[right])));
                // 合法情况下，向后去重
                while (left + 1 < right && nums[left] == nums[left + 1]) {
                    left++;
                }
                while (left < right - 1 && nums[right] == nums[right - 1]) {
                    right--;
                }
                left++;
                right--;
            } else if (firstVal + nums[left] + nums[right] < 0) {
                left++;
            } else {
                right--;
            }
        }
    }
}
