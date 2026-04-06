package com.sailor.inventario.dao;

import com.sailor.inventario.model.Venta;
import com.sailor.inventario.config.ConexionMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class VentaDAO {
    
    // =========================
    // REGISTRAR VENTA
    // =========================
    public void registrarVenta(Venta venta) {

        String sql = "INSERT INTO venta (idUsuario, fecha, estado, precio) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, venta.getIdUsuario());
            pstmt.setString(2, venta.getFecha());
            pstmt.setBoolean(3, venta.isEstado());
            pstmt.setDouble(4, venta.getTotal());

            pstmt.executeUpdate();

            System.out.println("Venta registrada correctamente");

        } catch (SQLException e) {
            System.out.println("Error al registrar venta: " + e.getMessage());

        }
    }

    // =========================
    // MOSTRAR VENTA
    // =========================
    public Venta mostrarVenta(int id) {

        String sql = "SELECT * FROM venta WHERE idVenta = ?";
        Venta venta = null;

        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                venta = new Venta();

                venta.setIdVenta(rs.getInt("idVenta"));
                venta.setIdUsuario(rs.getInt("idUsuario"));
                venta.setFecha(rs.getString("fecha"));
                venta.setEstado(rs.getBoolean("estado"));
                venta.setTotal(rs.getDouble("precio"));

            }

        } catch (SQLException e) {
            System.out.println("Error al buscar venta: " + e.getMessage());
        }

        return venta;
    }

    // =========================
    // ACTUALIZAR VENTA
    // =========================
    public void actualizarVenta(Venta venta) {

        String sql = "UPDATE venta SET idUsuario=?, fecha=?, estado=?, precio=? WHERE idVenta=?";

        try (Connection conn = ConexionMySQL.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, venta.getIdUsuario());
            pstmt.setString(2, venta.getFecha());
            pstmt.setBoolean(3, venta.isEstado());
            pstmt.setDouble(4, venta.getTotal());
            pstmt.setInt(5, venta.getIdVenta());

            pstmt.executeUpdate();

            System.out.println("Venta actualizada correctamente");

        } catch (SQLException e) {
            System.out.println("Error al actualizar venta: " + e.getMessage());
        }
    }

    // =========================
    // ELIMINAR VENTA
    // =========================
    public void eliminarVenta(int id) {

        String sql = "DELETE FROM venta WHERE idVenta=?";

        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            pstmt.executeUpdate();

            System.out.println("Venta eliminada correctamente");

        } catch (SQLException e) {
            System.out.println("Error al eliminar venta: " + e.getMessage());
        }
    }   

    // =========================
    // CAMBIAR FECHA
    // =========================
    public void cambiarFecha(int id, String fecha) {

        String sql = "UPDATE venta SET fecha=? WHERE idVenta=?";

        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, fecha);
            pstmt.setInt(2, id);

            pstmt.executeUpdate();

            System.out.println("Fecha actualizada");

        } catch (SQLException e) {
            System.out.println("Error al cambiar fecha: " + e.getMessage());
        }
    }

    // =========================
    // CAMBIAR ESTADO
    // =========================
    public void cambiarEstado(int id, boolean estado) {

        String sql = "UPDATE venta SET estado=? WHERE idVenta=?";

        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setBoolean(1, estado);
            pstmt.setInt(2, id);

            pstmt.executeUpdate();

            System.out.println("Estado actualizado");

        } catch (SQLException e) {
            System.out.println("Error al cambiar estado: " + e.getMessage());
        }
    }

    // =========================
    // CAMBIAR TOTAL
    // =========================
    public void cambiarTotal(int id, double total) {

        String sql = "UPDATE venta SET total=? WHERE idVenta=?";

        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDouble(1, total);
            pstmt.setInt(2, id);

            pstmt.executeUpdate();

            System.out.println("Total actualizado");

        } catch (SQLException e) {
            System.out.println("Error al cambiar total: " + e.getMessage());
        }
    }
}