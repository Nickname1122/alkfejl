package hu.alkfejl.dao;

import hu.alkfejl.model.Room;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDaoImpl implements RoomDao {

    private static final String CONN = "jdbc:sqlite:C:/Users/Levente/OneDrive/Documents/Egyetem/4. félév/Alk/Chat App/src/main/resources/db/chat.db";
    private static final String ADD_ROOM = "INSERT INTO room (roomName, rules, category) VALUES (?,?,?)";
    private static final String LIST_ALL_ROOM = "SELECT * FROM room";
    private static final String SEARCH_ROOM_NAME = "SELECT * FROM room WHERE roomName LIKE ?";
    private static final String SEARCH_ROOM_CATEGORY = "SELECT * FROM room WHERE category LIKE ?";

    //constructor
    public RoomDaoImpl() {
        try{
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e){
            System.out.println("[ROOM DAO IMPL CONSTRUCTOR] " + e);
        }
    }


    //Register room into database
    @Override
    public boolean addRoom(Room room) {

        try (Connection c = DriverManager.getConnection(CONN); PreparedStatement pst = c.prepareStatement(ADD_ROOM) ){

            pst.setString(1, room.getRoomName());
            pst.setString(2, room.getRules());
            pst.setString(3, room.getCategory());

            return pst.executeUpdate() == 1;

        } catch (SQLException e) {
            System.out.println("[ADD ROOM] " + e);
        }

        return false;
    }


    //Listing all room
    @Override
    public List<Room> room() {

        Room room = new Room();
        List<Room> rooms = new ArrayList<>();

        try (Connection c = DriverManager.getConnection(CONN); PreparedStatement pst = c.prepareStatement(LIST_ALL_ROOM)){

            ResultSet resultSet = pst.executeQuery();

            while (resultSet.next()){
                room.setRoomID(resultSet.getInt(Integer.parseInt("roomID")));
                room.setRoomName(resultSet.getString("roomName"));
                room.setRules(resultSet.getString("rules"));
                room.setCategory(resultSet.getString("category"));
                rooms.add(room);
            }

            resultSet.close();


        }catch (SQLException e){
            System.out.println("[LIST ALL ROOM] " + e);
        }

        return rooms;
    }


    //Listing rooms by its names
    @Override
    public List<Room> searchRoomByName(String roomName) {
        Room room = new Room();
        List<Room> rooms = new ArrayList<>();

        try (Connection c = DriverManager.getConnection(CONN); PreparedStatement pst = c.prepareStatement(SEARCH_ROOM_NAME)){

            pst.setString(1, "%" + roomName + "%");
            listingRooms(room, rooms, pst);
            rooms.add(listingRooms(room, rooms, pst));

        } catch (SQLException e) {
            System.out.println("[SEARCH ROOM BY NAME] " + e);
        }

        return rooms;
    }


    //Listing rooms by its categories
    @Override
    public List<Room> searchRoomByCategory(String category) {
        Room room = new Room();
        List<Room> rooms = new ArrayList<>();

        try (Connection c = DriverManager.getConnection(CONN); PreparedStatement pst = c.prepareStatement(SEARCH_ROOM_CATEGORY)){

            pst.setString(1, category);
            listingRooms(room, rooms, pst);

        } catch (SQLException e) {
            System.out.println("[SEARCH ROOM BY CATEGORY] " + e);
        }

        return rooms;
    }


    //put the rooms into a result set
    private Room listingRooms(Room room, List<Room> rooms, PreparedStatement preparedStatement) throws SQLException{
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            room.setRoomID(resultSet.getInt("roomID"));
            room.setRoomName(resultSet.getString("roomName"));
            room.setRules(resultSet.getString("rules"));
            room.setCategory(resultSet.getString("category"));
            rooms.add(room);
        }

        resultSet.close();

        return room;
    }



}