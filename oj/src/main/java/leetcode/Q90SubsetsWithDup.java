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

    private boolean[] visit;

    private int len;

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        len = nums.length;
        if (len == 0) return result;
        visit = new boolean[len];
        traceBackSubset(new ArrayList<>(), nums, 0);
        return calcUniqueList();
    }

    private void traceBackSubset(List<Integer> tmp, int[] nums, int pos) {
        if (allVisit()) {
            result.add(new ArrayList<>(tmp));
            return;
        }

        for (int i = pos; i < len; i++) {
            if (visit[i]) continue;

            tmp.add(nums[i]);
            visit[i] = true;
            traceBackSubset(tmp, nums, i);
            tmp.remove(tmp.size() - 1);

            traceBackSubset(tmp, nums, i);
            visit[i] = false;
        }
    }

    private boolean allVisit() {
        boolean res = true;
        for (boolean b : visit) {
            res = res && b;
        }
        return res;
    }

    private List<List<Integer>> calcUniqueList() {
        List<List<Integer>> uniques = new ArrayList<>();
        Set<String> set = new HashSet<>();

        for (List<Integer> list : result) {
            String hashCode = calcHashCode(list);
            if (!set.contains(hashCode)) {
                set.add(hashCode);
                uniques.add(list);
            }
        }

        return uniques;
    }

    private String calcHashCode(List<Integer> list) {
        StringBuilder sb = new StringBuilder();
        for (int val : list) {
            sb.append(val);
        }

        return sb.toString();
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
