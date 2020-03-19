package leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
 * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * <p>
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 */
public class Q11ContainerWithMostWater {
    public int maxArea(int[] height) {
        int len = height.length;

        int maxArea = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                int area = (j - i) * Math.min(height[i], height[j]);
                maxArea = Math.max(area, maxArea);
            }
        }

        return maxArea;
    }

    public int maxArea2(int[] height) {
        int len = height.length;
        int maxArea = 0;

        int left = 0;
        int right = len - 1;
        while (left < right) {
            int area = (right - left) * Math.min(height[left], height[right]);
            maxArea = Math.max(area, maxArea);
            if (height[left] <= height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }

    @Test
    public void testCase01() {
        int[] input = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int res = maxArea2(input);
        Assert.assertEquals(49, res);
    }
}
