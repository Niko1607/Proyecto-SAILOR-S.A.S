package src.main.java.com.sailor.inventario.dao;
import src.main.java.com.sailor.inventario.model.Producto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class ProductoDAO {
    public void registrarProducto(Producto producto) {
    String sql = "INSERT INTO productos (nombre, categoria, precio, cantidad) VALUES (?, ?, ?, ?)";
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(url, usuario, contraseña);
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, producto.getNombre_Producto());
        pstmt.setString(2, producto.getCategoria_Producto());
        pstmt.setDouble(3, producto.getPrecio_Producto());
        pstmt.setInt(4, producto.getCantidad_Stock());

        pstmt.executeUpdate();
        System.out.println("Producto registrado con éxito.");

        conn.close();
        } catch (ClassNotFoundException e) {
        System.out.println("Error: Driver no encontrado.");
        } catch (SQLException e) {
        System.out.println("Error al registrar producto: " + e.getMessage());
        }
    }

    public void listarProductos(){
        String sql = "SELECT * FROM productos";
        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                System.out.println("ID:" +  rs.getInt("id")+
                                   ", Nombre: " + rs.getString("nombre") +
                                   ", Categoría: " + rs.getString("categoria") +
                                   ", Precio: " + rs.getDouble("precio") +
                                   ", Cantidad: " + rs.getInt("cantidad"));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar productos: " + e.getMessage());
        }
    }
}

