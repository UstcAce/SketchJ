package leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 哦，不！你不小心把一个长篇文章中的空格、标点都删掉了，并且大写也弄成了小写。
 * 像句子"I reset the computer. It still didn’t boot!"已经变成了"iresetthecomputeritstilldidntboot"。
 * 在处理标点符号和大小写之前，你得先把它断成词语。当然了，你有一本厚厚的词典dictionary，不过，有些词没在词典里。
 * 假设文章用sentence表示，设计一个算法，把文章断开，要求未识别的字符最少，返回未识别的字符数。
 * 注意：本题相对原题稍作改动，只需返回未识别的字符数
 * <p>
 * 示例：
 * 输入：
 * dictionary = ["looked","just","like","her","brother"]
 * sentence = "jesslookedjustliketimherbrother"
 * 输出： 7
 * 解释： 断句后为"jess looked just like tim her brother"，共7个未识别字符。
 * <p>
 * 提示：
 * 0 <= len(sentence) <= 1000
 * dictionary中总字符数不超过 150000。
 * 你可以认为dictionary和sentence中只包含小写字母。
 */
public class Q17_13Respace {
    /**
     * 1. 定义动态规划求解问题dp[i]表示前i+1个字符（s[0]~s[i])的串最小未识别字符数
     * 0 <= i <= len - 1
     * 2. dp[i]
     * (2.1) s[i]不选，dp[i] = dp[i-1]+1
     * (2.2) s[i]选，dp[i] = min(dp[j-1]) 且 s[j]~s[i]在dict中, 0 < j <= i
     * 3. 边界条件 dp[0] = 0
     */
    public int respace(String[] dictionary, String sentence) {
        int len = sentence.length();
        if (dictionary.length == 0) {
            return len;
        }
        if (len == 0) {
            return 0;
        }

        Set<String> set = Arrays.stream(dictionary).collect(Collectors.toSet());
        int[] dp = new int[len];
        dp[0] = set.contains(sentence.substring(0, 1)) ? 0 : 1;

        for (int i = 1; i < len; i++) {
            int min = dp[i - 1] + 1;
            for (int j = 0; j <= i; j++) {
                String str = sentence.substring(j, i + 1);
                if (set.contains(str)) {
                    int local = j == 0 ? 0 : dp[j - 1];
                    min = Math.min(min, local);
                }
            }
            dp[i] = min;
        }

        return dp[len - 1];
    }

    @Test
    public void testCase01() {
        String[] dict = {"looked", "just", "like", "her", "brother"};
        String sentence = "jesslookedjustliketimherbrother";

        System.out.println(respace(dict, sentence));
    }
}
