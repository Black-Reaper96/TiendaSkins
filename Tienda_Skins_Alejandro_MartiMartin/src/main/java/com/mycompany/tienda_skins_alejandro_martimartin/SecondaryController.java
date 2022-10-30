package com.mycompany.tienda_skins_alejandro_martimartin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SecondaryController {
    @FXML
    public Connection con;
    @FXML
    public Label bienvenida;
    
    @FXML
    public TableView<Skin> tabla1 ;
    @FXML
    public TableColumn<Skin, String> nombre_skin ;
    @FXML
    public TableColumn<Skin, String> codigo_skin ;
    @FXML
    public TableColumn<Skin, Double> precio_skin ;
    @FXML
    public TableColumn<Skin, String> juego ;
    @FXML
    public TableColumn<Skin, String> vendedor ;
    
    public TableView<Skin> tabla2 ;
    @FXML
    public TableColumn<Skin, String> nombre_skin2 ;
    @FXML
    public TableColumn<Skin, String> codigo_skin2 ;
    @FXML
    public TableColumn<Skin, Double> precio_skin2 ;
    @FXML
    public TableColumn<Skin, String> juego2 ;
    @FXML
    public TableColumn<Skin, String> vendedor2 ;
    
    @FXML
    private void initialize() throws SQLException  {
        
        try{
            con = ConnectionDB.openConnection();
            System.out.println("Conectandome a la BD Correctamente"+ con.toString());
        }catch(Exception ex){
            System.out.println(ex.fillInStackTrace());
        }
        String nombre="";
        PreparedStatement ps;
        ResultSet rs;
        boolean encontrado=false;
        try{
		String SQL = "SELECT * FROM usuarios WHERE correo_electronico = ? ;";
                ps = (PreparedStatement) con.prepareStatement(SQL,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE );
		ps.setString(1, UsuarioHolder.getUsuario());
                rs = ps.executeQuery();
                
                while (rs.next())
                {
                   encontrado=true;
                   nombre=rs.getString("nombre");
                }
                if(encontrado){
                    this.bienvenida.setText("Bienvenido "+nombre+" nos alegra verte");
                }
                else
                    System.out.println("Usuario NO ENCONTRADO");
                
        }
        catch(SQLException ex)
         {
             System.out.println(ex.fillInStackTrace());
            
         }
        
        MysqlCRUD.obtenerSkinsCompra(UsuarioHolder.getUsuario(), tabla1, nombre_skin, codigo_skin, precio_skin, juego, vendedor);
        MysqlCRUD.obtenerSkinsVenta(UsuarioHolder.getUsuario(), tabla2, nombre_skin2, codigo_skin2, precio_skin2, juego2, vendedor2);
    }


    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}