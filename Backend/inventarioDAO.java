import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class inventarioDAO {
    private String url = "jdbc:mysql://localhost:3306/base_de_datos";
    private String usuario = "root";
    private String contraseña = "1617";

    public void agregarStock(String nombre, int cantidad) {
        String sql = "UPDATE productos SET cantidad = cantidad + ? WHERE nombre = ?";
        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña);
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, cantidad);
            pstmt.setString(2, nombre);
            int filas = pstmt.executeUpdate();
            if (filas > 0) {
                System.out.println("Stock agregado correctamente.");
            } else {
                System.out.println("Producto no encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Error al agregar stock: " + e.getMessage());
        }
    }

    public void retirarStock(String nombre, int cantidad) {
        String sql = "UPDATE productos SET cantidad = cantidad - ? WHERE nombre = ? AND cantidad >= ?";
        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, cantidad);
            pstmt.setString(2, nombre);
            pstmt.setInt(3, cantidad);
            int filas = pstmt.executeUpdate();
            if (filas > 0) {
                System.out.println("Stock retirado correctamente.");
            } else {
                System.out.println("Producto no encontrado o stock insuficiente.");
            }
        } catch (SQLException e) {
            System.out.println("Error al retirar stock: " + e.getMessage());
        }
    }

    public void consultarStock() {
        String sql = "SELECT nombre, cantidad FROM productos";
        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("Stock actual de productos:");
            while (rs.next()) {
                System.out.println(rs.getString("nombre") + ": " + rs.getInt("cantidad") + " unidades");
            }
        } catch (SQLException e) {
            System.out.println("Error al consultar stock: " + e.getMessage());
        }
    }
} 