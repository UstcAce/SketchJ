package leetcode;

import common.TreeNode;

import java.util.Stack;

/**
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，
 * 满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 *
 * 说明:
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉树中。
 */
public class Q236LowestCommonAncestor {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == p || root == q) {
            return root;
        }
        if (root.left == null) {
            return lowestCommonAncestor(root.right, p, q);
        }

        if (root.right == null) {
            return lowestCommonAncestor(root.left, p, q);
        }

        int count = postOrderTraversal(root.left, p, q);
        if (count == 0) {
            return lowestCommonAncestor(root.right, p, q);
        } else if (count == 1) {
            return root;
        } else {
            return lowestCommonAncestor(root.left, p, q);
        }
    }

    /**
     * 非递归后续遍历查看以node为根的节点中包含p,q点的数目
     */
    private int postOrderTraversal(TreeNode node, TreeNode p, TreeNode q) {
        int count = 0;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode iter = node;
        TreeNode pre = null;
        while (iter != null || !stack.isEmpty()) {
            while (iter != null) {
                stack.add(iter);
                iter = iter.left;
            }
            TreeNode temp = stack.peek();
            // 当前节点的右子树为空 或者 上一个遍历到的节点为当前节点的右子树
            if (temp.right == null || temp.right == pre) {
                stack.pop();
                if (temp == p || temp == q) {
                    count++;
                }
                pre = temp;
            } else {
                iter = temp.right;
            }
        }
        return count;
    }

    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor2(root.left, p, q);
        TreeNode right = lowestCommonAncestor2(root.right, p, q);
        if (left == null && right == null)
            return null;
        else if (left != null && right != null)
            return root;
        else
            return left == null ? right : left;
    }
}
