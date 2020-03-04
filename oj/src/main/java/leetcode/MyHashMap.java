package leetcode;

class MyHashMap {

    private int[] elements;

    private boolean[] flag;

    /** Initialize your data structure here. */
    public MyHashMap() {
        int capacity = 1000000;
        elements = new int[capacity];
        flag = new boolean[capacity];
    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        flag[key] = true;
        elements[key] = value;
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        if (flag[key]) {
            return elements[key];
        } else {
            return -1;
        }
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        flag[key] = false;
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */
