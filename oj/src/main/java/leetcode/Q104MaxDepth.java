package leetcode;

import common.TreeNode;

public class Q104MaxDepth {
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;

        TreeNode left = root.left;
        TreeNode right = root.right;

        if (left == null && right == null) return 1;

        return Math.max(maxDepth(left), maxDepth(right)) + 1;
    }
}
