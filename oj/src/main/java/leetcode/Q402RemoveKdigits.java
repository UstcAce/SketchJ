package leetcode;

import org.junit.Test;

import java.util.LinkedList;

/**
 * 给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。
 * <p>
 * 注意:
 * num 的长度小于 10002 且 ≥ k。
 * num 不会包含任何前导零。
 * <p>
 * 示例 1 :
 * 输入: num = "1432219", k = 3
 * 输出: "1219"
 * 解释: 移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219。
 * <p>
 * 示例 2 :
 * 输入: num = "10200", k = 1
 * 输出: "200"
 * 解释: 移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
 * <p>
 * 示例 3 :
 * 输入: num = "10", k = 2
 * 输出: "0"
 * 解释: 从原数字移除所有的数字，剩余为空就是0。
 */
public class Q402RemoveKdigits {
    public String removeKdigits(String num, int k) {
        if (k == 0) return num;
        if (num.length() == k) return 0 + "";
        LinkedList<Character> stack = new LinkedList<Character>();

        for(char digit : num.toCharArray()) {
            while(stack.size() > 0 && k > 0 && stack.peekLast() > digit) {
                stack.removeLast();
                k -= 1;
            }
            stack.addLast(digit);
        }

        /* remove the remaining digits from the tail. */
        for(int i=0; i<k; ++i) {
            stack.removeLast();
        }
        String str = "";
        while (!stack.isEmpty()) {
            str += stack.pop();
        }
        return trimFrontZero(str);
    }

    private String trimFrontZero(String str) {
        int i = 0;
        while (i < str.length() && str.charAt(i) == '0') {
            i++;
        }

        String res = str.substring(i);
        return res.length() == 0 ? "0" : res;
    }

    private String min;

    public String removeKdigits2(String num, int k) {
        if (k == 0) return num;
        if (num.length() == k) return 0 + "";
        min = num;
        traceBack(new LinkedList<>(), num, k);
        return trimFrontZero(min);
    }

    private void traceBack(LinkedList<Integer> temp, String num, int k) {
        if (temp.size() == k) {
            String str = calcString(temp, num);
            min = min.compareTo(str) < 0 ? min : str;
            return;
        }

        int start = temp.size() == 0 ? 0 : temp.getLast() + 1;

        for (int i = start; i < num.length(); i++) {
            temp.add(i);
            traceBack(temp, num, k);
            temp.removeLast();
        }
    }

    private String calcString(LinkedList<Integer> temp, String num) {
        StringBuilder sb = new StringBuilder(num);
        for (int i = 0; i < temp.size(); i++) {
            sb.deleteCharAt(temp.get(i) - i);
        }
        return sb.toString();
    }

    @Test
    public void testCase01() {
        String input = "1432219";
        System.out.println(removeKdigits(input, 3));
        System.out.println(removeKdigits2(input, 3));
    }
}
