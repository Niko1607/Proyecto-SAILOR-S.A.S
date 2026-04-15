package com.sailor.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sailor.backend.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

}