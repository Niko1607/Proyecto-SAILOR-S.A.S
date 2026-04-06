package com.sailor.inventario.dao;

import com.sailor.inventario.model.Inventario;
import com.sailor.inventario.config.ConexionMySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InventarioDAO {

    // =========================
    // REGISTRAR INVENTARIO
    // =========================
    public void registrarInventario(Inventario inventario) {

        String sql = "INSERT INTO inventario (idProducto, idUsuario, cantidad, fechaMovimiento) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, inventario.getProducto().getIdProducto());
            pstmt.setInt(2, inventario.getUsuario().getIdUsuario());
            pstmt.setInt(3, inventario.getCantidad());
            pstmt.setString(4, inventario.getFechaMovimiento());

            pstmt.executeUpdate();

            System.out.println("Inventario registrado correctamente");

        } catch (SQLException e) {
            System.out.println("Error al registrar inventario: " + e.getMessage());
        }
    }

    // =========================
    // MOSTRAR INVENTARIO
    // =========================
    public Inventario mostrarInventario(int id) {

        String sql = "SELECT * FROM inventario WHERE idInventario = ?";
        Inventario inventario = null;

        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                inventario = new Inventario();

                inventario.setIdInventario(rs.getInt("idInventario"));
                inventario.setCantidad(rs.getInt("cantidad"));
                inventario.setFechaMovimiento(rs.getString("fechaMovimiento"));

            }

        } catch (SQLException e) {
            System.out.println("Error al buscar inventario: " + e.getMessage());
        }

        return inventario;
    }

    // =========================
    // ACTUALIZAR INVENTARIO
    // =========================
    public void actualizarInventario(Inventario inventario) {

        String sql = "UPDATE inventario SET cantidad=?, fechaMovimiento=? WHERE idInventario=?";

        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, inventario.getCantidad());
            pstmt.setString(2, inventario.getFechaMovimiento());
            pstmt.setInt(3, inventario.getIdInventario());

            pstmt.executeUpdate();

            System.out.println("Inventario actualizado correctamente");

        } catch (SQLException e) {
            System.out.println("Error al actualizar inventario: " + e.getMessage());
        }
    }

    // =========================
    // ELIMINAR INVENTARIO
    // =========================
    public void eliminarInventario(int id) {

        String sql = "DELETE FROM inventario WHERE idInventario=?";

        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            pstmt.executeUpdate();

            System.out.println("Inventario eliminado correctamente");

        } catch (SQLException e) {
            System.out.println("Error al eliminar inventario: " + e.getMessage());
        }
    }

    // =========================
    // CAMBIAR CANTIDAD
    // =========================
    public void cambiarCantidad(int id, int cantidad) {

        String sql = "UPDATE inventario SET cantidad=? WHERE idInventario=?";

        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, cantidad);
            pstmt.setInt(2, id);

            pstmt.executeUpdate();

            System.out.println("Cantidad actualizada");

        } catch (SQLException e) {
            System.out.println("Error al cambiar cantidad: " + e.getMessage());
        }
    }

    // =========================
    // CAMBIAR FECHA
    // =========================
    public void cambiarFecha(int id, String fecha) {

        String sql = "UPDATE inventario SET fechaMovimiento=? WHERE idInventario=?";

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
}

