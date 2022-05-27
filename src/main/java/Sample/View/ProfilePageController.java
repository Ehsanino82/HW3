package Sample.View;

import Sample.Controller.UserController;
import Sample.App;
import Sample.Transition.ButtonTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class ProfilePageController {

    @FXML
    private ImageView icon;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Label invalidUsername;
    @FXML
    private Label updated;
    @FXML
    private Button change;
    @FXML
    private Button deleteUser;
    @FXML
    private Button logout;
    @FXML
    private Button back;

    public void handleGuestProfile() {
        if (UserController.getLoggedInUser().getUsername().equals("guest")) {
            username.setDisable(true);
            password.setDisable(true);
            deleteUser.setDisable(true);
        } else {
            icon.setImage(new Image(UserController.getLoggedInUser().getIconAddress()));
        }
    }

    public void changeData(MouseEvent mouseEvent) {
        App.clickSound();
        invalidUsername.setVisible(false);
        updated.setVisible(false);
        username.setStyle("-fx-border-color: #412100");
        password.setStyle("-fx-border-color: #412100");
        if (UserController.isUsernameUnique(username.getText()) && !username.getText().equals(UserController.getLoggedInUser().getUsername())) {
            UserController.changeUsername(username.getText());
            UserController.changePassword(password.getText());
            updated.setVisible(true);
        } else {
            invalidUsername.setVisible(true);
            username.setStyle("-fx-border-color: red");
        }
    }

    public void deleteUser(MouseEvent mouseEvent) {
        App.clickSound();
        UserController.removeUser();
        UserController.logoutUser();
        ButtonTransition.fadeTransition(deleteUser, "WelcomePage");
    }

    public void logout(MouseEvent mouseEvent) {
        App.clickSound();
        UserController.logoutUser();
        ButtonTransition.fadeTransition(logout, "WelcomePage");
    }

    public void back(MouseEvent mouseEvent) {
        App.clickSound();
        ButtonTransition.fadeTransition(back, "MainPage");
    }

    public void changeVisibility(KeyEvent keyEvent) {
        if (username.getText().length() <= 2 || password.getText().length() <= 2) {
            change.setDisable(true);
        } else {
            change.setDisable(false);
        }
        invalidUsername.setVisible(false);
        updated.setVisible(false);
        username.setStyle("-fx-border-color: #412100");
        password.setStyle("-fx-border-color: #412100");
    }
}