package leetcode;

import org.junit.Test;

/**
 * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
 * 你可以认为 s 和 t 中仅包含英文小写字母。字符串 t 可能会很长（长度 ~= 500,000），而 s 是个短字符串（长度 <=100）。
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。
 * （例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
 *
 * 示例 1:
 * s = "abc", t = "ahbgdc"
 * 返回 true.
 *
 * 示例 2:
 * s = "axc", t = "ahbgdc"
 * 返回 false.
 *
 * 后续挑战 : 构建dp[n][26]的查询数组
 * <p>如果有大量输入的 S，称作S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？
 */
public class Q392IsSubsequence {
    public boolean isSubsequence(String s, String t) {
        if (s.length() == 0) {
            return true;
        } else if (t.length() == 0) {
            return false;
        }
        if (s.length() > t.length()) {
            return false;
        }
        return isSubseq(s, 0, t, 0);
    }

    private boolean isSubseq(String s, int start1, String t, int start2) {
        if (start1 == s.length() - 1) {
            return t.indexOf(s.charAt(start1), start2) != -1;
        } else {
            char firstCh = s.charAt(start1);
            int idx = t.indexOf(firstCh, start2);
            while (idx != -1) {
                boolean b = isSubseq(s, start1 + 1, t, idx + 1);;
                if (b) {
                    return true;
                } else {
                    idx = t.indexOf(firstCh, idx + 1);
                }
            }
            return false;
        }
    }

    public boolean isSubsequence2(String s, String t) {
        int i = 0;
        for (char ch : s.toCharArray()) {
            while (i < t.length() && t.charAt(i) != ch) {
                i++;
            }
            i++;
        }
        return i <= t.length();
    }

    @Test
    public void testCase01() {
        String s = "abc", t = "aaaaaaaaatttttttttttttthbgdc";
        System.out.println(isSubsequence(s, t));
    }
}
