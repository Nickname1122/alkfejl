package hu.alkfejl.view.controller;

import hu.alkfejl.controller.RoomController;
import hu.alkfejl.model.Room;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HomePage implements Initializable {

    @FXML
    private AnchorPane roomsAnchor;

    @FXML
    private TableView<Room> roomsTableView;

    @FXML
    private TableColumn<Room, String> roomNameColumn;

    @FXML
    private TableColumn<Room, String> roomCategoryColumn;

    @FXML
    private TableColumn<Room, String> roomRuleColumn;

    @FXML
    private Button refreshButton;

    @FXML
    public void refreshTable() {
        List<Room> rooms = RoomController.getInstance().rooms();
        roomsTableView.setItems(FXCollections.observableList(rooms));
    }

    @FXML
    public void search() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Room> rooms = RoomController.getInstance().rooms();
        roomsTableView.setItems(FXCollections.observableList(rooms));

        roomNameColumn.setCellValueFactory(new PropertyValueFactory<>("roomName"));
        roomCategoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        roomRuleColumn.setCellValueFactory(new PropertyValueFactory<>("rules"));

    }

}
