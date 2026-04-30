package com.sailor.backend.service;

import com.sailor.backend.model.Producto;
import com.sailor.backend.repository.ProductoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository repository;

    public ProductoService(ProductoRepository repository) {
        this.repository = repository;
    }

    // Listar sin paginación (para compatibilidad)
    public List<Producto> listar() {
        return repository.findAll();
    }

    // Listar con paginación
    public Page<Producto> listarPaginado(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Producto guardar(Producto producto) {
        return repository.save(producto);
    }

    public Producto obtener(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}