package leetcode;

/**
 * 实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
 *
 * 示例:
 * Trie trie = new Trie();
 *
 * trie.insert("apple");
 * trie.search("apple");   // 返回 true
 * trie.search("app");     // 返回 false
 * trie.startsWith("app"); // 返回 true
 * trie.insert("app");
 * trie.search("app");     // 返回 true
 *
 * 说明:
 * 你可以假设所有的输入都是由小写字母 a-z 构成的。
 * 保证所有输入均为非空字符串。
 */
public class Q208Trie {
    private Node root;

    /** Initialize your data structure here. */
    public Q208Trie() {
        root = new Node();
    }

    /**
     * 时间复杂度O(k)，k为字符串长度
     */
    public void insert(String word) {
        Node iter = root;
        for (char ch : word.toCharArray()) {
            int idx = ch - 'a';
            if (iter.nextNode[idx] == null) {
                iter.nextNode[idx] = new Node();
            }
            iter = iter.nextNode[idx];
        }
        iter.isEnd = true;
    }

    /**
     * 时间复杂度O(k)，k为字符串长度
     */
    public boolean search(String word) {
        Node iter = root;
        for (char ch : word.toCharArray()) {
            int idx = ch - 'a';
            if (iter.nextNode[idx] == null) {
                return false;
            }
            iter = iter.nextNode[idx];
        }
        return iter.isEnd;
    }

    /**
     * 时间复杂度O(k)，k为字符串长度
     */
    public boolean startsWith(String prefix) {
        Node iter = root;
        for (char ch : prefix.toCharArray()) {
            int idx = ch - 'a';
            if (iter.nextNode[idx] == null) {
                return false;
            }
            iter = iter.nextNode[idx];
        }
        return true;
    }

    public class Node {
        // 记录下一个字母的位置
        public Node[] nextNode = new Node[26];

        // 是否是端点
        public boolean isEnd;
    }
}
