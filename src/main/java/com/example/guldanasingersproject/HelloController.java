package com.example.guldanasingersproject;

import com.example.guldanasingersproject.DatabaseConnection.DatabaseConnection;
import com.example.guldanasingersproject.MenuPage.MenuPage;
import com.example.guldanasingersproject.Windows.AlertWindow;
import com.example.guldanasingersproject.Windows.ConfirmationWindow;
import com.example.guldanasingersproject.Windows.ErrorWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
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

    public void onLoginConnectButtonClick(ActionEvent event) throws IOException {
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

                if (actualLogin != null && actualPassword != null) {
                    if (actualLogin.equals(login) && actualPassword.equals(password)) {
                        new MenuPage().setMenuScene(event);
                        return;
                    }
                }
                new ErrorWindow().showWindow("Вход в систему", "Вы ввели неправильный логин или пароль. Проверьте ввод и попробуйте еще раз");

            }
        } catch (Exception error) {
            error.printStackTrace();
        }
    }

    public void onExitButton() {
        Optional<ButtonType> result = new ConfirmationWindow().showConfirmationWindow("Закрытие приложения", "Вы уверены, что ходите закрыть приложение?");
        if (result.get() == ButtonType.OK){
            System.exit(1);
        }
    }

    public void openSingersPage(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage parentStage = (Stage) node.getScene().getWindow();

//        parentStage.close();
        Stage dialog = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("singers-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 579, 612);
        dialog.initOwner(parentStage);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Певцы");
        dialog.setScene(scene);
        dialog.showAndWait();
    }
}