package com.sailor.inventario.main;

import java.util.Scanner;

import com.sailor.inventario.dao.DetalleVentaDAO;
import com.sailor.inventario.dao.InventarioDAO;
import com.sailor.inventario.dao.ProductoDAO;
import com.sailor.inventario.dao.ProveedorDAO;
import com.sailor.inventario.dao.VentaDAO;
import com.sailor.inventario.dao.UsuarioDAO;
import com.sailor.inventario.model.Detalleventa;
import com.sailor.inventario.model.Inventario;
import com.sailor.inventario.model.Proveedor;
import com.sailor.inventario.model.Producto;
import com.sailor.inventario.model.Usuario;
import com.sailor.inventario.model.Venta;

public class sistema {

    Scanner sc = new Scanner(System.in);

    // =========================
    // MENU PRINCIPAL
    // =========================
    void menuPrincipal() {
        int opcion;

        do {
            System.out.println("\n========== MENU PRINCIPAL ==========");
            System.out.println("1. Login");
            System.out.println("2. Menu Detalle Venta");
            System.out.println("3. Salir");
            System.out.print("Opcion: ");

            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1:
                    login();
                    break;
                case 2:
                    menuDetalleVenta();
                    break;
                case 3:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion invalida");
            }

        } while (opcion != 3);
    }

    // =========================
    // LOGIN
    // =========================
    public Usuario login() {

        UsuarioDAO usuarioDAO = new UsuarioDAO();

        System.out.print("Correo: ");
        String correo = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        Usuario usuario = usuarioDAO.login(correo, password);

        if (usuario != null) {
            System.out.println("✅ Login exitoso - Bienvenido " + usuario.getNombre());
        } else {
            System.out.println("❌ Credenciales incorrectas");
        }

        return usuario;
    }

    // =========================
    // MENU DETALLE VENTA
    // =========================
    void menuDetalleVenta() {
        int opcion;

        do {
            System.out.println("\n====== DETALLE VENTA ======");
            System.out.println("1. Registrar");
            System.out.println("2. Mostrar");
            System.out.println("3. Actualizar");
            System.out.println("4. Eliminar");
            System.out.println("5. Volver");
            System.out.print("Opcion: ");

            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1:
                    registrarDetalleVenta();
                    break;
                case 2:
                    mostrarDetalleVenta();
                    break;
                case 3:
                    actualizarDetalleVenta();
                    break;
                case 4:
                    eliminarDetalleVenta();
                    break;
                case 5:
                    System.out.println("Volviendo...");
                    break;
                default:
                    System.out.println("Opcion invalida");
            }

        } while (opcion != 5);
    }

    // =========================
    // REGISTRAR
    // =========================
    void registrarDetalleVenta() {

        DetalleVentaDAO dao = new DetalleVentaDAO();
        Detalleventa detalle = new Detalleventa();

        Venta venta = new Venta();
        Producto producto = new Producto();

        System.out.print("ID Venta: ");
        int idVenta = Integer.parseInt(sc.nextLine());

        System.out.print("ID Producto: ");
        int idProducto = Integer.parseInt(sc.nextLine());

        System.out.print("Cantidad: ");
        int cantidad = Integer.parseInt(sc.nextLine());

        System.out.print("Precio Unitario: ");
        double precio = Double.parseDouble(sc.nextLine());

        double subtotal = cantidad * precio;

        venta.setIdVenta(idVenta);
        producto.setIdProducto(idProducto);

        detalle.setVenta(venta);
        detalle.setProducto(producto);
        detalle.setCantidad(cantidad);
        detalle.setPrecioUnitario(precio);
        detalle.setSubtotal(subtotal);

        dao.registrarDetalle(detalle);
    }

    // =========================
    // MOSTRAR
    // =========================
    void mostrarDetalleVenta() {

        DetalleVentaDAO dao = new DetalleVentaDAO();

        System.out.print("ID Detalle: ");
        int id = Integer.parseInt(sc.nextLine());

        Detalleventa detalle = dao.mostrarDetalle(id);

        if (detalle != null) {
            System.out.println("ID: " + detalle.getIdDetalleVenta());
            System.out.println("Venta: " + detalle.getVenta().getIdVenta());
            System.out.println("Producto: " + detalle.getProducto().getIdProducto());
            System.out.println("Cantidad: " + detalle.getCantidad());
            System.out.println("Precio: " + detalle.getPrecioUnitario());
            System.out.println("Subtotal: " + detalle.getSubtotal());
        } else {
            System.out.println("No encontrado");
        }
    }

    // =========================
    // ACTUALIZAR
    // =========================
    void actualizarDetalleVenta() {

        DetalleVentaDAO dao = new DetalleVentaDAO();

        System.out.print("ID Detalle: ");
        int id = Integer.parseInt(sc.nextLine());

        System.out.print("Nueva cantidad: ");
        int cantidad = Integer.parseInt(sc.nextLine());

        System.out.print("Nuevo precio: ");
        double precio = Double.parseDouble(sc.nextLine());

        double subtotal = cantidad * precio;

        Detalleventa detalle = new Detalleventa();

        detalle.setIdDetalleVenta(id);
        detalle.setCantidad(cantidad);
        detalle.setPrecioUnitario(precio);
        detalle.setSubtotal(subtotal);

        dao.actualizarDetalle(detalle);
    }

    // =========================
    // ELIMINAR
    // =========================
    void eliminarDetalleVenta() {

        DetalleVentaDAO dao = new DetalleVentaDAO();

        System.out.print("ID Detalle: ");
        int id = Integer.parseInt(sc.nextLine());

        dao.eliminarDetalle(id);
    }

    // =========================
    // MAIN
    // =========================
    public static void main(String[] args) {
        sistema sistema = new sistema();
        sistema.menuPrincipal();
    }
}