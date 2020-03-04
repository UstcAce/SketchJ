package leetcode;

import org.junit.Test;

/**
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 *
 */
public class Q26RemoveDuplicates {
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;

        // 非重复元素递增下标
        int idx1 = 0;
        int res = 1;

        for (int i=1; i<len; i++) {
            if (nums[i] > nums[idx1]) {
                idx1 += 1;
                nums[idx1] = nums[i];
                res += 1;
            }
        }

        return res;
    }

    /**
     * 示例 1:
     * 给定数组 nums = [1,1,2],
     *
     * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
     *
     * 你不需要考虑数组中超出新长度后面的元素。
     * 示例 2:
     *
     * 给定 nums = [0,0,1,1,1,2,2,3,3,4],
     *
     * 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
     *
     * 你不需要考虑数组中超出新长度后面的元素。
     */
    @Test
    public void testCase01() {
        int [] intput = {0,0,1,1,1,2,2,3,3,4};

        int res = removeDuplicates(intput);
        System.out.println(res);

        for (int i=0; i<res; i++) {
            System.out.println(intput[i]);
        }
    }
}
