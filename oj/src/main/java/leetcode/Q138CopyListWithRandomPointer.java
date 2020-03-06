package leetcode;

import common.Node;

import java.util.ArrayList;
import java.util.List;

public class Q138CopyListWithRandomPointer {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        List<Node> oriNodes = new ArrayList<>();
        List<Node> randomNodes = new ArrayList<>();
        List<Node> newNodes = new ArrayList<>();

        Node iter = head;
        while (iter != null) {
            oriNodes.add(iter);
            randomNodes.add(iter.random);

            Node node = new Node(iter.val);
            newNodes.add(node);

            iter = iter.next;
        }

        for (int i=0; i<oriNodes.size(); i++) {
            if (i < oriNodes.size()-1) {
                newNodes.get(i).next = newNodes.get(i+1);
            }
            Node ran = randomNodes.get(i);
            if (ran != null) {
                int idx = oriNodes.indexOf(ran);
                newNodes.get(i).random = newNodes.get(idx);
            }
        }

        return newNodes.iterator().next();
    }
}
