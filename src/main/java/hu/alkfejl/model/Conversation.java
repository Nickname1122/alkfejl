package hu.alkfejl.model;

public class Conversation {

    private int roomID;
    private String message;
    private String sender;
    private String receiver;

    public Conversation() {
    }

    public Conversation(Room room, String message, User sender, User receiver) {
        this.roomID = room.getRoomID();
        this.message = message;
        this.sender = sender.getUsername();
        this.receiver = receiver.getUsername();
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
}
