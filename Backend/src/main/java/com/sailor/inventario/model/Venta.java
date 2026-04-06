package com.sailor.inventario.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Venta {

    private int idVenta;
    private String fecha;
    private int idUsuario;
    private double total;
    private boolean estado;

    public Venta() {
        this.fecha = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        this.estado = true;
    }

    public Venta(int idVenta, int idUsuario, double total) {
        this.idVenta = idVenta;
        this.idUsuario = idUsuario;
        this.total = total;
        this.estado = true;
        this.fecha = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public String getFecha() {
        return fecha;
    }

    // 🔧 ESTE FALTABA
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}