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
