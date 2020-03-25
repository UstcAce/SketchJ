package leetcode;

import common.TreeNode;
import org.junit.Test;

/**
 * 给定两个非空二叉树 s 和 t，检验 s 中是否包含和 t 具有相同结构和节点值的子树。s 的一个子树包括 s 的一个节点和这个节点的所有子孙。s 也可以看做它自身的一棵子树。
 *
 */
public class Q572SubtreeOfAnotherTree {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        return traverse(s, t);
    }

    private boolean traverse(TreeNode s, TreeNode t) {
        return s != null && (equals(s, t) || traverse(s.left, t) || traverse(s.right, t));
    }

    private boolean equals(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        else if (t1 == null || t2 == null) return false;

        return t1.val == t2.val && equals(t1.left, t2.left) && equals(t1.right, t2.right);
    }

    @Test
    public void testCase01() {
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(4);
        TreeNode node3 = new TreeNode(5);
        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(2);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node3.left = node5;

        TreeNode node6 = new TreeNode(3);
        TreeNode node7 = new TreeNode(1);
        TreeNode node8 = new TreeNode(2);

        node6.left = node7;
        node6.right = node8;

        System.out.println(isSubtree(node1, node6));
    }
}
