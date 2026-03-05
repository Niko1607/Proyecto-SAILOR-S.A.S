package src.main.java.com.sailor.inventario.config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMySQL {
    private static final String url = "jdbc:mysql://localhost:3306/db_sailor_s.a.s";
    private static final String USER = "root";
    private static final String PASSWORD = "1617";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, USER, PASSWORD);
    }
    
}
