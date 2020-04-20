package hu.alkfejl.controller;

import hu.alkfejl.dao.RoomDao;
import hu.alkfejl.dao.RoomDaoImpl;
import hu.alkfejl.model.Room;

import java.util.List;

public class RoomController {

    private RoomDao roomDao = new RoomDaoImpl();
    private static RoomController single_instance = null;

    public RoomController(){}

    public static RoomController getInstance(){
        if(single_instance == null){
            single_instance = new RoomController();
        }
        return single_instance;
    }

    public List<Room> rooms(){ return roomDao.room(); }

}
