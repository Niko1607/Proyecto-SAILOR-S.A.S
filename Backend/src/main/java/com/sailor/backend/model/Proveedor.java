package com.sailor.backend.model;

import jakarta.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "proveedores")
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProveedor;  // Cambiado de int a Long para consistencia

    private String nombreProveedorEmpresa;
    private String identificacion;
    private String telefono;
    private String direccion;
    private String correo;
    private String tipoProveedor;
    private boolean activo;
    private String fechaRegistro;
    private String ciudad;

    public Proveedor() {
        this.fechaRegistro = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        this.activo = true;
    }

    // Constructor con parámetros (sin fechaRegistro, se genera automática)
    public Proveedor(String nombreProveedorEmpresa, String identificacion,
                     String telefono, String direccion, String correo,
                     String tipoProveedor, String ciudad) {
        this();
        this.nombreProveedorEmpresa = nombreProveedorEmpresa;
        this.identificacion = identificacion;
        this.telefono = telefono;
        this.direccion = direccion;
        this.correo = correo;
        this.tipoProveedor = tipoProveedor;
        this.ciudad = ciudad;
    }

    // Getters y Setters (todos, incluyendo idProveedor Long)
    public Long getIdProveedor() { return idProveedor; }
    public void setIdProveedor(Long idProveedor) { this.idProveedor = idProveedor; }

    public String getNombreProveedorEmpresa() { 
        return nombreProveedorEmpresa; 
    }
    public void setNombreProveedorEmpresa(String nombreProveedorEmpresa) {
         this.nombreProveedorEmpresa = nombreProveedorEmpresa; 
    }

    public String getIdentificacion() { 
        return identificacion; 
    }
    public void setIdentificacion(String identificacion) {
         this.identificacion = identificacion; 
    }

    public String getTelefono() { 
        return telefono; 
    }
    public void setTelefono(String telefono) { 
        this.telefono = telefono; 
    }

    public String getDireccion() { 
        return direccion; 
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
         return correo; 
    }
    public void setCorreo(String correo) {
        this.correo = correo;
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