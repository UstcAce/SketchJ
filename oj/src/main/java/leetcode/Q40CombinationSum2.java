package leetcode;

import org.junit.Test;

import java.util.*;

/**
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * <p>
 * candidates 中的每个数字在每个组合中只能使用一次。
 * <p>
 * 说明：
 * <p>
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。
 * 示例 1:
 * <p>
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 所求解集为:
 * [
 * [1, 7],
 * [1, 2, 5],
 * [2, 6],
 * [1, 1, 6]
 * ]
 * 示例 2:
 * <p>
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 所求解集为:
 * [
 * [1,2,2],
 * [5]
 * ]
 */
public class Q40CombinationSum2 {
    private List<List<Integer>> result = new ArrayList<>();

    private boolean[] visit;

    private int len;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        len = candidates.length;
        if (len == 0) return result;
        visit = new boolean[len];

        Arrays.sort(candidates);

        traceBackCombine2(new ArrayList<>(), candidates, 0, 0, target);

        return result;
    }

    private void traceBackCombine2(List<Integer> tmp, int[] candidates, int sum, int pos, int target) {
        if (sum == target) {
            result.add(new ArrayList<>(tmp));
            return;
        }

        /**
         * 从下一个元素开始搜寻
         */
        for (int i = pos; i < len; i++) {
            if (visit[i]) continue;

            if (i > 0 && candidates[i] == candidates[i - 1] && !visit[i - 1]) continue;

            if (sum + candidates[i] > target) continue;

            tmp.add(candidates[i]);
            sum += candidates[i];
            visit[i] = true;
            traceBackCombine2(tmp, candidates, sum, i, target);
            visit[i] = false;
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
        int[] input = {10, 1, 2, 7, 6, 1, 5};
        int target = 8;

        List<List<Integer>> res = combinationSum2(input, target);
        System.out.println(res);
    }
}
