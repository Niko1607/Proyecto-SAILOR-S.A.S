package com.sailor.inventario.model;

public class Usuario {

    private int IdUsuario = 0;
    private String Nombre;
    private String Apellido;
    private int Identificacion;
    private String Correo;
    private String Contraseña ;
    private String Rol;
    private String Direccion;


    public Usuario(){}

    public Usuario(int IdUsuario ,String Nombre, String Apellido ,int Identificacion, String Correo, String Contraseña, String Rol, String Direccion){
        this.IdUsuario = IdUsuario;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Identificacion = Identificacion;
        this.Correo = Correo;
        this.Contraseña = Contraseña;
        this.Rol = Rol;
        this.Direccion = Direccion;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int IdUsuario) {
        this.IdUsuario = IdUsuario;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public int getIdentificacion() {
        return Identificacion;
    }

    public void setIdentificacion(int Identificacion) {
        this.Identificacion = Identificacion;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String Contraseña) {
        this.Contraseña = Contraseña;
    }

    public String getRol() {
        return Rol;
    }

    public void setRol(String Rol) {
        this.Rol = Rol;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }
}