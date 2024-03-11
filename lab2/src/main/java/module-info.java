module org.recefi.lab {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.xml.ws;  // really need
    requires java.jws;  // really need
    requires java.sql;  // really need
    requires org.eclipse.persistence.asm;  // really need
    requires org.eclipse.persistence.core;  // really need
    requires org.eclipse.persistence.moxy;  // really need
    requires org.eclipse.persistence.sdo;  // really need

    exports org.recefi.lab.client;
    exports org.recefi.lab.server;  // really need
    opens org.recefi.lab.webservice;  // really need
    opens org.recefi.lab;
    opens org.recefi.lab.server;
    exports org.recefi.lab;
    opens org.recefi.lab.client;  // really need
}