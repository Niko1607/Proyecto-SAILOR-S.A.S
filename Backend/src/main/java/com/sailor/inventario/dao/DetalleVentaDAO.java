package com.sailor.inventario.dao;

import com.sailor.inventario.config.ConexionMySQL;
import com.sailor.inventario.model.Detalleventa;
import com.sailor.inventario.model.Venta;
import com.sailor.inventario.model.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DetalleVentaDAO {

    public void registrarDetalle(Detalleventa detalle) {

        String sql = "INSERT INTO detalleventa (idVenta, idProducto, cantidad, precioUnitario, subtotal) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, detalle.getVenta().getIdVenta());
            pstmt.setInt(2, detalle.getProducto().getIdProducto());
            pstmt.setInt(3, detalle.getCantidad());
            pstmt.setDouble(4, detalle.getPrecioUnitario());
            pstmt.setDouble(5, detalle.getSubtotal());

            pstmt.executeUpdate();

            System.out.println("Detalle de venta registrado");

        } catch (SQLException e) {
            System.out.println("Error al registrar detalle: " + e.getMessage());
        }
    }

    public void actualizarDetalle(Detalleventa detalle) {

        String sql = "UPDATE detalleventa SET idVenta=?, idProducto=?, cantidad=?, precioUnitario=?, subtotal=? WHERE idDetalleVenta=?";

        try (Connection conn = ConexionMySQL.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, detalle.getVenta().getIdVenta());
            pstmt.setInt(2, detalle.getProducto().getIdProducto());
            pstmt.setInt(3, detalle.getCantidad());
            pstmt.setDouble(4, detalle.getPrecioUnitario());
            pstmt.setDouble(5, detalle.getSubtotal());
            pstmt.setInt(6, detalle.getIdDetalleVenta());

            pstmt.executeUpdate();

            System.out.println("Detalle de venta actualizado");

        } catch (SQLException e) {
            System.out.println("Error al actualizar detalle: " + e.getMessage());
        }
    }

    public void eliminarDetalle(int id) {

        String sql = "DELETE FROM detalleventa WHERE idDetalleVenta=?";

        try (Connection conn = ConexionMySQL.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            pstmt.executeUpdate();

            System.out.println("Detalle de venta eliminado");

        } catch (SQLException e) {
            System.out.println("Error al eliminar detalle: " + e.getMessage());
        }
    }

    public Detalleventa mostrarDetalle(int id) {

        String sql = "SELECT * FROM detalleventa WHERE idDetalleVenta=?";

        try (Connection conn = ConexionMySQL.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {

                    Detalleventa detalle = new Detalleventa();

                    detalle.setIdDetalleVenta(rs.getInt("idDetalleVenta"));

                    // 🔥 CREAR OBJETO VENTA
                    Venta venta = new Venta();
                    venta.setIdVenta(rs.getInt("idVenta"));
                    detalle.setVenta(venta);

                    // 🔥 CREAR OBJETO PRODUCTO
                    Producto producto = new Producto();
                    producto.setIdProducto(rs.getInt("idProducto"));
                    detalle.setProducto(producto);

                    detalle.setCantidad(rs.getInt("cantidad"));
                    detalle.setPrecioUnitario(rs.getDouble("precioUnitario"));
                    detalle.setSubtotal(rs.getDouble("subtotal"));

                    return detalle;

                } else {
                    System.out.println("Detalle no encontrado");
                    return null;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al mostrar detalle: " + e.getMessage());
            return null;
        }
    }
} 