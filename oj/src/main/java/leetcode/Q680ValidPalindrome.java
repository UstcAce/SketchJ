package leetcode;

import org.junit.Test;

/**
 * 给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。
 *
 * 示例 1:
 *
 * 输入: "aba"
 * 输出: True
 * 示例 2:
 *
 * 输入: "abca"
 * 输出: True
 * 解释: 你可以删除c字符。
 * 注意:
 *
 * 字符串只包含从 a-z 的小写字母。字符串的最大长度是50000。
 *
 */
public class Q680ValidPalindrome {
    public boolean validPalindrome(String s) {
        return validLeft(s) || validRight(s);
    }

    private boolean validLeft(String s) {
        int left = 0;
        int right = s.length() - 1;

        boolean isSkip = true;

        while (left <= right) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else if (s.charAt(left) == s.charAt(right - 1) && isSkip) {
                left++;
                right -= 2;
                isSkip = false;
            } else if (s.charAt(left + 1) == s.charAt(right) && isSkip) {
                left += 2;
                right--;
                isSkip = false;
            } else {
                return false;
            }
        }

        return true;
    }

    private boolean validRight(String s) {
        int left = 0;
        int right = s.length() - 1;

        boolean isSkip = true;

        while (left <= right) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else if (s.charAt(left + 1) == s.charAt(right) && isSkip) {
                left += 2;
                right--;
                isSkip = false;
            } else if (s.charAt(left) == s.charAt(right - 1) && isSkip) {
                left++;
                right -= 2;
                isSkip = false;
            } else {
                return false;
            }
        }

        return true;
    }

    @Test
    public void testCase01() {
        String input = "cuppucu";
        System.out.println(validPalindrome(input));
    }
}
