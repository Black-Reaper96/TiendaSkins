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

/**
 *
 * @author am199
 */
public class MysqlCRUD {
    
    public static void consultarUsuario(Connection conexion,String correo) throws IOException{
        PreparedStatement ps;
        ResultSet rs;
        boolean encontrado=false;
        Usuario u=new Usuario();
        try{
		String SQL = "SELECT * FROM usuarios WHERE correo_electronico = ? ;";
                ps = (PreparedStatement) conexion.prepareStatement(SQL,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE );
		ps.setString(1, correo);
                rs = ps.executeQuery();
                
                while (rs.next())
                {
                   encontrado=true;
                }
                if(encontrado)
                    App.setRoot("secondary");
                else
                      System.out.println("Usuario NO ENCONTRADO");
                
        }
        catch(SQLException ex)
         {
             System.out.println(ex.fillInStackTrace());
            
         }
    }
    
}
