package leetcode;

import common.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Node copyRandomList2(Node head) {
        if (head == null) {
            return null;
        }

        Map<Node, Node> map = new HashMap<>();
        Node iter = head;
        Node newNode = new Node(head.val);
        map.put(iter, newNode);
        while (iter != null) {
            Node random = getClonedNode(iter.random, map);
            Node next = getClonedNode(iter.next, map);

            newNode.next = next;
            newNode.random = random;

            iter = iter.next;
            newNode = next;
        }
        return map.get(head);
    }

    private Node getClonedNode(Node node, Map<Node, Node> map) {
        if (node == null) {
            return null;
        }

        if (map.containsKey(node)) {
            return map.get(node);
        } else {
            Node newNode = new Node(node.val);
            map.put(node, newNode);
            return newNode;
        }
    }
}
