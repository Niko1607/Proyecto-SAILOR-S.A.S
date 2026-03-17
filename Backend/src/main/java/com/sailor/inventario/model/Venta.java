package com.sailor.inventario.model;

public class Venta {
    private int IdVenta = 0;
    private double fecha;
    private double Nombre;
    private int Total;
    private boolean estado;

    public Venta(int IdVenta, int fecha, double Nombre, int Total, boolean estado) {
        this.IdVenta = IdVenta;
        this.fecha = fecha;
        this.Nombre = Nombre;
        this.Total = Total;
        this.estado = estado;
    }

    public void setIdVenta(int IdVenta) {
        this.IdVenta = IdVenta;
    }

    public int getIdVenta() {
        return IdVenta;
    }

    public void setFecha(double fecha) {
        this.fecha = fecha;
    }

    public double getFecha() {
        return fecha;
    }

    public void setNombre(double Nombre) {
        this.Nombre = Nombre;
    }

    public double getNombre() {
        return Nombre;
    }   

    public void setTotal(int Total) {
        this.Total = Total;
    }

    public int getTotal() {
        return Total;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public boolean isEstado() {
        return estado;
    }   
}
 