package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 *
 * 示例:
 *
 * 输入: n = 4, k = 2
 * 输出:
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 */
public class Q77Combinations {
    private List<List<Integer>> result = new ArrayList<>();
    public List<List<Integer>> combine(int n, int k) {
        if (n <= 0 || k <= 0) return result;
        boolean[] visit = new boolean[n];

        traceBackCombine(new ArrayList<>(), visit, 0, k);
        return result;
    }

    private void traceBackCombine(List<Integer> temp, boolean[] visit, int pos, int k) {
        if (k == 0) {
            result.add(new ArrayList<>(temp));
            return;
        }

        if (pos >= visit.length) {
            return;
        }

        for (int i = pos; i < visit.length; i++) {
            if (!visit[i]) {
                temp.add(i + 1);
                visit[i] = true;
                traceBackCombine(temp, visit, i + 1, k - 1);
                temp.remove(temp.size() - 1);
                visit[i] = false;
            }
        }
    }
}
