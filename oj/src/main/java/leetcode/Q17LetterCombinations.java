package leetcode;

import org.junit.Test;

import java.util.*;

public class Q17LetterCombinations {
    private Map<Integer, List<String>> map = new HashMap<>();

    private List<String> res = new ArrayList<>();

    /**
     * 回溯法题解
     */
    public List<String> letterCombinations(String digits) {
        if (digits.isEmpty()) return res;

        map.put(2, Arrays.asList("a", "b", "c"));
        map.put(3, Arrays.asList("d", "e", "f"));
        map.put(4, Arrays.asList("g", "h", "i"));
        map.put(5, Arrays.asList("j", "k", "l"));
        map.put(6, Arrays.asList("m", "n", "o"));
        map.put(7, Arrays.asList("p", "q", "r", "s"));
        map.put(8, Arrays.asList("t", "u", "v"));
        map.put(9, Arrays.asList("w", "x", "y", "z"));

        traceBackCombine("", digits);
        return res;
    }

    private void traceBackCombine(String str, String digits) {
        if (digits.length() == 0) {
            res.add(str);
            return;
        }

        int d = Integer.parseInt(digits.substring(0, 1));
        for (String s : map.get(d)) {
            String local = str + s;
            traceBackCombine(local, digits.substring(1));
        }
    }

    @Test
    public void testCase01() {
        String input = "";
        List<String> res = letterCombinations(input);
        System.out.println(res);
    }
}
