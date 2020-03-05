package leetcode;

import common.ListNode;

public class Q142DetectCycle {
    public ListNode detectCycle(ListNode head) {
        ListNode inNode = getInCycleNode(head);
        if (inNode == null) {
            return null;
        }
        ListNode inIter = inNode;
        while (head != inIter) {
            inIter = inIter.next;
            if (inNode == inIter) {
                head = head.next;
            }
        }
        return head;
    }

    private ListNode getInCycleNode(ListNode head) {
        ListNode step1Iter = head;
        ListNode step2Iter = head;
        while (step1Iter != null) {
            step1Iter = step1Iter.next;
            if (step1Iter == null) {
                return null;
            }
            if (step2Iter.next == null || step2Iter.next.next == null) {
                return null;
            }
            step2Iter = step2Iter.next.next;
            if (step1Iter == step2Iter) {
                return step1Iter;
            }
        }
        return null;
    }
}
