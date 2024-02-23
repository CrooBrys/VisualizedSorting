//Package
package com.example.assignment1;

//Importing classes
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.IOException;

//Creating class
public class SortingHubApplication extends Application {

    //Start method
    @Override
    public void start(Stage stage) throws IOException {

        //Loading stage
        FXMLLoader fxmlLoader = new FXMLLoader(SortingHubApplication.class.getResource("SortingHub-view.fxml"));

        //Setting stage image
        stage.getIcons().add(new Image(new FileInputStream("src/main/resources/com/example/assignment1/WesternLogo.png")));

        //Setting stage size
        Scene scene = new Scene(fxmlLoader.load(), 800, 400);

        //Naming Stage
        stage.setTitle("Sorting Hub");

        //Setting scene
        stage.setScene(scene);

        //Showing stage
        stage.show();
    }

    //Driver method
    public static void main(String[] args) {
        launch();
    }
}