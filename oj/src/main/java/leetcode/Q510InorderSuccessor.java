package leetcode;

/**
 * 给定一棵二叉搜索树和其中的一个节点 node ，找到该节点在树中的中序后继。
 * 如果节点没有中序后继，请返回 null 。
 * 一个结点 node 的中序后继是键值比 node.val大所有的结点中键值最小的那个。
 * 你可以直接访问结点，但无法直接访问树。每个节点都会有其父节点的引用。节点定义如下：
 *
 * class Node {
 *     public int val;
 *     public Node left;
 *     public Node right;
 *     public Node parent;
 * }
 *
 * 进阶：
 * 你能否在不访问任何结点的值的情况下解决问题?
 *
 */
public class Q510InorderSuccessor {
    public Node inorderSuccessor(Node node) {
        if (node == null) return null;

        if (node.right != null) {
            node = node.right;
            while (node.left != null) {
                node = node.left;
            }
            return node;
        } else {
            if (node.parent == null) {
                return null;
            } else {
                int oriVal = node.val;
                while (node.parent != null && node.parent.val < oriVal) {
                    node = node.parent;
                }
                return node.parent;
            }
        }
    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    };
}
