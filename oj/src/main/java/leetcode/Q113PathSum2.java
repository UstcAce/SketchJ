package leetcode;

import common.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
 * 说明: 叶子节点是指没有子节点的节点。
 */
public class Q113PathSum2 {
    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root == null) {
            return res;
        }
        dfs(root, new ArrayList<>(), 0, sum);
        return res;
    }

    private void dfs(TreeNode node, List<Integer> temp, int localSum, int targetSum) {
        TreeNode left = node.left;
        TreeNode right = node.right;
        if (left == null && right == null) {
            if (localSum + node.val == targetSum) {
                temp.add(node.val);
                res.add(new ArrayList<>(temp));
                temp.remove(temp.size() - 1);
            }
            return;
        }

        if (left != null) {
            temp.add(node.val);
            dfs(left, temp, localSum + node.val, targetSum);
            temp.remove(temp.size() - 1);
        }
        if (right != null) {
            temp.add(node.val);
            dfs(right, temp, localSum + node.val, targetSum);
            temp.remove(temp.size() - 1);
        }
    }

    @Test
    public void testCase01() {
        TreeNode node = null;
        int targetSum = 10;
        System.out.println(pathSum(node, targetSum));
    }

    @Test
    public void testCase02() {
        TreeNode node = new TreeNode(10);
        int targetSum = 10;
        System.out.println(pathSum(node, targetSum));
    }

    @Test
    public void testCase03() {
        TreeNode node = new TreeNode(10);
        int targetSum = 1;
        System.out.println(pathSum(node, targetSum));
    }

//                           5
//                          / \
//                         4   8
//                        /   / \
//                       11  13  4
//                      /  \    / \
//                     7    2  5   1
    @Test
    public void testCase04() {
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(4);
        TreeNode node3 = new TreeNode(8);
        TreeNode node4 = new TreeNode(11);
        TreeNode node5 = new TreeNode(13);
        TreeNode node6 = new TreeNode(4);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(2);
        TreeNode node9 = new TreeNode(5);
        TreeNode node10 = new TreeNode(1);

        node1.left = node2;
        node1.right = node3;

        node2.left = node4;

        node3.left = node5;
        node3.right = node6;

        node4.left = node7;
        node4.right = node8;

        node6.left = node9;
        node6.right = node10;

        int targetSum = 22;
        System.out.println(pathSum(node1, targetSum));
    }
}
