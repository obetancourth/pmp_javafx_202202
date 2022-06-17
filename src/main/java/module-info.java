module com.pmp.fx101 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.pmp.fx101 to javafx.fxml;
    exports com.pmp.fx101;
}
