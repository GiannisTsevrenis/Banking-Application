module Cash.Me.Outside {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires org.slf4j;
    requires de.jensd.fx.glyphs.fontawesome;

    exports org.bankingapp to javafx.graphics;
//    exports org.bankingapp.Controllers; // Export only public APIs you want accessible
    exports org.bankingapp.Controllers.Admin;
    exports org.bankingapp.Controllers.Client;
    exports org.bankingapp.Models;
    exports org.bankingapp.Views;
}