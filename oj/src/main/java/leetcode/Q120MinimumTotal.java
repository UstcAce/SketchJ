package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q120MinimumTotal {
    /**
     * 1. 定义动态规划求解问题 dp[i][j]表示到达 第i行第j列的最小和
     * 2. dp[i][j]; i >= j
     * (1) i = 0, j = 0; dp[0][0] = triangle[0][0]
     * (2) i != 0, j == 0; dp[i][0] = dp[i-1][0] + triangle[i][0]
     * (3) i != 0, j == i; dp[i][i] = dp[i-1][i-1] + triangle[i][i]
     * (4) 其他情况；dp[i][j] = min(dp[i-1][j], dp[i-1][j-1]) + triangle[i][j]
     */
    public int maxTrianglePath(List<List<Integer>> triangle) {
        int len = triangle.size();
        if (len == 1) return triangle.get(0).get(0);

        int[][] dp = new int[len][len];

        int maxArea = Integer.MIN_VALUE;

        for (int i = 0; i < len; i++) {
            for (int j = 0; j <= i; j++) {
                int localMax;
                if (j == 0) {
                    localMax = i == 0 ? 0 : dp[i - 1][0];
                } else if (j == i) {
                    localMax = dp[i - 1][i - 1];
                } else {
                    localMax = Math.max(dp[i - 1][j], dp[i - 1][j - 1]);
                }
                localMax += triangle.get(i).get(j);

                dp[i][j] = localMax;

                if (i == len - 1) {
                    maxArea = Math.max(maxArea, localMax);
                }
            }
        }
        return maxArea;
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        int len = triangle.size();
        if (len == 1) return triangle.get(0).get(0);

        int[] dp = new int[len];

        int minArea = Integer.MAX_VALUE;

        for (int i = 0; i < len; i++) {
            for (int j = i; j >= 0; j--) {
                int localMin;
                if (j == 0) {
                    localMin = dp[0];
                } else if (j == i) {
                    localMin = dp[j - 1];
                } else {
                    localMin = Math.min(dp[j], dp[j - 1]);
                }
                localMin += triangle.get(i).get(j);

                dp[j] = localMin;

                if (i == len - 1) {
                    minArea = Math.min(minArea, localMin);
                }
            }
        }
        return minArea;
    }

    @Test
    public void testCase01() {
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(Arrays.asList(7));
        triangle.add(Arrays.asList(3, 8));
        triangle.add(Arrays.asList(8, 1, 0));
        triangle.add(Arrays.asList(2, 7, 4, 5));
        triangle.add(Arrays.asList(4, 5, 2, 6, 5));
        System.out.println(maxTrianglePath(triangle));
    }

    @Test
    public void testCase02() {
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(Arrays.asList(2));
        triangle.add(Arrays.asList(3, 4));
        triangle.add(Arrays.asList(6, 5, 7));
        triangle.add(Arrays.asList(4, 1, 8, 3));
        System.out.println(minimumTotal(triangle));
    }
}
