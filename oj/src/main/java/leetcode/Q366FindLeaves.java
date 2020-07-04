package leetcode;

import common.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 给你一棵二叉树，请按以下要求的顺序收集它的全部节点：
 1.依次从左到右，每次收集并删除所有的叶子节点
 2. 重复如上过程直到整棵树为空

 示例:
 输入: [1,2,3,4,5]

     1
    / \
   2   3
  / \
 4   5

 输出: [[4,5,3],[2],[1]]


 解释:
 1. 删除叶子节点 [4,5,3] ，得到如下树结构：

 1
 /
 2

 2. 现在删去叶子节点 [2] ，得到如下树结构：

 1

 3. 现在删去叶子节点 [1] ，得到空树：

 []
 */
public class Q366FindLeaves {
    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> findLeaves(TreeNode root) {
        if (root == null) {
            return result;
        }
        traceBack(root);
        return result;
    }

    private void traceBack(TreeNode root) {
        while (root.left != null || root.right != null) {
            List<Integer> temp = new ArrayList<>();
            dfs(null, root, temp);
            result.add(temp);
        }
        result.add(new ArrayList<>(Arrays.asList(root.val)));
    }

    private void dfs(TreeNode parent, TreeNode node, List<Integer> temp) {
        TreeNode left = node.left;
        TreeNode right = node.right;
        if (left == null && right == null) {
            temp.add(node.val);
            if (parent != null) {
                if (parent.left == node) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            }
        } else if (left != null && right == null) {
            dfs(node, left, temp);
        } else if (left == null && right != null) {
            dfs(node, right, temp);
        } else {
            dfs(node, left, temp);
            dfs(node, right, temp);
        }
    }
}
