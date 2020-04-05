package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给出一个无重叠的 ，按照区间起始端点排序的区间列表。
 * <p>
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 * <p>
 * 示例 1:
 * 输入: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * 输出: [[1,5],[6,9]]
 * <p>
 * 示例 2:
 * 输入: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * 输出: [[1,2],[3,10],[12,16]]
 * 解释: 这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
 */
public class Q57InsertInterval {
    /**
     * 读题：
     * 1. 区间按照起始端点升序排序
     * 2. 区间不重叠
     * <p>
     * 1. 新区间起始端点分别有2种情况
     * (1) 在某个区间内
     * (2) 不在某个区间内
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int len1 = intervals.length;
        int len2 = newInterval.length;

        if (len1 == 0 && len2 == 0) {
            return new int[0][0];
        } else if (len1 == 0) {
            int[][] res = new int[1][2];
            res[0][0] = newInterval[0];
            res[0][1] = newInterval[1];
            return res;
        } else if (len2 == 0) {
            return intervals;
        }

        int left = findPos(intervals, newInterval[0]);
        int right = findPos(intervals, newInterval[1]);

        int[] mergeInterval = new int[2];

        mergeInterval[0] = left == -1 ? newInterval[0] : intervals[left][0];
        mergeInterval[1] = right == -1 ? newInterval[1] : intervals[right][1];
        List<Integer> startList = new ArrayList<>();
        List<Integer> endList = new ArrayList<>();

        for (int[] v : intervals) {
            if (!(v[0] >= mergeInterval[0] && v[0] <= mergeInterval[1])
                    && !(v[1] >= mergeInterval[0] && v[1] <= mergeInterval[1])) {
                startList.add(v[0]);
                endList.add(v[1]);
            }
        }

        // 将mergeInterval插入到startList和endList中
        int pre = Integer.MIN_VALUE;
        int insertIndex = 0;
        int tmpSize = startList.size();
        for (int i = 0; i < startList.size(); i++) {
            if (pre <= mergeInterval[0] && startList.get(i) >= mergeInterval[0]) {
                insertIndex = i;
                startList.add(i, mergeInterval[0]);
                break;
            }
            pre = startList.get(i);
        }

        if (tmpSize == startList.size()) {
            insertIndex = startList.size();
            startList.add(insertIndex, mergeInterval[0]);
        }

        endList.add(insertIndex, mergeInterval[1]);

        int[][] res = new int[startList.size()][2];

        for (int i = 0; i < res.length; i++) {
            res[i][0] = startList.get(i);
            res[i][1] = endList.get(i);
        }

        return res;
    }

    private int findPos(int[][] intervals, int val) {
        for (int i = 0; i < intervals.length; i++) {
            if (val >= intervals[i][0] && val <= intervals[i][1]) {
                return i;
            }
        }

        return -1;
    }

    @Test
    public void testCase01() {
        int[][] intervals = {{1, 3}, {6, 9}};
        int[] newInterval = {2, 5};
        int[][] res = insert(intervals, newInterval);
        for (int[] v : res) {
            System.out.println(Arrays.toString(v));
        }
    }

    @Test
    public void testCase02() {
        int[][] intervals = {{1,2},{3,5},{6,7},{8,10},{12,16}};
        int[] newInterval = {4, 8};
        int[][] res = insert(intervals, newInterval);
        for (int[] v : res) {
            System.out.println(Arrays.toString(v));
        }
    }

}
