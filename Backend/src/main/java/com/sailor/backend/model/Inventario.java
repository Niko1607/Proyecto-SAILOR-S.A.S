package com.sailor.backend.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "inventario_movimientos")
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInventario;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    private int cantidad;  // positivo = entrada, negativo = salida (opcional)
    private LocalDate fechaMovimiento;
    private int stock;

    // Constructores
    public Inventario() {
        this.fechaMovimiento = LocalDate.now();
    }

    public Inventario(Producto producto, Usuario usuario, int cantidad) {
        this.producto = producto;
        this.usuario = usuario;
        this.cantidad = cantidad;
        this.fechaMovimiento = LocalDate.now();
    }

    // Getters y Setters
    public Long getIdInventario() {
         return idInventario; 
    }
    public void setIdInventario(Long idInventario) {
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

    public LocalDate getFechaMovimiento() {
        return fechaMovimiento;
    }
    public void setFechaMovimiento(LocalDate fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento; 
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
