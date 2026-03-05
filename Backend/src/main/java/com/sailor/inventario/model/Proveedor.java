package src.main.java.com.sailor.inventario.model;
import src.main.java.com.sailor.inventario.dao.ProveedorDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Proveedor {
    private String nombre;
    private String empresa;
    private List<String> productosSuministrados;

    public Proveedor(String nombre, String empresa) {
        this.nombre = nombre;
        this.empresa = empresa;
        this.productosSuministrados = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getEmpresa() {
        return empresa;
    }
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
    public List<String> getProductosSuministrados() {
        return productosSuministrados;
    }
    public void setProductosSuministrados(List<String> productosSuministrados) {
        this.productosSuministrados = productosSuministrados;
    }

    public void registrar_proveedor() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el nombre del proveedor: ");
        setNombre(sc.nextLine());
        System.out.println("Ingrese la empresa del proveedor: ");
        setEmpresa(sc.nextLine());
    }

    public void listar_productos() {
        if (productosSuministrados.isEmpty()) {
            System.out.println("No hay productos registrados para este proveedor.");
        } else {
            System.out.println("Productos suministrados por " + nombre + ":");
            for (String producto : productosSuministrados) {
                System.out.println("- " + producto);
            }
        }
    }

    public void agregar_producto(String producto) {
        productosSuministrados.add(producto);
    }

    public static void main(String[] args) {
    Proveedor proveedor = new Proveedor("", "");
    proveedor.registrar_proveedor();

    Scanner sc = new Scanner(System.in);
    String opcion;
    do {
        System.out.println("¿Desea agregar un producto suministrado? (s/n): ");
        opcion = sc.nextLine();
        if (opcion.equalsIgnoreCase("s")) {
            System.out.print("Nombre del producto: ");
            String producto = sc.nextLine();
            proveedor.agregar_producto(producto);
        }
    } while (opcion.equalsIgnoreCase("s"));

    ProveedorDAO dao = new ProveedorDAO();
    dao.registrar_proveedor( proveedor);
    dao.listarProveedores();
    }
}
