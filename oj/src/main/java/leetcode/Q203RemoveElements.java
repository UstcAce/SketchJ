package leetcode;

import common.ListNode;
import org.junit.Test;

/**
 * 删除链表中等于给定值 val 的所有节点。
 *
 * 示例:
 *
 * 输入: 1->2->6->3->4->5->6, val = 6
 * 输出: 1->2->3->4->5
 */
public class Q203RemoveElements {
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) return null;

        ListNode dummy = new ListNode(val + 1);
        dummy.next = head;
        ListNode pre = dummy;

        ListNode iter = head;

        while (iter != null) {
            if (iter.val == val) {
                pre.next = iter.next;
                iter = iter.next;
            } else {
                iter = iter.next;
                pre = pre.next;
            }
        }

        return dummy.next;
    }

    @Test
    public void testCase01() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(6);
        ListNode node4 = new ListNode(3);
        ListNode node5 = new ListNode(4);
        ListNode node6 = new ListNode(5);
        ListNode node7 = new ListNode(6);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        System.out.println(removeElements(node1, 6));
    }
}
