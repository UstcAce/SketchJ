package leetcode;

import common.TreeNode;

public class Q226InvertBinaryTree {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return root;
        invertRecur(root);
        return root;
    }

    private void invertRecur(TreeNode treeNode) {
        TreeNode left = treeNode.left;
        TreeNode right = treeNode.right;

        TreeNode tmp = left;
        treeNode.left = right;
        treeNode.right = tmp;

        if (left != null) invertTree(left);
        if (right != null) invertTree(right);
    }
}
