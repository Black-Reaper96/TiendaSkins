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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author am199
 */
public class MysqlCRUD {
    
    
    
    public static void consultarUsuario(Connection conexion, String correo, String contra, Label c1) throws IOException{
        PreparedStatement ps;
        ResultSet rs;
        boolean encontrado=false;
        try{
		String SQL = "SELECT * FROM usuarios WHERE correo_electronico = ? AND pass = ? ;";
                ps = (PreparedStatement) conexion.prepareStatement(SQL,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE );
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
    
    public static void obtenerSkinsCompra(String correo, TableView tabla, TableColumn colID, TableColumn colNombre,TableColumn colEvDe, TableColumn colEmblema, TableColumn colTipo, TableColumn colUlt) throws SQLException{
        
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
                    String SQL = "SELECT * FROM skins WHERE vendedor != ? ;";
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
}
