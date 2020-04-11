package leetcode;

import common.ListNode;

/**
 * 给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
 *
 * 你应当保留两个分区中每个节点的初始相对位置。
 *
 * 示例:
 *
 * 输入: head = 1->4->3->2->5->2, x = 3
 * 输出: 1->2->2->4->3->5
 */
public class Q86PartitionList {
    public ListNode partition(ListNode head, int x) {
        ListNode dummy1 = new ListNode(-1);
        ListNode dummy2 = new ListNode(-1);

        ListNode iter1 = dummy1;
        ListNode iter2 = dummy2;
        ListNode iter = head;

        while (iter != null) {
            ListNode next = iter.next;
            iter.next = null;
            if (iter.val < x) {
                iter1.next = iter;
                iter1 = iter1.next;
            } else {
                iter2.next = iter;
                iter2 = iter2.next;
            }
            iter = next;
        }

        if (dummy1.next == null) {
            return dummy2.next;
        } else {
            iter1.next = dummy2.next;
            return dummy1.next;
        }
    }
}
