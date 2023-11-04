package hw10.task1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumbers {

    private FileInputStream file;

    public static void main(String[] args) {
        PhoneNumbers phoneNumbers = new PhoneNumbers();
//        System.out.println(System.getProperty("user.dir"));
        phoneNumbers.openFile();
        phoneNumbers.printNumbers();
        phoneNumbers.closeFile();
    }

    public void printNumbers() {
        if (this.file == null) return;

        Scanner scanner = new Scanner(this.file);

        while (scanner.hasNextLine()) {
            String currentNum = scanner.nextLine();

            if (isNumberValid(currentNum)) {
                System.out.println(currentNum);
            }
        }

        scanner.close();
    }

    private boolean isNumberValid(String number) {
        Pattern pattern1 = Pattern.compile("\\([0-9]{3}\\) [0-9]{3}-[0-9]{4}", Pattern.CASE_INSENSITIVE);
        Pattern pattern2 = Pattern.compile("[0-9]{3}-[0-9]{3}-[0-9]{4}", Pattern.CASE_INSENSITIVE);

        Matcher matcher1 = pattern1.matcher(number);
        Matcher matcher2 = pattern2.matcher(number);

        return matcher2.find() || matcher1.find();
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