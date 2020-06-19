package leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * 返回滑动窗口中的最大值。
 *
 * 进阶：
 * 你能在线性时间复杂度内解决此题吗？
 *
 * 示例:
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 *   滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 */
public class Q239MaxSlidingWindow {

    /**
     * 单调递减双端队列
     */
    class DecreasingDeque{
        private LinkedList<Integer> deque;

        public DecreasingDeque() {
            this.deque = new LinkedList<>();
        }

        public void push(int val) {
            while (!deque.isEmpty() && val > deque.peekLast()) {
                deque.removeLast();
            }
            deque.addLast(val);
        }

        public void pop(int val) {
            if (!deque.isEmpty() && deque.peekFirst() == val) {
                deque.removeFirst();
            }
        }

        public int getMax() {
            return deque.peekFirst();
        }
    }

    /**
     * 维护一个单调递减双端队列
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length;

        DecreasingDeque deque = new DecreasingDeque();
        int[] res = new int[len - k + 1];
        for (int i = 0; i < len; i++) {
            if (i < k - 1) {
                deque.push(nums[i]);
            } else {
                deque.push(nums[i]);
                res[i - k + 1] = deque.getMax();
                deque.pop(nums[i - k + 1]);
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
        int k = 3;
        int[] res = maxSlidingWindow2(nums, k);

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
