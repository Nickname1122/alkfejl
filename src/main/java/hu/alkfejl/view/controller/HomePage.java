package hu.alkfejl.view.controller;

import hu.alkfejl.App;
import hu.alkfejl.controller.RoomController;
import hu.alkfejl.model.Room;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HomePage implements Initializable {

    @FXML
    private TableView<Room> roomsTableView;

    @FXML
    private TableColumn<Room, String> roomNameColumn;

    @FXML
    private TableColumn<Room, String> roomCategoryColumn;

    @FXML
    private TableColumn<Room, String> roomRuleColumn;

    @FXML
    public TableColumn<Room, Void> enterToRoom;

    @FXML
    public void refreshTable() {
        List<Room> rooms = RoomController.getInstance().rooms();
        roomsTableView.setItems(FXCollections.observableList(rooms));
    }

    @FXML
    public void searchRoom() {
    }

    @FXML
    public void searchUser(){
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
                    enterRoom(room, (Stage) enter.getScene().getWindow());

                    Stage stage = (Stage) enter.getScene().getWindow();
                    stage.close();

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

    private void enterRoom(Room room, Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/org/example/view/videolejatszo.fxml"));
            Parent root = loader.load();
            ChatWindow controller = loader.getController();
            controller.initRoom(room);

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(room.getRoomName());
            stage.show();

        } catch (IOException e) {
            System.out.println("[CHATWINDOW] " + e.toString());
            e.printStackTrace();
        }
    }

}
