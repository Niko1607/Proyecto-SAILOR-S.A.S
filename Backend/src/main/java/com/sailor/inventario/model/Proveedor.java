package com.sailor.inventario.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Proveedor {
    private int IdProveedor = 0;
    private String NombreProveedorEmpresa;
    private String Identificación;
    private String Telefono;
    private String Direccion;
    private String Correo;
    private String tipoProveedor;
    private boolean activo;
    private String fechaRegistro;
    private String ciudad;


    public Proveedor(int IdProveedor, String NombreProveedorEmpresa, String Identificación, String Telefono, String Direccion, String Correo, String tipoProveedor, boolean activo, String fechaRegistro) {
        this.IdProveedor = IdProveedor;
        this.NombreProveedorEmpresa = NombreProveedorEmpresa;
        this.Identificación = Identificación;
        this.Telefono = Telefono;
        this.Direccion = Direccion;
        this.Correo = Correo;
        this.tipoProveedor = "Proveedor";
        this.activo = true;
        this.fechaRegistro = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        this.ciudad = "Ciudad";
    }
    
    public Proveedor() {
    }

    public int getIdProveedor() {
        return IdProveedor;
    }

    public void setIdProveedor(int IdProveedor) {
        this.IdProveedor = IdProveedor;
    }

    public String getNombreProveedorEmpresa() {
        return NombreProveedorEmpresa;
    }

    public void setNombreProveedorEmpresa(String NombreProveedorEmpresa) {
        this.NombreProveedorEmpresa = NombreProveedorEmpresa;
    }

    public String getIdentificación() {
        return Identificación;
    }

    public void setIdentificación(String Identificación) {
        this.Identificación = Identificación;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public String getTipoProveedor() {
        return tipoProveedor;
    }

    public void setTipoProveedor(String tipoProveedor) {
        this.tipoProveedor = tipoProveedor;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}