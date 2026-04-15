package com.sailor.backend.repository;

import com.sailor.backend.model.Inventario;
import com.sailor.backend.model.Producto;
import com.sailor.backend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface InventarioRepository extends JpaRepository<Inventario, Long> {
    List<Inventario> findByProducto(Producto producto);
    List<Inventario> findByUsuario(Usuario usuario);
    List<Inventario> findByFechaMovimientoBetween(LocalDate inicio, LocalDate fin);
}