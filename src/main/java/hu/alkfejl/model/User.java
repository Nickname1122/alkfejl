package hu.alkfejl.model;

import javafx.beans.property.*;

public class User {

    private StringProperty username = new SimpleStringProperty();
    private StringProperty password = new SimpleStringProperty();
    private StringProperty age = new SimpleStringProperty();
    private StringProperty interest = new SimpleStringProperty();
    private BooleanProperty status = new SimpleBooleanProperty();
    private IntegerProperty admin = new SimpleIntegerProperty();

    public User() {
    }

    public User(String username, String password, String age, String interest, boolean status, int admin) {
        this.username.set(username);
        this.password.set(password);
        this.age.set(age);
        this.interest.set(interest);
        this.status.set(status);
        this.admin.set(admin);
    }

    public void copyTo(User user){
        user.username.set(this.getUsername());
    }

    public String getUsername() {
        return username.get();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getAge() {
        return age.get();
    }

    public StringProperty ageProperty() {
        return age;
    }

    public void setAge(String age) {
        this.age.set(age);
    }

    public String getInterest() {
        return interest.get();
    }

    public StringProperty interestProperty() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest.set(interest);
    }

    public boolean isStatus() {
        return status.get();
    }

    public BooleanProperty statusProperty() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status.set(status);
    }

    public int getAdmin() {
        return admin.get();
    }

    public IntegerProperty adminProperty() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin.set(admin);
    }
}
