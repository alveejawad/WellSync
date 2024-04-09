package org.softeng.assignment;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.softeng.assignment.persistence.utils.TestUtils;

import java.io.File;
import java.io.IOException;

public class HelloApplication extends Application {
    private static File tempDB;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("WellSync!");
        stage.setScene(scene);
        stage.show();
    }

    public static void setup() throws IOException {
        tempDB = TestUtils.copyDB();
    }


    public static void main(String[] args) {
        try {
            setup();
            launch();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}