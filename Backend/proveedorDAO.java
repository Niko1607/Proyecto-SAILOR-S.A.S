import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class proveedorDAO {
    private String url = "jdbc:mysql://localhost:3306/base_de_datos";
    private String usuario = "root";
    private String contraseña = "1617"; 

    public void registrar_proveedor(Proveedor proveedor) {
        String sql = "INSERT INTO proveedores (nombre, empresa, productos_suministrados) VALUES (?, ?, ?)";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, usuario, contraseña);
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, proveedor.getNombre());
            pstmt.setString(2, proveedor.getEmpresa());
            
            String productos = String.join(",", proveedor.getProductosSuministrados());
            pstmt.setString(3, productos);

            pstmt.executeUpdate();
            System.out.println("Proveedor registrado con éxito.");

            conn.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Error: Driver no encontrado.");
        } catch (SQLException e) {
            System.out.println("Error al registrar proveedor: " + e.getMessage());
        }
    }

    public void listarProveedores() {
        String sql = "SELECT * FROM proveedores";
        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                                   ", Nombre: " + rs.getString("nombre") +
                                   ", Empresa: " + rs.getString("empresa") +
                                   ", Productos Suministrados: " + rs.getString("productos_suministrados"));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar proveedores: " + e.getMessage());
        }
    }
}
