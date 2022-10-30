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
        String url="jdbc:mysql://localhost:3306/tienda?serverTimezone=UTC"; 
        String user="root";
        String pass="CursoDAM_2223";
        try {
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

    
