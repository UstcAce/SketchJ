package leetcode;

import common.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 *
 * 示例 1:
 *
 * 输入: 1->1->2
 * 输出: 1->2
 * 示例 2:
 *
 * 输入: 1->1->2->3->3
 * 输出: 1->2->3
 *
 */
public class Q83DeleteDuplicates {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }

        Set<Integer> set = new HashSet<>();
        ListNode pre = head;
        ListNode iter = head;
        while (iter != null) {
            if (set.contains(iter.val)) {
                pre.next = iter.next;
                iter = iter.next;
            } else {
                set.add(iter.val);
                pre = iter;
                iter = iter.next;
            }
        }
        return head;
    }

    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode pre = null;
        ListNode iter = head;
        while (iter != null) {
            if (pre!=null && pre.val == iter.val) {
                pre.next = iter.next;
                iter = iter.next;
            } else {
                pre = iter;
                iter = iter.next;
            }
        }
        return head;
    }
}
