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
                new AlertWindow().showWarningWindow("Вход в систему", "Вы не ввели логин");
                return;
            }

            if (password.isEmpty()) {
            new AlertWindow().showWarningWindow("Вход в систему", "Вы не ввели пароль");
            return;
        }

        try {
            Statement statement = dbLink.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Users");

            while (resultSet.next()) {
                String actualLogin = resultSet.getString("login");
                String actualPassword = resultSet.getString("password");

                if (actualLogin.equals(login) && actualPassword.equals(password)) {
                    System.out.println("Мы узнали вас");
                    return;
                }
                new AlertWindow().showErrorWindow("Вход в систему", "Вы ввели неправильный логин или пароль. Проверьте ввод и попробуйте еще раз");

            }
        } catch (Exception error) {
            error.printStackTrace();
        }
    }

    public void onExitButton() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Подтвердите действие");
        alert.setHeaderText("Закрытие приложения");
        alert.setContentText("Вы уверены, что ходите закрыть приложение?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            System.exit(1);
        }
    }
}