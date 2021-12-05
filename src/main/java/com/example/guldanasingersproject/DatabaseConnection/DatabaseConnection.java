package com.example.guldanasingersproject.DatabaseConnection;

import com.example.guldanasingersproject.Entities.AlbumEntity;
import com.example.guldanasingersproject.Entities.SingerEntity;
import com.example.guldanasingersproject.Entities.TotalAlbumsFields;
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

    public ObservableList<AlbumEntity> getAlbums(Connection connection) throws SQLException {
        try {
            ObservableList<AlbumEntity> albums = FXCollections.observableArrayList();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT albums.id, title, date_released, name FROM albums LEFT JOIN singers ON albums.singer_id = singers.id");

            while (resultSet.next()) {
                AlbumEntity album  = new AlbumEntity(
                        Integer.parseInt(resultSet.getString("id")),
                        resultSet.getString("title"),
                        resultSet.getString("date_released"),
                        resultSet.getString("name"));
                albums.add(album);
            }
            return albums;
        } catch (Exception error) {
            error.printStackTrace();
            return null;
        }

    }

    public ObservableList<TotalAlbumsFields> getTotalAlbumsFields(Connection connection) throws SQLException {
        try {
            ObservableList<TotalAlbumsFields> totalAlbums = FXCollections.observableArrayList();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT name, COUNT(name) as count FROM albums LEFT JOIN singers ON albums.singer_id = singers.id GROUP BY name");

            while (resultSet.next()) {
                TotalAlbumsFields album  = new TotalAlbumsFields(
                        Integer.parseInt(resultSet.getString("count")),
                        resultSet.getString("name"));
                totalAlbums.add(album);
            }
            return totalAlbums;
        } catch (Exception error) {
            error.printStackTrace();
            return null;
        }

    }
}
