package src.main.java.com.sailor.inventario.dao;

import src.main.java.com.sailor.inventario.model.Inventario;
import src.main.java.com.sailor.inventario.config.ConexionMySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InventarioDAO {
    private Connection conexion;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public InventarioDAO() {
        this.conexion = ConexionMySQL.getConnection();
    }

    public void registrarInventario(Inventario inventario) {        
        try {
            pstmt = conexion.prepareStatement("INSERT INTO inventario (idUsuario, fecha, precio) VALUES (?, ?, ?)");
            pstmt.setInt(1, inventario.getUsuario().getIdUsuario());
            pstmt.setString(2, inventario.getFecha());
            pstmt.setDouble(3, inventario.getPrecio());
            pstmt.executeUpdate();
            System.out.println("Inventario registrado correctamente");
        } catch (SQLException e) {
            System.out.println("Error al registrar inventario: " + e.getMessage());
        }
    }

    public void mostrarInventario(int id) {
        try {
            pstmt = conexion.prepareStatement("SELECT * FROM inventario WHERE id = ?");
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                Inventario inventario = new Inventario();
                inventario.setIdUsuario(rs.getInt("idUsuario"));
                inventario.setFecha(rs.getString("fecha"));
                inventario.setPrecio(rs.getDouble("precio"));
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar inventario: " + e.getMessage());
        }
    }

    public void actualizarInventario(Inventario inventario) {
    String sql = "UPDATE inventario SET fecha=?, precio=? WHERE id=?";
    try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
        pstmt.setString(1, inventario.getFecha());
        pstmt.setDouble(2, inventario.getPrecio());
        pstmt.setInt(3, inventario.getIdInventario());
        pstmt.executeUpdate();
        System.out.println("Inventario actualizado correctamente");
    } catch (SQLException e) {
        System.out.println("Error al actualizar inventario: " + e.getMessage());
    }
}


    public void eliminarInventario(int id) {
        try {
            pstmt = conexion.prepareStatement("DELETE FROM inventario WHERE id=?");
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Inventario eliminado correctamente");
        } catch (SQLException e) {
            System.out.println("Error al eliminar inventario: " + e.getMessage());
        }
    }

    public void cambiarFecha(int id, String fecha) {
        try {
            pstmt = conexion.prepareStatement("UPDATE inventario SET fecha=? WHERE id=?");
            pstmt.setString(1, fecha);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            System.out.println("Fecha actualizada");
        } catch (SQLException e) {
            System.out.println("Error al cambiar fecha: " + e.getMessage());
        }
    }

    public void cambiarPrecio(int id, double precio) {
        try {
            pstmt = conexion.prepareStatement("UPDATE inventario SET precio=? WHERE id=?");
            pstmt.setDouble(1, precio);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            System.out.println("Precio actualizado");
        } catch (SQLException e) {
            System.out.println("Error al cambiar precio: " + e.getMessage());
        }
    }
}