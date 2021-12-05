module com.example.guldanasingersproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.sql;
    requires jasperreports;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens com.example.guldanasingersproject to javafx.fxml;
    exports com.example.guldanasingersproject;
    exports com.example.guldanasingersproject.MenuPage;
    exports com.example.guldanasingersproject.SingersPage;
    exports com.example.guldanasingersproject.AlbumsPage;
    exports com.example.guldanasingersproject.Entities;
    opens com.example.guldanasingersproject.MenuPage to javafx.fxml;
}