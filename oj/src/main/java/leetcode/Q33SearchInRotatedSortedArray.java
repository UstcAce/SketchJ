package leetcode;

import org.junit.Test;

/**
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * <p>
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * <p>
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * <p>
 * 你可以假设数组中不存在重复的元素。
 * <p>
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 * 示例 2:
 * <p>
 * 输入: nums = [4,5,6,7,0,1,2], target = 3
 * 输出: -1
 */
public class Q33SearchInRotatedSortedArray {
    /**
     * 要求时间复杂度为O(logn)
     * 从nums数组二分： left 和 right其中必然有一个是有序数组
     * 假设 left 有序则在 left中二分查找 target
     * right 再二分
     */
    public int search(int[] nums, int target) {
        int len = nums.length;
        if (len == 0) {
            return -1;
        }

        return hybridSearch(nums, 0, len - 1, target);
    }

    private int binarySearch(int[] nums, int i, int j, int target) {
        while (i <= j) {
            int mid = (i + j) / 2;
            if (nums[mid] < target) {
                i = mid + 1;
            } else if (nums[mid] > target) {
                j = j - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    private int hybridSearch(int[] nums, int i, int j, int target) {
        int mid = (i + j) / 2;
        int midVal = nums[mid];

        if (midVal == target) {
            return mid;
        }

        if (i >= j) {
            return -1;
        }

        int leftRes;
        int rightRes;
        if (nums[mid] > nums[i]) {
            leftRes = binarySearch(nums, i, mid - 1, target);
            rightRes = hybridSearch(nums, mid + 1, j, target);
        } else {
            leftRes = hybridSearch(nums, i, mid - 1, target);
            rightRes = binarySearch(nums, mid + 1, j, target);
        }

        return Math.max(leftRes, rightRes);
    }

    @Test
    public void testCase01() {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int target = 0;
        System.out.println(search(nums, target));
    }

    @Test
    public void testCase02() {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int target = 3;
        System.out.println(search(nums, target));
    }

    @Test
    public void testCase03() {
        int[] nums = {1, 3};
        int target = 3;
        System.out.println(search(nums, target));
    }

    @Test
    public void testCase04() {
        int[] nums = {4,5,6,7,0,1,2};
        int target = 6;
        System.out.println(search(nums, target));
    }
}
