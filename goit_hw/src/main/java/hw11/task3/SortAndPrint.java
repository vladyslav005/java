package hw11.task3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class SortAndPrint {


    public static void main(String[] args) {

        SortAndPrint sortAndPrint = new SortAndPrint();
        sortAndPrint.sortAndPrintList(List.of("1, 2, 0", "4, 5"));
    }



    public void sortAndPrintList (List<String> list_) {
        StringBuilder stringBuilder  = new StringBuilder();
        List<String> list = new ArrayList<>();

        list_.stream().forEach(x -> {
            x = x.replace(",", "");
            list.addAll(Arrays.asList(x.split(" ")));
        });

        list.stream().sorted().forEach(x -> stringBuilder.append(x).append(", "));
        System.out.println(stringBuilder.toString().substring(0, stringBuilder.length() -2));
    }
}
