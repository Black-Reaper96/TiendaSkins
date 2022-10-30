/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tienda_skins_alejandro_martimartin;

/**
 *
 * @author am199
 */
public class Usuario {
    
    private String correo_electronico;
    private String pass;

    public Usuario(String correo_electronico, String pass) {
        this.correo_electronico = correo_electronico;
        this.pass = pass;
    }
    
     public Usuario(String correo_electronico){
        this.correo_electronico = correo_electronico;
     }
    
    public Usuario(){}

    public String getCorreo_electronico() {
        return correo_electronico;
    }

    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    
    
    
}
