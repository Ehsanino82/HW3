package Sample.View;

import Sample.App;
import Sample.Transition.ButtonTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class EndGamePageController {
    private static boolean win;
    @FXML
    private Rectangle progress;
    @FXML
    private Rectangle progressBar;
    @FXML
    private Button newGame;
    @FXML
    private Button backToMenu;
    @FXML
    private Label score;
    @FXML
    private Label time;
    @FXML
    private Rectangle background;
    @FXML
    private Rectangle state;

    public static void setIsWin(boolean isWin) {
        EndGamePageController.win = isWin;
    }

    public void init(MouseEvent mouseEvent) {
        background.setFill(new ImagePattern(new Image(App.class.getResource("pictures/Backgrounds/image.png").toExternalForm())));
        if (win) {
            state.setFill(new ImagePattern(new Image(App.class.getResource("pictures/victory.png").toExternalForm())));
        } else {
            state.setLayoutY(state.getLayoutY() - 100);
            state.setFill(new ImagePattern(new Image(App.class.getResource("pictures/defeat.png").toExternalForm())));
            progress.setVisible(true);
            progressBar.setWidth(200 - Integer.parseInt(GamePageController.getHealth().getText()) * 2);
            progressBar.setVisible(true);

        }
        score.setText(String.valueOf(GamePageController.getGameScore()));
        time.setText(App.formatTime(GamePageController.getTime()));
    }

    public void backToMenu(MouseEvent mouseEvent) {
        App.clickSound();
        ButtonTransition.fadeTransition(backToMenu, "MainPage");
        if (App.music.isPlaying()) App.playSound("Menu");
    }

    public void newGame(MouseEvent mouseEvent) {
        App.clickSound();
        ButtonTransition.fadeTransition(newGame, "GamePage");
        if (App.music.isPlaying()) App.playSound("Game");
    }
}