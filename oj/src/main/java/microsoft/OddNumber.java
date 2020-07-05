package microsoft;

import org.junit.Test;

/**
 * 给一个长度为n的数组，其中只有一个数字出现的奇数次，找出这个数
 */
public class OddNumber {
    public int findOddNumber(int[] arr) {
        int res = 0;
        for (int val : arr) {
            res ^= val;
        }
        return res;
    }

    @Test
    public void testCase01() {
        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 9, 8, 7, 6, 5, 4, 1, 2, 3, 3, 0};
        System.out.println(findOddNumber(arr));
    }
}
