package com.sailor.backend.controller;

import com.sailor.backend.model.Usuario;
import com.sailor.backend.dto.UsuarioResponseDTO;
import com.sailor.backend.dto.LoginResponseDTO;
import com.sailor.backend.dto.LoginRequestDTO;
import com.sailor.backend.dto.UsuarioRegistroDTO;
import com.sailor.backend.service.UsuarioService;
import com.sailor.backend.security.JwtTokenProvider;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*") // Permite que tu Frontend se conecte sin errores de CORS
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDTO loginRequest) {
        Usuario usuario = usuarioService.login(loginRequest.getCorreo(), loginRequest.getPassword());
        if (usuario != null) {
            String token = jwtTokenProvider.generarToken(usuario.getId(), usuario.getCorreo(), usuario.getRol());
            UsuarioResponseDTO usuarioDTO = new UsuarioResponseDTO(usuario);
            LoginResponseDTO response = new LoginResponseDTO(token, usuarioDTO);
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(401).body("Credenciales incorrectas");
    }

    // REGISTRAR
    @PostMapping("/registrar")
    public ResponseEntity<UsuarioResponseDTO> registrar(@Valid @RequestBody UsuarioRegistroDTO registroDTO) {
        Usuario usuario = new Usuario();
        usuario.setNombre(registroDTO.getNombre());
        usuario.setApellido(registroDTO.getApellido());
        usuario.setIdentificacion(registroDTO.getIdentificacion());
        usuario.setCorreo(registroDTO.getCorreo());
        usuario.setPassword(registroDTO.getPassword());
        usuario.setRol(registroDTO.getRol());
        usuario.setDireccion(registroDTO.getDireccion());
        
        Usuario usuarioGuardado = usuarioService.registrarUsuario(usuario);
        return ResponseEntity.ok(new UsuarioResponseDTO(usuarioGuardado));
    }

    // OBTENER UNO
   @GetMapping("/buscar/{id}")
    public ResponseEntity<UsuarioResponseDTO> obtener(@PathVariable Long id) {
        Usuario usuario = usuarioService.obtener(id);
        return usuario != null ? ResponseEntity.ok(new UsuarioResponseDTO(usuario)) : ResponseEntity.notFound().build();
    }

    // ACTUALIZAR
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> actualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        usuario.setId(id);
        Usuario usuarioActualizado = usuarioService.guardar(usuario);
        return ResponseEntity.ok(new UsuarioResponseDTO(usuarioActualizado));
    }

    // LISTAR TODOS
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listar() {
        List<UsuarioResponseDTO> usuarios = usuarioService.listarUsuarios()
            .stream()
            .map(UsuarioResponseDTO::new)
            .collect(Collectors.toList());
        return ResponseEntity.ok(usuarios);
    }

    // LISTAR CON PAGINACIÓN
    @GetMapping("/paginado")
    public ResponseEntity<Page<UsuarioResponseDTO>> listarPaginado(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Usuario> usuariosPage = usuarioService.listarUsuariosPaginados(pageable);
        Page<UsuarioResponseDTO> usuariosDTOPage = usuariosPage.map(UsuarioResponseDTO::new);
        return ResponseEntity.ok(usuariosDTOPage);
    }

    // ELIMINAR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}