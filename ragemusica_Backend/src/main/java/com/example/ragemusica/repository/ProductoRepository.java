package com.example.ragemusica.repository;

import com.example.ragemusica.model.Producto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    List<Producto> findByArtistaId(Integer artistaId);
}
