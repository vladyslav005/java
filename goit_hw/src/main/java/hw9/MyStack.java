package hw9;

public class MyStack<T>{
    int length = 0;
    private Node first = null;
    private Node last = null;

    public void push(T value) {
        if (first == null) {
            first = new Node(null, null, value);
            last = first;
        } else {
            Node toAdd = new Node(null, last, value);
            last.next = toAdd;
            last = toAdd;
        }
        length++;
    }
    public void remove(int index) {
        Node curr = first;
        for  (int i = 0; i != index; i++ ) {
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

    public T pop() {
        Node tmp = last;
        last = tmp.previous;
        last.next = null;
        return tmp.value;
    }

    public T peek() {
        return first.value;
    }

    class Node {
        private Node next;
        private Node previous;
        private final T value;

        public Node(Node next, Node previous, T value) {
            this.next = next;
            this.previous = previous;
            this.value = value;
        }
    }

    @Override
    public String toString() {
        String str = "MyStack{[";
        Node curr = first;
        while (curr.next != null) {
            str += curr.value + ", ";
            curr = curr.next;
        }

        str += curr.value + "]}";

        return  str;
    }
}
