package com.sailor.inventario.model;

import com.sailor.inventario.dao.UsuarioDAO;
import java.util.Scanner;

public class Usuario {

    private int IdUsuario = 0;
    private String Nombre;
    private String Apellido;
    private int Identificacion;
    private String Correo;
    private String Contrasena;
    private String Rol;
    private String Direccion;

    private static Scanner sc = new Scanner(System.in);

    public Usuario(){}

    public Usuario(int IdUsuario ,String Nombre, String Apellido ,int Identificacion, String Correo, String Contraseña, String Rol, String Direccion){
        this.IdUsuario = IdUsuario;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Identificacion = Identificacion;
        this.Correo = Correo;
        this.Contrasena = Contrasena;
        this.Rol = Rol;
        this.Direccion = Direccion;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setId(int IdUsuario) {
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

    public String getContrasena() {
        return Contrasena;
    }

    public void setContrasena(String Contrasena) {
        this.Contrasena = Contrasena;
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

    // ===============================
    // REGISTRAR USUARIO
    // ===============================

    public void registrarUsuario() {

        System.out.println("Escribe el Nombre: ");
        setNombre(sc.nextLine());

        System.out.println("Escribe el Apellido :");
        setApellido(sc.nextLine());

        System.out.println("Escribe la Identificación:");
        setIdentificacion(sc.nextInt());
        sc.nextLine();

        System.out.println("Escribe el Correo:");
        setCorreo(sc.nextLine());

        System.out.println("Escribe la Contraseña:");
        setContrasena(sc.nextLine());

        System.out.println("Escribe el rol:");
        setRol(sc.nextLine());

        System.out.println("Escribe la Dirección del empleado:");
        setDireccion(sc.nextLine());

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.insertarUsuario(this);
    }

    // ===============================
    // MOSTRAR USUARIO
    // ===============================

    public void mostrarUsuario(int id) {

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.mostrarUsuario(id);

        if(usuario != null){

            System.out.println("Nombre: " + usuario.getNombre());
            System.out.println("Apellido: " + usuario.getApellido());
            System.out.println("Identificación: " + usuario.getIdentificacion());
            System.out.println("Correo: " + usuario.getCorreo());
            System.out.println("Rol: " + usuario.getRol());
            System.out.println("Dirección: " + usuario.getDireccion());

        } else {

            System.out.println("Usuario no encontrado");

        }
    }

    // ===============================
    // ACTUALIZAR USUARIO
    // ===============================

    public void actualizarUsuario() {

        System.out.println("Escribe el nombre del usuario:");
        setNombre(sc.nextLine());

        System.out.println("Escribe el apellido del usuario:");
        setApellido(sc.nextLine());

        System.out.println("Escribe la identificación del usuario:");
        setIdentificacion(sc.nextInt());
        sc.nextLine();

        System.out.println("Escribe el correo del usuario:");
        setCorreo(sc.nextLine());

        System.out.println("Escribe el rol del usuario:");
        setRol(sc.nextLine());

        System.out.println("Escribe la dirección del usuario:");
        setDireccion(sc.nextLine());

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.actualizarUsuario(this);
    }

    // ===============================
    // ELIMINAR USUARIO
    // ===============================

    public void eliminarUsuario() {

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.eliminarUsuario(this.getIdUsuario());

    }

    // ===============================
    // CAMBIAR CONTRASEÑA
    // ===============================

    public void cambiarContrasena() {

        System.out.println("Escribe la nueva contraseña:");
        setContrasena(sc.nextLine());

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.cambiarContrasena(this.getIdUsuario(), this.getContrasena());

    }

    // ===============================
    // CAMBIAR ROL
    // ===============================

    public void cambiarRol() {

        System.out.println("Escribe el nuevo rol:");
        setRol(sc.nextLine());

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.cambiarRol(this.getIdUsuario(), this.getRol());

    }

    // ===============================
    // CAMBIAR DIRECCION
    // ===============================

    public void cambiarDireccion() {

        System.out.println("Escribe la nueva dirección:");
        setDireccion(sc.nextLine());

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.cambiarDireccion(this.getIdUsuario(), this.getDireccion());

    }

}