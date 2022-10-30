module com.mycompany.tienda_skins_alejandro_martimartin {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.mycompany.tienda_skins_alejandro_martimartin to javafx.fxml;
    exports com.mycompany.tienda_skins_alejandro_martimartin;
}
