package hw10.task2;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ProcessFile {

    private FileInputStream file;
    private ArrayList<User> userList = new ArrayList<>();

    public static void main(String[] args) {
        ProcessFile inst = new ProcessFile();
        inst.openFile();
        inst.readUsersFromFile();
        inst.closeFile();
        inst.makeJsonFile();
    }

    public void makeJsonFile() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter output;
        File myObj = new File("./src/main/resources/user.json");

        try {
            output = new FileWriter(myObj);
            output.write(gson.toJson(this.userList));
            output.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private void readUsersFromFile() {
        if (this.file == null) return;

        Scanner scanner = new Scanner(this.file);

        while (scanner.hasNextLine()) {
            String[] next = scanner.nextLine().split(" ");
            this.userList.add(new User(next[0], Integer.valueOf(next[1])));
        }

        scanner.close();
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
