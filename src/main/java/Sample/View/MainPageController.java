package Sample.View;

import Sample.Controller.UserController;
import Sample.App;
import Sample.Transition.ButtonTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class MainPageController {
    @FXML
    private Button newGame;
    @FXML
    private Button scoreboard;
    @FXML
    private ImageView mute;
    @FXML
    private ImageView unmute;
    @FXML
    private Button profile;

    public void handleSoundIcon(MouseEvent mouseEvent) {
        if (!App.music.isPlaying()) {
            mute.setVisible(false);
            unmute.setVisible(true);
            App.stopSound();
        }
    }

    public void muteSound(MouseEvent mouseEvent) {
        mute.setVisible(false);
        unmute.setVisible(true);
        App.stopSound();
    }

    public void unmuteSound(MouseEvent mouseEvent) {
        unmute.setVisible(false);
        mute.setVisible(true);
        App.playSound("Menu");
    }

    public void exitGame(MouseEvent mouseEvent) {
        UserController.writeDataToJson();
        System.exit(0);
    }

    public void profile(MouseEvent mouseEvent) {
        App.clickSound();
        ButtonTransition.fadeTransition(profile, "ProfilePage");
    }

    public void scoreboard(MouseEvent mouseEvent) {
        App.clickSound();
        ButtonTransition.fadeTransition(scoreboard, "ScoreboardPage");
    }

    public void newGame(MouseEvent mouseEvent) {
        App.clickSound();
        ButtonTransition.fadeTransition(newGame, "GamePage");
        if (App.music.isPlaying()) App.playSound("Game");
    }
}