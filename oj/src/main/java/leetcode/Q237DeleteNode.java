package leetcode;

import common.ListNode;
import org.junit.Test;

/**
 * 请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点，你将只被给定要求被删除的节点。
 *
 * 现有一个链表 -- head = [4,5,1,9]
 * (1) 链表至少包含两个节点。
 * (2) 链表中所有节点的值都是唯一的。
 * (3) 给定的节点为非末尾节点并且一定是链表中的一个有效节点。
 * (4) 不要从你的函数中返回任何结果。
 *
 * 题解：
 * 从链表里删除一个节点 node 的最常见方法是修改之前节点的 next 指针，使其指向之后的节点。
 * 因为，我们无法访问我们想要删除的节点 之前 的节点，我们始终不能修改该节点的 next 指针。相反，我们必须将想要删除的节点的值替换为它后面节点中的值，然后删除它之后的节点。
 */
public class Q237DeleteNode {
    public void deleteNode(ListNode node) {
        ListNode next = node.next;
        node.val = next.val;
        node.next = next.next;
    }


    @Test
    public void test01() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        System.out.println(node1);

        deleteNode(node2);

        System.out.println(node1);
    }
}
