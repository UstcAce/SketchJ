package leetcode;

import common.TreeNode;

/**
 * 给定一个二叉树，找出其最小深度。
 *
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 *
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最小深度  2.
 *
 */
public class Q111MinimumDepthOfBinaryTree {
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        return traceBackMinDepth(0, root);
    }

    private int traceBackMinDepth(int depth, TreeNode node) {
        if (node.left == null && node.right == null) return depth + 1;

        if (node.left != null && node.right != null) {
            return Math.min(traceBackMinDepth(depth + 1, node.left),
                    traceBackMinDepth(depth + 1, node.right));
        } else if (node.left != null) {
            return traceBackMinDepth(depth + 1, node.left);
        } else {
            return traceBackMinDepth(depth + 1, node.right);
        }
    }
}
