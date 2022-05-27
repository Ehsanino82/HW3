package Sample.Controller;

import Sample.Model.User;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class UserController {
    private static ArrayList<User> users;
    private static User loggedInUser;

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User loggedInUser) {
        UserController.loggedInUser = loggedInUser;
    }

    public static void addUser(String username, String password) {
        User user = new User(username, password);
        if (users == null) users = new ArrayList<>();
        users.add(user);
    }

    public static void removeUser() {
        users.remove(loggedInUser);
    }

    public static boolean isUsernameUnique(String username) {
        if (users != null) {
            for (User user : users) {
                if (user.getUsername().equals(username)) return false;
            }
        }
        return true;
    }

    public static void changeUsername(String username) {
        loggedInUser.setUsername(username);
    }

    public static void changePassword(String password) {
        loggedInUser.setPassword(password);
    }

    public static boolean loginUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                loggedInUser = user;
                return true;
            }
        }
        return false;
    }

    public static void logoutUser() {
        loggedInUser = null;
    }

    public static ArrayList<User> getBestUsers() {
        ArrayList<User> sorted = new ArrayList<>(users);
        if (loggedInUser.getUsername().equals("guest")) sorted.add(loggedInUser);
        Comparator<User> comparator = Comparator.comparing(User::getScore).reversed().thenComparing(User::getFinishedTime);
        sorted = (ArrayList<User>) sorted.stream().sorted(comparator).collect(Collectors.toList());
        return sorted;
    }

    public static void readDataFromJson() {
        try {
            String json = new String(Files.readAllBytes(Paths.get("./src/main/resources/Sample/json.json")));
            users = new Gson().fromJson(json, new TypeToken<List<User>>() {
            }.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeDataToJson() {
        try {
            FileWriter fileWriter = new FileWriter("./src/main/resources/Sample/json.json");
            fileWriter.write(new Gson().toJson(users));
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
