package hu.alkfejl.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Message {

    private IntegerProperty roomID = new SimpleIntegerProperty();
    private IntegerProperty messageID = new SimpleIntegerProperty();
    private StringProperty message = new SimpleStringProperty();
    private StringProperty sender = new SimpleStringProperty();

    public Message() {
    }

    public Message(int roomID, int messageID, String message, String sender) {
        this.roomID.set(roomID);
        this.messageID.set(messageID);
        this.message.set(message);
        this.sender.set(sender);
    }


    public void copyTo(Message message){
        message.roomID.set(this.getRoomID());
        message.messageID.set(this.getMessageID());
        message.message.set(this.getMessage());
        message.sender.set(this.getSender());
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

    public int getMessageID() {
        return messageID.get();
    }

    public IntegerProperty messageIDProperty() {
        return messageID;
    }

    public void setMessageID(int messageID) {
        this.messageID.set(messageID);
    }

    public String getMessage() {
        return message.get();
    }

    public StringProperty messageProperty() {
        return message;
    }

    public void setMessage(String message) {
        this.message.set(message);
    }

    public String getSender() {
        return sender.get();
    }

    public StringProperty senderProperty() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender.set(sender);
    }
}
