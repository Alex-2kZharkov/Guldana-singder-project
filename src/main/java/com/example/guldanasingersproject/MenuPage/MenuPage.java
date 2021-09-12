package com.example.guldanasingersproject.MenuPage;

import com.example.guldanasingersproject.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuPage {
    private Stage stage;
    private Scene scene;
    private Parent root;


    public void setMenuScene(ActionEvent event) throws IOException {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("menu-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 349, 475);
        stage.setTitle("Меню");
        stage.setScene(scene);
        stage.show();
    }
}
