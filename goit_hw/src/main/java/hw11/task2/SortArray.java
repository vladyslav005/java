package hw11.task2;

import java.util.List;

//@SuppressWarnings("ALL")
public class SortArray<T extends Comparable<T>> {


    public static void main(String[] args) {
        SortArray<String> sortArray = new SortArray<>();
        System.out.println(sortArray.sortNotIncreasing(List.of("Yana", "Anna", "Vlad", "Beta", "Zuzanna")));
    }

    public List<T> sortNotIncreasing (List<T> list) {
        return list.stream().sorted((a, b) -> b.compareTo(a) ).toList();
    }
}
