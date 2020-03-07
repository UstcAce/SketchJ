package leetcode;

import java.util.LinkedList;

public class MovingAverage {
    private int size;

    private LinkedList<Integer> queue;

    private int sum;
    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        this.size = size;
        queue = new LinkedList<>();
        sum = 0;
    }

    public double next(int val) {
        if (queue.size() == size) {
            int popVal = queue.removeFirst();
            sum -= popVal;
        }
        queue.add(val);
        sum += val;
        return sum * 1.0d / queue.size();
    }
}
