package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个无序的整数数组，找到其中第一组最长上升子序列。
 * <p>
 * 示例1:
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 一组最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 * 示例2:
 * 输入: [1,3,5,2,4,6,7,8]
 * 输出: 一组最长的上升子序列是 [1, 3, 5, 6, 7, 8]，它的长度是 6。
 */
public class Q300_1SeqOfLIS {
    /**
     * 时间复杂度O(n^2)， 空间复杂度O(n)，空间复杂度是2n
     */
    public List<Integer> getSeqOfLIS(int[] nums) {
        int len = nums.length;
        if (len == 0) return new ArrayList<>();

        // dp[i]表示确定以下标为i元素结尾的nums[0]~nums[i]子串的最长递增子序列的长度
        int dp[] = new int[len];
        Arrays.fill(dp, 1);
        // res[i]表示确定以下标i元素结尾的nums[0]~nums[i]子串一个最长递增子序列
        List<List<Integer>> res = new ArrayList<>();

        int maxId = -1;
        int maxLength = Integer.MIN_VALUE;

        for (int i = 0; i < len; i++) {
            List<Integer> tmp = new ArrayList<>();
            // 链接到上一个不包括i的最长递增子序列的res里面的下标
            int index = -1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    index = j;
                }
            }

            if (index > -1) {
                tmp.addAll(res.get(index));
            }
            tmp.add(nums[i]);
            res.add(tmp);

            if (tmp.size() > maxLength) {
                maxLength = tmp.size();
                maxId = i;
            }
        }
        return res.get(maxId);
    }

    /**
     * 时间复杂度O(n^2)， 空间复杂度O(n)，空间复杂度是n
     */
    public List<Integer> getSeqOfLIS2(int[] nums) {
        int len = nums.length;
        if (len == 0) return new ArrayList<>();

        // res[i]表示确定以下标i元素结尾的nums[0]~nums[i]子串一个最长递增子序列
        List<List<Integer>> res = new ArrayList<>();

        int maxId = -1;
        int maxLength = Integer.MIN_VALUE;

        for (int i = 0; i < len; i++) {
            List<Integer> tmp = new ArrayList<>();
            // 链接到上一个不包括i的最长递增子序列的res里面的下标
            int index = -1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j] && res.get(j).size() + 1 > tmp.size()) {
                    tmp = new ArrayList<>(res.get(j));
                    tmp.add(nums[i]);
                    index = j;
                }
            }

            if (index == -1) {
                tmp.add(nums[i]);
            }

            res.add(tmp);

            if (tmp.size() > maxLength) {
                maxLength = tmp.size();
                maxId = i;
            }
        }
        return res.get(maxId);
    }

    /**
     * 输入: [10,9,2,5,3,7,101,18]
     * 输出: 4
     * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
     */
    @Test
    public void testCase01() {
        int[] input = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(getSeqOfLIS(input));
        System.out.println(getSeqOfLIS2(input));
    }

    /**
     * 输入: [1,3,5,2,4,6,7,8]
     * 解释: 最长的上升子序列是 [1,2,4,6,7,8]，它的长度是 6。
     */
    @Test
    public void testCase02() {
        int[] input = {1, 3, 5, 2, 4, 6, 7, 8};
        System.out.println(getSeqOfLIS(input));
        System.out.println(getSeqOfLIS2(input));
    }

    @Test
    public void testCase03() {
        int[] input = {1};
        System.out.println(getSeqOfLIS(input));
        System.out.println(getSeqOfLIS2(input));
    }
}