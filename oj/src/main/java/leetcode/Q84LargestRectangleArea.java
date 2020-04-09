package leetcode;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class Q84LargestRectangleArea {
    public int largestRectangleArea2(int[] heights) {
        int len = heights.length;
        if (len == 0) return 0;

        int max = 0;
        for (int i = 0; i < len; i++) {
            int left = i;
            // 比当前高度还高
            while (left > 0 && heights[left - 1] >= heights[i]) {
                left--;
            }

            int right = i;
            // 比当前高度还高
            while (right < len - 1 && heights[right + 1] >= heights[i]) {
                right++;
            }
            max = Math.max(max, (right - left + 1) * heights[i]);
        }

        return max;
    }

    public int largestRectangleArea(int[] heights) {
        int len = heights.length;
        if (len == 0) {
            return 0;
        }

        int res = 0;

        int[] newHeights = new int[len + 2];
        newHeights[0] = 0;
        System.arraycopy(heights, 0, newHeights, 1, len);
        len += 2;
        heights = newHeights;

        Deque<Integer> stack = new ArrayDeque<>(len);
        // 先放入哨兵，在循环里就不用做非空判断
        stack.addLast(0);

        for (int i = 1; i < len; i++) {
            while (heights[i] < heights[stack.peekLast()]) {
                int curHeight = heights[stack.pollLast()];
                int curWidth = i - stack.peekLast() - 1;
                res = Math.max(res, curHeight * curWidth);
            }
            stack.addLast(i);
        }
        return res;
    }

    public int largestRectangleArea3(int[] heights) {
        int len = heights.length;
        if (len == 0) return 0;

        int res = 0;

        int[] newHeights = new int[len + 2];
        System.arraycopy(heights, 0, newHeights, 1, len);
        len += 2;
        heights = newHeights;

        Stack<Integer> stack = new Stack<>();
        stack.add(0);

        for (int i = 1; i < len; i++) {
            while (heights[i] < heights[stack.peek()]) {
                int curHeight = heights[stack.pop()];
                int curWidth = i - stack.peek() - 1;
                res = Math.max(res, curHeight * curWidth);
            }
            stack.add(i);
        }
        return res;
    }

    @Test
    public void testCase01() {
         int[] heights = {2, 1, 5, 6, 2, 3};
//        int[] heights = {1, 1};
        int res = largestRectangleArea(heights);
        System.out.println(res);
    }

}
