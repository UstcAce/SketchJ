package leetcode;

import java.util.LinkedList;

/**
 * 给定一个整数数据流和一个窗口大小，根据该滑动窗口的大小，计算其所有整数的移动平均值。
 *
 * 示例:
 *
 * MovingAverage m = new MovingAverage(3);
 * m.next(1) = 1
 * m.next(10) = (1 + 10) / 2
 * m.next(3) = (1 + 10 + 3) / 3
 * m.next(5) = (10 + 3 + 5) / 3
 */
public class Q346MovingAverage {
    private int size;

    private LinkedList<Integer> queue;

    private int sum;
    /** Initialize your data structure here. */
    public Q346MovingAverage(int size) {
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
