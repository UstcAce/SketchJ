package leetcode;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 *
 * 面积 = 高度 * 长度，固定高度，确定长度
 */
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

    /**
     * 维护一个单调递减的栈
     */
    public int largestRectangleArea3(int[] heights) {
        int len = heights.length;
        if (len == 0) return 0;

        int maxArea = 0;
        // 维护一个下标栈，其对应的高度单调递减
        LinkedList<Integer> stack = new LinkedList<>();
        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && heights[i] < heights[stack.peekLast()]) {
                int popIdx = stack.pollLast();
                int currHeight = heights[popIdx];
                int currWidth;
                if (stack.isEmpty()) {
                    currWidth = i;
                } else {
                    currWidth = i - stack.peekLast() - 1;
                }
                maxArea = Math.max(maxArea, currWidth * currHeight);
            }
            stack.addLast(i);
        }

        while (!stack.isEmpty()) {
            int popIdx = stack.pollLast();
            int currHeight = heights[popIdx];
            int currWidth;
            if (stack.isEmpty()) {
                currWidth = len;
            } else {
                currWidth = len - stack.peekLast() - 1;
            }
            maxArea = Math.max(maxArea, currWidth * currHeight);
        }

        return maxArea;
    }

    @Test
    public void testCase01() {
         int[] heights = {5,4,1,2};
//        int[] heights = {1, 1};
        int res = largestRectangleArea(heights);
        System.out.println(res);
    }

}
