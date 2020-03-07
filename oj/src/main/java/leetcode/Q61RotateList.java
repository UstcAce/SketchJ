package leetcode;

import common.ListNode;
import org.junit.Test;

/**
 * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
 *
 * 示例 1:
 *
 * 输入: 1->2->3->4->5->NULL, k = 2
 * 输出: 4->5->1->2->3->NULL
 * 解释:
 * 向右旋转 1 步: 5->1->2->3->4->NULL
 * 向右旋转 2 步: 4->5->1->2->3->NULL
 *
 */
public class Q61RotateList {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) return head;

        ListNode iter = head;
        ListNode tail = head;
        int len = 0;
        while (iter != null) {
            tail = iter;
            len += 1;
            iter = iter.next;
        }

        int rotateNum = len - k % len;

        if (rotateNum == len) {
            return head;
        }

        int i = 1;
        iter = head;
        while (i<rotateNum) {
            iter = iter.next;
            i++;
        }
        ListNode newHead = iter.next;
        iter.next = null;
        tail.next = head;

        return newHead;
    }

    @Test
    public void testCase01() {
        ListNode head = new ListNode(1);
        System.out.println(rotateRight(head, 1));
    }

    @Test
    public void testCase02() {
        ListNode head = null;
        System.out.println(rotateRight(head, 1));
    }

    @Test
    public void testCase03() {
        ListNode head = new ListNode(1);
        System.out.println(rotateRight(head, 0));
    }

    @Test
    public void testCase04() {
        ListNode head = new ListNode(1);
        ListNode head2 = new ListNode(2);
        head.next = head2;
        System.out.println(rotateRight(head, 0));
    }
}
