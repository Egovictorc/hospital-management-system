    module com.helen.hms {
    requires javafx.controls;
    requires javafx.fxml;
    requires MaterialFX;
    requires java.sql;
    requires mysql.connector.java;
    requires org.junit.jupiter.api;


    opens com.helen.hms to javafx.fxml;
    exports com.helen.hms;

    opens com.helen.hms.dao to javafx.base;
    opens com.helen.hms.controller to javafx.base, javafx.fxml;
}