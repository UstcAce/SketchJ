package leetcode;

import java.util.Stack;

class MinStack2 {

    private Stack<Integer> stack1;

    private Stack<Integer> stack2;

    /** initialize your data structure here. */
    public MinStack2() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void push(int x) {
        if (stack1.empty()) {
            stack2.push(x);
        } else {
            int min = Math.min(stack2.peek(), x);
            stack2.push(min);
        }
        stack1.push(x);
    }

    public void pop() {
        stack1.pop();
        stack2.pop();
    }

    public int top() {
        return stack1.peek();
    }

    public int getMin() {
        return stack2.peek();
    }
}
