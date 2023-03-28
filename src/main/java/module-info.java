module com.example.projwithfxandsql {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.example.projwithfxandsql to javafx.fxml;
    exports com.example.projwithfxandsql;
}