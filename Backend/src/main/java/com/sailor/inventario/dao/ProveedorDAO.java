package com.sailor.inventario.dao;

import com.sailor.inventario.model.Proveedor;
import com.sailor.inventario.config.ConexionMySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class ProveedorDAO {

    // =========================
    // INSERTAR PROVEEDOR
    // =========================

    public void insertarProveedor(Proveedor proveedorObj) {

        String sql = "INSERT INTO proveedores (nombre, identificacion, telefono, direccion, correo, tipo_de_proveedor, activo, fecha_de_registro) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, proveedorObj.getNombre_del_provedor_o_empresa());
            pstmt.setInt(2, proveedorObj.getIdentificación());
            pstmt.setInt(3, proveedorObj.getTelefono());
            pstmt.setString(4, proveedorObj.getDireccion());
            pstmt.setString(5, proveedorObj.getCorreo());
            pstmt.setString(6, proveedorObj.getTipo_de_provedor());
            pstmt.setBoolean(7, proveedorObj.isActivo());
            pstmt.setString(8, proveedorObj.getFecha_de_registro());

            pstmt.executeUpdate();

            System.out.println("Proveedor registrado correctamente");

        } catch (SQLException e) {

            System.out.println("Error al registrar proveedor: " + e.getMessage());

        }
    }

    // =========================
    // MOSTRAR PROVEEDOR
    // =========================

    public Proveedor mostrarProveedor(int id) {

        String sql = "SELECT * FROM proveedores WHERE id = ?";
        Proveedor proveedor = null;

        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                proveedor = new Proveedor();

                proveedor.setIdProveedor(rs.getInt("id"));
                proveedor.setNombre_del_provedor_o_empresa(rs.getString("nombre"));
                proveedor.setIdentificación(rs.getInt("identificacion"));
                proveedor.setTelefono(rs.getInt("telefono"));
                proveedor.setDireccion(rs.getString("direccion"));
                proveedor.setCorreo(rs.getString("correo"));
                proveedor.setTipo_de_provedor(rs.getString("tipo_de_proveedor"));
                proveedor.setActivo(rs.getBoolean("activo"));
                proveedor.setFecha_de_registro(rs.getString("fecha_de_registro"));

            }

        } catch (SQLException e) {

            System.out.println("Error al buscar proveedor: " + e.getMessage());

        }

        return proveedor;
    }

    // =========================
    // ACTUALIZAR PROVEEDOR
    // =========================

    public void actualizarProveedor(Proveedor proveedorObj) {

        String sql = "UPDATE proveedores SET nombre=?, identificacion=?, telefono=?, direccion=?, correo=? WHERE id=?";

        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, proveedorObj.getNombre_del_provedor_o_empresa());
            pstmt.setInt(2, proveedorObj.getIdentificación());
            pstmt.setInt(3, proveedorObj.getTelefono());
            pstmt.setString(4, proveedorObj.getDireccion());
            pstmt.setString(5, proveedorObj.getCorreo());
            pstmt.setInt(6, proveedorObj.getIdProveedor());
            pstmt.setString(7, proveedorObj.getTipo_de_provedor());
            pstmt.setBoolean(8, proveedorObj.isActivo());
            pstmt.setString(9, proveedorObj.getFecha_de_registro());

            pstmt.executeUpdate();

            System.out.println("Proveedor actualizado correctamente");

        } catch (SQLException e) {

            System.out.println("Error al actualizar proveedor: " + e.getMessage());

        }
    }

    // =========================
    // ELIMINAR PROVEEDOR
    // =========================

    public void eliminarProveedor(int id) {

        String sql = "DELETE FROM proveedores WHERE id=?";

        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            pstmt.executeUpdate();

            System.out.println("Proveedor eliminado correctamente");

        } catch (SQLException e) {

            System.out.println("Error al eliminar proveedor: " + e.getMessage());

        }
    }

    // =========================
    // CAMBIAR TELEFONO
    // =========================

    public void cambiarTelefono(int id, int telefono) {

        String sql = "UPDATE proveedores SET telefono=? WHERE id=?";

        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, telefono);
            pstmt.setInt(2, id);

            pstmt.executeUpdate();

            System.out.println("Telefono actualizado");

        } catch (SQLException e) {            
            System.out.println("Error al cambiar telefono: " + e.getMessage());
        }
    }

    // =========================
    // CAMBIAR DIRECCION
    // =========================

    public void cambiarDireccion(int id, String direccion) {

        String sql = "UPDATE proveedores SET direccion=? WHERE id=?";

        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, direccion);
            pstmt.setInt(2, id);

            pstmt.executeUpdate();

            System.out.println("Dirección actualizada");

        } catch (SQLException e) {
            System.out.println("Error al cambiar dirección: " + e.getMessage());
        }
    }

    // =========================
    // CAMBIAR CORREO
    // =========================

    public void cambiarCorreo(int id, String correo) {

        String sql = "UPDATE proveedores SET correo=? WHERE id=?";

        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, correo);
            pstmt.setInt(2, id);

            pstmt.executeUpdate();

            System.out.println("Correo actualizado");

        } catch (SQLException e) {
            System.out.println("Error al cambiar correo: " + e.getMessage());
        }
    }  

    // =========================
    // CAMBIAR NOMBRE
    // =========================

    public void cambiarNombre(int id, String nombre) {

        String sql = "UPDATE proveedores SET nombre=? WHERE id=?";

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
    // CAMBIAR IDENTIFICACION
    // =========================

    public void cambiarIdentificacion(int id, int identificacion) {

        String sql = "UPDATE proveedores SET identificacion=? WHERE id=?";

        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, identificacion);
            pstmt.setInt(2, id);

            pstmt.executeUpdate();

            System.out.println("Identificación actualizada");

        } catch (SQLException e) {
            System.out.println("Error al cambiar identificación: " + e.getMessage());
        }
    }

    // =========================
    // CAMBIAR TIPO DE PROVEEDOR
    // =========================

    public void cambiarTipo_de_provedor(int id, String tipo_de_provedor) {

        String sql = "UPDATE proveedores SET tipo_de_proveedor=? WHERE id=?";

        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, tipo_de_provedor);
            pstmt.setInt(2, id);

            pstmt.executeUpdate();

            System.out.println("Tipo de proveedor actualizado");

        } catch (SQLException e) {
            System.out.println("Error al cambiar tipo de proveedor: " + e.getMessage());
        }
    }

    // =========================
    // CAMBIAR FECHA DE REGISTRO
    // =========================

    public void cambiarFecha_de_registro(int id, String fecha_de_registro) {

        String sql = "UPDATE proveedores SET fecha_de_registro=? WHERE id=?";

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

    // =========================
    // CAMBIAR ACTIVO
    // =========================

    public void cambiarActivo(int id, boolean activo) {

        String sql = "UPDATE proveedores SET activo=? WHERE id=?";

        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setBoolean(1, activo);
            pstmt.setInt(2, id);

            pstmt.executeUpdate();

            System.out.println("Activo actualizado");

        } catch (SQLException e) {
            System.out.println("Error al cambiar activo: " + e.getMessage());
        }
    }
}
