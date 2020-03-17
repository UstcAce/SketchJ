package leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 *
 * 说明：
 *
 * 拆分时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 * 示例 1：
 *
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 * 示例 2：
 *
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
 *      注意你可以重复使用字典中的单词。
 * 示例 3：
 *
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 *
 */
public class Q139WordBreak {
    /**
     * 解法1: 记忆化回溯
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        return calcRecur(s, new HashSet<>(wordDict), 0, new Boolean[s.length()]);
    }

    private boolean calcRecur(String s, Set<String> wordDict, int start, Boolean[] table) {
        // 递归结束条件
        if (start == s.length()) return true;

        // 增加记忆化迭代退出条件
        if (table[start] != null) return table[start];

        for (int end = start + 1; end <= s.length(); end++) {
            if (wordDict.contains(s.substring(start, end)) && calcRecur(s, wordDict, end, table)) {
                table[start] = true;
                return true;
            }
        }

        table[start] = false;
        return false;
    }

    /**
     * 解法2：动态规划
     * 1. 定义动态规划求解问题 dp[i]表示前i个字符是否能拆成多个单词
     * 2. 对于一个 i 如果存在  0 <= j < i，dp[j] = true 且 j+1 to i的子串在wordDict中
     *   则 dp[i] = true，否则 dp[i] = false
     * 3. 边界条件 dp[0] = 0,要考虑左边空字符串的情况
     */
    public boolean wordBreak2(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];

        dp[0] = true;

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                String sub = s.substring(j, i);
                if (dp[j] && set.contains(sub)) {
                    dp[i] = true;
                }
            }
        }

        return dp[s.length()];
    }

    @Test
    public void testCase01() {
        String s = "leetcode";
        List<String> wordDict = Arrays.asList("leet", "code");
        System.out.println(wordBreak2(s, wordDict));
    }

    @Test
    public void testCase02() {
        String s = "applepenapple";
        List<String> wordDict = Arrays.asList("apple", "pen");
        System.out.println(wordBreak2(s, wordDict));
    }

    @Test
    public void testCase03() {
        String s = "catsandog";
        List<String> wordDict = Arrays.asList("cats", "dog", "sand", "and", "cat");
        System.out.println(wordBreak2(s, wordDict));
    }
}
