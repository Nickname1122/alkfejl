package hu.alkfejl.controller;

import hu.alkfejl.dao.MessageDao;
import hu.alkfejl.dao.MessageDaoImpl;
import hu.alkfejl.model.Message;

import java.util.List;

public class MessageController {

    private MessageDao messageDao = new MessageDaoImpl();
    private static MessageController single_instance = null;

    public MessageController() {
    }


    public static MessageController getInstance() {
        if (single_instance == null) {
            single_instance = new MessageController();
        }
        return single_instance;
    }


    public void addMessage(Message message) {
        messageDao.addMessage(message);
    }


    public void deleteMessegesRoom(int roomID) {
        messageDao.deleteMessegesRoom(roomID);
    }


    public void deleteMessegesUser(String username) {
        messageDao.deleteMessegesUser(username);
    }


    public List<Message> getMesseges(int roomID) {
        return messageDao.getMesseges(roomID);
    }
}
