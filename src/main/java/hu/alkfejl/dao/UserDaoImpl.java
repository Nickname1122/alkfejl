package hu.alkfejl.dao;

import hu.alkfejl.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private User user = new User();

    private static final String CONN = "jdbc:sqlite:C:/Users/Levente/OneDrive/Documents/Egyetem/4. félév/Alk/kotprog/chat-app-core/src/main/resources/db/chat.db";
    private static final String ADD_USER = "INSERT INTO user (username, password, age, interest) VALUES (?,?,?,?)";
    private static final String LIST_ALL_USER = "SELECT username FROM user";
    private static final String SEARCH_USER_NAME = "SELECT * FROM user WHERE username LIKE ?";
    private static final String SEARCH_USER_INTEREST = "SELECT * FROM user WHERE interest LIKE ?";
    private static final String LOGIN_USER = "SELECT username, password FROM user";
    private static final String USED_USERNAME = "SELECT username FROM user";
    private static final String LOGGED_IN = "UPDATE user SET status = 1 where status = 0 AND username = ?";
    private static final String LOGGED_OUT = "UPDATE user SET status = 0 where status = 1 AND username = ?";


    //constructor
    public UserDaoImpl() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.out.println("[USER DAO IMPL CONSTRUCTOR] " + e.toString());
        }

    }


    //Register users into database
    @Override
    public boolean addUser(User user) {
        try (Connection c = DriverManager.getConnection(CONN); PreparedStatement pst = c.prepareStatement(ADD_USER)) {

            pst.setString(1, user.getUsername());
            pst.setString(2, user.getPassword());
            pst.setString(3, user.getAge());
            pst.setString(4, user.getInterest());

            return pst.executeUpdate() == 1;

        } catch (SQLException e) {
            System.out.println("[ADD USER] " + e.toString());
//            e.printStackTrace();
        }

        return false;

    }


    //Listing all user (not sure for its need)
    @Override
    public List<User> user() {

        List<User> users = new ArrayList<>();

        try (Connection c = DriverManager.getConnection(CONN); Statement st = c.createStatement()) {

            ResultSet resultSet = st.executeQuery(LIST_ALL_USER);

            while (resultSet.next()) {
                User user = new User();
                user.setUsername(resultSet.getString("username"));
                users.add(user);
            }

            resultSet.close();

        } catch (SQLException e) {
            System.out.println("[LIST ALL USER] " + e.toString());
        }

        return users;
    }


    //Listing users by its names
    @Override
    public List<User> searchUserByName(String username) {

        List<User> users = new ArrayList<>();

        try (Connection c = DriverManager.getConnection(CONN); PreparedStatement pst = c.prepareStatement(SEARCH_USER_NAME)) {

            pst.setString(1, "%" + username + "%");
            listingUsers(users, pst);

        } catch (SQLException e) {
            System.out.println("[SEARCH USER BY NAME] " + e.toString());
        }

        return users;
    }


    //Listing users by its interest
    @Override
    public List<User> searchUserByInterest(String interest) {

        List<User> users = new ArrayList<>();

        try (Connection c = DriverManager.getConnection(CONN); PreparedStatement pst = c.prepareStatement(SEARCH_USER_INTEREST)) {

            pst.setString(1, interest);
            listingUsers(users, pst);

        } catch (SQLException e) {
            System.out.println("[SEARCH USER BY INTEREST] " + e.toString());
        }

        return users;
    }


    @Override
    public boolean loginUser(String username, String password) {
        try (Connection c = DriverManager.getConnection(CONN); Statement login = c.createStatement(); PreparedStatement status = c.prepareStatement(LOGGED_IN)) {

            status.setString(1, username);

            ResultSet resultSet = login.executeQuery(LOGIN_USER);

            while (resultSet.next()) {
                if (username.equals(resultSet.getString("username")) && password.equals(resultSet.getString("password"))) {
                    status.executeUpdate();
                    return true;
                }
            }

            resultSet.close();

        } catch (SQLException e) {
            System.out.println("[LOGIN USER] " + e.toString());
        }

        return false;

    }


    @Override
    public boolean logoutUser(User user) {

        try(Connection c = DriverManager.getConnection(CONN); PreparedStatement loggedOut = c.prepareStatement(LOGGED_OUT)){

            loggedOut.setString(1, user.getUsername());

            return loggedOut.executeUpdate() == 1;

        } catch (SQLException e){
            System.out.println("[LOGOUT] " + e.toString());
        }

        return false;
    }

    @Override
    public boolean usedUsername(String username) {

        try (Connection c = DriverManager.getConnection(CONN); Statement usedUsername = c.createStatement()) {

            ResultSet resultSet = usedUsername.executeQuery(USED_USERNAME);

            while (resultSet.next()) {
                if (username.equals(resultSet.getString("username"))) {
                    return true;
                }
            }

            resultSet.close();

        } catch (SQLException e) {
            System.out.println("[USED USERNAME] " + e.toString());
        }

        return false;
    }


    //put users into result set
    private void listingUsers(List<User> users, PreparedStatement preparedStatement) {

        try {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                user.setUsername(resultSet.getString("username"));
                user.setAge(resultSet.getString("age"));
                user.setInterest(resultSet.getString("interest"));
                users.add(user);
            }

            resultSet.close();

        } catch (SQLException e) {
            System.out.println("[LISTING USERS] " + e.toString());
        }
    }


}
