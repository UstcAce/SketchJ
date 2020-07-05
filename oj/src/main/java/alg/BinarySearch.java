package alg;

public class BinarySearch {
    /**
     * 标准二分查找，在nums数组中找值为target的下标，
     * 没有则返回-1
     */
    public int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int midIdx = (left + right) / 2;
            if (nums[midIdx] == target) {
                return midIdx;
            } else if (nums[midIdx] < target) {
                left = midIdx + 1;
            } else {
                right = midIdx - 1;
            }
        }
        return -1;
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
}
