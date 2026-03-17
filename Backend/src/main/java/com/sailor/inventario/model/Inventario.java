package com.sailor.inventario.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Inventario {

    private int idInventario;
    private Producto producto;
    private Usuario usuario;
    private int cantidad;
    private String fechaMovimiento;

    public Inventario() {
        this.fechaMovimiento = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
    }

    public Inventario(Producto producto, Usuario usuario, int cantidad) {
        this.producto = producto;
        this.usuario = usuario;
        this.cantidad = cantidad;
        this.fechaMovimiento = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
    }

    public int getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(int idInventario) {
        this.idInventario = idInventario;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(String fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }
}