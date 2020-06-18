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
     * 1. 定义动态规划求解问题 dp[i][j]表示从arr[i]~arr[j]删除所有数字的最小操作次数
     * 2. 状态转移方程 dp[i][j] i <= j
     * (1) j <= i + 1; dp[i][j] = arr[i] == arr[j] ? 1 : 2
     * (2) arr[i] == arr[j]; dp[i][j] = min(dp[i+1][j-1], dp[i][k] + dp[k+1][j]); i <= k < j
     * (3) arr[i] != arr[j]; dp[i][j] = min(dp[i][k] + dp[k+1][j]); i <= k < j
     * 3. 递推方向：
     * (1) dp[i+1][j-1] ->  dp[i][j] 从左下指向递推点
     * (2) dp[i][k] -> dp[i][j]; i <= k < j 从左边指向递推点
     * (3) dp[k+1][j] -> dp[i][j]; i <= k < j 从下方指向递推点
     */
    public int minimumMoves(int[] arr) {
        int len = arr.length;
        int[][] dp = new int[len][len];
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                if (j <= i + 1) {
                    dp[i][j] = arr[i] == arr[j] ? 1 : 2;
                } else {
                    int min = Integer.MAX_VALUE;
                    for (int k = i; k < j; k++) {
                        min = Math.min(min, dp[i][k] + dp[k + 1][j]);
                    }
                    if (arr[i] == arr[j]) {
                        min = Math.min(dp[i + 1][j - 1], min);
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
