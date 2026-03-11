package src.main.java.com.sailor.inventario.model;

import src.main.java.com.sailor.inventario.dao.ProductoDAO;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Inventario {
    private Producto producto;
    private static Scanner sc = new Scanner(System.in); 

    public Inventario(Producto producto) {
        this.producto = producto;
        
        if (this.producto.getFecha_de_registro() == null || this.producto.getFecha_de_registro().isEmpty()) {
            this.producto.setFecha_de_registro(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        }
    }

    public void registrar_inventario() {
        ProductoDAO productoDAO = new ProductoDAO();
        productoDAO.registrarProducto(this.producto);
    }

    public void mostrar_inventario(int id) {
        ProductoDAO productoDAO = new ProductoDAO();
        Producto prod = productoDAO.mostrarProducto(id);
        if(prod != null){
            System.out.println("Nombre del producto: " + prod.getNombre_Producto());
            System.out.println("Cantidad en stock: " + prod.getCantidad());
            System.out.println("Está en stock minimo: " + prod.isStrok_minimo());
            System.out.println("Está en stock maximo: " + prod.isStrok_maximo());
            System.out.println("Fecha de registro: " + prod.getFecha_de_registro());
            System.out.println("Activo: " + prod.isActivo());
        } else {
            System.out.println("Producto no encontrado");
        }
    }

    public void actualizar_inventario() {
        ProductoDAO productoDAO = new ProductoDAO();
        // CORRECCIÓN 3: Pasar this.producto en lugar de this
        productoDAO.actualizarProducto(this.producto); 
    }

    public void eliminar_inventario() {
        ProductoDAO productoDAO = new ProductoDAO();
        // CORRECCIÓN 4: Acceder al ID a través del producto
        productoDAO.eliminarProducto(this.producto.getIdProducto()); 
    }

    public void cambiar_nombre() {
        System.out.println("Escribe el nuevo nombre del producto: ");
        // CORRECCIÓN 4: Usar this.producto.set...
        this.producto.setNombre_Producto(sc.nextLine()); 
        ProductoDAO productoDAO = new ProductoDAO();
        productoDAO.cambiarNombre(this.producto.getIdProducto(), this.producto.getNombre_Producto());
    }

    public void cambiar_stock() {
        System.out.println("Escribe la nueva cantidad en stock: ");
        this.producto.setCantidad(sc.nextInt());
        sc.nextLine(); // Limpiar el buffer del scanner
        ProductoDAO productoDAO = new ProductoDAO();
        productoDAO.cambiarCantidad(this.producto.getIdProducto(), this.producto.getCantidad()); // Asumo que el método en ProductoDAO se llama cambiarCantidad según tu archivo DAO
    }

    public void cambiar_stock_minimo() {
        System.out.println("Escribe si el stock minimo es (true/false): ");
        this.producto.setStrok_minimo(sc.nextBoolean());
        sc.nextLine(); // Limpiar el buffer del scanner
        // Nota: Asegúrate de que este método exista en tu ProductoDAO
        // productoDAO.cambiarStockMinimo(this.producto.getIdProducto(), this.producto.isStrok_minimo());
    }

    public void cambiar_activo() {
        System.out.println("Escribe si el producto está activo (true/false): ");
        this.producto.setActivo(sc.nextBoolean());
        sc.nextLine(); // Limpiar el buffer del scanner
        ProductoDAO productoDAO = new ProductoDAO();
        productoDAO.cambiarActivo(this.producto.getIdProducto(), this.producto.isActivo());
    }
}