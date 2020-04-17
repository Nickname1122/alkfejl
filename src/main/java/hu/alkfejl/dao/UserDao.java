package hu.alkfejl.dao;

import hu.alkfejl.model.User;

import java.util.List;

public interface UserDao {

    boolean addUser(User user);

    boolean loginUser(String username, String password);

    boolean usedUsername(String username);

    List<User> user();

    List<User> searchUserByName(String username);

    List<User> searchUserByInterest(String interest);

}
