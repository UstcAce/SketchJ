package leetcode;


import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    private Map<Integer, Node> map;

    private DoubleLinkedList doubleLinkedList;

    private int capacity;
    public LRUCache(int capacity) {
        map = new HashMap<>();
        doubleLinkedList = new DoubleLinkedList();
        this.capacity = capacity;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            doubleLinkedList.removeNode(node);
            doubleLinkedList.addNodeOnTheHead(node);
            return node.val;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.val = value;
            doubleLinkedList.removeNode(node);
            doubleLinkedList.addNodeOnTheHead(node);
        } else {
            int size = doubleLinkedList.size;
            if (size >= capacity) {
                Node popNode = doubleLinkedList.removeNodeOnTheTail();
                map.remove(popNode.key);
            }
            Node newNode = new Node(key, value);
            map.put(key, newNode);
            doubleLinkedList.addNodeOnTheHead(newNode);
        }
    }


    class Node {
        public int key;

        public int val;

        public Node prev;

        public Node next;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    class DoubleLinkedList {
        // 虚头节点
        public Node head;

        // 虚尾节点
        public Node tail;

        public int size;

        public DoubleLinkedList() {
            this.size = 0;
            head = new Node(0, 0);
            tail = new Node(0, 0);
            // 虚头节点只有后继节点，没有前驱节点
            head.next = tail;
            // 虚尾节点只有前驱节点，没有后续节点
            tail.prev = head;
        }

        public void addNodeOnTheHead(Node node) {
            Node tmp = head.next;
            head.next = node;
            node.prev = head;

            tmp.prev = node;
            node.next = tmp;

            size += 1;
        }

        public Node removeNodeOnTheTail() {
            if (size == 0) {
                return null;
            }

            Node node = tail.prev;
            Node tmp = tail.prev.prev;
            tmp.next = tail;
            tail.prev = tmp;
            size -= 1;
            return node;
        }

        /**
         * 需要保证node在这个双向链表里面
         */
        public void removeNode(Node node) {
            Node pre = node.prev;
            Node next = node.next;
            pre.next = next;
            next.prev = pre;
            size -= 1;
        }
    }
}
