package Sample.View;

import Sample.Controller.UserController;
import Sample.App;
import Sample.Model.User;
import Sample.Transition.ButtonTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class WelcomePageController {
    @FXML
    private Button guest;
    @FXML
    private Button register;
    @FXML
    private Button login;
    @FXML
    private Label invalidRegister;
    @FXML
    private Label invalidLogin;
    @FXML
    private TextField username;
    @FXML
    private TextField password;

    @FXML
    public void registerUser(MouseEvent mouseEvent) {
        App.clickSound();
        invalidRegister.setVisible(false);
        invalidLogin.setVisible(false);
        username.setStyle("-fx-border-color: #412100");
        password.setStyle("-fx-border-color: #412100");
        if (UserController.isUsernameUnique(username.getText())) {
            UserController.addUser(username.getText(), password.getText());
        } else {
            invalidRegister.setVisible(true);
            username.setStyle("-fx-border-color: red");
        }
    }

    public void loginUser(MouseEvent mouseEvent) {
        App.clickSound();
        invalidRegister.setVisible(false);
        invalidLogin.setVisible(false);
        username.setStyle("-fx-border-color: #412100");
        password.setStyle("-fx-border-color: #412100");
        if (UserController.loginUser(username.getText(), password.getText())) {
            ButtonTransition.fadeTransition(login, "MainPage");
        } else {
            invalidLogin.setVisible(true);
            username.setStyle("-fx-border-color: red");
            password.setStyle("-fx-border-color: red");
        }
    }

    public void changeVisibility(KeyEvent keyEvent) {
        if (username.getText().length() <= 2 || password.getText().length() <= 2) {
            login.setDisable(true);
            register.setDisable(true);
        } else {
            login.setDisable(false);
            register.setDisable(false);
        }
        invalidRegister.setVisible(false);
        invalidLogin.setVisible(false);
        username.setStyle("-fx-border-color: #412100");
        password.setStyle("-fx-border-color: #412100");
    }

    public void loginAsGuest(MouseEvent mouseEvent) {
        App.clickSound();
        User user = new User("guest", "guest");
        UserController.setLoggedInUser(user);
        ButtonTransition.fadeTransition(guest, "MainPage");
    }
}