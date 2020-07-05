package leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 * 实现一个基本的计算器来计算一个简单的字符串表达式的值。
 * 字符串表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格  。 整数除法仅保留整数部分。
 *
 * 示例 1:
 * 输入: "3+2*2"
 * 输出: 7
 *
 * 示例 2:
 * 输入: " 3/2 "
 * 输出: 1
 *
 * 示例 3:
 * 输入: " 3+5 / 2 "
 * 输出: 5
 *
 * 说明：
 * 你可以假设所给定的表达式都是有效的。 请不要使用内置的库函数 eval。
 * 字符串表达式仅包含非负整数
 */
public class Q227BasicCalculator2 {
    public int calculate(String s) {
        // 去除所有空格
        s = s.replaceAll(" ", "");
        Stack<Integer> stack = new Stack<>();
        int idx = 0;
        while (idx < s.length()) {
            int next = getNextNotNumIdx(s, idx);
            char c = s.charAt(idx);
            if (Character.isDigit(c)) {
                int num = Integer.parseInt(s.substring(idx, next));
                stack.add(num);
            } else if (c == '+') {
                int num = Integer.parseInt(s.substring(idx + 1, next));
                stack.add(num);
            } else if (c == '-') {
                int num = Integer.parseInt(s.substring(idx + 1, next));
                stack.add(-num);
            } else if (c == '*') {
                int num = Integer.parseInt(s.substring(idx + 1, next));
                stack.add(num * stack.pop());
            } else {
                int num = Integer.parseInt(s.substring(idx + 1, next));
                stack.add(stack.pop() / num);
            }
            idx = next;
        }
        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }

    /**
     * 获取下一个非数字的字符下标
     * 没有则返回 len
     */
    private int getNextNotNumIdx(String s, int idx) {
        for (int i = idx + 1; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) {
                return i;
            }
        }
        return s.length();
    }

    @Test
    public void testCase01() {
        String input = "2*2";
        int res = calculate(input);
        int except = 4;
        Assert.assertEquals(except, res);
    }


    @Test
    public void testCase02() {
        String input = "3+2*2";
        int res = calculate(input);
        int except = 7;
        Assert.assertEquals(except, res);
    }

    @Test
    public void testCase03() {
        String input = "3/2";
        int res = calculate(input);
        int except = 1;
        Assert.assertEquals(except, res);
    }

    @Test
    public void testCase04() {
        String input = " 3+5 / 2 ";
        int res = calculate(input);
        int except = 5;
        Assert.assertEquals(except, res);
    }

    @Test
    public void testCase05() {
        String input = "2*2/2";
        int res = calculate(input);
        int except = 2;
        Assert.assertEquals(except, res);
    }

    @Test
    public void testCase06() {
        String input = "0+20*2/2-1";
        int res = calculate(input);
        int except = 19;
        Assert.assertEquals(except, res);
    }

    @Test
    public void testCase07() {
        String input = "0-1";
        int res = calculate(input);
        int except = -1;
        Assert.assertEquals(except, res);
    }

    @Test
    public void testCase08() {
        String input = "1";
        int res = calculate(input);
        int except = 1;
        Assert.assertEquals(except, res);
    }
}
