package hw11.task1;

import java.util.List;

public class FilterList {

    public static void main(String[] args) {
        FilterList filterList = new FilterList();

        System.out.println(filterList.oddIndexesOnly(List.of("Ivan", "Vlad", "Oleh", "Denys", "David")));
    }


    public String oddIndexesOnly (List<?> list) {
        StringBuilder stringBuilder = new StringBuilder();

        return list.stream()
                .filter(x ->  list.indexOf(x) % 2 != 1)
                .map(x -> {
                    return (list.indexOf(x)+1) + ". " + x;
                }).toList().toString();

    }
}
