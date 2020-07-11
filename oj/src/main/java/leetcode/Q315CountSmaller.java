package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个整数数组 nums，按要求返回一个新数组 counts。
 * 数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。
 *
 * 示例:
 * 输入: [5,2,6,1]
 * 输出: [2,1,1,0]
 * 解释:
 * 5 的右侧有 2 个更小的元素 (2 和 1).
 * 2 的右侧仅有 1 个更小的元素 (1).
 * 6 的右侧有 1 个更小的元素 (1).
 * 1 的右侧有 0 个更小的元素.
 */
public class Q315CountSmaller {
    public List<Integer> countSmaller(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return new ArrayList<>();
        }
        if (len == 1) {
            return Arrays.asList(0);
        }

        LinkedList<Integer> result = new LinkedList<>();
        result.addFirst(0);
        TreeNode root = new TreeNode(nums[len - 1], 1, 0);
        for (int i = len - 2; i >= 0; i--) {
            insertVal(root, result, 0, nums[i]);
        }
        return result;
    }

    private void insertVal(TreeNode node, LinkedList<Integer> res, int temp, int val) {
        if (node.val == val) {
            node.count++;
            res.addFirst(node.leftCount + temp);
        } else if (val < node.val) {
            if (node.left == null) {
                node.left = new TreeNode(val, 1, 0);
                node.leftCount++;
                res.addFirst(temp);
            } else {
                node.leftCount++;
                insertVal(node.left, res, temp, val);
            }
        } else {  // 每次往右子树走，temp += (node.count + node.leftCount)
            if (node.right == null) {
                node.right = new TreeNode(val, 1, 0);
                res.addFirst(temp + node.count + node.leftCount);
            } else {
                insertVal(node.right, res, temp + node.count + node.leftCount, val);
            }
        }
    }

    class TreeNode {
        public int val;

        // 当前节点重复数值的个数
        public int count;

        // 左子树数值的个数
        public int leftCount;

        public TreeNode left;

        public TreeNode right;

        public TreeNode(int val, int count, int leftCount) {
            this.val = val;
            this.count = count;
            this.leftCount = leftCount;
        }
    }
}
