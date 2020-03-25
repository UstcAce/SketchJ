package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个字符串S，通过将字符串S中的每个字母转变大小写，我们可以获得一个新的字符串。返回所有可能得到的字符串集合。
 *
 * 示例:
 * 输入: S = "a1b2"
 * 输出: ["a1b2", "a1B2", "A1b2", "A1B2"]
 *
 * 输入: S = "3z4"
 * 输出: ["3z4", "3Z4"]
 *
 * 输入: S = "12345"
 * 输出: ["12345"]
 *
 */
public class Q784LetterCasePermutation {
    private List<String> result = new ArrayList<>();
    public List<String> letterCasePermutation(String S) {
        traceBackPermutate(new StringBuilder(), 0, S);
        return result;
    }

    private void traceBackPermutate(StringBuilder temp, int pos, String str) {
        if (pos == str.length()) {
            result.add(temp.toString());
            return;
        }

        char c = str.charAt(pos);
        if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
            temp.append(c);
            traceBackPermutate(temp, pos + 1, str);
            temp.deleteCharAt(temp.length() - 1);
            String s;
            if (c >= 'a' && c <= 'z') {
                s = ("" + c).toUpperCase();

            } else {
                s = ("" + c).toLowerCase();
            }
            temp.append(s);
            traceBackPermutate(temp, pos + 1, str);
            temp.deleteCharAt(temp.length() - 1);
        } else {
            temp.append(c);
            traceBackPermutate(temp, pos + 1, str);
            temp.deleteCharAt(temp.length() - 1);
        }
    }

    @Test
    public void testCase01() {
        String input = "a1b2";
        System.out.println(letterCasePermutation(input));
    }
}
