package src.main.java.com.sailor.inventario.dao;
import src.main.java.com.sailor.inventario.model.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class UsuarioDAO {
    private String url = "jdbc:mysql://localhost:3306/base_de_datos";
    private String usuario = "root";
    private String contraseña = "1617"; 

    public void registrarEmpleado(Usuario empleado) {
    String sql = "INSERT INTO empleados (nombre, identificacion, cargo, salario, Horas_trabajo) VALUES (?, ?, ?, ?, ?)";
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(url, usuario, contraseña);
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, empleado.getNombre());
        pstmt.setInt(2, empleado.getIdentificacion());
        pstmt.setString(3, empleado.getCargo());
        pstmt.setDouble(4, empleado.getSalario());
        pstmt.setInt(5, empleado.getHorasTrabajadas());

        pstmt.executeUpdate();
        System.out.println("Empleado registrado con éxito.");

        conn.close();
        } catch (ClassNotFoundException e) {
        System.out.println("Error: Driver no encontrado.");
        } catch (SQLException e) {
        System.out.println("Error al registrar empleado: " + e.getMessage());
         }
    }
    
    public void listarEmpleados(){
        String sql = "SELECT * FROM empleados";
        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                System.out.println("ID:" +  rs.getInt("id")+
                                   ", Nombre: " + rs.getString("nombre") +
                                   ", Identificación: " + rs.getInt("identificacion") +
                                   ", Cargo: " + rs.getString("cargo") +
                                   ", Salario: " + rs.getDouble("salario") +
                                   ", Horas Trabajadas: " + rs.getInt("Horas_trabajo"));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar empleados: " + e.getMessage());
        }
    }

}