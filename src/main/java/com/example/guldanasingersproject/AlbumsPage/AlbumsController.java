package com.example.guldanasingersproject.AlbumsPage;

import com.example.guldanasingersproject.DatabaseConnection.DatabaseConnection;
import com.example.guldanasingersproject.Entities.AlbumEntity;
import com.example.guldanasingersproject.Entities.SingerEntity;
import com.example.guldanasingersproject.Entities.TotalAlbumsFields;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AlbumsController implements Initializable {

    @FXML
    public Button addAlbumButton;

    @FXML
    public Button updateAlbumButton;

    @FXML
    public Button deleteAlbumButton;

    @FXML
    public Button printReportButton;

    @FXML
    public TableView<AlbumEntity> tableView;

    @FXML
    public TableColumn<AlbumEntity, InternalError> id;

    @FXML
    public TableColumn<AlbumEntity, String> title;

    @FXML
    public TableColumn<AlbumEntity, String> date_released;

    @FXML
    public TableColumn<AlbumEntity, String> name;

    @FXML
    public TextField currentId;

    @FXML
    public TextField currentTitle;

    @FXML
    public TextField currentReleasedDate;

    @FXML
    public TextField currentName;

    @FXML
    public PieChart pieChart;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        date_released.setCellValueFactory(new PropertyValueFactory<>("date_released"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        try {
            setInitialValues();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setInitialValues() throws SQLException {
        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connection = dbConnection.getDatabaseLink();
        ObservableList<AlbumEntity> albums  = dbConnection.getAlbums(connection);
        tableView.setItems(albums);

        setPieChartValues();
    }

    public void setPieChartValues() throws SQLException {
        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connection = dbConnection.getDatabaseLink();
        ObservableList<TotalAlbumsFields> albums  = dbConnection.getTotalAlbumsFields(connection);

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        for (int i = 0; i < albums.size(); i++) {
            pieChartData.add(new PieChart.Data(albums.get(i).name + " - " + albums.get(i).count, albums.get(i).count));
        }

        pieChart.setData(pieChartData);
        pieChart.setTitle("Количество альбомов у певцов");
    }

    public void printReport() {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                HashMap parameters = new HashMap();
                JasperPrint jp = JasperFillManager.fillReport("/Users/alex/JaspersoftWorkspace/FInalMusicPortal/Albums.jasper", parameters, new DatabaseConnection().getDatabaseLink());
                JasperViewer viewer = new JasperViewer(jp, false);
                viewer.setVisible(true);
                return null;
            }
        };
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(task);
        service.shutdown();
    }
}
