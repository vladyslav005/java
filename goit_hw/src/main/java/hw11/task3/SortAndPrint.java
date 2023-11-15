package hw11.task3;

import java.util.*;
import java.util.stream.Collectors;

public class SortAndPrint {


    public static void main(String[] args) {

        SortAndPrint sortAndPrint = new SortAndPrint();
        sortAndPrint.sortAndPrintList(List.of("1, 2, 0", "4, 5"));
    }



    public void sortAndPrintList (List<String> list) {
        System.out.println(list.stream()
                .map(x -> x.split(", "))
                .flatMap(x -> Arrays.stream(x))
                .sorted()
                .toList());
    }
}
