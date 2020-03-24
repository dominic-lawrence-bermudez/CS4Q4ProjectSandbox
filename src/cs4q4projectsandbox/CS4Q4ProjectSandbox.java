package cs4q4projectsandbox;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CS4Q4ProjectSandbox extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("RPG Prototype");
        
        Parent root = FXMLLoader.load(getClass().getResource("CS4Q4ProjectSandbox.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
