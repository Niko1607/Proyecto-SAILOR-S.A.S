package com.sailor.backend.service;

import com.sailor.backend.model.Venta;
import com.sailor.backend.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    public Venta guardar(Venta venta) {
        return ventaRepository.save(venta);
    }

    public List<Venta> listar() {
        return ventaRepository.findAll();
    }

    public Venta obtener(Long id) {
        return ventaRepository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        ventaRepository.deleteById(id);
    }
}