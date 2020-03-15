package leetcode;

/**
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 *
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 *
 * 你可以假设 nums1 和 nums2 不会同时为空。
 *
 * 示例 1:
 *
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * 则中位数是 2.0
 * 示例 2:
 *
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 *
 * 则中位数是 (2 + 3)/2 = 2.5
 */
public class Q4FindMedianSortedArrays {
    /**
     * 1. 分别找两个数组合在一起第(m+n+1)/2和(m+n+2)/2的数，然后求平均值，
     * 这样对m+n是奇数和偶数的情况都适用
     * 2. 问题转化为从两个有序数组中寻找第k个元素(排序为k)
     * 3. 为了加速对k二分，分别在nums1和nums2中寻找第k/2个元素
     * 4. 最后就是二分法的核心啦，比较这两个数组的第K/2小的数字midVal1和midVal2的大小，如果第一个数组的第K/2个数字小的话，
     * 那么说明我们要找的数字肯定不在nums1中的前K/2个数字，所以我们可以将其淘汰，将nums1的起始位置向后移动K/2个，
     * 并且此时的K也自减去K/2，调用递归。反之，我们淘汰nums2中的前K/2个数字，并将nums2的起始位置向后移动K/2个，
     * 并且此时的K也自减去K/2，调用递归即可。
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int left = (m + n + 1) / 2;
        int right = (m + n + 2) / 2;

        return (findKthVal(nums1, 0, nums2, 0, left) + findKthVal(nums1, 0, nums2, 0, right)) / 2.0d;
    }

    /**
     * 从nums1数组的i位置和nums2数组的j位置起，寻找第k个元素
     */
    private int findKthVal(int[] nums1, int i, int[] nums2, int j, int k) {
        // nums1已经没有元素去寻找
        if (i >= nums1.length) return nums2[j + k - 1];
        if (j >= nums2.length) return nums1[i + k - 1];

        if (k == 1) return Math.min(nums1[i], nums2[j]);

        int midVal1 = (i + k / 2 - 1 < nums1.length) ? nums1[i + k / 2 - 1] : Integer.MAX_VALUE;
        int midVal2 = (j + k / 2 - 1 < nums2.length) ? nums2[j + k / 2 - 1] : Integer.MAX_VALUE;
        if (midVal1 < midVal2) {
            return findKthVal(nums1, i + k / 2, nums2, j, k - k / 2);
        } else {
            return findKthVal(nums1, i, nums2, j + k / 2, k - k / 2);
        }
    }
}
