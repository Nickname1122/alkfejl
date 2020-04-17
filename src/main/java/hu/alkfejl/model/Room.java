package hu.alkfejl.model;

public class Room {

    private int roomID;
    private String roomName;
    private String rules;
    private String category;

    public Room() {
    }

    public Room(int roomID, String roomName, String rules, String category) {
        this.roomID = roomID;
        this.roomName = roomName;
        this.rules = rules;
        this.category = category;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
