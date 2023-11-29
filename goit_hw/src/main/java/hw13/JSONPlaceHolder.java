package hw13;

import hw13.holders.*;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;

import java.util.Arrays;
import java.util.List;


import com.google.gson.*;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class JSONPlaceHolder {

    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
        JSONPlaceHolder communication = new JSONPlaceHolder();

        Geo geo = new Geo("0.0", "1.11");
        Address address = new Address("Bernolakova", "10", "Bratislava", "333", geo);
        Company company = new Company("Fresh breath", "help me", "idk");
        User userToAdd = new User(
                "Vladyslv", "vlado",
                "vf.gm.com", address, "+777xxxxx",
                "vlad.com", company);
        communication.addUser(userToAdd);

//        communication.getAllUsers();



        User[] users = communication.getAllUsers();
        users[0].setName("Oleh");
        communication.updateUser(users[0]);
        communication.deleteUser(users[0]);
        communication.getUserByName("Bret");
        communication.getUserById(1);

        communication.getCommentsToLastPost(1);
        communication.getTasks(3);
    }


    void addUser(User user) throws IOException {

        Gson gson = new GsonBuilder().setLenient().create();
        String userJSON = gson.toJson(user);

        URL url = new URL("https://jsonplaceholder.typicode.com/users");

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        OutputStream os = connection.getOutputStream();
        os.write(userJSON.getBytes());
        os.flush();
        os.close();


        int responseCode = connection.getResponseCode();
        System.out.println("Create user response code: " + responseCode);
        System.out.println("Message: " +  connection.getResponseMessage());


    }


    User[] getAllUsers() throws IOException {
        Connection connection = Jsoup.connect("https://jsonplaceholder.typicode.com/users")
                .ignoreContentType(true);

        Document document = connection.get();
        Gson gsonBuilder = new GsonBuilder().setPrettyPrinting().create();
        User[] users = gsonBuilder.fromJson(document.body().text(), User[].class);
//        Arrays.stream(users).forEach(System.out::println);

        return users;
    }

    void updateUser(User changedUser) throws IOException, URISyntaxException, InterruptedException {

        Gson gson = new GsonBuilder().setLenient().create();
        String userJSON = gson.toJson(changedUser);

        URL url = new URL("https://jsonplaceholder.typicode.com/users" + "/" + changedUser.getId() );

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestMethod("PUT");
        connection.setDoOutput(true);
        OutputStream os = connection.getOutputStream();
        os.write(userJSON.getBytes());
        os.flush();
        os.close();

        int responseCode = connection.getResponseCode();
        System.out.println("Update User response code: " + responseCode);
        System.out.println("Message: " +  connection.getResponseMessage());

    }

    User getUserById(int id) throws IOException {
        Connection connection = Jsoup.connect("https://jsonplaceholder.typicode.com/users" + "/" + id)
                .ignoreContentType(true);

        Document document = connection.get();
        Gson gsonBuilder = new GsonBuilder().setPrettyPrinting().create();
        User user = gsonBuilder.fromJson(document.body().text(), User.class);

//        System.out.println(user);
        return user;

    }

    User getUserByName(String name) throws IOException {
        Connection connection = Jsoup.connect("https://jsonplaceholder.typicode.com/users" + "?username=" + name)
                .ignoreContentType(true);

        Document document = connection.get();
        Gson gsonBuilder = new GsonBuilder().setPrettyPrinting().create();

        User[] users = gsonBuilder.fromJson(document.body().text(), User[].class);

        return users[0];

    }
    void deleteUser(User user) throws IOException {
        URL url = new URL("https://jsonplaceholder.typicode.com/users" + "/" + user.getId() );

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestMethod("PUT");
        int responseCode = connection.getResponseCode();
        System.out.println("Delete User response code: " + responseCode);
        System.out.println("Message: " +  connection.getResponseMessage());
    }

    Comment[] getCommentsToLastPost(int id) throws IOException {
        Connection connection = Jsoup.connect("https://jsonplaceholder.typicode.com/users" + "/" + id + "/posts")
                .ignoreContentType(true);
        Document doc = connection.get();
        Gson gson =  new GsonBuilder().setPrettyPrinting().create();

        int lastPostId;

        List<Post> posts = Arrays.stream(gson.fromJson(doc.body().text(), Post[].class)).toList();

        posts = posts.stream()
                .sorted((a, b) ->  b.getId() - a.getId())
                .toList();
        lastPostId = posts.get(0).getId();


        connection = Jsoup.connect(
                "https://jsonplaceholder.typicode.com/" + "posts/" + lastPostId + "/comments")
                .ignoreContentType(true);
        doc = connection.get();

        Comment[] comments = gson.fromJson(doc.body().text(), Comment[].class);
        try (FileOutputStream out = new FileOutputStream("./src/main/resources/" +"user-" + id + "-post-" + lastPostId + "-comments.json")) {
            out.write(gson.toJson(comments).getBytes());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return comments;
    }


    void getTasks(int id) throws IOException {
        Connection connection = Jsoup.connect("https://jsonplaceholder.typicode.com/users/" + id + "/todos")
                .ignoreContentType(true);
        Document doc = connection.get();

        Gson gson =  new GsonBuilder().setPrettyPrinting().create();

        Todo[] tasks =  gson.fromJson(doc.body().text(), Todo[].class);

        Arrays.stream(tasks)
                .filter(t -> !t.isCompleted())
                .forEach(System.out::println);

    }
}
