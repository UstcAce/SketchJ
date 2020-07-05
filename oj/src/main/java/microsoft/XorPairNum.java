package microsoft;

import org.junit.Test;

import java.util.*;

/**
 * [7,11,12,9,5,2,7,17,22]
 * 输入一个数组， i < j <= k
 * x = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1]， [i, j-1]之间的异或
 * y = arr[j] ^ arr[j + 1] ^ ... ^ arr[k]，[j, k]之间的异或
 * x == y对应的i,j,k的对数有多少种
 */
public class XorPairNum {
    /**
     * 暴力遍历，
     * 时间复杂度O(n^4)
     * 空间间复杂度O(1)
     */
    public int calcXorPairs0(int[] arr) {
        int len = arr.length;
        if (len <= 1) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                int x = 0;
                for (int m = i; m < j; m++) {
                    x ^= arr[m];
                }
                for (int k = j; k < len; k++) {
                    int y = 0;
                    for (int n = j; n <= k; n++) {
                        y ^= arr[n];
                    }
                    if (x == y) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    /**
     * 构造累计异或数组，然后x值和y值便可查询得到
     * 时间复杂度O(n^3)
     * 空间间复杂度O(n)
     */
    public int calcXorPairs(int[] arr) {
        int len = arr.length;
        if (len <= 1) {
            return 0;
        }

        // xor[i]表示从arr[0]异或到arr[i]的数值
        int[] xor = new int[len];
        for (int i = 0; i < len; i++) {
            if (i == 0) {
                xor[0] = arr[0];
            } else {
                xor[i] = xor[i - 1] ^ arr[i];
            }
        }

        // x: arr[i] ^ ... arr[j-1] = xor[j-1] ^ xor[i-1]
        // y: arr[j] ^ ... arr[k] = xor[k] ^ xor[j-1]
        int count = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                for (int k = j; k < len; k++) {
                    int x = i == 0 ? xor[j - 1] : (xor[j - 1] ^ xor[i - 1]);
                    int y = xor[k] ^ xor[j - 1];
                    if (x == y) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    /**
     * 时间复杂度O(n^2)
     * 空间复杂度O(n)
     *  x: arr[i] ^ ... arr[j-1] = xor[j-1] ^ xor[i-1]
     *  y: arr[j] ^ ... arr[k] = xor[k] ^ xor[j-1]
     *
     *  x == y意味着
     *  (1) i >= 1; xor[i-1] == xor[k]
     *  (2) i = 0, x = arr[0] ^...^arr[j-1] = xor[j-1]
     *             y = xor[k] ^ xor[j-1]
     *  即xor[k] == 0
     * 进行异或遍历 xor
     * 在下标idx的情况下，xor = 0,arr[0] ^ ... arr[idx] = 0
     *  len - idx
     */
    public int calcXorPairs2(int[] arr) {
        int len = arr.length;
        if (len <= 1) {
            return 0;
        }

        // xor[i]表示从arr[0]异或到arr[i]的数值
        int[] xor = new int[len];
        for (int i = 0; i < len; i++) {
            if (i == 0) {
                xor[0] = arr[0];
            } else {
                xor[i] = xor[i - 1] ^ arr[i];
            }
        }

        int count = 0;
        for (int i = 0; i < len ; i++) {
            for (int k = i + 1; k < len; k++) {
                if (i == 0) {
                    if (xor[k] == 0) {
                        count = count + k;
                    }
                } else if (xor[i - 1] == xor[k]) {
                    count = count + (k - i);
                }
            }
        }
        return count;
    }

    /**
     * 时间复杂度O(n^2)
     * 空间复杂度O(1)
     *  x: arr[i] ^ ... arr[j-1] = xor[j-1] ^ xor[i-1]
     *  y: arr[j] ^ ... arr[k] = xor[k] ^ xor[j-1]
     *
     *  arr[i] ^ ... arr[j-1] = arr[j] ^ ... arr[k]
     *  两边异或 arr[i] ^...arr[j-1]，可得
     *  0 = arr[i] ^ ... arr[j-1]^arr[j] ^ ... arr[k] = arr[i] ^ ... ^ arr[k]
     *  即 arr[i] ^ ... ^ arr[k] = 0；
     */
    public int calcXorPairs3(int[] arr) {
        int len = arr.length;
        if (len <= 1) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < len; i++) {
            int xor = arr[i];
            for (int k = i + 1; k < len; k++) {
                xor ^= arr[k];
                if (xor == 0) {
                    count += (k - i);
                }
            }
        }
        return count;
    }

    /**
     * 时间复杂度O(n)
     * 空间复杂度O(n)
     *  x: arr[i] ^ ... arr[j-1] = xor[j-1] ^ xor[i-1]
     *  y: arr[j] ^ ... arr[k] = xor[k] ^ xor[j-1]
     *
     *  x == y意味着
     *  (1) i >= 1; xor[i-1] == xor[k]
     *  (2) i = 0, x = arr[0] ^...^arr[j-1] = xor[j-1]
     *             y = xor[k] ^ xor[j-1]
     *  即xor[k] == 0
     */
    public int calcXorPairs4(int[] arr) {
        int len = arr.length;
        if (len <= 1) {
            return 0;
        }
        int count = 0;
        Map<Integer, List<Integer>> map = new HashMap<>();
        // 前缀和0出现了一次，可以认为它是在-1的位置
        map.put(0, new ArrayList<>(Arrays.asList(-1)));
        int xor = 0;
        for (int i = 0; i < len; i++) {
            xor ^= arr[i];
            if (map.containsKey(xor)) {
                List<Integer> preIdxs = map.get(xor);
                for (int pre : preIdxs) {
                    count += (i - pre - 1);
                }
                preIdxs.add(i);
            } else {
                map.put(xor, new ArrayList<>(Arrays.asList(i)));
            }
        }
        return count;
    }

    public boolean validPair(int[] arr, int i, int j, int k) {
        int x = 0;
        for (int m = i; m < j; m++) {
            x ^= arr[m];
        }
        int y = 0;
        for (int m = j; m <= k; m++) {
            y ^= arr[m];
        }
        return x == y;
    }

    @Test
    public void testCase01() {
        int[] input = {7,11,12,9,5,2,7,17,22};
        System.out.println(calcXorPairs(input));
        System.out.println("=========================");
        System.out.println(calcXorPairs0(input));
        System.out.println(calcXorPairs2(input));
        System.out.println(calcXorPairs3(input));
        System.out.println(calcXorPairs4(input));
    }

    @Test
    public void testCase03() {
        int[] input = {7,11,11,9,5,2,7,17,22};
        System.out.println(calcXorPairs(input));
        System.out.println("=========================");
        System.out.println(calcXorPairs0(input));
        System.out.println(calcXorPairs2(input));
        System.out.println(calcXorPairs3(input));
        System.out.println(calcXorPairs4(input));
    }

    @Test
    public void testCase02() {
        int[] input = {7,11,12,9,5,2,7,17,22};
        System.out.println(validPair(input, 0, 1, 3));
    }
}
