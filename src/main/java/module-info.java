module com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;
    requires com.fasterxml.jackson.databind;


    opens com.example.demo1 to javafx.fxml;
    exports com.example.demo1;
    exports com.example.demo1.dao.Impl;
    opens com.example.demo1.dao.Impl to javafx.fxml;
}