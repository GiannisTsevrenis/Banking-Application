module Cash.Me.Outside {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.fontawesome;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires org.slf4j;

    exports org.bankingapp to javafx.graphics;
//    exports org.bankingapp.Controllers; // Export only public APIs you want accessible
    exports org.bankingapp.Controllers.Admin;
    exports org.bankingapp.Controllers.Client;
    exports org.bankingapp.Models;
    exports org.bankingapp.Views;
}