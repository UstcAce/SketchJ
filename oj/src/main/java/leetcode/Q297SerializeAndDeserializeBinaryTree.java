package leetcode;

import common.TreeNode;
import org.junit.Test;

import java.util.*;

/**
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 *
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 */
public class Q297SerializeAndDeserializeBinaryTree {
    public String serialize(TreeNode root) {
        StringBuilder res = preOrderNonRecur(root, new StringBuilder());
        return res.toString();
    }

    /**
     * 前序遍历(DFS)，根-左-右
     *     1
     *    / \
     *   2   3
     *      / \
     *     4   5
     * 1,2,null,null,3,4,null,null,5,null,null
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

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        // 将序列化的结果转为字符串数组
        String[] temp = data.split(",");
        // 字符串数组转为集合类便于操作
        LinkedList<String> list = new LinkedList<>(Arrays.asList(temp));
        return preOrderDeser(list);
    }

    /**
     * 反前序遍历(DFS)的序列化
     */
    public TreeNode preOrderDeser(LinkedList<String> list) {
        TreeNode root;
        if (list.peekFirst().equals("null")) {
            // 删除第一个元素 则第二个元素成为新的首部 便于递归
            list.pollFirst();
            return null;
        } else {
            root = new TreeNode(Integer.parseInt(list.peekFirst()));
            list.pollFirst();
            root.left = preOrderDeser(list);
            root.right = preOrderDeser(list);
        }
        return root;
    }

    public String serialize2(TreeNode root) {
        StringBuilder res = postOrderRecur(root, new StringBuilder());
        return res.toString();
    }

    /**
     * 后序遍历，左-右-根
     *     1
     *    / \
     *   2   3
     *      / \
     *     4   5
     * null,null,2,null,null,4,null,null,5,3,1
     */
    StringBuilder postOrderRecur(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("null,");
            return sb;
        } else {
            postOrderRecur(root.left, sb);
            postOrderRecur(root.right, sb);
            sb.append(root.val);
            sb.append(",");
        }
        return sb;
    }

    public TreeNode deserialize2(String data) {
        // 将序列化的结果转为字符串数组
        String[] temp = data.split(",");
        // 字符串数组转为集合类便于操作
        LinkedList<String> list = new LinkedList<>(Arrays.asList(temp));
        return postOrderDeser(list);
    }

    /**
     * 反后序遍历(DFS)的序列化
     */
    private TreeNode postOrderDeser(LinkedList<String> list) {
        TreeNode root;
        if (list.peekLast().equals("null")) {
            // 删除倒数第一个元素 则倒数第二个元素成为新的尾部 便于递归
            list.pollLast();
            return null;
        } else {
            root = new TreeNode(Integer.parseInt(list.pollLast()));
            root.right = postOrderDeser(list);
            root.left = postOrderDeser(list);
        }
        return root;
    }

    /**
     * 层次遍历(BFS)
     */
    public String serialize3(TreeNode root) {
        if (root == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode pop = queue.removeFirst();
            sb.append(pop == null ? "null," : (pop.val + ","));
            if (pop != null) {
                queue.add(pop.left);
                queue.add(pop.right);
            }
        }
        return sb.toString();
    }

    /**
     * 反层次遍历(BFS)的序列化
     */
    public TreeNode deserialize3(String data) {
        if (data.isEmpty()) return null;
        String[] strs = data.split(",");
        Integer[] layerNode = new Integer[strs.length];

        for (int i = 0; i < strs.length; i++) {
            layerNode[i] = strs[i].equals("null") ? null : Integer.parseInt(strs[i]);
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        TreeNode root = new TreeNode(layerNode[0]);
        queue.add(root);
        int cur = 1;
        while (!queue.isEmpty()) {
            TreeNode pop = queue.poll();
            if (layerNode[cur] != null) {
                pop.left = new TreeNode(layerNode[cur]);
                queue.add(pop.left);
            }
            cur++;
            if (layerNode[cur] != null) {
                pop.right = new TreeNode(layerNode[cur]);
                queue.add(pop.right);
            }
            cur++;
        }

        return root;
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

