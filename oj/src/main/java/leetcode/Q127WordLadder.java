package leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class Q127WordLadder {

    /**
     * bfs是浪费空间节省时间，dfs是浪费时间节省空间。
     */
    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList.size());
        wordSet.addAll(wordList);
        if (!wordSet.contains(endWord)) return 0;
        Set<String> smallSet = new HashSet<>();
        Set<String> largetSet = new HashSet<>();
        smallSet.add(beginWord);
        largetSet.add(endWord);
        int wordLen = beginWord.length();
        int step = 0;
        while (!smallSet.isEmpty() && !largetSet.isEmpty()) {
            step++;
            // 一直从小的集合端搜寻
            if (smallSet.size() > largetSet.size()) {
                Set<String> tmp = smallSet;
                smallSet = largetSet;
                largetSet = tmp;
            }
            Set<String> tmpSet = new HashSet<>();
            for (String word : smallSet) {
                for (int i = 0; i < wordLen; i++) {
                    char[] chars = word.toCharArray();
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        chars[i] = ch;
                        String tmp = new String(chars);
                        if (largetSet.contains(tmp)) return step + 1;
                        if (!wordSet.contains(tmp)) continue;
                        wordSet.remove(tmp);
                        tmpSet.add(tmp);
                    }
                }
            }
            smallSet = tmpSet;
        }
        return 0;
    }

    private boolean[] visit;

    private int minLen;

    private int listLen;

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        listLen = wordList.size();
        minLen = Integer.MAX_VALUE;
        visit = new boolean[listLen];

        if (isDifferOneLetter(beginWord, endWord)) {
            return 2;
        }

        if (!new HashSet<>(wordList).contains(endWord)) {
            return 0;
        }

        LinkedList<String> linkedList = new LinkedList<>(Arrays.asList(beginWord));
        traceBackladder(linkedList, wordList, endWord);
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    void traceBackladder(LinkedList<String> iter, List<String> wordList, String endWord) {
        if (iter.getLast().equals(endWord)) {
            minLen = Math.min(minLen, iter.size());
            return;
        }

        for (int i = 0; i < listLen; i++) {
            if (visit[i]) continue;
            if (iter.size() >= minLen) continue;
            String local =  wordList.get(i);
            if (isDifferOneLetter(local, iter.getLast())) {
                iter.addLast(local);
                visit[i] = true;
                traceBackladder(iter, wordList, endWord);
                visit[i] = false;
                iter.removeLast();
            }
        }
    }

    public boolean isDifferOneLetter(String A, String B) {
        int count = 0;
        for (int i = 0; i < A.length(); i++) {
            if (A.charAt(i) != B.charAt(i)) {
                count++;
            }
        }
        return count == 1;
    }

    @Test
    public void testCase01() {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        int res = ladderLength(beginWord, endWord, wordList);

        System.out.println(res);
        Assert.assertEquals(5, res);
    }

    @Test
    public void testCase02() {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList("hot","dot","dog","lot","log");
        int res = ladderLength(beginWord, endWord, wordList);

        System.out.println(res);
        Assert.assertEquals(0, res);
    }

    @Test
    public void testCase03() {
        String beginWord = "lost";
        String endWord = "miss";
        List<String> wordList = Arrays.asList("most","mist","miss","lost","fist","fish");
        int res = ladderLength(beginWord, endWord, wordList);

        System.out.println(res);
        Assert.assertEquals(4, res);
    }

    @Test
    public void testCase04() {
        String beginWord = "qa";
        String endWord = "sq";
        List<String> wordList = Arrays.asList("si","go","se","cm","so","ph","mt","db","mb","sb","kr","ln","tm","le","av","sm","ar","ci","ca","br","ti","ba","to","ra","fa","yo","ow","sn","ya","cr","po","fe","ho","ma","re","or","rn","au","ur","rh","sr","tc","lt","lo","as","fr","nb","yb","if","pb","ge","th","pm","rb","sh","co","ga","li","ha","hz","no","bi","di","hi","qa","pi","os","uh","wm","an","me","mo","na","la","st","er","sc","ne","mn","mi","am","ex","pt","io","be","fm","ta","tb","ni","mr","pa","he","lr","sq","ye");
        int res = ladderLength2(beginWord, endWord, wordList);

        System.out.println(res);
        Assert.assertEquals(5, res);
    }
}
