package com.example.guldanasingersproject.SingersPage;

import com.example.guldanasingersproject.DatabaseConnection.DatabaseConnection;
import com.example.guldanasingersproject.Entities.SingerEntity;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SingersController implements Initializable {
    @FXML
    public TableView<SingerEntity> tableView;

    @FXML
    public TableColumn<SingerEntity, Integer> id;

    @FXML
    public TableColumn<SingerEntity, String> name;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        try {
//            setInitialValues();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setInitialValues() throws SQLException {
        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connection = dbConnection.getDatabaseLink();
        ObservableList<SingerEntity> cars  = dbConnection.getSingers(connection);
        tableView.setItems(cars);
    }
}
