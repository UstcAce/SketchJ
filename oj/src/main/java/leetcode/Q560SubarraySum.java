package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
 *
 * 示例 1 :
 * 输入:nums = [1,1,1], k = 2
 * 输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
 *
 *  说明 :
 * 数组的长度为 [1, 20,000]。
 * 数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。
 */
public class Q560SubarraySum {
    /**
     * 1. 先求前缀和数组prefixSum[i] 表示从nums[0] ~ nums[i]的连续子数组之和
     * 2. 遍历（i, j）i <= j
     * prefixSum[i]: nums[0] ~ nums[i]
     * prefixSum[j]: nums[0] ~ nums[j]
     * prefixSum[j] - prefixSum[i] = nums[i+1] ... nums[j]
     * 3. 边界条件 i == j, prefixSum[j] - prefixSum[i] = 0
     *
     * 时间复杂度: O(n^2)
     * 空间复杂度：O(n)
     */
    public int subarraySum(int[] nums, int k) {
        int len = nums.length;
        int[] prefixSum = new int[len + 1];

        for (int i = 1; i < len; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i];
        }
        int count = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                if (prefixSum[j] - prefixSum[i] + nums[i] == k) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * https://www.bilibili.com/video/BV13t4y1y7ya
     *
     * 1. 先求前缀和数组pre[i] 表示从nums[0] ~ nums[i]的连续子数组之和
     * 2. nums[i]~nums[j]的连续子数组的和为k的条件转化为：
     *  pre[j] - pre[i-1] == k 即 pre[j] = k - pre[i-1]
     *  0 <= j < len
     * 3. 对于pre[j]只要之前出现过前缀和为pre[j] - k的情况即可
     */
    public int subarraySum2(int[] nums, int k) {
        int count = 0;
        int pre = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int j = 0; j < nums.length; j++) {
            pre = pre + nums[j];
            if (map.containsKey(pre - k)) {
                count += map.get(pre - k);
            }
            map.put(pre, map.getOrDefault(pre, 0) + 1);
        }
        return count;
    }
}
