package leetcode;

/**
 * 给定一个单词列表和两个单词 word1 和 word2，返回列表中这两个单词之间的最短距离。
 *
 * 示例:
 * 假设 words = ["practice", "makes", "perfect", "coding", "makes"]
 *
 * 输入: word1 = “coding”, word2 = “practice”
 * 输出: 3
 * 输入: word1 = "makes", word2 = "coding"
 * 输出: 1
 * 注意:
 * 你可以假设 word1 不等于 word2, 并且 word1 和 word2 都在列表里。
 */
public class Q243ShortestDistance {
    public int shortestDistance(String[] words, String word1, String word2) {
        int minDis = Integer.MAX_VALUE;
        int idx1 = -1;
        int idx2 = -1;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) {
                idx1 = i;
                if (idx2 != -1) {
                    minDis = Math.min(Math.abs(idx1 - idx2), minDis);
                }
            }

            if (words[i].equals(word2)) {
                idx2 = i;
                if (idx1 != -1) {
                    minDis = Math.min(Math.abs(idx1 - idx2), minDis);
                }
            }
        }
        return minDis;
    }
}
