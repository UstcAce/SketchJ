package leetcode;

public class Q16_11DivingBoard {
    public int[] divingBoard(int shorter, int longer, int k) {
        if (k == 0) {
            return new int[0];
        }
        int[] res;
        if (shorter == longer) {
            res = new int[1];
            res[0] = k * shorter;
        } else {
            // k = i + j, i块shorter, j块longer
            res = new int[k + 1];
            for (int i = 0; i < k + 1; i++) {
                res[i] = i * shorter + (k - i) * longer;
            }
        }
        return res;
    }
}
