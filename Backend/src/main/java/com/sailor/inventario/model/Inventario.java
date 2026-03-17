package com.sailor.inventario.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Inventario {
    private int IdUsuario;
    private Usuario usuario;
    private Producto producto;
    private int IdInventario;
    private String Fecha;
    private double Precio;

    public Inventario() {
        this.producto = new Producto();
        this.IdInventario = 0;
        this.Fecha = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        this.Precio = 0.0;
        this.IdUsuario = 0;
    }

    public Inventario(Producto producto) {
        this.producto = producto;
        
        if (this.producto.getFecha_de_registro() == null || this.producto.getFecha_de_registro().isEmpty()) {
            this.producto.setFecha_de_registro(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        }
    }
    public void setIdUsuario(int IdUsuario) {
        this.IdUsuario = IdUsuario;
    }   
    public int getIdUsuario() {
        return IdUsuario;
    }

    public int getIdInventario() {
        return IdInventario;
    }

    public void setIdInventario(int IdInventario) {
        this.IdInventario = IdInventario;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public double getPrecio() {
        return Precio;
    }

    public void setPrecio(double Precio) {
        this.Precio = Precio;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}