package com.sailor.inventario.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Producto {

    private int idProducto;
    private String nombreProducto;
    private String descripcion;
    private double precioProducto;
    private int cantidad;
    private int stockMinimo;
    private int stockMaximo;
    private String fechaRegistro;
    private boolean activo;

    public Producto(){
        this.fechaRegistro = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        this.activo = true;
    }

    public Producto(int idProducto, String nombreProducto, String descripcion,
                    double precioProducto, int cantidad,
                    int stockMinimo, int stockMaximo, boolean activo){

        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.descripcion = descripcion;
        this.precioProducto = precioProducto;
        this.cantidad = cantidad;
        this.stockMinimo = stockMinimo;
        this.stockMaximo = stockMaximo;
        this.activo = activo;
        this.fechaRegistro = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
    }

    public int getIdProducto() {
        return idProducto;  
    
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;   
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(double precioProducto) {
        this.precioProducto = precioProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;   
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
        this.activo = true;
    }

    public int getStockMaximo() {
        return stockMaximo;
    }

    public void setStockMaximo(int stockMaximo) {
        this.stockMaximo = stockMaximo;
        this.activo = true; 
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
        this.activo = true;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
            this.activo = activo;
    }
}