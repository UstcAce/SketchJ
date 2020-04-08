package leetcode;

import common.ListNode;

/**
 * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
 *
 * 示例 1:
 * 输入: 1->2->3->3->4->4->5
 * 输出: 1->2->5
 *
 *  示例 2:
 * 输入: 1->1->1->2->3
 * 输出: 2->3
 *
 */
public class Q82DeleteDuplicates {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode iter = head;
        while (iter != null) {
            if (iter.next == null) {
                iter = null;
            } else {
                if (iter.val == iter.next.val) {
                    int num = iter.val;
                    while (iter != null && iter.val == num) {
                        iter = iter.next;
                    }
                    pre.next = iter;
                } else {
                    iter = iter.next;
                    pre = pre.next;
                }
            }
        }

        return dummy.next;
    }
}
