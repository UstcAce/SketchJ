package leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 给定一个整数数组，判断是否存在重复元素。
 *
 * 如果任何值在数组中出现至少两次，函数返回 true。如果数组中每个元素都不相同，则返回 false。
 */
public class Q217DuplicateArr {
    public boolean containsDuplicate(int[] nums) {
        int len = nums.length;
        Set<Integer> set = Arrays.stream(nums).boxed().collect(Collectors.toSet());
        return len != set.size();
    }

    public boolean containsDuplicate2(int[] nums) {
        int len = nums.length;
        Set<Integer> set = new HashSet<>();
        for (int i=0; i<len; i++) {
            set.add(nums[i]);
            if (set.size() != i+1) {
                return true;
            }
        }
        return false;
    }
}
