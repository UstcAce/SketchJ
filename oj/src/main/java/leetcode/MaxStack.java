package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 设计一个最大栈，支持 push、pop、top、peekMax 和 popMax 操作。
 *
 * push(x) -- 将元素 x 压入栈中。
 * pop() -- 移除栈顶元素并返回这个值。
 * top() -- 返回栈顶元素。
 * peekMax() -- 返回栈中最大元素。
 * popMax() -- 返回栈中最大的元素，并将其删除。如果有多个最大元素，只要删除最靠近栈顶的那个。
 */
public class MaxStack {
    private Stack<Integer> stack;

    private Stack<Integer> maxStack;

    /** initialize your data structure here. */
    public MaxStack() {
        stack = new Stack<>();
        maxStack = new Stack<>();
    }

    public void push(int x) {
        if (stack.isEmpty()) {
            maxStack.push(x);
        } else {
            int popVal = maxStack.peek();
            maxStack.push(Math.max(popVal, x));
        }
        stack.push(x);
    }

    public int pop() {
        int popVal = stack.pop();
        maxStack.pop();
        return popVal;
    }

    public int top() {
        return stack.peek();
    }

    public int peekMax() {
        return maxStack.peek();
    }

    public int popMax() {
        int max = maxStack.peek();
        int popVal;
        List<Integer> list = new ArrayList<>();
        while (true) {
            popVal = stack.pop();
            maxStack.pop();
            if (popVal != max) {
                list.add(popVal);
            } else {
                break;
            }
        }
        for (int i = list.size()-1; i>=0 ;i--) {
            push(list.get(i));
        }
        return max;
    }

    @Test
    public void testCase01() {
        MaxStack maxStack = new MaxStack();
        maxStack.push(5);
        System.out.println(maxStack.peekMax());
        System.out.println(maxStack.popMax());
    }
}

/**
 * Your MaxStack object will be instantiated and called as such:
 * MaxStack obj = new MaxStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.peekMax();
 * int param_5 = obj.popMax();
 */