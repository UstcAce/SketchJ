package leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

public class Q239MaxSlidingWindow {

    /**
     * 单调递减队列，双端队列实现
     */
    class DecreasingDeque{
        private LinkedList<Integer> deque;

        public DecreasingDeque() {
            deque = new LinkedList<>();
        }

        public void push(int n) {
            while (!deque.isEmpty() && deque.getLast() < n) {
                deque.removeLast();
            }
            deque.addLast(n);
        }

        /**
         * 需要保证deque非空
         */
        public int max () {
            return deque.getFirst();
        }

        /**
         * 需要保证 n 在deque内部
         */
        public void pop(int n) {
            if (!deque.isEmpty() && deque.getFirst() == n) {
                deque.removeFirst();
            }
        }
    }


    public int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        if (len == 0) {
            return new int[0];
        }

        DecreasingDeque deque = new DecreasingDeque();
        int[] res = new int[len - k + 1];

        for (int i = 0; i < len; i++) {
            if (i < k - 1) {  // 先填满窗口的前 k - 1 项
                deque.push(nums[i]);
            } else {
                int idx = i - (k - 1);
                deque.push(nums[i]);
                res[idx] = deque.max();
                deque.pop(nums[idx]);
            }
        }
        return res;
    }

    /**
     * 需要一个数据结构，能在删除和增加操作后，log(n)时间复杂度下得到最大值
     */
    public int[] maxSlidingWindow2(int[] nums, int k) {
        int len = nums.length;

        if (len == 0) {
            return new int[]{};
        }

        int resArrLen = len - k + 1;
        int[] res = new int[resArrLen];

        int end = k - 1;
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i <= end; i++) {
            queue.add(nums[i]);
        }

        res[0] = calcMaxVal(queue);

        int j = 1;
        while (j < resArrLen) {
            queue.removeFirst();
            end = end + 1;
            queue.add(nums[end]);
            res[j] = calcMaxVal(queue);
            j += 1;
        }

        return res;
    }

    private int calcMaxVal(LinkedList<Integer> queue) {
        int max = Integer.MIN_VALUE;
        for (int val : queue) {
            max = Math.max(val, max);
        }
        return max;
    }

    /**
     * len = 8;
     * resArrLen = 6
     *
     * output = [3,3,5,5,6,7]
     */
    @Test
    public void testCase01() {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 8;
        int[] res = maxSlidingWindow(nums, k);

        System.out.println(Arrays.toString(res));
    }

    @Test
    public void testCase02() {
        int[] nums = {};
        int k = 0;
        int[] res = maxSlidingWindow(nums, k);

        System.out.println(Arrays.toString(res));
    }
}
