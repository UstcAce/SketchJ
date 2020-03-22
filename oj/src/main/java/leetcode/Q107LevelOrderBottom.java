package leetcode;

import common.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Q107LevelOrderBottom {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) {
            return new LinkedList<>();
        }
        LinkedList<List<Integer>> result = new LinkedList<>();

        List<TreeNode> iter = new LinkedList<>(Arrays.asList(root));
        while (!iter.isEmpty()) {
            List<Integer> local = new LinkedList<>();
            List<TreeNode> newIter = new LinkedList<>();
            for (TreeNode node : iter) {
                local.add(node.val);
                if (node.left != null) {
                    newIter.add(node.left);
                }
                if (node.right != null) {
                    newIter.add(node.right);
                }
            }
            result.addFirst(local);
            iter = newIter;
        }

        return result;
    }
}
