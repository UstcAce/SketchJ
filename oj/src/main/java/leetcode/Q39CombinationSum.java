package leetcode;

import org.junit.Test;

import java.util.*;

/**
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的数字可以无限制重复被选取。
 *
 * 说明：
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。
 * 示例 1:
 * 输入: candidates = [2,3,6,7], target = 7,
 * 所求解集为:
 * [
 *   [7],
 *   [2,2,3]
 * ]
 *
 * 示例 2:
 * 输入: candidates = [2,3,5], target = 8,
 * 所求解集为:
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 */
public class Q39CombinationSum {
    private List<List<Integer>> result = new ArrayList<>();

    private int len;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        len = candidates.length;
        if (len == 0) return result;

        Arrays.sort(candidates);

        traceBackCombine2(new ArrayList<>(), candidates, 0, 0, target);

        return result;
    }

    private void traceBackCombine2(List<Integer> tmp, int[] candidates, int sum, int pos, int target) {
        if (sum == target) {
            result.add(new ArrayList<>(tmp));
            return;
        }

        for (int i = pos; i < len; i++) {
            if (sum + candidates[i] > target) {
                continue;
            }
            tmp.add(candidates[i]);
            sum += candidates[i];
            traceBackCombine2(tmp, candidates, sum, i, target);
            tmp.remove(tmp.size() - 1);
            sum -= candidates[i];
        }
    }

    /**
     * 回溯三要素：
     * 1. 有效的结果(停止的条件)
     * 2. 回溯的条件
     * 3. 剪枝的条件
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        len = candidates.length;
        if (len == 0) return result;

        Arrays.sort(candidates);
        traceBackCombine(new ArrayList<>(), candidates, 0, target);
        return calcUniqueList();
    }

    private void traceBackCombine(List<Integer> tmp, int[] candidates, int sum, int target) {
        if (sum == target) {
            result.add(new ArrayList<>(tmp));
            return;
        }

        for (int i = 0; i < len; i++) {
            if (sum + candidates[i] > target) {
                continue;
            }
            tmp.add(candidates[i]);
            sum += candidates[i];
            traceBackCombine(tmp, candidates, sum, target);
            tmp.remove(tmp.size() - 1);
            sum -= candidates[i];
        }
    }

    private List<List<Integer>> calcUniqueList() {
        List<List<Integer>> uniques = new ArrayList<>();
        Set<String> set = new HashSet<>();

        for (List<Integer> list : result) {
            Collections.sort(list);
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
        int[] input = {2, 3, 6, 7};
        int target = 7;

        List<List<Integer>> res = combinationSum2(input, target);
        System.out.println(res);
    }

    @Test
    public void testCase02() {
        int[] input = {2, 3, 5};
        int target = 8;

        List<List<Integer>> res = combinationSum2(input, target);
        System.out.println(res);
    }
}
