package leetcode;

import common.TreeNode;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 * <p>
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 */
public class Q297SerializeAndDeserializeBinaryTree {

    public String serialize2(TreeNode root) {
        StringBuilder res = mySeri(root, new StringBuilder());
        return res.toString();
    }

    StringBuilder mySeri(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("null,");
            return sb;
        } else if (root != null) {
            sb.append(root.val);
            sb.append(",");

            mySeri(root.left, sb);
            mySeri(root.right, sb);
        }
        return sb;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize2(String data) {
        String[] temp = data.split(","); // 将序列化的结果转为字符串数组
        List<String> list = new LinkedList<>(Arrays.asList(temp)); // 字符串数组转为集合类 便于操作
        return myDeSeri(list);
    }

    public TreeNode myDeSeri(List<String> list) {
        TreeNode root;
        if (list.get(0).equals("null")) {
            list.remove(0); // 删除第一个元素 则第二个元素成为新的首部 便于递归
            return null;
        } else {
            root = new TreeNode(Integer.valueOf(list.get(0)));
            list.remove(0);
            root.left = myDeSeri(list);
            root.right = myDeSeri(list);
        }
        return root;
    }


    class NodeWithDepth {
        public TreeNode node;
        public int depth;

        public NodeWithDepth(TreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }

    public String serialize(TreeNode root) {
        if (root == null) return "";
        int maxDepth = calcMaxDepth(root, 1);
        LinkedList<NodeWithDepth> list = new LinkedList<>(Arrays.asList(new NodeWithDepth(root, 1)));
        StringBuilder sb = new StringBuilder();
        while (!list.isEmpty()) {
            NodeWithDepth pop = list.pop();
            int depth = pop.depth;
            sb.append(pop.node.val).append(" ");
            if (depth != maxDepth) {
                TreeNode left = pop.node.left;
                TreeNode right = pop.node.right;

                if (left != null) {
                    list.add(new NodeWithDepth(left, depth + 1));
                } else {
                    list.add(new NodeWithDepth(new TreeNode(Integer.MAX_VALUE), depth + 1));
                }

                if (right != null) {
                    list.add(new NodeWithDepth(right, depth + 1));
                } else {
                    list.add(new NodeWithDepth(new TreeNode(Integer.MAX_VALUE), depth + 1));
                }
            }
        }

        return sb.toString();
    }

    private int calcMaxDepth(TreeNode root, int depth) {
        TreeNode left = root.left;
        TreeNode right = root.right;

        if (left != null && right != null) {
            return Math.max(calcMaxDepth(left, depth + 1), calcMaxDepth(right, depth + 1));
        } else if (left != null) {
            return calcMaxDepth(left, depth + 1);
        } else if (right != null) {
            return calcMaxDepth(right, depth + 1);
        } else {
            return depth;
        }
    }

    public TreeNode deserialize(String data) {
        if (data.isEmpty()) return null;
        String[] arr = data.split(" ");
        int len = arr.length;
        TreeNode[] nodeArr = new TreeNode[len];
        for (int i = 0; i < len; i++) {
            int num = Integer.parseInt(arr[i]);
            if (num != Integer.MAX_VALUE) {
                nodeArr[i] = new TreeNode(num);
            }
        }

        for (int i = 0; i <= len / 2 - 1; i++) {
            TreeNode node = nodeArr[i];
            if (node != null) {
                TreeNode left = nodeArr[2 * i + 1];
                TreeNode right = nodeArr[2 * i + 2];
                node.left = left;
                node.right = right;
            }
        }

        return nodeArr[0];
    }

    /**
     * 1
     * / \
     * 2   3
     * / \
     * 4   5
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

        System.out.println(calcMaxDepth(node1, 1));
        String res = serialize(node1);
        System.out.println(serialize(node1));

        System.out.println(deserialize(res));

        System.out.println();
    }

    @Test
    public void testCase02() {
        TreeNode node1 = new TreeNode(1);

        String res = serialize(node1);
        System.out.println(res);

    }
}
