package leetcode;

import java.util.PriorityQueue;

/**
 * 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
 *
 * 例如，
 * [2,3,4] 的中位数是 3
 * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 * 设计一个支持以下两种操作的数据结构：
 * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 * double findMedian() - 返回目前所有元素的中位数。
 */
public class Q295MedianFinder {
    // 存放前一半小的数，堆顶元素最大
    private PriorityQueue<Integer> maxHeap;

    // 存放后一半大的数，栈顶元素最小
    private PriorityQueue<Integer> minHeap;

    public Q295MedianFinder() {
        maxHeap = new PriorityQueue<>((it1, it2) -> it2 - it1);
        minHeap = new PriorityQueue<>();
    }

    /**
     * 为了找到添加新数据以后，数据流的中位数，我们让这个新数据在大顶堆和小顶堆中都走了一遍。
     * 而为了让大顶堆的元素多 11 个，我们让从小顶堆中又拿出一个元素“送回”给大顶堆；
     */
    public void addNum(int num) {
        maxHeap.offer(num);
        minHeap.offer(maxHeap.poll());
        if ((maxHeap.size() + minHeap.size()) % 2 == 1) {
            maxHeap.offer(minHeap.poll());
        }
    }

    public double findMedian() {
        if ((maxHeap.size() + minHeap.size()) % 2 == 0) {
            return (maxHeap.peek() + minHeap.peek()) * 1d / 2;
        } else {
            return maxHeap.peek();
        }
    }
}
