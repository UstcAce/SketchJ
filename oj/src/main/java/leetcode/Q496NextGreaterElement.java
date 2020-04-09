package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Q496NextGreaterElement {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int len = nums2.length;
        Map<Integer, Integer> map = new HashMap<>();
        if (nums1.length == 0) return new int[0];

        // 从栈底到栈底下标对应的气温单调递减
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < len; i++) {
            if (stack.isEmpty() || nums2[stack.peek()] >= nums2[i]) {
                stack.add(i);
            } else {
                while (!stack.isEmpty() && nums2[stack.peek()] < nums2[i]) {
                    int index = stack.pop();
                    map.put(nums2[index], nums2[i]);
                }
                stack.add(i);
            }
        }

        int[] res = new int[nums1.length];

        for (int i  = 0; i < nums1.length; i++) {
            res[i] = map.getOrDefault(nums1[i], -1);
        }

        return res;
    }
}
