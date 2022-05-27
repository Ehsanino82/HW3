package Sample.View;

import Sample.Controller.UserController;
import Sample.App;
import Sample.Model.User;
import Sample.Transition.ButtonTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class ScoreboardPageController {

    private static boolean entered = false;
    @FXML
    private ImageView icon1;
    @FXML
    private ImageView icon2;
    @FXML
    private ImageView icon3;
    @FXML
    private VBox vbox1;
    @FXML
    private VBox vbox2;
    @FXML
    private VBox vbox3;
    @FXML
    private Button back;

    public void showScoreboard(MouseEvent mouseEvent) {
        if (entered) return;
        entered = true;
        ArrayList<User> users = UserController.getBestUsers();
        int j = 1;
        for (int i = 0; i < users.size() && i < 8; i++) {
            Text text1 = new Text();
            Text text2 = new Text();
            Text text3 = new Text();
            text1.setText(j + ". " + users.get(i).getUsername());
            text2.setText(String.valueOf(users.get(i).getScore()));
            text3.setText(App.formatTime(users.get(i).getFinishedTime()));
            text1.setFont(Font.font(14));
            text2.setFont(Font.font(14));
            text3.setFont(Font.font(14));
            if (j == 1) {
                text1.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
                text1.setFill(Color.RED);
                text2.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
                text2.setFill(Color.RED);
                text3.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
                text3.setFill(Color.RED);
                icon1.setImage(new Image(users.get(i).getIconAddress()));
            }
            if (j == 2) {
                text1.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 14));
                text1.setFill(Color.SILVER);
                text2.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 14));
                text2.setFill(Color.SILVER);
                text3.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 14));
                text3.setFill(Color.SILVER);
                icon2.setImage(new Image(users.get(i).getIconAddress()));
            }
            if (j == 3) {
                text1.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 13));
                text1.setFill(Color.BROWN);
                text2.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 13));
                text2.setFill(Color.BROWN);
                text3.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 13));
                text3.setFill(Color.BROWN);
                icon3.setImage(new Image(users.get(i).getIconAddress()));
            }
            vbox1.getChildren().add(text1);
            vbox2.getChildren().add(text2);
            vbox3.getChildren().add(text3);
            j++;
        }
    }

    public void back(MouseEvent mouseEvent) {
        entered = false;
        App.clickSound();
        ButtonTransition.fadeTransition(back, "MainPage");
    }
}