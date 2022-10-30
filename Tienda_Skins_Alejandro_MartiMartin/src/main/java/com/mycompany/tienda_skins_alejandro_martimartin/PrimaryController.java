package com.mycompany.tienda_skins_alejandro_martimartin;

import java.io.IOException;
import java.sql.Connection;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class PrimaryController {
     @FXML
    private Connection con;
    @FXML
    private TextField usuario;
    @FXML
    private TextField contrasena;
    
    @FXML
    private Button entrar;
    
    @FXML
    private void initialize() throws IOException {
        try{
              con = ConnectionDB.openConnection();
              System.out.println("Conectandome a la BD Correctamente"+ con.toString());
        }catch(Exception ex)
         {
             System.out.println(ex.fillInStackTrace());
            
         }
    }

    @FXML
    private void Entrar() throws IOException {
       MysqlCRUD.consultarUsuario(con, usuario.getText());
    }
}
