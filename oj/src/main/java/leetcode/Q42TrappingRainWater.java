package leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class Q42TrappingRainWater {
    public int trap(int[] height) {
        int len = height.length;
        if (len <= 2) {
            return 0;
        }

        int result = 0;
        int existMax = height[0];
        Stack<Integer> stack = new Stack<>();
        stack.add(height[0]);
        for (int i = 1; i < len; i++) {
            int h = height[i];
            if (h >= existMax) {
                while (!stack.isEmpty()) {
                    int pop = stack.pop();
                    result += (existMax - pop);
                }
                existMax = h;
            }
            stack.add(h);
        }
        if (stack.size() <= 2) {
            return result;
        } else {
            int newLen = stack.size();
            int[] newHeight = new int[newLen];
            for (int i = 0; i < newLen; i++) {
                newHeight[i] = stack.pop();
            }
            return result + trap(newHeight);
        }
    }

    public int trap2(int[] height) {
        int len = height.length;

        if (len <= 1) {
            return 0;
        }

        List<Integer> heightList = Arrays.stream(height).boxed().distinct().sorted().collect(Collectors.toList());

        int rainCount = 0;

        for (int i = 1; i < heightList.size(); i++) {
            int[] local = calcHeightNum(height, heightList.get(i));
            if (local[0] > 1) {
                rainCount = rainCount + local[1] * (heightList.get(i) - heightList.get(i - 1));
            }
        }

        return rainCount;
    }

    /**
     * [countLarger(>=), countSmaller(<), firstIdx, lastIdx]
     * 1. 大于等于givenHeight的柱子的数目，在[firstIdx, lastIdx]区间内
     * 2. 在[firstIdx, lastIdx]区间内小于givenHeight的柱子的数目
     * 3. 大于等于givenHeight的柱子的起始idx
     * 4. 小于givenHeight的柱子的末尾idx
     */
    private int[] calcHeightNum(int[] height, int givenHeight) {
        int[] result = new int[4];
        result[2] = -1;

        for (int i = 0; i < height.length; i++) {
            if (height[i] >= givenHeight) {
                result[0] += 1;
                result[2] = result[2] == -1 ? i : result[2];
                result[3] = i;
            }
        }

        for (int i = result[2]; i <= result[3]; i++) {
            if (height[i] < givenHeight) {
                result[1] += 1;
            }
        }

        return result;
    }

    @Test
    public void testCase01() {
        int[] input = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(trap(input));
        System.out.println(trap2(input));
    }
}
