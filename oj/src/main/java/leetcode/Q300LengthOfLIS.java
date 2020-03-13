package leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 * <p>
 * 示例:
 * <p>
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 * 说明:
 * <p>
 * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
 * 你算法的时间复杂度应该为 O(n2) 。
 */
public class Q300LengthOfLIS {
    public int lengthOfLIS(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }

        int max = 0;
        int[] dp = new int[len];
        // 结束于i的最长子序列至少长度为1
        Arrays.fill(dp, 1);
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                    }
                }
            }
            max = Math.max(max, dp[i]);
        }

        return max;
    }

    /**
     * https://blog.csdn.net/lxt_Lucia/article/details/81206439
     */
    public int lengthOfLIS2(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }

        LinkedList<Integer> low = new LinkedList<>();
        for (int i = 0; i < len; i++) {
            updateLowList(low, nums[i]);
        }

        return low.size();
    }

    private void updateLowList(LinkedList<Integer> low, int val) {
        if (low.isEmpty()) {
            low.add(val);
        } else {
            if (val > low.getLast()) {
                low.add(val);
            } else {
                // 二分查找第一个大于等于val的数值，并替换它
                int idx = binarySearchLargerEleIndex(low, val);
                low.set(idx, val);
            }
        }
    }

    private int binarySearchLargerEleIndex(LinkedList<Integer> low, int val) {
        int left = 0;
        int right = low.size() - 1;
        while (left <= right) {
            int mid = (right + left) / 2;
            if (low.get(mid) < val) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    @Test
    public void testCase01() {
        int[] input = {10, 9, 2, 5, 3, 7, 101, 1};
        System.out.println(lengthOfLIS(input));
        System.out.println(lengthOfLIS2(input));
    }

    @Test
    public void testCase02() {
        LinkedList<Integer> linkedList = new LinkedList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6));
        System.out.println(binarySearchLargerEleIndex(linkedList, 0));
    }
}
