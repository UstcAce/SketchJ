package leetcode;

import common.ListNode;
import org.junit.Test;

public class Q160IntersectionOfTwoList {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode iter1 = headA;
        ListNode iter2;
        while (iter1 != null) {
            iter2 = headB;
            while (iter2 != null) {
                if (iter1 == iter2) {
                    return iter1;
                } else {
                    iter2 = iter2.next;
                }
            }
            iter1 = iter1.next;
        }

        return null;
    }

    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        ListNode iter1 = headA;
        ListNode iter2 = headB;
        int len1 = 0;
        int len2 = 0;
        while (iter1 != null || iter2 != null) {
            if (iter1 != null) {
                len1 += 1;
                iter1 = iter1.next;
            }

            if (iter2 != null) {
                len2 += 1;
                iter2 = iter2.next;
            }
        }

        ListNode large = len1 >= len2 ? headA : headB;
        ListNode small = len1 < len2 ? headA : headB;

        for (int i = 0; i < Math.abs(len1 - len2); i++) {
            large = large.next;
        }

        for (int i = 0; i < Math.max(len1, len2); i++) {
            if (large == small) {
                return large;
            }
            large = large.next;
            small = small.next;
        }
        return null;
    }

    @Test
    public void testCase01() {
        ListNode node1 = new ListNode(4);
        ListNode node2 = new ListNode(1);
        ListNode node3 = new ListNode(5);
        ListNode node4 = new ListNode(0);
        ListNode node5 = new ListNode(1);
        ListNode node6 = new ListNode(8);
        ListNode node7 = new ListNode(4);
        ListNode node8 = new ListNode(5);

        node1.next = node2;

        node3.next = node4;
        node4.next = node5;

        node2.next = node6;
        node5.next = node6;

        node6.next = node7;
        node7.next = node8;

        System.out.println(getIntersectionNode2(node1, node3));
    }
}
