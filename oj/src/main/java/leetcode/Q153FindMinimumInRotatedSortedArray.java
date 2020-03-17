package leetcode;

import org.junit.Test;

/**
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 *
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 *
 * 请找出其中最小的元素。
 *
 * 你可以假设数组中不存在重复元素。
 *
 * 示例 1:
 *
 * 输入: [3,4,5,1,2]
 * 输出: 1
 * 示例 2:
 *
 * 输入: [4,5,6,7,0,1,2]
 * 输出: 0
 *
 */
public class Q153FindMinimumInRotatedSortedArray {
    public int findMin(int[] nums) {
        int len = nums.length;
        if (len == 1) return nums[0];

        int left = 0;
        int right = len - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (mid - 1 < 0) {
                left = mid + 1;
                continue;
            }

            if (nums[mid] < nums[mid - 1]) {
                return nums[mid];
            } else {
                if (nums[mid] > nums[0]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return nums[0];
    }

    @Test
    public void testCase01() {
        int[] input = {2, 1};
        System.out.println(findMin(input));
    }

    @Test
    public void testCase02() {
        int[] input = {2, 0, 1};
        System.out.println(findMin(input));
    }

    @Test
    public void testCase03() {
        int[] input = {3,4,5,1,2};
        System.out.println(findMin(input));
    }

    @Test
    public void testCase04() {
        int[] input = {4,5,6,7,0,1,2};
        System.out.println(findMin(input));
    }

    @Test
    public void testCase05() {
        int[] input = {1};
        System.out.println(findMin(input));
    }

    @Test
    public void testCase06() {
        int[] input = {1, 2};
        System.out.println(findMin(input));
    }
}
