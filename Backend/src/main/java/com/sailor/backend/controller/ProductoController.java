package com.sailor.backend.controller;

import com.sailor.backend.model.Producto;
import com.sailor.backend.repository.ProductoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoRepository repository;

    public ProductoController(ProductoRepository repository) {
        this.repository = repository;
    }

    // GET - listar todos
    @GetMapping
    public List<Producto> obtenerProductos() {
        return repository.findAll();
    }

    // POST - guardar
    @PostMapping
    public Producto guardarProducto(@RequestBody Producto producto) {
        return repository.save(producto);
    }

    // GET por ID
    @GetMapping("/{id}")
    public Producto obtenerPorId(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}