package hu.alkfejl.dao;

import hu.alkfejl.model.Message;

import java.util.List;

public interface MessageDao {

    boolean addMessage(Message message);

    List<Message> getMesseges(int roomID);

}
