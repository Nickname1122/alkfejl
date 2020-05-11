package hu.alkfejl.view.controller;

import hu.alkfejl.App;
import hu.alkfejl.controller.RoomController;
import hu.alkfejl.controller.UserController;
import hu.alkfejl.model.Room;
import hu.alkfejl.model.User;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HomePage implements Initializable {

    private User user = new User();

    @FXML
    private TableView<Room> searchTableView;

    @FXML
    private TableView<Room> searchTableView1;

    @FXML
    private TableColumn<Room, String> searchNameColumn;

    @FXML
    private TableColumn<Room, String> searchCategoryColumn;

    @FXML
    private TableColumn<Room, String> searchRuleTableColumn;

    @FXML
    private TextField searchRoomNameField;

    @FXML
    private TableColumn<Room, String> searchNameColumn1;

    @FXML
    private TableColumn<Room, String> searchCategoryColumn1;

    @FXML
    private TableColumn<Room, String> searchRuleTableColumn1;

    @FXML
    private TextField searchRoomInterestField;

    @FXML
    private TableColumn<User, String> searchUsernameInterestColumn;

    @FXML
    private TableColumn<User, String> searchUserInterestInterestColumn;

    @FXML
    private TableView<User> searchUserInterestView;

    @FXML
    private TextField searchUserInterest;

    @FXML
    public TableColumn<User, String> searchUsernameNameColumn;

    @FXML
    public TableColumn<User, String> searchInterestNameColumn;

    @FXML
    public TableView<User> searchUserNameView;

    @FXML
    private TextField searchUserName;

    @FXML
    private TableView<Room> roomsTableView;

    @FXML
    private TableColumn<Room, String> roomNameColumn;

    @FXML
    private TableColumn<Room, String> roomCategoryColumn;

    @FXML
    private TableColumn<Room, String> roomRuleColumn;

    @FXML
    private TableColumn<Room, Void> enterToRoom;

    @FXML
    private Label profilUsernameLabel;

    @FXML
    private Label profilInterestLabel;

    @FXML
    private Label profilAgeLabel;

    @FXML
    private Button logoutButton;


    @FXML
    public void initializeProfile() {

        //initilize profile
        user.usernameProperty().bindBidirectional(profilUsernameLabel.textProperty());
        user.interestProperty().bindBidirectional(profilInterestLabel.textProperty());
        user.ageProperty().bindBidirectional(profilAgeLabel.textProperty());

    }


    @FXML
    public void refreshTable() {
        List<Room> rooms = RoomController.getInstance().rooms();
        roomsTableView.setItems(FXCollections.observableList(rooms));
    }


    @FXML
    public void logout() {
        UserController.getInstance().logoutUser(user);

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/hu/alkfejl/view/login.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) logoutButton.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Bejelentkezés");
            stage.show();

        } catch (IOException e) {
            System.out.println("[LOGOUT] " + e.toString());
            e.printStackTrace();
        }

    }


    @FXML
    public void searchRoomName() {
        List<Room> rooms = RoomController.getInstance().searchRoomByName(searchRoomNameField.getText());
        searchTableView.setItems(FXCollections.observableList(rooms));

        roomNameColumn.setCellValueFactory(new PropertyValueFactory<>("roomName"));
        roomCategoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        roomRuleColumn.setCellValueFactory(new PropertyValueFactory<>("rules"));
    }


    @FXML
    public void searchRoomInterest() {
        List<Room> rooms = RoomController.getInstance().searchRoomByCategory(searchRoomInterestField.getText());
        roomsTableView.setItems(FXCollections.observableList(rooms));

        roomNameColumn.setCellValueFactory(new PropertyValueFactory<>("roomName"));
        roomCategoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        roomRuleColumn.setCellValueFactory(new PropertyValueFactory<>("rules"));
    }


    @FXML
    public void searchUserName() {
        List<User> users = UserController.getInstance().searchUserByName(searchUserName.getText());
        searchUserNameView.setItems(FXCollections.observableList(users));

        searchUsernameNameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        searchInterestNameColumn.setCellValueFactory(new PropertyValueFactory<>("interest"));

    }


    @FXML
    public void searchUserInterest() {
        List<User> users = UserController.getInstance().searchUserByInterest(searchUserInterest.getText());
        searchUserNameView.setItems(FXCollections.observableList(users));

        searchUsernameNameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        searchInterestNameColumn.setCellValueFactory(new PropertyValueFactory<>("interest"));
    }


    public void initUser(User user) {
        user.copyTo(this.user);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Room> rooms = RoomController.getInstance().rooms();
        roomsTableView.setItems(FXCollections.observableList(rooms));

        roomNameColumn.setCellValueFactory(new PropertyValueFactory<>("roomName"));
        roomCategoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        roomRuleColumn.setCellValueFactory(new PropertyValueFactory<>("rules"));

        enterToRoom.setCellFactory(actions -> new TableCell<>() {
            private final Button enter = new Button("Belépés");

            {
                enter.setOnAction(event -> {
                    Room room = getTableView().getItems().get(getIndex());
                    enterRoom(user, room, (Stage) enter.getScene().getWindow());
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    HBox container = new HBox();
                    container.getChildren().addAll(enter);
                    setGraphic(container);
                }
            }

        });

    }


    private void enterRoom(User user, Room room, Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/hu/alkfejl/view/chat_window.fxml"));
            Parent root = loader.load();
            ChatWindow controller = loader.getController();
            controller.initRoom(room);
            controller.initUser(user);

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(room.getRoomName());
            stage.show();

        } catch (IOException e) {
            System.out.println("[CHAT WINDOW] " + e.toString());
            e.printStackTrace();
        }
    }

}
