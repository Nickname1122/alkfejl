package hu.alkfejl;

import com.sun.source.tree.ParenthesizedTree;
import hu.alkfejl.controller.UserController;
import hu.alkfejl.model.User;
import hu.alkfejl.view.controller.Choice;
import hu.alkfejl.view.controller.LoginWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private UserController userController = new UserController();
    private User user = new User();

    @Override
    public void start(Stage stage) {


        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/hu/alkfejl/view/login.fxml"));
            Parent root = loader.load();
            LoginWindow controller = loader.getController();
            controller.initUser(user);

            Scene scene = new Scene(root);
            stage.setTitle("Bejelentkezés");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            System.out.println("[LAUNCH] " + e);
        }
    }


    @Override
    public void stop() {
        userController.logoutUser(user);
    }

    public static void main(String[] args) {
        launch();
    }

}