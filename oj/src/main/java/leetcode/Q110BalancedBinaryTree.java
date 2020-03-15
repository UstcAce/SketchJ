package leetcode;

import common.TreeNode;
import org.junit.Test;

/**
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 *
 * 本题中，一棵高度平衡二叉树定义为：
 *
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
 *
 * 示例 1:
 * 给定二叉树 [3,9,20,null,null,15,7]
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回 true 。
 *
 * 示例 2:
 * 给定二叉树 [1,2,2,3,3,null,null,4,4]
 *        1
 *       / \
 *      2   2
 *     / \
 *    3   3
 *   / \
 *  4   4
 * 返回 false 。
 *
 */
public class Q110BalancedBinaryTree {
    private boolean isBalanced;

    public boolean isBalanced(TreeNode root) {
        isBalanced = true;
        if (root == null) {
            return isBalanced;
        }

        calcHeight(root);

        return isBalanced;
    }

    private int calcHeight(TreeNode root) {
        TreeNode left = root.left;
        TreeNode right = root.right;

        if (left == null && right == null) {
            return 0;
        } else if (left == null && right != null) {
            int rightHeight = calcHeight(right) + 1;
            if (rightHeight > 1) {
                isBalanced = false;
            }
            return rightHeight;
        } else if (left != null && right == null) {
            int leftHeight = calcHeight(left) + 1;
            if (leftHeight > 1) {
                isBalanced = false;
            }
            return leftHeight;
        } else {
            int leftHeight = calcHeight(left);
            int rightHeight = calcHeight(right);
            if (Math.abs(leftHeight - rightHeight) > 1) {
                isBalanced = false;
            }
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    @Test
    public void testCase01() {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);

        node1.left = node2;
        node2.left = node3;

        System.out.println(isBalanced(node1));
    }

    @Test
    public void testCase02() {
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(1);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);

        node1.left = node2;
        node1.right = node3;
        node3.right = node4;


        System.out.println(isBalanced(node1));
    }
}
