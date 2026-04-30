package com.sailor.backend.service;

import com.sailor.backend.model.Usuario;
import com.sailor.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // =========================
    // REGISTRAR USUARIO
    // =========================
    public Usuario registrarUsuario(Usuario usuario) {

        validarPassword(usuario);

        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        return usuarioRepository.save(usuario);
    }

    // =========================
    // LOGIN
    // =========================
    public Usuario login(String correo, String password) {

        Optional<Usuario> usuarioOptional = usuarioRepository.findByCorreo(correo);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();

            if (passwordEncoder.matches(password, usuario.getPassword())) {
                return usuario;
            }
        }

        return null;
    }

    // =========================
    // GUARDAR / ACTUALIZAR
    // =========================
    public Usuario guardar(Usuario usuario) {

        if (usuario.getPassword() != null && !usuario.getPassword().isEmpty()) {

            if (!usuario.getPassword().startsWith("$2a$")) {
                usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
            }
        }

        return usuarioRepository.save(usuario);
    }

    // =========================
    // OBTENER POR ID
    // =========================
    public Usuario obtener(Long id) { 
        return usuarioRepository.findById(id).orElse(null);
    }
    
    // =========================
    // ELIMINAR
    // =========================
    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }
    
    // =========================
    // BUSCAR POR CORREO
    // =========================
    public Usuario buscarPorCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo).orElse(null);
    }

    // =========================
    // LISTAR (sin paginación)
    // =========================
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    // =========================
    // LISTAR CON PAGINACIÓN
    // =========================
    public Page<Usuario> listarUsuariosPaginados(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }

    // =========================
    // VALIDACIÓN PRIVADA
    // =========================
    private void validarPassword(Usuario usuario) {
        if (usuario.getPassword() == null || usuario.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password requerido");
        }
    }
}