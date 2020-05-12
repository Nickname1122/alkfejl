package hu.alkfejl.view.controller;

import hu.alkfejl.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Choice {

    private User user = new User();

    @FXML
    private Button goToHome;

    @FXML
    private Button goToAdmin;

    @FXML
    public void goToHomePage() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/hu/alkfejl/view/homepage.fxml"));
            Parent root = loader.load();
            HomePage controller = loader.getController();
            controller.initUser(user);

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Főoldal");
            stage.setScene(scene);
            stage.show();

        }catch (IOException e){
            System.out.println("[GO TO HOME] " + e.toString());
        }

        Stage stage = (Stage) goToHome.getScene().getWindow();
        stage.close();
    }


    @FXML
    public void goToAdminPage() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/hu/alkfejl/view/adminPage.fxml"));
            Parent root = loader.load();
            AdminPage controller = loader.getController();
            controller.initUser(user);

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Admin felület");
            stage.setScene(scene);
            stage.show();

        }catch (IOException e){
            System.out.println("[GO TO ADMIN] " + e.toString());
        }

        Stage stage = (Stage) goToAdmin.getScene().getWindow();
        stage.close();
    }

    public void initUser(User user) {
        user.copyTo(this.user);
    }

}
