package com.sailor.backend.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ventas")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;

    private boolean estado;

    private double total;

    // RELACIÓN CON USUARIO
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    // Constructor
    public Venta() {
        this.fecha = LocalDate.now();
        this.estado = true;
    }

    // GETTERS Y SETTERS
    public Long getId() {
         return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() { 
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
         this.fecha = fecha;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public double getTotal() {
        return total; 
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}