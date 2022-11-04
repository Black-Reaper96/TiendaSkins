package com.mycompany.tienda_skins_alejandro_martimartin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SecondaryController {
    @FXML
    public Connection con;
    @FXML
    public Label bienvenida;
    @FXML
    public int id2;
    @FXML
    public int id1;
    
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
    @FXML
    public TableColumn<Skin, Integer> id ;
    
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
    public TableColumn<Skin, Integer> id_skin2 ;
    
    @FXML
    private TextField nombre_insertar_skin;
    @FXML
    private TextField codigo_insertar_skin;
     @FXML
    private TextField precio_insertar_skin;
    @FXML
    private TextField juego_insertar_skin;
    @FXML
    private Button insertar;
    @FXML
    private Button modificar;
    @FXML
    private Button eliminar;
    @FXML
    private Button comprar;
    @FXML
    private Button boton_bucar;
     
     
    @FXML
    private TextField nombre_compra_skin;
    @FXML
    private TextField codigo_compra_skin;
    @FXML
    private TextField precio_compra_skin;
    @FXML
    private TextField juego_compra_skin;
    @FXML
    private TextField vendedor_compra_skin;
    
    @FXML
    private TextField buscador_juego;
    
    @FXML
    private void initialize() throws SQLException, IOException  {
        Bienvenida();
        MysqlCRUD.obtenerSkinsCompra(UsuarioHolder.getUsuario(), tabla1, nombre_skin, codigo_skin, precio_skin, juego, vendedor, id, buscador_juego);
        MysqlCRUD.obtenerSkinsVenta(UsuarioHolder.getUsuario(), tabla2, nombre_skin2, codigo_skin2, precio_skin2, juego2, vendedor2, id_skin2);
    }
    
    @FXML
    private void mostrarDatosTablaInventario(){
        if(this.tabla2 != null){
            List<Skin> item = this.tabla2.getSelectionModel().getSelectedItems();
            final Skin dMostrar = item.get(0);
            String precio= " "+dMostrar.getPrecio()+" ";
            this.precio_insertar_skin.setText(precio.trim());
            this.nombre_insertar_skin.setText(dMostrar.getNombre());
            this.codigo_insertar_skin.setText(dMostrar.getCodigo());
            this.juego_insertar_skin.setText(dMostrar.getJuego());
            this.id2=dMostrar.getId();
            insertar.setDisable(false);
            modificar.setDisable(false);
            eliminar.setDisable(false);
        }
        
        
    }
    
    @FXML
    private void mostrarDatosTablaVentas(){
        if(this.tabla1 != null){
            List<Skin> item = this.tabla1.getSelectionModel().getSelectedItems();
            final Skin dMostrar = item.get(0);
            String precio= " "+dMostrar.getPrecio()+" ";
            System.out.println(dMostrar.getVendedor());
            this.precio_compra_skin.setText(precio.trim());
            this.nombre_compra_skin.setText(dMostrar.getNombre());
            this.codigo_compra_skin.setText(dMostrar.getCodigo());
            this.juego_compra_skin.setText(dMostrar.getJuego());
            this.vendedor_compra_skin.setText(dMostrar.getVendedor());
            this.id1=dMostrar.getId();
            comprar.setDisable(false);
        }
        
        
    }

    @FXML
    private void Insertar() throws IOException, SQLException {
        Skin sk1 = new Skin(this.nombre_insertar_skin.getText(),this.codigo_insertar_skin.getText(),Double.parseDouble(this.precio_insertar_skin.getText()),this.juego_insertar_skin.getText(),UsuarioHolder.getUsuario());
        MysqlCRUD.insertarSkin(sk1);
        MysqlCRUD.obtenerSkinsVenta(UsuarioHolder.getUsuario(), tabla2, nombre_skin2, codigo_skin2, precio_skin2, juego2, vendedor2, id_skin2);
        Limpieza();
        insertar.setDisable(true);
        modificar.setDisable(true);
        eliminar.setDisable(true);
    }
    
    @FXML
    private void Modificar() throws IOException, SQLException {
        Skin sk2 = new Skin(this.id2,this.nombre_insertar_skin.getText(),this.codigo_insertar_skin.getText(),Double.parseDouble(this.precio_insertar_skin.getText()),this.juego_insertar_skin.getText(),UsuarioHolder.getUsuario());
        MysqlCRUD.modificarSkin(sk2);
        MysqlCRUD.obtenerSkinsVenta(UsuarioHolder.getUsuario(), tabla2, nombre_skin2, codigo_skin2, precio_skin2, juego2, vendedor2,id_skin2);
        Limpieza();
        insertar.setDisable(true);
        modificar.setDisable(true);
        eliminar.setDisable(true);
    }
    
    @FXML
    private void Eliminar() throws IOException, SQLException {
        Skin sk3 = new Skin(this.id2, this.nombre_insertar_skin.getText(),this.codigo_insertar_skin.getText(),Double.parseDouble(this.precio_insertar_skin.getText()),this.juego_insertar_skin.getText(),UsuarioHolder.getUsuario());
        MysqlCRUD.eliminarSkin(sk3);
        MysqlCRUD.obtenerSkinsVenta(UsuarioHolder.getUsuario(), tabla2, nombre_skin2, codigo_skin2, precio_skin2, juego2, vendedor2,id_skin2);
        Limpieza();
        insertar.setDisable(true);
        modificar.setDisable(true);
        eliminar.setDisable(true);
    }
    
    @FXML
    private void Comprar() throws IOException, SQLException {
        Skin sk4 = new Skin(this.id1, this.nombre_compra_skin.getText(),this.codigo_compra_skin.getText(),Double.parseDouble(this.precio_compra_skin.getText()),this.juego_compra_skin.getText(),this.vendedor_compra_skin.getText());
        MysqlCRUD.comprarSkin(sk4, UsuarioHolder.getUsuario());
        Bienvenida();
        MysqlCRUD.obtenerSkinsCompra(UsuarioHolder.getUsuario(), tabla1, nombre_skin, codigo_skin, precio_skin, juego, vendedor, id, buscador_juego);
        MysqlCRUD.obtenerSkinsVenta(UsuarioHolder.getUsuario(), tabla2, nombre_skin2, codigo_skin2, precio_skin2, juego2, vendedor2,id_skin2);
        Limpieza();
        comprar.setDisable(true);
    }
    
    @FXML
    private void Bienvenida() throws IOException {
        try{
            con = ConnectionDB.openConnection();
            System.out.println("Conectandome a la BD Correctamente"+ con.toString());
        }catch(Exception ex){
            System.out.println(ex.fillInStackTrace());
        }
        String nombre="";
        String saldo ="";
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
                   saldo=String.valueOf(rs.getDouble("saldo"));
                }
                if(encontrado){
                    this.bienvenida.setText("Bienvenido "+nombre+" nos alegra verte. Tu saldo es de: "+saldo);
                }
                else
                    System.out.println("Usuario NO ENCONTRADO");
                
        }
        catch(SQLException ex)
         {
             System.out.println(ex.fillInStackTrace());
            
         }
    }
    
    @FXML
    private void Limpieza() throws IOException {
    
        this.nombre_compra_skin.setText("");
        this.codigo_compra_skin.setText("");
        this.precio_compra_skin.setText("");
        this.juego_compra_skin.setText("");
        this.vendedor_compra_skin.setText("");
        this.nombre_insertar_skin.setText("");
        this.codigo_insertar_skin.setText("");
        this.precio_insertar_skin.setText("");
        this.juego_insertar_skin.setText("");
        this.buscador_juego.setText("");
    }
    
    @FXML
    private void Buscador() throws IOException, SQLException {
        MysqlCRUD.obtenerSkinsCompra(UsuarioHolder.getUsuario(), tabla1, nombre_skin, codigo_skin, precio_skin, juego, vendedor, id, buscador_juego);  
    }
}