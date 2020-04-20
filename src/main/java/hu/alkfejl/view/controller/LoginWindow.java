package hu.alkfejl.view.controller;

import hu.alkfejl.App;
import hu.alkfejl.controller.UserController;
import hu.alkfejl.model.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginWindow implements Initializable {

    private User user = new User();

    @FXML
    private Label usernameLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private Label passwordLabel;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private Button registrationButton;
    @FXML
    private Label loginErrorLabel;


    public LoginWindow(){
    }


    @FXML
    public void submit(){

        loginButton.setOnAction(login -> {

            user.setUsername(usernameTextField.getText());
            user.setPassword(String.valueOf(passwordField.getText()));

            try {
                if(!(user.getUsername().equals("")) && !(user.getPassword().equals(""))){

                    if (UserController.getInstance().loginUser(user.getUsername(), user.getPassword())) {

                        Parent root = FXMLLoader.load(getClass().getResource("/hu/alkfejl/view/homepage.fxml"));
                        Stage stage = (Stage)((Node) login.getSource()).getScene().getWindow();
                        Scene scene = new Scene(root);
                        stage.setTitle("Home page");
                        stage.setScene(scene);
                        stage.show();

                    } else {

                        loginErrorLabel.setText("Rossz felhasználónév/jelszó!");

                    }

                } else {
                    loginErrorLabel.setText("Kérjük adja meg a felhasználónevet/jelszót!");
                }

            } catch (IOException e) {
//                System.out.println("[LOGIN BUTTON] " + e.toString());
                e.printStackTrace();
            }
        });

    }


    @FXML
    public void openRegistrationWindow(){

        registrationButton.setOnAction(reg -> {

            try {

                Parent root = FXMLLoader.load(getClass().getResource("/hu/alkfejl/view/registration.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();

            } catch (IOException e) {
                System.out.println("[REGISTRATION BUTTON] " + e);
            }

        });

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
