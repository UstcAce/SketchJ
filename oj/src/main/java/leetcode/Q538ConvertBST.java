package leetcode;

import common.TreeNode;

import java.util.Stack;

/**
 * 给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，使得每个节点的值是原来的节点值加上所有大于它的节点值之和。
 *
 *  例如：
 * 输入: 原始二叉搜索树:
 *               5
 *             /   \
 *            2     13
 *
 * 输出: 转换为累加树:
 *              18
 *             /   \
 *           20     13
 */
public class Q538ConvertBST {
    /**
     * 中序遍历：左-根-右
     * 逆中序遍历：右-根-左
     */
    public TreeNode convertBST(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode iter = root;
        TreeNode pre = null;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || iter != null) {
            while (iter != null) {
                stack.add(iter);
                iter = iter.right;
            }
            TreeNode pop = stack.pop();
            if (pre != null) {
                pop.val = pre.val + pop.val;
            }
            pre = pop;
            iter = pop.left;
        }
        return root;
    }
}
