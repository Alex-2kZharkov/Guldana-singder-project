package com.example.guldanasingersproject;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application implements EventHandler<ActionEvent> {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("menu-page.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 438, 587);
        Scene scene = new Scene(fxmlLoader.load(), 590, 550);
        stage.setTitle("Музыкальное приложение");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void handle(ActionEvent actionEvent) {

    }

    public static void main(String[] args) {
        launch();
    }
}