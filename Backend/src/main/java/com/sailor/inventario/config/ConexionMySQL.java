package src.main.java.com.sailor.inventario.config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMySQL {
    private static final String url = System.getenv ("db_URL");
    private static final String USER = System.getenv ("db_USER");
    private static final String PASSWORD = System.getenv ("db_PASS");

    public static Connection getConnection(){
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection(url, USER, PASSWORD);
            System.out.println("Conexión establecida con éxito a la base de datos.");
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }
        return conexion;
    }
    
    
}
