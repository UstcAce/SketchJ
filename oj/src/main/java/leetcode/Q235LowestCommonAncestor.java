package leetcode;

import common.TreeNode;

public class Q235LowestCommonAncestor {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode small = p.val >= q.val ? q : p ;
        TreeNode large = p.val >= q.val ? p : q;
        if (small.val==root.val || large.val==root.val || (small.val<root.val && large.val>root.val)) {
            return root;
        } else if (root.val < small.val) {
            return lowestCommonAncestor(root.right, p, q);
        } else {
            return lowestCommonAncestor(root.left, p, q);
        }
    }
}
