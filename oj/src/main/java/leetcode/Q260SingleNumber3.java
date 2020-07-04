package leetcode;

import org.junit.Test;

/**
 * 给定一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。
 * <p>
 * 示例 :
 * 输入: [1,2,1,3,2,5] 输出: [3,5]
 * <p>
 * 注意：
 * 结果输出的顺序并不重要，对于上面的例子， [5, 3] 也是正确答案。 你的算法应该具有线性时间复杂度。你能否仅使用常数空间复杂度来实现？
 */
public class Q260SingleNumber3 {
    public int[] singleNumber(int[] nums) {
        int xorTotal = 0;
        for (int val : nums) {
            xorTotal ^= val;
        }
        // xorTotal从右数第一个为1的二进制对应的数值(Integer.lowestOneBit(xorTotal))，根据与它按位与(&)是否等于它本身来将原数组划分成2种
        int lastOne = (xorTotal & (xorTotal - 1)) ^ xorTotal;
        int first = 0;
        int second = 0;
        for (int val : nums) {
            if ((val & lastOne) == lastOne) {
                first ^= val;
            } else {
                second ^= val;
            }
        }
        return new int[]{first, second};
    }

    @Test
    public void testCase01() {
        System.out.println(Integer.toBinaryString(-1));
        int signedMoveRight = -1 >> 1;
        int unSignedMoveRight = -1 >>> 1;
        System.out.println(signedMoveRight);
        System.out.println(Integer.toBinaryString(signedMoveRight));
        System.out.println(Integer.lowestOneBit(signedMoveRight));

        System.out.println(unSignedMoveRight);
        System.out.println(Integer.toBinaryString(unSignedMoveRight));
        System.out.println(Integer.lowestOneBit(unSignedMoveRight));

        System.out.println(Integer.lowestOneBit(4));
    }

    @Test
    public void testCase02() {
        for (int i = -10; i <= 10; i++) {
        System.out.println(Integer.toBinaryString(i ^ (i - 1)));
        }
    }
}
