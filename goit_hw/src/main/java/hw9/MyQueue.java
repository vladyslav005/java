package hw9;

public class MyQueue<T> {
    int length = 0;
    private Node first = null;
    private Node last = null;

    public void add(T value) {
        if (first == null) {
            first = new Node(null,  value);
            last = first;
        } else {
            Node toAdd = new Node(null, value);
            last.next = toAdd;
            last = toAdd;
        }
        length++;
    }
    public T poll() {
        Node tmp = first;
        first = first.next;
        return tmp.value;
    }

    public void clear () {
        first = null;
        last = null;
        System.gc();
    }

    public int size() {
        return length;
    }

    public T peek() {
        return first.value;
    }

    class Node {
        private Node next;
        private Node previous;
        private final T value;

        public Node(Node next, T value) {
            this.next = next;
            this.value = value;
        }
    }

    @Override
    public String toString() {
        String str = "MyQueue{[";
        Node curr = first;
        while (curr.next != null) {
            str += curr.value + ", ";
            curr = curr.next;
        }
        str += curr.value + "]}";

        return  str;
    }
}
