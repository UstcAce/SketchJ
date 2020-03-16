package leetcode;

import java.util.*;

/**
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 *
 * 示例:
 *
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * 输出:
 * [
 *   ["ate","eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 * 说明：
 *
 * 所有输入均为小写字母。
 * 不考虑答案输出的顺序。
 *
 */
public class Q49GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String item : strs) {
            String newStr = calcSortedString(item);
            if (map.containsKey(newStr)) {
                map.get(newStr).add(item);
            } else {
                map.put(newStr, new ArrayList<>(Arrays.asList(item)));
            }
        }

        List<List<String>> res = new ArrayList<>(map.values());
        return res;
    }

    private String calcSortedString(String str) {
        StringBuilder sb = new StringBuilder();
        char[] array = str.toCharArray();
        Arrays.sort(array);
        for (char c : array) {
            sb.append(c);
        }
        return sb.toString();
    }
}
