package leetcode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 为搜索引擎设计一个搜索自动补全系统。用户会输入一条语句（最少包含一个字母，以特殊字符 '#' 结尾）。
 * 除 '#' 以外用户输入的每个字符，返回历史中热度前三并以当前输入部分为前缀的句子。下面是详细规则：
 * <p>
 * 一条句子的热度定义为历史上用户输入这个句子的总次数。
 * 返回前三的句子需要按照热度从高到低排序（第一个是最热门的）。如果有多条热度相同的句子，请按照 ASCII 码的顺序输出（ASCII 码越小排名越前）。
 * 如果满足条件的句子个数少于 3，将它们全部输出。
 * 如果输入了特殊字符，意味着句子结束了，请返回一个空集合。
 * 你的工作是实现以下功能：
 * <p>
 * 构造函数：
 * AutocompleteSystem(String[] sentences, int[] times): 
 * 这是构造函数，输入的是历史数据。 Sentences 是之前输入过的所有句子，Times 是每条句子输入的次数，你的系统需要记录这些历史信息。
 * <p>
 * 现在，用户输入一条新的句子，下面的函数会提供用户输入的下一个字符：
 * List<String> input(char c): 其中 c 是用户输入的下一个字符。
 * 字符只会是小写英文字母（'a' 到 'z' ），空格（' '）和特殊字符（'#'）。输出历史热度前三的具有相同前缀的句子。
 * <p>
 * <p>
 * 样例 ：
 * 操作 ： AutocompleteSystem(["i love you", "island","ironman", "i love leetcode"], [5,3,2,2])
 * 系统记录下所有的句子和出现的次数：
 * "i love you" : 5 次
 * "island" : 3 次
 * "ironman" : 2 次
 * "i love leetcode" : 2 次
 * 现在，用户开始新的键入：
 * <p>
 * 输入 ： input('i')
 * 输出 ： ["i love you", "island","i love leetcode"]
 * 解释 ：
 * 有四个句子含有前缀 "i"。其中 "ironman" 和 "i love leetcode" 有相同的热度，由于 ' ' 的 ASCII 码是 32 而 'r' 的 ASCII 码是 114，所以 "i love leetcode" 在 "ironman" 前面。同时我们只输出前三的句子，所以 "ironman" 被舍弃。
 * <p>
 * 输入 ： input(' ')
 * 输出 ： ["i love you","i love leetcode"]
 * 解释:
 * 只有两个句子含有前缀 "i "。
 * <p>
 * 输入 ： input('a')
 * 输出 ： []
 * 解释 ：
 * 没有句子有前缀 "i a"。
 * <p>
 * 输入 ： input('#')
 * 输出 ： []
 * 解释 ：
 * 用户输入结束，"i a" 被存到系统中，后面的输入被认为是下一次搜索。
 * <p>
 * 注释 ：
 * 输入的句子以字母开头，以 '#' 结尾，两个字母之间最多只会出现一个空格。
 * 即将搜索的句子总数不会超过 100。每条句子的长度（包括已经搜索的和即将搜索的）也不会超过 100。
 * 即使只有一个字母，输出的时候请使用双引号而不是单引号。
 * 请记住清零 AutocompleteSystem 类中的变量，因为静态变量、类变量会在多组测试数据中保存之前结果。详情请看这里。
 */
public class Q642AutocompleteSystem {
    private Node root;
    /**
     * 1. 用户会输入一条语句（最少包含一个字母，以特殊字符 '#' 结尾）。
     * 2. 返回前三的句子需要按照热度从高到低排序（第一个是最热门的）。如果有多条热度相同的句子，请按照 ASCII 码的顺序输出（ASCII 码越小排名越前）。
     * 3. 如果满足条件的句子个数少于 3，将它们全部输出。
     * 4. 如果输入了特殊字符，意味着句子结束了，请返回一个空集合。
     */

    private StringBuilder sb;
    private Comparator<Map.Entry<String, Integer>> sorter = (it1, it2) -> {
        if (!it1.getValue().equals(it2.getValue())) {
            return -Integer.compare(it1.getValue(), it2.getValue());
        } else {
            return it1.getKey().compareTo(it2.getKey());
        }
    };

    public Q642AutocompleteSystem(String[] sentences, int[] times) {
        root = new Node();
        sb = new StringBuilder();
        int len = sentences.length;
        for (int i = 0; i < len; i++) {
            insertSentence(sentences[i], times[i]);
        }
    }

    private void insertSentence(String sentence, int time) {
        Node iter = root;
        for (char c : sentence.toCharArray()) {
            int idx = 0;
            if (c >= 'a' && c <= 'z') {
                idx = c - 'a';
            } else if (c == ' ') {
                idx = 26;
            } else {
                throw new IllegalArgumentException("输入句子不合法!");
            }
            if (iter.nextNode[idx] == null) {
                iter.nextNode[idx] = new Node();
            }
            iter = iter.nextNode[idx];
        }
        iter.count += time;
    }

    public List<String> input(char c) {
        if (c == '#') {
            insertSentence(sb.toString(), 1);
            sb = new StringBuilder();
            return new ArrayList<>();
        } else {
            sb.append(c);
            Node node = getStartWithNode();
            Map<String, Integer> map = new HashMap<>();
            StringBuilder temp = new StringBuilder(sb);
            dfs(temp, map, node);
            List<String> list = map.entrySet().stream().sorted(sorter).collect(Collectors.toList())
                    .subList(0, Math.min(3, map.size())).stream().map(Map.Entry::getKey).collect(Collectors.toList());
            return list;
        }
    }

    private void dfs(StringBuilder temp, Map<String, Integer> map, Node node) {
        if (node == null) {
            return;
        }
        if (node.count != 0) {
            map.put(temp.toString(), node.count);
        }
        for (int i = 0; i < node.nextNode.length; i++) {
            Node next = node.nextNode[i];
            if (next != null) {
                temp.append(getCharByIndex(i));
                if (next.count != 0) {
                    map.put(temp.toString(), next.count);
                }
                dfs(temp, map, next);
                temp.deleteCharAt(temp.length() - 1);
            }
        }
    }

    private char getCharByIndex(int idx) {
        if (idx == 26) {
            return ' ';
        } else {
            return (char) ('a' + idx);
        }
    }


    private Node getStartWithNode() {
        Node iter = root;
        for (char c : sb.toString().toCharArray()) {
            int idx = 0;
            if (c >= 'a' && c <= 'z') {
                idx = c - 'a';
            } else if (c == ' ') {
                idx = 26;
            } else {
                return null;
            }
            if (iter.nextNode[idx] == null) {
                return null;
            }
            iter = iter.nextNode[idx];
        }
        return iter;
    }

    public class Node {
        // 记录下一个字母的位置
        public Node[] nextNode = new Node[27];

        // 句子出现的次数
        public int count;
    }
}
