import java.util.Scanner;
import java.text.DecimalFormat;

public class Producto {
    private String nombre_Producto;
    private String Categoria_Producto;
    private double precio_Producto;
    private int cantidad_Stock; 

    public Producto(String nombre_Producto, String Categoria_Producto, double precio_Producto, int cantidad_Stock) {
        this.nombre_Producto = nombre_Producto;
        this.Categoria_Producto = Categoria_Producto;
        this.precio_Producto = precio_Producto;
        this.cantidad_Stock = cantidad_Stock;
    }

    public String getNombre_Producto() {
        return nombre_Producto;
    }
    public void setNombre_Producto(String nombre_Producto) {
        this.nombre_Producto = nombre_Producto;
    }
    public String getCategoria_Producto() {
        return Categoria_Producto;
    }
    public void setCategoria_Producto(String Categoria_Producto) {
        this.Categoria_Producto = Categoria_Producto;
    }
    public double getPrecio_Producto() {
        return precio_Producto;
    }
    public void setPrecio_Producto(double precio_Producto) {
        this.precio_Producto = precio_Producto;
    }
    public int getCantidad_Stock() {
        return cantidad_Stock;
    }
    public void setCantidad_Stock(int cantidad_Stock) {
        this.cantidad_Stock = cantidad_Stock;
    }

    public void registrar_producto(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Escribe el nombre del producto: ");
        setNombre_Producto(sc.nextLine());
        System.out.println("Escribe la categoría del producto: ");
        setCategoria_Producto(sc.nextLine());
        System.out.println("Escribe el precio del producto: ");
        setPrecio_Producto(sc.nextInt());
        System.out.println("Escribe la cantidad en stock: ");
        setCantidad_Stock(sc.nextInt());    
    }

    public void actualizar_stock(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Escribe la cantidad en stock: ");
        setCantidad_Stock(sc.nextInt());
    }

    public void Mostrar_Informacion(){
        DecimalFormat df = new DecimalFormat("###,###.###");
        System.out.println("Nombre del producto: " + getNombre_Producto());
        System.out.println("Categoría del producto: " + getCategoria_Producto());
        System.out.println("Precio del producto: " + df.format(getPrecio_Producto())+ " pesos");
        System.out.println("Cantidad en stock: " + getCantidad_Stock());
    }

    public static void main(String[] args) {
        Producto producto = new Producto("", "", 0.0, 0);
        producto.registrar_producto();

        ProductoDAO dao = new ProductoDAO();
        dao.registrarProducto(producto);
        dao.listarProductos();
    }
}
