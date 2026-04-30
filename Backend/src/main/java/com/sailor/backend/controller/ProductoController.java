package com.sailor.backend.controller;

import com.sailor.backend.model.Producto;
import com.sailor.backend.dto.ProductoDTO;
import com.sailor.backend.service.ProductoService;
import com.sailor.backend.repository.ProductoRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductoController {

    private final ProductoRepository repository;
    private final ProductoService productoService;

    public ProductoController(ProductoRepository repository, ProductoService productoService) {
        this.repository = repository;
        this.productoService = productoService;
    }

    // GET - listar todos (sin paginación, para compatibilidad)
    @GetMapping
    public ResponseEntity<List<Producto>> obtenerProductos() {
        return ResponseEntity.ok(repository.findAll());
    }

    // GET - listar con paginación
    @GetMapping("/paginado")
    public ResponseEntity<Page<Producto>> obtenerProductosPaginados(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(productoService.listarPaginado(pageable));
    }

    // POST - guardar
    @PostMapping
    public ResponseEntity<Producto> guardarProducto(@Valid @RequestBody ProductoDTO productoDTO) {
        Producto producto = new Producto();
        producto.setNombreProducto(productoDTO.getNombreProducto());
        producto.setDescripcion(productoDTO.getDescripcion());
        producto.setPrecioProducto(productoDTO.getPrecioProducto());
        producto.setStock(productoDTO.getStock());
        return ResponseEntity.ok(repository.save(producto));
    }

    // GET por ID
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT - actualizar
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @Valid @RequestBody ProductoDTO productoDTO) {
        return repository.findById(id)
                .map(producto -> {
                    producto.setNombreProducto(productoDTO.getNombreProducto());
                    producto.setDescripcion(productoDTO.getDescripcion());
                    producto.setPrecioProducto(productoDTO.getPrecioProducto());
                    producto.setStock(productoDTO.getStock());
                    return ResponseEntity.ok(repository.save(producto));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}