package com.sailor.backend.service;

import com.sailor.backend.model.Proveedor;
import com.sailor.backend.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    // Registrar nuevo proveedor
    public Proveedor registrarProveedor(Proveedor proveedor) {
        // Podrías agregar validaciones (ej. correo único, identificación única)
        return proveedorRepository.save(proveedor);
    }

    // Listar todos
    public List<Proveedor> listarTodos() {
        return proveedorRepository.findAll();
    }

    // Buscar por ID
    public Proveedor obtenerPorId(Long id) {
        return proveedorRepository.findById(id).orElse(null);
    }

    // Actualizar
    public Proveedor actualizarProveedor(Long id, Proveedor proveedorActualizado) {
        Optional<Proveedor> optional = proveedorRepository.findById(id);
        if (optional.isPresent()) {
            Proveedor existente = optional.get();
            existente.setNombreProveedorEmpresa(proveedorActualizado.getNombreProveedorEmpresa());
            existente.setIdentificacion(proveedorActualizado.getIdentificacion());
            existente.setTelefono(proveedorActualizado.getTelefono());
            existente.setDireccion(proveedorActualizado.getDireccion());
            existente.setCorreo(proveedorActualizado.getCorreo());
            existente.setTipoProveedor(proveedorActualizado.getTipoProveedor());
            existente.setCiudad(proveedorActualizado.getCiudad());
            existente.setActivo(proveedorActualizado.isActivo());
            // fechaRegistro no se modifica normalmente
            return proveedorRepository.save(existente);
        }
        return null;
    }

    // Eliminar
    public void eliminarProveedor(Long id) {
        proveedorRepository.deleteById(id);
    }

    // Buscar por correo
    public Proveedor buscarPorCorreo(String correo) {
        return proveedorRepository.findByCorreo(correo).orElse(null);
    }
}
