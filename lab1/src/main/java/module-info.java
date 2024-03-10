module org.recefi.lab1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires com.google.gson;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    exports org.recefi.lab1.client;
    opens org.recefi.lab1.client to javafx.fxml;
    opens org.recefi.lab1 to com.google.gson;
}