package hw9.tests;

import java.util.HashSet;

public class MyHashMap<K, T> {

    private Node first = null;
    private Node last = null;
    int length = 0;

    private HashSet<K> keys = new HashSet<>();



    public void put(K key, T value) {
        if (first == null) {
            first = new Node(null, null, key, value);
            last = first;
        } else if ( !keys.contains(key) ) {
            Node toAdd = new Node(null, last, key, value);
            last.next = toAdd;
            last = toAdd;
        } else {
            System.out.println("That key is already used");
            return;
        }

        keys.add(key);
        length++;
    }


    public void remove(K key) {
        Node curr = first;
        while (!curr.key.equals(key)) {
            curr = curr.next;
        }

        if (curr == first) {
            first = first.next;
            first.previous = null;
        } else if (curr == last) {
            last = last.previous;
            last.next = null;
        } else {
            curr.previous.next = curr.next;
            curr.next.previous = curr.previous;
        }

        length--;
    }

    public void clear () {
        first = null;
        last = null;
        System.gc();
    }

    public int size() {
        return length;
    }

    public T get(K key) {
        Node curr = first;
        while (!curr.key.equals(key)) {
            curr = curr.next;
        }

        return curr.value;
    }

    class Node {
        private Node next;
        private Node previous;
        private K key;
        private final T value;

        public Node(Node next, Node previous, K key, T value) {
            this.next = next;
            this.previous = previous;
            this.key = key;
            this.value = value;
        }
    }

    @Override
    public String toString() {
        String str = "MyQueue{[";
        Node curr = first;
        while (curr.next != null) {
            str += curr.key + ":" + curr.value + ", ";
            curr = curr.next;
        }
        str += curr.key  + ":" + curr.value + "]}";

        return  str;
    }

}
