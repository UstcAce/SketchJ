package leetcode;

import java.util.*;

/**
 * 给出一个区间的集合，请合并所有重叠的区间。
 *
 * 示例 1:
 *
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为[1,6].
 *
 * 示例 2:
 * 输入: [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 *
 */
public class Q56MergeIntervals {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(it -> it[0]));
        LinkedList<int[]> list = new LinkedList<>();
        for (int [] arr : intervals) {
            if (list.isEmpty()) {
                list.add(arr);
            } else {
                int[] inverval = list.getLast();
                // 交叉
                if (inverval[1] >= arr[0]) {
                    list.removeLast();
                    if (arr[1]<=inverval[1]) {
                        list.add(new int[]{inverval[0], inverval[1]});
                    } else {
                        list.add(new int[]{inverval[0], arr[1]});
                    }
                } else {  // 不交叉
                    list.add(arr);
                }
            }
        }

        int len = list.size();
        int [][] newIntervals = new int[len][2];
        for (int i=0; i<len; i++) {
            int[] inverval = list.pop();
            newIntervals[i][0] = inverval[0];
            newIntervals[i][1] = inverval[1];
        }

        return newIntervals;
    }

    public int[][] merge2(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[][]{};
        }
        LinkedList<int[]> list = new LinkedList<>();
        Arrays.sort(intervals, Comparator.comparingInt(it -> it[0]));
        int[] pre = intervals[0];
        int maxCount = 1;
        int count = 1;
        for (int i = 1; i < intervals.length; i++) {
            int[] local = intervals[i];
            if (local[0] <= pre[1]) {
                pre[1] = Math.max(pre[1], local[1]);
                count++;
                maxCount = Math.max(maxCount, count);
            } else {
                list.add(pre);
                pre = local;
                count = 1;
            }
        }
        list.add(pre);
        int len = list.size();
        int [][] newIntervals = new int[len][2];
        for (int i=0; i<len; i++) {
            int[] inverval = list.pop();
            newIntervals[i][0] = inverval[0];
            newIntervals[i][1] = inverval[1];
        }
        return newIntervals;
    }
}
