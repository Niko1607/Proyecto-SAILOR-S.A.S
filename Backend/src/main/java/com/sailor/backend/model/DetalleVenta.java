package com.sailor.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "detalle_venta")
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int cantidad;

    private double precioUnitario;

    private double subtotal;

    // RELACIÓN CON VENTA
    @ManyToOne
    @JoinColumn(name = "venta_id")
    private Venta venta;

    // RELACIÓN CON PRODUCTO
    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    // GETTERS Y SETTERS
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double getSubtotal() { 
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
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
}
