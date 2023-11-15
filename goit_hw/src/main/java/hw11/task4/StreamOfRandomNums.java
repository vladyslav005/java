package hw11.task4;

import java.util.stream.Stream;

public class StreamOfRandomNums {

    public static void main(String[] args) {

        StreamOfRandomNums.getStream(25214903917l ,11 ,2^48).limit(20).forEach(System.out::println);
    }


    public static Stream<Long> getStream(long a, long c, long m) {
        Stream<Long> result = Stream.iterate(11l, x -> (a * x + c) % m);

        return result;
    }
}
