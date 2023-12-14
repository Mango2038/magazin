module com.example.magaz {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.magaz to javafx.fxml;
    exports com.example.magaz;
}