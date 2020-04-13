package leetcode;

import org.junit.Test;

/**
 * 给你一个整数数组 arr，每一次操作你都可以选择并删除它的一个 回文 子数组 arr[i], arr[i+1], ..., arr[j]（ i <= j）。
 * 注意，每当你删除掉一个子数组，右侧元素都会自行向前移动填补空位。
 * 请你计算并返回从数组中删除所有数字所需的最少操作次数。
 *  
 *
 * 示例 1：
 * 输入：arr = [1,2]
 * 输出：2
 *
 * 示例 2：
 * 输入：arr = [1,3,4,1,5]
 * 输出：3
 * 解释：先删除 [4]，然后删除 [1,3,1]，最后再删除 [5]。
 *
 */
public class Q1246PalindromeRemoval {
    /**
     * 1. 定义动态规划求解问题：dp[i][j]表示从下标i到下标j即区间[i, j]删除所有数字的最小操作次数
     * 2. dp[i][j] (注意状态转移顺序)
     * (1) arr[i] == arr[j], dp[i][j] = min(dp[i+1][j-1], dp[i][k] + dp[k+1][j]), i <= k < j
     * (2) arr[i] != arr[j], dp[i][j] = min(j-i+1, dp[i][k] + dp[k+1][j]), i <= k < j
     * 3. 边界条件dp[i][i] = 1, dp[i][i+1] = arr[i] == arr[i+1] ? 2 : 1
     */
    public int minimumMoves(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        if (arr.length == 1) return 1;
        int len = arr.length;
        int[][] dp = new int[len][len];

        for (int j = 0; j < len; j++) {
            for (int i = j; i >= 0; i--) {
                if (i == j) {
                    dp[i][j] = 1;
                } else if (i + 1 == j) {
                    dp[i][j] = arr[i] == arr[j] ? 1 : 2;
                } else {
                    int min = j - i + 1;
                    if (arr[i] == arr[j]) {
                        min = Math.min(min, dp[i + 1][j - 1]);
                    }
                    for (int k = i; k < j; k++) {
                        min = Math.min(min, dp[i][k] + dp[k+1][j]);
                    }
                    dp[i][j] = min;
                }
            }
        }
        return dp[0][len - 1];
    }

    @Test
    public void testCase01() {
        int[] arr = {1, 3, 4, 1, 5};
        System.out.println(minimumMoves(arr));
    }
}
