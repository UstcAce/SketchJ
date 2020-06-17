package common;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                ", left=" + left +
                ", right=" + right +
                '}';
    }

    /**
     * 二叉树的非递归后序遍历：左-右-根
     * 遍历时需要判断是从左子树回到根节点还是从右子树回到根节点
     * (1) 若当前节点右子树为空 或者 上一个遍历节点是当前节点的右子树，则弹出当前节点
     * (2) 否则迭代节点=当前节点的右子树
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        TreeNode iter = root;
        TreeNode last = null;
        Stack<TreeNode> stack = new Stack<>();
        while (iter != null || !stack.isEmpty()) {
            while (iter != null) {
                stack.add(iter);
                iter = iter.left;
            }
            TreeNode node = stack.peek();
            if (node.right == null || node.right == last) {
                stack.pop();
                result.add(node.val);
                last = node;
            } else {
                iter = node.right;
            }
        }

        return result;
    }
}
