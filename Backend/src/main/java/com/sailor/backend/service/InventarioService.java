package com.sailor.backend.service;

import com.sailor.backend.model.Inventario;
import com.sailor.backend.model.Producto;
import com.sailor.backend.model.Usuario;
import com.sailor.backend.repository.InventarioRepository;
import com.sailor.backend.repository.ProductoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    @Autowired
    private ProductoRepository productoRepository;

    // =========================
    // REGISTRAR MOVIMIENTO
    // =========================
    public Inventario registrarMovimiento(Inventario movimiento) {

        if (movimiento.getProducto() == null) {
            throw new RuntimeException("El producto es obligatorio");
        }

        if (movimiento.getCantidad() == 0) {
            throw new RuntimeException("La cantidad no puede ser 0");
        }

        Producto producto = productoRepository
                .findById(movimiento.getProducto().getId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        int nuevoStock = producto.getStock() + movimiento.getCantidad();

        if (nuevoStock < 0) {
            throw new RuntimeException("Stock insuficiente");
        }

        producto.setStock(nuevoStock);
        productoRepository.save(producto);

        return inventarioRepository.save(movimiento);
    }

    // =========================
    // OBTENER POR ID
    // =========================
    public Inventario obtenerPorId(Long id) {
        return inventarioRepository.findById(id).orElse(null);
    }

    // =========================
    // LISTAR TODOS
    // =========================
    public List<Inventario> listar() {
        return inventarioRepository.findAll();
    }

    // =========================
    // ELIMINAR
    // =========================
    public void eliminar(Long id) {
        inventarioRepository.deleteById(id);
    }

    public List<Inventario> buscarPorProducto(Producto producto) {
        return inventarioRepository.findByProducto(producto);
    }

    public List<Inventario> buscarPorUsuario(Usuario usuario) {
        return inventarioRepository.findByUsuario(usuario);
    }
}