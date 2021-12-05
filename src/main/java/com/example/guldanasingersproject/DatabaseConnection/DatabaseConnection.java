package com.example.guldanasingersproject.DatabaseConnection;

import com.example.guldanasingersproject.Entities.SingerEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DatabaseConnection {
    public Connection databaseLink;

    public Connection getDatabaseLink() {
        String databaseName = "Music_portal";
        String databaseUserName = "root";
        String databasePassword = "2000-2000";
        String databaseUrl = "jdbc:mysql://localhost/" + databaseName;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(databaseUrl, databaseUserName, databasePassword);

        } catch (Exception err) {
            err.printStackTrace();
        }
        return databaseLink;
    }

    public ObservableList<SingerEntity> getSingers(Connection connection) throws SQLException {
        try {
            ObservableList<SingerEntity> singers = FXCollections.observableArrayList();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id, name FROM singers");

            while (resultSet.next()) {
                SingerEntity singer  = new SingerEntity(
                        Integer.parseInt(resultSet.getString("id")),
                        resultSet.getString("name"));
                singers.add(singer);
            }
            return singers;
        } catch (Exception error) {
            error.printStackTrace();
            return null;
        }

    }
}
