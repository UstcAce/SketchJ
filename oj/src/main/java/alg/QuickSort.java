package alg;

import org.junit.Test;

import java.util.Arrays;

/**
 * QuickSort is a kind of sorting algorithm. It can be used to sort fixed array or dynamic array.
 * The time complexity of the algorithm is O(nlogn) on the average. But in the worst case, the time
 * complexity can be O(n^2). There are almost two major steps for the algorithm.
 *
 * 1.First step, partition: choose one element of the array as the pivot. To sort incrementally,
 * you can place the elements less than the pivot to the left side of it, and place the elements
 * larger than or equal to the pivot to the right side of it. After that, you divide the original
 * array to three parts: the subarray of the elements less than the pivot, the pivot itself and the
 * subarray of the elements large than or equal to the pivot.
 *
 * 2. The second step, sort the two subarray recursively using the same way.
 *
 * Then you will get an ascending array. You might need to deal with some corner cases during the implementation.
 */
public class QuickSort {
    /**
     * 升序排序
     */
    public void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    /**
     * 排序的返回包括left和right下标
     */
    private void quickSort(int[] arr, int left, int right) {
        if (left >=  right) {
            return;
        } else if (left + 1 == right) {
            if (arr[left] > arr[right]) {
                swap(arr, left, right);
            }
        } else {
            int pivotIdx = partition(arr, left, right);
            quickSort(arr, left, pivotIdx - 1);
            quickSort(arr, pivotIdx + 1, right);
        }
    }

    /**
     * 使用arr[left]作为pivot，将比pivot小的元素放到它左边，其余的放到它右边
     * @return pivot元素下标
     */
    private int partition(int[] arr, int left, int right) {
        int pivotIdx = left;
        for (int i = left; i <= right; i++) {
            if (arr[i] < arr[pivotIdx]) {
                swap(arr, pivotIdx, i);
                pivotIdx = i;
            }
        }
        return pivotIdx;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    @Test
    public void testCase00() {
        int[] input = {3, 1, 2, 4};
        System.out.println(partition(input, 0, 3));
        System.out.println(Arrays.toString(input));
    }

    @Test
    public void testCase01() {
        int[] input = {3, 1, 2, 4};
        quickSort(input);
        System.out.println(Arrays.toString(input));
    }

    @Test
    public void testCase02() {
        int[] input = {4, 3, 2, 1};
        quickSort(input);
        System.out.println(Arrays.toString(input));
    }
}
