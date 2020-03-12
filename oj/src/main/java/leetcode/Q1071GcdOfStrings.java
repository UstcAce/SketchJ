package leetcode;

import org.junit.Test;

/**
 * 对于字符串 S 和 T，只有在 S = T + ... + T（T 与自身连接 1 次或多次）时，我们才认定 “T 能除尽 S”。
 *
 * 返回最长字符串 X，要求满足 X 能除尽 str1 且 X 能除尽 str2。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：str1 = "ABCABC", str2 = "ABC"
 * 输出："ABC"
 * 示例 2：
 *
 * 输入：str1 = "ABABAB", str2 = "ABAB"
 * 输出："AB"
 * 示例 3：
 *
 * 输入：str1 = "LEET", str2 = "CODE"
 * 输出：
 *
 */
public class Q1071GcdOfStrings {
    /**
     * 辗转相除法，迭代更新large和small，有GCD的字符串pair除完之后small应该为空字符串""
     * 如：large = "ABCABC", small = "ABC"
     *    large = "ABC", small = ""
     * 没有GCD的字符串pair除完之后small不为空
     * 如：large = "ABCDEF", small = "ABC"
     *    large = "ABC", small = "DEF"
     */
    public String gcdOfStrings(String str1, String str2) {
        String large = str1.length() >= str2.length() ? str1 : str2;
        String small = str1.length() < str2.length() ? str1 : str2;
        while (!getRemainingString(large, small).equals(large)) {
            String temp = large;
            large = small;
            small = getRemainingString(temp, small);
        }
        if (small.length() != 0) {
            return "";
        }

        return large;
    }


    private String getRemainingString(String large, String small) {
        return large.replaceAll(small, "");
    }

    @Test
    public void testCase01() {
        String str1 = "ABCABC";
        String str2 = "ABC";
        System.out.println(gcdOfStrings(str1, str2));
    }

    @Test
    public void testCase02() {
        String str1 = "ABABAB";
        String str2 = "ABAB";
        System.out.println(gcdOfStrings(str1, str2));
    }

    @Test
    public void testCase03() {
        String str1 = "ABCDEF";
        String str2 = "ABC";
        System.out.println(gcdOfStrings(str1, str2));
    }
}
