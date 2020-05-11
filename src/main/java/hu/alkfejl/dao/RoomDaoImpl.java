package hu.alkfejl.dao;

import hu.alkfejl.model.Room;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDaoImpl implements RoomDao {

    private static final String CONN = "jdbc:sqlite:C:/Users/Levente/OneDrive/Documents/Egyetem/4. félév/Alk/kotprog/chat-app-core/src/main/resources/db/chat.db";
    private static final String ADD_ROOM = "INSERT INTO room (roomName, rules, category) VALUES (?,?,?)";
    private static final String LIST_ALL_ROOM = "SELECT * FROM room";
    private static final String SEARCH_ROOM_NAME = "SELECT roomName, rules, category FROM room WHERE roomName LIKE ?";
    private static final String SEARCH_ROOM_CATEGORY = "SELECT roomName, rules, category FROM room WHERE category LIKE ?";

    //constructor
    public RoomDaoImpl() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.out.println("[ROOM DAO IMPL CONSTRUCTOR] " + e.toString());
        }
    }


    //Register room into database
    @Override
    public boolean addRoom(Room room) {

        try (Connection c = DriverManager.getConnection(CONN); PreparedStatement pst = c.prepareStatement(ADD_ROOM)) {

            pst.setString(1, room.getRoomName());
            pst.setString(2, room.getRules());
            pst.setString(3, room.getCategory());

            return pst.executeUpdate() == 1;

        } catch (SQLException e) {
            System.out.println("[ADD ROOM] " + e.toString());
        }

        return false;
    }


    //Listing all room
    @Override
    public List<Room> room() {

        List<Room> rooms = new ArrayList<>();

        try (Connection c = DriverManager.getConnection(CONN); Statement pst = c.createStatement()) {

            ResultSet resultSet = pst.executeQuery(LIST_ALL_ROOM);

            getRoomsData(rooms, resultSet);


        } catch (SQLException e) {
            System.out.println("[LIST ALL ROOM] " + e.toString());
        }

        return rooms;
    }


    //Listing rooms by its names
    @Override
    public List<Room> searchRoomByName(String roomName) {
        List<Room> rooms = new ArrayList<>();

        try (Connection c = DriverManager.getConnection(CONN); PreparedStatement pst = c.prepareStatement(SEARCH_ROOM_NAME)) {

            pst.setString(1, "%" + roomName + "%");
            listingRooms(rooms, pst);

        } catch (SQLException e) {
            System.out.println("[SEARCH ROOM BY NAME] " + e.toString());
        }

        return rooms;
    }


    //Listing rooms by its categories
    @Override
    public List<Room> searchRoomByCategory(String category) {
        List<Room> rooms = new ArrayList<>();

        try (Connection c = DriverManager.getConnection(CONN); PreparedStatement pst = c.prepareStatement(SEARCH_ROOM_CATEGORY)) {

            pst.setString(1, category);
            listingRooms(rooms, pst);

        } catch (SQLException e) {
            System.out.println("[SEARCH ROOM BY CATEGORY] " + e.toString());
        }

        return rooms;
    }


    private void getRoomsData(List<Room> rooms, ResultSet resultSet) {

        try {
            while (resultSet.next()) {
                Room room = new Room();
                room.setRoomID(resultSet.getInt("roomID"));
                room.setRoomName(resultSet.getString("roomName"));
                room.setRules(resultSet.getString("rules"));
                room.setCategory(resultSet.getString("category"));
                rooms.add(room);
            }

            resultSet.close();

        } catch (SQLException e) {
            System.out.println("[GET ROOMS DATA] " + e.toString());
            e.printStackTrace();
        }

    }


    //put the rooms into a result set
    private void listingRooms(List<Room> rooms, PreparedStatement preparedStatement) {

        try {
            ResultSet resultSet = preparedStatement.executeQuery();

            getRoomsData(rooms, resultSet);

            resultSet.close();

        } catch (SQLException e) {
            System.out.println("[LISTING ROOMS] " + e.toString());
//            e.printStackTrace();
        }

    }

}
