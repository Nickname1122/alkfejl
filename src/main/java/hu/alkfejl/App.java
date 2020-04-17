package hu.alkfejl;

import com.sun.source.tree.ParenthesizedTree;
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

    @Override
    public void start(Stage stage) {
       try {
           Parent root = FXMLLoader.load(getClass().getResource("/hu/alkfejl/view/login.fxml"));
           Scene scene = new Scene(root);
           stage.setTitle("Chat APP");
           stage.setScene(scene);
           stage.show();

       } catch (IOException e){
           System.out.println("[LAUNCH] " + e);
       }
    }

    public static void main(String[] args) {
        launch();
    }

}