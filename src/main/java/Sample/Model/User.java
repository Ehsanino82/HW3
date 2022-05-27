package Sample.Model;

import Sample.App;

import java.util.Random;

public class User {
    private String username;
    private String password;
    private String iconAddress;
    private int score;
    private int finishedTime;

    public User(String username, String password) {
        Random random = new Random();
        this.username = username;
        this.password = password;
        this.iconAddress = App.class.getResource("pictures/Icons/" + random.nextInt(5) + ".png").toExternalForm();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getScore() {
        return score;
    }

    public int getFinishedTime() {
        return finishedTime;
    }

    public String getIconAddress() {
        return iconAddress;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setFinishedTime(int finishedTime) {
        this.finishedTime = finishedTime;
    }
}