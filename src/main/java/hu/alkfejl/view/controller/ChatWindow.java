package hu.alkfejl.view.controller;

import hu.alkfejl.controller.MessageController;
import hu.alkfejl.model.Message;
import hu.alkfejl.model.Room;
import hu.alkfejl.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ChatWindow implements Initializable {

    private Room room = new Room();
    private User user = new User();
    private Message message = new Message();

    @FXML
    private TextField messegesTextField;

    @FXML
    private TextArea messagesTextArea;

    @FXML
    private Label chatRoomNameLabel;

    @FXML
    private Button homeButton;


    @FXML
    public void refresh() {
        List<Message> messeges = MessageController.getInstance().getMesseges(room.getRoomID());

        messagesTextArea.clear();

        for (Message message : messeges){
            messagesTextArea.appendText(message.getMessage() + " by: " + message.getSender() + "\n");
        }
    }


    @FXML
    public void sendMessage() {
        Message message = new Message();

        message.setRoomID(room.getRoomID());
        message.setSender(user.getUsername());
        message.setMessage(messegesTextField.getText());

        MessageController.getInstance().addMessage(message);

        messegesTextField.clear();
    }


    @FXML
    public void backToHomepage() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/hu/alkfejl/view/homepage.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) homeButton.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Főoldal");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("[BACK TO HOMEPAGE] " + e.toString());
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        room.roomNameProperty().bindBidirectional(chatRoomNameLabel.textProperty());
        messagesTextArea.setEditable(false);
    }

    public void initUser(User user) {
        user.copyTo(this.user);
    }

    public void initRoom(Room room) {
        room.copyTo(this.room);
    }
}
