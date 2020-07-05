package leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * 如果数组中不存在目标值，返回 [-1, -1]。
 * <p>
 * 示例 1:
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: [3,4]
 * <p>
 * 示例 2:
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: [-1,-1]
 */
public class Q34SearchRange {
    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[]{-1, -1};
        }
        int idx = binarySearch(nums, target);
        if (idx == -1) {
            return new int[]{-1, -1};
        } else {
            int leftIdx = idx;
            while (leftIdx - 1 >= 0 && nums[leftIdx - 1] == target) {
                leftIdx--;
            }
            int rightIdx = idx;
            while (rightIdx + 1 < nums.length && nums[rightIdx + 1] == target) {
                rightIdx++;
            }
            return new int[]{leftIdx, rightIdx};
        }
    }

    /**
     * 标准二分查找
     * 1. 取值范围为[0, len - 1]
     * 2. mid = (left + right) / 2
     * 3. 判断条件为 left <= right，搜寻每一个元素
     */
    private int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    public int[] searchRange2(int[] nums, int target) {
        int lowerBound = binarySearchLowerBound(nums, target);
        int upperBound = binarySearchUpperBound(nums, target);
        return new int[]{lowerBound, upperBound};
    }

    /**
     * 在nums数组中二分查找第一次值为target的下标idx，
     * 没有则返回-1
     * 隐藏含义：idx表示nums数组中小于target的数值的个数
     *
     * {1, 2, 3}, target = 0, ret = 0
     * {1, 2, 3}, target = 10, ret = 3
     * {1, 1, 2, 3}, target = 1, ret = 0
     * {1, 2, 3, 3}, target = 3, ret = 2
     */
    public int binarySearchLowerBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int midIdx = (left + right) / 2;
            if (nums[midIdx] == target) {
                right = midIdx;
            } else if (nums[midIdx] < target) {
                left = midIdx + 1;
            } else {
                right = midIdx;
            }
        }
        // [0, len]
        if (left == nums.length) {
            return -1;
        }
        return nums[left] == target ? left : -1;
    }

    /**
     * 在nums数组中二分查找最后一次值为target的下标idx，
     * 没有则返回-1
     *
     * {1, 2, 3}, target = 0, ret = 0
     * {1, 2, 3}, target = 10, ret = 3
     * {1, 1, 2, 3}, target = 1, ret = 2
     * {1, 2, 3, 3}, target = 3, ret = 4
     */
    public int binarySearchUpperBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int midIdx = (left + right) / 2;
            if (nums[midIdx] == target) {
                left = midIdx + 1;
            } else if (nums[midIdx] < target) {
                left = midIdx + 1;
            } else {
                right = midIdx;
            }
        }
        // [0, len]
        if (left == 0) {
            return -1;
        }
        return nums[left - 1] == target ? left - 1: - 1;
    }

    @Test
    public void testCase01() {
        int[] nums = {5, 7, 7, 8, 8, 10};
        int target = 8;
        int[] res = searchRange2(nums, target);
        int[] except = {3, 4};
        Assert.assertArrayEquals(except, res);
    }

    @Test
    public void testCase02() {
        int[] nums = {5, 7, 7, 8, 8, 10};
        int target = 6;
        int[] res = searchRange2(nums, target);
        int[] except = {-1, -1};
        Assert.assertArrayEquals(except, res);
    }

    @Test
    public void testCase03() {
        int[] nums = {1};
        int target = 1;
        int[] res = searchRange2(nums, target);
        int[] except = {0, 0};
        Assert.assertArrayEquals(except, res);
    }

    @Test
    public void testCase04() {
        int[] nums = {1};
        int target = 11;
        int[] res = searchRange2(nums, target);
        int[] except = {-1, -1};
        Assert.assertArrayEquals(except, res);
    }

    @Test
    public void testCase05() {
        int[] nums = {};
        int target = 11;
        int[] res = searchRange2(nums, target);
        int[] except = {-1, -1};
        Assert.assertArrayEquals(except, res);
    }
}
