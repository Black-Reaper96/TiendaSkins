/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tienda_skins_alejandro_martimartin;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 *
 * @author am199
 */
public class MysqlCRUD {
    
    
    
    public static void consultarUsuario(String correo, String contra, Label c1) throws IOException{
        Connection con =  ConnectionDB.openConnection();
        PreparedStatement ps;
        ResultSet rs;
        boolean encontrado=false;
        try{
		String SQL = "SELECT * FROM usuarios WHERE correo_electronico = ? AND pass = sha1(?) ;";
                ps = (PreparedStatement) con.prepareStatement(SQL,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE );
		ps.setString(1, correo);
                ps.setString(2, contra);
                rs = ps.executeQuery();
                
                while (rs.next())
                {
                   encontrado=true;
                }
                if(encontrado){
                    UsuarioHolder.setUsuario(correo);
                    App.setRoot("secondary");
                }
                else
                    c1.setVisible(true);
                    c1.setText("Usuario y/o contraseña incorrectos");
                    
                
        }
        catch(SQLException ex)
         {
             System.out.println(ex.fillInStackTrace());
            
         }
        
        
    }
    
    public static void obtenerSkinsCompra(String correo, TableView tabla, TableColumn colID, TableColumn colNombre,TableColumn colEvDe, TableColumn colEmblema, TableColumn colTipo, TableColumn colUlt, TextField nom_juego) throws SQLException{
        
        Connection con = null;
        ObservableList<Skin> obs = FXCollections.observableArrayList();
        
        try{
            con = ConnectionDB.openConnection();
            System.out.println("Conectandome a la BD Correctamente"+ con.toString());
        }catch(Exception ex){
            System.out.println(ex.fillInStackTrace());
        }
        String nombre="";
        PreparedStatement ps1;
        PreparedStatement ps2;
        ResultSet rs;
        
           
                try{
                    String SQL;
                    if(nom_juego.getText().equals("")){
                        System.out.println("");
                        SQL = "SELECT * FROM skins WHERE vendedor != ? ;";
                        ps1 = (PreparedStatement) con.prepareStatement(SQL,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE );
                        ps1.setString(1, correo);
                        rs = ps1.executeQuery();
                    }else{
                        SQL = "SELECT * FROM skins WHERE vendedor != ? AND juego LIKE ? ;";
                        ps2 = (PreparedStatement) con.prepareStatement(SQL,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE );
                        ps2.setString(1, correo);
                        ps2.setString(2, "%"+nom_juego.getText()+"%");
                        System.out.println(SQL);
                        rs = ps2.executeQuery();
                    }
                    

                    while (rs.next()){


                        Skin s3 = new Skin(rs.getInt("id"),rs.getString("nombre"),rs.getString("codigo"),rs.getDouble("precio"),rs.getString("juego"),rs.getString("vendedor"));

                        obs.add(s3);
                        tabla.setItems(obs);
                        colID.setCellValueFactory(new PropertyValueFactory("nombre"));
                        colNombre.setCellValueFactory(new PropertyValueFactory("codigo"));
                        colEvDe.setCellValueFactory(new PropertyValueFactory("precio"));
                        colEmblema.setCellValueFactory(new PropertyValueFactory("juego"));
                        colTipo.setCellValueFactory(new PropertyValueFactory("vendedor"));
                        colUlt.setCellValueFactory(new PropertyValueFactory("id"));

                    }
                }catch(SQLException ex){
                    System.out.println(ex.fillInStackTrace());

                }

           
    }
    
    public static void obtenerSkinsVenta(String correo, TableView tabla, TableColumn colID, TableColumn colNombre,TableColumn colEvDe, TableColumn colEmblema, TableColumn colTipo, TableColumn colUlt) throws SQLException{
        
        Connection con = null;
        ObservableList<Skin> obs = FXCollections.observableArrayList();
        
        try{
            con = ConnectionDB.openConnection();
            System.out.println("Conectandome a la BD Correctamente"+ con.toString());
        }catch(Exception ex){
            System.out.println(ex.fillInStackTrace());
        }
        String nombre="";
        PreparedStatement ps;
        ResultSet rs;
        
           
                try{
                    String SQL = "SELECT * FROM skins WHERE vendedor = ? ;";
                    ps = (PreparedStatement) con.prepareStatement(SQL,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE );
                    ps.setString(1, correo);
                    rs = ps.executeQuery();

                    while (rs.next()){


                        Skin s3 = new Skin(rs.getInt("id"),rs.getString("nombre"),rs.getString("codigo"),rs.getDouble("precio"),rs.getString("juego"),rs.getString("vendedor"));

                        obs.add(s3);
                        tabla.setItems(obs);
                        colID.setCellValueFactory(new PropertyValueFactory("nombre"));
                        colNombre.setCellValueFactory(new PropertyValueFactory("codigo"));
                        colEvDe.setCellValueFactory(new PropertyValueFactory("precio"));
                        colEmblema.setCellValueFactory(new PropertyValueFactory("juego"));
                        colTipo.setCellValueFactory(new PropertyValueFactory("vendedor"));
                        colUlt.setCellValueFactory(new PropertyValueFactory("id"));

                    }
                }catch(SQLException ex){
                    System.out.println(ex.fillInStackTrace());

                }

           
    }
    
    public static void insertarSkin(Skin skin) throws IOException{
        
        Connection con = null; 
        PreparedStatement ps;
        
        try {
            con = ConnectionDB.openConnection();
            System.out.println("Conectandome a la BD Correctamente"+ con.toString());
            
            String sql = "Insert into skins (nombre,codigo,precio,juego,vendedor) values(?,?,?,?,?);";
            ps = (PreparedStatement) con.prepareStatement(sql);

            ps.setString(1, skin.getNombre());
            ps.setString(2, skin.getCodigo());
            ps.setDouble(3, skin.getPrecio());
            ps.setString(4, skin.getJuego());
            ps.setString(5, skin.getVendedor());
            
            ps.executeUpdate();
            System.out.println("Usuario Insertado CORRECTAMENTE");

        } catch (SQLException ex) {
            System.out.print(ex);
        }
    
    
    }
    
    public static void modificarSkin(Skin skin) throws IOException{
        
        Connection con = null; 
        PreparedStatement ps;
        
        try {
            con = ConnectionDB.openConnection();
            System.out.println("Conectandome a la BD Correctamente"+ con.toString());
            
            String sql = "Update skins set nombre=?,codigo=?, precio=?,juego=?,vendedor=? where id=?";
            ps = (PreparedStatement) con.prepareStatement(sql);

            ps.setString(1, skin.getNombre());
            ps.setString(2, skin.getCodigo());
            ps.setDouble(3, skin.getPrecio());
            ps.setString(4, skin.getJuego());
            ps.setString(5, skin.getVendedor());
            ps.setInt(6, skin.getId());
            
            ps.executeUpdate();
            System.out.println("Skin Actualizada CORRECTAMENTE");

        } catch (SQLException ex) {
            System.out.print(ex);
        }
    
    
    }
    
    public static void eliminarSkin(Skin skin) throws IOException{
        
        Connection con = null; 
        PreparedStatement ps;
        
        try {
            con = ConnectionDB.openConnection();
            System.out.println("Conectandome a la BD Correctamente"+ con.toString());
            
            String sql = "Delete from skins where id=?";
            ps = (PreparedStatement) con.prepareStatement(sql);

            ps.setInt(1, skin.getId());
            
            ps.executeUpdate();
            System.out.println("Skin Borrada CORRECTAMENTE");

        } catch (SQLException ex) {
            System.out.print(ex);
        }
    
    
    }
    
    public static void comprarSkin(Skin skin, String nuevodueño) throws IOException{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Confirmación");
        alert.setContentText("¿Estas seguro de confirmar la acción?");
       
        Connection con = null;
        double precioSkin = skin.getPrecio();
        double precioSkin1 = skin.getPrecio();
        
        try{
            con = ConnectionDB.openConnection();
            con.setAutoCommit(false); ////// ----->> Desactivamos auto commit
            String sql2 ="Update usuarios set saldo=saldo-? where correo_electronico=?";
            PreparedStatement st2 =  (PreparedStatement)con.prepareStatement(sql2);
            st2.setDouble(1, precioSkin);
            st2.setString(2, nuevodueño);
            st2.executeUpdate();
            String sql3 ="Update usuarios set saldo=saldo+? where correo_electronico=?";
            PreparedStatement st3 =  (PreparedStatement)con.prepareStatement(sql3);
            st3.setDouble(1, precioSkin1);
            st3.setString(2, skin.getVendedor());
            st3.executeUpdate();
            String sql1 ="Update skins set vendedor=? where id=?";
            PreparedStatement st1 =  (PreparedStatement)con.prepareStatement(sql1);
            st1.setString(1, nuevodueño);
            st1.setInt(2, skin.getId());
            st1.executeUpdate();
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
                con.commit();
            } else {
               con.rollback();
            }
              ///// ---->> reflejar las operaciones en la base de datos
            
        } catch (SQLException e) {  //Si se produce una Excepción deshacemos las operaciones
                System.out.println(e.toString());
                if(con!=null){
                   try {
                     con.rollback();///// -----> Deshacer operaciones
                    } catch (SQLException ex) {
                             System.out.println(ex.toString());
                   }
                }
        } 
        

    
    }
    
    
}
