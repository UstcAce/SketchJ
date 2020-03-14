package leetcode;

import common.TreeNode;

/**
 *给定一个二叉树，原地将它展开为链表。
 *
 * 例如，给定二叉树
 *
 *     1
 *    / \
 *   2   5
 *  / \   \
 * 3   4   6
 * 将其展开为：
 *
 * 1
 *  \
 *   2
 *    \
 *     3
 *      \
 *       4
 *        \
 *         5
 *          \
 *           6
 *
 */
public class Q114FlattenBinaryTree {
    private TreeNode last;
    public void flatten(TreeNode root) {
        last = null;
        preOrderTraversal(root);
    }

    /**
     * 递归前序遍历 二叉树 根-左-右 先序遍历
     */
    private void preOrderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }

        TreeNode left = root.left;
        TreeNode right = root.right;

        if (last == null) {
            last = root;
        } else {
            last.right = root;
            last = root;
            root.left = null;
            root.right = null;
        }

        preOrderTraversal(left);

        preOrderTraversal(right);
    }


    /**
     * 递归解法，二叉树 根-左-右 先序遍历
     */
    public void flatten2(TreeNode root) {
        flattenTree(root);
    }

    private TreeNode flattenTree(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return root;
        }

        TreeNode left = root.left;
        TreeNode right = root.right;

        if (left != null && right == null) {
            TreeNode leftRes = flattenTree(left);
            root.left = null;
            root.right = left;
            return leftRes;
        } else if (left == null) {
            return flattenTree(right);
        } else {
            TreeNode leftRes = flattenTree(left);
            root.left = null;
            root.right = left;
            TreeNode rightRes = flattenTree(right);
            leftRes.right = right;

            return rightRes;
        }
    }
}
