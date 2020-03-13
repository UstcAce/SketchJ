package leetcode;

import common.ListNode;
import org.junit.Test;

/**
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 *
 * 示例:
 *
 * 输入:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 *
 */
public class Q23MergeKSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        int len = lists.length;
        if (len == 0) {
            return null;
        }

        if (len == 1) {
            return lists[0];
        }

        ListNode node = lists[0];
        for (int i = 1; i < len; i++) {
            node = mergeTwoSortedList(node, lists[i]);
        }
        return node;
    }

    public ListNode mergeKLists2(ListNode[] lists) {
        int len = lists.length;
        if (len == 0) {
            return null;
        }

        if (len == 1) {
            return lists[0];
        }
        return mergeKLists2(lists, 0, len - 1);
    }

    private ListNode mergeKLists2(ListNode[] lists, int start, int end) {
        if (start == end) {
            return lists[start];
        } else if (start + 1 == end) {
            return mergeTwoSortedList(lists[start], lists[end]);
        } else {
            int mid = (start + end) / 2;
            ListNode left = mergeKLists2(lists, start, mid);
            ListNode right = mergeKLists2(lists, mid + 1, end);
            return mergeTwoSortedList(left, right);
        }
    }


    private ListNode mergeTwoSortedList(ListNode node1, ListNode node2) {
        if (node1 == null) {
            return node2;
        } else if (node2 == null) {
            return node1;
        }

        ListNode dummy = new ListNode(0);
        ListNode iter = dummy;
        while (node1 != null && node2 != null) {
            if (node1.val <= node2.val) {
                iter.next = node1;
                node1 = node1.next;
            } else {
                iter.next = node2;
                node2 = node2.next;
            }
            iter = iter.next;
        }

        if (node1 != null) {
            iter.next = node1;
        }

        if (node2 != null) {
            iter.next = node2;
        }
        return dummy.next;
    }

    @Test
    public void testCase01() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(4);
        ListNode node3 = new ListNode(5);

        node1.next = node2;
        node2.next = node3;

        ListNode node4 = new ListNode(1);
        ListNode node5 = new ListNode(3);
        ListNode node6 = new ListNode(4);

        node4.next = node5;
        node5.next = node6;

        ListNode node7 = new ListNode(2);
        ListNode node8 = new ListNode(6);
        node7.next = node8;

        ListNode[] nodes = {node1, node4, node7, null};
        System.out.println(mergeKLists2(nodes));
    }

    @Test
    public void testCase02() {
        ListNode[] nodes = {null, null};
        System.out.println(mergeKLists2(nodes));
    }


}
