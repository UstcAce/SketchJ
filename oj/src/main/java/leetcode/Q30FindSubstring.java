package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 给定一个字符串 s 和一些长度相同的单词 words。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
 * 注意子串要与 words 中的单词完全匹配，中间不能有其他字符，但不需要考虑 words 中单词串联的顺序。
 *  
 *
 * 示例 1：
 * 输入：
 *   s = "barfoothefoobarman",
 *   words = ["foo","bar"]
 * 输出：[0,9]
 * 解释：
 * 从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
 * 输出的顺序不重要, [9,0] 也是有效答案。
 *
 *  示例 2：
 * 输入：
 *   s = "wordgoodgoodgoodbestword",
 *   words = ["word","good","best","word"]
 * 输出：[]
 */
public class Q30FindSubstring {
    /**
     * 解法一: HashMap + 滑动窗口
     * 注意到words中的单词都是长度相同的
     */
    public List<Integer> findSubstring(String s, String[] words) {
        int sLen = s.length();
        int wordLen = words.length;

        if (sLen == 0 || wordLen == 0) return new ArrayList<>();

        Map<String, Integer> wordMap = new HashMap<>();
        for (String word : words) {
            wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
        }

        int oneWordLen = words[0].length();
        int allWordsLen = oneWordLen * words.length;

        if (sLen < allWordsLen) return new ArrayList<>();

        List<Integer> res = new ArrayList<>();

        int i = 0;
        label: while (i <= sLen - allWordsLen) {
            Map<String, Integer> local = new HashMap<>();
            for (int j = 0; j < words.length; j++) {
                String oneWord = s.substring(i + j * oneWordLen, i + (j + 1) * oneWordLen);
                if (!wordMap.containsKey(oneWord) || local.getOrDefault(oneWord, 0) + 1 > wordMap.get(oneWord)) {
                    i++;
                    continue label;
                } else {
                    local.put(oneWord, local.getOrDefault(oneWord, 0) + 1);
                }
            }
            if (local.equals(wordMap)) {
                res.add(i);
            }
            i++;
        }

        return res;
    }

    @Test
    public void testCase01() {
        String s = "barfoothefoobarman";
        String[] words = {"foo", "bar"};
        System.out.println(findSubstring(s, words));
    }

    @Test
    public void testCase02() {
        String s = "wordgoodgoodgoodbestword";
        String[] words = {"good","best","word"};
        System.out.println(findSubstring(s, words));
    }

    private List<Integer> res = new ArrayList<>();
    /**
     * 解法二：回溯法
     */
    public List<Integer> findSubstring2(String s, String[] words) {
        int sLen = s.length();
        int wordLen = words.length;

        if (sLen == 0 || wordLen == 0) return new ArrayList<>();

        String firstWord = words[0];
        List<Integer> firstIdList = calcFirstIdList(s, firstWord);
        if (firstIdList.isEmpty()) return new ArrayList<>();

        List<String> rest = calcTheRestWords(words);

        if (rest.isEmpty()) return firstIdList;

        for (int id : firstIdList) {
            int firstWordLen = firstWord.length();
            traceBackSearch(s, new ArrayList<>(rest), id, id + firstWordLen - 1);
        }

        return res.stream().distinct().collect(Collectors.toList());
    }

    private void traceBackSearch(String s, List<String> rest, int start, int end) {
        if (rest.isEmpty()) {
            res.add(start);
            return;
        }

        List<String> matchList = getMatchWords(s, rest, start, end);
        if (matchList.isEmpty()) return;

        for (String match : matchList) {
            int len = match.length();
            rest.remove(match);
            if (start - len >= 0 && s.substring(start - len, start).equals(match)) {
                traceBackSearch(s, rest, start - len, end);
            }
            if (end + len < s.length() && s.substring(end + 1, end + len + 1).equals(match)) {
                traceBackSearch(s, rest, start, end + len);
            }
            rest.add(match);
        }
    }

    private List<String> getMatchWords(String s, List<String> rest, int start, int end) {
        List<String> matchWords = new ArrayList<>();
        for (String word : rest) {
            int len = word.length();
            if ((start - len >= 0 && s.substring(start - len, start).equals(word))
                    || (end + len < s.length() && s.substring(end + 1, end + len + 1).equals(word))) {
                matchWords.add(word);
            }
        }
        return matchWords;
    }

    private List<Integer> calcFirstIdList(String s, String firstWord) {
        List<Integer> firstIdList = new ArrayList<>();
        int id = 0;
        while (s.indexOf(firstWord, id) != -1) {
            int localId = s.indexOf(firstWord, id);
            if (localId == id) {
                firstIdList.add(localId);
            }
            id++;
        }

        return firstIdList;
    }

    private List<String> calcTheRestWords(String[] words) {
        List<String> rest = new ArrayList<>();
        for (int i = 1; i < words.length; i++) {
            rest.add(words[i]);
        }
        return rest;
    }
}
