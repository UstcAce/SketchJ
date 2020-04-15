package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 * 示例:
 *
 * 输入: "25525511135"
 * 输出: ["255.255.11.135", "255.255.111.35"]
 */
public class Q93RestoreIpAddresses {
    private LinkedList<LinkedList<Integer>> list = new LinkedList<>();
    public List<String> restoreIpAddresses(String s) {
        int len = s.length();
        if (len < 4) return new ArrayList<>();

        traceBack(new LinkedList<>(), s);

        return transferResult(s);
    }

    private List<String> transferResult(String s) {
        List<String> res = new ArrayList<>();
        for (List<Integer> solution : list) {
            StringBuilder sb = new StringBuilder(s);
            int count = 0;
            for (int i : solution) {
                sb.insert(i + count, '.');
                count++;
            }
            res.add(sb.toString());
        }
        return res;
    }

    private void traceBack(LinkedList<Integer> temp, String s) {
        int size = temp.size();
        int len = s.length();
        if (size == 3 && isValid(temp.getLast(), len - 1, s)) {
            list.add(new LinkedList<>(temp));
            return;
        } else if (size == 3) {
            return;
        }

        int startIdx = size == 0 ? 0 : temp.getLast();

        for (int i = 0; i <= len - 3 + temp.size(); i++) {
            if (isValid(startIdx, i, s)) {
                temp.add(i + 1);
                traceBack(temp, s);
                temp.removeLast();
            }
        }
    }

    private boolean isValid(int start, int end, String s) {
        if (start > end || end - start + 1 > 3) return false;

        if (s.charAt(start) == '0' && end > start) return false;

        int num = Integer.parseInt(s.substring(start, end + 1));
        return num >= 0 && num <= 255;
    }

    @Test
    public void testCase01() {
        String input = "25525511135";

        System.out.println(restoreIpAddresses(input));
    }

    @Test
    public void testCase02() {
        String input = "0000";

        System.out.println(restoreIpAddresses(input));
    }

    @Test
    public void testCase03() {
        String input = "255255255255";

        System.out.println(restoreIpAddresses(input));
    }
}
