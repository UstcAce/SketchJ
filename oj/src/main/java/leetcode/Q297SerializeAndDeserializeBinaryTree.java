package leetcode;

import common.TreeNode;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 * <p>
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 */
public class Q297SerializeAndDeserializeBinaryTree {

    public String serialize(TreeNode root) {
        StringBuilder res = inOrderNonRecur(root, new StringBuilder());
        return res.toString();
    }

    /**
     * 前序遍历，根-左-右
     *     1
     *    / \
     *   2   3
     *      / \
     *     4   5
     * 1,2,null,null,3,4,null,5,null,null
     */
    StringBuilder preOrderRecur(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("null,");
            return sb;
        } else {
            sb.append(root.val);
            sb.append(",");

            preOrderRecur(root.left, sb);
            preOrderRecur(root.right, sb);
        }
        return sb;
    }

    StringBuilder preOrderNonRecur(TreeNode root, StringBuilder sb) {
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);

        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            sb.append(pop == null ? "null" : pop.val).append(",");
            if (pop == null) continue;
            stack.add(pop.right);
            stack.add(pop.left);
        }

        return sb;
    }

    /**
     * 中序遍历，左-根-右
     *     1
     *    / \
     *   2   3
     *      / \
     *     4   5
     * null,2,null,1,null,4,null,3,null,5,null
     */
    StringBuilder inOrderNonRecur(TreeNode root, StringBuilder sb) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode iter = root;
        sb.append("null").append(",");
        while (iter != null || !stack.isEmpty()) {
            while (iter != null) {
                stack.add(iter);
                iter = iter.left;
            }
            TreeNode pop = stack.pop();
            sb.append(pop.val).append(",null,");
            iter = pop.right;
        }
        return sb;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] temp = data.split(","); // 将序列化的结果转为字符串数组
        List<String> list = new LinkedList<>(Arrays.asList(temp)); // 字符串数组转为集合类 便于操作
        return inOrderDeser(list);
    }

    /**
     * 反前序遍历的序列化
     */
    public TreeNode preOrderDeser(List<String> list) {
        TreeNode root;
        if (list.get(0).equals("null")) {
            list.remove(0); // 删除第一个元素 则第二个元素成为新的首部 便于递归
            return null;
        } else {
            root = new TreeNode(Integer.parseInt(list.get(0)));
            root.left = preOrderDeser(list);
            root.right = preOrderDeser(list);
        }
        return root;
    }

    /**
     * 反中序遍历的序列化
     * 左-根-右
     */
    public TreeNode inOrderDeser(List<String> list) {
        return null;
    }

    /**
     *     1
     *    / \
     *   2   3
     *      / \
     *     4   5
     */
    @Test
    public void testCase01() {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);

        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        node3.right = node5;

        String res = serialize(node1);
        System.out.println(res);

        System.out.println(deserialize(res));
    }
}
