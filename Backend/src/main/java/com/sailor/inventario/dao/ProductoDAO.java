package com.sailor.inventario.dao;

import com.sailor.inventario.model.Producto;
import com.sailor.inventario.config.ConexionMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;



public class ProductoDAO {
    
    // =========================
    // REGISTRAR PRODUCTO
    // =========================    
    public void registrarProducto(Producto producto) {
        String sql = "INSERT INTO productos (nombreProducto, descripcion, precioProducto, cantidad, stockMinimo, stockMaximo, fechaRegistro, activo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {            
            pstmt.setString(1, producto.getNombreProducto());
            pstmt.setString(2, producto.getDescripcion());
            pstmt.setDouble(3, producto.getPrecioProducto());
            pstmt.setInt(4, producto.getCantidad());
            pstmt.setInt(5, producto.getStockMinimo());
            pstmt.setInt(6, producto.getStockMaximo());
            pstmt.setString(7, producto.getFechaRegistro());
            pstmt.setBoolean(8, producto.isActivo());
            pstmt.executeUpdate();
            System.out.println("Producto registrado correctamente");
        }
        catch (SQLException e) {
            System.out.println("Error al registrar producto: " + e.getMessage());
        }
    }

    // =========================
    // MOSTRAR PRODUCTO
    // =========================

    public Producto mostrarProducto(int idProducto) {
        String sql = "SELECT * FROM productos WHERE idProducto = ?";
        Producto producto = null;
        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idProducto);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                producto = new Producto();
                producto.setIdProducto(rs.getInt("idproducto"));
                producto.setNombreProducto(rs.getString("nombreProducto"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecioProducto(rs.getDouble("precioproducto"));
                producto.setCantidad(rs.getInt("cantidad"));
                producto.setStockMinimo(rs.getInt("stockMinimo"));
                producto.setStockMaximo(rs.getInt("stockMaximo"));
                producto.setFechaRegistro(rs.getString("fechaRegistro"));
                producto.setActivo(rs.getBoolean("activo"));
            }
        } catch (SQLException e) {            
            System.out.println("Error al buscar producto: " + e.getMessage());             
        }
        return producto;
    }

    // =========================
    // ACTUALIZAR PRODUCTO
    // =========================

    public void actualizarProducto(Producto producto) {
        String sql = "UPDATE productos SET nombreProducto=?, descripcion=?, precioProducto=?, cantidad=?, stockMinimo=?, stockMaximo=?, fechaRegistro=?, activo=? WHERE idProducto=?";
        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, producto.getNombreProducto());
            pstmt.setString(2, producto.getDescripcion());
            pstmt.setDouble(3, producto.getPrecioProducto());
            pstmt.setInt(4, producto.getCantidad());
            pstmt.setInt(5, producto.getStockMinimo());
            pstmt.setInt(6, producto.getStockMaximo());
            pstmt.setString(7, producto.getFechaRegistro());
            pstmt.setBoolean(8, producto.isActivo());
            pstmt.setInt(9, producto.getIdProducto());
            pstmt.executeUpdate();
            System.out.println("Producto actualizado correctamente");
        } catch (SQLException e) {
            System.out.println("Error al actualizar producto: " + e.getMessage());
        }
    }

    // =========================
    // ELIMINAR PRODUCTO
    // =========================

    public void eliminarProducto(int idProducto) {
        String sql = "DELETE FROM productos WHERE idProducto=?";
        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idProducto);
            pstmt.executeUpdate();
            System.out.println("Producto eliminado correctamente");
        } catch (SQLException e) {
            System.out.println("Error al eliminar producto: " + e.getMessage());
        }
    }

    // =========================
    // CAMBIAR NOMBRE
    // =========================

    public void cambiarNombre(int idProducto, String nombreProducto) {
        String sql = "UPDATE productos SET nombreProducto=? WHERE idProducto=?";
        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombreProducto);
            pstmt.setInt(2, idProducto);
            pstmt.executeUpdate();
            System.out.println("Nombre actualizado");
        } catch (SQLException e) {
            System.out.println("Error al cambiar nombre: " + e.getMessage());
        }
    }

    // =========================
    // CAMBIAR PRECIO
    // =========================

    public void cambiarPrecio(int idProducto, double precioProducto) {
        String sql = "UPDATE productos SET precioProducto=? WHERE idProducto=?";
        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, precioProducto);
            pstmt.setInt(2, idProducto);
            pstmt.executeUpdate();
            System.out.println("Precio actualizado");
        } catch (SQLException e) {
            System.out.println("Error al cambiar precio: " + e.getMessage());
        }
    }

    // =========================
    // CAMBIAR DESCRIPCION
    // =========================

    public void cambiarDescripcion(int idProducto, String descripcion) {
        String sql = "UPDATE productos SET descripcion=? WHERE idProducto=?";
        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, descripcion);
            pstmt.setInt(2, idProducto);
            pstmt.executeUpdate();
            System.out.println("Descripcion actualizada");
        } catch (SQLException e) {
            System.out.println("Error al cambiar descripcion: " + e.getMessage());
        }
    }

    // =========================
    // CAMBIAR STOCK
    // =========================

    public void cambiarStock(int idProducto, int cantidad) {
        String sql = "UPDATE productos SET cantidad=? WHERE idProducto=?";
        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, cantidad);
            pstmt.setInt(2, idProducto);
            pstmt.executeUpdate();
            System.out.println("Stock actualizado");
        } catch (SQLException e) {
            System.out.println("Error al cambiar stock: " + e.getMessage());
        }
    }

    // =========================
    // CAMBIAR STOCK MINIMO
    // =========================

    public void cambiarStockMinimo(int idProducto, int stockMinimo) {
        String sql = "UPDATE productos SET stockMinimo=? WHERE idProducto=?";
        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, stockMinimo);
            pstmt.setInt(2, idProducto);
            pstmt.executeUpdate();
            System.out.println("Stock minimo actualizado");
        } catch (SQLException e) {
            System.out.println("Error al cambiar stock minimo: " + e.getMessage());
        }
    }

    // =========================
    // CAMBIAR STOCK MAXIMO
    // =========================

    public void cambiarStockMaximo(int idProducto, int stockMaximo) {
        String sql = "UPDATE productos SET stockMaximo=? WHERE idProducto=?";
        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, stockMaximo);
            pstmt.setInt(2, idProducto);
            pstmt.executeUpdate();
            System.out.println("Stock maximo actualizado");
        } catch (SQLException e) {
            System.out.println("Error al cambiar stock maximo: " + e.getMessage());
        }
    }
    
    // =========================
    // CAMBIAR FECHA DE REGISTRO
    // =========================

    public void cambiarFechaDeRegistro(int idProducto, String fechaRegistro) {
        String sql = "UPDATE productos SET fechaRegistro=? WHERE idProducto=?";
        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, fechaRegistro);
            pstmt.setInt(2, idProducto);
            pstmt.executeUpdate();
            System.out.println("Fecha de registro actualizada");
        } catch (SQLException e) {
            System.out.println("Error al cambiar fecha de registro: " + e.getMessage());
        }
    }

    // =========================
    // CAMBIAR ACTIVO
    // =========================

    public void cambiarActivo(int idProducto, boolean activo) {
        String sql = "UPDATE productos SET activo=? WHERE idProducto=?";
        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setBoolean(1, activo);
            pstmt.setInt(2, idProducto);
            pstmt.executeUpdate();
            System.out.println("Activo actualizado");
        } catch (SQLException e) {
            System.out.println("Error al cambiar activo: " + e.getMessage());
        }
    }
}
