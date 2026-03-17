package com.sailor.inventario.model;

public class Detalleventa {

    private int IdDetalleVenta;
    private Venta venta;
    private Producto producto;
    private int Cantidad;
    private double PrecioUnitario;
    private double subTotal;

    public Detalleventa(int IdDetalleVenta, Venta venta, Producto producto, int Cantidad, double PrecioUnitario, double subTotal) {
        this.IdDetalleVenta = IdDetalleVenta;
        this.venta = venta;
        this.producto = producto;
        this.Cantidad = Cantidad;
        this.PrecioUnitario = PrecioUnitario;
        this.subTotal = subTotal;
    }

    public int getIdDetalleVenta() {
        return IdDetalleVenta;
    }

    public void setIdDetalleVenta(int IdDetalleVenta) {
        this.IdDetalleVenta = IdDetalleVenta;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }

    public double getPrecioUnitario() {
        return PrecioUnitario;
    }

    public void setPrecioUnitario(double PrecioUnitario) {
        this.PrecioUnitario = PrecioUnitario;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }
}