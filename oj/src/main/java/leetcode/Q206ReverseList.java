package leetcode;

import common.ListNode;
import org.junit.Test;

public class Q206ReverseList {
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode pre = null;
        ListNode tmp;
        ListNode iter = head;
        while (iter != null) {
            tmp = iter.next;
            iter.next = pre;
            pre = iter;
            iter = tmp;
        }
        return pre;
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
    }
}
