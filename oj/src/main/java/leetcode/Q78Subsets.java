package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *
 * 说明：解集不能包含重复的子集。
 *
 * 示例:
 *
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 *
 */
public class Q78Subsets {

    /**
     * 1. 定义动态规划问题 dp[i] 表示前i个数字的非空子集
     * 2. 状态转移方程 dp[i]的子集等于
     * （1）dp[i-1]的子集
     *  (2) dp[i-1]的子集序列 + nums[i]结尾
     *  (3) 只包含 nums[i]的集合 { nums[i] }
     */
    public List<List<Integer>> subsets(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return new ArrayList<>();
        }

        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            // part 1: dp[i-1]的子集
            List<List<Integer>> localList = new ArrayList<>(result);
            // part 2: dp[i-1]的子集序列 + nums[i]结尾
            for (List<Integer> oneSet : result) {
                List<Integer> tmp = new ArrayList<>(oneSet);
                tmp.add(nums[i]);
                localList.add(tmp);
            }
            // part 3: 只包含 nums[i]的集合 { nums[i] }
            List<Integer> oneItemList = new ArrayList<>(Arrays.asList(nums[i]));
            localList.add(oneItemList);
            result = localList;
        }

        // 特殊空集
        result.add(new ArrayList<>());
        return result;
    }

    @Test
    public void testCase01() {
        int[] nums = {1, 2, 3};
        List<List<Integer>> res = subsets(nums);
        for (List<Integer> list : res) {
            System.out.println(list);
        }
    }

    @Test
    public void testCase02() {
        int[] nums = {1};
        List<List<Integer>> res = subsets(nums);
        for (List<Integer> list : res) {
            System.out.println(list);
        }
    }
}
