package Sample.View;

import Sample.App;
import Sample.Controller.UserController;
import Sample.View.Component.Boss;
import Sample.View.Component.CupHead;
import Sample.View.Component.MiniBoss;
import Sample.Transition.BackgroundTransition;
import Sample.Transition.BossMovement;
import Sample.Transition.CupHeadMovement;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class GamePageController {
    private static boolean gameStarted = false;
    private static int time;
    private static int counter;
    private static int gameScore;
    private static Rectangle progressBar;
    private static Text health;
    private static Text score;
    private static Text hp;
    private static Text passedTime;
    @FXML
    private AnchorPane pane;

    public static int getTime() {
        return time;
    }

    public static int getGameScore() {
        return gameScore;
    }

    public static Rectangle getProgressBar() {
        return progressBar;
    }

    public static Text getHealth() {
        return health;
    }

    public static Text getScore() {
        return score;
    }

    public static Text getPassedTime() {
        return passedTime;
    }

    public static Text getHp() {
        return hp;
    }

    public static void setTime(int time) {
        GamePageController.time = time;
    }

    public static void setGameScore(int gameScore) {
        GamePageController.gameScore = gameScore;
    }

    public void startGame(MouseEvent mouseEvent) {
        if (gameStarted) return;
        gameStarted = true;
        time = 0;
        counter = 1;
        gameScore = 0;
        initializeBackground();
        CupHead cupHead = createCupHead(pane);
        pane.getChildren().add(2, cupHead);
        pane.getChildren().get(2).requestFocus();
        initializeGameDetails();
        Boss boss = createBoss();
        pane.getChildren().add(boss);
    }

    private void initializeBackground() {
        Rectangle background1 = new Rectangle(0, 0, 900, 600);
        Rectangle background2 = new Rectangle(900, 0, 900, 600);
        background1.setFill(new ImagePattern(new Image(App.class.getResource("pictures/Backgrounds/GameBackground.png").toExternalForm())));
        background2.setFill(new ImagePattern(new Image(App.class.getResource("pictures/Backgrounds/GameBackground.png").toExternalForm())));
        pane.getChildren().add(0, background1);
        pane.getChildren().add(1, background2);
        BackgroundTransition transition = new BackgroundTransition(background1, background2);
        transition.play();
    }

    private CupHead createCupHead(AnchorPane pane) {
        CupHead cupHead = CupHead.getInstance();
        cupHead.setCupHeadMovement(new CupHeadMovement(cupHead, pane));
        cupHead.getCupHeadMovement().play();
        cupHead.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                String keyName = keyEvent.getCode().getName();
                if (!gameStarted) gameStarted = true;
                if (counter % 50 == 0) {
                    createMiniBoss(pane, (counter % 100) * 4 + 200);
                    Boss.getInstance(pane).bossShot();
                }
                switch (keyName) {
                    case "A":
                        cupHead.moveLeft();
                        break;
                    case "D":
                        cupHead.moveRight();
                        break;
                    case "S":
                        cupHead.moveUp();
                        break;
                    case "W":
                        cupHead.moveDown();
                        break;
                    case "Space":
                        cupHead.shot(pane);
                        break;
                }
                counter++;
            }
        });
        return cupHead;
    }

    private void initializeGameDetails() {
        progressBar = new Rectangle(700, 560, 200, 40);
        progressBar.setFill(Color.GREEN);
        Rectangle rectangle = new Rectangle(700, 560, 200, 40);
        rectangle.setFill(Color.WHITE);
        health = new Text(640, 590, "100");
        health.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 24));
        score = new Text(40, 590, "Score: 0");
        score.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 24));
        hp = new Text(210, 590, "HP: 4");
        hp.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 24));
        passedTime = new Text(300, 590, "Time: 00:00");
        passedTime.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 24));
        pane.getChildren().add(rectangle);
        pane.getChildren().add(progressBar);
        pane.getChildren().add(health);
        pane.getChildren().add(score);
        pane.getChildren().add(hp);
        pane.getChildren().add(passedTime);
    }

    private Boss createBoss() {
        Boss boss = Boss.getInstance(pane);
        Boss.setPane(pane);
        boss.setBossMovement(new BossMovement(boss));
        boss.getBossMovement().play();
        return boss;
    }

    private void createMiniBoss(AnchorPane pane, int y) {
        for (int i = 0; i < 4; i++) {
            MiniBoss miniBoss = new MiniBoss(pane, 900 + i * 100, y);
            pane.getChildren().add(miniBoss);
        }
    }

    public static void endGame(AnchorPane pane) {
        UserController.getLoggedInUser().setFinishedTime(time);
        gameStarted = false;
        Boss.getInstance(pane).setHealth(100);
        Boss.getInstance(pane).setX(630);
        Boss.getInstance(pane).setY(0);
        Boss.getInstance(pane).getBossMovement().play();
        CupHead.getInstance().setBlink(false);
        CupHead.getInstance().setHealth(4);
        CupHead.getInstance().setX(30);
        CupHead.getInstance().setY(90);
        pane.getChildren().clear();
        if (gameScore > UserController.getLoggedInUser().getScore()
                || (gameScore == UserController.getLoggedInUser().getScore()
                && time < UserController.getLoggedInUser().getFinishedTime())) {
            UserController.getLoggedInUser().setScore(gameScore);
            UserController.getLoggedInUser().setFinishedTime(time);
        }
        App.changeScene("EndGamePage");
    }
}