package leetcode;

import org.junit.Test;

/**
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。找出那个只出现了一次的元素。
 *
 * <p>说明：
 * <p>你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 *
 *  <p>示例 1:
 * <p>输入: [2,2,3,2] 输出: 3 示例 2:
 * <p>输入: [0,1,0,1,0,1,99] 输出: 99
 */
public class Q137SingleNumber2 {
    public int singleNumber(int[] nums) {
        int[] count = new int[32];
        for (int val : nums) {
            // 统计每一位上1的出现次数
            for (int i = 0; i < 32; i++) {
                count[i] += val & 1;
                // 无符号右移，主要处理负数右移除2的情况
                val = val >>> 1;
            }
        }
        int res = 0;
        int m = 3;
        for (int i = 0; i < 32; i++) {
            res = res << 1;
            res = res | (count[31 - i] % m);
        }
        return res;
    }

    public int singleNumber2(int[] nums) {
        // 第i位表示二进制右数第i位出现1的次数
        int[] count = new int[32];
        for (int val : nums) {
            for (int i = 0; i < 32; i++) {
                count[i] += val & 1;
                val = val >>> 1;
            }
        }
        int res = 0;
        int m = 3;
        for (int i = 0; i < 32; i++) {
            int bitOne = 1 << i;
            if (count[i] % m == 1) {
                res = res | bitOne;
            }
        }
        return res;
    }

    /**
     * <<: 左移补0
     * >>: 带符号右移，正数补0，负数补1
     * >>>: 无符号右移，正，负数都补0，
     */
    @Test
    public void testCase01() {
        System.out.println(1 >> 1);

        System.out.println(Integer.toBinaryString(-1));
        int signedMoveRight = -1 >> 1;
        int unSignedMoveRight = -1 >>> 1;
        System.out.println(signedMoveRight);
        System.out.println(Integer.toBinaryString(signedMoveRight));

        System.out.println(unSignedMoveRight);
        System.out.println(Integer.toBinaryString(unSignedMoveRight));
    }

    @Test
    public void testCase02() {
        int[] input = {-2,-2,1,1,-3,1,-3,-3,-4,-2};
        System.out.println(singleNumber2(input));
    }
}
