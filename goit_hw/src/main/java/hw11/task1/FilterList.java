package hw11.task1;

import java.util.List;

public class FilterList {

    public static void main(String[] args) {
        FilterList filterList = new FilterList();

        System.out.println(filterList.oddIndexesOnly(List.of("Ivan", "Vlad", "Oleh", "Denys", "David")));
    }


    public String oddIndexesOnly (List<?> list) {
        StringBuilder stringBuilder = new StringBuilder();

        list.stream()
                .filter(x ->  list.indexOf(x) % 2 != 1)
                .forEach(x -> {
                    var i = list.indexOf(x);
                    stringBuilder.append((i+1) + ". " + list.get(i)+ ", ");
                });

        return stringBuilder.toString().substring(0, stringBuilder.length() - 2);
    }
}
