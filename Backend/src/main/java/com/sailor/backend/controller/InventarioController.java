package com.sailor.backend.controller;

import com.sailor.backend.model.Inventario;
import com.sailor.backend.model.Producto;
import com.sailor.backend.model.Usuario;
import com.sailor.backend.service.InventarioService;
import com.sailor.backend.service.ProductoService;
import com.sailor.backend.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventario")
@CrossOrigin(origins = "*")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private UsuarioService usuarioService;

    // =========================
    // REGISTRAR MOVIMIENTO
    // =========================
    @PostMapping("/movimiento")
    public ResponseEntity<?> registrarMovimiento(@RequestBody Inventario movimiento) {

        if (movimiento.getProducto() == null || movimiento.getUsuario() == null) {
            return ResponseEntity.badRequest().body("Producto o Usuario requerido");
        }

        Producto producto = productoService.obtener(movimiento.getProducto().getId());
        Usuario usuario = usuarioService.obtener(movimiento.getUsuario().getId());

        if (producto == null || usuario == null) {
            return ResponseEntity.badRequest().body("Producto o Usuario no válido");
        }

        movimiento.setProducto(producto);
        movimiento.setUsuario(usuario);

        try {
            Inventario nuevo = inventarioService.registrarMovimiento(movimiento);
            return ResponseEntity.ok(nuevo);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // =========================
    // LISTAR
    // =========================
    @GetMapping
    public ResponseEntity<List<Inventario>> listar() {
        return ResponseEntity.ok(inventarioService.listar());
    }

    // =========================
    // OBTENER POR ID
    // =========================
    @GetMapping("/{id}")
    public ResponseEntity<Inventario> obtener(@PathVariable Long id) {
        Inventario inv = inventarioService.obtenerPorId(id);
        return inv != null ? ResponseEntity.ok(inv) : ResponseEntity.notFound().build();
    }

    // =========================
    // ELIMINAR
    // =========================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        inventarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}