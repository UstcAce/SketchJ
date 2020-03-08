package leetcode;

import java.util.HashMap;
import java.util.Map;

public class Q266PalindromePermutation {
    public boolean canPermutePalindrome(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }
        int count = 0;
        for (int val : map.values()) {
            if (val%2 == 1) {
                count += 1;
            }
        }

        return count <= 1;
    }
}
