package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * 注意：给定 n 是一个正整数。
 *
 * 示例 1：
 *
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * 示例 2：
 *
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 *
 */
public class Q70ClimbingStairs {
    /**
     * 1. 定义动态规划求解问题 f(n)爬到n阶的不同种方法
     * 2. 状态转移方程：f(n) = f(n-1) + f(n-2), n>=3
     * 3. 边界情况 f(1) = 1, f(2) = 2
     */
    private Map<Integer, Integer> map = new HashMap<>();

    public int climbStairs(int n) {
        if (n==1) {
            return 1;
        } else if (n==2) {
            return 2;
        }

        int val = (map.containsKey(n-1) ? map.get(n-1) : climbStairs(n-1))
                + (map.containsKey(n-2) ? map.get(n-2) : climbStairs(n-2));

        map.put(n, val);

        return val;
    }
}
