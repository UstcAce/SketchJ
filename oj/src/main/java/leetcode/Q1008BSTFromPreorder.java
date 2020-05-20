package leetcode;


import common.TreeNode;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 返回与给定先序遍历 preorder 相匹配的二叉搜索树（binary search tree）的根结点。
 *
 * 示例：
 *
 * 输入：[8,5,1,7,10,12]
 * 输出：[8,5,10,1,7,null,12]
 *            8
 *          /   \
 *         5    10
 *       /  \    \
 *      1    7    12
 *
 * 提示：
 *  1 <= preorder.length <= 100
 * 先序 preorder 中的值是不同的。
 */
public class Q1008BSTFromPreorder {
    /**
     * 方法1：二分查找插入点
     * 时间复杂度O(nlogn), 空间复杂度O(1)
     * 时间复杂度 = 1 + log1 + log2 + log3 + ... + log(n-1) = log(n-1)! + 1
     * log(n!) < log(n^n) = nlogn
     */
    public TreeNode recursively(int[] preorder) {
        TreeNode root = new TreeNode(preorder[0]);
        for (int i = 1; i < preorder.length; i++) {
            int val = preorder[i];
            TreeNode node = new TreeNode(val);
            putNode(root, node);
        }
        return root;
    }

    private void putNode(TreeNode root, TreeNode node) {
        TreeNode last = root;
        TreeNode iter = root;
        while (iter != null) {
            last = iter;
            if (iter.val > node.val) {
                iter = iter.left;
            } else {
                iter = iter.right;
            }
        }
        if (last.val > node.val) {
            last.left = node;
        } else {
            last.right = node;
        }
    }

    /**
     * 方法2：分治递归方法
     */
    public TreeNode bstFromPreorder2(int[] preorder) {
        return recursively(preorder, 0, preorder.length - 1);
    }

    private TreeNode recursively(int[] preorder, int start, int end) {
        if (start > end) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[start]);
        int idx = start + 1;
        while (idx <= end && preorder[idx] < preorder[start]) {
            idx++;
        }
        root.left = recursively(preorder, start + 1, idx - 1);
        root.right = recursively(preorder, idx, end);
        return root;
    }

    // 方法3： 上下界方法
    int idx = 0;
    int[] preorder;
    int n;

    public TreeNode helper(int lower, int upper) {
        // if all elements from preorder are used
        // then the tree is constructed
        if (idx == n) return null;

        int val = preorder[idx];
        // if the current element
        // couldn't be placed here to meet BST requirements
        if (val < lower || val > upper) return null;

        // place the current element
        // and recursively construct subtrees
        TreeNode root = new TreeNode(val);
        idx++;
        root.left = helper(lower, val);
        root.right = helper(val, upper);
        return root;
    }

    public TreeNode bstFromPreorder3(int[] preorder) {
        this.preorder = preorder;
        n = preorder.length;
        return helper(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    // 方法4：迭代法
    public TreeNode bstFromPreorder4(int[] preorder) {
        int n = preorder.length;
        if (n == 0) return null;

        TreeNode root = new TreeNode(preorder[0]);
        Deque<TreeNode> deque = new ArrayDeque<TreeNode>();
        deque.push(root);

        for (int i = 1; i < n; i++) {
            // take the last element of the deque as a parent
            // and create a child from the next preorder element
            TreeNode node = deque.peek();
            TreeNode child = new TreeNode(preorder[i]);
            // adjust the parent
            while (!deque.isEmpty() && deque.peek().val < child.val)
                node = deque.pop();

            // follow BST logic to create a parent-child link
            if (node.val < child.val) node.right = child;
            else node.left = child;
            // add the child into deque
            deque.push(child);
        }
        return root;
    }

    @Test
    public void testCase01() {
        int[] preorder = {8,5,1,7,10,12};
        System.out.println(bstFromPreorder2(preorder));
    }

    @Test
    public void testCase02() {
        int[] preorder = {19,4,8,11};
        System.out.println(recursively(preorder));
    }
}