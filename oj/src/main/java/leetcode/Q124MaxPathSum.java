package leetcode;

import common.TreeNode;

/**
 * 给定一个非空二叉树，返回其最大路径和。
 * 本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
 *
 * 示例 1:
 * 输入: [1,2,3]
 *        1
 *       / \
 *      2   3
 * 输出: 6
 *
 * 示例 2:
 * 输入: [-10,9,20,null,null,15,7]
 *    -10
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 输出: 42
 */
public class Q124MaxPathSum {
    int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        traceBackSum(root);
        return max;
    }

    /**
     * 计算以root为路径一端的最大路径和
     * (1) 仅包含root节点的值
     * (2) 包含root及其左子树的路径和
     * (3) 包含root及其右子树的路径和
     */
    private int traceBackSum(TreeNode root) {
        if (root.left == null && root.right == null) {
            max = Math.max(max, root.val);
            return root.val;
        } else if (root.left == null) {
            int rightSum = traceBackSum(root.right);
            int localMax = rightSum >= 0 ? root.val + rightSum : root.val;
            max = Math.max(max, localMax);
            return localMax;
        } else if (root.right == null) {
            int leftSum = traceBackSum(root.left);
            int localMax = leftSum >= 0 ? root.val + leftSum : root.val;
            max = Math.max(max, localMax);
            return localMax;
        } else {
            int leftSum = traceBackSum(root.left);
            int rightSum = traceBackSum(root.right);
            int localMax;
            if (leftSum >= 0 && rightSum >= 0) {
                localMax = root.val + leftSum + rightSum;
            } else if (leftSum >= 0) {
                localMax = root.val + leftSum;
            } else if (rightSum >= 0) {
                localMax = root.val + rightSum;
            } else {
                localMax = root.val;
            }
            max = Math.max(max, localMax);
            int ret = Math.max(root.val, root.val + leftSum);
            ret = Math.max(ret, root.val + rightSum);
            return ret;
        }
    }

    /**
     * 计算从root节点初始往左子树延伸或者往右子树延伸的最大连续序列和
     */
    public int maxSeqPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxSeqPathSum(root.left);
        int right = maxSeqPathSum(root.right);

        int localMax = root.val;

        localMax = Math.max(localMax, root.val + left);
        localMax = Math.max(localMax, root.val + right);
        max = Math.max(localMax, max);
        max = Math.max(max, root.val + right + left);

        return localMax;
    }
}
