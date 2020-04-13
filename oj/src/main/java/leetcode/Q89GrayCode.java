package leetcode;

import org.junit.Test;

import java.util.*;

/**
 * 格雷编码是一个二进制数字系统，在该系统中，两个连续的数值仅有一个位数的差异。
 * 给定一个代表编码总位数的非负整数 n，打印其格雷编码序列。格雷编码序列必须以 0 开头。
 *
 * 示例 1:
 * 输入: 2
 * 输出: [0,1,3,2]
 * 解释:
 * 00 - 0
 * 01 - 1
 * 11 - 3
 * 10 - 2
 * 对于给定的 n，其格雷编码序列并不唯一。
 * 例如，[0,2,3,1] 也是一个有效的格雷编码序列。
 * 00 - 0
 * 10 - 2
 * 11 - 3
 * 01 - 1
 *
 * 示例 2:
 * 输入: 0
 * 输出: [0]
 * 解释: 我们定义格雷编码序列必须以 0 开头。
 *      给定编码总位数为 n 的格雷编码序列，其长度为 2n。当 n = 0 时，长度为 20 = 1。
 *      因此，当 n = 0 时，其格雷编码序列为 [0]。
 */
public class Q89GrayCode {
    public List<Integer> grayCode(int n) {
        List<Integer> list = new LinkedList<>(Arrays.asList(0));
        if (n == 0) return list;

        Set<Integer> set = new HashSet<>(list);
        for (int i = 1; i < Math.pow(2, n); i++) {
            int last = list.get(list.size() - 1);
            for (int j = 0; j < n; j++) {
                int val = last ^ (1 << j);
                if (!set.contains(val)) {
                    list.add(val);
                    set.add(val);
                    break;
                }
            }
        }
        return list;
    }

    @Test
    public void testCase01() {
        System.out.println(grayCode(2));
    }
}
