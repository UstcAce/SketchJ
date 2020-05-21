package leetcode;

/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

import common.TreeNode;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 返回与给定后序遍历 postorder 相匹配的二叉搜索树（binary search tree）的根结点。
 * <p>
 * 示例：
 * <p>
 * 输入：[1,7,5,12,10,8]
 * 输出：[8,5,10,1,7,null,12]
 *          8
 *        /   \
 *       5    10
 *     /  \    \
 *    1    7    12
 * <p>
 * 提示：
 * 1 <= postorder.length <= 100
 * 后序 postorder 中的值是不同的。
 */
public class Q1008_1BSTFromPostorder {
    /**
     * 方法1：二分插入点
     */
    public TreeNode bstFromPostorder(int[] postorder) {
        int len = postorder.length;
        if (postorder.length == 0) return null;

        TreeNode root = new TreeNode(postorder[len - 1]);
        if (len - 2 >= 0) {
            for (int i = len - 2; i >= 0; i--) {
                int val = postorder[i];
                TreeNode node = new TreeNode(val);
                putNode(root, node);
            }
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
     * 方法2：递归
     */
    public TreeNode bstFromPostorder2(int[] postorder) {
        return bstFromPostorderRecursivly(postorder, 0, postorder.length - 1);
    }

    /**
     * 后序遍历： 左-右-根
     */
    private TreeNode bstFromPostorderRecursivly(int[] postorder, int start, int end) {
        if (start > end) {
            return null;
        }

        TreeNode root = new TreeNode(postorder[end]);

        int idx = start;
        while (idx < end && postorder[idx] < postorder[end]) {
            idx++;
        }

        root.left = bstFromPostorderRecursivly(postorder, start, idx - 1);
        root.right = bstFromPostorderRecursivly(postorder, idx, end - 1);
        return root;
    }

    /**
     * 方法3：上下界法
     */

    int idx;
    int[] postorder;
    int n;

    public TreeNode helper(int lower, int upper) {
        // if all elements from preorder are used
        // then the tree is constructed
        if (idx == -1) return null;

        int val = postorder[idx];
        // if the current element
        // couldn't be placed here to meet BST requirements
        if (val < lower || val > upper) return null;

        // place the current element
        // and recursively construct subtrees
        TreeNode root = new TreeNode(val);
        idx--;
        root.right = helper(val, upper);
        root.left = helper(lower, val);
        return root;
    }

    public TreeNode bstFromPostorder3(int[] postorder) {
        this.postorder = postorder;
        this.idx = postorder.length - 1;
        this.n = postorder.length;

        return helper(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    /**
     * 方法4：迭代法
     */
    public TreeNode bstFromPostorder4(int[] postorder) {
        int n = postorder.length;
        if (n == 0) return null;

        TreeNode root = new TreeNode(postorder[postorder.length - 1]);
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.push(root);

        for (int i = postorder.length - 2; i >= 0; i--) {
            TreeNode node = deque.peek();
            TreeNode child = new TreeNode(postorder[i]);

            while (!deque.isEmpty() && deque.peek().val > child.val) {
                node = deque.pop();
            }

            if (node.val < child.val) {
                node.right = child;
            } else {
                node.left = child;
            }
            deque.push(child);
        }
        return root;
    }

    /**
     *          8
     *        /   \
     *       5    10
     *     /  \    \
     *    1    7    12
     */
    @Test
    public void testCase01() {
        int[] postorder = {1, 7, 5, 12, 10, 8};
        System.out.println(bstFromPostorder(postorder));
        System.out.println(bstFromPostorder2(postorder));
        System.out.println(bstFromPostorder3(postorder));
        System.out.println(bstFromPostorder4(postorder));
    }

    /**
     *          8
     *           \
     *           10
     *             \
     *              12
     */
    @Test
    public void testCase02() {
        int[] postorder = {12, 10, 8};
        System.out.println(bstFromPostorder(postorder));
        System.out.println(bstFromPostorder2(postorder));
        System.out.println(bstFromPostorder3(postorder));
        System.out.println(bstFromPostorder4(postorder));
    }

    /**
     *          8
     *        /
     *       5
     *     /
     *    1
     */
    @Test
    public void testCase03() {
        int[] postorder = {1, 5, 8};
        System.out.println(bstFromPostorder(postorder));
        System.out.println(bstFromPostorder2(postorder));
        System.out.println(bstFromPostorder3(postorder));
        System.out.println(bstFromPostorder4(postorder));
    }

    /**
     *           8
     *        /     \
     *       5      10
     *     /  \    /  \
     *    1    7  11   12
     */
    @Test
    public void testCase04() {
        int[] postorder = {1, 7, 5, 11, 12, 10, 8};
        System.out.println(bstFromPostorder(postorder));
        System.out.println(bstFromPostorder2(postorder));
        System.out.println(bstFromPostorder3(postorder));
        System.out.println(bstFromPostorder4(postorder));
    }
}

