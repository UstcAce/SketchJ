package leetcode;

import org.junit.Test;

/**
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 *
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 *
 * 示例 1:
 *
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 * 示例 2:
 *
 * 输入: "race a car"
 * 输出: false
 *
 */
public class Q125ValidPalindrome {
    public boolean isPalindrome(String s) {
        if (s==null || s.length()==0) {
            return true;
        }
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (isValid(c)) {
                sb.append(c);
            }
        }
        int newLen = sb.length();
        for (int i=0; i<newLen/2; i++) {
            String iStr = "" + sb.charAt(i);
            String jStr = "" + sb.charAt(newLen-i-1);
            if (!iStr.equalsIgnoreCase(jStr)) {
                return false;
            }
        }

        return true;
    }

    private boolean isValid(char c) {
        return (c >= '0' && c <= '9') || (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    @Test
    public void testCase01() {
        String input = "A man, a plan, a canal: Panama";
        System.out.println(isPalindrome(input));
    }

    @Test
    public void testCase02() {
        String input = "race a car";
        System.out.println(isPalindrome(input));
    }

    @Test
    public void testCase03() {
        String input = "a.";
        System.out.println(isPalindrome(input));
    }
}
