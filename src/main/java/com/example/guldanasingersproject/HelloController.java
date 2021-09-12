package com.example.guldanasingersproject;

import com.example.guldanasingersproject.DatabaseConnection.DatabaseConnection;
import com.example.guldanasingersproject.Windows.AlertWindow;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private TextField loginField;

    @FXML
    private TextField passwordField;

    public void onLoginConnectButtonClick() {
        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection dbLink = dbConnection.getDatabaseLink();
        String login = loginField.getText();
        String password = passwordField.getText();

        if (login.isEmpty()) {
            new AlertWindow().showWindow("Вход в систему", "Вы не ввели логин");
            return;
        }

        if (password.isEmpty()) {
            new AlertWindow().showWindow("Вход в систему", "Вы не ввели пароль");
            return;
        }

        try {
            Statement statement = dbLink.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Users");

            while (resultSet.next()) {
                System.out.println(resultSet.getString("name"));
            }
        } catch (Exception error) {
            error.printStackTrace();
        }
    }

    public void onExitButton() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Подтвердите действие");
        alert.setHeaderText("Закрытие приложения");
        alert.setContentText("Вы уверены, что ходите закрыть прилоежние?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            System.exit(1);
        }
    }
}