package src.main.java.com.sailor.inventario.model;
import java.util.Scanner;

public class Inventario {
    private String nombre;
    private int cantidad;

    public Inventario(String nombre, int cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void agregarStock(int cantidad) {
        this.cantidad += cantidad;
        System.out.println("Stock agregado. Cantidad actual: " + this.cantidad);
    }

    public void retirarStock(int cantidad) {
        if (this.cantidad >= cantidad) {
            this.cantidad -= cantidad;
            System.out.println("Stock retirado. Cantidad actual: " + this.cantidad);
        } else {
            System.out.println("Stock insuficiente para retirar " + cantidad + " unidades.");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el nombre del producto: ");
        String nombreProducto = sc.nextLine();
        System.out.print("Ingrese la cantidad inicial: ");
        int cantidadInicial = sc.nextInt();

        Inventario inv = new Inventario(nombreProducto, cantidadInicial);
        
        inv.agregarStock(10); 
        inv.retirarStock(5); 
        inv.retirarStock(10); 
    } 

}
