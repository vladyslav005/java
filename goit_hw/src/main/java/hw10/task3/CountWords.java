package hw10.task3;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class CountWords {


    private FileInputStream file;
    private HashMap<String, Integer> wordsMap = new HashMap<>();

    public static void main(String[] args) {
        CountWords countWords = new CountWords();
        countWords.openFile();
        countWords.processFile();
        countWords.closeFile();
        countWords.printWords();
    }

    public void printWords() {
        while (!wordsMap.isEmpty()) {
            String biggestKey = getBiggest();
            System.out.println(biggestKey + " " + wordsMap.get(biggestKey) );
            wordsMap.remove(biggestKey);
        }
    }

    public String getBiggest() {
        int biggestValue = 0;
        String biggestKey = null;

        for (String key :  wordsMap.keySet()) {

            if (wordsMap.get(key) > biggestValue) {
                biggestValue = wordsMap.get(key);
                biggestKey = key;
            }
        }

        return biggestKey;
    }

    public void processFile() {
        Scanner scanner = new Scanner(this.file);

        while (scanner.hasNext()) {
            String word = scanner.next();
            if (wordsMap.containsKey(word)) {
                wordsMap.replace(word, wordsMap.get(word) + 1);
            } else {
                wordsMap.put(word, 1);
            }
        }
    }

    public void openFile() {
        try {
            this.file = new FileInputStream("./src/main/resources/file.txt");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void closeFile() {
        try {
            this.file.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
