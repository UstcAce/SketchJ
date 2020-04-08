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

    /**
     * 1. 定义动态规划求解问题，dp[i]表示确定以第i个数字结束的最长上升子序列的长度
     * 2. 状态转移方程
     * (1) dp[i] = Max(dp[j] + 1),  0 <= j < i < len 且 nums[i] > nums[j]
     * (2) dp[i] = 1
     * (3) 边界条件 dp[0] = 1
     * 时间复杂度O(n^2) 空间复杂度O(n)
     */
    public int lengthOfLIS(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }

        int max = 1;
        int[] dp = new int[len];
        Arrays.fill(dp, 1);

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                }
            }
            max = Math.max(dp[i], max);
        }
        return max;
    }

    /**
     * 维护一个slow列表表示nums[]中的最慢上升序列，遍历nums
     * 对于一个nums[i], 若
     * 1. nums[i] > low列表的最后一个元素（即大于low中的所有元素），就讲其接到low的后面
     * 2. 否则，二分查找low列表中第一个大于等于nums[i]的数值并替换它 (low是一个增序序列)
     * 3. 最终的结果 = low列表的长度
     */
    public int lengthOfLIS2(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }

        LinkedList<Integer> slow = new LinkedList<>();
        for (int i = 0; i < len; i++) {
            int ele = nums[i];
            if (slow.isEmpty() || ele > slow.getLast()) {
                slow.add(ele);
            } else {
                int idx = binarySearchLargerEleIndex(slow, ele);
                slow.set(idx, ele);
            }
        }

        return slow.size();
    }

    private int binarySearchLargerEleIndex(LinkedList<Integer> low, int val) {
        int left = 0;
        int right = low.size() - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            int ele = low.get(mid);
            if (ele < val) {
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
