package microsoft;

import common.TreeNode;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 对于普通的二叉树，如何找到两个给定节点之家的距离？
 * 距离是指连接两个节点需要的最小的边的条数。
 *                   1
 *                /     \
 *              2         3
 *           /   \       /  \
 *         4      5     6    7
 *                       \
 *                        8
 *    dist(4, 5) = 2
 *    dist(4, 6) = 4
 *    dist(3, 4) = 3
 *    dist(2, 4) = 1
 *    dist(8, 5) = 5
 */
public class NodeDistanceInBinaryTree {
    /**
     * 1. 计算两个点的最近公共最先lca
     * 2. 计算两个点+lca的深度
     */
    // 记录点的深度
    Map<TreeNode, Integer> map = new HashMap<>();

    public int calcDistanceInBinaryTree(TreeNode root, TreeNode node1, TreeNode node2) {
        TreeNode lca = lowestCommonAncestor(root, node1, node2);
        preOrderTraversal(root, 0);
        return map.get(node1) + map.get(node2) - 2 * map.get(lca);
    }

    private void preOrderTraversal(TreeNode root, int depth) {
        if (root == null) {
            return;
        }
        map.put(root, depth);
        preOrderTraversal(root.left, depth + 1);
        preOrderTraversal(root.right, depth + 1);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode node1, TreeNode node2) {
        if (root == null || node1 == root || node2 == root) {
            return root;
        }
        TreeNode leftRes = lowestCommonAncestor(root.left, node1, node2);
        TreeNode rightRes = lowestCommonAncestor(root.right, node1, node2);
        if (leftRes != null && rightRes != null) {
            return root;
        } else if (leftRes != null) {
            return leftRes;
        } else {
            return rightRes;
        }
    }

    /**
     *                   1
     *                /     \
     *              2         3
     *           /   \       /  \
     *         4      5     6    7
     *                       \
     *                        8
     *
     */
    @Test
    public void testCase01() {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);

        node1.left = node2;
        node1.right = node3;

        node2.left = node4;
        node2.right = node5;

        node3.left = node6;
        node3.right = node7;

        node6.right = node8;

        /**
         *    dist(4, 5) = 2
         *    dist(4, 6) = 4
         *    dist(3, 4) = 3
         *    dist(2, 4) = 1
         *    dist(8, 5) = 5
         */
        System.out.println(calcDistanceInBinaryTree(node1, node4, node5));
        System.out.println(calcDistanceInBinaryTree(node1, node4, node6));
        System.out.println(calcDistanceInBinaryTree(node1, node3, node4));
        System.out.println(calcDistanceInBinaryTree(node1, node2, node4));
        System.out.println(calcDistanceInBinaryTree(node1, node8, node5));
    }
}
