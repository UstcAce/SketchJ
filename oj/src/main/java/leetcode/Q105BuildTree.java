package leetcode;

import common.TreeNode;
import org.junit.Test;

/**
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 */
public class Q105BuildTree {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int len = preorder.length;
        if (len == 0) return null;

        return buildTreeNode(preorder, 0, len - 1, inorder, 0, len - 1);
    }

    private TreeNode buildTreeNode(int[] preorder, int start1, int end1, int[] inorder, int start2, int end2) {
        int rootVal = preorder[start1];
        TreeNode root = new TreeNode(rootVal);

        if (start1 < end1) {
            int idx = findIndex(inorder, start2, end2, rootVal);
            int leftLen = idx - start2;
            int rightLen = end2 - idx;

            if (leftLen > 0) {
                TreeNode left = buildTreeNode(preorder, start1 + 1, start1 + leftLen, inorder, start2, start2 + leftLen - 1);
                root.left = left;
            }

            if (rightLen > 0) {
                TreeNode right = buildTreeNode(preorder, start1 + 1 + leftLen, end1, inorder, idx + 1, end2);
                root.right = right;
            }
        }

        return root;
    }

    private int findIndex(int[] array, int start, int end, int val) {
        for (int i = start; i <= end; i++) {
            if (array[i] == val) {
                return i;
            }
        }
        return -1;
    }

    @Test
    public void testCase01() {
        int[] pre = {1, 2};
        int[] in = {1, 2};

        System.out.println(buildTree(pre, in));
    }

    @Test
    public void testCase02() {
        int[] pre = {1, 2, 3};
        int[] in = {3, 2, 1};

        System.out.println(buildTree(pre, in));
    }

    @Test
    public void testCase03() {
        int[] pre = {1, 4, 2, 3};
        int[] in = {1, 2, 3, 4};

        System.out.println(buildTree(pre, in));
    }
}
