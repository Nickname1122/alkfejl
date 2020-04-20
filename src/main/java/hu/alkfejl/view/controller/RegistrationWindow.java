package hu.alkfejl.view.controller;

import hu.alkfejl.controller.UserController;
import hu.alkfejl.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class RegistrationWindow implements Initializable {

    private User user = new User();

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private GridPane gridPane;
    @FXML
    private Label regUsernameLabel;
    @FXML
    private Label regPasswordLabel;
    @FXML
    private Label regConfirmPasswordLabel;
    @FXML
    private Label ageLabel;
    @FXML
    private Label interestLabel;
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


    public RegistrationWindow(){}


    @FXML
    public void register(){

        regRegistrationButton.setOnAction(pressed -> {

            int age;
            boolean success = false;

            if(!(regUsernameField.getText().isEmpty())) {
                user.setUsername(regUsernameField.getText());
                if (!(UserController.getInstance().usedUsername(user.getUsername()))) {
                    if (String.valueOf(regPasswordField.getText()).equals(String.valueOf(regConfirmPasswordField.getText())) && !(regPasswordField.getText().isEmpty())) {
                        user.setPassword(String.valueOf(regPasswordField.getText()));
                    } else if (regPasswordField.getText().isEmpty()) {
                        registrationErrorLabel.setText("A jelszó nem lehet üres");
                    } else {
                        registrationErrorLabel.setText("A két jelszó nem egyezik meg");
                    }
                    try {
                        age = Integer.parseInt(ageField.getText());
                        user.setAge(age);
                    } catch (NumberFormatException number){
                        registrationErrorLabel.setText("Az életkor csak szám lehet");
                    }

                    user.setInterest(interestChoiceBox.getValue());

                    success = true;

                } else {
                    registrationErrorLabel.setText("A felhasználónév foglalt");
                }

            }else{
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


                Stage stage = (Stage)((Node) pressed.getSource()).getScene().getWindow();
                stage.close();


            }

        });

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
