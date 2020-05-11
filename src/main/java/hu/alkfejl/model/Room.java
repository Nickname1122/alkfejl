package hu.alkfejl.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Room {

    private IntegerProperty roomID = new SimpleIntegerProperty();
    private StringProperty roomName = new SimpleStringProperty();
    private StringProperty rules = new SimpleStringProperty();
    private StringProperty category = new SimpleStringProperty();

    public Room() {
    }

    public Room(int roomID, String roomName, String rules, String category) {
        this.roomID.set(roomID);
        this.roomName.set(roomName);
        this.rules.set(rules);
        this.category.set(category);
    }

    public int getRoomID() {
        return roomID.get();
    }

    public IntegerProperty roomIDProperty() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID.set(roomID);
    }

    public String getRoomName() {
        return roomName.get();
    }

    public StringProperty roomNameProperty() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName.set(roomName);
    }

    public String getRules() {
        return rules.get();
    }

    public StringProperty rulesProperty() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules.set(rules);
    }

    public String getCategory() {
        return category.get();
    }

    public StringProperty categoryProperty() {
        return category;
    }

    public void setCategory(String category) {
        this.category.set(category);
    }

    public void copyTo(Room room) {
        room.roomID.set(this.getRoomID());
        room.roomName.set(this.getRoomName());
        room.rules.set(this.getRules());
        room.category.set(this.getCategory());
    }
}
