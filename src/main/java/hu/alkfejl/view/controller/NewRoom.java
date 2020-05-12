package hu.alkfejl.view.controller;

import hu.alkfejl.controller.RoomController;
import hu.alkfejl.model.Room;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class NewRoom implements Initializable {

    private Room room = new Room();

    @FXML
    private Button submit;

    @FXML
    private Button cancel;

    @FXML
    private ChoiceBox<String> interest;

    @FXML
    private TextField rules;

    @FXML
    private TextField roomName;


    @FXML
    public void submitRoom(){
        room.setRoomName(roomName.getText());
        room.setRules(rules.getText());
        room.setCategory(interest.getValue());

        if (RoomController.getInstance().addRoom(room)){
            Stage stage = (Stage) submit.getScene().getWindow();
            stage.close();
        }

    }

    @FXML
    public void cancelRoom(){
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> interests = FXCollections.observableArrayList("Állat", "Étel", "Hobbi", "Sport", "Szórakozás", "Utazás", "Tanulás");

        interest.setItems(interests);
        interest.setValue(interests.get(0));
        interest.getSelectionModel().select(0);
    }
}
