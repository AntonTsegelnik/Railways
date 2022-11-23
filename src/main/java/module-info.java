module com.rw {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.rw to javafx.fxml;
    exports com.rw;
}