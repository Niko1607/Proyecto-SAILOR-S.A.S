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
            System.out.println("2. Menu Usuario");
            System.out.println("3. Menu Proveedor");
            System.out.println("4. Menu Producto");
            System.out.println("5. Menu Inventario");
            System.out.println("6. Registrar Venta");
            System.out.println("7. Menu Detalle Venta");
            System.out.println("8. Salir");
            System.out.print("Opcion: ");

            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1:
                    login();
                    break;
                case 2:
                    menuUsuario();
                    break;
                case 3:
                    menuProveedor();
                    break;
                case 4:
                    menuProducto();
                    break;
                case 5:
                    menuInventario();
                    break;
                case 6:
                    menuVenta();
                    break;
                case 7:
                    menuDetalleVenta();
                    break;
                case 8:
                    System.out.println("Saliendo...");
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

        System.out.println("Rol de usuario: ");
        String rol = sc.nextLine();

        Usuario usuario = usuarioDAO.login(correo, password);

        if (usuario != null) {
            System.out.println("✅ Login exitoso - Bienvenido " + usuario.getNombre());
        } else {
            System.out.println("❌ Credenciales incorrectas");
        }

        return usuario;
    }

    // =========================
    // MENU USUARIO y void DE TEST DE FUNCIONAMIENTO 
    // =========================

    void menuUsuario() {
        int opcion;
        do {
            System.out.println("\n====== MENU USUARIO ======");
            System.out.println("1. Registrar");
            System.out.println("2. Mostrar");
            System.out.println("3. Actualizar");
            System.out.println("4. Eliminar");
            System.out.println("5. Volver");
            System.out.print("Opcion: ");
            opcion = Integer.parseInt(sc.nextLine());
            switch (opcion) {
                case 1:
                    registrarUsuario();
                    break;
                case 2:
                    mostrarUsuario();
                    break;
                case 3:
                    actualizarUsuario();
                    break;
                case 4:
                    eliminarUsuario();
                    break;
                case 5:
                    System.out.println("Volviendo...");
                    break;
                default:
                    System.out.println("Opcion invalida");
            }
        } while (opcion != 5);
    }

    void registrarUsuario() {

        UsuarioDAO dao = new UsuarioDAO();
        Usuario usuario = new Usuario();

        System.out.println("\n=== REGISTRAR USUARIO ===");

        System.out.print("Nombre: ");
        usuario.setNombre(sc.nextLine());

        System.out.print("Apellido: ");
        usuario.setApellido(sc.nextLine());

        System.out.print("Identificacion: ");
        usuario.setIdentificacion(sc.nextLine());

        System.out.print("Correo: ");
        usuario.setCorreo(sc.nextLine());

        System.out.print("Password: ");
        usuario.setPassword(sc.nextLine());

        System.out.print("Rol: ");
        usuario.setRol(sc.nextLine());

        System.out.print("Direccion: ");
        usuario.setDireccion(sc.nextLine());

        // 🔥 AQUÍ SE ENVÍA AL DAO
        dao.insertarUsuario(usuario);
    }

    void mostrarUsuario() {
        UsuarioDAO dao = new UsuarioDAO();
        System.out.print("ID Usuario: ");
        int id = Integer.parseInt(sc.nextLine());
        Usuario usuario = dao.mostrarUsuario(id);
        if (usuario != null) {
            System.out.println("ID: " + usuario.getIdUsuario());
            System.out.println("Correo: " + usuario.getCorreo());
            System.out.println("Nombre: " + usuario.getNombre());
            System.out.println("Identificacion: " + usuario.getIdentificacion());
            System.out.println("Apellido: " + usuario.getApellido());
            System.out.println("Rol: " + usuario.getRol());
            System.out.println("Direccion: " + usuario.getDireccion());
        } else {
            System.out.println("No encontrado");
        }
    }

    void actualizarUsuario() {
        UsuarioDAO dao = new UsuarioDAO();

        System.out.print("\n=== ACTUALIZAR USUARIO ===");

        System.out.print("ID Usuario: ");
        int id = Integer.parseInt(sc.nextLine());

        Usuario usuario = new Usuario();
        usuario.setIdUsuario(id);

        System.out.print("Nuevo nombre: ");
        usuario.setNombre(sc.nextLine());

        System.out.print("Nuevo apellido: ");
        usuario.setApellido(sc.nextLine());

        System.out.print("Nuevo identificacion: ");
        usuario.setIdentificacion(sc.nextLine());

        System.out.print("Nuevo correo: ");
        usuario.setCorreo(sc.nextLine());

        System.out.print("Nuevo password: ");
        usuario.setPassword(sc.nextLine());

        System.out.print("Nuevo rol: ");
        usuario.setRol(sc.nextLine());

        System.out.print("Nueva direccion: ");
        usuario.setDireccion(sc.nextLine());

        dao.actualizarUsuario(usuario);
    }

    void eliminarUsuario() {
        UsuarioDAO dao = new UsuarioDAO();
        System.out.print("ID Usuario: ");
        int id = Integer.parseInt(sc.nextLine());
        dao.eliminarUsuario(id);
    }

    // =========================
    // MENU PROVEEDOR y VOID DE TEST DE FUNCIONAMIENTO
    // =========================

    void menuProveedor(){
        int opcion;
        do {
            System.out.println("\n====== MENU PROVEEDOR ======");
            System.out.println("1. Registrar");
            System.out.println("2. Mostrar");
            System.out.println("3. Actualizar");
            System.out.println("4. Eliminar");
            System.out.println("5. Volver");
            System.out.print("Opcion: ");
            opcion = Integer.parseInt(sc.nextLine());
            switch (opcion) {
                case 1:
                    registrarProveedor();
                    break;
                case 2:
                    mostrarProveedor();
                    break;
                case 3:
                    actualizarProveedor();
                    break;
                case 4:
                    eliminarProveedor();
                    break;
                case 5:
                    System.out.println("Volviendo...");
                    break;
                default:
                    System.out.println("Opcion invalida");
            }
        } while (opcion != 5);
    }

    void registrarProveedor() {

        ProveedorDAO dao = new ProveedorDAO();
        Proveedor proveedor = new Proveedor();

        System.out.print("Nombre empresa: ");
        proveedor.setNombreProveedorEmpresa(sc.nextLine());

        System.out.print("Identificacion: ");
        proveedor.setIdentificacion(sc.nextLine());

        System.out.print("Telefono: ");
        proveedor.setTelefono(sc.nextLine());

        System.out.print("Direccion: ");
        proveedor.setDireccion(sc.nextLine());

        System.out.print("Correo: ");
        proveedor.setCorreo(sc.nextLine());

        System.out.print("Tipo proveedor: ");
        proveedor.setTipoProveedor(sc.nextLine());

        System.out.print("Ciudad: ");
        proveedor.setCiudad(sc.nextLine());

        System.out.print("Activo (true/false): ");
        proveedor.setActivo(Boolean.parseBoolean(sc.nextLine()));

        System.out.print("Fecha (yyyy-MM-dd): ");
        proveedor.setFechaRegistro(sc.nextLine());

        dao.registrarProveedor(proveedor);
    }

    void mostrarProveedor() {

        ProveedorDAO dao = new ProveedorDAO();

        System.out.print("ID Proveedor: ");
        int id = Integer.parseInt(sc.nextLine());

        Proveedor proveedor = dao.mostrarProveedor(id);

        if (proveedor != null) {
            System.out.println("ID: " + proveedor.getIdProveedor());
            System.out.println("Nombre: " + proveedor.getNombreProveedorEmpresa());
            System.out.println("Identificacion: " + proveedor.getIdentificacion());
            System.out.println("Telefono: " + proveedor.getTelefono());
            System.out.println("Direccion: " + proveedor.getDireccion());
            System.out.println("Correo: " + proveedor.getCorreo());
            System.out.println("Tipo: " + proveedor.getTipoProveedor());
            System.out.println("Activo: " + proveedor.isActivo());
            System.out.println("Ciudad: " + proveedor.getCiudad());
            System.out.println("Fecha: " + proveedor.getFechaRegistro());
        } else {
            System.out.println("No encontrado");
        }
    } 

    void actualizarProveedor() {

        ProveedorDAO dao = new ProveedorDAO();
        
        System.out.print("ID Proveedor: ");
        int id = Integer.parseInt(sc.nextLine());   

        Proveedor proveedor = new Proveedor();

        proveedor.setIdProveedor(id);

        System.out.print("Nuevo nombre empresa: ");
        proveedor.setNombreProveedorEmpresa(sc.nextLine());

        System.out.print("Nueva identificacion: ");
        proveedor.setIdentificacion(sc.nextLine());

        System.out.print("Nuevo telefono: ");
        proveedor.setTelefono(sc.nextLine());

        System.out.print("Nueva direccion: ");
        proveedor.setDireccion(sc.nextLine());

        System.out.print("Nuevo correo: ");
        proveedor.setCorreo(sc.nextLine());

        System.out.print("Nuevo tipo proveedor: ");
        proveedor.setTipoProveedor(sc.nextLine());

        System.out.print("Nuevo activo (true/false): ");
        proveedor.setActivo(Boolean.parseBoolean(sc.nextLine()));

        System.out.print("Nueva ciudad: ");
        proveedor.setCiudad(sc.nextLine());

        System.out.print("Nueva fecha (dd/MM/yyyy): ");
        proveedor.setFechaRegistro(sc.nextLine());

        dao.actualizarProveedor(proveedor);
    }

    void eliminarProveedor() {

        ProveedorDAO dao = new ProveedorDAO();

        System.out.print("ID Proveedor: ");
        int id = Integer.parseInt(sc.nextLine());

        dao.eliminarProveedor(id);
    }

    // =========================
    // MENU PRODUCTO y VOID DE TEST DE FUNCIONAMIENTO
    // =========================

    void menuProducto(){
        int opcion;
        do {
            System.out.println("\n====== MENU PRODUCTO ======");
            System.out.println("1. Registrar");
            System.out.println("2. Mostrar");
            System.out.println("3. Actualizar");
            System.out.println("4. Eliminar");
            System.out.println("5. Volver");
            System.out.print("Opcion: ");
            opcion = Integer.parseInt(sc.nextLine());
            switch (opcion) {
                case 1:
                    registrarProducto();
                    break;
                case 2:
                    mostrarProducto();
                    break;
                case 3:
                    actualizarProducto();
                    break;
                case 4:
                    eliminarProducto();
                    break;
                case 5:
                    System.out.println("Volviendo...");
                    break;
                default:
                    System.out.println("Opcion invalida");
            }
        } while (opcion != 5);
    }

    void registrarProducto() {
        ProductoDAO dao = new ProductoDAO();
        Producto producto = new Producto();

        System.out.print("Nombre producto: ");
        producto.setNombreProducto(sc.nextLine());

        System.out.print("Descripcion: ");
        producto.setDescripcion(sc.nextLine());

        System.out.print("Precio producto: ");
        producto.setPrecioProducto(Double.parseDouble(sc.nextLine()));

        System.out.print("Cantidad: ");
        producto.setCantidad(Integer.parseInt(sc.nextLine()));

        System.out.print("Stock minimo: ");
        producto.setStockMinimo(Integer.parseInt(sc.nextLine()));

        System.out.print("Stock maximo: ");
        producto.setStockMaximo(Integer.parseInt(sc.nextLine()));

        System.out.print("Fecha (dd/MM/yyyy): ");
        producto.setFechaRegistro(sc.nextLine());

        System.out.print("Activo (true/false): ");
        producto.setActivo(Boolean.parseBoolean(sc.nextLine()));

        dao.registrarProducto(producto);
    }
    
    void mostrarProducto() {

        ProductoDAO dao = new ProductoDAO();

        System.out.print("ID Producto: ");
        int id = Integer.parseInt(sc.nextLine());

        Producto producto = dao.mostrarProducto(id);

        if (producto != null) {
            System.out.println("ID: " + producto.getIdProducto());
            System.out.println("Nombre: " + producto.getNombreProducto());
            System.out.println("Descripcion: " + producto.getDescripcion());
            System.out.println("Precio: " + producto.getPrecioProducto());
            System.out.println("Cantidad: " + producto.getCantidad());
            System.out.println("Stock minimo: " + producto.getStockMinimo());
            System.out.println("Stock maximo: " + producto.getStockMaximo());
            System.out.println("Fecha: " + producto.getFechaRegistro());
            System.out.println("Activo: " + producto.isActivo());
        } else {
            System.out.println("No encontrado");
        }
    }

    void actualizarProducto() {
        ProductoDAO dao = new ProductoDAO();

        System.out.print("ID Producto: ");
        int id = Integer.parseInt(sc.nextLine());

        Producto producto = new Producto();
        producto.setIdProducto(id);

        System.out.print("Nuevo nombre producto: ");
        producto.setNombreProducto(sc.nextLine());

        System.out.print("Nueva descripcion: ");
        producto.setDescripcion(sc.nextLine());

        System.out.print("Nuevo precio producto: ");
        producto.setPrecioProducto(Double.parseDouble(sc.nextLine()));

        System.out.print("Nueva cantidad: ");
        producto.setCantidad(Integer.parseInt(sc.nextLine()));

        System.out.print("Nuevo stock minimo: ");
        producto.setStockMinimo(Integer.parseInt(sc.nextLine()));

        System.out.print("Nuevo stock maximo: ");
        producto.setStockMaximo(Integer.parseInt(sc.nextLine()));

        System.out.print("Nueva fecha (dd/MM/yyyy): ");
        producto.setFechaRegistro(sc.nextLine());

        System.out.print("Nuevo activo (true/false): ");
        producto.setActivo(Boolean.parseBoolean(sc.nextLine()));

        dao.actualizarProducto(producto);
    }

    void eliminarProducto() {

        ProductoDAO dao = new ProductoDAO();

        System.out.print("ID Producto: ");
        int id = Integer.parseInt(sc.nextLine());

        dao.eliminarProducto(id);
    }
    
    // =========================
    // MENU INVENTARIO y VOID DE TEST DE FUNCIONAMIENTO
    // =========================

    void menuInventario(){
        int opcion;
        do {
            System.out.println("\n====== MENU INVENTARIO ======");
            System.out.println("1. Registrar");
            System.out.println("2. Mostrar");
            System.out.println("3. Actualizar");
            System.out.println("4. Eliminar");
            System.out.println("5. Volver");
            System.out.print("Opcion: ");
            opcion = Integer.parseInt(sc.nextLine());
            switch (opcion) {
                case 1:
                    registrarInventario();
                    break;
                case 2:
                    mostrarInventario();
                    break;
                case 3:
                    actualizarInventario();
                    break;
                case 4:
                    eliminarInventario();
                    break;
                case 5:
                    System.out.println("Volviendo...");
                    break;
                default:
                    System.out.println("Opcion invalida");
            }
        } while (opcion != 5); 
    }

    void registrarInventario() {
        InventarioDAO dao = new InventarioDAO();
        Inventario inventario = new Inventario();

        System.out.print("ID Producto: ");
        int idProducto = Integer.parseInt(sc.nextLine());

        System.out.print("ID Usuario: ");
        int idUsuario = Integer.parseInt(sc.nextLine());

        System.out.print("Cantidad: ");
        int cantidad = Integer.parseInt(sc.nextLine());

        System.out.print("Fecha (dd/MM/yyyy): ");
        String fechaMovimiento = sc.nextLine();

        inventario.setProducto(new Producto());
        inventario.setUsuario(new Usuario());

        inventario.getProducto().setIdProducto(idProducto);
        inventario.getUsuario().setIdUsuario(idUsuario);
        inventario.setCantidad(cantidad);
        inventario.setFechaMovimiento(fechaMovimiento);

        dao.registrarInventario(inventario);
    }

    void mostrarInventario() {

        InventarioDAO dao = new InventarioDAO();

        System.out.print("ID Inventario: ");
        int id = Integer.parseInt(sc.nextLine());

        Inventario inventario = dao.mostrarInventario(id);

        if (inventario != null) {
            System.out.println("ID: " + inventario.getIdInventario());
            System.out.println("Cantidad: " + inventario.getCantidad());
            System.out.println("Fecha: " + inventario.getFechaMovimiento());
        } else {
            System.out.println("No encontrado");
        }
    }

    void actualizarInventario() {

        InventarioDAO dao = new InventarioDAO();

        System.out.print("ID Inventario: ");
        int id = Integer.parseInt(sc.nextLine());

        System.out.print("Nueva cantidad: ");
        int cantidad = Integer.parseInt(sc.nextLine());

        System.out.print("Nueva fecha (dd/MM/yyyy): ");
        String fechaMovimiento = sc.nextLine();

        Inventario inventario = new Inventario();
        inventario.setIdInventario(id);
        inventario.setCantidad(cantidad);
        inventario.setFechaMovimiento(fechaMovimiento);

        dao.actualizarInventario(inventario);
    }

    void eliminarInventario() {

        InventarioDAO dao = new InventarioDAO();

        System.out.print("ID Inventario: ");
        int id = Integer.parseInt(sc.nextLine());

        dao.eliminarInventario(id);
    }

    // =========================
    // MENU VENTA y VOID DE TEST DE FUNCIONAMIENTO
    // =========================
    void menuVenta (){
        int opcion;
        do {
            System.out.println("\n====== MENU VENTA ======");
            System.out.println("1. Registrar");
            System.out.println("2. Mostrar");
            System.out.println("3. Actualizar");
            System.out.println("4. Eliminar");
            System.out.println("5. Volver");
            System.out.print("Opcion: ");
            opcion = Integer.parseInt(sc.nextLine());
            switch (opcion) {
                case 1:
                    registrarVenta();
                    break;
                case 2:
                    mostrarVenta();
                    break;
                case 3:
                    actualizarVenta();
                    break;
                case 4:
                    eliminarVenta();
                    break;
                case 5:
                    System.out.println("Volviendo...");
                    break;
                default:
                    System.out.println("Opcion invalida");
            }
        } while (opcion != 5);
    }

    void registrarVenta() {
        VentaDAO dao = new VentaDAO();
        Venta venta = new Venta();

        System.out.print("ID Usuario: ");
        int idUsuario = Integer.parseInt(sc.nextLine());

        System.out.print("Fecha (dd/MM/yyyy): ");
        String fecha = sc.nextLine();

        System.out.print("Estado (true/false): ");
        boolean estado = Boolean.parseBoolean(sc.nextLine());

        System.out.print("Precio total: ");
        double total = Double.parseDouble(sc.nextLine());

        venta.setIdUsuario(idUsuario);
        venta.setFecha(fecha);
        venta.setEstado(estado);
        venta.setTotal(total);

        dao.registrarVenta(venta);
    }

    void mostrarVenta() {

        VentaDAO dao = new VentaDAO();

        System.out.print("ID Venta: ");
        int id = Integer.parseInt(sc.nextLine());

        Venta venta = dao.mostrarVenta(id);

        if (venta != null) {
            System.out.println("ID: " + venta.getIdVenta());
            System.out.println("ID Usuario: " + venta.getIdUsuario());
            System.out.println("Fecha: " + venta.getFecha());
            System.out.println("Estado: " + venta.isEstado());
            System.out.println("Precio total: " + venta.getTotal());
        } else {
            System.out.println("No encontrado");
        }
    }

    void actualizarVenta() {

        VentaDAO dao = new VentaDAO();

        System.out.print("ID Venta: ");
        int id = Integer.parseInt(sc.nextLine());

        System.out.print("Nuevo estado (true/false): ");
        boolean estado = Boolean.parseBoolean(sc.nextLine());

        System.out.print("Nuevo precio total: ");
        double total = Double.parseDouble(sc.nextLine());

        Venta venta = new Venta();
        venta.setIdVenta(id);
        venta.setEstado(estado);
        venta.setTotal(total);

        dao.actualizarVenta(venta);
    }

    void eliminarVenta() {

        VentaDAO dao = new VentaDAO();

        System.out.print("ID Venta: ");
        int id = Integer.parseInt(sc.nextLine());

        dao.eliminarVenta(id);  
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