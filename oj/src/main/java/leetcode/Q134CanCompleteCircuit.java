package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
 * 如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。
 *
 * 说明: 
 *
 * 如果题目有解，该答案即为唯一答案。
 * 输入数组均为非空数组，且长度相同。
 * 输入数组中的元素均为非负数。
 * 示例 1:
 * 输入:
 * gas  = [1,2,3,4,5]
 * cost = [3,4,5,1,2]
 *
 * 输出: 3
 * 解释:
 * 从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
 * 开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
 * 开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
 * 开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
 * 开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
 * 开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
 * 因此，3 可为起始索引。
 *
 * 示例 2:
 * 输入:
 * gas  = [2,3,4]
 * cost = [3,4,3]
 *
 * 输出: -1
 *
 * 解释:
 * 你不能从 0 号或 1 号加油站出发，因为没有足够的汽油可以让你行驶到下一个加油站。
 * 我们从 2 号加油站出发，可以获得 4 升汽油。 此时油箱有 = 0 + 4 = 4 升汽油
 * 开往 0 号加油站，此时油箱有 4 - 3 + 2 = 3 升汽油
 * 开往 1 号加油站，此时油箱有 3 - 3 + 3 = 3 升汽油
 * 你无法返回 2 号加油站，因为返程需要消耗 4 升汽油，但是你的油箱只有 3 升汽油。
 * 因此，无论怎样，你都不可能绕环路行驶一周。
 */
public class Q134CanCompleteCircuit {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas.length == 1) return gas[0] >= cost[0] ? 0 : -1;

        List<Integer> startIdxList = new ArrayList<>();
        for (int i = 0; i < gas.length; i++) {
            if (gas[i] >= cost[i]) {
                startIdxList.add(i);
            }
        }
        List<Integer> newIdx = trimDuplicates(startIdxList);
        if (newIdx.isEmpty()) return -1;

        for (int startIdx : newIdx) {
            int iter = (startIdx + 1) % gas.length;
            int remaining = gas[startIdx] - cost[startIdx];
            while (remaining + gas[iter] >= cost[iter] && iter != startIdx) {
                remaining = remaining + gas[iter] - cost[iter];
                iter = (iter + 1) % gas.length;
            }
            if (iter == startIdx) return startIdx;
        }
        return -1;
    }

    /**
     * 若有连续(大于等于2)的start idx则只保留第一个idx，如
     * [0, 1, 2] -> [0]
     */
    private List<Integer> trimDuplicates(List<Integer> startIdx) {
        List<Integer> res = new ArrayList<>();

        int i = 0;
        while (i < startIdx.size()) {
            if (i + 1 < startIdx.size() && startIdx.get(i) + 1 == startIdx.get(i + 1)) {
                int start = startIdx.get(i);
                while (i < startIdx.size() - 1 && startIdx.get(i) + 1 == startIdx.get(i + 1)) {
                    i++;
                }
                res.add(start);
            } else {
                res.add(startIdx.get(i));
                i++;
            }
        }
        return res;
    }

    @Test
    public void testCase01() {
        List<Integer> list = new ArrayList<>(Arrays.asList(0, 1, 2, 4, 5, 11, 15, 20, 21));
        System.out.println(trimDuplicates(list));
    }

    @Test
    public void testCase02() {
        int[] gas = {1, 2, 3, 4, 5};
        int[] cost = {3, 4, 5, 1, 2};
        System.out.println(canCompleteCircuit(gas, cost));
    }

    @Test
    public void testCase03() {
        int[] gas = {2, 3, 4};
        int[] cost = {3, 4, 3};
        System.out.println(canCompleteCircuit(gas, cost));
    }

    @Test
    public void testCase04() {
        int[] gas = {2, 0};
        int[] cost = {2, 1};
        System.out.println(canCompleteCircuit(gas, cost));
    }

    @Test
    public void testCase05() {
        int[] gas = {5, 1, 2, 3, 4};
        int[] cost = {4, 4, 1, 5, 1};
        System.out.println(canCompleteCircuit(gas, cost));
    }
}
