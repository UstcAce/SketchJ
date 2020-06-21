package leetcode;

import org.junit.Test;

import java.util.LinkedList;

/**
 * 给定一个仅包含 0 和 1 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 * 示例:
 *
 * 输入:
 * [
 *   ["1","0","1","0","0"],
 *   ["1","0","1","1","1"],
 *   ["1","1","1","1","1"],
 *   ["1","0","0","1","0"]
 * ]
 * 输出: 6
 */
public class Q85MaximalRectangle {
    /**
     * 每一行往上可以看作一个直方图，问题便个转化为求直方图中的最大矩形面积了。
     */
    public int maximalRectangle(char[][] matrix) {
        int rowNum = matrix.length;
        if (rowNum == 0) return 0;
        int colNum = matrix[0].length;
        if (colNum == 0) return 0;

        int maxArea = 0;
        int[] heights = new int[colNum];
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                heights[j] = matrix[i][j] == '0' ? 0 : heights[j] + 1;
            }
            maxArea = Math.max(largestRectangleArea(heights), maxArea);
        }
        return maxArea;
    }

    /**
     * 维护一个单调递增的高度直方图的下标栈
     */
    public int largestRectangleArea(int[] heights) {
        int len = heights.length;
        if (len == 0) {
            return 0;
        }

        int maxArea = 0;
        LinkedList<Integer> stack = new LinkedList<>();
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[stack.peekLast()] > heights[i]) {
                int popIdx = stack.removeLast();
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
            int popIdx = stack.removeLast();
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
    public void testCase00() {
        char[][] input = {
                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'}
        };
    }

    @Test
    public void testCase01() {
        char[][] input = {
                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'}
        };
        System.out.println(maximalRectangle(input));
    }

    @Test
    public void testCase02() {
        char[][] input = {
                {'1','0','1','1','1'},
                {'0','1','0','1','0'},
                {'1','1','0','1','1'},
                {'1','1','0','1','1'},
                {'0','1','1','1','1'}
        };
    }

    @Test
    public void testCase03() {
        char[][] input = {
                {'1','0','1','1','1'},
                {'0','1','0','1','0'},
                {'1','1','0','1','1'},
                {'1','1','0','1','1'},
                {'0','1','1','1','1'}
        };
        System.out.println(maximalRectangle(input));
    }
}
