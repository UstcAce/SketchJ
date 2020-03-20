package leetcode;

import org.junit.Test;

import java.util.*;

/**
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 *
 * 示例:
 *
 * 输入: [1,1,2]
 * 输出:
 * [
 *   [1,1,2],
 *   [1,2,1],
 *   [2,1,1]
 * ]
 */
public class Q47PermuteUnique {
    private List<List<Integer>> result = new ArrayList<>();
    private boolean[] visit;
    private int len;

    /**
     * 回溯+剪枝
     */
    public List<List<Integer>> permuteUnique2(int[] nums) {
        len = nums.length;
        if (len == 0) return new ArrayList<>(new ArrayList<>());
        visit = new boolean[len];

        Arrays.sort(nums);

        traceBackPermuteUnique2(new ArrayList<>(), nums);

        return result;
    }

    /**
     * 在之前的剪枝条件1：用过的元素不能再使用之外，又添加了一个新的剪枝条件，也就是我们考虑重复部分思考的结果，
     * 于是剪枝条件2：当当前元素和前一个元素值相同（此处隐含这个元素的index>0），并且前一个元素还没有被使用过的时候，我们要剪枝
     */
    private void traceBackPermuteUnique2(List<Integer> tmp, int[] nums) {
        if (tmp.size() == len) {
            result.add(new ArrayList<>(tmp));
            return;
        }

        for (int i = 0; i < len; i++) {
            if (visit[i]) continue;
            if (i > 0 && nums[i] == nums[i - 1] && !visit[i - 1]) continue;

            tmp.add(nums[i]);
            visit[i] = true;
            traceBackPermuteUnique2(tmp, nums);
            visit[i] = false;
            tmp.remove(tmp.size() - 1);
        }
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        len = nums.length;
        if (len == 0) return new ArrayList<>(new ArrayList<>());
        visit = new boolean[len];

        traceBackPermuteUnique(new ArrayList<>(), nums);

        return calcUniqueList();
    }

    private void traceBackPermuteUnique(List<Integer> tmp, int[] nums) {
        if (tmp.size() == len) {
            result.add(new ArrayList<>(tmp));
            return;
        }

        for (int i = 0; i < len; i++) {
            if (visit[i]) continue;

            tmp.add(nums[i]);
            visit[i] = true;
            traceBackPermuteUnique(tmp, nums);
            visit[i] = false;
            tmp.remove(tmp.size() - 1);
        }
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
        int[] input = {1, 2, 1};
        List<List<Integer>> res = permuteUnique2(input);

        System.out.println(res);
    }

    @Test
    public void testCase02() {
        int[] input = {};
        List<List<Integer>> res = permuteUnique(input);

        System.out.println(res);
    }
}
