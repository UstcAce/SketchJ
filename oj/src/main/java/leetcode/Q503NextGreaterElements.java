package leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.Stack;

public class Q503NextGreaterElements {
    public int[] nextGreaterElements(int[] nums) {
        int len = nums.length;
        if (len == 0) return new int[0];

        int[] newArr = new int[2 * len];
        System.arraycopy(nums, 0, newArr, 0, len);
        System.arraycopy(nums, 0, newArr, len, len);

        int[] res = new int[len];
        Arrays.fill(res, -1);
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < 2 * len; i++) {
            if (stack.isEmpty() || newArr[stack.peek()] >= newArr[i]) {
                stack.add(i);
            } else {
                while (!stack.isEmpty() && newArr[stack.peek()] < newArr[i]) {
                    int idx = stack.pop();
                    if (idx < len) {
                        res[idx] = newArr[i];
                    } else {
                        res[idx - len] = newArr[i];
                    }
                }
                stack.add(i);
            }
        }
        return res;
    }

    public int[] nextGreaterElement2(int[] nums) {
        int len = nums.length;
        if (len == 0) return new int[0];

        int[] res = new int[len];
        Arrays.fill(res, -1);
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < 2 * len; i++) {
            int trueId = i < len ? i : i - len;
            if (stack.isEmpty() || nums[stack.peek()] >= nums[trueId]) {
                stack.add(trueId);
            } else {
                while (!stack.isEmpty() && nums[stack.peek()] < nums[trueId]) {
                    int idx = stack.pop();
                    if (idx < len) {
                        res[idx] = nums[trueId];
                    } else {
                        res[idx - len] = nums[trueId];
                    }
                }
                stack.add(trueId);
            }
        }
        return res;
    }

    @Test
    public void testCase01() {
        int[] input = {1, 2, 1};
        System.out.println(Arrays.toString(nextGreaterElement2(input)));
    }
}
