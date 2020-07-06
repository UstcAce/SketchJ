package leetcode;

import common.ListNode;
import org.junit.Test;

/**
 * 反转一个单链表。
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 */
public class Q206ReverseList {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode pre = null;
        ListNode iter = head;
        while (iter != null) {
            ListNode next = iter.next;
            iter.next = pre;
            pre = iter;
            iter = next;
        }
        return pre;
    }

    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode oriNext = head.next;
        ListNode newNext = reverseList2(head.next);
        oriNext.next = head;
        head.next = null;
        return newNext;
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

        ListNode res = reverseList(node1);

        System.out.println(res);

        System.out.println(reverseList2(res));
    }
}
