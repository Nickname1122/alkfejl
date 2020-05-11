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


    public boolean addMessage(Message message) {
        return messageDao.addMessage(message);
    }


    public List<Message> getMesseges(int roomID) {
        return messageDao.getMesseges(roomID);
    }
}
