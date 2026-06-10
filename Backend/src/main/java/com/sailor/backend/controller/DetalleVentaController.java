package com.sailor.backend.controller;

import com.sailor.backend.model.DetalleVenta;
import com.sailor.backend.service.DetalleVentaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalle-venta")
@CrossOrigin(origins = "*")
public class DetalleVentaController {

    @Autowired
    private DetalleVentaService service;

    @PostMapping
    public DetalleVenta crear(@RequestBody DetalleVenta detalle) {
        return service.guardar(detalle);
    }

    @GetMapping
    public List<DetalleVenta> listar() {
        return service.listar();
    }

    @GetMapping("/venta/{id}")
    public List<DetalleVenta> porVenta(@PathVariable Long id) {
        return service.porVenta(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}