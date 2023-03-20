module com.example.projwithfxandsql {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.projwithfxandsql to javafx.fxml;
    exports com.example.projwithfxandsql;
}