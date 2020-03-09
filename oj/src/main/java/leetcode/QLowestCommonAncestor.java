package leetcode;

import common.TreeNode;
import org.junit.Test;

import java.util.Stack;

public class QLowestCommonAncestor {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == p || root == q) {
            return root;
        }
        if (root.left == null) {
            return lowestCommonAncestor(root.right, p, q);
        }

        if (root.right == null) {
            return lowestCommonAncestor(root.left, p, q);
        }

        int leftCount = calcNodeNum(root.left, p, q);
        if (leftCount == 0) {
            return lowestCommonAncestor(root.right, p, q);
        } else if (leftCount == 1) {
            return root;
        } else {
            return lowestCommonAncestor(root.left, p, q);
        }
    }

    /**
     * node肯定包含p,q两个节点
     */
    private int calcNodeNum(TreeNode node, TreeNode p, TreeNode q) {
        // 中序遍历root
        Stack<TreeNode> stack = new Stack<>();
        int count  = 0;
        while (!stack.isEmpty() || node != null) {
            while (node!=null) {
                stack.add(node);
                node = node.left;
            }
            TreeNode pop = stack.pop();
            if (p == pop || q == pop) {
                count += 1;
            }
            node = pop.right;
        }
        return count;
    }

    @Test
    public void testCase01() {
        TreeNode treeNode1 = new TreeNode(3);
        TreeNode treeNode2 = new TreeNode(5);
        TreeNode treeNode3 = new TreeNode(1);
        TreeNode treeNode4 = new TreeNode(6);
        TreeNode treeNode5 = new TreeNode(2);
        TreeNode treeNode6 = new TreeNode(0);
        TreeNode treeNode7 = new TreeNode(8);
        TreeNode treeNode8 = new TreeNode(7);
        TreeNode treeNode9 = new TreeNode(4);

        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;

        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;

        treeNode3.left = treeNode6;
        treeNode3.right = treeNode7;

        treeNode5.left = treeNode8;
        treeNode5.right = treeNode9;

        System.out.println(lowestCommonAncestor(treeNode1, treeNode8, treeNode7));
    }
}
