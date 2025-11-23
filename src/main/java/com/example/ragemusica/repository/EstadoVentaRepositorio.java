package com.example.ragemusica.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ragemusica.model.EstadoVenta;

@Repository //componente de acceso a datos
public interface EstadoVentaRepositorio extends JpaRepository<EstadoVenta, Integer>{
    Optional<EstadoVenta> findByCodigo(String codigo);
}
