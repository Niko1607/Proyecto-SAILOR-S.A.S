package com.sailor.inventario.dao;

import com.sailor.inventario.model.Usuario;
import com.sailor.inventario.config.ConexionMySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    // =========================
    // INSERTAR USUARIO
    // =========================

    public void insertarUsuario(Usuario usuarioObj) {

        String sql = "INSERT INTO Usuarios (nombre, apellido, identificacion, correo, password, rol, direccion) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, usuarioObj.getNombre());
            pstmt.setString(2, usuarioObj.getApellido());
            pstmt.setString(3, usuarioObj.getIdentificacion());
            pstmt.setString(4, usuarioObj.getCorreo());
            pstmt.setString(5, usuarioObj.getPassword());
            pstmt.setString(6, usuarioObj.getRol());
            pstmt.setString(7, usuarioObj.getDireccion());

            pstmt.executeUpdate();

            System.out.println("Usuario registrado correctamente");

        } catch (SQLException e) {

            System.out.println("Error al registrar usuario: " + e.getMessage());

        }
    }

    // =========================
    // MOSTRAR USUARIO
    // =========================

    public Usuario mostrarUsuario(int idUsuario) {

        String sql = "SELECT * FROM usuarios WHERE idUsuario = ?";
        Usuario usuario = null;

        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idUsuario);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                usuario = new Usuario();

                usuario.setIdUsuario(rs.getInt("idUsuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setIdentificacion(rs.getString("identificacion"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setPassword(rs.getString("password"));
                usuario.setRol(rs.getString("rol"));
                usuario.setDireccion(rs.getString("direccion"));

            }

        } catch (SQLException e) {

            System.out.println("Error al buscar usuario: " + e.getMessage());

        }

        return usuario;
    }

    // =========================
    // ACTUALIZAR USUARIO
    // =========================

    public void actualizarUsuario(Usuario usuarioObj) {

        String sql = "UPDATE usuarios SET nombre=?, apellido=?, identificacion=?, correo=?, rol=?, direccion=? WHERE idUsuario=?";

        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, usuarioObj.getNombre());
            pstmt.setString(2, usuarioObj.getApellido());
            pstmt.setString(3, usuarioObj.getIdentificacion());
            pstmt.setString(4, usuarioObj.getCorreo());
            pstmt.setString(5, usuarioObj.getRol());
            pstmt.setString(6, usuarioObj.getDireccion());
            pstmt.setInt(7, usuarioObj.getIdUsuario());

            int filas = pstmt.executeUpdate();

            if (filas > 0) {
                System.out.println("Usuario actualizado correctamente");
            } else {
                System.out.println("No se encontro usuario");
            }
        } catch (SQLException e) {

            System.out.println("Error al actualizar usuario: " + e.getMessage());

        }
    }

    // =========================
    // ELIMINAR USUARIO
    // =========================

    public void eliminarUsuario(int IdUsuario) {

        String sql = "DELETE FROM usuarios WHERE idUsuario=?";

        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, IdUsuario);

            pstmt.executeUpdate();

            System.out.println("Usuario eliminado correctamente");

        } catch (SQLException e) {

            System.out.println("Error al eliminar usuario: " + e.getMessage());

        }
    }

    // =========================
    // CAMBIAR CONTRASEÑA
    // =========================

    public void cambiarContraseña(int IdUsuario, String nuevaContraseña) {

        String sql = "UPDATE usuarios SET password=? WHERE idUsuario=?";

        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nuevaContraseña);
            pstmt.setInt(2, IdUsuario);

            pstmt.executeUpdate();

            System.out.println("Contraseña actualizada");

        } catch (SQLException e) {

            System.out.println("Error al cambiar contraseña: " + e.getMessage());

        }
    }

    // =========================
    // CAMBIAR ROL
    // =========================

    public void cambiarRol(int IdUsuario, String rol) {

        String sql = "UPDATE usuarios SET rol=? WHERE idUsuario=?";

        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, rol);
            pstmt.setInt(2, IdUsuario);

            pstmt.executeUpdate();

            System.out.println("Rol actualizado");

        } catch (SQLException e) {

            System.out.println("Error al cambiar rol: " + e.getMessage());

        }
    }

    // =========================
    // CAMBIAR DIRECCION
    // =========================

    public void cambiarDireccion(int IdUsuario, String direccion) {

        String sql = "UPDATE usuarios SET direccion=? WHERE idUsuario=?";

        try (Connection conn = ConexionMySQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, direccion);
            pstmt.setInt(2, IdUsuario);

            pstmt.executeUpdate();

            System.out.println("Dirección actualizada");

        } catch (SQLException e) {
            System.out.println("Error al cambiar dirección: " + e.getMessage());
        }
    }
}