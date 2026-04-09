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

    public void registrarProveedor(Proveedor proveedorObj) {
    if (proveedorObj.getNombreProveedorEmpresa().isEmpty() ||
        proveedorObj.getIdentificacion().isEmpty() ||
        proveedorObj.getTelefono().isEmpty() ||
        proveedorObj.getDireccion().isEmpty() ||
        proveedorObj.getCorreo().isEmpty() ||
        proveedorObj.getTipoProveedor().isEmpty() ||
        proveedorObj.getCiudad().isEmpty()){
        System.out.println("Campos vacíos");
        return;
    }

    if(!proveedorObj.getCorreo().contains("@")){
        System.out.println("Correo inválido");
        return;
    }
    
    if(proveedorObj.getCiudad().length() < 3){
        System.out.println("La ciudad debe tener mínimo 3 caracteres");
        return;
    }   

    if(proveedorObj.getTipoProveedor().length() < 3){
        System.out.println("El tipo de proveedor debe tener mínimo 3 caracteres");
        return;
    }   


        String sql = "INSERT INTO proveedores (nombreProveedorEmpresa, identificacion, telefono, direccion, correo, tipoProveedor, activo, fechaRegistro, ciudad) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, proveedorObj.getNombreProveedorEmpresa());
            pstmt.setString(2, proveedorObj.getIdentificacion());
            pstmt.setString(3, proveedorObj.getTelefono());
            pstmt.setString(4, proveedorObj.getDireccion());
            pstmt.setString(5, proveedorObj.getCorreo());
            pstmt.setString(6, proveedorObj.getTipoProveedor());
            pstmt.setBoolean(7, proveedorObj.isActivo());
            pstmt.setString(8, proveedorObj.getFechaRegistro());
            pstmt.setString(9, proveedorObj.getCiudad());

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

        String sql = "SELECT * FROM proveedores WHERE idProveedor = ?";
        Proveedor proveedor = null;

        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                proveedor = new Proveedor();

                proveedor.setIdProveedor(rs.getInt("idProveedor"));
                proveedor.setNombreProveedorEmpresa(rs.getString("nombreProveedorEmpresa"));
                proveedor.setIdentificacion(rs.getString("identificacion"));
                proveedor.setTelefono(rs.getString("telefono"));
                proveedor.setDireccion(rs.getString("direccion"));
                proveedor.setCorreo(rs.getString("correo"));
                proveedor.setTipoProveedor(rs.getString("tipoProveedor"));
                proveedor.setActivo(rs.getBoolean("activo"));
                proveedor.setFechaRegistro(rs.getString("fechaRegistro"));
                proveedor.setCiudad(rs.getString("ciudad"));

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

    String sql = "UPDATE proveedores SET nombreProveedorEmpresa=?, identificacion=?, telefono=?, direccion=?, correo=?, tipoProveedor=?, activo=?, ciudad=?, fechaRegistro=? WHERE idProveedor=?";

    try (Connection conn = ConexionMySQL.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setString(1, proveedorObj.getNombreProveedorEmpresa());
        pstmt.setString(2, proveedorObj.getIdentificacion());
        pstmt.setString(3, proveedorObj.getTelefono());
        pstmt.setString(4, proveedorObj.getDireccion());
        pstmt.setString(5, proveedorObj.getCorreo());
        pstmt.setString(6, proveedorObj.getTipoProveedor());
        pstmt.setBoolean(7, proveedorObj.isActivo());
        pstmt.setString(8, proveedorObj.getCiudad());
        pstmt.setString(9, proveedorObj.getFechaRegistro());
        pstmt.setInt(10, proveedorObj.getIdProveedor());

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

    public void cambiarTelefono(int idProveedor, String telefono) {

        String sql = "UPDATE proveedores SET telefono=? WHERE idProveedor=?";

        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, telefono);
            pstmt.setInt(2, idProveedor);

            pstmt.executeUpdate();

            System.out.println("Telefono actualizado");

        } catch (SQLException e) {            
            System.out.println("Error al cambiar telefono: " + e.getMessage());
        }
    }

    // =========================
    // CAMBIAR DIRECCION
    // =========================

    public void cambiarDireccionProveedor(int idProveedor, String direccion) {

        String sql = "UPDATE proveedores SET direccion=? WHERE idproveedor=?";

        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, direccion);
            pstmt.setInt(2, idProveedor);

            pstmt.executeUpdate();

            System.out.println("Dirección actualizada");

        } catch (SQLException e) {
            System.out.println("Error al cambiar dirección: " + e.getMessage());
        }
    }

    // =========================
    // CAMBIAR CORREO
    // =========================

    public void cambiarCorreo(int idproveedor, String correo) {

        String sql = "UPDATE proveedores SET correo=? WHERE idProveedor=?";

        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, correo);
            pstmt.setInt(2, idproveedor);

            pstmt.executeUpdate();

            System.out.println("Correo actualizado");

        } catch (SQLException e) {
            System.out.println("Error al cambiar correo: " + e.getMessage());
        }
    }  

    // =========================
    // CAMBIAR NOMBRE
    // =========================

    public void cambiarNombre(int idProveedor, String nombreProveedorEmpresa) {

        String sql = "UPDATE proveedores SET nombreProveedorEmpresa=? WHERE idproveedor=?";

        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nombreProveedorEmpresa);
            pstmt.setInt(2, idProveedor);

            pstmt.executeUpdate();

            System.out.println("Nombre actualizado");

        } catch (SQLException e) {
            System.out.println("Error al cambiar nombre: " + e.getMessage());
            
        }
    }
    
    // =========================
    // CAMBIAR IDENTIFICACION
    // =========================

    public void cambiarIdentificacion(int idproveedor, String identificacion) {

        String sql = "UPDATE proveedores SET identificacion=? WHERE idProveedor=?";

        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, identificacion);
            pstmt.setInt(2, idproveedor);

            pstmt.executeUpdate();

            System.out.println("Identificación actualizada");

        } catch (SQLException e) {
            System.out.println("Error al cambiar identificación: " + e.getMessage());
        }
    }

    // =========================
    // CAMBIAR TIPO DE PROVEEDOR
    // =========================

    public void cambiarTipo_de_provedor(int idProveedor, String tipoProveedor) {

        String sql = "UPDATE proveedores SET tipoProveedor=? WHERE idProveedor=?";

        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, tipoProveedor);
            pstmt.setInt(2, idProveedor);

            pstmt.executeUpdate();

            System.out.println("Tipo de proveedor actualizado");

        } catch (SQLException e) {
            System.out.println("Error al cambiar tipo de proveedor: " + e.getMessage());
        }
    }

    // =========================
    // CAMBIAR FECHA DE REGISTRO
    // =========================

    public void cambiarFecha_de_registro(int idProveedor, String fechaRegistro) {

        String sql = "UPDATE proveedores SET fechaRegistro=? WHERE idProveedor=?";

        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, fechaRegistro);
            pstmt.setInt(2, idProveedor);

            pstmt.executeUpdate();

            System.out.println("Fecha de registro actualizada");

        } catch (SQLException e) {
            System.out.println("Error al cambiar fecha de registro: " + e.getMessage());
        }
    }

    // =========================
    // CAMBIAR ACTIVO
    // =========================

    public void cambiarActivo(int idproveedor, boolean activo) {

        String sql = "UPDATE proveedores SET activo=? WHERE idProveedor=?";

        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setBoolean(1, activo);
            pstmt.setInt(2, idproveedor);

            pstmt.executeUpdate();

            System.out.println("Activo actualizado");

        } catch (SQLException e) {
            System.out.println("Error al cambiar activo: " + e.getMessage());
        }
    }

    // =========================
    // CAMBIAR CIUDAD
    // =========================

    public void cambiarCiudad(int idProveedor, String ciudad) {

        String sql = "UPDATE proveedores SET ciudad=? WHERE idproveedor=?";

        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, ciudad);
            pstmt.setInt(2, idProveedor);

            pstmt.executeUpdate();

            System.out.println("Ciudad actualizada");

        } catch (SQLException e) {
            System.out.println("Error al cambiar ciudad: " + e.getMessage());
        }
    }
}
