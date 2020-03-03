package leetcode;

import common.ListNode;
import org.junit.Test;


/**
 *反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 *
 * 说明:
 * 1 ≤ m ≤ n ≤ 链表长度。
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
 * 输出: 1->4->3->2->5->NULL
 *
 * 将序列 1 -> .. -> N,分成三个部分
 * 反转前：
 * 1 -> mainTail; subHead -> ... -> subTail; mainHead -> ... -> N
 * 反转后：
 * 1 -> mainTail; subTail -> ... -> subHead; mainHead -> ... -> N
 *
 */
public class Q92ReverseList {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || m == n) {
            return head;
        }

        ListNode mainTail = null;
        ListNode mainHead = null;
        ListNode subHead = null;
        ListNode subTail = null;

        ListNode iter = head;
        ListNode pre = null;

        int i = 1;
        while (iter != null && i <= n) {
            if (i < m) {
                mainTail = iter;
                iter = iter.next;
                i ++;
            } else {

                if (i == m) {
                    subHead = iter;
                }

                ListNode tmp;
                while (i <= n) {
                    tmp = iter.next;
                    iter.next = pre;
                    pre = iter;
                    iter = tmp;
                    subTail = pre;
                    i++;
                }
                mainHead = iter;
            }
        }

        if (mainTail == null && mainHead == null) {
            return subTail;
        } else if (mainTail == null && mainHead != null) {
            subHead.next = mainHead;
            return subTail;
        }
        subHead.next = mainHead;
        mainTail.next = subTail;

        return head;
    }

    /**
     * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
     * 输出: 1->4->3->2->5->NULL
     */
    @Test
    public void test01() {
        int m = 2;
        int n = 4;

        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        System.out.println(node1);

        ListNode res = reverseBetween(node1, m ,n);
        System.out.println(res);
    }

    @Test
    public void test02() {
        int m = 1;
        int n = 2;

        ListNode node1 = new ListNode(3);
        ListNode node2 = new ListNode(5);
        node1.next = node2;
        System.out.println(node1);
        ListNode res = reverseBetween(node1, m, n);
        System.out.println(res);
    }

    @Test
    public void test03() {
        int m = 1;
        int n = 2;

        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);

        node1.next = node2;
        node2.next = node3;

        System.out.println(node1);

        ListNode res = reverseBetween(node1, m ,n);
        System.out.println(res);
    }
}
