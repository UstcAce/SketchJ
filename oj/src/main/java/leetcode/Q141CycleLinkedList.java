package leetcode;

import common.ListNode;

import java.util.HashSet;
import java.util.Set;

public class Q141CycleLinkedList {
    /**
     * 方法1： Set
     */
    public boolean hasCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        ListNode iter = head;
        while (iter != null) {
            if (set.contains(iter)) {
                return true;
            }
            set.add(iter);
            iter = iter.next;
        }
        return false;
    }

    /**
     * 方法2：快慢指针
     */
    public boolean hasCycle2(ListNode head) {
        ListNode step1Iter = head;
        ListNode step2Iter = head;
        while (step1Iter != null && step2Iter != null) {
            step1Iter = step1Iter.next;
            if (step1Iter == null) {
                return false;
            }
            if (step2Iter.next == null || step2Iter.next.next == null) {
                return false;
            }
            step2Iter = step2Iter.next.next;
            if (step1Iter == step2Iter) {
                return true;
            }
        }
        return false;
    }

}
