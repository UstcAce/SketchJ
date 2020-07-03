package microsoft;

/**
 * [7,11,12,9,5,2,7,17,22]
 * 输入一个数组， i < j <= k
 * x = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1]， [i, j-1]之间的异或
 * y = arr[j] ^ arr[j + 1] ^ ... ^ arr[k]，[j, k]之间的异或
 * x == y对应的i,j,k的对数有多少种
 */
public class XorPairNum {
    /**
     * 进行异或遍历 xor
     * 在下标idx的情况下，xor = 0,arr[0] ^ ... arr[idx] = 0
     *  len - idx
     */
    public int calcPairs(int[] arr) {
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
}
