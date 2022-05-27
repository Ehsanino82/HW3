package Sample;

import Sample.Controller.UserController;
import Sample.View.GamePageController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

public class App extends Application {
    public static Stage stage;
    public static Scene scene;
    public static AudioClip music;

    @Override
    public void start(Stage stage) throws IOException {
        App.stage = stage;
        FXMLLoader fxmlLoader1 = new FXMLLoader(App.class.getResource("fxml/WelcomePage.fxml"));
        Scene scene = new Scene(fxmlLoader1.load(), 900, 600);
        App.scene = scene;
        stage.setTitle("CupHead");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        playSound("Menu");
    }

    public static void main(String[] args) {
        UserController.readDataFromJson();
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                GamePageController.setTime(GamePageController.getTime() + 1);
                if (GamePageController.getPassedTime() != null)
                    GamePageController.getPassedTime().setText(App.formatTime(GamePageController.getTime()));
            }
        };
        timer.schedule(task, 1000, 1000);
        launch();
        UserController.writeDataToJson();
    }

    public static void changeScene(String name) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/" + name + ".fxml"));
            scene.setRoot(fxmlLoader.load());
        } catch (Exception exception) {
            System.out.println("wrong");
        }
    }

    public static void playSound(String name) {
        if (music != null) music.stop();
        URL url = App.class.getResource("sounds/" + name + ".wav");
        music = new AudioClip(url.toExternalForm());
        music.play();
    }

    public static void stopSound() {
        music.stop();
    }

    public static void clickSound() {
        Media media = new Media(App.class.getResource("sounds/click.wav").toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    public static String formatTime(int secondsCount) {
        int seconds = secondsCount % 60;
        secondsCount -= seconds;
        int minutesCount = secondsCount / 60;
        int minutes = minutesCount % 60;
        String output = "Time: ";
        if (minutes / 10 <= 1) output = output.concat("0");
        output = output.concat(String.valueOf(minutes));
        output = output.concat(":");
        if (seconds / 10 < 1) output = output.concat("0");
        output = output.concat(String.valueOf(seconds));
        return output;
    }
}