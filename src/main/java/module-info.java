module com.example.csc221_p4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.csc221_p4 to javafx.fxml;
    exports com.example.csc221_p4;
}