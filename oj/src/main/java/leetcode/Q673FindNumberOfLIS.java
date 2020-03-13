package leetcode;

import org.junit.Test;

import java.util.Arrays;

/**
 * 给定一个未排序的整数数组，找到最长递增子序列的个数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,3,5,4,7]
 * 输出: 2
 * 解释: 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。
 * 示例 2:
 * <p>
 * 输入: [2,2,2,2,2]
 * 输出: 5
 * 解释: 最长递增子序列的长度是1，并且存在5个子序列的长度为1，因此输出5。
 * 注意: 给定的数组长度不超过 2000 并且结果一定是32位有符号整数。
 */
public class Q673FindNumberOfLIS {
    /**
     * 此题等价于求最长递增子序列长度或者所有解
     * 1. 定义动态规划求解问题 longest[i] 表示以第i个数字结尾最长子序列长度
     * 这里假设longest[0] ~ longest[i-1]的解都已经求出
     * count[i]表示以第i个数字结尾最长子序列的个数
     * 2. 状态转移方程 longest[i] = Max(longest[j] + 1), 0<=j<i<n, 且 nums[i] > nums[j]
     */
    public int findNumberOfLIS(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }
        // longest[i]: longest ending in i number
        int[] longest = new int[len];
        // counts[i]: count number of longest ending in i number
        int[] count = new int[len];
        Arrays.fill(longest, 1);
        Arrays.fill(count, 1);

        int max = 1;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    // 第一次取到结束到i的最长子序列
                    if (longest[j] + 1 > longest[i]) {
                        count[i] = count[j];
                        longest[i] = longest[j] + 1;
                    } else if (longest[j] + 1 == longest[i]) {  // 已经找到过结束于i的最长子序列了
                        count[i] += count[j];
                    }
                }
            }
            max = Math.max(max, longest[i]);
        }

        int res = 0;
        for (int i = 0; i < len; i++) {
            if (max == longest[i]) {
                res += count[i];
            }
        }

        return res;
    }

    @Test
    public void testCase01() {
        int[] input = {1, 3, 5, 4, 7};
        System.out.println(findNumberOfLIS(input));
    }

    @Test
    public void testCase02() {
        int[] input = {2, 2, 2, 2};
        System.out.println(findNumberOfLIS(input));
    }
}
