package leetcode;

import common.TreeNode;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 *
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 */
public class Q297SerializeAndDeserializeBinaryTree {
    /**
     * 输出叶子节点空孩子的 前序遍历： 根-左-右
     */
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        preOrderTraversal(sb, root);
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    private void preOrderTraversal(StringBuilder temp, TreeNode root) {
        if (root == null) {
            temp.append("null,");
            return;
        }
        temp.append(root.val).append(",");
        preOrderTraversal(temp, root.left);
        preOrderTraversal(temp, root.right);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        List<String> list = Arrays.stream(data.split(",")).collect(Collectors.toList());
        return deserializeRecursively(list);
    }

    private TreeNode deserializeRecursively(List<String> list) {
        if (list.isEmpty()) {
            return null;
        }
        String str = list.remove(0);
        if (str.equals("null")) {
            return null;
        } else {
            TreeNode node = new TreeNode(Integer.parseInt(str));
            node.left = deserializeRecursively(list);
            node.right = deserializeRecursively(list);
            return node;
        }
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

    @Test
    public void testCase02() {
        String[] str = "1,2,3,".split(",");
        System.out.println(Arrays.toString(str));
    }
}

