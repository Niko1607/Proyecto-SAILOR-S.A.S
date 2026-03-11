package src.main.java.com.sailor.inventario.dao;
import src.main.java.com.sailor.inventario.model.Producto;
import src.main.java.com.sailor.inventario.config.ConexionMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;



public class ProductoDAO {
    
    // =========================
    // REGISTRAR PRODUCTO
    // =========================    
    public void registrarProducto(Producto producto) {
        String sql = "INSERT INTO productos (nombre, descripcion, precio, cantidad, stock_minimo, stock_maximo, fecha_de_registro, activo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {            
            pstmt.setString(1, producto.getNombre_Producto());
            pstmt.setString(2, producto.getDescripcion());
            pstmt.setDouble(3, producto.getPrecio_Producto());
            pstmt.setInt(4, producto.getCantidad());
            pstmt.setBoolean(5, producto.isStrok_minimo());
            pstmt.setBoolean(6, producto.isStrok_maximo());
            pstmt.setString(7, producto.getFecha_de_registro());
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

    public Producto mostrarProducto(int id) {
        String sql = "SELECT * FROM productos WHERE id = ?";
        Producto producto = null;
        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                producto = new Producto();
                producto.setIdProducto(rs.getInt("id"));
                producto.setNombre_Producto(rs.getString("nombre"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecio_Producto(rs.getDouble("precio"));
                producto.setCantidad(rs.getInt("cantidad"));
                producto.setStrok_minimo(rs.getBoolean("stock_minimo"));
                producto.setStrok_maximo(rs.getBoolean("stock_maximo"));
                producto.setFecha_de_registro(rs.getString("fecha_de_registro"));
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
        String sql = "UPDATE productos SET nombre=?, descripcion=?, precio=?, cantidad=?, stock_minimo=?, stock_maximo=?, fecha_de_registro=?, activo=? WHERE id=?";
        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, producto.getNombre_Producto());
            pstmt.setString(2, producto.getDescripcion());
            pstmt.setDouble(3, producto.getPrecio_Producto());
            pstmt.setInt(4, producto.getCantidad());
            pstmt.setBoolean(5, producto.isStrok_minimo());
            pstmt.setBoolean(6, producto.isStrok_maximo());
            pstmt.setString(7, producto.getFecha_de_registro());
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

    public void eliminarProducto(int id) {
        String sql = "DELETE FROM productos WHERE id=?";
        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Producto eliminado correctamente");
        } catch (SQLException e) {
            System.out.println("Error al eliminar producto: " + e.getMessage());
        }
    }

    // =========================
    // CAMBIAR NOMBRE
    // =========================

    public void cambiarNombre(int id, String nombre) {
        String sql = "UPDATE productos SET nombre=? WHERE id=?";
        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            System.out.println("Nombre actualizado");
        } catch (SQLException e) {
            System.out.println("Error al cambiar nombre: " + e.getMessage());
        }
    }

    // =========================
    // CAMBIAR PRECIO
    // =========================

    public void cambiarPrecio(int id, double precio) {
        String sql = "UPDATE productos SET precio=? WHERE id=?";
        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, precio);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            System.out.println("Precio actualizado");
        } catch (SQLException e) {
            System.out.println("Error al cambiar precio: " + e.getMessage());
        }
    }

    // =========================
    // CAMBIAR DESCRIPCION
    // =========================

    public void cambiarDescripcion(int id, String descripcion) {
        String sql = "UPDATE productos SET descripcion=? WHERE id=?";
        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, descripcion);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            System.out.println("Descripcion actualizada");
        } catch (SQLException e) {
            System.out.println("Error al cambiar descripcion: " + e.getMessage());
        }
    }

    // =========================
    // CAMBIAR STOCK
    // =========================

    public void cambiarStock(int id, int cantidad) {
        String sql = "UPDATE productos SET cantidad=? WHERE id=?";
        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, cantidad);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            System.out.println("Stock actualizado");
        } catch (SQLException e) {
            System.out.println("Error al cambiar stock: " + e.getMessage());
        }
    }

    // =========================
    // CAMBIAR STOCK MINIMO
    // =========================

    public void cambiarStockMinimo(int id, boolean stock_minimo) {
        String sql = "UPDATE productos SET stock_minimo=? WHERE id=?";
        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setBoolean(1, stock_minimo);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            System.out.println("Stock minimo actualizado");
        } catch (SQLException e) {
            System.out.println("Error al cambiar stock minimo: " + e.getMessage());
        }
    }

    // =========================
    // CAMBIAR STOCK MAXIMO
    // =========================

    public void cambiarStockMaximo(int id, boolean stock_maximo) {
        String sql = "UPDATE productos SET stock_maximo=? WHERE id=?";
        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setBoolean(1, stock_maximo);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            System.out.println("Stock maximo actualizado");
        } catch (SQLException e) {
            System.out.println("Error al cambiar stock maximo: " + e.getMessage());
        }
    }

    // =========================
    // CAMBIAR FECHA DE REGISTRO
    // =========================

    public void cambiarFechaDeRegistro(int id, String fecha_de_registro) {
        String sql = "UPDATE productos SET fecha_de_registro=? WHERE id=?";
        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, fecha_de_registro);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            System.out.println("Fecha de registro actualizada");
        } catch (SQLException e) {
            System.out.println("Error al cambiar fecha de registro: " + e.getMessage());
        }
    }
}