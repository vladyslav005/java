package hw9.tests;

import hw9.MyArrayList;
import hw9.MyLinkedList;
import hw9.MyQueue;
import hw9.MyStack;

public class CollectionsTest {

    public static void main(String[] args) {

        MyHashMap<String, Integer> arr = new MyHashMap<>();
        arr.put("a", 0);
        arr.put("b", 1);
        arr.put("c", 2);
        arr.put("d", 3);
        arr.put("e", 4);
        arr.put("f", 5);



        System.out.println(arr);




        System.out.println(arr.get("c"));




    }
}
