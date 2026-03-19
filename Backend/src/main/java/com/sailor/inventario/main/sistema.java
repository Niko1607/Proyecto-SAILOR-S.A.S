package com.sailor.inventario.main;

import java.util.Scanner;

import com.sailor.inventario.model.Detalleventa;
/*import com.sailor.inventario.model.Producto;
import com.sailor.inventario.model.Usuario;*/
/*import com.sailor.inventario.model.Proveedor;
import com.sailor.inventario.dao.ProveedorDAO;*/

import com.sailor.inventario.model.Producto;
import com.sailor.inventario.dao.ProductoDAO;

/*import com.sailor.inventario.model.Inventario;
import com.sailor.inventario.model.Producto;
import com.sailor.inventario.dao.InventarioDAO;*/

import com.sailor.inventario.model.Venta;
import com.sailor.inventario.dao.DetalleVentaDAO;


public class sistema {
    void menuPricipal(){
        Scanner sc = new Scanner(System.in);
        int opcion;

        do{
            System.out.println("========== MENU PRINCIPAL ==========");
            System.out.println("1. Menu de Usuario");
            System.out.println("2. Menu de Proveedor");
            System.out.println("3. Menu de Producto");
            System.out.println("4. Menu de Inventario");
            System.out.println("5. Menu de Venta");
            System.out.println("6. Menu de Detalle de Venta");
            System.out.println("Opcion: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion){
                case 1:
                    /*menuUsuario();*/
                    break;
                case 2:
                    /*menuProveedor();*/
                    break;
                case 3:
                    /*menuProducto();*/
                    break;
                case 4:
                    /*menuInventario();*/
                    break;
                case 5:
                    /*menuVenta();*/
                    break;
                case 6:
                    menuDetalleVenta();
                    break;
                default:
                    System.out.println("Opcion invalida");
                    break;
            }
        }while (opcion != 2);

        sc.close();
    }

    /*void menuUsuario(){
        Scanner sc = new Scanner(System.in);
        int opcion;

        do{
            System.out.println("========== MENU DE USUARIO ==========");
            System.out.println("1. Registrar Usuario");
            System.out.println("2. Mostrar Usuario");
            System.out.println("3. Actualizar Usuario");
            System.out.println("4. Eliminar Usuario");
            System.out.println("5. Cambiar Contraseña");
            System.out.println("6. Cambiar Rol");
            System.out.println("7. Cambiar Direccion");
            System.out.println("8. Salir");
            System.out.println("Opcion: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion){
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
                    cambiarContraseña();
                    break;
                case 6:
                    cambiarRol();
                    break;
                case 7:
                    cambiarDireccion();
                    break;
                case 8:
                    System.out.println("Salir");
                default:
                    System.out.println("Opcion invalida");
                    break;
            }
        }while (opcion != 4);

        sc.close();
    }
*/
    /*void menuProveedor(){
        Scanner sc = new Scanner(System.in);
        int opcion;

        do{
            System.out.println("========== MENU DE PROVEEDOR ==========");
            System.out.println("1. Registrar Proveedor");
            System.out.println("2. Mostrar Proveedor");
            System.out.println("3. Actualizar Proveedor");
            System.out.println("4. Eliminar Proveedor");
            System.out.println("5. Cambiar Telefono");
            System.out.println("6. Cambiar Direccion");
            System.out.println("7. Cambiar Correo");
            System.out.println("8. Cambiar Nombre");
            System.out.println("9. Cambiar Identificacion");
            System.out.println("10. Cambiar Tipo de Proveedor");
            System.out.println("11. Cambiar Fecha de Registro");
            System.out.println("12. Cambiar Activo");
            System.out.println("13. Cambiar Ciudad");
            System.out.println("14. Salir");
            System.out.println("Opcion: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion){
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
                    cambiarTelefono();
                    break;
                case 6:
                    cambiarDireccionProveedor();
                    break;
                case 7:
                    cambiarCorreo();
                    break;
                case 8:
                    cambiarNombre();
                    break;
                case 9:
                    cambiarIdentificacion();
                    break;
                case 10:
                    cambiarTipo_de_provedor();
                    break;
                case 11:
                    cambiarFecha_de_registro();
                    break;
                case 12:
                    cambiarActivo();
                    break;
                case 13:
                    cambiarCiudad();
                    break;
                case 14:
                    System.out.println("Salir");
                default:
                    System.out.println("Opcion invalida");
                    break;
            }
        }while (opcion != 4);

        sc.close();
    }
*/
/*     void menuProducto(){
        Scanner sc = new Scanner(System.in);
        int opcion;
        ProductoDAO productoDAO = new ProductoDAO();
        Producto producto = new Producto();
        do{
            System.out.println("========== MENU DE PRODUCTO ==========");
            System.out.println("1. Registrar Producto");
            System.out.println("2. Mostrar Producto");
            System.out.println("3. Actualizar Producto");
            System.out.println("4. Eliminar Producto");
            System.out.println("5. Cambiar Nombre");
            System.out.println("6. Cambiar Precio");
            System.out.println("7. Cambiar Descripcion");
            System.out.println("8. Cambiar Stock");
            System.out.println("9. Cambiar Stock Minimo");
            System.out.println("10. Cambiar Stock Maximo");
            System.out.println("11. Cambiar Fecha de Registro");
            System.out.println("12. Cambiar Activo");
            System.out.println("13. Salir");
            System.out.println("Opcion: ");
            opcion = Integer.parseInt(sc.nextLine());
            switch (opcion){
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
                    cambiarNombre();
                    break;
                case 6:
                    cambiarPrecio();
                    break;
                case 7:
                    cambiarDescripcion();
                    break;
                case 8:
                    cambiarStock();
                    break;
                case 9:
                    cambiarStockMinimo();
                    break;
                case 10:
                    cambiarStockMaximo();
                    break;
                case 11:
                    cambiarFechaDeRegistro();
                    break;
                case 12:
                    cambiarActivo();
                    break;
                case 13:
                    System.out.println("Salir");
                default:
                    System.out.println("Opcion invalida");
                    break;
            }
        }while (opcion != 13);
        sc.close();
    }
*/
    /*void menuInventario(){
        Scanner sc = new Scanner(System.in);
        int opcion;

        do{
            System.out.println("========== MENU DE INVENTARIO ==========");
            System.out.println("1. Registrar Inventario");
            System.out.println("2. Mostrar Inventario");
            System.out.println("3. Actualizar Inventario");
            System.out.println("4. Eliminar Inventario");
            System.out.println("5. Salir");
            System.out.println("Opcion: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion){
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
                    System.out.println("Salir");
                default:
                    System.out.println("Opcion invalida");
                    break;
            }
        }while (opcion != 5);

        sc.close();
    }   
*/
    /*void menuVenta(){
        Scanner sc = new Scanner(System.in);
        int opcion;     
        do{
            System.out.println("========== MENU DE VENTA ==========");
            System.out.println("1. Registrar Venta");
            System.out.println("2. Mostrar Venta");
            System.out.println("3. Actualizar Venta");
            System.out.println("4. Eliminar Venta");
            System.out.println("5. Salir");
            System.out.println("Opcion: ");
            opcion = Integer.parseInt(sc.nextLine());            
            switch (opcion){
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
                    System.out.println("Salir");
                default:
                    System.out.println("Opcion invalida");
                    break;
            }
        }while (opcion != 5);
        sc.close();
    }*/

    void menuDetalleVenta(){
        Scanner sc = new Scanner(System.in);
        int opcion;
        do{
            System.out.println("========== MENU DE DETALLE DE VENTA ==========");
            System.out.println("1. Registrar Detalle de Venta");
            System.out.println("2. Mostrar Detalle de Venta");
            System.out.println("3. Actualizar Detalle de Venta");
            System.out.println("4. Eliminar Detalle de Venta");
            System.out.println("5. Salir");
            System.out.println("Opcion: ");
            opcion = Integer.parseInt(sc.nextLine());
            switch (opcion){
                case 1:
                    registrarDetalleVenta();
                    break;
                case 2:
                    probarMostrarDetalle();
                    break;
                case 3:
                    actualizarDetalleVenta();
                    break;
                case 4:
                    eliminarDetalleVenta();
                    break;
                case 5:
                    System.out.println("Salir");
                default:
                    System.out.println("Opcion invalida");
                    break;
            }
        }while (opcion != 5);
        sc.close();
    }

/*     void registrarProveedor(){
        Scanner sc = new Scanner(System.in);
        ProveedorDAO proveedorDAO = new ProveedorDAO();
        Proveedor proveedor = new Proveedor();

        System.out.println("Ingrese nombre: ");
        proveedor.setNombreProveedorEmpresa(sc.nextLine());

        System.out.println("Ingrese identificacion: ");
        proveedor.setIdentificacion(sc.nextLine());

        System.out.println("Ingrese telefono: ");
        proveedor.setTelefono(sc.nextLine());

        System.out.println("Ingrese direccion: ");
        proveedor.setDireccion(sc.nextLine());

        System.out.println("Ingrese correo: ");
        proveedor.setCorreo(sc.nextLine());
        
        System.out.println("Ingrese tipo de proveedor: ");
        proveedor.setTipoProveedor(sc.nextLine());

        System.out.println("Ingrese ciudad: ");
        proveedor.setCiudad(sc.nextLine());

        System.out.println("Ingrese activo: ");
        proveedor.setActivo(sc.nextBoolean());
        sc.nextLine();

        System.out.println("Ingrese fecha de registro (yyyy-MM-dd): ");
        proveedor.setFechaRegistro(sc.nextLine());

        proveedorDAO.registrarProveedor(proveedor);

        sc.close();
    }

    void mostrarProveedor(){
        Scanner sc = new Scanner(System.in);
        ProveedorDAO proveedorDAO = new ProveedorDAO();

        System.out.println("Ingrese idProveedor: ");
        int idProveedor = Integer.parseInt(sc.nextLine());

        Proveedor proveedor = proveedorDAO.mostrarProveedor(idProveedor);

        System.out.println("Nombre: " + proveedor.getNombreProveedorEmpresa());
        System.out.println("Identificacion: " + proveedor.getIdentificacion());
        System.out.println("Telefono: " + proveedor.getTelefono());
        System.out.println("Direccion: " + proveedor.getDireccion());
        System.out.println("Correo: " + proveedor.getCorreo());
        System.out.println("Tipo de proveedor: " + proveedor.getTipoProveedor());
        System.out.println("Activo: " + proveedor.isActivo());
        System.out.println("Fecha de registro: " + proveedor.getFechaRegistro());
        System.out.println("Ciudad: " + proveedor.getCiudad());

        sc.close();
    }

    void actualizarProveedor(){
        Scanner sc = new Scanner(System.in);
        ProveedorDAO proveedorDAO = new ProveedorDAO();

        System.out.println("Ingrese idProveedor: ");
        int idProveedor = Integer.parseInt(sc.nextLine());

        Proveedor proveedor = proveedorDAO.mostrarProveedor(idProveedor);

        System.out.println("Ingrese nombre o proveedor empresa: ");
        proveedor.setNombreProveedorEmpresa(sc.nextLine());

        System.out.println("Ingrese identificacion: ");
        proveedor.setIdentificacion(sc.nextLine());

        System.out.println("Ingrese email: ");
        proveedor.setCorreo(sc.nextLine());

        System.out.println("Ingrese telefono: ");
        proveedor.setTelefono(sc.nextLine());

        System.out.println("Ingrese direccion: ");
        proveedor.setDireccion(sc.nextLine());

        System.out.println("Ingrese tipo de proveedor: ");
        proveedor.setTipoProveedor(sc.nextLine());

        System.out.println("Ingrese activo: ");
        proveedor.setActivo(sc.nextBoolean());
        sc.nextLine();

        System.out.println("Ingrese fecha de registro: ");
        proveedor.setFechaRegistro(sc.nextLine());

        proveedorDAO.actualizarProveedor(proveedor);

        sc.close();
    }

    void eliminarProveedor(){
        Scanner sc = new Scanner(System.in);
        ProveedorDAO proveedorDAO = new ProveedorDAO();

        System.out.println("Ingrese idProveedor: ");
        int idProveedor = Integer.parseInt(sc.nextLine());

        proveedorDAO.eliminarProveedor(idProveedor);

        sc.close();
    }

    void cambiarTelefono(){
        Scanner sc = new Scanner(System.in);
        ProveedorDAO proveedorDAO = new ProveedorDAO();

        System.out.println("Ingrese idProveedor: ");
        int idProveedor = Integer.parseInt(sc.nextLine());

        System.out.println("Ingrese nuevo telefono: ");
        String telefono = sc.nextLine();

        proveedorDAO.cambiarTelefono(idProveedor, telefono);

        sc.close();
    }

    void cambiarDireccionProveedor(){
        Scanner sc = new Scanner(System.in);
        ProveedorDAO proveedorDAO = new ProveedorDAO();

        System.out.println("Ingrese idProveedor: ");
        int idProveedor = Integer.parseInt(sc.nextLine());

        System.out.println("Ingrese nueva direccion: ");
        String direccion = sc.nextLine();

        proveedorDAO.cambiarDireccionProveedor(idProveedor, direccion);

        sc.close();
    }

    void cambiarCorreo(){
        Scanner sc = new Scanner(System.in);
        ProveedorDAO proveedorDAO = new ProveedorDAO();

        System.out.println("Ingrese idProveedor: ");
        int idProveedor = Integer.parseInt(sc.nextLine());

        System.out.println("Ingrese nuevo correo: ");
        String correo = sc.nextLine();

        proveedorDAO.cambiarCorreo(idProveedor, correo);

        sc.close();
    }

    void cambiarNombre(){
        Scanner sc = new Scanner(System.in);
        ProveedorDAO proveedorDAO = new ProveedorDAO();

        System.out.println("Ingrese idProveedor: ");
        int idProveedor = Integer.parseInt(sc.nextLine());

        System.out.println("Ingrese nuevo nombre: ");
        String nombre = sc.nextLine();

        proveedorDAO.cambiarNombre(idProveedor, nombre);

        sc.close();
    }

    void cambiarIdentificacion(){
        Scanner sc = new Scanner(System.in);
        ProveedorDAO proveedorDAO = new ProveedorDAO();

        System.out.println("Ingrese idProveedor: ");
        int idProveedor = Integer.parseInt(sc.nextLine());

        System.out.println("Ingrese nueva identificacion: ");
        String identificacion = sc.nextLine();

        proveedorDAO.cambiarIdentificacion(idProveedor, identificacion);

        sc.close();
    }

    void cambiarTipo_de_provedor(){
        Scanner sc = new Scanner(System.in);
        ProveedorDAO proveedorDAO = new ProveedorDAO();

        System.out.println("Ingrese idProveedor: ");
        int idProveedor = Integer.parseInt(sc.nextLine());

        System.out.println("Ingrese nuevo tipo de proveedor: ");
        String tipo_de_provedor = sc.nextLine();

        proveedorDAO.cambiarTipo_de_provedor(idProveedor, tipo_de_provedor);

        sc.close();
    }

    void cambiarFecha_de_registro(){
        Scanner sc = new Scanner(System.in);
        ProveedorDAO proveedorDAO = new ProveedorDAO();

        System.out.println("Ingrese idProveedor: ");
        int idProveedor = Integer.parseInt(sc.nextLine());

        System.out.println("Ingrese nueva fecha de registro: ");
        String fecha_de_registro = sc.nextLine();

        proveedorDAO.cambiarFecha_de_registro(idProveedor, fecha_de_registro);

        sc.close();
    }

    void cambiarActivo(){
        Scanner sc = new Scanner(System.in);
        ProveedorDAO proveedorDAO = new ProveedorDAO();

        System.out.println("Ingrese idProveedor: ");
        int idProveedor = Integer.parseInt(sc.nextLine());

        System.out.println("Ingrese nuevo activo: ");
        boolean activo = sc.nextBoolean();

        proveedorDAO.cambiarActivo(idProveedor, activo);

        sc.close();
    }

    void cambiarCiudad(){
        Scanner sc = new Scanner(System.in);
        ProveedorDAO proveedorDAO = new ProveedorDAO();

        System.out.println("Ingrese idProveedor: ");
        int idProveedor = Integer.parseInt(sc.nextLine());

        System.out.println("Ingrese nueva ciudad: ");
        String ciudad = sc.nextLine();

        proveedorDAO.cambiarCiudad(idProveedor, ciudad);

        sc.close();
    }
*/

    /*void registrarProducto(){
        Scanner sc = new Scanner(System.in);
        ProductoDAO productoDAO = new ProductoDAO();
        Producto producto = new Producto();

        System.out.println("Ingrese nombre: ");
        producto.setNombreProducto(sc.nextLine());

        System.out.println("Ingrese descripcion: ");
        producto.setDescripcion(sc.nextLine());

        System.out.println("Ingrese precio: ");
        producto.setPrecioProducto(sc.nextDouble());

        System.out.println("Ingrese cantidad: ");
        producto.setCantidad(sc.nextInt());

        System.out.println("Ingrese stock minimo: ");
        producto.setStockMinimo(sc.nextInt());

        System.out.println("Ingrese stock maximo: ");
        producto.setStockMaximo(sc.nextInt());
        sc.nextLine();

        System.out.println("Ingrese fecha de registro (yyyy-MM-dd): ");
        producto.setFechaRegistro(sc.nextLine());

        System.out.println("Ingrese activo: ");
        producto.setActivo(sc.nextBoolean());
        sc.nextLine();

        productoDAO.registrarProducto(producto);

        sc.close();
    }

    void mostrarProducto(){
        Scanner sc = new Scanner(System.in);
        ProductoDAO productoDAO = new ProductoDAO();

        System.out.println("Ingrese idProducto: ");
        int idProducto = Integer.parseInt(sc.nextLine());

        Producto producto = productoDAO.mostrarProducto(idProducto);

        System.out.println("Nombre: " + producto.getNombreProducto());
        System.out.println("Descripcion: " + producto.getDescripcion());
        System.out.println("Precio: " + producto.getPrecioProducto());
        System.out.println("Cantidad: " + producto.getCantidad());
        System.out.println("Stock minimo: " + producto.getStockMinimo());
        System.out.println("Stock maximo: " + producto.getStockMaximo());
        System.out.println("Fecha de registro: " + producto.getFechaRegistro());
        System.out.println("Activo: " + producto.isActivo());

        sc.close();
    }

    void actualizarProducto(){
        Scanner sc = new Scanner(System.in);
        ProductoDAO productoDAO = new ProductoDAO();

        System.out.println("Ingrese idProducto: ");
        int idProducto = Integer.parseInt(sc.nextLine());

        Producto producto = productoDAO.mostrarProducto(idProducto);

        System.out.println("Ingrese nombre o producto: ");
        producto.setNombreProducto(sc.nextLine());

        System.out.println("Ingrese descripcion: ");
        producto.setDescripcion(sc.nextLine());

        System.out.println("Ingrese precio: ");
        producto.setPrecioProducto(sc.nextDouble());

        System.out.println("Ingrese cantidad: ");
        producto.setCantidad(sc.nextInt());

        System.out.println("Ingrese stock minimo: ");
        producto.setStockMinimo(sc.nextInt());

        System.out.println("Ingrese stock maximo: ");
        producto.setStockMaximo(sc.nextInt());
        sc.nextLine();

        System.out.println("Ingrese fecha de registro: ");
        producto.setFechaRegistro(sc.nextLine());

        System.out.println("Ingrese activo: ");
        producto.setActivo(sc.nextBoolean());

        productoDAO.actualizarProducto(producto);

        sc.close();
    }

    void eliminarProducto(){
        Scanner sc = new Scanner(System.in);
        ProductoDAO productoDAO = new ProductoDAO();

        System.out.println("Ingrese idProducto: ");
        int idProducto = Integer.parseInt(sc.nextLine());

        productoDAO.eliminarProducto(idProducto);

        sc.close();
    }

    void cambiarNombre(){
        Scanner sc = new Scanner(System.in);
        ProductoDAO productoDAO = new ProductoDAO();

        System.out.println("Ingrese idProducto: ");
        int idProducto = Integer.parseInt(sc.nextLine());

        System.out.println("Ingrese nuevo nombre: ");
        String nombreProducto = sc.nextLine();

        productoDAO.cambiarNombre(idProducto, nombreProducto);

        sc.close();
    }

    void cambiarPrecio(){
        Scanner sc = new Scanner(System.in);
        ProductoDAO productoDAO = new ProductoDAO();

        System.out.println("Ingrese idProducto: ");
        int idProducto = Integer.parseInt(sc.nextLine());

        System.out.println("Ingrese nuevo precio: ");
        double precioProducto = sc.nextDouble();

        productoDAO.cambiarPrecio(idProducto, precioProducto);

        sc.close();
    }

    void cambiarDescripcion(){
        Scanner sc = new Scanner(System.in);
        ProductoDAO productoDAO = new ProductoDAO();

        System.out.println("Ingrese idProducto: ");
        int idProducto = Integer.parseInt(sc.nextLine());

        System.out.println("Ingrese nueva descripcion: ");
        String descripcion = sc.nextLine();

        productoDAO.cambiarDescripcion(idProducto, descripcion);

        sc.close();
    }

    void cambiarStock(){
        Scanner sc = new Scanner(System.in);
        ProductoDAO productoDAO = new ProductoDAO();

        System.out.println("Ingrese idProducto: ");
        int idProducto = Integer.parseInt(sc.nextLine());

        System.out.println("Ingrese nuevo stock: ");
        int cantidad = Integer.parseInt(sc.nextLine());

        productoDAO.cambiarStock(idProducto, cantidad);

        sc.close();
    }

    void cambiarStockMinimo(){
        Scanner sc = new Scanner(System.in);
        ProductoDAO productoDAO = new ProductoDAO();

        System.out.println("Ingrese idProducto: ");
        int idProducto = Integer.parseInt(sc.nextLine());

        System.out.println("Ingrese nuevo stock minimo: ");
        int stockMinimo = Integer.parseInt(sc.nextLine());

        productoDAO.cambiarStockMinimo(idProducto, stockMinimo);

        sc.close();
    }

    void cambiarStockMaximo(){
        Scanner sc = new Scanner(System.in);
        ProductoDAO productoDAO = new ProductoDAO();

        System.out.println("Ingrese idProducto: ");
        int idProducto = Integer.parseInt(sc.nextLine());

        System.out.println("Ingrese nuevo stock maximo: ");
        int stockMaximo = Integer.parseInt(sc.nextLine());

        productoDAO.cambiarStockMaximo(idProducto, stockMaximo);

        sc.close();
    }

    void cambiarFechaDeRegistro(){
        Scanner sc = new Scanner(System.in);
        ProductoDAO productoDAO = new ProductoDAO();

        System.out.println("Ingrese idProducto: ");
        int idProducto = Integer.parseInt(sc.nextLine());

        System.out.println("Ingrese nueva fecha de registro: ");
        String fechaRegistro = sc.nextLine();

        productoDAO.cambiarFechaDeRegistro(idProducto, fechaRegistro);

        sc.close();
    }

    void cambiarActivo(){
        Scanner sc = new Scanner(System.in);
        ProductoDAO productoDAO = new ProductoDAO();

        System.out.println("Ingrese idProducto: ");
        int idProducto = Integer.parseInt(sc.nextLine());

        System.out.println("Ingrese nuevo activo: ");
        boolean activo = sc.nextBoolean();

        productoDAO.cambiarActivo(idProducto, activo);

        sc.close();
    }
*/
 /*    void registrarInventario(){

        Scanner sc = new Scanner(System.in);
        InventarioDAO inventarioDAO = new InventarioDAO();

        Inventario inventario = new Inventario();

        System.out.println("Ingrese idProducto: ");
        int idProducto = Integer.parseInt(sc.nextLine());

        System.out.println("Ingrese idUsuario: ");
        int idUsuario = Integer.parseInt(sc.nextLine());

        System.out.println("Ingrese cantidad: ");
        int cantidad = Integer.parseInt(sc.nextLine());

        System.out.println("Ingrese fecha: ");
        String fecha = sc.nextLine();

        // Crear producto
        Producto producto = new Producto();
        producto.setIdProducto(idProducto);

        // Crear usuario
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(idUsuario);

        sc.close();

        // Asignar al inventario
        inventario.setProducto(producto);
        inventario.setUsuario(usuario);
        inventario.setCantidad(cantidad);
        inventario.setFechaMovimiento(fecha);

        inventarioDAO.registrarInventario(inventario);
    }
    void actualizarInventario(){

        Scanner sc = new Scanner(System.in);
        InventarioDAO inventarioDAO = new InventarioDAO();

        System.out.println("Ingrese idInventario: ");
        int idInventario = Integer.parseInt(sc.nextLine());

        System.out.println("Ingrese cantidad: ");
        int cantidad = Integer.parseInt(sc.nextLine());

        System.out.println("Ingrese fecha: ");
        String fecha = sc.nextLine();

        // Crear objeto inventario
        Inventario inventario = new Inventario();

        inventario.setIdInventario(idInventario);
        inventario.setCantidad(cantidad);
        inventario.setFechaMovimiento(fecha);

        // enviar al DAO
        inventarioDAO.actualizarInventario(inventario);

        sc.close();
    }   

    void eliminarInventario(){
        Scanner sc = new Scanner(System.in);
        InventarioDAO inventarioDAO = new InventarioDAO();

        System.out.println("Ingrese idInventario: ");
        int idInventario = Integer.parseInt(sc.nextLine());

        inventarioDAO.eliminarInventario(idInventario);

        sc.close();
    }       

    void mostrarInventario(){
        Scanner sc = new Scanner(System.in);
        InventarioDAO inventarioDAO = new InventarioDAO();

        System.out.println("Ingrese idInventario: ");
        int idInventario = Integer.parseInt(sc.nextLine());

        Inventario inventario = inventarioDAO.mostrarInventario(idInventario);

        System.out.println("Cantidad: " + inventario.getCantidad());
        System.out.println("Fecha: " + inventario.getFechaMovimiento());

        sc.close();
    }
*/
    /*void registrarVenta() {

        Scanner sc = new Scanner(System.in);
        VentaDAO ventaDAO = new VentaDAO();
        Venta venta = new Venta();

        System.out.println("Ingrese idUsuario:");
        int idUsuario = Integer.parseInt(sc.nextLine());

        System.out.println("Ingrese fecha:");
        String fecha = sc.nextLine();

        System.out.println("Ingrese total:");
        double total = Double.parseDouble(sc.nextLine());

        System.out.println("Ingrese estado (true/false):");
        boolean estado = Boolean.parseBoolean(sc.nextLine());

        venta.setIdUsuario(idUsuario);
        venta.setFecha(fecha);
        venta.setTotal(total);
        venta.setEstado(estado);

        ventaDAO.registrarVenta(venta);
        sc.close();
    } 

    void mostrarVenta() {

        Scanner sc = new Scanner(System.in);
        VentaDAO ventaDAO = new VentaDAO();

        System.out.println("Ingrese idVenta:");
        int idVenta = Integer.parseInt(sc.nextLine());

        Venta venta = ventaDAO.mostrarVenta(idVenta);

        if (venta != null) {
            System.out.println("ID Venta: " + venta.getIdVenta());
            System.out.println("ID Usuario: " + venta.getIdUsuario());
            System.out.println("Fecha: " + venta.getFecha());
            System.out.println("Total: " + venta.getTotal());
            System.out.println("Estado: " + venta.isEstado());
        } else {
            System.out.println("Venta no encontrada");
        }
        sc.close();
    }

    void actualizarVenta() {

        Scanner sc = new Scanner(System.in);
        VentaDAO ventaDAO = new VentaDAO();

        System.out.println("Ingrese idVenta:");
        int idVenta = Integer.parseInt(sc.nextLine());

        Venta venta = ventaDAO.mostrarVenta(idVenta);

        if (venta == null) {
            System.out.println("Venta no encontrada");
            return;
        }

        System.out.println("Nueva fecha:");
        String fecha = sc.nextLine();

        System.out.println("Nuevo total:");
        double total = Double.parseDouble(sc.nextLine());

        venta.setFecha(fecha);
        venta.setTotal(total);

        ventaDAO.actualizarVenta(venta);
        sc.close();
    }

    void eliminarVenta() {

        Scanner sc = new Scanner(System.in);
        VentaDAO ventaDAO = new VentaDAO();

        System.out.println("Ingrese idVenta:");
        int idVenta = Integer.parseInt(sc.nextLine());

        ventaDAO.eliminarVenta(idVenta);
        sc.close();
    }*/

    void registrarDetalleVenta(){

        Scanner sc = new Scanner(System.in);

        DetalleVentaDAO detalleDAO = new DetalleVentaDAO();
        Detalleventa detalle = new Detalleventa();

        Venta venta = new Venta();
        Producto producto = new Producto();

        System.out.println("Ingrese idVenta:");
        int idVenta = Integer.parseInt(sc.nextLine());

        System.out.println("Ingrese idProducto:");
        int idProducto = Integer.parseInt(sc.nextLine());

        System.out.println("Cantidad:");
        int cantidad = Integer.parseInt(sc.nextLine());

        System.out.println("Precio Unitario:");
        double precio = Double.parseDouble(sc.nextLine());

        double subtotal = cantidad * precio;

        venta.setIdVenta(idVenta);
        producto.setIdProducto(idProducto);

        detalle.setVenta(venta);
        detalle.setProducto(producto);
        detalle.setCantidad(cantidad);
        detalle.setPrecioUnitario(precio);
        detalle.setSubTotal(subtotal);

        detalleDAO.registrarDetalle(detalle);
        sc.close();
    }

    void actualizarDetalleVenta(){

        Scanner sc = new Scanner(System.in);

        DetalleVentaDAO detalleDAO = new DetalleVentaDAO();

        System.out.println("Ingrese idDetalleVenta:");
        int idDetalle = Integer.parseInt(sc.nextLine());

        detalleDAO.mostrarDetalle(idDetalle);

        System.out.println("Nueva cantidad:");
        int cantidad = Integer.parseInt(sc.nextLine());

        System.out.println("Nuevo precio unitario:");
        double precio = Double.parseDouble(sc.nextLine());

        double subtotal = cantidad * precio;

        Detalleventa detalle = new Detalleventa();

        detalle.setIdDetalleVenta(idDetalle);
        detalle.setCantidad(cantidad);
        detalle.setPrecioUnitario(precio);
        detalle.setSubTotal(subtotal);

        detalleDAO.actualizarDetalle(detalle);
        sc.close();
    }

    void probarMostrarDetalle() {

        Scanner sc = new Scanner(System.in);
        DetalleVentaDAO detalleDAO = new DetalleVentaDAO();

        System.out.println("Ingrese idDetalleVenta:");
        int id = Integer.parseInt(sc.nextLine());

        Detalleventa detalle = detalleDAO.mostrarDetalle(id);

        if (detalle != null) {

            System.out.println("ID Detalle: " + detalle.getIdDetalleVenta());
            System.out.println("ID Venta: " + detalle.getVenta().getIdVenta());
            System.out.println("ID Producto: " + detalle.getProducto().getIdProducto());
            System.out.println("Cantidad: " + detalle.getCantidad());
            System.out.println("Precio Unitario: " + detalle.getPrecioUnitario());
            System.out.println("Subtotal: " + detalle.getSubTotal());

        } else {

            System.out.println("Detalle no encontrado");

        }
    }

    void eliminarDetalleVenta(){

        Scanner sc = new Scanner(System.in);

        DetalleVentaDAO detalleDAO = new DetalleVentaDAO();

        System.out.println("Ingrese idDetalleVenta:");
        int id = Integer.parseInt(sc.nextLine());

        detalleDAO.eliminarDetalle(id);
        sc.close();
    }

    public static void main(String[] args) {
        sistema sistema = new sistema();
        sistema.menuPricipal();

    }

}