package leetcode;

import java.util.*;

public class Q140WordBreak2 {

    public List<String> wordBreak(String s, List<String> wordDict) {
        return traceBack(s, new HashSet<>(wordDict), 0);
    }

    HashMap<Integer, List<String>> map = new HashMap<>();

    private List<String> traceBack(String s, Set<String> wordDict, int start) {
        if (map.containsKey(start)) {
            return map.get(start);
        }

        List<String> result = new ArrayList<>();
        if (start == s.length()) {
            result.add("");
        }
        for (int end = start + 1; end <= s.length(); end++) {
            if (wordDict.contains(s.substring(start, end))) {
                List<String> list = traceBack(s, wordDict, end);
                for (String s1 : list) {
                    String temp = s.substring(start, end) + (s1.isEmpty() ? "" : " ") + s1;
                    result.add(temp);
                }
            }
        }
        map.put(start, result);
        return result;
    }

}
