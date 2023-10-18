package hw9;
import java.util.Arrays;

@SuppressWarnings("unchecked")
public class MyArrayList<T> {

    private int length = 0;
    private T[] mainArray = (T[]) new Object[1];

    public void add(T value) {

        length++;
        T[] tmpArray = mainArray;
        mainArray = (T[]) new Object[length];

        for (int i = 0; i < length - 1; i++) {
            mainArray[i] = tmpArray[i];
        }

        mainArray[length-1] = value;
    }

    public void remove(int index) {
        length--;
        T[] tmpArray = mainArray;
        mainArray = (T[]) new Object[length];

        for (int i = 0, j = 0; i < length; i++, j++) {
            if (j == index) j++;
            mainArray[i] = tmpArray[j];
        }
    }

    public void clear() {
        length = 0;
        mainArray = (T[]) new Object[1];
    }

    public int size() {
        return length;
    }

    public T get(int index) {
        return mainArray[index];
    }

    @Override
    public String toString() {
        return "MyArrayList{" +
                "mainArray=" + Arrays.toString(mainArray) +
                '}';
    }
}