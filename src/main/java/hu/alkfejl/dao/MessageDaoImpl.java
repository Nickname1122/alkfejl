package hu.alkfejl.dao;

import hu.alkfejl.model.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDaoImpl implements MessageDao {

    private static final String CONN = "jdbc:sqlite:chat.db";
    private static final String ADD_MESSAGE = "INSERT INTO messeges (roomID, sender, message) VALUES (?,?,?)";
    private static final String GET_MESSEGES = "SELECT * FROM messeges WHERE roomID = ?";

    public MessageDaoImpl() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.out.println("[MESSAGE DAO IMPL CONSTRUCTOR] " + e.toString());
        }
    }

    @Override
    public boolean addMessage(Message message) {
        try (Connection c = DriverManager.getConnection(CONN); PreparedStatement addMessage = c.prepareStatement(ADD_MESSAGE)) {
            addMessage.setInt(1, message.getRoomID());
            addMessage.setString(2, message.getSender());
            addMessage.setString(3, message.getMessage());

            return addMessage.executeUpdate() == 1;

        } catch (SQLException e) {
            System.out.println("[ADD MESSAGE] " + e.toString());
        }

        return false;
    }

    @Override
    public List<Message> getMesseges(int roomID){

        List<Message> result = new ArrayList<>();

        try(Connection c = DriverManager.getConnection(CONN); PreparedStatement getMesseges = c.prepareStatement(GET_MESSEGES)){

            getMesseges.setInt(1, roomID);

            ResultSet resultSet = getMesseges.executeQuery();

            while(resultSet.next()){
                Message message = new Message();
                message.setMessage(resultSet.getString("message"));
                message.setSender(resultSet.getString("sender"));
                message.setRoomID(resultSet.getInt("roomID"));
                message.setMessageID(resultSet.getInt("id"));
                result.add(message);
            }

            resultSet.close();

        }catch (SQLException e){
            System.out.println("[GET MESSEGES] " + e.toString());
        }

        return result;

    }
}
