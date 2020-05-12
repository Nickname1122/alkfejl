package hu.alkfejl.view.controller;

import hu.alkfejl.controller.UserController;
import hu.alkfejl.model.User;
import javafx.beans.property.IntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class RegistrationWindow implements Initializable {

    private User user = new User();

    @FXML
    private TextField regUsernameField;

    @FXML
    private PasswordField regPasswordField;

    @FXML
    private PasswordField regConfirmPasswordField;

    @FXML
    private ChoiceBox<String> interestChoiceBox;

    @FXML
    private TextField ageField;

    @FXML
    private Button regRegistrationButton;

    @FXML
    private Label registrationErrorLabel;


    public RegistrationWindow() {
    }


    @FXML
    public void register() {

        boolean success = false;

        if (!(regUsernameField.getText().isEmpty())) {
            user.setUsername(regUsernameField.getText());
            if (!(UserController.getInstance().usedUsername(user.getUsername()))) {
                if (String.valueOf(regPasswordField.getText()).equals(String.valueOf(regConfirmPasswordField.getText())) && !(regPasswordField.getText().isEmpty())) {
                    user.setPassword(String.valueOf(regPasswordField.getText()));
                } else if (regPasswordField.getText().isEmpty()) {
                    registrationErrorLabel.setText("A jelszó nem lehet üres");
                } else {
                    registrationErrorLabel.setText("A két jelszó nem egyezik meg");
                }

                user.setAge(ageField.getText());

                user.setInterest(interestChoiceBox.getValue());

                success = true;
            } else {
                registrationErrorLabel.setText("A felhasználónév foglalt");
            }


        } else {
            registrationErrorLabel.setText("A felhasználónév nem lehet üres");
        }


        if (success) {

            UserController.getInstance().addUser(user);
            registrationErrorLabel.setText("Sikeres regisztráció");

            regUsernameField.clear();
            regPasswordField.clear();
            regConfirmPasswordField.clear();
            ageField.clear();
            interestChoiceBox.getSelectionModel().select(0);

            try {
                Parent root = FXMLLoader.load(getClass().getResource("/hu/alkfejl/view/login.fxml"));
                Stage stage = (Stage) regRegistrationButton.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setTitle("Főoldal");
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                System.out.println("[RIGISTRATION WINDOW] " + e.toString());
            }

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //String interests[] = {"Állat", "Étel", "Sport", "Szórakozás", "Tanulás"};

        ObservableList<String> interests = FXCollections.observableArrayList("Állat", "Étel", "Hobbi", "Sport", "Szórakozás", "Utazás", "Tanulás");

        interestChoiceBox.setItems(interests);
        interestChoiceBox.setValue(interests.get(0));
        interestChoiceBox.getSelectionModel().select(0);

    }

}
