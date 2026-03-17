package com.sailor.inventario.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Producto {
    private int IdProducto;
    private String Nombre_Producto;
    private String Descripcion;
    private double Precio_Producto;
    private int Cantidad; 
    private boolean Strok_minimo;
    private boolean Strok_maximo;
    private String Fecha_de_registro;
    private boolean Activo;

    public Producto(int IdProducto, String Nombre_Producto, String Descripcion, double Precio_Producto, int Cantidad, boolean Strok_minimo, boolean Strok_maximo, String Fecha_de_registro, boolean Activo) {
        this.IdProducto = IdProducto;
        this.Nombre_Producto = Nombre_Producto;
        this.Descripcion = Descripcion;
        this.Precio_Producto = Precio_Producto;
        this.Cantidad = Cantidad;
        this.Strok_minimo = Strok_minimo;
        this.Strok_maximo = Strok_maximo;
        this.Fecha_de_registro = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        this.Activo = Activo;   
    }

    public Producto() {
    }

    public int getIdProducto() {
        return IdProducto;
    }

    public void setIdProducto(int IdProducto) {
        this.IdProducto = IdProducto;
    }

    public String getNombre_Producto() {        
        return Nombre_Producto;
    }

    public void setNombre_Producto(String Nombre_Producto) {
        this.Nombre_Producto = Nombre_Producto;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public double getPrecio_Producto() {
        return Precio_Producto;
    }

    public void setPrecio_Producto(double Precio_Producto) {
        this.Precio_Producto = Precio_Producto;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }

    public boolean isStrok_minimo() {
        return Strok_minimo;
    }

    public void setStrok_minimo(boolean Strok_minimo) {
        this.Strok_minimo = Strok_minimo;
    }

    public boolean isStrok_maximo() {
        return Strok_maximo;
    }

    public void setStrok_maximo(boolean Strok_maximo) {
        this.Strok_maximo = Strok_maximo;
    }

    public String getFecha_de_registro() {
        return Fecha_de_registro;
    }

    public void setFecha_de_registro(String Fecha_de_registro) {
        this.Fecha_de_registro = Fecha_de_registro;
    }

    public boolean isActivo() {
        return Activo;
    }

    public void setActivo(boolean Activo) {
        this.Activo = Activo;
    }   
}
