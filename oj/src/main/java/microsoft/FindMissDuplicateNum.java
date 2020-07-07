package microsoft;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FindMissDuplicateNum {
    /**
     * 长为N的数组中，每个值都是1-N范围内的，现在有某个数字出现了两次，某个数字丢失了，其他的数字仅出现一次，求重复的数和丢失的数。
     * @ return [missNum, duplicateNum]
     */
    public int[] findMissDuplicateNum(int[] nums) {
        int len = nums.length;

        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += nums[i];
        }

        int duplicateNum = 0;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < len; i++) {
            if (set.contains(nums[i])) {
                duplicateNum = nums[i];
                break;
            }
            set.add(nums[i]);
        }
        int missNum = (len + 1) * len / 2 - sum + duplicateNum;
        return new int[]{missNum, duplicateNum};
    }

    /**
     * 解法2：
     * 首先该题中一个数组中的值可以对应数组的一个下标（[1,N] -> [0,N-1]）。
     * 从前向后遍历数组，取到每一个值，就对对应下标位置的值做乘以-1的操作，
     * 这样既存储了额外信息，也不会丢失原信息（取绝对值就是原来的值）。
     * 最后数组中剩两个正值，一个是乘了两次-1，一个是乘了0次-1，
     * 再稍加判断就可以得到结果
     */

    @Test
    public void testCase01() {
        int[] input = {1, 1, 3, 4};
        int[] res = findMissDuplicateNum(input);
        System.out.println(Arrays.toString(res));
    }

    @Test
    public void testCase02() {
        int[] input = {1, 3, 1, 4};
        int[] res = findMissDuplicateNum(input);
        System.out.println(Arrays.toString(res));
    }
}
