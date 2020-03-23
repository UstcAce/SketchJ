package leetcode;

import common.TreeNode;
import org.junit.Test;

public class Q112HasPathSum {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;

        return traceBackPathSum(root, 0, sum);
    }

    private boolean traceBackPathSum(TreeNode node, int sum, int target) {
        if (node.left == null && node.right == null && sum + node.val == target) return true;

        boolean b1  = false;
        boolean b2  = false;
        if (node.left != null) {
            b1  = traceBackPathSum(node.left, sum + node.val, target);
        }

        if (node.right != null) {
            b2  = traceBackPathSum(node.right, sum + node.val, target);
        }

        return b1 || b2;
    }

    @Test
    public void testCase01() {
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(4);

        node1.left = node2;
        System.out.println(hasPathSum(node1, 0));
    }
}
