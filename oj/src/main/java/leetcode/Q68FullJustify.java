package leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个单词数组和一个长度 maxWidth，重新排版单词，使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。
 * 你应该使用“贪心算法”来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格 ' ' 填充，使得每行恰好有 maxWidth 个字符。
 * 要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。
 * 文本的最后一行应为左对齐，且单词之间不插入额外的空格。
 *
 * 说明:
 *
 * 单词是指由非空格字符组成的字符序列。
 * 每个单词的长度大于 0，小于等于 maxWidth。
 * 输入单词数组 words 至少包含一个单词。
 *
 */
public class Q68FullJustify {
    public List<String> fullJustify(String[] words, int maxWidth) {
        int len = words.length;
        List<String> res = new ArrayList<>();

        List<Integer> wordLenList = new ArrayList<>();
        for (String word : words) {
            wordLenList.add(word.length());
        }

        int start = 0;
        while (start < len) {
            int end = calcEndIndex(start, wordLenList, maxWidth);
            res.add(calcOneLine(words, start, end, maxWidth));
            start = end + 1;
        }

        return res;
    }

    private String calcOneLine(String[] words, int start, int end, int maxWidth) {
        StringBuilder res = new StringBuilder();
        if (end == words.length - 1) {
            for (int i = start; i <= end; i++) {
                res.append(words[i]);
                if (i == end) continue;
                res.append(" ");
            }
            while (res.length() < maxWidth) {
                res.append(" ");
            }
        } else {
            int spaceNum = end - start;
            int wordsLen = 0;
            for (int i = start; i <= end; i++) {
                wordsLen += words[i].length();
            }

            if (spaceNum == 0) {
                res.append(words[start]);
                while (res.length() < maxWidth) {
                    res.append(" ");
                }
            } else {
                int quotient = (maxWidth - wordsLen) / spaceNum;
                int remainder = (maxWidth - wordsLen) % spaceNum;
                for (int i = start; i <= end; i++) {
                    res.append(words[i]);
                    if (i == end) continue;
                    int temp = quotient;
                    if (remainder > 0) {
                        temp++;
                        remainder--;
                    }
                    int j = 0;
                    while (j < temp) {
                        res.append(" ");
                        j++;
                    }
                }
            }
        }

        return res.toString();
    }

    private int calcEndIndex(int start, List<Integer> wordLenList, int maxWidth) {
        int remainSum = 0;
        for (int i = start; i <= wordLenList.size() - 1; i++) {
            remainSum += wordLenList.get(i);
        }

        for (int i = wordLenList.size() - 1; i >= start; i--) {
            int tempLen = remainSum + i - start;
            remainSum -= wordLenList.get(i);
            if (tempLen <= maxWidth) {
                return i;
            }
        }

        throw new UnsupportedOperationException();
    }

    @Test
    public void testCase01() {
        int start = 0;
        List<Integer> wordLenList = Arrays.asList(4, 2, 2, 7);
        int maxWidth = 7;
        System.out.println(calcEndIndex(start, wordLenList, maxWidth));
    }

    @Test
    public void testCase02() {
        String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
        int maxWidth = 16;
        String standard = "This    is    an";
        String res = calcOneLine(words, 0, 2, maxWidth);
        Assert.assertEquals(standard, res);
    }
}
