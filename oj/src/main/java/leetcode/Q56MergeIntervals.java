package leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

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
}
