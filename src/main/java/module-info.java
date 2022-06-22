module com.pmp.fx101 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;

    opens com.pmp.fx101 to javafx.fxml;
    opens com.pmp.dao to javafx.base;
    exports com.pmp.fx101;
}
