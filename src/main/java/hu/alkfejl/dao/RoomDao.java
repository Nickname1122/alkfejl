package hu.alkfejl.dao;

import hu.alkfejl.model.Room;
import java.util.List;

public interface RoomDao {

    boolean addRoom(Room room);

    List<Room> room();

    List<Room> searchRoomByName(String roomName);

    List<Room> searchRoomByCategory(String category);

}
