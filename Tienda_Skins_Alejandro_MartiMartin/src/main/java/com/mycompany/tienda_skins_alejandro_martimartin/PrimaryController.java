package com.mycompany.tienda_skins_alejandro_martimartin;

import java.io.IOException;
import java.sql.Connection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PrimaryController {
    @FXML
    private String nombre_usuario;
    @FXML
    private Connection con;
    @FXML
    private TextField usuario;
    @FXML
    private TextField contrasena;
    @FXML
    private Label error;
    @FXML
    private Button entrar;
    
    @FXML
    public void initialize() throws IOException{
    
    }
    
    @FXML
    private void Entrar() throws IOException {
        MysqlCRUD.consultarUsuario(usuario.getText(), contrasena.getText(),error);
        
        
    }
}
