package leetcode;

import org.junit.Test;

import java.util.Stack;

/**
 * 给定一个字符串 S，返回 “反转后的” 字符串，其中不是字母的字符都保留在原地，而所有字母的位置发生反转。
 * <p>
 * 示例 1：
 * 输入："ab-cd"
 * 输出："dc-ba"
 * <p>
 * 示例 2：
 * 输入："a-bC-dEf-ghIj"
 * 输出："j-Ih-gfE-dCba"
 * <p>
 * 示例 3：
 * 输入："Test1ng-Leet=code-Q!"
 * 输出："Qedo1ct-eeLg=ntse-T!"
 */
public class Q917ReverseOnlyLetters {
    public String reverseOnlyLetters(String S) {
        int len = S.length();
        char[] arr = S.toCharArray();
        int left = 0;
        int right = len - 1;
        while (left < right) {
            while (!Character.isLetter(arr[left])) {
                left++;
                if (!isValid(left, S)) return S;
            }
            while (!Character.isLetter(arr[right])) {
                right--;
                if (!isValid(right, S)) return S;
            }
            if (left < right) {
                char tmp = arr[left];
                arr[left] = arr[right];
                arr[right] = tmp;
                left++;
                right--;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (char c : arr) {
            sb.append(c);
        }

        return sb.toString();
    }

    private boolean isValid(int index, String s) {
        return index >= 0 && index < s.length();
    }

    /**
     * 将 s 中的所有字母单独存入栈中，所以出栈等价于对字母反序操作。（或者，可以用数组存储字母并反序数组。）
     * 然后，遍历 s 的所有字符，如果是字母我们就选择栈顶元素输出。
     */
    public String reverseOnlyLetters2(String S) {
        Stack<Character> letters = new Stack<>();
        for (char c: S.toCharArray())
            if (Character.isLetter(c))
                letters.push(c);

        StringBuilder ans = new StringBuilder();
        for (char c: S.toCharArray()) {
            if (Character.isLetter(c))
                ans.append(letters.pop());
            else
                ans.append(c);
        }

        return ans.toString();
    }

    @Test
    public void testCase01() {
        String input = "1";
        System.out.println(reverseOnlyLetters(input));
    }
}
