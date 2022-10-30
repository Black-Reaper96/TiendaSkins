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
    
    public static void obtenerSkinsCompra(String correo, TableView tabla, TableColumn colID, TableColumn colNombre,TableColumn colEvDe, TableColumn colEmblema, TableColumn colTipo) throws SQLException{
        
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


                        Skin s3 = new Skin(rs.getString("nombre"),rs.getString("codigo"),rs.getDouble("precio"),rs.getString("juego"),rs.getString("vendedor"));

                        obs.add(s3);
                        tabla.setItems(obs);
                        colID.setCellValueFactory(new PropertyValueFactory("nombre"));
                        colNombre.setCellValueFactory(new PropertyValueFactory("codigo"));
                        colEvDe.setCellValueFactory(new PropertyValueFactory("precio"));
                        colEmblema.setCellValueFactory(new PropertyValueFactory("juego"));
                        colTipo.setCellValueFactory(new PropertyValueFactory("vendedor"));

                    }
                }catch(SQLException ex){
                    System.out.println(ex.fillInStackTrace());

                }

           
    }
    
    public static void obtenerSkinsVenta(String correo, TableView tabla, TableColumn colID, TableColumn colNombre,TableColumn colEvDe, TableColumn colEmblema, TableColumn colTipo) throws SQLException{
        
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


                        Skin s3 = new Skin(rs.getString("nombre"),rs.getString("codigo"),rs.getDouble("precio"),rs.getString("juego"),rs.getString("vendedor"));

                        obs.add(s3);
                        tabla.setItems(obs);
                        colID.setCellValueFactory(new PropertyValueFactory("nombre"));
                        colNombre.setCellValueFactory(new PropertyValueFactory("codigo"));
                        colEvDe.setCellValueFactory(new PropertyValueFactory("precio"));
                        colEmblema.setCellValueFactory(new PropertyValueFactory("juego"));
                        colTipo.setCellValueFactory(new PropertyValueFactory("vendedor"));

                    }
                }catch(SQLException ex){
                    System.out.println(ex.fillInStackTrace());

                }

           
    }
    
    
}
