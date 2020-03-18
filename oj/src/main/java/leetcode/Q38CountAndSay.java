package leetcode;

import org.junit.Test;

public class Q38CountAndSay {
    public String countAndSay(int n) {
        String res = "1";
        for (int i = 2; i <= n; i++) {
            res = calcIter(res);
        }
        return res;
    }

    private String calcIter(String s) {
        StringBuilder sb = new StringBuilder();
        String last = null;
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            String iter = s.substring(i, i + 1);
            if (last == null) {
                last = iter;
                count = 1;
            } else {
                if (last.equals(iter)) {
                    count += 1;
                } else {
                    sb.append(count).append(last);
                    last = iter;
                    count = 1;
                }
            }
        }
        sb.append(count).append(last);

        return sb.toString();
    }

    @Test
    public void testCase01() {
        String s = "111221";
        System.out.println(calcIter(s));
    }
}
