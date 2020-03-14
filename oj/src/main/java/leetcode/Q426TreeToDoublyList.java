package leetcode;

import common.Node;
import org.junit.Test;

import java.util.Stack;


/**
 * 将一个二叉搜索树就地转化为一个已排序的双向循环链表。可以将左右孩子指针作为双向循环链表的前驱和后继指针。
 */
public class Q426TreeToDoublyList {

    /**
     * 二插排序树，左-根-右，
     * 迭代解法中序遍历
     */
    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        Node first = null;
        Node last = null;

        Stack<Node> stack = new Stack<>();
        Node iter = root;
        while (iter != null || !stack.isEmpty()) {
            while (iter != null) {
                stack.add(iter);
                iter = iter.left;
            }
            Node pop = stack.pop();
            if (first == null) {
                first = pop;
            }

            if (last == null) {
                last = pop;
            } else {
                last.right = pop;
                pop.left = last;
                last = pop;
            }
            iter = pop.right;
        }
        first.left = last;
        last.right = first;
        return first;
    }


    /**
     * 二插排序树，左-根-右，
     * 递归解法中序遍历
     */
    private Node first;

    private Node last;

    public Node treeToDoublyList2(Node root) {
        if (root == null) {
            return null;
        }
        inOrderTraversal(root);
        first.left = last;
        last.right = first;
        return first;
    }

    private void inOrderTraversal(Node root) {
        if (root == null) {
            return;
        }
        inOrderTraversal(root.left);
        if (first == null) {
            first = root;
        }

        if (last == null) {
            last = root;
        } else {
            last.right = root;
            root.left = last;
            last = root;
        }
        inOrderTraversal(root.right);
    }


    @Test
    public void testCase01() {
        Node node1 = new Node(4);
        Node node2 = new Node(2);
        Node node3 = new Node(5);
        Node node4 = new Node(1);
        Node node5 = new Node(3);

        node1.left = node2;
        node1.right = node3;

        node2.left = node4;
        node2.right = node5;

        treeToDoublyList(node1);
        System.out.println();
    }

    @Test
    public void testCase02() {
        Node node1 = new Node(30);
        Node node2 = new Node(13);
        Node node3 = new Node(-28);
        Node node4 = new Node(-44);
        Node node5 = new Node(-35);

        node1.left = node2;

        node2.left = node3;

        node3.left = node4;

        node4.right = node5;

        treeToDoublyList(node1);
        System.out.println();
    }

    @Test
    public void testCase03() {
        Node node1 = new Node(2);
        Node node2 = new Node(4);

        Node node1dot5 = new Node(3);

        Node node3 = new Node(6);
        Node node4 = new Node(8);
        Node node5 = new Node(10);

        node1.right = node2;

        node2.right = node3;
        node2.left = node1dot5;

        node3.right = node4;

        node4.right = node5;

        treeToDoublyList(node1);
        System.out.println();
    }
}
