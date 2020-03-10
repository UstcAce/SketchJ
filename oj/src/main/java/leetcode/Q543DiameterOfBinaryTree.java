package leetcode;

import common.TreeNode;
import org.junit.Test;

public class Q543DiameterOfBinaryTree {
    /**
     * 在迭代过程中计算每一个点的左高度+右高度的最大值
     */
    private int max = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        calcHeight(root);

        return max;
    }

    private int calcHeight(TreeNode root) {
        int left = 0;
        int right = 0;
        if (root.left != null && root.right != null) {
            left = calcHeight(root.left);
            right = calcHeight(root.right);
            max = Math.max(left + right + 2, max);
            return Math.max(left, right) + 1;
        } else if (root.left != null) {
            left = calcHeight(root.left);
            max = Math.max(left + right + 1, max);
            return Math.max(left, right) + 1;
        } else if (root.right != null) {
            right = calcHeight(root.right);
            max = Math.max(left + right + 1, max);
            return Math.max(left, right) + 1;
        } else {
            return 0;
        }
    }

    @Test
    public void testCase01() {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);

        treeNode1.left = treeNode2;
        System.out.println(diameterOfBinaryTree(treeNode1));

    }
}
