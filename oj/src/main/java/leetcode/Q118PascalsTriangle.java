package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 */
public class Q118PascalsTriangle {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if (numRows == 0) return res;

        res.add(new ArrayList<>(Arrays.asList(1)));
        if (numRows == 1) return res;

        res.add(new ArrayList<>(Arrays.asList(1, 1)));
        if (numRows == 2) return res;

        for (int i = 2; i < numRows; i++) {
            List<Integer> local = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    local.add(1);
                    continue;
                }

                int sum = res.get(i - 1).get(j - 1) + res.get(i - 1).get(j);
                local.add(sum);
            }
            res.add(local);
        }

        return res;
    }

    @Test
    public void testCase01() {
        int numRows = 5;
        List<List<Integer>> result = generate(numRows);
        System.out.println(result);
    }
}
