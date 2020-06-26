package leetcode;

import common.ListNode;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 编写代码，移除未排序链表中的重复节点。保留最开始出现的节点。
 *
 * 示例1:
 *
 *  输入：[1, 2, 3, 3, 2, 1]
 *  输出：[1, 2, 3]
 * 示例2:
 *
 *  输入：[1, 1, 1, 1, 2]
 *  输出：[1, 2]
 * 提示：
 *
 * 链表长度在[0, 20000]范围内。
 * 链表元素在[0, 20000]范围内。
 *
 * 进阶：
 * 如果不得使用临时缓冲区(即要求时间复杂度O(1))，该怎么解决？
 */
public class Q02_01RemoveDuplicateNodes {
    /**
     * 一次遍历，用set或者map存储不出现过的元素
     *
     * 时间复杂度O(n)
     * 空间复杂度O(n)
     */
    public ListNode removeDuplicateNodes(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        Map<Integer, Integer> map = new HashMap<>();
        ListNode iter = head;
        ListNode pre = null;
        while (iter != null) {
            if (!map.containsKey(iter.val)) {
                map.put(iter.val, 1);
                pre = iter;
            } else {
                pre.next = iter.next;
            }
            iter = iter.next;
        }
        return head;
    }

    /**
     * 1. 先对链表进行归并排序
     * 2. 然后遍历排序好的链表去重
     */
    public ListNode removeDuplicateNodes2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = mergeSort(head);
        ListNode iter = newHead;
        ListNode pre = null;
        while (iter != null) {
            if (pre != null && iter.val == pre.val) {
                ListNode next = iter.next;
                iter.next = null;
                pre.next = next;
                iter = next;
            } else {
                pre = iter;
                iter = iter.next;
            }
        }
        return newHead;
    }

    /**
     * 对链表进行归并排序
     */
    private ListNode mergeSort(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // step 1: 快慢指针找链表中间元素
        ListNode slow = head;
        ListNode fast = head.next.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // step 2: 从中间将链表分成两个链表
        ListNode middle = slow.next;
        slow.next = null;

        ListNode left = mergeSort(head);
        ListNode right = mergeSort(middle);
        return mergeTwoSortedListNode(left, right);
    }

    private ListNode mergeTwoSortedListNode(ListNode first, ListNode second) {
        ListNode dummy = new ListNode(-1);
        ListNode iter = dummy;
        while (first != null || second != null) {
            if (first != null && second != null) {
                if (first.val <= second.val) {
                    iter.next = first;
                    first = first.next;
                } else {
                    iter.next = second;
                    second = second.next;
                }
            } else if (first != null) {
                iter.next = first;
                first = first.next;
            } else {
                iter.next = second;
                second = second.next;
            }
            iter = iter.next;
        }
        return dummy.next;
    }

    @Test
    public void testCase01() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(3);
        ListNode node5 = new ListNode(2);
        ListNode node6 = new ListNode(1);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;

        System.out.println(removeDuplicateNodes2(node1));
    }
}
