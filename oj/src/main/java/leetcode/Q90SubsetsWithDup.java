package leetcode;

import org.junit.Test;

import java.util.*;

/**
 * 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * 说明：解集不能包含重复的子集。
 *
 * 示例:
 * 输入: [1,2,2]
 * 输出:
 * [
 *   [2],
 *   [1],
 *   [1,2,2],
 *   [2,2],
 *   [1,2],
 *   []
 * ]
 */
public class Q90SubsetsWithDup {
    private List<List<Integer>> result = new ArrayList<>();

    private int len;

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        len = nums.length;
        if (len == 0) return result;
        Arrays.sort(nums);
        traceBackSubset(new ArrayList<>(), nums, 0);
        return result;
    }

    private void traceBackSubset(List<Integer> tmp, int[] nums, int pos) {
        result.add(new ArrayList<>(tmp));
        for (int i = pos; i < len; i++) {
            // 和上个数字相等就跳过
            if (i > pos && nums[i] == nums[i - 1]) {
                continue;
            }
            tmp.add(nums[i]);
            traceBackSubset(tmp, nums, i + 1);
            tmp.remove(tmp.size() - 1);
        }
    }

    @Test
    public void testCase01() {
        int[] nums = {1, 2, 2};
        List<List<Integer>> res = subsetsWithDup(nums);
        for (List<Integer> list : res) {
            System.out.println(list);
        }
    }
}
