package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class MinStack {

    private Stack<Integer> stack;

    private List<Integer> list;

    /** initialize your data structure here. */
    public MinStack() {
        stack = new Stack<>();
        list = new ArrayList<>();
    }

    public void push(int x) {
        stack.push(x);
        int i=0;
        for (int ele : list) {
            if (ele <= x) {
                break;
            }
            i++;
        }
        list.add(i, x);
    }

    public void pop() {
        int pop = stack.pop();
        list.remove((Integer) pop);
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return list.get(0);
    }

    @Test
    public void testCase01() {
        List<Integer> list = new ArrayList<>(Arrays.asList(0, 1,2,3));
        list.add(0, 111);
        System.out.println(list);
    }

    @Test
    public void testCase02() {
        MinStack stack = new MinStack();
        stack.push(-2);
        stack.push(0);
        stack.push(-3);
        System.out.println(stack.getMin());
    }
}
