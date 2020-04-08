package leetcode;

import org.junit.Test;

public class Q81SearchInRotatedSortedArray2 {
    public boolean search(int[] nums, int target) {
        int len = nums.length;
        if (len == 0) {
            return false;
        }

        return hybridSearch(nums, 0, len - 1, target);
    }

    private boolean binarySearch(int[] nums, int i, int j, int target) {
        while (i <= j) {
            int mid = (i + j) / 2;
            if (nums[mid] < target) {
                i = mid + 1;
            } else if (nums[mid] > target) {
                j = j - 1;
            } else {
                return true;
            }
        }
        return false;
    }

    private boolean hybridSearch(int[] nums, int i, int j, int target) {
        int mid = (i + j) / 2;
        int midVal = nums[mid];

        if (midVal == target) {
            return true;
        }

        if (i >= j) {
            return false;
        }

        boolean leftRes;
        boolean rightRes;
        if (nums[mid] == nums[i]) {
            return hybridSearch(nums, i + 1, j, target);
        } else if (nums[mid] > nums[i]) {
            leftRes = binarySearch(nums, i, mid - 1, target);
            rightRes = hybridSearch(nums, mid + 1, j, target);
        } else {
            leftRes = hybridSearch(nums, i, mid - 1, target);
            rightRes = binarySearch(nums, mid + 1, j, target);
        }

        return leftRes || rightRes;
    }

    @Test
    public void testCase01() {
        int[] nums = {1};
        System.out.println(search(nums, 0));
    }

    @Test
    public void testCase02() {
        int[] nums = {3,1,1};
        System.out.println(search(nums, 3));
    }

    @Test
    public void testCase03() {
        int[] nums = {2,5,6,0,0,1,2};
        System.out.println(search(nums, 2));
    }

    @Test
    public void testCase04() {
        int[] nums = {1,2,1};
        System.out.println(search(nums, 3));
    }

    @Test
    public void testCase05() {
        int[] nums = {2, 2, 3, 1};
        System.out.println(search(nums, 1));
    }
}
