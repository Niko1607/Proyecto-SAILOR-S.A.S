package com.sailor.backend.service;

import com.sailor.backend.model.*;
import com.sailor.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleVentaService {

    @Autowired
    private DetalleVentaRepository detalleRepo;

    @Autowired
    private ProductoRepository productoRepo;

    @Autowired
    private VentaRepository ventaRepo;

    // =========================
    // GUARDAR DETALLE
    // =========================
    public DetalleVenta guardar(DetalleVenta detalle) {

        if (detalle == null || detalle.getProducto() == null || detalle.getVenta() == null) {
            throw new RuntimeException("Datos incompletos");
        }

        Producto producto = productoRepo.findById(detalle.getProducto().getId())
                .orElseThrow(() -> new RuntimeException("Producto no existe"));

        Venta venta = ventaRepo.findById(detalle.getVenta().getId())
                .orElseThrow(() -> new RuntimeException("Venta no existe"));

        // VALIDAR CANTIDAD
        if (detalle.getCantidad() <= 0) {
            throw new RuntimeException("Cantidad inválida");
        }

        // VALIDAR STOCK — usa getStock(), no getCantidad()
        if (producto.getStock() < detalle.getCantidad()) {
            throw new RuntimeException("Stock insuficiente");
        }

        // CALCULAR SUBTOTAL
        double precio = producto.getPrecioProducto();
        double subtotal = precio * detalle.getCantidad();

        detalle.setPrecioUnitario(precio);
        detalle.setSubtotal(subtotal);

        // DESCONTAR STOCK — usa getStock() / setStock()
        producto.setStock(producto.getStock() - detalle.getCantidad());
        productoRepo.save(producto);

        // ACTUALIZAR TOTAL VENTA — double primitivo, nunca es null
        venta.setTotal(venta.getTotal() + subtotal);
        ventaRepo.save(venta);

        detalle.setProducto(producto);
        detalle.setVenta(venta);

        return detalleRepo.save(detalle);
    }

    // =========================
    // LISTAR
    // =========================
    public List<DetalleVenta> listar() {
        return detalleRepo.findAll();
    }

    // =========================
    // POR VENTA
    // =========================
    public List<DetalleVenta> porVenta(Long ventaId) {
        return detalleRepo.findByVentaId(ventaId);
    }

    // =========================
    // ELIMINAR (DEVOLVER STOCK)
    // =========================
    public void eliminar(Long id) {

        DetalleVenta detalle = detalleRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Detalle no existe"));

        Producto producto = detalle.getProducto();
        Venta venta = detalle.getVenta();

        // DEVOLVER STOCK — usa getStock() / setStock()
        producto.setStock(producto.getStock() + detalle.getCantidad());
        productoRepo.save(producto);

        // RESTAR TOTAL — double primitivo, sin null check
        venta.setTotal(venta.getTotal() - detalle.getSubtotal());
        ventaRepo.save(venta);

        detalleRepo.deleteById(id);
    }
}
