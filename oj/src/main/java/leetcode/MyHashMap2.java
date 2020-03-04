package leetcode;

public class MyHashMap2 {
    private Node[] elements;
    private int capacity = 10000;


    /** Initialize your data structure here. */
    public MyHashMap2() {
        elements = new Node[capacity];
    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        int idx = key % capacity;

        Node node = elements[idx];
        if (node == null) {
            elements[idx] = new Node(key, value);
        } else {
            Node iter = node;
            while (iter != null) {
                if (iter.key == key) {
                    iter.val = value;
                    return;
                }
                iter = iter.next;
            }
            Node newNode = new Node(key, value);
            newNode.next = node;
            elements[idx] = newNode;
        }
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int idx = key % capacity;

        Node node = elements[idx];
        if (node == null) {
            return -1;
        } else {
            Node iter = node;
            while (iter != null) {
                if (iter.key == key) {
                    return iter.val;
                }
                iter = iter.next;
            }
            return -1;
        }
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int idx = key % capacity;

        Node node = elements[idx];
        if (node != null) {
            if (node.key == key) {
                elements[idx] = node.next;
            } else {
                Node pre = node;
                Node iter = node.next;
                while (iter != null) {
                    if (iter.key == key) {
                        pre.next = iter.next;
                        return;
                    } else {
                        pre = iter;
                        iter = iter.next;
                    }
                }
            }
        }
    }

    private static class Node{
        public int key;
        public int val;
        public Node next;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
}
