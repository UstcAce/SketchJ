package leetcode;

import common.ListNode;
import org.junit.Test;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * <p>
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * <p>
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 示例：
 * <p>
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 */
public class Q2AddTwoNum {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode iter = dummy;
        ListNode iter1 = l1;
        ListNode iter2 = l2;
        int flag = 0;
        while (iter1 != null || iter2 != null) {
            int val = (iter1 == null ? 0 : iter1.val) + (iter2 == null ? 0 : iter2.val) + flag;
            flag = val >= 10 ? 1 : 0;
            val = val % 10;
            iter.next = new ListNode(val);
            iter = iter.next;
            if (iter1 != null) {
                iter1 = iter1.next;
            }

            if (iter2 != null) {
                iter2 = iter2.next;
            }
        }
        if (flag == 1) {
            iter.next = new ListNode(1);
        }
        return dummy.next;
    }

    /**
     *  * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     *  * 输出：7 -> 0 -> 8
     *  * 原因：342 + 465 = 807
     */
    @Test
    public void testCase01() {
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(4);
        ListNode node3 = new ListNode(3);
        node1.next = node2;
        node2.next = node3;

        ListNode node4 = new ListNode(5);
        ListNode node5 = new ListNode(6);
        ListNode node6 = new ListNode(4);
        node4.next = node5;
        node5.next = node6;

        System.out.println(addTwoNumbers(node1, node4));
    }

    @Test
    public void testCase02() {
        ListNode node1 = new ListNode(9);
        ListNode node2 = new ListNode(9);

        System.out.println(addTwoNumbers(node1, node2));
    }
}
