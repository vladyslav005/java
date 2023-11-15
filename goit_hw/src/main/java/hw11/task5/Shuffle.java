package hw11.task5;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Shuffle {

    public static void main(String[] args) {
        Shuffle.zip(Stream.of(1 ,2 ,3,4,5,6), Stream.of(7, 8, 9, 10)).forEach(
                x -> System.out.print(x + " ")
        );
    }

    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
        List<T> list1;
        List<T> list2 = new ArrayList<>();

        list1 = Stream.concat(first, second).toList();
        list1.parallelStream().forEach(x -> list2.add(x));

        return list2.stream();
    }
}
