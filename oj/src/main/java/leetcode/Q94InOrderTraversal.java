package leetcode;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个二叉树，返回它的中序 遍历。
 *
 * 示例:
 *
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [1,3,2]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 *
 */
public class Q94InOrderTraversal {

    /**
     * 二叉树中序遍历 左-根-右
     * 迭代解法，使用堆栈
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode iter = root;
        while (iter != null || !stack.empty()) {
            if (iter != null) {
                stack.push(iter);
                iter = iter.left;
            } else {
                TreeNode node = stack.pop();
                result.add(node.val);
                if (node.right != null) {
                    iter = node.right;
                }
            }
        }

        return result;
    }
}
