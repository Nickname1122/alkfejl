package hu.alkfejl.controller;

import hu.alkfejl.dao.UserDao;
import hu.alkfejl.dao.UserDaoImpl;
import hu.alkfejl.model.User;

import java.util.List;

public class UserController {

    private UserDao userDao = new UserDaoImpl();
    private static UserController single_instance = null;

    public UserController(){
    }

    public static UserController getInstance(){
        if (single_instance == null){
            single_instance = new UserController();
        }
        return single_instance;
    }

    public boolean addUser(User user){
        return userDao.addUser(user);
    }

    public boolean loginUser(String username, String password){
        return userDao.loginUser(username, password);
    }

    public boolean usedUsername(String username){ return userDao.usedUsername(username); }

    public List<User> user(){
        return userDao.user();
    }

    public List<User> searchUserByName(String username){
        return userDao.searchUserByName(username);
    }

    public List<User> searchUserByInterest(String interest){
        return userDao.searchUserByInterest(interest);
    }

}
