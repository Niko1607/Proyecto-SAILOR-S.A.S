package src.main.java.com.sailor.inventario.model;

import src.main.java.com.sailor.inventario.dao.ProveedorDAO;

import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Proveedor {
    private int IdProveedor = 0;
    private String Nombre_del_provedor_o_empresa;
    private int Identificación;
    private int Telefono;
    private String Direccion;
    private String Correo;
    private String tipo_de_provedor;
    private boolean activo;
    private String fecha_de_registro;

    private static Scanner sc = new Scanner(System.in);

    public Proveedor(int IdProveedor, String Nombre_del_provedor_o_empresa, int Identificación, int Telefono, String Direccion, String Correo) {
        this.IdProveedor = IdProveedor;
        this.Nombre_del_provedor_o_empresa = Nombre_del_provedor_o_empresa;
        this.Identificación = Identificación;
        this.Telefono = Telefono;
        this.Direccion = Direccion;
        this.Correo = Correo;
        this.tipo_de_provedor = "Proveedor";
        this.activo = true;
        this.fecha_de_registro = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
    }
    
    public Proveedor() {
    }

    public int getIdProveedor() {
        return IdProveedor;
    }

    public void setIdProveedor(int IdProveedor) {
        this.IdProveedor = IdProveedor;
    }

    public String getNombre_del_provedor_o_empresa() {
        return Nombre_del_provedor_o_empresa;
    }

    public void setNombre_del_provedor_o_empresa(String Nombre_del_provedor_o_empresa) {
        this.Nombre_del_provedor_o_empresa = Nombre_del_provedor_o_empresa;
    }

    public int getIdentificación() {
        return Identificación;
    }

    public void setIdentificación(int Identificación) {
        this.Identificación = Identificación;
    }

    public int getTelefono() {
        return Telefono;
    }

    public void setTelefono(int Telefono) {
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

    public String getTipo_de_provedor() {
        return tipo_de_provedor;
    }

    public void setTipo_de_provedor(String tipo_de_provedor) {
        this.tipo_de_provedor = tipo_de_provedor;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getFecha_de_registro() {
        return fecha_de_registro;
    }

    public void setFecha_de_registro(String fecha_de_registro) {
        this.fecha_de_registro = fecha_de_registro;
    }

    // ===============================
    // REGISTRAR PROVEEDOR
    // ===============================

    public void registrarProveedor() {

        System.out.println("Escribe el nombre del proveedor:");
        setNombre_del_provedor_o_empresa(sc.nextLine());

        System.out.println("Escribe la identificación:");
        setIdentificación(sc.nextInt());
        sc.nextLine();

        System.out.println("Escribe el telefono:");
        setTelefono(sc.nextInt());
        sc.nextLine();

        System.out.println("Escribe la dirección:");
        setDireccion(sc.nextLine());
        sc.nextLine();

        System.out.println("Escribe el correo:");
        setCorreo(sc.nextLine());
        sc.nextLine();

        System.out.println("Escribe el tipo de proveedor:");
        setTipo_de_provedor(sc.nextLine());
        sc.nextLine();

        System.out.println("Escribe si el proveedor está activo:");
        setActivo(sc.nextBoolean());
        sc.nextLine();

        System.out.println("Escribe la fecha de registro:");
        setFecha_de_registro(sc.nextLine());
        sc.nextLine();

        ProveedorDAO proveedorDAO = new ProveedorDAO();
        proveedorDAO.insertarProveedor(this);
    }

    // ===============================
    // MOSTRAR PROVEEDOR
    // ===============================

    public void mostrarProveedor(int id) {

        ProveedorDAO proveedorDAO = new ProveedorDAO();
        Proveedor proveedor = proveedorDAO.mostrarProveedor(id);

        if(proveedor != null){
            System.out.println("Nombre del proveedor: " + proveedor.getNombre_del_provedor_o_empresa());
            System.out.println("Identificación: " + proveedor.getIdentificación());
            System.out.println("Telefono: " + proveedor.getTelefono());
            System.out.println("Dirección: " + proveedor.getDireccion());
            System.out.println("Correo: " + proveedor.getCorreo());
            System.out.println("Tipo de proveedor: " + proveedor.getTipo_de_provedor());
            System.out.println("Activo: " + proveedor.isActivo());
            System.out.println("Fecha de registro: " + proveedor.getFecha_de_registro());
        } else {
            System.out.println("Proveedor no encontrado");
        }
    }

    // ===============================
    // ACTUALIZAR PROVEEDOR
    // ===============================

    public void actualizarProveedor() {

        System.out.println("Escribe el nombre del proveedor:");
        setNombre_del_provedor_o_empresa(sc.nextLine());

        System.out.println("Escribe la identificación:");
        setIdentificación(sc.nextInt());
        sc.nextLine();

        System.out.println("Escribe el telefono:");
        setTelefono(sc.nextInt());
        sc.nextLine();

        System.out.println("Escribe la dirección:");
        setDireccion(sc.nextLine());
        sc.nextLine();
        
        System.out.println("Escribe el correo:");
        setCorreo(sc.nextLine());
        sc.nextLine();
        
        System.out.println("Escribe el tipo de proveedor:");
        setTipo_de_provedor(sc.nextLine());
        sc.nextLine();
        
        System.out.println("Escribe si el proveedor está activo:");
        setActivo(sc.nextBoolean());
        sc.nextLine();
        
        System.out.println("Escribe la fecha de registro:");
        setFecha_de_registro(sc.nextLine());
        sc.nextLine();
        
        ProveedorDAO proveedorDAO = new ProveedorDAO();
        proveedorDAO.actualizarProveedor(this);
    }   

    // ===============================
    // ELIMINAR PROVEEDOR
    // ===============================

    public void eliminarProveedor() {

        ProveedorDAO proveedorDAO = new ProveedorDAO();
        proveedorDAO.eliminarProveedor(this.getIdProveedor());

    }   

    // ===============================
    // CAMBIAR TELEFONO
    // ===============================

    public void cambiarTelefono() {

        System.out.println("Escribe el nuevo telefono:");
        setTelefono(sc.nextInt());

        ProveedorDAO proveedorDAO = new ProveedorDAO();
        proveedorDAO.cambiarTelefono(this.getIdProveedor(), this.getTelefono());

    }   

    // ===============================
    // CAMBIAR DIRECCION
    // ===============================

    public void cambiarDireccion() {

        System.out.println("Escribe la nueva dirección:");
        setDireccion(sc.nextLine());

        ProveedorDAO proveedorDAO = new ProveedorDAO();
        proveedorDAO.cambiarDireccion(this.getIdProveedor(), this.getDireccion());

    }   

    // ===============================
    // CAMBIAR CORREO
    // ===============================

    public void cambiarCorreo() {

        System.out.println("Escribe la nueva dirección:");
        setCorreo(sc.nextLine());

        ProveedorDAO proveedorDAO = new ProveedorDAO();
        proveedorDAO.cambiarCorreo(this.getIdProveedor(), this.getCorreo());

    }

    // ===============================
    // CAMBIAR NOMBRE
    // ===============================

    public void cambiarNombre() {

        System.out.println("Escribe el nuevo nombre:");
        setNombre_del_provedor_o_empresa(sc.nextLine());

        ProveedorDAO proveedorDAO = new ProveedorDAO();
        proveedorDAO.cambiarNombre(this.getIdProveedor(), this.getNombre_del_provedor_o_empresa());

    }

    // ===============================
    // CAMBIAR IDENTIFICACION
    // ===============================

    public void cambiarIdentificacion() {

        System.out.println("Escribe el nuevo identificación:");
        setIdentificación(sc.nextInt());

        ProveedorDAO proveedorDAO = new ProveedorDAO();
        proveedorDAO.cambiarIdentificacion(this.getIdProveedor(), this.getIdentificación());

    }    

    public void cambiarTipo_de_provedor() {

        System.out.println("Escribe el nuevo tipo de proveedor:");
        setTipo_de_provedor(sc.nextLine());

        ProveedorDAO proveedorDAO = new ProveedorDAO();
        proveedorDAO.cambiarTipo_de_provedor(this.getIdProveedor(), this.getTipo_de_provedor());

    }

    public void cambiarActivo() {

        System.out.println("Escribe si el proveedor está activo:");
        setActivo(sc.nextBoolean());

        ProveedorDAO proveedorDAO = new ProveedorDAO();
        proveedorDAO.cambiarActivo(this.getIdProveedor(), this.isActivo());

    }

    public void cambiarFecha_de_registro() {

        System.out.println("Escribe la nueva fecha de registro:");
        setFecha_de_registro(sc.nextLine());

        ProveedorDAO proveedorDAO = new ProveedorDAO();
        proveedorDAO.cambiarFecha_de_registro(this.getIdProveedor(), this.getFecha_de_registro());

    }
}