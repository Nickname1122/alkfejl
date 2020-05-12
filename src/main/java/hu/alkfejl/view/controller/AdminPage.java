package hu.alkfejl.view.controller;

import hu.alkfejl.controller.MessageController;
import hu.alkfejl.controller.RoomController;
import hu.alkfejl.controller.UserController;
import hu.alkfejl.model.Room;
import hu.alkfejl.model.User;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AdminPage implements Initializable {

    private User user = new User();

    @FXML
    private Button goToHome;

    @FXML
    private Button newRoom;

    @FXML
    private Button logoutButton;

    @FXML
    private TableView<User> usersView;

    @FXML
    private TableColumn<User, String> username;

    @FXML
    private TableColumn<User, String> interest;

    @FXML
    private TableColumn<User, String> age;

    @FXML
    private TableColumn<User, Void> deleteUser;

    @FXML
    private TableView<Room> roomsView;

    @FXML
    private TableColumn<Room, String> roomName;

    @FXML
    private TableColumn<Room, String> category;

    @FXML
    private TableColumn<Room, String> rules;

    @FXML
    private TableColumn<Room, Void> deleteRoom;


    @FXML
    public void refresh() {
        List<Room> rooms = RoomController.getInstance().rooms();
        roomsView.setItems(FXCollections.observableList(rooms));
        List<User> users = UserController.getInstance().user();
        usersView.setItems(FXCollections.observableList(users));
    }


    @FXML
    public void goToHomePage() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/hu/alkfejl/view/homepage.fxml"));
            Stage stage = (Stage) goToHome.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Főoldal");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("[BACK TO HOMEPAGE] " + e.toString());
        }
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
    public void createNewRoom() {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/hu/alkfejl/view/new_room.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Új szoba létrehozása");
            stage.show();

        } catch (IOException e) {
            System.out.println("[CREATE NEW ROOM] " + e.toString());
//            e.printStackTrace();
        }

    }


    private void deleteRoom(Room room) {

        RoomController.getInstance().deleteRoom(room);
        MessageController.getInstance().deleteMessegesRoom(room.getRoomID());

    }


    private void deleteUser(User user) {
        UserController.getInstance().deleteUser(user);
        MessageController.getInstance().deleteMessegesUser(user.getUsername());
    }


    public void initUser(User user) {
        user.copyTo(this.user);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Room> rooms = RoomController.getInstance().rooms();
        List<User> users = UserController.getInstance().user();

        roomsView.setItems(FXCollections.observableList(rooms));
        usersView.setItems(FXCollections.observableList(users));

        roomName.setCellValueFactory(new PropertyValueFactory<>("roomName"));
        category.setCellValueFactory(new PropertyValueFactory<>("category"));
        rules.setCellValueFactory(new PropertyValueFactory<>("rules"));
        deleteRoom.setCellFactory(actions -> new TableCell<>() {
            private final Button delete = new Button("Törlés");

            {
                delete.setOnAction(event -> {
                    Room room = getTableView().getItems().get(getIndex());
                    deleteRoom(room);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    HBox container = new HBox();
                    container.getChildren().addAll(delete);
                    setGraphic(container);
                }
            }

        });

        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        interest.setCellValueFactory(new PropertyValueFactory<>("interest"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        deleteUser.setCellFactory(actions -> new TableCell<>() {
            private final Button delete = new Button("Törlés");

            {
                delete.setOnAction(event -> {
                    User user = getTableView().getItems().get(getIndex());
                    deleteUser(user);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    HBox container = new HBox();
                    container.getChildren().addAll(delete);
                    setGraphic(container);
                }
            }

        });
    }
}
