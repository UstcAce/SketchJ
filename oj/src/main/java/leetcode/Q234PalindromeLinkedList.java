package leetcode;

import common.ListNode;
import org.junit.Test;

public class Q234PalindromeLinkedList {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        int len = 0;
        ListNode iter = head;
        while (iter!=null) {
            len+=1;
            iter = iter.next;
        }

        for (int i=0; i<len/2; i++) {
            int i1=0;
            ListNode iter1 = head;
            while (i1<i) {
                iter1 = iter1.next;
                i1+=1;
            }

            int i2=0;
            ListNode iter2 = head;
            while (i2<len-i-1) {
                iter2 = iter2.next;
                i2+=1;
            }
            if (iter1.val != iter2.val) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void testCase01() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        node1.next = node2;
        System.out.println(isPalindrome(node1));
    }

    @Test
    public void testCase02() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(1);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        System.out.println(isPalindrome(node1));
    }
}
