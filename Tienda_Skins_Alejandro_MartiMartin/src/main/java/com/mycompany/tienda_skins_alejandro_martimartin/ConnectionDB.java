/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tienda_skins_alejandro_martimartin;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 *
 * @author
 */
public class ConnectionDB {
    

    public static Connection openConnection() throws FileNotFoundException, IOException {
        
        Connection con =null;
        //HAy que añadir la zona sino da ERROR en la conexion
        try {
            InputStream is = new FileInputStream("C:\\Users\\am199\\OneDrive\\Documentos\\NetBeansProjects\\TiendaSkins\\Tienda_Skins_Alejandro_MartiMartin\\PruebaPropiedades.properties");
            Properties propiedad = new Properties();
            propiedad.load(is);
            String url = propiedad.getProperty("base.direccion"), user = propiedad.getProperty("base.usuario"), pass = propiedad.getProperty("base.contra");
            is.close();
            // Cargar el driver de mysql
            Class.forName("com.mysql.cj.jdbc.Driver");// la otra que se ultilizaba en el ejemplo anterior esta OBSOLETA
        
            // Obtener la conexión
            con= DriverManager.getConnection(url,user,pass);
            
            
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.toString());
        } catch (ClassNotFoundException cE) {
            System.out.println("Excepción: " + cE.toString());
        }
        return con;
    }
    
    public void closeConnection(Connection con) throws SQLException{
        
        try{
            //Cierra la conexión
            con.close();
        }catch(SQLException e){
            System.out.println("SQL Exception: "+e.toString());
        }//Cierra try-catch

    }//Cierra closeConnection
}

    
