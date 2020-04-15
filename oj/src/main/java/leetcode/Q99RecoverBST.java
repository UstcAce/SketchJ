package leetcode;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Q99RecoverBST {
    public void recoverTree(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();

        Stack<TreeNode> stack = new Stack<>();

        TreeNode pre = null;
        TreeNode first = null;
        TreeNode second = null;

        TreeNode iter = root;
        while (!stack.isEmpty() || iter != null) {
            while (iter != null) {
                stack.add(iter);
                iter = iter.left;
            }
            TreeNode pop = stack.pop();


            if (pre != null && first == null && pre.val >= pop.val) {
                first = pre;
            }
            if (pre != null && first != null && pre.val >= pop.val) {
                second = pop;
            }
            pre = pop;

            list.add(pop);
            iter = pop.right;
        }
        swapNodes(first, second);
    }

    public void swapNodes(TreeNode node1, TreeNode node2) {
        int temp = node1.val;
        node1.val = node2.val;
        node2.val = temp;
    }
}
