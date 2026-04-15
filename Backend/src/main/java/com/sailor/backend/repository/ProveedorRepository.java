package com.sailor.backend.repository;

import com.sailor.backend.model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
    Optional<Proveedor> findByCorreo(String correo);
    Optional<Proveedor> findByIdentificacion(String identificacion);
}