package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。
 * <p>
 * 找到所有在 [1, n] 范围之间没有出现在数组中的数字。
 * <p>
 * 您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * [4,3,2,7,8,2,3,1]
 * <p>
 * 输出:
 * [5,6]
 */
public class Q448FindDisappearedNumbers {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int len = nums.length;
        List<Integer> res = new ArrayList<>();

        if (len == 1) return res;

        for (int i = 0; i < len; i++) {
            while (nums[i] != i + 1 && nums[i] != Integer.MAX_VALUE) {
                trySwap(i, nums[i] - 1, nums);
            }
        }

        for (int i = 0; i < len; i++) {
            if (i + 1 != nums[i]) {
                res.add(i + 1);
            }
        }

        return res;
    }

    private void trySwap(int i, int j, int[] nums) {
        if (nums[j] == j + 1) {
            nums[i] = Integer.MAX_VALUE;
        } else {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

    @Test
    public void testCase01() {
        int[] nums = {4, 3, 2, 7, 8, 2, 3, 1};
        List<Integer> res = findDisappearedNumbers(nums);
        System.out.println(res);
    }
}
