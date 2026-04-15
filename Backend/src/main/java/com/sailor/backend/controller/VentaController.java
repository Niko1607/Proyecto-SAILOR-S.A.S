package com.sailor.backend.controller;

import com.sailor.backend.model.Usuario;
import com.sailor.backend.model.Venta;
import com.sailor.backend.service.UsuarioService;
import com.sailor.backend.service.VentaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")
@CrossOrigin(origins = "*")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @Autowired
    private UsuarioService usuarioService;

    // CREAR VENTA
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Venta venta) {

        if (venta.getUsuario() == null) {
            return ResponseEntity.badRequest().body("Usuario requerido");
        }

        Usuario usuario = usuarioService.obtener(venta.getUsuario().getId());

        if (usuario == null) {
            return ResponseEntity.badRequest().body("Usuario no válido");
        }

        venta.setUsuario(usuario);

        return ResponseEntity.ok(ventaService.guardar(venta));
    }

    // LISTAR
    @GetMapping
    public List<Venta> listar() {
        return ventaService.listar();
    }

    // OBTENER
    @GetMapping("/{id}")
    public ResponseEntity<Venta> obtener(@PathVariable Long id) {
        Venta venta = ventaService.obtener(id);
        return venta != null ? ResponseEntity.ok(venta) : ResponseEntity.notFound().build();
    }

    // ELIMINAR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        ventaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Venta venta) {

        Venta existente = ventaService.obtener(id);

        if (existente == null) {
            return ResponseEntity.notFound().build();
        }

        existente.setTotal(venta.getTotal());
        existente.setEstado(venta.isEstado());

        return ResponseEntity.ok(ventaService.guardar(existente));
    }
}
