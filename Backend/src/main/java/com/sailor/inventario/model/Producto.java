package com.sailor.inventario.model;
import com.sailor.inventario.dao.ProductoDAO;

import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Producto {
    private int IdProducto;
    private String Nombre_Producto;
    private String Descripcion;
    private double Precio_Producto;
    private int Cantidad; 
    private boolean Strok_minimo;
    private boolean Strok_maximo;
    private String Fecha_de_registro;
    private boolean Activo;

    private static Scanner sc = new Scanner(System.in);

    public Producto(int IdProducto, String Nombre_Producto, String Descripcion, double Precio_Producto, int Cantidad, boolean Strok_minimo, boolean Strok_maximo, String Fecha_de_registro, boolean Activo) {
        this.IdProducto = IdProducto;
        this.Nombre_Producto = Nombre_Producto;
        this.Descripcion = Descripcion;
        this.Precio_Producto = Precio_Producto;
        this.Cantidad = Cantidad;
        this.Strok_minimo = Strok_minimo;
        this.Strok_maximo = Strok_maximo;
        this.Fecha_de_registro = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        this.Activo = Activo;   
    }

    public Producto() {
    }

    public int getIdProducto() {
        return IdProducto;
    }

    public void setIdProducto(int IdProducto) {
        this.IdProducto = IdProducto;
    }

    public String getNombre_Producto() {        
        return Nombre_Producto;
    }

    public void setNombre_Producto(String Nombre_Producto) {
        this.Nombre_Producto = Nombre_Producto;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public double getPrecio_Producto() {
        return Precio_Producto;
    }

    public void setPrecio_Producto(double Precio_Producto) {
        this.Precio_Producto = Precio_Producto;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }

    public boolean isStrok_minimo() {
        return Strok_minimo;
    }

    public void setStrok_minimo(boolean Strok_minimo) {
        this.Strok_minimo = Strok_minimo;
    }

    public boolean isStrok_maximo() {
        return Strok_maximo;
    }

    public void setStrok_maximo(boolean Strok_maximo) {
        this.Strok_maximo = Strok_maximo;
    }

    public String getFecha_de_registro() {
        return Fecha_de_registro;
    }

    public void setFecha_de_registro(String Fecha_de_registro) {
        this.Fecha_de_registro = Fecha_de_registro;
    }

    public boolean isActivo() {
        return Activo;
    }

    public void setActivo(boolean Activo) {
        this.Activo = Activo;
    }   

    public void registrar_producto() {
        System.out.println("Escribe el nombre del producto: ");
        setNombre_Producto(sc.nextLine());
        System.out.println("Escribe la descripción del producto: ");
        setDescripcion(sc.nextLine());
        System.out.println("Escribe el precio del producto: ");
        setPrecio_Producto(sc.nextDouble());
        System.out.println("Escribe la cantidad en stock: ");        
        setCantidad(sc.nextInt());
        sc.nextLine();
        System.out.println("Escribe si el stock minimo es: " + isStrok_minimo());
        setStrok_minimo(sc.nextBoolean());
        System.out.println("Escribe si el stock maximo es: " + isStrok_maximo());
        setStrok_maximo(sc.nextBoolean());
        System.out.println("Escribe la fecha de registro: ");
        setFecha_de_registro(sc.nextLine());
        System.out.println("Escribe si el producto está activo: ");
        setActivo(sc.nextBoolean());
    }

    public void Mostrar_Informacion(){
        System.out.println("Nombre del producto: " + getNombre_Producto());
        System.out.println("Descripción del producto: " + getDescripcion());
        System.out.println("Precio del producto: " + getPrecio_Producto());
        System.out.println("Cantidad en stock: " + getCantidad());
        System.out.println("Está en stock minimo: " + isStrok_minimo());
        System.out.println("Está en stock maximo: " + isStrok_maximo());    
        System.out.println("Fecha de registro: " + getFecha_de_registro());
        System.out.println("Activo: " + isActivo());
    }

    public void actualizar_producto() {
        System.out.println("Escribe el nombre del producto: ");
        setNombre_Producto(sc.nextLine());
        System.out.println("Escribe la descripción del producto: ");
        setDescripcion(sc.nextLine());
        System.out.println("Escribe el precio del producto: ");
        setPrecio_Producto(sc.nextDouble());
        System.out.println("Escribe la cantidad en stock: ");
        setCantidad(sc.nextInt());
        sc.nextLine();
        System.out.println("Escribe si el stock minimo es: " + isStrok_minimo());
        setStrok_minimo(sc.nextBoolean());
        System.out.println("Escribe si el stock maximo es: " + isStrok_maximo());
        setStrok_maximo(sc.nextBoolean());
        System.out.println("Escribe la fecha de registro: ");
        setFecha_de_registro(sc.nextLine());
        System.out.println("Escribe si el producto está activo: ");
        setActivo(sc.nextBoolean());
    }

    public void eliminar_producto() {
        System.out.println("Escribe el id del producto: ");
        int id = sc.nextInt();
        ProductoDAO productoDAO = new ProductoDAO();
        productoDAO.eliminarProducto(id);   
    }

    public void cambiar_nombre() {
        System.out.println("Escribe el nuevo nombre del producto: ");
        setNombre_Producto(sc.nextLine());
        ProductoDAO productoDAO = new ProductoDAO();
        productoDAO.cambiarNombre(this.getIdProducto(), this.getNombre_Producto());
    }

    public void cambiar_precio() {
        System.out.println("Escribe el nuevo precio del producto: ");
        setPrecio_Producto(sc.nextDouble());
        ProductoDAO productoDAO = new ProductoDAO();
        productoDAO.cambiarPrecio(this.getIdProducto(), this.getPrecio_Producto());
    }

    public void cambiar_descripcion() {
        System.out.println("Escribe la nueva descripción del producto: ");
        setDescripcion(sc.nextLine());
        ProductoDAO productoDAO = new ProductoDAO();
        productoDAO.cambiarDescripcion(this.getIdProducto(), this.getDescripcion());
    }

    public void cambiar_stock() {
        System.out.println("Escribe la nueva cantidad en stock: ");
        setCantidad(sc.nextInt());
        ProductoDAO productoDAO = new ProductoDAO();
        productoDAO.cambiarStock(this.getIdProducto(), this.getCantidad());
    }

    public void cambiar_stock_minimo() {
        System.out.println("Escribe si el stock minimo es: " + isStrok_minimo());
        setStrok_minimo(sc.nextBoolean());
        ProductoDAO productoDAO = new ProductoDAO();
        productoDAO.cambiarStockMinimo(this.getIdProducto(), this.isStrok_minimo());
    }

    public void cambiar_stock_maximo() {
        System.out.println("Escribe si el stock maximo es: " + isStrok_maximo());
        setStrok_maximo(sc.nextBoolean());
        ProductoDAO productoDAO = new ProductoDAO();
        productoDAO.cambiarStockMaximo(this.getIdProducto(), this.isStrok_maximo());
    }

    public void cambiar_fecha_de_registro() {
        System.out.println("Escribe la nueva fecha de registro: ");
        setFecha_de_registro(sc.nextLine());
        ProductoDAO productoDAO = new ProductoDAO();
        productoDAO.cambiarFechaDeRegistro(this.getIdProducto(), this.getFecha_de_registro());
    }

    public void cambiar_activo() {
        System.out.println("Escribe si el producto está activo: ");
        setActivo(sc.nextBoolean());
        ProductoDAO productoDAO = new ProductoDAO();
        productoDAO.cambiarActivo(this.getIdProducto(), this.isActivo());
    }
}
