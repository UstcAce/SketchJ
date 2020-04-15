package leetcode;

import common.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的二叉搜索树。
 * 示例:
 *
 * 输入: 3
 * 输出:
 * [
 *   [1,null,3,2],
 *   [3,2,null,1],
 *   [3,1,null,null,2],
 *   [2,1,3],
 *   [1,null,2,null,3]
 * ]
 * 解释:
 * 以上的输出对应以下 5 种不同结构的二叉搜索树：
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 *
 */
public class Q95UniqueBinarySearchTrees2 {
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) return new ArrayList<>();
        return generateNode(1, n);
    }

    public List<TreeNode> generateNode(int start, int end) {
        if (start > end) {
            return null;
        } if (start == end) {
            return new ArrayList<>(Arrays.asList(new TreeNode(start)));
        } else {
            List<TreeNode> result = new ArrayList<>();
            for (int i = start; i <= end; i++) {
                List<TreeNode> leftList = i - 1 < start ? new ArrayList<>(Collections.singleton(null)) : generateNode(start, i - 1);
                List<TreeNode> rightList = i + 1 > end ? new ArrayList<>(Collections.singleton(null)) : generateNode(i + 1, end);
                for (TreeNode left : leftList) {
                    for (TreeNode right : rightList) {
                        TreeNode root = new TreeNode(i);
                        root.left = left;
                        root.right = right;
                        result.add(root);
                    }
                }
            }
            return result;
        }
    }

    @Test
    public void testCase01() {
        System.out.println(generateTrees(3));
    }
}
