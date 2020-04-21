package leetcode;

import common.TreeNode;

import java.util.Stack;

/**
 * next() 和 hasNext() 操作的时间复杂度是 O(1)，并使用 O(h) 内存，其中 h 是树的高度。
 * 你可以假设 next() 调用总是有效的，也就是说，当调用 next() 时，BST 中至少存在一个下一个最小的数。
 */
public class Q173BSTIterator {
    Stack<TreeNode> stack = new Stack<>();

    public Q173BSTIterator(TreeNode root) {
        // 中序遍历
        TreeNode iter = root;
        while (iter != null) {
            stack.add(iter);
            iter = iter.left;
        }
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode node = stack.pop();
        int val = node.val;
        node = node.right;
        while (node != null) {
            stack.add(node);
            node = node.left;
        }
        return val;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return stack.size() > 0;
    }
}
