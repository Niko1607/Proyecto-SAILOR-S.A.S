package com.sailor.backend.controller;

import com.sailor.backend.model.Proveedor;
import com.sailor.backend.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
@CrossOrigin(origins = "*")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    // Registrar
    @PostMapping("/registrar")
    public ResponseEntity<Proveedor> registrar(@RequestBody Proveedor proveedor) {
        return ResponseEntity.ok(proveedorService.registrarProveedor(proveedor));
    }

    // Listar todos
    @GetMapping
    public ResponseEntity<List<Proveedor>> listar() {
        return ResponseEntity.ok(proveedorService.listarTodos());
    }

    // Obtener por ID
    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> obtener(@PathVariable Long id) {
        Proveedor proveedor = proveedorService.obtenerPorId(id);
        return proveedor != null ? ResponseEntity.ok(proveedor) : ResponseEntity.notFound().build();
    }

    // Actualizar
    @PutMapping("/{id}")
    public ResponseEntity<Proveedor> actualizar(@PathVariable Long id, @RequestBody Proveedor proveedor) {
        Proveedor actualizado = proveedorService.actualizarProveedor(id, proveedor);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    // Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        proveedorService.eliminarProveedor(id);
        return ResponseEntity.noContent().build();
    }

    // Buscar por correo (endpoint adicional)
    @GetMapping("/buscar")
    public ResponseEntity<Proveedor> buscarPorCorreo(@RequestParam String correo) {
        Proveedor proveedor = proveedorService.buscarPorCorreo(correo);
        return proveedor != null ? ResponseEntity.ok(proveedor) : ResponseEntity.notFound().build();
    }
}