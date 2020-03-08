package leetcode;

import common.ListNode;
import org.junit.Test;

/**
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * 示例:
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 *
 */
public class Q24SwapPairs {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode iter = head;
        ListNode newHead = null;
        ListNode next2Step;
        ListNode pre = null;
        while (iter != null && iter.next != null) {
            next2Step = iter.next.next;
            if (pre == null) {
                pre = iter;
                newHead = iter.next;
                iter.next = next2Step;
                newHead.next = iter;
            } else {
                pre.next = iter.next;
                iter.next.next = iter;
                pre = iter;
                iter.next = next2Step;
            }
            iter = next2Step;
        }
        return newHead;
    }

    @Test
    public void testCase01() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        System.out.println(swapPairs(node1));
    }
}
