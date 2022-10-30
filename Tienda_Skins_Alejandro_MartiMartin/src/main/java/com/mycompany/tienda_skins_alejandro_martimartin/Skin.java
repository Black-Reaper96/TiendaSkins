/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tienda_skins_alejandro_martimartin;

/**
 *
 * @author am199
 */
public class Skin {
    private int id;
    private String nombre;
    private String codigo;
    private double precio;
    private String juego;
    private String vendedor;

    public Skin(int id, String nombre, String codigo, double precio, String juego, String vendedor) {
        this.id=id;
        this.nombre = nombre;
        this.codigo = codigo;
        this.precio = precio;
        this.juego = juego;
        this.vendedor = vendedor;
    }
    
    public Skin(String nombre, String codigo, double precio, String juego, String vendedor) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.precio = precio;
        this.juego = juego;
        this.vendedor = vendedor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getJuego() {
        return juego;
    }

    public void setJuego(String juego) {
        this.juego = juego;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }
    
    
}
